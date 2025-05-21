import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;

/*
* This file contains static methods that read and instatiate Graph
* object from read files. It contains factory methods for both types, as
* well as a special method for our Grid class.
*/

public class GraphReader {
	
	public static Graph readGraph(String fileName) {
		
		Graph graph = null;
		
		try ( BufferedReader reader = new BufferedReader(new FileReader(fileName)) ) {
			
			String[] t = reader.readLine().split("\\s");
			
			graph = Graphs.makeGraph( Integer.parseInt(t[0]),
									  Integer.parseInt(t[1]),
									  readEdges(reader) );
			
		} catch (IOException e) {
			
			e.printStackTrace();
			System.exit(1);
			
		}
		
		// Have to put this in here, otherwise java will not compile
		// this file due to the possibility of returning a null.
		if ( graph == null )
			throw new IllegalArgumentException("The graph file is empty.");
		
		return graph;
		
	}
	
	public static Graph readDirectedGraph(String fileName) {
		
		Graph graph = null;
		try ( BufferedReader reader = new BufferedReader(new FileReader(fileName)) ) {
			
			String[] t = reader.readLine().split("\\s");
			
			graph = Graphs.makeDirectedGraph( Integer.parseInt(t[0]),
											  Integer.parseInt(t[1]),
											  readEdges(reader) );
			
		} catch (IOException e) {
			
			e.printStackTrace();
			System.exit(1);
			
		}
		
		if ( graph == null )
			throw new IllegalArgumentException("The graph file is empty.");
		
		return graph;
		
	}
	
	public static Grid readGrid(String fileName) {
		
		Grid grid = null;
		try ( BufferedReader reader = new BufferedReader(new FileReader(fileName)) ) {
			
			String[] t = reader.readLine().split("\\s");
			grid = new Grid( Integer.parseInt(t[0]),
							 Integer.parseInt(t[1]),
							 Integer.parseInt(t[2]), 
							 Integer.parseInt(t[3]) );
			grid = (Grid) Graphs.addEdges(grid, readEdges(reader));
			
		} catch (IOException e) {
			
			e.printStackTrace();
			System.exit(1);
			
		}
		
		if ( grid == null )
			throw new IllegalArgumentException("The graph file is empty.");
		
		return grid;
		
	}
	
	// We separate the reading of edges to another method since all the
	// methods above use the same implementation for reading edges in a
	// file.
	private static List<String> readEdges(BufferedReader reader) {
		
		List<String> edges = new LinkedList<String>();
		
		try ( reader ) {
			
			String line;  
			while ( (line = reader.readLine()) != null )  
			{  
		
				edges.add(line);
				
			} 
			
		} catch (IOException e) {
			
			e.printStackTrace();
			System.exit(1);
			
		}

		return edges;
		
	}
	
}