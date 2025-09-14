
package ariketa2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class VentanaProcesosCompleta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldPrograma;
	private JTextField textFieldComando;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                VentanaProcesosCompleta frame = new VentanaProcesosCompleta();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaProcesosCompleta frame = new VentanaProcesosCompleta();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}
	
	
	

	/**
	 * Create the frame.
	 */
	public VentanaProcesosCompleta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		// Etiquetas para mostrar PIDs
        JLabel lblPID = new JLabel("PID");
        lblPID.setBounds(10, 189, 45, 13);
        contentPane.add(lblPID);

        JLabel lblPadrePID = new JLabel("PID Padre");
        lblPadrePID.setBounds(10, 230, 77, 13);
        contentPane.add(lblPadrePID);

        JLabel lblP1Pid = new JLabel("");
        lblP1Pid.setBounds(84, 189, 84, 13);
        contentPane.add(lblP1Pid);

        JLabel lblP1PidP = new JLabel("");
        lblP1PidP.setBounds(84, 230, 121, 13);
        contentPane.add(lblP1PidP);

        JLabel lblP2Pid = new JLabel("");
        lblP2Pid.setBounds(229, 189, 77, 13);
        contentPane.add(lblP2Pid);

        JLabel lblP2PidPadre = new JLabel("");
        lblP2PidPadre.setBounds(229, 230, 77, 13);
        contentPane.add(lblP2PidPadre);

        
        // Área de texto para mostrar resultados
        JTextPane textPane = new JTextPane();
        textPane.setBounds(163, 258, 400, 363);
        contentPane.add(textPane);
		
		
        // -------------------------------
        // Botón 1 -> Ejecutar programa (ej: notepad)
        // -------------------------------
        JButton btnP1Start = new JButton("Start Programa");
        btnP1Start.addActionListener(e -> {
            try {
                ProcessBuilder bd = new ProcessBuilder(textFieldPrograma.getText());
                Process p = bd.start();

                lblP1Pid.setText(String.valueOf(p.pid())); // PID
                lblP1PidP.setText(String.valueOf(p.toHandle().parent().get().pid())); // PID padre

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        btnP1Start.setBounds(72, 140, 140, 21);
        contentPane.add(btnP1Start);

        // -------------------------------
        // Botón 2 -> Ejecutar comando en consola (ej: dir)
        // -------------------------------
        JButton btnP2Start = new JButton("Start Comando");
        btnP2Start.addActionListener(e -> {
            try {
                ProcessBuilder bd = new ProcessBuilder("cmd", "/c", textFieldComando.getText());
                Process p = bd.start();

                lblP2Pid.setText(String.valueOf(p.pid()));
                lblP2PidPadre.setText(String.valueOf(p.toHandle().parent().get().pid()));

                // Leemos salida del proceso
                BufferedReader brer = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while ((line = brer.readLine()) != null)
                    textPane.setText(textPane.getText() + line + "\n");

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        btnP2Start.setBounds(212, 140, 140, 21);
        contentPane.add(btnP2Start);

        // Campo de texto para el programa
        textFieldPrograma = new JTextField();
        textFieldPrograma.setBounds(72, 100, 96, 19);
        contentPane.add(textFieldPrograma);
        textFieldPrograma.setColumns(10);

        // Campo de texto para el comando
        textFieldComando = new JTextField();
        textFieldComando.setBounds(213, 100, 96, 19);
        contentPane.add(textFieldComando);
        textFieldComando.setColumns(10);

        JLabel lblResultado = new JLabel("Resultado");
        lblResultado.setBounds(10, 298, 64, 13);
        contentPane.add(lblResultado);

        
        // -------------------------------
        // Botón 3 -> Llamar 5 veces a EjemploLectura
        // -------------------------------
        JButton btnP3Start = new JButton("Start 5x EjemploLectura");
        btnP3Start.addActionListener(e -> {
            try {
                // Preguntamos al usuario un texto base
                String baseTexto = JOptionPane.showInputDialog(this, "Introduce un texto:");
                if (baseTexto == null) return;

                // Ruta al binario de java
                String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
                // Classpath actual
                String classpath = System.getProperty("java.class.path");

                for (int i = 1; i <= 5; i++) {
                    // Preparamos ProcessBuilder para ejecutar EjemploLectura
                    ProcessBuilder pb = new ProcessBuilder(javaBin, "-cp", classpath, "ariketa1.EjemploLectura");
                    pb.redirectErrorStream(true);
                    Process p = pb.start();

                    // Enviamos texto a la entrada estándar del hijo
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
                    bw.write(baseTexto + " (ejecución " + i + ")");
                    bw.newLine();
                    bw.flush();
                    bw.close();

                    // Leemos salida del proceso
                    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String linea;
                    textPane.setText(textPane.getText() + "---- Ejecución " + i + " ----\n");
                    while ((linea = br.readLine()) != null) {
                        textPane.setText(textPane.getText() + linea + "\n");
                    }
                    br.close();

                    // Mostramos PID y exit code
                    int exitCode = p.waitFor();
                    textPane.setText(textPane.getText() +
                            "PID: " + p.pid() +
                            " | Padre: " + p.toHandle().parent().get().pid() +
                            " | Exit: " + exitCode + "\n\n");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        btnP3Start.setBounds(360, 140, 200, 21);
        contentPane.add(btnP3Start);
    }
}