package arik_2;

public class HiloRunnable implements Runnable {

	private String nombre;

	public HiloRunnable(String nombre) {
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
		
		Thread h1 = new Thread(new HiloRunnable("Runnable-1"));
		Thread h2 = new Thread(new HiloRunnable("Runnable-2"));
		
		h1.start();
		h2.start();

		// Bucle nagusia
		while (h1.isAlive() || h2.isAlive()) {
			System.out.println(">> HiloRunnable: Hariak oraindik lanean.");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(">> HiloRunnable: Bi hariak amaitu dira!");
	}

}
