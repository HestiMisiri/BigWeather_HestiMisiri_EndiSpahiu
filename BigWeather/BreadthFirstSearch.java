import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Collection;

/*
* A modified BFS algorithm which is used to create a List of Strings
* which contain the Edge connections to form a Tree for each Connected
* Component.
*/

public class BreadthFirstSearch {
	
	public static List<String> modAlgo( Graph graph, 
									    Collection<LinkedList<Integer>> CC ) {
		
		int[] prev = new int[graph.getVertices()];
		List<Integer> buckets = new LinkedList<Integer>();
		for (int i = 0; i < prev.length; i++)
			prev[i] = -1;
		
		
		for ( LinkedList<Integer> l : CC ) {
			
			if ( l.size() == 1 )
				continue;
			
			Queue<Integer> q = new LinkedList<Integer>();
			buckets.add(l.peek());
			
			q.offer(l.peek());
			while ( !q.isEmpty() ) {
				
				int u = q.poll();
				
				for ( int v : graph.getEdges(u) ) {
					
					if ( prev[v] < 0 ) {
						
						prev[v] = u;
						
					}
					
				}
				
			}
			
		}
		
		List<String> edges = new LinkedList<String>();
		
		// For-loop for designating buckets in the Grid.
		StringBuilder b = new StringBuilder();
		for ( int i : buckets ) {
			
			b.append(String.format("%d ", i + 1));
			
		}
		edges.add(b.toString());
		
		// For-loop to add Edge connections.
		for (int i = 0; i < prev.length; i++) {
			
			if ( prev[i] < 0 )
				continue;
			
			edges.add(String.format("%d %d", prev[i] + 1, i + 1));
			
		}
		
		return edges;
		
	}
	
}