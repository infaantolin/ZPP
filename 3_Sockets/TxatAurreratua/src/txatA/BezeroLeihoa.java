package txatA;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//import javax.swing.border.EmptyBorder;

public class BezeroLeihoa extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	// private JPanel contentPane;

	Socket socket = null;

	String nick;
	static JTextField mezua = new JTextField();

	private JScrollPane scrollpane1;
	static JTextArea textarea1;
	JButton bidaliBotoia = new JButton("Bidali");
	JButton irtenBotoia = new JButton("Irten");
	Bezeroa bezeroa = null;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					BezeroLeihoa frame = new BezeroLeihoa();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Eraikitzailea
	 */
	public BezeroLeihoa(Socket s, String izena) throws IOException {

		super(" BEZEROAREN KONEXIOA TXAT: " + izena);
		getContentPane().setLayout(null);

		mezua.setBounds(10, 10, 400, 30);
		getContentPane().add(mezua);

		textarea1 = new JTextArea();
		scrollpane1 = new JScrollPane(textarea1);
		scrollpane1.setBounds(10, 50, 400, 300);
		getContentPane().add(scrollpane1);

		bidaliBotoia.setBounds(420, 10, 100, 30);
		getContentPane().add(bidaliBotoia);
		irtenBotoia.setBounds(420, 50, 100, 30);
		getContentPane().add(irtenBotoia);

		textarea1.setEditable(false);
		bidaliBotoia.addActionListener(this);
		irtenBotoia.addActionListener(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		socket = s;
		this.nick = izena;

		bezeroa = new Bezeroa();

		socket = new Socket(bezeroa.getHost(), bezeroa.getPortua());

		bezeroa = new Bezeroa(socket, textarea1, mezua, bidaliBotoia);
		bezeroa.mezuaBidali("> " + nick + " konektatu da\n");

	} // Eraikitzailearen bukaera

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bidaliBotoia) { // Bidali botoia sakatzen da
			if (mezua.getText().trim().length() == 0)
				return;
			String testua = nick + "> " + mezua.getText();

			bezeroa.mezuaBidali(testua + "\n");
			mezua.setText("");
		}

		if (e.getSource() == irtenBotoia) { // Irten botoia sakatzen da
			String testua = nick + " > txata utzi du ... \n";
			bezeroa.mezuaBidali(testua);
			bezeroa.mezuaBidali("*");
			System.exit(0);
		}

	}

}
