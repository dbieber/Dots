import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Scanner;

public class HumanTester {

    final static int GRID_SIZE = 5;
    final static int MATRIX_SIZE = 2*GRID_SIZE + 1;

    static int player;
    static int AUTOMATED = 1;
    static int turn = 1; //Computer = 1, player = 2
    public static void main(String args[]) {
        int[][] board = new int[MATRIX_SIZE][MATRIX_SIZE];
        int turn = 0; //Computer is 0, Player is 1
        /*
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
                    board[j][i] = ij;
                }
            }
        } catch (Exception e) {
            System.err.println("Error:" + e.getMessage());
        }
        */

        while(true)
        {
            printBoard(board);
            System.out.print("\n\n");
            if (turn == 1)
            {
                Solution AI = new Solution(board);
                if (board[AI.result[1]][AI.result[0]] != 0)
                    {
                    System.out.print("(" + AI.result[1] + ", " + AI.result[0] + ") is not a valid move");
                    break;
                    }
                addEdge(board, 1, AI.result[0], AI.result[1]);
                if (AUTOMATED == 0)
                    turn = 2;
                System.out.println("\n\n\n");
                
            } else {
                    System.out.print("Move in row column format: "); 
                    Scanner s = new Scanner(System.in);
                    int Row = s.nextInt();
                    int Col = s.nextInt();
                    addEdge(board, 2, Row, Col);
                    turn = 1;
            }
        }
    }




    public static void addEdge(int[][] board, int turn, int x, int y)
    {
        System.out.println("move: (" + x + "," + y + ")");
        board[y][x] = turn;
        // - line
        if (y % 2 == 0)
        {



        } else { // | line



        }


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
