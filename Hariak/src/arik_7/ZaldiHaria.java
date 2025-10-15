package arik_7;

import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

// ----- HariaZaldia klasea -----
public class ZaldiHaria extends Thread {

	private JProgressBar barra;
	private JLabel irabazlea;
	boolean jarraitu = true;

	public ZaldiHaria(JProgressBar barra, String izena, JLabel irabazlea) {
		this.barra = barra;
		this.setName(izena);
		this.irabazlea = irabazlea;
	}

	public void run() {
		int i = 0;
		while (jarraitu) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (i < barra.getMaximum()) {
				Random rd = new Random();
				i = i + rd.nextInt(3); // mugimendu aleatorioa
				barra.setValue(i);
			} else {
				jarraitu = false;
				irabazlea.setText("Irabazlea: " + this.getName() + " 🏆");
			}
		}
	}

	public void amaitu() {
		jarraitu = false;
	}
}
