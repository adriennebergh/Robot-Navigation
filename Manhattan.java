
public class Manhattan {
	
	int cost;
	
	public Manhattan() {
		
	}
	
	int calculate(Node n, Node goal) {
		int currI = n.getI();
		int currJ = n.getJ();
		int goalI = n.getI();
		int goalJ = n.getJ();
		//System.out.println("Calculating cost.......");
		//n.printInfo();
		//goal.printInfo();
		
		cost = (Math.abs(currI-goalI)+Math.abs(currJ-goalJ));
		//System.out.println("COST DETERMINED TO BE: "+cost);
		
		return cost;
	}

}
