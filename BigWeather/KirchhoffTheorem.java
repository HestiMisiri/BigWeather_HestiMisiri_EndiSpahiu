import java.util.Collection;
import java.util.LinkedList;

/*
* This file contains the algorithm implementation of Kirchhoff's Theorem
* which we will use to find all possible configuration, by first calculating
* all spanning trees of each Connected Component and then multiplying it with
* the amount of nodes that each Connected Component contains.
*/

public class KirchhoffTheorem {
	
	public static int algorithm(Graph graph, Collection<LinkedList<Integer>> CC) {
		
		int sum = 0;
		// We use this count variable to keep track of how many nodes
		// we've gone through in our Graph, to keep it from failing.
		int count = 0;
		
		// Enhanced for-loop which goes over each Connected Component.
		for ( LinkedList<Integer> l : CC) {
			
			// Instatiate a the Laplacian matrix for each component.
			int[][] matrix = new int[l.size()][l.size()];
			for ( int v : l ) {
				
				matrix[v - count][v - count] = graph.getEdges(v).size();
				for ( int u : graph.getEdges(v) ) {
					
					matrix[v - count][u - count] = -1;
					
				}
				
			}
			
			// Instatiate the minor of the Laplacian matrix.
			int[][] minor = new int[matrix.length - 1][matrix.length - 1];
			for (int i = 1; i < matrix.length; i++) {
				
				for (int j = 1; j < matrix[i].length; j++) {
					
					minor[i - 1][j - 1] = matrix[i][j];
					
				}
				
			}
			
			// Add-up the count.
			count += matrix.length;
			// Sum the determinant of the minor of the Laplacian Matrix for
			// the Connected Component.
			sum += l.size() * getDeterminant(minor, minor.length);
			
		}
		
		return sum;
		
	}
	
	/*
	* The implementation for calculating the determinant
	* of a Matrix has been taken from here:
	* https://www.geeksforgeeks.org/java-program-to-find-the-determinant-of-a-matrix/
	*/
	private static void getCofactor(int mat[][], int temp[][],
									int p, int q, int n) {
		
        int i = 0, j = 0;

        for (int row = 0; row < n; row++) {
			
            for (int col = 0; col < n; col++) {
                
                // Copying into temporary matrix only those element which are
                // not in given row and column.
                if ( (row != p) && (col != q) ) {
					
                    temp[i][j++] = mat[row][col];
                    
                    // Row is filled, so increase row index and reset col index.
                    if ( j == n - 1 ) {
						
                        j = 0;
                        i++;
						
                    }
					
                }
				
            }
			
        }
		
    }

    // Recursive function for finding determinant of a matrix. 
    // n is the current dimension of mat[][].
    private static int getDeterminant(int mat[][], int n) {
		
        int D = 0;

        // Base Case: Matrix contains only one element.
        if (n == 1)
            return mat[0][0];

        // 2D Array to store cofactors.
        int temp[][] = new int[n][n];

        // To store sign multiplier.
        int sign = 1;

        // Iterate for each element of first row.
        for (int f = 0; f < n; f++) {
            
            // Getting Cofactor of mat[0][f], and
			// assigning them to the temp[][].
            getCofactor(mat, temp, 0, f, n);
            D += sign * mat[0][f] * getDeterminant(temp, n - 1);

			// Terms are added up with alternated signs.
            sign = -sign;
        
		}

        return D;
		
    }
	
}