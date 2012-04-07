import java.util.ArrayList;

public class Board 
{
	static final int SIZE = Solution.GRID_SIZE;
	
	GraphNode[][] nodes;
	
	ArrayList<GraphNode> canBeTakens;
	
	public Board(int[][] input) {
		GraphNode outside = new GraphNode();
		outside.outside = true;

		canBeTakens = new ArrayList<GraphNode>();
		
		nodes = new GraphNode[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) { //y
				nodes[i][j] = new GraphNode();
				nodes[i][j].x = i;
				nodes[i][j].y = j;
			}
		}
		for (int i = 0; i < SIZE; i++) { //x
			for (int j = 0; j < SIZE; j++) { //y
				
				if (input[2*i][2*j+1] == 0)
					if (i>0)
						nodes[i][j].addChild(nodes[i-1][j]);
					else
						nodes[i][j].addChild(outside);

				if (input[2*i+2][2*j+1] == 0)
					if (i<SIZE - 1)
						nodes[i][j].addChild(nodes[i+1][j]);
					else
						nodes[i][j].addChild(outside);

				if (input[2*i+1][2*j] == 0)
					if (j>0)
						nodes[i][j].addChild(nodes[i][j-1]);
					else
						nodes[i][j].addChild(outside);

				if (input[2*i+1][2*j+2] == 0)
					if (j<SIZE - 1)
						nodes[i][j].addChild(nodes[i][j+1]);
					else
						nodes[i][j].addChild(outside);
				
				if (nodes[i][j].children.size() == 1) {
					canBeTakens.add(nodes[i][j]);
				}
			}
		}
	}
}
                    
            
        
    
    