package undirected;

public class TestGraph {

	
	public static void main(String[] args) {
		
		Graph G = new Graph();
		
		Vertex lahr = new Vertex("Lahr");
		Vertex offenburg = new Vertex("Offenburg");
		Vertex freiburg = new Vertex("Freiburg");
		Vertex basel = new Vertex("Basel");
		Vertex karlsruhe = new Vertex("Karlsruhe");
		Vertex stuttgart = new Vertex("Stuttgart");
		Vertex mannheim = new Vertex("Mannheim");

		G.addVertex(lahr);
		G.addVertex(offenburg);
		G.addVertex(freiburg);
		G.addVertex(basel);
		G.addVertex(karlsruhe);
		G.addVertex(stuttgart);
		G.addVertex(mannheim);
		
		G.addEdge(new Edge("lo", new Object[] {lahr, offenburg} ));
		G.addEdge(new Edge("of", new Object[] {freiburg, offenburg} ));
		G.addEdge(new Edge("bf", new Object[] {basel, freiburg} ));
		G.addEdge(new Edge("ks", new Object[] {karlsruhe, offenburg} ));
		G.addEdge(new Edge("ks", new Object[] {karlsruhe, stuttgart} ));
		G.addEdge(new Edge("sm", new Object[] {stuttgart,mannheim} ));
		G.addEdge(new Edge("mk", new Object[] {mannheim,karlsruhe} ));
		

		System.out.println(G.deleteLonelyVertices().toString());
		System.out.println(G.getAdjacentVerticesOf(freiburg));
		System.out.println(G.getAdjacentVerticesOf(karlsruhe));
		
		Graph H = new Graph();
		Vertex v = new Vertex("v");
		Vertex w = new Vertex("w");
		H.addVertex(v);
		H.addVertex(w);
		H.addEdge(new Edge("vw",new Object[] {v,w}));
		System.out.println(H.getAdjacentVerticesOf(v));
	}
	
	

}
