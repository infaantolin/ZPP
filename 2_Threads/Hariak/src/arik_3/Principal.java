package arik_3;

public class Principal {

	public static void main(String[] args) {
		// Hilo que escribe números
		Thread h1 = new Thread(new Escritora(true));

		// Hilo que escribe letras
		Thread h2 = new Thread(new Escritora(false));

		// Iniciar hilos
		h1.start();
		h2.start();

		// El main termina, pero los hilos siguen trabajando indefinidamente
	}
}