package arik;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Bezeroa {

	public static void main(String[] args) {
		final int portua = 5000;

		try {
			Socket bezero = new Socket("localhost", portua);
			System.out.println("Zerbitzariarekin konexioa eginda");

			OutputStream os = bezero.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			dos.writeUTF("Kaixo zerbitzari, bezeroa naiz");

			InputStream is = bezero.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			String mensServer = dis.readUTF();
			System.out.println(mensServer);

			dis.close();
			dos.close();

			bezero.close();

		} catch (IOException e) {
			System.out.println("Errorea: " + e.getMessage());
		}

	}

}
