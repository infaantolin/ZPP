package arik_1;

public class Zenbatzailea implements Runnable {

	private String izena;

	public Zenbatzailea(String izena) {
		this.izena = izena;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 1000; i++) {
			System.out.println("[" + izena + "] -> " + i);
		}
		System.out.println(">>> Haria [" + izena + "] amaitu da!");
	}

	public static void main(String[] args) {
		// Bi hari sortu
		Thread h1 = new Thread(new Zenbatzailea("Haria 1"));
		Thread h2 = new Thread(new Zenbatzailea("Haria 2"));

		// Abiarazi
		h1.start();
		h2.start();
	}
}