package adib0;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Bezero {

	public static void main(String[] args) {

		final String HOST = "127.0.0.1"; // para conectarme a mi propia maquina
		final int PORT = 5000;

		// Para crear los puentes
		DataInputStream in;
		DataOutputStream out;

		try {
			Socket sc = new Socket(HOST, PORT);

			in = new DataInputStream(sc.getInputStream()); // comunicación del cliente al servidor
			out = new DataOutputStream(sc.getOutputStream()); // comunicación desde el servidor al cliente

			// mando un mensaje al servidor
			out.writeUTF("Kaixo bezerotik!");

			String mezua = in.readUTF();

			System.out.println(mezua);

			sc.close();

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
