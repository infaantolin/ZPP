package ariketa1;

import java.util.Scanner;

public class EjemploLectura {

	public static void main(String[] args) {
		
		// Usamos Scanner para leer texto de la entrada estándar
        Scanner sc = new Scanner(System.in);

        // Pedimos una cadena (cuando lo ejecutemos en el CMD, podemos escribir algo)
        String texto = sc.nextLine();

        // Mostramos el texto que nos ha llegado
        System.out.println("El texto recibido es: " + texto);

        sc.close();
    }
}