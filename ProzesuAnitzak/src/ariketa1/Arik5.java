// Egizu ohar-bloka exekutatzen ari den detektatzeko programa bat, eta, hala bada, sortu exekuziotik ezabatuko duen prozesu bat (prozesua hil).
// Gestionar procesos del sistema operativo. -> Matar un proceso que está en ejecución.

package ariketa1;

import java.io.IOException; // IOException: manejar errores de entrada/salida.
import java.io.InputStream; // InputStream: para recibir la salida del proceso.
import java.io.BufferedReader;
import java.io.InputStreamReader; // InputStreamReader y BufferedReader: para leer la salida línea a línea.

public class Arik5 {

	public static void main(String[] args) {

		killNotepad();

	}

	public static void killNotepad() {
		// Este método lanza un comando del sistema para listar procesos activos
        // y, si encuentra "notepad", lo finaliza.
		
		try {

			ProcessBuilder pb = new ProcessBuilder("CMD", "/C", "tasklist");
			Process p = pb.start();

			InputStream is = p.getInputStream();

			// Creamos un BufferedReader para leer la salida línea a línea.
			BufferedReader brer = new BufferedReader(new InputStreamReader(is));

			// Variable que almacenará cada línea de la salida del comando.
			String linea = null;
			
			// Leemos línea a línea la salida de "tasklist"
            // hasta que no haya más datos (readLine devuelve null).
			while ((linea = brer.readLine()) != null)

				// Si en la línea aparece la palabra "notepad", significa que el proceso del Bloc de notas está en ejecución.
				if (linea.contains("notepad")) {

					// Creamos un nuevo ProcessBuilder para ejecutar:
                    // "taskkill /IM notepad.exe /F"
                    // - taskkill: comando para finalizar procesos.
                    // - /IM notepad.exe: especifica el nombre del proceso a cerrar.
                    // - /F: fuerza el cierre del proceso.
					ProcessBuilder pkill = new ProcessBuilder("CMD", "/C", "taskkill /IM notepad.exe /F");
					
					// Ejecutamos el comando que cierra el Bloc de notas.
					Process pk = pkill.start();

				}

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
