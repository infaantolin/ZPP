package txatO;

import java.io.IOException;
import java.net.Socket;

public class Bezeroa {

	public static void main(String[] args) {
		final String HOST = "127.0.0.1"; // localhost
		final int PORTUA = 5000;

		try {
			Socket bezeroa = new Socket(HOST, PORTUA);
			System.out.println("Bezeroa konektatuta");
			HariaBidali hb = new HariaBidali(bezeroa);
			hb.start();
			HariaJaso hj = new HariaJaso(bezeroa);
			hj.start();

			while (hb.isAlive() && hj.isAlive()) {

			}
			System.out.println("Elkarrizketaren amaiera.");
			hb.bukatu();
			hj.bukatu();

			bezeroa.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
