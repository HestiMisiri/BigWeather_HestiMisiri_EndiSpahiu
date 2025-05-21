import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

/*
* The file contains a basic DFS algorithm which is used to find
* all Connected Components of a Graph. It returns a Map object
* with a Entry Pair of (ccNum, CC[]), which we use for all our
* other Algorithms. 
*/

public class DepthFirstSearch {
	
	private static Map<Integer, LinkedList<Integer>> CC;
	private static int ccCount = 1;
	private static boolean[] visited;
	
	public static Map<Integer, LinkedList<Integer>> DFS(Graph G) {
		
		visited = new boolean[G.getVertices()];
		CC = new HashMap<>();
		
		for (int i = 0; i < visited.length; i++) {
			
			if ( !visited[i] ) {
				
				CC.put(ccCount, new LinkedList<>());
				explore(G, i);
				ccCount++;
				
			}
			
		}
		
		return CC;
		
	}
	
	private static void explore(Graph G, int v) {
		
		visited[v] = true;
		CC.get(ccCount).add(v);
		
		for (int u : G.getEdges(v)) {
			
			if ( !visited[u] ) {
				
				explore(G, u);
				
			}
			
		}
		
	}
	
}