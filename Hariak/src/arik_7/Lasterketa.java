package arik_7;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JProgressBar;
import javax.swing.JButton;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

public class Lasterketa extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	ZaldiHaria h1;
	ZaldiHaria h2;
	ZaldiHaria h3;
	ZaldiHaria h4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Lasterketa leihoa = new Lasterketa();
				leihoa.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Lasterketa() {
		setTitle("Zaldi lasterketa 🐎");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

//		JProgressBar progressBar = new JProgressBar();
//		contentPane.add(progressBar, BorderLayout.CENTER);
//		progressBar.setStringPainted(true);
//		progressBar.setValue(60);

		// --- Etiketa nagusia: irabazlea ---
		JLabel irabazlea = new JLabel("Irabazlea: -");
		irabazlea.setBounds(150, 10, 200, 20);
		contentPane.add(irabazlea);

		JProgressBar b1 = new JProgressBar();
		b1.setForeground(Color.PINK);
		b1.setStringPainted(true);
		b1.setBounds(90, 40, 300, 30);
		contentPane.add(b1);

		JProgressBar b2 = new JProgressBar();
		b1.setForeground(Color.BLUE);
		b2.setStringPainted(true);
		b2.setBounds(90, 85, 300, 30);
		contentPane.add(b2);

		JProgressBar b3 = new JProgressBar();
		b3.setForeground(Color.MAGENTA);
		b3.setStringPainted(true);
		b3.setBounds(90, 130, 300, 30);
		contentPane.add(b3);

		JProgressBar b4 = new JProgressBar();
		b4.setForeground(Color.GREEN);
		b4.setStringPainted(true);
		b4.setBounds(90, 175, 300, 30);
		contentPane.add(b4);



		JLabel l1 = new JLabel("Zaldia 1");
		l1.setForeground(Color.PINK);
		l1.setBounds(20, 45, 70, 20);
		contentPane.add(l1);

		JLabel l2 = new JLabel("Zaldia 2");
		l2.setForeground(Color.BLUE);
		l2.setBounds(20, 90, 70, 20);
		contentPane.add(l2);

		JLabel l3 = new JLabel("Zaldia 3");
		l3.setForeground(Color.MAGENTA);
		l3.setBounds(20, 135, 70, 20);
		contentPane.add(l3);

		JLabel l4 = new JLabel("Zaldia 4");
		l4.setForeground(Color.GREEN);
		l4.setBounds(20, 180, 70, 20);
		contentPane.add(l4);


		h1 = new ZaldiHaria(b1, "Zaldia 1", irabazlea);
		h2 = new ZaldiHaria(b2, "Zaldia 2", irabazlea);
		h3 = new ZaldiHaria(b3, "Zaldia 3", irabazlea);
		h4 = new ZaldiHaria(b4, "Zaldia 4", irabazlea);

		JButton hasiBotoia = new JButton("Hasi lasterketa");
		hasiBotoia.addActionListener(e -> {
			h1.start();
			h2.start();
			h3.start();
			h4.start();

			HariaDetektagailua detekt = new HariaDetektagailua(h1, h2, h3, h4);
			detekt.start();
		});

		hasiBotoia.setBounds(130, 250, 180, 25);
		contentPane.add(hasiBotoia);

		JButton trampakBotoia = new JButton("⚙️ Trampak");
		trampakBotoia.addActionListener(e -> {
			Trampak tr = new Trampak(h1, h2, h3, h4);
			tr.setVisible(true);
		});

		trampakBotoia.setBounds(130, 300, 180, 25);
		contentPane.add(trampakBotoia);
	}
}