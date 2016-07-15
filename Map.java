
public class Map {
	
	int dimension;
	char[][] map;
	int[][] convertedMap;
	
	public Map() {
		
	}
	
	void createMap(int dimensions) {
		this.dimension = dimensions;
		this.map = new char[dimensions][dimensions];
	}
	
	int[][] getMap() {
		return convertedMap;
	}
	
	int getDim() {
		return dimension;
	}
	
	void printInfo() {
		//System.out.println("Original map: " + dimension + " rows and columns.");
		for(int i=0; i<dimension; i++) {
			for(int j=0; j<dimension; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	void convertMap() {
		convertedMap = new int[dimension][dimension];
		for(int i=0; i<dimension; i++) {
			for(int j=0; j<dimension; j++) {
				char state = map[i][j];
				switch(state) {
					case '.':
						convertedMap[i][j] = 0;
						break;
					case '+':
						convertedMap[i][j] = 1;
						break;
					case 'i':
						convertedMap[i][j] = 2;
						break;
					case 'g':
						convertedMap[i][j] = 3;
						break;
				}
			}
		}
	}
	
	void printCInfo() {
		System.out.println("Converted map: " + dimension + " rows and columns.");
		for(int i=0; i<dimension; i++) {
			for(int j=0; j<dimension; j++) {
				System.out.print(convertedMap[i][j] + " ");
			}
			System.out.println();
		}
	}

}
