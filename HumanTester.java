import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class HumanTester {

    final static int GRID_SIZE = 5;
    final static int MATRIX_SIZE = 2*GRID_SIZE + 1;

    static int player;

    public static void main(String args[]) {
        int[][] input = new int[MATRIX_SIZE][MATRIX_SIZE];
        int turn = 0; //Computer is 0, Player is 1
        
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
        printBoard(input);
               
    }


    public static void printBoard(int[][] input)
    {
        System.out.print(" ");
        for (int c = 0; c < MATRIX_SIZE; c++)
        {
            System.out.print("  " + c);
        }
        System.out.print("\n");

        for (int r = 0; r < MATRIX_SIZE; r++)
        {
            System.out.print(r + " ");
            for (int c = 0; c < MATRIX_SIZE; c++)
            {
                //If at a dot or takeable position
                if (c % 2  ==  r % 2)
                {
                    //Is a dot
                    if (c % 2 == 0) { 
                        System.out.print(" + ");
                        //Otherwise owned by A,B, or nobody
                    } else if (input[c][r] == 1) {
                        System.out.print(" A ");
                    } else if (input[c][r] == 2) {
                        System.out.print(" B ");
                    } else {
                        System.out.print("   ");
                    }
                } else { //Handle lines
                    if (input[c][r] > 0)
                    {
                        if (r % 2 == 0)
                            System.out.print("---");
                        else
                            System.out.print(" | ");
                    } else {
                        System.out.print("   ");
                    }
                }
            }
            System.out.print("\n");
        }
    }
}
