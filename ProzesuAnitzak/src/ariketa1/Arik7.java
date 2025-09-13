// programa que llama a otro programa que envía una cadena de caracteres y muestre el texto.

package ariketa1;

import java.io.IOException; // IOException: manejar errores de entrada/salida.
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader; // InputStreamReader y BufferedReader: para leer la salida línea a línea.


public class Arik7 {

	public static void main(String[] args) {

		try {
			// Pedimos al usuario que escriba un texto
			BufferedReader brUsuario = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Introduce un texto: ");
			String textoUsuario = brUsuario.readLine();

			// Creamos el ProcessBuilder para ejecutar "EjemploLectura"
			ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", "ariketa1.EjemploLectura");
			// -java → ejecuta la JVM.
			// -cp bin → define el classpath donde están las clases compiladas.
			// -ariketa1.EjemploLectura → clase con su nombre totalmente cualificado (incluye el paquete).
			
			pb.redirectErrorStream(true); // combinamos salida de error con salida normal

			// Iniciamos el proceso
			Process p = pb.start();

			// Enviamos el texto del usuario al proceso EjemploLectura
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
			bw.write(textoUsuario);
			bw.newLine(); // simulamos ENTER
			bw.flush();
			bw.close();

			// Recogemos la salida del proceso
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String linea;
			while ((linea = br.readLine()) != null) {
				System.out.println("Salida del proceso: " + linea);
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}