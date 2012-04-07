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
	
    private Box[] board;
    
    private class Box 
    {
        int index;
        Box up;
        Box down;
        Box left;
        Box right;
        
        Box(int index)
        {
            this.index = index;
            if (index == 0){
                up = null;
                down = null;
                left = null;
                right = null;
            }
            else{
                if (index%5 == 0) {
                    right = board[0];
                    left = board[index - 1];
                }
                else {
                    right = board[index + 1];
                    if (index%5 == 1) left = board[0];
                    else left = board[index - 1];
                }
                if (index <= 5) {
                    up = board[0];
                    down = board[index + 5];
                }
                else {
                    up = board[index - 5];
                    if (index > 20) down = board[0];
                    else down = board[index + 5];
                }
            }
        }
    }
    
/*    public Board(int[][] b)
    {
        board = new Box[26];
        for (int i = 0; i < 26; i++) {
            board[i] = new Box(i);
        }
        int curr = 1;
        for (int i = 1; i < b.length; i += 2) {
            for (int j = 1; j < b.length; j += 2)
            {
             if (b[i-1][j] == 1) board[curr].up = null;
             if (b[i][j-1] == 1) board[curr].left = null;
             if (b[i][j+1] == 1) board[curr].right = null;
             if (b[i+1][j] == 1) board[curr].down = null;
             curr++;
            }
        }
    }
    */
    
    public ArrayList<Box> threeSided() {
        ArrayList<Box> list = new ArrayList<Box>();
        for (Box b:board) {
            int numNull = 0;
            if (b.up == null) numNull++;
            if (b.down == null) numNull++;
            if (b.left == null) numNull++;
            if (b.right == null) numNull++;
            if (numNull == 3) list.add(b);
        }
        return list;
    }
}
                    
            
        
    
    