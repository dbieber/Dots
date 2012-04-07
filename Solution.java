import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	final static int GRID_SIZE = 5;
	final static int MATRIX_SIZE = 2*GRID_SIZE + 1;
	
	static int player;
	
	public static void main(String args[]) {
		int[][] input = new int[MATRIX_SIZE][MATRIX_SIZE];
	    try {
	        BufferedReader in = 
	          new BufferedReader(new InputStreamReader(System.in));
	        String s = in.readLine();
	        player = Integer.parseInt(s);
			
			for (int i=0; i < MATRIX_SIZE; i++) {
				String line = in.readLine();
				StringTokenizer st = new StringTokenizer(line);
				for (int j = 0; j < MATRIX_SIZE; j++) {
					int ij = Integer.parseInt(st.nextToken());
					input[i][j] = ij;
				}
			}
	    } catch (Exception e) {
	        System.err.println("Error:" + e.getMessage());
	    }
	    Board b = new Board(input);
	    
	    for (GraphNode n : b.canBeTakens) {
	    	boolean[][] visited = new boolean[GRID_SIZE][GRID_SIZE];
	    	visited[n.x][n.y] = true;
	    	
	    	GraphNode next;
	    	for (GraphNode c : n.children) {
	    		
	    	}
	    	
	    	//2 open, 4 closed
	    }
	}
}
