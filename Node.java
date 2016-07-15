
public class Node {

	int jCoord;
	int iCoord;
	int cost;
	Node[] neighbors;
	boolean goal;
	Manhattan m;
	
	int parentI;
	int parentJ;
	
	public Node() {
		
	}
	
	public Node(int iCoord, int jCoord) {
		this.iCoord = iCoord;
		this.jCoord = jCoord;
	}
	
	void setJ(int jCoord) {
		this.jCoord = jCoord;
	}
	
	void setI(int iCoord) {
		this.iCoord = iCoord;
	}
	
	void setCoordinates(int j, int i) {
		this.jCoord = j;
		this.iCoord = i;
	}
	
	void setCost(int cost) {
		this.cost = cost;
	}
	
	void setAsGoal() {
		goal = true;
	}
	
	boolean isGoal() {
		return goal;
	}
	
	void setParent(int parentI, int parentJ) {
		this.parentI = parentI;
		this.parentJ = parentJ;
	}
	
	void printInfo() {
		System.out.println("j: " + jCoord + " i: " + iCoord + " cost: " + cost);
	}
	
	public int getJ() {
		return jCoord;
	}
	
	public int getI() {
		return iCoord;
	}
	
	public int getCost() {
		return cost;
	}
	
	public boolean childOf(Node prev) {
		int prevI = prev.getI();
		int prevJ = prev.getJ();
		
		if((this.parentI == prevI) && (this.parentJ == prevJ)) {
			return true;
		}
		return false;
	}
	
	public void setManCost(Node goal) {
		m = new Manhattan();
		//System.out.print("Calculating Man Cost for node: ");
		this.printInfo();
		cost = m.calculate(this, goal);
		this.setCost(cost);
	}
	
	public boolean nextTo(Node next) {
		this.printInfo();
		System.out.print("^ ??????NEXT TO : ");
		next.printInfo();
		
		int i = this.getI();
		int j = this.getJ();
		int nI = next.getI();
		int nJ = next.getJ();
		//is have to be the same or next to each other. if is are the same, j has to be nJ+1 or nJ-1
		//js have to be the same or next to each other. if js are the same, i has to be nI+1 or nI-1
		
		if(((i==(nI+1) || i==(nI-1) || i==nI)) && ((j==(nJ+1) || j==(nJ-1)) || j==nJ)) {
			return true;
		}
		/*if (j==(nJ+1) || j==(nJ-1)) { //if this is true, also make sure is are next to each other.
			//if the is arent next to each other then they could just be in adjacent rows but super far apart.
			return true;
		}*/
		return false;
	}
}
