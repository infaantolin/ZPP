package hariak;
import hariak.Haria;
import hariak.HariaRunnable;

public class Main {

	public static void main(String[] args) {
		Haria proceso = new Haria();
		Thread proceso2 = new Thread(new HariaRunnable());
		
		proceso.start();
		proceso2.start();

	}

}
