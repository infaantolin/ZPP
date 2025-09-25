package hariak;

public class Secuencial implements Runnable{

	private String nombre;
	private int tiempo_seg;
	
	public Secuencial (String nombre, int tiempo) {
		this.nombre= nombre;
		this.tiempo_seg = tiempo;
	}
	
	public void corre() {
		System.out.println("El proceso "+this.nombre+ " ha iniciado su ejecución.");
		try {
			Thread.sleep(this.tiempo_seg * 1000); //Simulamos el calculo del tiempo
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("FIN  -> ["+ this.nombre + "]");
	}
	
	public void run() {
		corre();
	}
	
	public static void main (String[] args) {
		Secuencial proc_1 = new Secuencial("ANA", 6);
		Secuencial proc_2 = new Secuencial("AITOR", 16);
		Secuencial proc_3 = new Secuencial("JON", 12);
		Secuencial proc_4 = new Secuencial("MAITE", 4);
		
		// Secuentzialki
//		  proc_1.run();
//		  proc_2.run(); 
//		  proc_3.run(); 
//		  proc_4.run();
		 
		
		// Asinkronoa
		new Thread(proc_1).start();
		new Thread(proc_2).start();
		new Thread(proc_3).start();
		new Thread(proc_4).start();
		
		
		
	}
}
