package arik_6;

public class Exekutagarria {

	public static void main(String[] args) {
		Buffer b = new Buffer();
		Ekoizlea e = new Ekoizlea(b);
		Kontsumitzailea k = new Kontsumitzailea(b);

		e.start();
		k.start();

		// Haria nagusiak besteak amaitu arte itxarongo du
		try {
			e.join();
			k.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		System.out.println(">>> Programa amaitu da (hari guztiak hil dira).");
	}
}