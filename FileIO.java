import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class FileIO {
	String fileName, line;
	Map m;
	
	public FileIO(String file) {
		this.fileName = file;
		this.line = null;
		this.m = new Map();
	}

	public void readFile() {
		
		try {
			BufferedReader bRead = new BufferedReader(new FileReader(fileName));
			
			int d = Integer.parseInt(bRead.readLine());
			m.createMap(d);
			
			//populate map with characters from file
			for(int i=0; i<d; i++) {
				line = bRead.readLine();
				for(int j=0; j<d; j++) {
					char c = line.charAt(j);
					m.map[i][j] = c;
				}
			}
			System.out.println("Original map: ");
			m.printInfo();
			m.convertMap();
			
			bRead.close();
		} catch (FileNotFoundException ex) {
			System.out.println(ex.toString());
		} catch (IOException ex) {
			System.out.println(ex.toString());
		}
	}

	public int[][] getMap() {
		return m.convertedMap;
	}
	
	public int getDimensions() {
		return m.dimension;
	}
}
