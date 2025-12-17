package txatA;

//import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class OngiEtorriLeihoa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OngiEtorriLeihoa frame = new OngiEtorriLeihoa();
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
	public OngiEtorriLeihoa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		textField = new JTextField();
		textField.setBounds(44, 47, 149, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("nickname sartu:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(44, 22, 128, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(44, 77, 165, 19);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		JButton btnNewButton = new JButton("Konektatu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_1.setVisible(false);
				if(!textField.getText().equals(""))
				{
					Socket s = null;
					BezeroLeihoa bezLeiho;
					try {
						bezLeiho = new BezeroLeihoa(s,textField.getText());
						bezLeiho.setBounds(0, 0, 540, 400);
						bezLeiho.setVisible(true);
						setVisible(false);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else
				{
					lblNewLabel_1.setVisible(true);
					lblNewLabel_1.setText("nickname bat sartu behar da");
				}
					
			}
		});
		btnNewButton.setBounds(108, 95, 85, 21);
		contentPane.add(btnNewButton);
	}

}
