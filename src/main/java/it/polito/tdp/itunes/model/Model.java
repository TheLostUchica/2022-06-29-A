package it.polito.tdp.itunes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import org.jgrapht.*;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.itunes.db.ItunesDAO;

public class Model {
	
	ItunesDAO dao;
	Graph<Album, DefaultWeightedEdge> graph;
	TreeMap<Integer, Album> AlbumId;
	
	public Model() {
		dao = new ItunesDAO();
		AlbumId = dao.getAllAlbums();
	}
	
	public void CreaGrafo(int x) {
		graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		for (Album a : AlbumId.values()) {
			if(a.getCount()>x) {
				graph.addVertex(a);
			}
		}
		
		for (Album v1 : graph.vertexSet()) {
			for (Album v2 : graph.vertexSet()) {
				if(v1.getCount()>v2.getCount()) {
					Graphs.addEdgeWithVertices(this.graph,v1, v2, v1.getCount()-v2.getCount());
				}
			}
		}
	}
	
	public Graph<Album, DefaultWeightedEdge> getGrafo(){
		return this.graph;
	}
	
	public List<Vertice> getAdiacenze(Album al){
		List<Vertice> V = new LinkedList<>();
		for (Album a : Graphs.successorListOf(this.graph, al)) {
			/*int bilancio = 0;
			for (DefaultWeightedEdge e : graph.outgoingEdgesOf(a)) {
				bilancio -= graph.getEdgeWeight(e);
			}
			for (DefaultWeightedEdge e : graph.incomingEdgesOf(a)) {
				bilancio += graph.getEdgeWeight(e);
			}
			V.add(new Vertice(a,bilancio));*/
			V.add(new Vertice(a, this.getBilancio(a)));
		}
		Collections.sort(V);
		return V;
	}
	
	private int getBilancio(Album a) {
		int bilancio = 0;
		List<DefaultWeightedEdge> edgesIN = new ArrayList<>(this.graph.incomingEdgesOf(a));
		List<DefaultWeightedEdge> edgesOUT = new ArrayList<>(this.graph.outgoingEdgesOf(a));
		
		for (DefaultWeightedEdge edge : edgesIN)
			bilancio += this.graph.getEdgeWeight(edge);
		
		for (DefaultWeightedEdge edge : edgesOUT)
			bilancio -= this.graph.getEdgeWeight(edge);
		
		return bilancio;		
	}
	
}
