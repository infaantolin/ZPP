package arik_3;

public class Escritora implements Runnable {

	private boolean escribeNumeros;

	public Escritora(boolean escribeNumeros) {
		this.escribeNumeros = escribeNumeros;
	}

	@Override
	public void run() {
		try {
			while (true) {
				if (escribeNumeros) {
					for (int i = 1; i <= 30; i++) {
						System.out.println("[NUM] " + i);
						Thread.sleep(100); // pausa pequeña para mezclar salidas
					}
				} else {
					for (char c = 'a'; c <= 'z'; c++) {
						System.out.println("[LETRA] " + c);
						Thread.sleep(100); // pausa pequeña
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}