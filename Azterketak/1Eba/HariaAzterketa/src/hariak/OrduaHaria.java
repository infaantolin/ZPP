package hariak;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

public class OrduaHaria extends Thread {

	private JLabel labelOrdua;
	private boolean jarraitu = true;

	public OrduaHaria(JLabel labelOrdua) {
		this.labelOrdua = labelOrdua;
	}

	public void run() {
		while (jarraitu) {
			Date date = new java.util.Date();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String UnekoOrdua = sdf.format(date);
			labelOrdua.setText(UnekoOrdua);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void bukatuOrdua() {
		jarraitu = false;
	}
}