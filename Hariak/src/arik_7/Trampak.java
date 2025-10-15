package arik_7;
//----- Trampak leihoa (lehentasunak ezartzeko) -----

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Trampak extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel edukia;
	private JTextField t1, t2, t3, t4;
	private JButton gordeBotoia;

	ZaldiHaria h1;
	ZaldiHaria h2;
	ZaldiHaria h3;
	ZaldiHaria h4;

	public Trampak(ZaldiHaria h1, ZaldiHaria h2, ZaldiHaria h3, ZaldiHaria h4) {

		this.h1 = h1;
		this.h2 = h2;
		this.h3 = h3;
		this.h4 = h4;

		setTitle("Trampak - Lehentasunak ezarri");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 250);

		edukia = new JPanel();
		edukia.setBorder(new EmptyBorder(5, 5, 5, 5));
		edukia.setLayout(null);
		setContentPane(edukia);

		JLabel l1 = new JLabel("Zaldia 1");
		l1.setBounds(30, 25, 80, 14);
		JLabel l2 = new JLabel("Zaldia 2");
		l2.setBounds(30, 50, 80, 14);
		JLabel l3 = new JLabel("Zaldia 3");
		l3.setBounds(30, 75, 80, 14);
		JLabel l4 = new JLabel("Zaldia 4");
		l4.setBounds(30, 100, 80, 14);
		edukia.add(l1);
		edukia.add(l2);
		edukia.add(l3);
		edukia.add(l4);

		t1 = new JTextField("6");
		t1.setBounds(120, 22, 50, 20);
		t2 = new JTextField("6");
		t2.setBounds(120, 47, 50, 20);
		t3 = new JTextField("6");
		t3.setBounds(120, 72, 50, 20);
		t4 = new JTextField("6");
		t4.setBounds(120, 97, 50, 20);

		edukia.add(t1);
		edukia.add(t2);
		edukia.add(t3);
		edukia.add(t4);

		gordeBotoia = new JButton("Gorde");
		gordeBotoia.addActionListener(this);
		gordeBotoia.setBounds(120, 140, 90, 25);
		edukia.add(gordeBotoia);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(gordeBotoia)) {
			h1.setPriority(Integer.parseInt(t1.getText()));
			h2.setPriority(Integer.parseInt(t2.getText()));
			h3.setPriority(Integer.parseInt(t3.getText()));
			h4.setPriority(Integer.parseInt(t4.getText()));

			dispose(); // leihoa itxi
		}
	}
}