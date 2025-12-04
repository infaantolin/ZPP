package argazkia;

import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Bezeroa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static final int TAM_PAKETE = 4096;
	static String fitxIzena = "error.jpg";
	final static int PORTUA = 5000;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			Socket bezeroa = new Socket("localhost", PORTUA);

			DataInputStream dos = new DataInputStream(bezeroa.getInputStream());
			int tam = dos.readInt();
			File fitxIrakurri = new File("kopia.jpg");
			FileOutputStream os = new FileOutputStream(fitxIrakurri);
			int veces = (int) tam / TAM_PAKETE;
			int resto = (int) tam - veces * TAM_PAKETE;
			byte[] leido = new byte[TAM_PAKETE];

			for (int i = 0; i < veces; i++) {
				dos.read(leido);
				os.write(leido);
			}
			if (resto != 0) {
				dos.read(leido, 0, resto);
				os.write(leido, 0, resto);
			}
			fitxIzena = "kopia.jpg";

			os.close();
			bezeroa.close();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ImageIcon fotoE = new ImageIcon(fitxIzena);
		JLabel lblNewLabel = new JLabel(fotoE);
		lblNewLabel.setBounds(50, 35, 308, 186);
		contentPane.add(lblNewLabel);
	}

}
