package txatA;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HariaJasoB extends Thread {

	JTextArea textArea = null;
	JTextField testua = null;
	ObjectInputStream ois = null;
	JButton bidaliBotoia = null;

	public HariaJasoB(JTextArea textArea, JTextField testua, ObjectInputStream ois, JButton bidaliBotoia) {
		this.textArea = textArea;
		this.testua = testua;
		this.ois = ois;
		this.bidaliBotoia = bidaliBotoia;
	}

	public void run() {
		String testua = "";

		while (!testua.equals("*")) {
			try {
				testua = (String) ois.readObject();
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
