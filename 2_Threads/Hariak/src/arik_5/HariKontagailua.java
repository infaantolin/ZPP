package arik_5;

import javax.swing.JLabel;
/**
 * {@code HariKontagailua} klaseak hari bat (Thread) exekutatzen du eta haren jarduera 
 * pantailan bistaratzen du Swing-eko {@link JLabel} etiketen bidez.
 * <p>
 * Hari bakoitzak bere izena eta lehentasuna dauka. Haria martxan dagoen bitartean
 * kontagailu bat etengabe handitzen du eta emaitza interfazean erakusten du.
 * Hariaren lehentasuna dinamikan alda daiteke eta, nahi izanez gero, haren 
 * exekuzioa amaitu.
 * </p>
 * 
 * <p><b>Oharra:</b> Klase hau GUI aplikazioetan erabiltzeko pentsatuta dago 
 * (Swing), kontagailuaren balioak eta lehentasuna etiketetan erakutsiz.</p>
 * 
 * @author  
 * @version 1.0
 */
public class HariKontagailua implements Runnable {

	/** Zenbaketa bistaratzen duen etiketa (Swing JLabel). */
    private JLabel labelC;

    /** Lehentasuna bistaratzen duen etiketa (Swing JLabel). */
    private JLabel prioridad;

    /** Hariaren exekuzioa kontrolatzeko aldagai sinplea (0 = jarraitu, 1 = amaitu). */
    private int seguir = 0;

    /** Zenbaketaren balioa. */
    private int cont = 0;

    /** Hariaren objektua. */
    Thread t1 = null;

    /**
     * HariKontagailua objektu berri bat sortzen du.
     * <p>
     * Eraikitzaileak hari berria hasieratzen du eta automatikoki abiarazten du.
     * </p>
     * 
     * @param labelC     kontagailuaren balioa erakutsiko duen etiketa
     * @param prioridad  hariaren lehentasuna erakutsiko duen etiketa
     * @param nombre     hariaren izena
     */
	public HariKontagailua(JLabel labelC, JLabel prioridad, String nombre) {
		this.labelC = labelC;
		this.prioridad = prioridad;
		t1 = new Thread(this);
		t1.setPriority(5);
		t1.setName(nombre);
		t1.start();
	}
	
	/**
     * Hariaren exekuzio nagusia.
     * <p>
     * {@code seguir} aldagaiak 0 balio du bitartean, kontagailua handitzen eta 
     * etiketan bistaratzen jarraituko du. 
     * </p>
     */
	@Override
	public void run() {
		while (seguir == 0) {
			incremetar();
			mostrar();
		}
		System.out.println("El " + t1.getName() + " ha terminado");
	}

	/**
     * Etiketetan informazioa erakusten du: 
     * hariaren izena, kontagailuaren balioa eta lehentasuna.
     */
	private void mostrar() {
		this.labelC.setText(t1.getName() + ": " + String.valueOf(cont));
		this.prioridad.setText("Pri: " + t1.getPriority());
		;
	}

	/**
     * Kontagailuaren balioa handitzen du 1 unitatez.
     */
	private void incremetar() {
		cont++;
	}

	/**
     * Hariaren exekuzioa gelditzen du.
     * <p>
     * Metodo honek {@code seguir} aldagaia 1era ezartzen du,
     * eta horrela hariaren bucle nagusia amaitzen da.
     * </p>
     */
	public void terminar() {
		seguir = 1;
	}

	/**
     * Hariaren lehentasuna 1 puntuz handitzen du (gehienez 10 arte).
     */
	public void incrementar() {
		if (t1.getPriority() < 10)
			t1.setPriority(t1.getPriority() + 1);
	}

	/**
     * Hariaren lehentasuna 1 puntuz jaisten du (gutxienez 0 arte).
     */
	public void decrecentar() {
		if (t1.getPriority() > 0)
			t1.setPriority(t1.getPriority() - 1);
	}

}
