package it.polito.tdp.food.model;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import java.util.*;
import it.polito.tdp.food.db.FoodDAO;

public class Model {

	FoodDAO dao;
	Graph<String, DefaultWeightedEdge> grafo;
	List<Arco> archi;
	List<String> vertici;
	
	public Model() {
		dao = new FoodDAO();
	}
	
	public void creaGrafo(int calorie) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		vertici = dao.getVertici(calorie);
		archi = dao.getArchi(calorie);
		Graphs.addAllVertices(this.grafo, vertici);
		
		for (Arco a : archi)
			Graphs.addEdgeWithVertices(this.grafo, a.getType1(), a.getType2(), a.getPeso());
	}
	
	public List<String> getVertici(){
		return vertici;
	}
	
	public int getNumeroVertici(){
		return vertici.size();
	}
	
	public int getNumeroArchi(){
		return archi.size();
	}
	
	public List<String> getConnessi(String partenza){
		List<String> vicini = Graphs.neighborListOf(this.grafo, partenza);
		List<String> viciniVeri = new ArrayList<>(); 
		for(String s : vicini) {
			DefaultWeightedEdge e = this.grafo.getEdge(s, partenza);
			int peso = (int) this.grafo.getEdgeWeight(e);
			viciniVeri.add(s+" peso: "+peso);
		}
		return viciniVeri;
	}
	//cammino semplice di PESO MASSIMO
	//lung == n 
	// vertice selezionato
//no cicli 
	
	//stampa cammino e peso
	
	List<String> parziale;
	List<String> soluzione;
	int pesoSoluzione;
	
	public List<String> getCammino(int passi, String inizio){
		
		parziale = new ArrayList<String>();
		soluzione = new ArrayList<String>();
		pesoSoluzione = 0;
		
		parziale.add(inizio);
		cerca(parziale, passi);
		return soluzione;
	}

	private void cerca(List<String> parziale, int passi) {
		if(parziale.size()==passi) {
			int pesoParziale = calcolaPeso(parziale);
			if(pesoParziale > pesoSoluzione) {
				pesoSoluzione = pesoParziale;
				soluzione = new ArrayList<>(parziale);
			}
		}
		
		for (String s : Graphs.neighborListOf(this.grafo, parziale.get(parziale.size()-1))) {
			if(!parziale.contains(s)) {
				parziale.add(s);
				cerca(parziale, passi);
				parziale.remove(s);
			}
		}
		
	}

	private int calcolaPeso(List<String> parziale) {
		int peso = 0;
		for (int i=1; i<parziale.size(); i++) {
			DefaultWeightedEdge e = this.grafo.getEdge(parziale.get(i-1), parziale.get(i));
			peso += (int) this.grafo.getEdgeWeight(e);
		}
		return peso;
	}
	
}
