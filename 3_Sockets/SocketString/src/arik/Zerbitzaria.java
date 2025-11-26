package arik;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Zerbitzaria {

	public static void main(String[] args) {
		final int portua = 5000;

		try {
			ServerSocket zerbitzaria = new ServerSocket(portua);

			for (int i = 0; i < 3; i++) {
				Socket bezero = zerbitzaria.accept();
				InputStream is = bezero.getInputStream();
				DataInputStream dis = new DataInputStream(is);
				String bezeroMezua = dis.readUTF();
				System.out.println("Jasota: " + bezeroMezua);

				OutputStream os = bezero.getOutputStream();
				DataOutputStream dos = new DataOutputStream(os);
				dos.writeUTF((i + 1) + " zenbakidun bezeroari zerbitzaritik agurrak");

				dos.close();
				dis.close();
				bezero.close();

			}
			System.out.println("Bezero gehiegi gaurkoz");

			zerbitzaria.close();

		} catch (IOException e) {
			System.out.println("Errorea: " + e.getMessage());
		}

	}

}
