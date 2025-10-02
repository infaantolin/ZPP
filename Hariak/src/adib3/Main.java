package adib3;

public class Main {

	public static void main(String[] args) {
		MiHilo hilo1 = new MiHilo("Hilo1", 500);
		MiHilo hilo2 = new MiHilo("Hilo2", 1000);
		Thread t1 = new Thread(hilo1);
		Thread t2 = new Thread(hilo2);

		t1.start();
		t2.start();
	}

}
