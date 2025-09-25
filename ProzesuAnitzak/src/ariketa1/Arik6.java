// Aurrez prestatutako “.bat” bat exekutatzen duen programa, fitxategi batean irteerako datuak eta beste batean egindako akatsak jasotzen dituena.
// Este ejemplo enseña cómo ejecutar un archivo por lotes (.bat) desde Java usando ProcessBuilder, y además cómo redirigir la salida y los errores del proceso a archivos externos.

package ariketa1;

import java.io.File; // manejar ficheros (en este caso, para redirigir salida y errores).
import java.io.IOException;

public class Arik6 {

	public static void main(String[] args) {

		exekutatuBat();

	}

	public static void exekutatuBat() {
		// Este método ejecuta un archivo batch (.bat) mediante ProcessBuilder.
		
		// EJEMPLO INCORRECTO (provocará error si se deja así):
		//ProcessBuilder pb = new ProcessBuilder("CMD", "/C", "Users\\infaantolin\\Desktop\\nireBat.bat");

		// KONTUZ - se ejecutará el .bat
		ProcessBuilder pb = new ProcessBuilder("CMD","/C","\\Users\\Aitzi\\Desktop\\nireBat.bat");
		// Esta sería la ruta correcta al archivo .bat.
        // "CMD" abre la consola de Windows.
        // "/C" indica que ejecute el comando y luego termine.
        // Luego se pasa la ruta del archivo batch a ejecutar.
		
		// Redirigimos la salida normal del proceso a un archivo de texto.
		pb.redirectOutput(new File("salida.txt"));
		// Todo lo que normalmente saldría en la consola estándar (System.out) se guardará en "salida.txt".
		
		// Redirigimos la salida de error del proceso a otro archivo de texto.
		pb.redirectError(new File("error.txt"));
		// Todo lo que normalmente saldría en System.err (errores) se guardará en "error.txt".
		
		try {

			Process p = pb.start();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
