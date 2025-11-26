package adib1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Bezeroa {

	public static void main(String[] args) {

		final int PORT_ZERB = 5000;
		byte[] buffer = new byte[1024];

		try {
			InetAddress helbideaZerb = InetAddress.getByName("localhost"); // para decirle luego al servidor donde estoy

			DatagramSocket socketUDP = new DatagramSocket(); // sin puerto

			String mezua = "Kaixo bezerotik!";
			buffer = mezua.getBytes();

			DatagramPacket galdera = new DatagramPacket(buffer, buffer.length, helbideaZerb, PORT_ZERB);
			System.out.println("Datagrama bidaltzen dut.");
			socketUDP.send(galdera);

			// Jaso
			byte[] bufferResp = new byte[1024];
			DatagramPacket eskaera = new DatagramPacket(bufferResp, bufferResp.length);
			socketUDP.receive(eskaera);
			System.out.println("Erantzuna jasotzen dut.");

			// LEER SOLO BYTES REALES
			String erantzuna = new String(eskaera.getData(), 0, eskaera.getLength());
			System.out.println(erantzuna);

			socketUDP.close();

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
