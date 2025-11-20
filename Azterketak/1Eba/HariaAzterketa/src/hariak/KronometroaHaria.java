package hariak;

import javax.swing.JLabel;

public class KronometroaHaria implements Runnable {

	private int ordua = 0;
	private int min = 1;
	private int seg = 0;
	private JLabel etiketa;
	private JLabel mezua;
	private String izena;
	private int aukera = 0; // 1 = martxan, 2 = pausatuta, 3 = bukatuta

	public KronometroaHaria(JLabel etiketa, JLabel mezua, String izena) {
		this.etiketa = etiketa;
		this.mezua = mezua;
		this.izena = izena;
		reset(); // inicia en 01:30:00
	}

	@Override
	public void run() {
		while (aukera != 3) {
			if (aukera == 1) {
				dekrementatu();
				bistaratu();
				if (ordua == 0 && min == 0 && seg == 0) {
					aukera = 3;
					mezua.setText("<<" + izena + ">> haria bukatu da.");
					break;
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void bistaratu() {
		String text = String.format("%02d:%02d:%02d", ordua, min, seg);
		etiketa.setText(text);
	}

	public void dekrementatu() {

		if (ordua == 0 && min == 0 && seg == 0) {
			return;
		}

		if (seg == 0) {
			seg = 59;
			if (min == 0) {
				min = 59;
				if (ordua > 0)
					ordua--;
			} else {
				min--;
			}
		} else {
			seg--;
		}
	}

	public void reset() {
		ordua = 0;
		min = 1;
		seg = 0;
		bistaratu();
		aukera = 0; // estado inicial
	}

	public int getAukera() {
		return aukera;
	}

	public void setAukera(int aukera) {
		this.aukera = aukera;
	}

}