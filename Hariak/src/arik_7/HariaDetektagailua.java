package arik_7;

public class HariaDetektagailua extends Thread {

	ZaldiHaria h1;
	ZaldiHaria h2;
	ZaldiHaria h3;
	ZaldiHaria h4;

	public HariaDetektagailua(ZaldiHaria h1, ZaldiHaria h2, ZaldiHaria h3, ZaldiHaria h4) {
		this.h1 = h1;
		this.h2 = h2;
		this.h3 = h3;
		this.h4 = h4;
	}

	public void run() {
		while (h1.isAlive() && h2.isAlive() && h3.isAlive() && h4.isAlive()) {
			// Itxaron lasterketa amaitu arte
		}
		h1.amaitu();
		h2.amaitu();
		h3.amaitu();
		h4.amaitu();
	}
}