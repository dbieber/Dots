import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Random;

public class Solution {

	final static int GRID_SIZE = 5;
	final static int MATRIX_SIZE = 2*GRID_SIZE + 1;
	
	static int player;
	
	int[] result;
	
	public Solution(int[][] input) {
		result = new int[2];
	    Board b = new Board(input);

	    ArrayList<GraphNode> twos = new ArrayList<GraphNode>();
	    ArrayList<GraphNode> fours = new ArrayList<GraphNode>();
	    
	    System.out.println("SIZE:" + b.canBeTakens.size());

    	boolean[][] visited = new boolean[GRID_SIZE][GRID_SIZE];
	    for (GraphNode n : b.canBeTakens) {
	    	if (visited[n.x][n.y]) continue;
	    	
	    	boolean chainDone = false;
	    	boolean closed = false;
	    	int chainSize = 1;
	    	visited[n.x][n.y] = true;
	    	GraphNode current = n.children.get(0);
	    	
	    	while (!chainDone) {
		    	if (current.children.size() == 0) {
		    		chainDone = true;
		    		closed = false;
		    		break;
		    	}
			    if (current.children.size() == 1) {
			    	visited[current.x][current.y] = true;
		    		chainDone = true;
		    		closed = true;
			    	chainSize++;
		    		break;
		    	}
			    if (current.children.size() > 2) {
			    	chainDone = true;
			    	closed = false;
			    	break;
			    }
			    if (current.children.size() == 2) {
			    	visited[current.x][current.y] = true;
			    	chainSize++;
			    	GraphNode next = current.children.get(0);
			    	if (!next.outside && visited[next.x][next.y])
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

        if (result[0] == 0 && result[1] == 0)
        {
            Random rand = new Random();
            while (true) {
                int x = rand.nextInt(5);
                int y = rand.nextInt(5);
                GraphNode n = b.nodes[y][x];
                if (n.children.size() == 0)
                    continue;
                GraphNode adj = n.children.get(rand.nextInt(n.children.size()));
                result[0] = n.y + adj.y + 1;
                result[1] = n.x + adj.x + 1;
                return;
            }   
        }
	}
	
	public ArrayList<ArrayList<GraphNode>> findComponents(Board b) {
		int[][] compMap = new int[GRID_SIZE][GRID_SIZE];
		ArrayList<ArrayList<GraphNode>> components = new ArrayList<ArrayList<GraphNode>>();
		for (int x = 0; x < GRID_SIZE; ++x) {
			for (int y = 0; y < GRID_SIZE; ++y) {
				if (compMap[x][y] == 0) {
					ArrayList<GraphNode> component = new ArrayList<GraphNode>();
					components.add(component);
					dfs(b.nodes[x][y], components.size(), compMap, component);
				}
			}
		}
		return components;
	}
	
	public void dfs(GraphNode node, int compNo, int[][] compMap, ArrayList<GraphNode> component) {
		if (node.outside) return;
		if (compMap[node.x][node.y] != 0) return;
		compMap[node.x][node.y] = compNo;
		component.add(node);
		for (GraphNode child : node.children) {
			dfs(child, compNo, compMap, component);
		}
	}

    public void takeSquare(GraphNode n) {
    	GraphNode adj = n.children.get(0);
    	result[0] = n.y+adj.y+1;
    	result[1] = n.x+adj.x+1;
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
	    Solution s = new Solution(input);
		System.out.println("(" + s.result[0] + "," + s.result[1] + ")");
	}
}
