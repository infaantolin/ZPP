package txatO;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class HariaJaso extends Thread {

	Socket bezeroa = null;
	boolean jarraitu = true;

	public HariaJaso(Socket bezeroa) {
		this.bezeroa = bezeroa;
	}

	public void run() {
		DataInputStream dis;
		try {
			dis = new DataInputStream(bezeroa.getInputStream());

			while (jarraitu) {
				String testua = dis.readUTF();

				System.out.println(testua);

				if (testua.equalsIgnoreCase("irten"))
					jarraitu = false;

			}
			System.out.println("Jaso haritik irtetzen...");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void bukatu() {
		jarraitu = false;
		System.exit(0);
	}

}
