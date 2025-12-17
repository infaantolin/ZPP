package txatA;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HariaJaso extends Thread {

	JTextArea textArea = null;
	JTextField TextField = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	ArrayList<ObjectOutputStream> lista = null;

	public HariaJaso(JTextArea textArea, JTextField testua, ObjectOutputStream oos, ObjectInputStream ois,
			ArrayList<ObjectOutputStream> lista) {
		this.textArea = textArea;
		this.TextField = testua;
		this.oos = oos;
		this.ois = ois;
		this.lista = lista;
	}

	public void run() {
		String testua = "";
		while (!testua.equals("*")) {
			try {
				testua = (String) ois.readObject();
				if (!testua.equals("*")) {
					textArea.append(testua);
					for (int i = 0; i < lista.size(); i++) {
						ObjectOutputStream os = lista.get(i);
						os.writeObject(testua);
					}
				} else {
					lista.remove(oos);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		this.TextField.setText("Uneko konekzioak: " + lista.size());

		System.out.println("Jaso zerbi bukatuta.");
	}


}
