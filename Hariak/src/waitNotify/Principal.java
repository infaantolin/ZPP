package waitNotify;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trabajo trabajo = new Trabajo();
		Trabajador t1 = new  Trabajador(trabajo,true);
		Trabajador t2 = new  Trabajador(trabajo,false);
		t2.start();
		t1.start();
		
	}

}
