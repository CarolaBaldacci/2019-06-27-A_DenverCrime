package it.polito.tdp.crimes.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {

	private EventsDao dao;
	private Graph<String , DefaultWeightedEdge> grafo;
	private List<Adiacenza> adiacenzeList;
	
	public Model() {
		dao= new EventsDao ();
		adiacenzeList=new ArrayList<>();
	}
	
	public List<String> getCategorie() {
		return dao.getAllCategory();
	}
	
	public String creaGrafo(String c, int a) {
		this.grafo= new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		//creoVertici
		Graphs.addAllVertices(this.grafo, dao.getAllType(c,a));
		//creo archi
		for(Adiacenza ai:this.dao.getArchi(c,a)) {
			if(!grafo.containsEdge(ai.getT2(), ai.getT1())) {
				Graphs.addEdgeWithVertices(this.grafo, ai.getT1(), ai.getT2(), ai.getPeso());
				adiacenzeList.add(ai);
			}
		}
		return "Grafo creato #Vertici: "+this.grafo.vertexSet().size()+"#Archi: "+this.grafo.edgeSet().size();
	}
	
}
