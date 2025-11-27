package arik2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Zerbitzari {

	public static void main(String[] args) {
		final int portua = 5002;

		try {
			ServerSocket zerbitzari = new ServerSocket(portua);
			System.out.println("Zerbitzaria hasieratuta");
			Socket bezero = zerbitzari.accept();

			Pertsona p = new Pertsona();
			p.setNif("16078123X");
			p.setIzena("Pepito");
			p.setAbizena("Grillo");
			p.setJaiotzeData(1990, 12, 12);
			p.setGeneroa('M');

			ObjectOutputStream oos = new ObjectOutputStream(bezero.getOutputStream());
			oos.writeObject(p);

			ObjectInputStream ois = new ObjectInputStream(bezero.getInputStream());
			Pertsona p2 = (Pertsona) ois.readObject();

			System.out.println(p2.toString());

			oos.close();
			ois.close();
			bezero.close();
			zerbitzari.close();

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
