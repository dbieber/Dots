import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	final static int GRID_SIZE = 5;
	final static int MATRIX_SIZE = 2*GRID_SIZE + 1;
	
	static int player;
	
	public static void takeSquare(GraphNode n) {
    	GraphNode adj = n.children.get(0);
		System.out.println("(" + (n.x+adj.x+1) + "," + (n.y+adj.y+1) + ")");
	}
	
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
					input[j][i] = ij;
				}
			}
	    } catch (Exception e) {
	        System.err.println("Error:" + e.getMessage());
	    }
	    Board b = new Board(input);

	    ArrayList<GraphNode> twos = new ArrayList<GraphNode>();
	    ArrayList<GraphNode> fours = new ArrayList<GraphNode>();
	    
	    for (GraphNode n : b.canBeTakens) {
	    	boolean[][] visited = new boolean[GRID_SIZE][GRID_SIZE];
	    	
	    	boolean chainDone = false;
	    	boolean closed = false;
	    	int chainSize = 1;
	    	visited[n.x][n.y] = true;
	    	GraphNode current = n.children.get(0);
	    	
	    	while (!chainDone) {
		    	visited[current.x][current.y] = true;

			    if (!current.outside)
			    	chainSize++;
		    	
		    	if (current.children.size() == 0) {
		    		chainDone = true;
		    		closed = false;
		    		break;
		    	}
			    if (current.children.size() == 1) {
		    		chainDone = true;
		    		closed = true;
		    		break;
		    	}
			    if (current.children.size() > 2) {
			    	chainDone = true;
			    	closed = false;
			    	break;
			    }
			    if (current.children.size() == 2) {
			    	GraphNode next = current.children.get(0);
			    	if (visited[next.x][next.y])
			    		next = current.children.get(1);
			    	current = next;
			    }
	    	}
	    	
	    	if (! ((closed && chainSize == 4) || (!closed && chainSize == 2))) {
	    		takeSquare(n);
	    		return;
	    	} else {
	    		if (chainSize == 2) {
	    			twos.add(n);
	    		} else if (chainSize == 4) {
	    			fours.add(n);
	    		}
	    	}
	    	
	    	//we've how returned the right edge if there was an obvious choice.
	    	//and we've calculated the twos and fours
	    	//if there's more than one, let's take some!
	    	
	    	if (fours.size() + twos.size() > 1) {
	    		if (fours.size() >= 1) {
	    			takeSquare(fours.get(0));
	    			return;
	    		}
    			takeSquare(twos.get(0));
    			return;
	    	}
	    }
	}
}
