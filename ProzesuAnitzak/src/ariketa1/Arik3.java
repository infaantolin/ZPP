// Egin MAC helbidea lortzen duen eta pantailan erakusten duen programa bat

package ariketa1;

import java.io.IOException;
import java.io.InputStream;

public class Arik3 {

	public static void main(String[] args) {

		eskuratuMAC();

	}

	public static void eskuratuMAC() {

		ProcessBuilder pb = new ProcessBuilder("CMD", "/C", "getmac");

		try {

			Process p = pb.start();

			InputStream is = p.getInputStream();

			int c;
			while ((c = is.read()) != -1)
				System.out.print((char) c);

			is.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
