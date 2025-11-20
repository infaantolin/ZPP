// Windows-eko aplikazio bat exekuta ezazu. (Ohar-bloka, Word, Kalkulagailua, …)

package ariketa1;

import java.io.IOException;
//Importamos IOException, necesaria para capturar 
//errores de entrada/salida al intentar ejecutar el proceso.

public class Arik1 {

	public static void main(String[] args) {

		exekutatuApp();

	}

	public static void exekutatuApp() {

//ProcessBuilder pb = new ProcessBuilder("notepad");
		ProcessBuilder pb = new ProcessBuilder("calc");
		try {
			Process p = pb.start();
			// Llamamos a start() para iniciar el proceso.
            // Esto abrirá la calculadora.
            // La variable "p" representa el proceso que hemos lanzado.
            // (Con ella podríamos interactuar: cerrarlo, consultar si sigue activo, etc.)
		} catch (IOException e) {
			e.printStackTrace();
			// Si ocurre un error (por ejemplo, si no se encuentra el comando),
            // mostramos el error por pantalla.
		}
	}

}
