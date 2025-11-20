// Windows-eko komando bat exekutatu (Dir, Taskmgr, …) eta emaitza pantailatik bistaratu.
// captura y muestra la salida del proceso en la consola de Java.
package ariketa1;

import java.io.IOException; //Importamos IOException para capturar posibles errores de E/S.
import java.io.InputStream; //Importamos InputStream porque lo usaremos para leer la salida del proceso.

public class Arik2 {

	public static void main(String[] args) {

		exekutatuKomandoa();

	}

	public static void exekutatuKomandoa() {
		 // Este método ejecuta un comando en la terminal de Windows (CMD) y captura la salida del mismo.
		
		ProcessBuilder pb = new ProcessBuilder("CMD", "/C", "DIR");
		// Creamos un objeto ProcessBuilder para lanzar un proceso.
        // "CMD" → abre la consola de Windows.
        // "/C"  → le dice a CMD que ejecute el comando y termine.
        // "DIR" → es el comando que lista los archivos y carpetas del directorio actual.
		
		try {
			Process p = pb.start(); // Iniciamos el proceso. "p" representa el proceso en ejecución.
			
			InputStream is = p.getInputStream(); // Obtenemos el flujo de entrada del proceso. 
            // Este flujo contiene la salida que normalmente aparecería en la consola de Windows.
 
			int c; // Declaramos una variable para ir leyendo los caracteres.
			
			while ((c = is.read()) != -1)
				System.out.print((char) c);
			// Leemos el flujo de salida del proceso, carácter a carácter,
            // hasta que no haya más datos (cuando devuelve -1).
            // Cada carácter leído se convierte en char y se imprime por pantalla.
            // De esta manera, mostramos en la consola de Java la salida del comando DIR.
			
			is.close(); // Cerramos el flujo de entrada después de usarlo.
			
		} catch (IOException e) {
			e.printStackTrace();
			// Si ocurre algún error al ejecutar el proceso o al leer la salida,
            // capturamos la excepción y mostramos la traza de error.
		}

	}
}
