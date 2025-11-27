package arik2;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

public class Pertsona implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nif;
	private String izena;
	private String abizena;
	private Date jaiotzeData;
	private char generoa;

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getAbizena() {
		return abizena;
	}

	public void setAbizena(String abizena) {
		this.abizena = abizena;
	}

	public Date getJaiotzeData() {
		return jaiotzeData;
	}

	public void setJaiotzeData(int urte, int hila, int eguna) {

		GregorianCalendar cal = new GregorianCalendar(urte, hila - 1, eguna);

		this.jaiotzeData = cal.getTime();
	}

	public char getGeneroa() {
		return generoa;
	}

	public void setGeneroa(char generoa) {
		this.generoa = generoa;
	}

	@Override
	public String toString() {
		return "Pertsona [nif=" + nif + ", izena=" + izena + ", abizena=" + abizena + ", jaiotzeData=" + jaiotzeData
				+ ", generoa=" + generoa + "]";
	}

}
