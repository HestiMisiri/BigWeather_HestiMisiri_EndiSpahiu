import java.util.LinkedList;

public class Graph {
	
	private final int vertices;
	private final int edgeNum;
	// Adjacency List implemented via an array of List objects.
	private LinkedList<Integer>[] edges;
	
	public Graph(int vertices, int edgeNum) {
		
		this.vertices = vertices;
		this.edgeNum = edgeNum;
		this.edges = new LinkedList[vertices];
		
		// When initialising an array of reference objects, the default value
		// for said objects is null. Therefore, we need to instatiate them for
		// each element of the array.
		for (int i = 0; i < edges.length; i++) {
			
			edges[i] = new LinkedList<>();
			
		}
		
	}
	
	public void addEdge(int v, int u) {
		
		this.edges[v].add(u);
		
	}
	
	public int getVertices() {
		
		return this.vertices;
		
	}
	
	public int getEdgeNum() {
		
		return this.edgeNum;
		
	}
	
	public LinkedList<Integer> getEdges(int v) {
		
		return this.edges[v];
		
	}
	
}