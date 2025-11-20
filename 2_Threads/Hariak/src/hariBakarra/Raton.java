package hariBakarra;
// Todo ocurre en el mismo hilo, por eso los ratones comen de uno en uno, nunca a la vez.

public class Raton {
	//atributos privados de la clase Raton
	private String nombre = ""; //nombre identificador del ratón.
	private int tiempo = 0; //número de segundos que tardará en comer.

	// Constructor
	// - Recibe el nombre y el tiempo.
	// - Asigna estos valores a los atributos del objeto.
	// - Así podemos crear ratones distintos, cada uno con su nombre y tiempo de comer.
	public Raton(String nombre, int tiempo) {
		super();
		this.nombre = nombre; 
		this.tiempo = tiempo; 
	}

	// Cada vez que el Raton come, espera n segundos
	public void comer() {
		try {
			System.out.printf("El Raton %s empieza a comer %n", nombre);
			Thread.sleep(tiempo * 1000); // bloquea el hilo principal durante el tiempo indicado (en milisegundos).
			// Al terminar la pausa, imprime que el ratón ha acabado de comer.
			System.out.printf("El Raton %s ha terminado de comer %n", nombre);
		} catch (InterruptedException e) { // Si el hilo es interrumpido mientras duerme, se captura la excepción InterruptedException
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Raton raton1 = new Raton("1", 4);
		Raton raton2 = new Raton("2", 2);
		Raton raton3 = new Raton("3", 5);

		raton1.comer();
		raton2.comer();
		raton3.comer();

		System.out.printf("Todos los Ratones han comido%n");
	}

}
