import java.util.Collection;

/*
* This is a class which houses factory methods to instatiate new Graph
* objects. I prefer this method as it allows me to keep only a single
* Graph class which can be instatiated as a Directed/Undirected Graph.
*/

public class Graphs {
	
	// Two different methods for each Graph type.
	public static Graph makeGraph(int vertices, 
								  int edgeNum, 
								  Collection<String> edges) {
		
		Graph graph = new Graph(vertices, edgeNum);
		graph = addEdges(graph, edges);
		
		return graph;
		
	}
	
	public static Graph makeDirectedGraph(int vertices, 
										  int edgeNum, 
										  Collection<String> edges) {
		
		Graph graph = new Graph(vertices, edgeNum);
		graph = addDirectedEdges(graph, edges);
		
		return graph;
		
	}
	
	// Different implementation for adding edges to a Graph based on its type.
	public static Graph addEdges(Graph graph, Collection<String> edges) {
		
		for (String e : edges) {
			
			String[] t = e.split("\\s");
			graph.addEdge( Integer.parseInt(t[0]) - 1,
						   Integer.parseInt(t[1]) - 1 );
			graph.addEdge( Integer.parseInt(t[1]) - 1,
						   Integer.parseInt(t[0]) - 1 );
			
		}
		
		return graph;
		
	}
	
	public static Graph addDirectedEdges(Graph graph, Collection<String> edges) {
		
		for (String e : edges) {
			
			String[] t = e.split("\\s");
			graph.addEdge( Integer.parseInt(t[0]) - 1,
						   Integer.parseInt(t[1]) - 1 );
			
		}
		
		return graph;
		
	}
	
}