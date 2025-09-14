package ariketa2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.JTextArea;
import java.util.concurrent.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Ejer2GUI: Interfaz con 3 botones que realiza 3 tareas con procesos:
 *  1) Ejecuta un programa del sistema (Notepad).
 *  2) Ejecuta un comando en cmd (dir) y captura su salida.
 *  3) Llama 5 veces al programa EjemploLectura, le envía texto y recoge la salida.
 *
 * Comentarios extensos en el código para entender cada paso.
 */
public class Ejer2GUI extends JFrame {

    private final JTextArea salidaArea;
    private final ExecutorService executor;

    public Ejer2GUI() {
        super("EJERCICIO 2 - Multiproceso (GUI)");

        // Text area para mostrar resultados
        salidaArea = new JTextArea(20, 80);
        salidaArea.setEditable(false);
        JScrollPane sp = new JScrollPane(salidaArea);

        // Botones
        JButton btn1 = new JButton("1) Ejecutar programa (Notepad)");
        JButton btn2 = new JButton("2) Ejecutar comando (cmd dir)");
        JButton btn3 = new JButton("3) Llamar 5x EjemploLectura");

        // Panel de botones
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botones.add(btn1);
        botones.add(btn2);
        botones.add(btn3);

        // Configuración del frame
        this.setLayout(new BorderLayout());
        this.add(botones, BorderLayout.NORTH);
        this.add(sp, BorderLayout.CENTER);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Executor para ejecutar las tareas largas fuera del hilo de la GUI
        executor = Executors.newSingleThreadExecutor();

        // Listeners de botones: delegan a métodos que ejecutan procesos en background
        btn1.addActionListener(e -> executor.submit(this::ejecutarProgramaSistema));
        btn2.addActionListener(e -> executor.submit(this::ejecutarComandoCmd));
        btn3.addActionListener(e -> executor.submit(this::llamarCincoVecesEjemploLectura));
    }

    // Método auxiliar para escribir en la zona de salida (Thread-safe con invokeLater)
    private void appendSalida(String linea) {
        SwingUtilities.invokeLater(() -> {
            salidaArea.append(linea + "\n");
            salidaArea.setCaretPosition(salidaArea.getDocument().getLength());
        });
    }

    /**
     * Botón 1: Ejecuta un programa del sistema (ejemplo: notepad).
     * Muestra PID del proceso y PID del padre.
     */
    private void ejecutarProgramaSistema() {
        try {
            appendSalida("---- Ejecutando programa del sistema (notepad) ----");

            // Lanzamos el programa del sistema. En Windows usamos "notepad".
            ProcessBuilder pb = new ProcessBuilder("notepad");
            Process p = pb.start();

            long pid = p.pid(); // PID del proceso recién creado
            long padrePid = ProcessHandle.of(pid).flatMap(ProcessHandle::parent).map(ProcessHandle::pid).orElse(-1L);

            appendSalida("PID del proceso (notepad): " + pid);
            appendSalida("PID del proceso padre: " + padrePid);

            // No esperamos a que notepad termine; se deja abierto.
            appendSalida("Notepad arrancado (no se espera a que termine).");

        } catch (IOException ex) {
            appendSalida("Error al ejecutar notepad: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Botón 2: Ejecuta un comando en cmd (dir),
     * recoge su salida (stdout + stderr) y el código de salida.
     */
    private void ejecutarComandoCmd() {
        appendSalida("---- Ejecutando comando: cmd /c dir ----");
        try {
            // Construimos el ProcessBuilder para cmd /c dir (Windows)
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir");
            pb.redirectErrorStream(true); // combinar stdout y stderr

            Process p = pb.start();
            long pid = p.pid();
            long padrePid = ProcessHandle.of(pid).flatMap(ProcessHandle::parent).map(ProcessHandle::pid).orElse(-1L);

            appendSalida("PID del proceso (cmd): " + pid);
            appendSalida("PID del proceso padre: " + padrePid);

            // Leemos la salida del comando
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            appendSalida("Salida del comando:");
            while ((line = reader.readLine()) != null) {
                appendSalida(line);
            }
            reader.close();

            // Esperamos a que termine para obtener el código de salida
            int exitCode = p.waitFor();
            appendSalida("Código de salida (cmd /c dir): " + exitCode);

        } catch (IOException ex) {
            appendSalida("IOException al ejecutar cmd: " + ex.getMessage());
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            appendSalida("Ejecución interrumpida: " + ex.getMessage());
        }
    }

    /**
     * Botón 3: Llama 5 veces al programa EjemploLectura.
     * Para cada invocación:
     *  - lanza la JVM con la clase EjemploLectura
     *  - envía por stdin un texto (el mismo base + número de instancia)
     *  - recoge la salida, el PID y el código de salida
     */
    private void llamarCincoVecesEjemploLectura() {
        appendSalida("---- Llamando 5 veces a EjemploLectura ----");

        // Pedimos al usuario el texto base a enviar
        String baseTexto = JOptionPane.showInputDialog(this, "Introduce el texto que se enviará a EjemploLectura:", "Texto para EjemploLectura", JOptionPane.QUESTION_MESSAGE);
        if (baseTexto == null) {
            appendSalida("Operación cancelada por el usuario.");
            return;
        }

        try {
            // Ruta al ejecutable java de la JVM actual: evita problemas si 'java' no está en PATH
            String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";

            // Classpath actual (incluye las clases compiladas)
            String classpath = System.getProperty("java.class.path");

            // Ejecutamos 5 instancias
            for (int i = 1; i <= 5; i++) {
                appendSalida("-> Lanzando EjemploLectura (instancia " + i + ") ...");

                // Preparamos ProcessBuilder para ejecutar: java -cp <classpath> ariketa1.EjemploLectura
                ProcessBuilder pb = new ProcessBuilder(javaBin, "-cp", classpath, "ariketa1.EjemploLectura");
                pb.redirectErrorStream(true);

                Process p = pb.start();
                long pid = p.pid();
                long padrePid = ProcessHandle.of(pid).flatMap(ProcessHandle::parent).map(ProcessHandle::pid).orElse(-1L);

                appendSalida("PID de EjemploLectura (instancia " + i + "): " + pid);
                appendSalida("PID del padre: " + padrePid);

                // Enviamos el texto al proceso por su stdin
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream(), "UTF-8"));
                String textoAEnviar = baseTexto + " (instancia " + i + ")";
                writer.write(textoAEnviar);
                writer.newLine(); // simula ENTER
                writer.flush();
                writer.close(); // cerramos stdin para indicar EOF al proceso hijo

                // Leemos la salida del proceso hijo
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                StringBuilder salidaProceso = new StringBuilder();
                String linea;
                while ((linea = reader.readLine()) != null) {
                    salidaProceso.append(linea).append("\n");
                }
                reader.close();

                // Esperamos a que termine y recogemos el código de salida
                int exitCode = p.waitFor();

                appendSalida("Salida de EjemploLectura (instancia " + i + "):");
                // mostramos la salida línea a línea para mayor claridad
                for (String s : salidaProceso.toString().split("\\r?\\n")) {
                    appendSalida("    " + s);
                }
                appendSalida("Código de salida (instancia " + i + "): " + exitCode);
                appendSalida("-----------------------------------------------");
            }

            appendSalida("Todas las invocaciones completadas.");

        } catch (IOException ex) {
            appendSalida("IOException al lanzar EjemploLectura: " + ex.getMessage());
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            appendSalida("Ejecución interrumpida: " + ex.getMessage());
        }
    }

    // Cierre ordenado del executor al cerrar la ventana
    @Override
    public void dispose() {
        super.dispose();
        executor.shutdownNow();
    }

    // Main para arrancar la GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ejer2GUI gui = new Ejer2GUI();
            gui.setVisible(true);
        });
    }
}
