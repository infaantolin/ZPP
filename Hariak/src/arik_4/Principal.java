package arik_4;

public class Principal {

	public static void main(String[] args) {
		Thread h1 = new Thread(new DetonadorConRetardo("Hilo-1", 5));
		Thread h2 = new Thread(new DetonadorConRetardo("Hilo-2", 7));
		Thread h3 = new Thread(new DetonadorConRetardo("Hilo-3", 4));
		Thread h4 = new Thread(new DetonadorConRetardo("Hilo-4", 6));

		h1.start();
		h2.start();
		h3.start();
		h4.start();

		// Esperar a que los 4 hilos terminen antes de que acabe main
		try {
			h1.join();
			h2.join();
			h3.join();
			h4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(">>> Todos los hilos han terminado. Fin del programa.");
	}
}
