import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

	final static int GRID_SIZE = 5;
	final static int MATRIX_SIZE = 2*GRID_SIZE + 1;
	
	static int player;
	
	public static void main(String args[]) {
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
					
				}
			}
	        
	        
	    } catch (Exception e) {
	        System.err.println("Error:" + e.getMessage());
	    }
	}
}
