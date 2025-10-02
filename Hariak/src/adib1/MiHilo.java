package adib1;

public class MiHilo extends Thread {
	// Nombre del hilo
	private String nombre;

	// Constructor
	public MiHilo(String nombreCons) {
		nombre = nombreCons;
	}

	// sobre escribimos el metodo run.
	// Con el metodo run --> vamos a ejecutar cualquier cosa en paralelo.
	@Override
	public void run() {
		//
		for (int i = 0; i < 5; i++) {
			System.out.println(nombre + " " + i);
			// El hilo va a tardar 1 segundo en contar
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
