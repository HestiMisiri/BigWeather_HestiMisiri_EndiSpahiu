import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;

public class BigWeather {
	
	private static Map<Integer, LinkedList<Integer>> CC;
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter import file name: ");
		String importFileName = input.nextLine();
		System.out.print("Enter export file name: ");
		String exportFileName = input.nextLine();
		
		Grid grid = GraphReader.readGrid(importFileName);
		CC = DepthFirstSearch.DFS(grid);
		
		System.out.printf( "Cheapest Configuration Value: %d%n", 
							findConfig(exportFileName, grid) );
		System.out.printf( "Number of Configurations: %d%n", 
							findConfigNum(grid) );
		
	}
	
	private static int findConfigNum(Grid grid) {
		
		// Special Case: If bucket cost is smaller than bond cost,
		// there is only one cheapest configuration.
		if ( grid.getBucketCost() < grid.getBondCost() )
			return 1;
		
		// We find the amount of configs using Kirchhoff's Theorem.
		int configAmount 
			= KirchhoffTheorem.algorithm( grid,
										  CC.values() );
		
		// Special Case: If bucket cost and bond cost are equal, the
		// amount of configurations is raised to the power 2.
		if ( grid.getBucketCost() == grid.getBondCost() )
			return configAmount * configAmount;
		else
			return configAmount;
		
	}
	
	private static int findConfig(String fileName, Grid grid) {
		
		// Special Case: If the bucket cost is lower or equal to the
		// bond cost, its easier to just designate all dynos as buckets
		if ( grid.getBucketCost() <= grid.getBondCost() ) {
			
			// Instatiate a List<String> with all dynos being designated
			// as buckets to send to the exportConfig method.
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < grid.getVertices(); i++)
				s.append(String.format("%d ", i + 1));
			
			List<String> l = new LinkedList<String>();
			l.add(s.toString());
			exportConfig(fileName, grid, l);
			
			return grid.getBucketCost() * grid.getVertices();
			
		}
		
		int costSum = CC.size() * grid.getBucketCost();
		
		for ( List<Integer> l : CC.values() ) {
			
			costSum += (l.size() - 1) * grid.getBondCost();
			
		}
		
		exportConfig(fileName, grid, BreadthFirstSearch.modAlgo(grid, CC.values()));
		
		return costSum;
		
	}
	
	// A method to export a configuration path to a file for visualisation.
	private static void exportConfig(String fileName, 
									 Graph graph, 
									 List<String> edges) {
		
		try ( BufferedWriter writer 
				= new BufferedWriter(new FileWriter(fileName)) ) {
			
			writer.write(String.format( "%d %d", graph.getVertices(), 
												 graph.getEdgeNum()) );
			
			for (String s : edges) {
				
				writer.newLine();
				writer.write(s);
				
			}
			
		} catch (IOException e) {
			
			throw new IllegalArgumentException("Invalid file name.");
			
		}
		
	}
	
}