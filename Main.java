import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		String fileName = args[0];
		
		////////////////////
		// MANHATTAN COST //
		////////////////////
		System.out.println("MANHATTAN COST ANALYSIS");
		System.out.println("-----------------------");
		ArrayList<Node> path = new ArrayList<Node>();
		Strategy s = new Strategy(fileName);
		Node curr, goal;
		s.setInitial(); 
		s.setGoal(); 
		curr = s.getInitial();
		goal = s.getGoal();
		s.sendtoPQ(curr);
		s.addToClosed(curr);
		
		while(!s.pq.isEmpty()) {
			curr = s.pq.remove();
			
			if((curr.getI() == goal.getI()) && (curr.getJ() == goal.getJ())) {
				path.add(curr);
				break;
			}
			path.add(curr);
			Node[] neighbors = s.getNeighbors(curr);
			s.addManCosts(neighbors, goal);
			s.sendtoPQ(neighbors);
		}
		s.fixPath(path);
		
		for(Node elem : path) {
			int iCoord = elem.getI();
			int jCoord = elem.getJ();
			s.map[iCoord][jCoord] = 2;
		}
		
		int initialICoordo = path.get(0).getI();
		int initialJCoordo = path.get(0).getJ();
		s.map[initialICoordo][initialJCoordo] = 8;
		
		int goalICoordo = path.get(path.size()-1).getI();
		int goalJCoordo = path.get(path.size()-1).getJ();
		s.map[goalICoordo][goalJCoordo] = 9;
		
		int[][] fMap = s.f.getMap();
		char[][] finalMap = new char[s.dimension][s.dimension];
		
		for(int i=0; i<s.dimension; i++) {
			for(int j=0; j<s.dimension; j++) {
				if(fMap[i][j] == 0) {
					finalMap[i][j] = '.';
				}
				else if(fMap[i][j] == 1) {
					finalMap[i][j] = '+';
				}
				else if(fMap[i][j] == 2) {
					finalMap[i][j] = 'o';
				}
				else if(fMap[i][j] == 9) {
					finalMap[i][j] = 'g';
				}
				else if(fMap[i][j] == 8) {
					finalMap[i][j] = 'i';
				}
			}
		}
		System.out.println();
		System.out.println("Navigated Map: ");
		for(int i=0; i<s.dimension; i++) {
			for(int j=0; j<s.dimension; j++) {
				System.out.print(finalMap[i][j]+ " ");
			}
			System.out.println();
		}
		
		////////////////////////////////////////////////
		// HEURISTIC COST DERIVED FROM MANHATTAN COST //
		////////////////////////////////////////////////
		System.out.println();
		System.out.println("HEURISTIC WITH MANHATTAN COST ANALYSIS");
		System.out.println("--------------------------------------");
		ArrayList<Node> pathH1 = new ArrayList<Node>();
		Strategy sH1 = new Strategy(fileName);
		Node currH1, goalH1;
		sH1.setInitial(); 
		sH1.setGoal(); 
		currH1 = sH1.getInitial();
		goalH1 = sH1.getGoal();
		sH1.sendtoPQ(currH1);
		sH1.addToClosed(currH1);
		
		while(!sH1.pq.isEmpty()) {
			currH1 = sH1.pq.remove();
			
			if((currH1.getI() == goalH1.getI()) && (currH1.getJ() == goalH1.getJ())) {
				pathH1.add(currH1);
				break;
			}
			pathH1.add(currH1);
			Node[] neighborsH1 = sH1.getNeighbors(currH1);
			sH1.addHOneCosts(neighborsH1, goalH1, pathH1);
			sH1.sendtoPQ(neighborsH1);
		}
		
		sH1.fixPath(pathH1);
		
		for(Node elem : pathH1) {
			int iCoord = elem.getI();
			int jCoord = elem.getJ();
			sH1.map[iCoord][jCoord] = 2;
		}
		
		int initialICoordH1 = pathH1.get(0).getI();
		int initialJCoordH1 = pathH1.get(0).getJ();
		sH1.map[initialICoordH1][initialJCoordH1] = 8;
		
		int goalICoordH1 = pathH1.get(pathH1.size()-1).getI();
		int goalJCoordH1 = pathH1.get(pathH1.size()-1).getJ();
		sH1.map[goalICoordH1][goalJCoordH1] = 9;
		
		int[][] fMapH1 = sH1.f.getMap();
		char[][] finalMapH1 = new char[sH1.dimension][sH1.dimension];
		
		for(int i=0; i<sH1.dimension; i++) {
			for(int j=0; j<sH1.dimension; j++) {
				if(fMapH1[i][j] == 0) {
					finalMapH1[i][j] = '.';
				}
				else if(fMapH1[i][j] == 1) {
					finalMapH1[i][j] = '+';
				}
				else if(fMapH1[i][j] == 2) {
					finalMapH1[i][j] = 'o';
				}
				else if(fMapH1[i][j] == 9) {
					finalMapH1[i][j] = 'g';
				}
				else if(fMapH1[i][j] == 8) {
					finalMapH1[i][j] = 'i';
				}
			}
		}
		
		System.out.println();
		System.out.println("Navigated Map: ");
		for(int i=0; i<sH1.dimension; i++) {
			for(int j=0; j<sH1.dimension; j++) {
				System.out.print(finalMapH1[i][j]+ " ");
			}
			System.out.println();
		}
		
		////////////////////
		// EUCLIDEAN COST //
		////////////////////
		
		System.out.println();
		System.out.println("EUCLIDEAN COST ANALYSIS");
		System.out.println("-----------------------");
		ArrayList<Node> pathE = new ArrayList<Node>();
		Strategy sE = new Strategy(fileName);
		Node currE, goalE;
		sE.setInitial(); 
		sE.setGoal(); 
		currE = sE.getInitial();
		goalE = sE.getGoal();
		sE.sendtoPQ(currE);
		sE.addToClosed(currE);
		
		while(!sE.pq.isEmpty()) {
			currE = sE.pq.remove();
			
			if((currE.getI() == goalE.getI()) && (currE.getJ() == goalE.getJ())) {
				pathE.add(currE);
				break;
			}
			pathE.add(currE);
			Node[] neighborsE = sE.getNeighbors(currE);
			sE.addEuclideanCosts(neighborsE, goalE);
			sE.sendtoPQ(neighborsE);
		}
		
		sE.fixPath(pathE);
		
		for(Node elem : pathE) {
			int iCoord = elem.getI();
			int jCoord = elem.getJ();
			sE.map[iCoord][jCoord] = 2;
		}
		
		int initialICoordE = pathE.get(0).getI();
		int initialJCoordE = pathE.get(0).getJ();
		sE.map[initialICoordE][initialJCoordE] = 8;
		
		int goalICoordE = pathE.get(pathE.size()-1).getI();
		int goalJCoordE = pathE.get(pathE.size()-1).getJ();
		sE.map[goalICoordE][goalJCoordE] = 9;
		
		int[][] fMapE = sE.f.getMap();
		char[][] finalMapE = new char[sE.dimension][sE.dimension];
		
		for(int i=0; i<sE.dimension; i++) {
			for(int j=0; j<sE.dimension; j++) {
				if(fMapE[i][j] == 0) {
					finalMapE[i][j] = '.';
				}
				else if(fMapE[i][j] == 1) {
					finalMapE[i][j] = '+';
				}
				else if(fMapE[i][j] == 2) {
					finalMapE[i][j] = 'o';
				}
				else if(fMapE[i][j] == 9) {
					finalMapE[i][j] = 'g';
				}
				else if(fMapE[i][j] == 8) {
					finalMapE[i][j] = 'i';
				}
			}
		}
		
		System.out.println();
		System.out.println("Navigated Map: ");
		for(int i=0; i<sE.dimension; i++) {
			for(int j=0; j<sE.dimension; j++) {
				System.out.print(finalMapE[i][j]+ " ");
			}
			System.out.println();
		}
		
		////////////////////////////////////////////////
		// HEURISTIC COST DERIVED FROM EUCLIDEAN COST //
		////////////////////////////////////////////////
		
		System.out.println();
		System.out.println("HEURISTIC WITH EUCLIDEAN COST ANALYSIS");
		System.out.println("--------------------------------------");
		ArrayList<Node> pathH2 = new ArrayList<Node>();
		Strategy sH2 = new Strategy(fileName);
		Node currH2, goalH2;
		sH2.setInitial(); 
		sH2.setGoal(); 
		currH2 = sH2.getInitial();
		goalH2 = sH2.getGoal();
		sH2.sendtoPQ(currH2);
		sH2.addToClosed(currH2);
		
		while(!sH2.pq.isEmpty()) {
			currH2 = sH2.pq.remove();
			
			if((currH2.getI() == goalH2.getI()) && (currH2.getJ() == goalH2.getJ())) {
				pathH2.add(currH2);
				break;
			}
			pathH2.add(currH2);
			Node[] neighborsH2 = sH2.getNeighbors(currH2);
			sH2.addHTwoCosts(neighborsH2, goalH2, pathH2);
			sH2.sendtoPQ(neighborsH2);
		}
		
		sH2.fixPath(pathH2);
		
		for(Node elem : pathH2) {
			int iCoord = elem.getI();
			int jCoord = elem.getJ();
			sH2.map[iCoord][jCoord] = 2;
		}
		
		int initialICoord = pathH2.get(0).getI();
		int initialJCoord = pathH2.get(0).getJ();
		sH2.map[initialICoord][initialJCoord] = 8;
		
		int goalICoord = pathH2.get(pathH2.size()-1).getI();
		int goalJCoord = pathH2.get(pathH2.size()-1).getJ();
		sH2.map[goalICoord][goalJCoord] = 9;
		
		int[][] fMapH2 = sH2.f.getMap();
		char[][] finalMapH2 = new char[sH2.dimension][sH2.dimension];
		
		for(int i=0; i<sH2.dimension; i++) {
			for(int j=0; j<sH2.dimension; j++) {
				if(fMapH2[i][j] == 0) {
					finalMapH2[i][j] = '.';
				}
				else if(fMapH2[i][j] == 1) {
					finalMapH2[i][j] = '+';
				}
				else if(fMapH2[i][j] == 2) {
					finalMapH2[i][j] = 'o';
				}
				else if(fMapH2[i][j] == 3) {
					finalMapH2[i][j] = 'g';
				}
				else if(fMapH2[i][j] == 8) {
					finalMapH2[i][j] = 'i';
				}
				else if(fMapH2[i][j] == 9) {
					finalMapH2[i][j] = 'g';
				}
			}
		}
		
		System.out.println();
		System.out.println("Navigated Map: ");
		for(int i=0; i<sH2.dimension; i++) {
			for(int j=0; j<sH2.dimension; j++) {
				System.out.print(finalMapH2[i][j]+ " ");
			}
			System.out.println();
		}
		
		System.exit(0);
		//change the map to show path, output info
	}
}
