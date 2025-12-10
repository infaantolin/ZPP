package argazkia;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Zerbitzaria {

	public static void main(String[] args) {
		final int PORTUA = 5000;

		try {
			ServerSocket zerbitzaria = new ServerSocket(PORTUA);
			System.out.println("Zerbitzaria itxoiten");

			while (true) {
				Socket bezeroa = zerbitzaria.accept();
				System.out.println(bezeroa.getPort() + " portuan bezeroa konektatu da.");

				File fitxategia = new File("faro.jpg");
				byte[] datuak = new byte[(int) fitxategia.length()];
				

				FileInputStream fis = new FileInputStream(fitxategia);
				BufferedInputStream bis = new BufferedInputStream(fis);
	            bis.read(datuak, 0, datuak.length);

	            DataOutputStream dos = new DataOutputStream(bezeroa.getOutputStream()); //Tamaina bidaltzeko
	            dos.writeInt(datuak.length); // Bezeroak fitxategiaren luzeera behar duelako
	            dos.write(datuak); // Argazkia
	            dos.flush();

	            System.out.println("Imagen enviada correctamente.");

				bis.close();
				dos.close();
				bezeroa.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Errorea:" + e.getMessage());
		}
	}

}
