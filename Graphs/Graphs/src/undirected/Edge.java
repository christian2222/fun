package undirected;

public class Edge {

	Object o;
	Object[] refVertices = new Object[2];

	
	public Edge(Object o, Object[] refVertices) {
		super();
		this.o = o;
		this.refVertices = refVertices;
	}
	
	public boolean isIncident(Vertex v) {
		if(this.refVertices[0].equals(v)) return true;
		if(this.refVertices[1].equals(v)) return true;
		return false;
	}
	
	
}
