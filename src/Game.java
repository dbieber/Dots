import java.util.Scanner;

public class Solution {

	final static int GRID_SIZE = 5;
	final static int MATRIX_SIZE = 2*GRID_SIZE + 1;
	
	int player;
	
	public Solution(Scanner in) {
		player = in.nextInt();
		
		for (int i=0; i < MATRIX_SIZE; i++) {
			String line = in.nextLine();
			for (int j = 0; j < MATRIX_SIZE; j++) {
				int ij = in.nextInt();
				
			}
		}
		
	}
}
