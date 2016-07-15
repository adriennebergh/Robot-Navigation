import java.util.ArrayList;

//start at 2
	//look at all neighbors
	//if neighbor == 1, it can't go there
	//else, create a node for the neighbor that contains its coordinates and cost to get to goal
		//put the node into the fringe
	//if neighbor == 3, that neighbor is the goal state
	
	//FRINGE: sort based on costs
public class Strategy {
	FileIO f;
	int[][] map;
	Node[] neighbors;
	Node[] CLOSED;
	int closedLength;
	int dimension;
	Node initial;
	Node goal;
	PriorityQueue pq;
	
	public Strategy(String file) {
		this.f = new FileIO(file);
		f.readFile();
		this.map = f.getMap(); //map = convertedMap from Map class
		this.dimension = f.getDimensions();
		pq = new PriorityQueue();
		pq.createPQ(dimension);
		this.closedLength = 0;
		this.CLOSED = new Node[dimension*dimension];
	}
	
	//iterate through map to find initial position --> add coordinates to initialNode
	public void setInitial() {
		initial = new Node();
		for(int i=0; i<dimension; i++) {
			for(int j=0; j<dimension; j++) {
				if (map[i][j] == 2) {
					initial.setCoordinates(j,i);
				}
			}
		}
	}
	
	public Node getInitial() {
		return initial;
	}
	
	//iterate through map to find goal position --> add coordinates to goalNode
	public void setGoal() {
		goal = new Node();
		for(int i=0; i<dimension; i++) {
			for(int j=0; j<dimension; j++) {
				if (map[i][j] == 3) {
					goal.setCoordinates(j,i);
					goal.setCost(0);
				}
			}
		}
	}
	
	public Node getGoal() {
		return goal;
	}
	
	//method returning an array of all viable neighbors of current node.
	//method does not add costs to neighbor Nodes (unless GOAL)
	public Node[] getNeighbors(Node n) {
		neighbors = new Node[4];
		
		int jC = n.getJ();
		int iC = n.getI();
		int left=4;
		int right=4;
		int top=4;
		int bottom=4;
		Node newN;
		
		if(iC == 0) { //if node in top row
			if(jC == 0) { //if node is on upper left corner of map, only check right and below neighbors
				right = map[iC][jC+1];
				bottom = map[iC+1][jC];
			}
			else if(jC == dimension-1) { //if node is on upper right corner, only check left and below neighbors
				left = map[iC][jC-1];
				bottom = map[iC+1][jC];
			}
			else { //only check left, right, and below neighbors
				left = map[iC][jC-1];
				bottom = map[iC+1][jC];
				right = map[iC][jC+1];
			}
		}
		else if (iC == dimension-1) { //if node is in bottom row
			if(jC==0) { //if node bottom left corner, only check top and right neighbors
				right = map[iC][jC+1];
				top = map[iC-1][jC];
			}
			else if(jC == dimension-1) { //if node bottom right corner, only check top and left neighbors
				left = map[iC][jC-1];
				top = map[iC-1][jC];
			}
			else { //only check left, top, right neighbors
				left = map[iC][jC-1];
				top = map[iC-1][jC];
				right = map[iC][jC+1];
			}
		}
		else if(jC==0 && !(iC==0 || iC == dimension-1)) { //if node is in left column, not corner
			top = map[iC-1][jC];
			right = map[iC][jC+1];
			bottom = map[iC+1][jC];
		}
		else if(jC==dimension-1 && !(iC==0 || iC==dimension-1)) { //if node is in right column, not corner
			bottom = map[iC+1][jC];
			left = map[iC][jC-1];
			top = map[iC-1][jC];
		}
		else { //all nodes not on the border of the map
			left = map[iC][jC-1];
			right = map[iC][jC+1];
			bottom = map[iC+1][jC];
			top = map[iC-1][jC];
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////
		
		if(left == 0) {
			newN = new Node(iC, jC-1);
			newN.setParent(iC, jC);
			neighbors[0] = newN;
		}
		else if(left == 3) {
			newN = new Node(iC, jC-1);
			newN.setParent(iC, jC);
			newN.setCost(0);
			newN.setAsGoal();
			neighbors[0] = newN;
		}
		
		if(right == 0) {
			newN = new Node(iC, jC+1);
			newN.setParent(iC, jC);
			neighbors[1] = newN;
		}
		else if(right == 3) {
			newN = new Node(iC, jC+1);
			newN.setParent(iC, jC);
			newN.setCost(0);
			newN.setAsGoal();
			neighbors[1] = newN;
		}
		
		if(top == 0) {
			newN = new Node(iC-1, jC);
			newN.setParent(iC, jC);
			neighbors[2] = newN;
		}
		else if(top == 3) {
			newN = new Node(iC-1, jC);
			newN.setParent(iC, jC);
			newN.setCost(0);
			newN.setAsGoal();
			neighbors[2] = newN;
		}
		
		if(bottom == 0) {
			newN = new Node(iC+1, jC);
			newN.setParent(iC, jC);
			neighbors[3] = newN;
		}
		else if(bottom == 3) {
			newN = new Node(iC+1, jC);
			newN.setParent(iC, jC);
			newN.setCost(0);
			newN.setAsGoal();
			neighbors[3] = newN;
		}
		
		return neighbors;
	}
	
	//add Manhattan costs to Nodes in neighbors array
	public void addManCosts(Node[] n, Node g) {
		int nI, nJ, gI, gJ;
		int cost;
		goal = g;
		for(Node current : n) {
			if(current == goal) {
				current.setCost(0);
				current.setAsGoal();
			}
			else if (current != null) {
				nI = current.getI();
				nJ = current.getJ();
				gI = goal.getI();
				gJ = goal.getJ();
				cost = (Math.abs(nI-gI)+Math.abs(nJ-gJ));
				current.setCost(cost);
			}
			else {
				continue;
			}
				
		}
	}
	
	//add HEURISTIC1 costs to Nodes in neighbors array
	public void addHOneCosts(Node[] n, Node g, ArrayList<Node> path) {
		int nI, nJ, gI, gJ;
		int mcost, cost;
		goal = g;
		for(Node current : n) {
			if(current == goal) {
				current.setCost(0);
				current.setAsGoal();
			}
			else if (current != null) {
				//current.setManCost(goal);
				nI = current.getI();
				nJ = current.getJ();
				gI = goal.getI();
				gJ = goal.getJ();
				mcost = (Math.abs(nI-gI)+Math.abs(nJ-gJ));
				cost = path.size() +mcost;
				current.setCost(cost);
			}
			else {
				continue;
			}
				
		}
	}
	
	//add Euclidean costs to Nodes in neighbors array
	public void addEuclideanCosts(Node[] n, Node g) {
		int nI, nJ, gI, gJ;
		int cost;
		Double d;
		goal = g;
		for(Node current : n) {
			if(current == goal) {
				current.setCost(0);
				current.setAsGoal();
			}
			else if (current != null) {
				nI = current.getI();
				nJ = current.getJ();
				gI = goal.getI();
				gJ = goal.getJ();
				d = new Double(Math.sqrt(((nI-gI)*(nI-gI))+((nJ-gJ)*(nJ-gJ))));
				cost = d.intValue();
				current.setCost(cost);
			}
			else {
				continue;
			}
				
		}
	}
	
	//add HEURISTIC2 costs to Nodes in neighbors array
	public void addHTwoCosts(Node[] n, Node g, ArrayList<Node> path) {
		int nI, nJ, gI, gJ;
		int cost;
		Double d;
		goal = g;
		for(Node current : n) {
			if(current == goal) {
				current.setCost(0);
				current.setAsGoal();
			}
			else if (current != null) {
				//current.setManCost(goal);
				nI = current.getI();
				nJ = current.getJ();
				gI = goal.getI();
				gJ = goal.getJ();
				d = new Double(Math.sqrt(((nI-gI)*(nI-gI))+((nJ-gJ)*(nJ-gJ))));
				cost = path.size() + d.intValue();
				current.setCost(cost);
			}
			else {
				continue;
			}
				
		}
	}
	
	//send neighbors to priority queue
	public void sendtoPQ(Node[] n) {
		mainLoop: for(Node current : n) {
			if(current == goal) {
				pq.add(current);
				pq.sort();
			}
			if (current != null) {
				for(int i=0; i<closedLength; i++) {
					if (CLOSED[i].getI() == current.getI() && CLOSED[i].getJ() == current.getJ()) {
						//break;
						continue mainLoop;
					}
				}
				pq.add(current);
				pq.sort();
				
				CLOSED[closedLength] = current;
				closedLength++;
			}
			else {
				continue;
			}
		}
		/*System.out.print("PQ: ");
		pq.print();*/
	}
	
	public void sendtoPQ(Node n) {
		boolean inClosed = false;
		for(int i=0; i<closedLength; i++) {
			if (CLOSED[i].getI() == n.getI() && CLOSED[i].getJ() == n.getJ()) {
				inClosed = true;
				break;
			}
		}
		if(!inClosed) {
			pq.add(n);
			pq.sort();
		}
	}
	
	public void addToClosed(Node n) {
		CLOSED[closedLength] = n;
		closedLength++;
	}
	
	public void checkPath(ArrayList<Node> path) {
		restartLoop: while(true) {
			for(int i=0; i<(path.size()-1); i++) {
				if(!(path.get(i).nextTo(path.get(i+1)))) {
					if(path.get(i).nextTo(path.get(i+2))) {
						path.remove(i+1);
						continue restartLoop;
					}
					else {
						path.remove(i);
						continue restartLoop;
					}
				}
			}
			break;
		}
	}
	
	public void fixPath(ArrayList<Node> path) {
		restartLoop: while(true) {
			for(int i=path.size()-1; i>0; i--) {
				if(!path.get(i).childOf(path.get(i-1))) {
					path.remove(i-1);
					continue restartLoop;
				}
			}
			break;
		}
	}
	
	public void addToPath(ArrayList<Node> path, Node curr) {
		//if(path.isEmpty() || path.size() == 1) {
		if(path.isEmpty()) {
			path.add(curr);
		}
		else {
			while(true) {
				Node end = path.get(path.size() -1);
				if(!(curr.childOf(end))) {
					path.remove(end);
				}
				else {
					path.add(curr);
					break;
				}
			}
		}
	}
	
}
