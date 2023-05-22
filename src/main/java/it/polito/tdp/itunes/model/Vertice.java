package it.polito.tdp.itunes.model;

public class Vertice implements Comparable<Vertice>{
	
	private Album album;
	private int bilancio;
	
	public Vertice(Album album, int bilancio) {
		super();
		this.album = album;
		this.bilancio = bilancio;
	}
	
	public Album getAlbum() {
		return this.album;
	}

	@Override
	public String toString() {
		return "Vertice [album=" + album + ", bilancio=" + bilancio + "]";
	}

	@Override
	public int compareTo(Vertice o) {
		return this.bilancio-o.bilancio;
	}
	
	

}
