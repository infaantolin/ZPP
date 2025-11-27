package arik2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Bezero {

	public static void main(String[] args) {
		try {
			Socket bezero = new Socket("localhost",5002);
			System.out.println("Bezeroa konektatuta");
			
			ObjectInputStream ois = new ObjectInputStream(bezero.getInputStream());
			Pertsona p2 = (Pertsona) ois.readObject();
			
			p2.setIzena("Ane");
			p2.setJaiotzeData(1983, 1, 1);
			
			ObjectOutputStream oos = new ObjectOutputStream( bezero.getOutputStream());
			oos.writeObject(p2);
			System.out.println("Pertsona bidalia");
			
			oos.close();
			ois.close();
			
			bezero.close();
			
			
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
