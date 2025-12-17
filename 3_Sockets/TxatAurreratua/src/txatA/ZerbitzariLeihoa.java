package txatA;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ZerbitzariLeihoa extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollpane1;

	static JTextField mezua = new JTextField();
	static JTextArea textarea1;
	JButton bidaliBotoia = new JButton("Bidali");
	JButton irtenBotoia = new JButton("Irten");
	Zerbitzaria z = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZerbitzariLeihoa frame = new ZerbitzariLeihoa();
					frame.setBounds(0, 0, 500, 450);
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
	public ZerbitzariLeihoa() {
		super(" ZERBITZARI LEIHOA ");
		getContentPane().setLayout(null);

		mezua.setBounds(10, 10, 400, 30);
		getContentPane().add(mezua);
		mezua.setEditable(false);
		textarea1 = new JTextArea();

		scrollpane1 = new JScrollPane(textarea1);
		scrollpane1.setBounds(10, 50, 400, 300);
		getContentPane().add(scrollpane1);
		textarea1.setText("Konekzioak itxoiten... \n");
		irtenBotoia.setBounds(206, 370, 100, 30);
		getContentPane().add(irtenBotoia);

		textarea1.setEditable(false);
		bidaliBotoia.addActionListener(this);
		irtenBotoia.addActionListener(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		z = new Zerbitzaria(textarea1, mezua);
		z.start();

//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == irtenBotoia) { // IRTEN botoia sakatzen da
			z.deskonektatu();
		}
	}

}
