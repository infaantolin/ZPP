package com.arik.zereginak.model;

public class Zeregina {

	private Integer id;
	private String izena;
	private boolean osatua;

	public Zeregina(Integer id, String izena, boolean osatua) {
		this.id = id;
		this.izena = izena;
		this.osatua = osatua;
	}

	public Integer getId() {
		return id;
	}

	public String getIzena() {
		return izena;
	}

	public boolean isOsatua() {
		return osatua;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public void setOsatua(boolean osatua) {
		this.osatua = osatua;
	}

}
