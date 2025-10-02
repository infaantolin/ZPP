package arik_2;

public class Ej3 implements Runnable {

	private String persona;

	public Ej3(String persona) {
		this.persona = persona;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 3; i++) {
				System.out.println("[" + persona + "] operazioa " + i);
				Thread.sleep(10); // 10 ms simulazioa
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("[" + persona + "] amaitu du bere lana.");
	}

	public static void main(String[] args) {
		Thread p1 = new Thread(new Ej3("Pertsona 1"));
		Thread p2 = new Thread(new Ej3("Pertsona 2"));

		p1.start();
		p2.start();

		// Bukaera itxaron
		try {
			p1.join();
			p2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(">> Bi pertsonak amaitu dute.");
	}
}