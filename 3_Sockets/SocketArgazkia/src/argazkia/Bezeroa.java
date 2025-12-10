package argazkia;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Bezeroa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static String fitxIzena = "error.jpg";
	final static int PORTUA = 5000;
	private JLabel lblArgazkia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bezeroa frame = new Bezeroa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Bezeroa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblArgazkia = new JLabel();
		lblArgazkia.setBounds(20, 20, 350, 320);
		add(lblArgazkia);

		argazkiaJaso();

		setVisible(true);
	}

	private void argazkiaJaso() {
		final String HOST = "127.0.0.1"; // localhost
		final int PORTUA = 5000;

		try {
			Socket bezeroa = new Socket(HOST, PORTUA);

			InputStream is = bezeroa.getInputStream();
			DataInputStream dis = new DataInputStream(is);

			// Jasotako fitxategiaren tamaina irakurri
			int tam = dis.readInt();
			if (tam <= 0) {
				erakutsiNoDisponible();
				return;
			}

			// buffer-a sortu tamaina horrekin
			byte[] argazkiData = new byte[tam];

			// Rellenar el array
			dis.readFully(argazkiData);

			// Argazkia bistaratzeko modua aukeratu/galdetu
			bistaratzekoModuaAukeratu(argazkiData);
			
			bezeroa.close();

		} catch (IOException e) {
			System.out.println("Errorea:" + e.getMessage());
			erakutsiNoDisponible();
		}
	}

	private void bistaratzekoModuaAukeratu(byte[] argazkiData) {

		String[] aukerak = { "1. Gordeta eta erakutsi (kopia.jpg)", "2. Zuzen-zuzenean erakutsi" };

		int aukera = JOptionPane.showOptionDialog(this, "Aukeratu zer egin nahi duzun:", "Aukerak",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, aukerak, aukerak[0]);

		if (aukera == 0) {
			// 1 aukera: Gorde kopia bat eta erakutsi
			try (FileOutputStream fos = new FileOutputStream("kopia.jpg")) {
				fos.write(argazkiData);
			} catch (Exception e) {
				System.err.println("Errorea kopia gordetzean.");
			}
			erakutsiIrudiaFitxategitik("kopia.jpg");

		} else {
			// 2, aukera: erakutsi zuzenean
			erakutsiIrudiaByte(argazkiData);
		}
	}

	private void erakutsiIrudiaFitxategitik(String helbidea) {
		ImageIcon icon = new ImageIcon(helbidea);
		Image img = icon.getImage().getScaledInstance(350, 320, Image.SCALE_SMOOTH);
		lblArgazkia.setIcon(new ImageIcon(img));
	}

	private void erakutsiIrudiaByte(byte[] datuak) {
		ImageIcon icon = new ImageIcon(datuak);
		Image img = icon.getImage().getScaledInstance(350, 320, Image.SCALE_SMOOTH);
		lblArgazkia.setIcon(new ImageIcon(img));
	}

	private void erakutsiNoDisponible() {
		ImageIcon icon = new ImageIcon("noDisponible.jpg");
		Image img = icon.getImage().getScaledInstance(350, 320, Image.SCALE_SMOOTH);
		lblArgazkia.setIcon(new ImageIcon(img));
	}

}
