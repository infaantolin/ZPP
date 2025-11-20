package hariak;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HariakLeihoa extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JButton btnHasi;
	private JButton btnPausatu;
	private JButton btnGelditu;
	private JButton btnIrten;

	private JLabel lblKrono;
	private JLabel lblMezua;
	private JLabel lblOrduaPc;

	private OrduaHaria hariaOrdua;

	private KronometroaHaria h1;
	private Thread t1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HariakLeihoa frame = new HariakLeihoa();
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
	public HariakLeihoa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 566, 253);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnHasi = new JButton("Hasi");
		btnHasi.setBounds(29, 113, 89, 23);
		contentPane.add(btnHasi);
		btnHasi.addActionListener(this);

		btnPausatu = new JButton("Pausatu");
		btnPausatu.setBounds(128, 113, 89, 23);
		contentPane.add(btnPausatu);
		btnPausatu.addActionListener(this);
		btnPausatu.setEnabled(false);

		btnGelditu = new JButton("Gelditu");
		btnGelditu.setBounds(227, 113, 89, 23);
		contentPane.add(btnGelditu);
		btnGelditu.addActionListener(this);
		btnGelditu.setEnabled(false);

		btnIrten = new JButton("Irten");
		btnIrten.setBounds(326, 113, 89, 23);
		contentPane.add(btnIrten);
		btnIrten.addActionListener(this);

		lblKrono = new JLabel("01:30:00");
		lblKrono.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblKrono.setBounds(202, 29, 180, 53);
		contentPane.add(lblKrono);

		lblMezua = new JLabel("New label");
		lblMezua.setBounds(152, 173, 249, 14);
		contentPane.add(lblMezua);

		lblOrduaPc = new JLabel("");
		lblOrduaPc.setBounds(437, 113, 89, 14);
		contentPane.add(lblOrduaPc);

		hariaOrdua = new OrduaHaria(lblOrduaPc);
		hariaOrdua.setPriority(Thread.NORM_PRIORITY);
		hariaOrdua.setName("ordua");
		hariaOrdua.start();
		lblMezua.setText("<<" + hariaOrdua.getName() + ">> haria martxan dago.");

		// Kronometroaren haria hasieratu
		h1 = new KronometroaHaria(lblKrono, lblMezua, "kronometroa");
		t1 = new Thread(h1);
		t1.setName("kronometroa");
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnHasi) {
			// Si estaba detenido, creamos un nuevo hilo
			if (h1.getAukera() == 3 || !t1.isAlive()) {
				h1 = new KronometroaHaria(lblKrono, lblMezua, "kronometroa");
				t1 = new Thread(h1);
				t1.setName("kronometroa");
				t1.start();
			}
			h1.setAukera(1); // iniciar
			lblMezua.setText("<<" + t1.getName() + ">> haria martxan dago.");
			btnHasi.setEnabled(false);
			btnPausatu.setEnabled(true);
			btnGelditu.setEnabled(true);
		}

		if (e.getSource() == btnPausatu) {
			if (btnPausatu.getText().equals("Pausatu")) {
				h1.setAukera(2);
				btnPausatu.setText("Jarraitu");
				lblMezua.setText("<<" + t1.getName() + ">> haria pausatuta dago.");
			} else {
				h1.setAukera(1);
				btnPausatu.setText("Pausatu");
				lblMezua.setText("<<" + t1.getName() + ">> haria berriro martxan dago.");
			}
		}

		if (e.getSource() == btnGelditu) {
			h1.setAukera(3);
			h1.reset(); // vuelve a 01:30:00
			lblMezua.setText("<<" + t1.getName() + ">> haria gelditu eta berrabiarazi da.");
			btnHasi.setEnabled(true);
			btnPausatu.setEnabled(false);
			btnGelditu.setEnabled(false);
			btnPausatu.setText("Pausatu");
		}

		if (e.getSource() == btnIrten) {
			h1.setAukera(3);
			lblMezua.setText("<<" + t1.getName() + ">> haria bukatu da (1).");
			lblMezua.setText("<<" + hariaOrdua.getName() + ">> haria bukatu da (2).");
			hariaOrdua.bukatuOrdua();
		}

	}

}