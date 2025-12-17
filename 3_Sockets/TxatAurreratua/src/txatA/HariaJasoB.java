package txatA;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HariaJasoB extends Thread {

	JTextArea textArea = null;
	JTextField testua = null;
	ObjectInputStream oos = null;
	JButton bidaliBotoia = null;

	public HariaJasoB(JTextArea textArea, JTextField testua, ObjectInputStream oos, JButton bidaliBotoia) {
		this.textArea = textArea;
		this.testua = testua;
		this.oos = oos;
		this.bidaliBotoia = bidaliBotoia;
	}

	public void run() {
		String testua = "";

		while (!testua.equals("*")) {
			try {
				testua = (String) oos.readObject();
				if(!testua.equals("*"))
					textArea.append(testua);
				else
					textArea.append("Zerbitzaria deskonektatuta");

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Bezeroa jaso bukatuta");
		bidaliBotoia.setEnabled(false);
	}
}
