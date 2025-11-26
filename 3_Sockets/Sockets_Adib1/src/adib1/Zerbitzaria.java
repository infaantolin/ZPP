package adib1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Zerbitzaria {

	public static void main(String[] args) {

		final int PORT = 5000;

		// creo que se crea automaticamente al crear el mensaje
		byte[] buffer = new byte[1024]; // buffer que vamos a utilizar (array de bytes)

		try {
			System.out.println("UDP zerbitzaria hasieratuta");
			DatagramSocket socketUDP = new DatagramSocket(PORT); // le decimos en qué puerto estamos, pero no la IP

			while (true) {
				// Creamos una petición
				// Creamos un paquete que va a contener este buffer con esta longitud
				DatagramPacket eskaera = new DatagramPacket(buffer, buffer.length);

				// recibimos
				socketUDP.receive(eskaera);
				System.out.println("Bezeroaren informazioa heltzen zait.");

				// tengo que saber quien me contacta, el puerto y la ip
//				String mezua = new String(eskaera.getData()); // a partir del buffer (mensaje que me manda) hacemos un
//																// String
				
				String mezua = new String(eskaera.getData(), 0, eskaera.getLength()); // lee SOLO los bytes válidos del datagrama
				System.out.println("Jasotako mezua: " + mezua);

				int bezeroPortua = eskaera.getPort(); // me da el puerto
				InetAddress helbidea = eskaera.getAddress(); // donde está esa persona que me ha contactado (una
																// dirección)

				// Respuesta del servidor al cliente
				String erantzuna = "Kaixo zerbitzaritik!";
				byte[] bufferErantzuna = erantzuna.getBytes(); // devolver de una cadena hace un array de bytes

				// creo un nuevo paquete y además le añado el puerto y la dirección
				DatagramPacket packetErantzuna = new DatagramPacket(bufferErantzuna, bufferErantzuna.length, helbidea, bezeroPortua);

				System.out.println("Bezeroari informazioa bidaltzen diot.");
				socketUDP.send(packetErantzuna);
				
				// MUY IMPORTANTE: limpiar buffer antes de próxima recepción
                buffer = new byte[1024];
			}

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
