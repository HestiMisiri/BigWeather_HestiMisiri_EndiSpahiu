/* 
* Special data structure which is used for completing the assignment.
* Inherits from the Graph class so we can use its instatiated objects
* as arguments for algorithm methods that accept Graph types.
*/
public class Grid extends Graph {
	
	private final int bucketCost;
	private final int bondCost;
	
	public Grid(int vertices, int edgeNum, int bucketCost, int bondCost) {
		
		super(vertices, edgeNum);
		this.bucketCost = bucketCost;
		this.bondCost = bondCost;
		
	}
	
	public int getBucketCost() {
		
		return this.bucketCost;
		
	}
	
	public int getBondCost() {
		
		return this.bondCost;
		
	}
	
}