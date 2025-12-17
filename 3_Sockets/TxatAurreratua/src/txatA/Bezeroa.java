package txatA;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Bezeroa {
	private int portua = 5000;
	private String host = "localhost";
	Socket socket = null;
	JTextArea textArea = null;
	JTextField testua = null;
	ObjectOutputStream oos = null;
	JButton bidaliBotoia = null;
	
	public Bezeroa() {
		
	}
	
	public Bezeroa(Socket socket, JTextArea textArea, JTextField testua, JButton bidaliBotoia){
		this.socket = socket;
		this.textArea = textArea;
		this.testua = testua;
		this.bidaliBotoia = bidaliBotoia;
		ObjectInputStream ois = null;

		try {
			oos = new ObjectOutputStream(socket.getOutputStream());//Irteera
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		try {
			ois = new ObjectInputStream(socket.getInputStream());//Sarrera
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		HariaJasoB hjB = new HariaJasoB(this.textArea, this.testua, ois, this.bidaliBotoia);
		hjB.start();
	}
	
	public void mezuaBidali(String mezua) {
		try {
			if (bidaliBotoia.isEnabled())
				oos.writeObject(mezua);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getPortua() {
		return portua;
	}

	public String getHost() {
		return host;
	}
}
