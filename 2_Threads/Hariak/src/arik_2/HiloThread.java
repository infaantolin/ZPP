package arik_2;

public class HiloThread extends Thread {

	private String nombre;

	public HiloThread(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void run() {
		System.out.println("Haria " + nombre + " hasi da.");
		try {
			for (int i = 0; i < 5; i++) {
				System.out.println("[" + nombre + "] -> iterazioa " + i);
				Thread.sleep(500); // simulazio
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Haria " + nombre + " amaitu da.");
	}

	public static void main(String[] args) {
		HiloThread h1 = new HiloThread("Thread-1");
		HiloThread h2 = new HiloThread("Thread-2");

		h1.start();
		h2.start();

		// Bucle nagusia
		while (h1.isAlive() || h2.isAlive()) {
			System.out.println(">> HiloThread: Hariak oraindik lanean.");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(">> HiloThread: Bi hariak amaitu dira!");
	}

}
