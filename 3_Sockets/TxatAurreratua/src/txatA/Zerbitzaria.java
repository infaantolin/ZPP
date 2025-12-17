package txatA;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Zerbitzaria extends Thread {

	int PORTUA = 5000;
	int MAX_KONEKZIOAK = 3;
	ArrayList<ObjectOutputStream> lista;
	JTextArea textArea = null;
	JTextField testua = null;
	boolean jarraitu = true;
	ServerSocket zerbitzaria = null;

	public Zerbitzaria(JTextArea textArea, JTextField testua) {
		lista = new ArrayList<ObjectOutputStream>();
		this.testua = testua;
		this.textArea = textArea;
		testua.setText("0");
	}

	public void run() {

		try {
			zerbitzaria = new ServerSocket(PORTUA);

			System.out.println("Zerbitzaria hasieratua. Itxoiten...");
			Socket socket = new Socket();

			testua.setText("Uneko konekzioak: " + lista.size());
			while (jarraitu) {
				socket = zerbitzaria.accept();
				if (lista.size() < MAX_KONEKZIOAK) {
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); // Irteera
					lista.add(oos);
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()); // sarrera
					testua.setText("Uneko konekzioak: " + lista.size());
					HariaJaso hj = new HariaJaso(textArea, testua, oos, ois, lista);
					hj.start();
				} else
					socket.close();

			}

			socket.close();

			System.out.println("Zerbitzaria bukatuta");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Zerbitzaria itxita");
			System.exit(0);
		}
	}

	public void deskonektatu() {
		jarraitu = false;
		try {
			for (int i = 0; i < lista.size(); i++) {
				ObjectOutputStream os = lista.get(i);
				os.writeObject("*");
			}
			zerbitzaria.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
