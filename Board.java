import java.util.ArrayList;

public class Board 
{
    private Box[] board;
    
    private class Box 
    {
        int index;
        int up;
        int down;
        int left;
        int right;
        
        Box(int index)
        {
            this.index = index;
            if (index == 0){
                up == null;
                down == null;
                left == null;
                right == null;
            }
            else{
                if (index%5 == 0) {
                    right = 0;
                    left = index - 1;
                }
                else {
                    right = index + 1;
                    if (index%5 == 1) left = 0;
                    else left = index - 1;
                }
                if (index <= 5) {
                    top = 0;
                    bottom = index + 5;
                }
                else {
                    top = index - 5;
                    if (index > 20) bottom = 0;
                    else bottom index + 5;
                }
            }
        }
    }
    
    public Board(int[][] b)
    {
        board = new Box[26];
        for (int i = 0; i < 26, i++) {
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
    
    public ArrayList<Box> threeSided() {
        ArrayList<Box> list = new ArrayList<Box>();
        for (Box b:board) {
            numNull = 0;
            if (b.up == null) numNull++;
            if (b.down == null) numNul++;
            if (b.left == null) numNull++;
            if (b.right == null) numNull++;
            if (numNull == 3) list.add(b);
        }
        return list;
    }
}
                    
            
        
    
    