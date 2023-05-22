package it.polito.tdp.itunes.model;

import java.util.LinkedList;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		model.CreaGrafo(5);
		
		LinkedList<Album> a= new LinkedList<>(model.getGrafo().vertexSet());
		
		model.setX(3);
		
		LinkedList<Vertice> aa = new LinkedList<>(model.getAdiacenze(a.get(7)));
		
		model.getPath(a.get(7), aa.get(7).getAlbum());
		
	}

}
