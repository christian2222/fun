package undirected;

import java.util.*;

public class Graph {

	ArrayList<Vertex> vList = new ArrayList<Vertex>();
	ArrayList<Edge> eList = new ArrayList<Edge>();
	
	public void addVertex(Vertex v) {
		this.vList.add(v);
	}
	
	public void addEdge(Edge e) {
		this.eList.add(e);
	}
	
	public void removeVertex(Vertex v) {
		// remove all edges of Vertex v
		this.removeAllEdgesOf(v);
		// remove Vertex v itself
		this.vList.remove(v);
	}
	
	
	public boolean areAdjacent(Vertex v, Vertex w) {
		boolean areAdjacent = false;
		for(Edge e: this.eList) {
			if(e.isIncident(v) && e.isIncident(w)) areAdjacent = true;
		}
		return areAdjacent;
	}
	
	public ArrayList<Vertex> getAdjacentVerticesOf(Vertex v) {
		ArrayList<Vertex> adjacencyList = new ArrayList<Vertex>();
		for(Vertex w : this.vList) {
			if(this.areAdjacent(v, w) && !adjacencyList.contains(w)  && !v.equals(w)) adjacencyList.add(w);
		}
		return adjacencyList;
	}
	
	
	public boolean hasLonelyVertices() {
		for(Vertex v: this.vList) {
			if(isLonely(v)) return true;
		}
		return false;
	}
	
	public ArrayList<Vertex> deleteLonelyVertices() {
		ArrayList<Vertex> lonelyVertices = new ArrayList<Vertex>();
		for(Vertex v : this.vList) {
			if(isLonely(v)) {
				this.vList.remove(v);
				lonelyVertices.add(v);
			}
		}
		return lonelyVertices;
	}
	
	private boolean isLonely(Vertex v) {
		boolean lonely = true;
		for(Edge e: eList) {
			if(e.isIncident(v)) lonely = false;
		}
		return lonely;
	}
	
	public void removeEdge(Edge e) {
		this.eList.remove(e);
	}
	
	public void removeAllEdgesOf(Vertex v) {
			eList.forEach(e -> this.checkRemoveEdgeOfVertex(v, e));
	}
	
	private void checkRemoveEdgeOfVertex(Vertex v, Edge e) {
		if(e.isIncident(v)) this.eList.remove(e);
	}
	
	public int size() {
		return this.vList.size();
	}
	
	public int numVertices() {
		return this.vList.size();
	}
	
	public int numEdges() {
		return this.eList.size();
	}
	
	public boolean isEmpty() {
		return this.vList.isEmpty();
	}
	
	public Vertex aVertex() {
		if(this.vList.isEmpty()) return null;
		return this.vList.get(0);
	}
}
