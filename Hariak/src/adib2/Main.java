package adib2;

public class Main {

	public static void main(String[] args) {
		MiHilo hilo1 = new MiHilo("Hilo1", 500);
		MiHilo hilo2 = new MiHilo("Hilo2", 1000);
		
		hilo1.start();
		hilo2.start();
	}

}
