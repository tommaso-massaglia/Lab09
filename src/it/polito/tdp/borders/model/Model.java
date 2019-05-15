package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.event.ConnectedComponentTraversalEvent;
import org.jgrapht.event.EdgeTraversalEvent;
import org.jgrapht.event.TraversalListener;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	/*private class EdgeTraversedGraphListener implements TraversalListener<Country, DefaultEdge> {


		Graph<Country, DefaultEdge> grafo;

		public EdgeTraversedGraphListener(Graph<Country, DefaultEdge> grafo) {
			this.grafo = grafo;
		}

		@Override
		public void connectedComponentFinished(ConnectedComponentTraversalEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void connectedComponentStarted(ConnectedComponentTraversalEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void edgeTraversed(EdgeTraversalEvent<DefaultEdge> arg0) {
			Country sourceVertex = grafo.getEdgeSource(arg0.getEdge());
			Country targetVertex = grafo.getEdgeTarget(arg0.getEdge());

			if (!backVisit.containsKey(targetVertex) && backVisit.containsKey(sourceVertex)) {
				backVisit.put(targetVertex, sourceVertex);
			} else if (!backVisit.containsKey(sourceVertex) && backVisit.containsKey(targetVertex)) {
				backVisit.put(sourceVertex, targetVertex);
			}

		}

		@Override
		public void vertexFinished(VertexTraversalEvent<Country> arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void vertexTraversed(VertexTraversalEvent<Country> arg0) {
			// TODO Auto-generated method stub

		}

	}*/

	private Map<Integer, Country> countries = new HashMap<Integer, Country>();
	private Map<Country, Country> backVisit;
	private Graph<Country, DefaultEdge> grafo;

	public Model() {
		this.setCountries();
	}

	private void setCountries() {
		BordersDAO dao = new BordersDAO();
		for (Country c : dao.loadAllCountries()) {
			countries.put(c.getStatecode(), c);
		}
	}

	private void creaGrafo(int anno) {

		BordersDAO dao = new BordersDAO();

		grafo = new SimpleGraph<>(DefaultEdge.class);
		Graphs.addAllVertices(this.grafo, this.getCountries().values());

		for (Country c : this.getCountries().values()) {

			List<Border> borders = new ArrayList<Border>(dao.getCountryPairs(anno, c.getStatecode()));

			for (Border b : borders) {
				this.grafo.addEdge(c, this.getCountries().get(b.getSecondcountry()));
			}

		}

	}

	/**
	 * Restituisce una stringa contenente il numero di confini di ogni stato
	 * nell'anno specificato;
	 */
	
	public String getnumconfini(int anno) {
		String result = new String();

		this.creaGrafo(anno);

		result += "Elenco dei confini via terra degli stati nell'anno " + anno + ": \n";

		for (Country c : this.countries.values()) {
			result += "Stato: " + c.getStatename() + ", Num Confini: " + grafo.degreeOf(c) + "\n";
		}

		return result;

	}

	public int getConnessioni(int anno) {
		
		this.creaGrafo(anno);
		int contatore = 0;
		List<String> visitati = new ArrayList<String>();
		
		for (Country c1 : this.countries.values()) {
			for (Country c2 : this.countries.values()) {
				if (!c1.equals(c2)) {
					if (grafo.containsEdge(c1, c2)) {
						if (!visitati.contains(c1.getStateabb()+c2.getStateabb())&&!visitati.contains(c2.getStateabb()+c1.getStateabb())) {
							contatore++;
							visitati.add(c1.getStateabb()+c2.getStateabb());
						}
					}
				}
			}
		}
		
		return contatore;
	}

	public Map<Integer, Country> getCountries() {
		return countries;
	}

}
