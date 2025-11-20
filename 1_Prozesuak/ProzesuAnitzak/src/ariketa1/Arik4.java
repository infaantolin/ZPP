// Egin martxan dauden prozesuak erakusten dituen programa bat.

package ariketa1;

import java.io.IOException;
import java.io.InputStream;

public class Arik4 {

	public static void main(String[] args) {

		erakutsiProzesuak();

	}

	public static void erakutsiProzesuak() {

		ProcessBuilder pb = new ProcessBuilder("CMD", "/C", "tasklist");

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
