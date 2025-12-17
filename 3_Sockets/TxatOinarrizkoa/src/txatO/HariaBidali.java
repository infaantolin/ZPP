package txatO;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class HariaBidali extends Thread {

	Socket bezeroa = null;
	boolean jarraitu = true;

	public HariaBidali(Socket bezeroa) {
		this.bezeroa = bezeroa;
	}

	public void run() {
		try {
			DataOutputStream dos = new DataOutputStream(bezeroa.getOutputStream());
			Scanner sc = new Scanner(System.in);
			while (jarraitu) {
				String texto = sc.nextLine();
				dos.writeUTF(texto);
				if (texto.equalsIgnoreCase("irten"))
					jarraitu = false;
			}

			sc.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Bidali haritik irtetzen...");

	}

	public void bukatu() {
		jarraitu = false;
		System.exit(0);

	}
}
