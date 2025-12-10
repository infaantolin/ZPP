package txatO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Zerbitzaria {

	public static void main(String[] args) {
		final int PORTUA = 5000;

		try {
			ServerSocket zerbitzaria = new ServerSocket(PORTUA);
			System.out.println("Zerbitzaria itxoiten");

			Socket bezeroa = zerbitzaria.accept();
			System.out.println(bezeroa.getPort() + " portuan bezeroa konektatu da.");

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
			zerbitzaria.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Errorea:" + e.getMessage());
		}

	}

}
