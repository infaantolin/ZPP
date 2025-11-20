package itzul;

import java.util.Scanner;

public class Itzultzailea {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String hitza = sc.nextLine();
		sc.close();
		switch (hitza.toLowerCase()) {
		case "kaixo":
			System.out.println("hello");
			break;
		case "agur":
			System.out.println("bye");
			break;
		case "maite":
			System.out.println("love");
			break;
		default:
			System.out.println("unknown");
		}
	}
}