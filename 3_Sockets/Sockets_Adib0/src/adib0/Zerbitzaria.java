package adib0;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Zerbitzaria {

	public static void main(String[] args) {

		
		ServerSocket zerbitzaria = null; // Socket del servidor
		Socket sc = null; // Socket del cliente
		final int PORT = 5000;
		
		// Para crear los puentes
		DataInputStream in;
		DataOutputStream out;
		
		try {
			zerbitzaria = new ServerSocket(PORT);
			System.out.println("Zerbitzaria hasieratuta.");
			
			while(true) {
				sc = zerbitzaria.accept(); // se queda a la espera de algún cliente haga una request
				
				System.out.println("Bezeroa konektatuta");
				in = new DataInputStream(sc.getInputStream()); // comunicación del cliente al servidor
				out = new DataOutputStream(sc.getOutputStream()); // comunicación desde el servidor al cliente
				
				String mezua = in.readUTF(); // se queda a la espera de que el cliente diga algo  (mensaje del cliente)
				
				System.out.println(mezua);
				
				out.writeUTF("Kaixo zerbitzaritik!"); //mensaje desde el servidor
				
				sc.close(); // bezeroa ixten dugu
				System.out.println("Bezeroa deskonektatuta");
				
				
			}
		}catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		

	}

}
