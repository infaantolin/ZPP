package adib1;

public class Main {

	public static void main(String[] args) {
		MiHilo hilo1 = new MiHilo("Hilo1");
		MiHilo hilo2 = new MiHilo("Hilo2");
		
		hilo1.start();
		hilo2.start();
	}

}
