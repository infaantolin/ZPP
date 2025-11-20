package itzul;

import java.io.BufferedReader;
//import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

public class ProzesuItzultzailea {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			// ProcessBuilder prestatu Itzultzailea exekutatzeko
			ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", "itzul.Itzultzailea");
			pb.redirectErrorStream(true); // stdout eta stderr batera <-- garrantzitsua: kanalizazioa ez ixteko

			// Prozesua abiarazi
			Process p = pb.start();

			// PIDak erakutsi
			System.out.println("============= PROZESU ITZULTZAILEA ==============");
			System.out.println("PID (Itzultzailea): " + p.pid());
			System.out.println("PID gurasoa (Prozesu itzultzailea): " + p.toHandle().parent().get().pid());
			System.out.println("=================================================");
			System.out.println();

			// Erabiltzaileari hitza eskatu
			System.out.print("Idatzi hitza itzultzeko: ");
			String hitza = sc.nextLine();

			// Enviamos la palabra al stdin del proceso hijo
			try (OutputStream os = p.getOutputStream()) {
				String linea = hitza + System.lineSeparator();
				os.write(linea.getBytes());
				os.flush();
				// IMPORTANTE: cerramos el stream para indicar EOF al hijo
			}

			// Leemos toda la salida del hijo (puede ser varias líneas)
			StringBuilder irteera = new StringBuilder();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
				String line;
				while ((line = br.readLine()) != null) {
					irteera.append(line).append(System.lineSeparator());
				}
			}

			// Mostramos por consola y guardamos en fichero
			String resultado = irteera.toString().trim(); // quitar salto final si lo hay
			System.out.println("------------------------------------");
			System.out.println("Itzultzailearen emaitza: " + resultado);

			try (FileWriter fw = new FileWriter("irteera.txt")) {
				fw.write(resultado);
			}
			// Prozesua amaitu arte itxaron
			int kodea = p.waitFor();
			System.out.println();
			System.out.println("======================================");
			System.out.println("Itzultzaile prozesua amaitu da. Kodea:" + kodea);
			System.out.println("Emaitza 'irteera.txt' fitxategian gorde da.");
			System.out.println("======================================");

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}

}