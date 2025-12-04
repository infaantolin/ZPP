package argazkia;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Zerbitzaria {

	public static void main(String[] args) {
		final int TAM_PAKETE = 4096;
		final int PORTUA = 5000;
		
		try {
			ServerSocket zerbitzaria = new ServerSocket(PORTUA);
			System.out.println("Zerbitzaria itxoiten");
			Socket bezeroa = zerbitzaria.accept();
			System.out.println(bezeroa.getPort() + " portuan bezeroa konektatu da.");

			DataOutputStream dos = new DataOutputStream(bezeroa.getOutputStream());
			
			File bidaltzekoFitx = new File("faro.jpg");
			int tam = (int) bidaltzekoFitx.length();
			dos.writeInt(tam);

			FileInputStream is = new FileInputStream(bidaltzekoFitx);
			int zenbat = (int) tam / TAM_PAKETE;
			int hondarra = (int) tam - zenbat * TAM_PAKETE;
			byte[] irakurrita = new byte[TAM_PAKETE];

			for (int i = 0; i < zenbat; i++) {
				is.read(irakurrita);
				dos.write(irakurrita);
			}
			if (hondarra != 0) {
				is.read(irakurrita, 0, hondarra);
				dos.write(irakurrita, 0, hondarra);
			}
			
			is.close();
			dos.close();
			bezeroa.close();
			zerbitzaria.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
