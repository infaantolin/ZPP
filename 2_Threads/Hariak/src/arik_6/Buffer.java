package arik_6;

public class Buffer {

	private char edukia;
	private boolean bufferBetea = false;

	public Buffer() {
	}


	/**
	 * Ekoizleak karaktere bat gordetzen du bufferrean.
	 * 
	 * @param c gordeko den karakterea
	 */
	public synchronized void jarri(char c) {
		while (bufferBetea) { // bufferra beteta badago, itxaron
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		edukia = c;
		bufferBetea = true;
		System.out.println("Ekoizleak jarri du: " + c);
		notifyAll(); // Kontsumitzailea abisatu
	}

	/**
	 * Kontsumitzaileak karaktere bat jasotzen du bufferretik.
	 * 
	 * @return jasotako karakterea edo karaktere huts bat (ez badago ezer)
	 */
	public synchronized char jaso() {
		while (!bufferBetea) { // buffer hutsik badago, itxaron
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		bufferBetea = false;
		System.out.println("Kontsumitzaileak jaso du: " + edukia);
		notifyAll(); // Ekoizlea abisatu
		return edukia;
	}
}
