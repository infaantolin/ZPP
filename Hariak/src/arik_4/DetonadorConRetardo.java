package arik_4;

public class DetonadorConRetardo implements Runnable {

	private String nombre;
	private int contador;

	public DetonadorConRetardo(String nombre, int contador) {
		this.nombre = nombre;
		this.contador = contador;
	}

	@Override
	public void run() {
		try {
			while (contador > 0) {
				System.out.println("[" + nombre + "] contador: " + contador);
				contador--;
				Thread.sleep(500); // medio segundo para que se vea la salida
			}
			System.out.println("[" + nombre + "] >>> Ha finalizado.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
