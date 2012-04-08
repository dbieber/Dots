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

        //System.out.println("SIZE:" + b.canBeTakens.size());

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

        
        ArrayList<Edge> okayEdges = new ArrayList<Edge>();
        ArrayList<Edge> badEdges = new ArrayList<Edge>();
        for (GraphNode row[] : b.nodes)
        {
            for (GraphNode n : row)
            {
                if (n.children.size() > 2)
                {
                    for (GraphNode neighbor : n.children)
                    {
                        if (neighbor.children.size() > 2)
                            okayEdges.add(new Edge(n, neighbor));
                        else
                            badEdges.add(new Edge(n, neighbor));
                    }
                }
                else
                {
                    for (GraphNode neighbor : n.children)
                        badEdges.add(new Edge(n, neighbor));
                }
            }
        }

        if (okayEdges.size() > 0) {
            if (fours.size() > 0) {
                takeSquare(fours.get(0));
                return;
            } else if (twos.size() > 0) {
                takeSquare(twos.get(0));
                return;
            }
        }

        Random rand = new Random();
        
        if (okayEdges.size() > 0) {
            int randomIndex = rand.nextInt(okayEdges.size());
            result = okayEdges.get(randomIndex).getResult();
            return;
        }
        
        Edge bestBad = null;
        int bestLen = -1;
        int bestDiff = 0;
        for (Edge e : badEdges) {
            visited = new boolean[GRID_SIZE][GRID_SIZE];
            
            boolean foundLength = false;
            int len1 = 0, len2 = 0;
            GraphNode n = e.node1;
            boolean twoOrFour = false; //open 2 or closed 4

            if (n.children.size() == 1) {
                twoOrFour = true;
                continue;
            }
            if (n.children.size() == 2) {
                visited[n.x][n.y] = true;
                len1++;
                GraphNode next = n.children.get(0);
                if (next == e.node2)
                    next = n.children.get(1);
                
                while (!foundLength) {
                    n = next;
                    if (n.children.size() == 1) {
                        twoOrFour = true;
                        break;
                    }
                    if (n.children.size() == 3 && n == e.node2) {
                        visited[n.x][n.y] = true;
                        next = n.children.get(0);
                        if (!next.outside && visited[next.x][next.y])
                            next = n.children.get(1);
                        if (!next.outside && visited[next.x][next.y])
                            next = n.children.get(2);
                        len1++;
                    }
                    else if (n.children.size() == 2) {
                        visited[n.x][n.y] = true;
                        next = n.children.get(0);
                        if (!next.outside && visited[next.x][next.y])
                            next = n.children.get(1);
                        if (!next.outside && visited[next.x][next.y]) {
                            foundLength = true;
                            break;
                        }
                        len1++;
                    }
                    else {
                        foundLength = true;
                        break;
                    }
                }
                if (twoOrFour) continue;
            }

            //* copy pasted *//
            
            foundLength = false;
            n = e.node2;
            if (n.children.size() == 1) {
                twoOrFour = true;
                continue;
            }
            if (n.children.size() == 2 && !visited[n.x][n.y]) {
                visited[n.x][n.y] = true;
                len2++;
                GraphNode next = n.children.get(0);
                if (next == e.node1)
                    next = n.children.get(1);
                
                while (!foundLength) {
                    n = next;
                    if (n.children.size() == 1) {
                        twoOrFour = true;
                        break;
                    }
                    if (n.children.size() == 3 && n == e.node1) {
                        visited[n.x][n.y] = true;
                        next = n.children.get(0);
                        if (!next.outside && visited[next.x][next.y])
                            next = n.children.get(1);
                        if (!next.outside && visited[next.x][next.y])
                            next = n.children.get(2);
                        len2++;
                    }
                    else if (n.children.size() == 2) {
                        visited[n.x][n.y] = true;
                        next = n.children.get(0);
                        if (!next.outside && visited[next.x][next.y])
                            next = n.children.get(1);
                        if (!next.outside && visited[next.x][next.y]) {
                            foundLength = true;
                            break;
                        }
                        len2++;
                    }
                    else {
                        foundLength = true;
                        break;
                    }
                }
                if (twoOrFour) continue;
            }
            
            //* end copy *//
                
            int len = len1 + len2;
            int diff = Math.abs(len1 - len2);
            if (bestBad == null || (len == bestLen && diff < bestDiff) || len < bestLen) {
                bestBad = e;
                bestLen = len;
                bestDiff = diff;
                //System.out.println("New best: " + bestLen + " edge: " + bestBad +"    lens: "+len1+" "+len2);
            }
        }

        //System.out.println("TWOS AND FOURS: "+twos.size()+" "+fours.size()+" bestLen: "+bestLen);
        if (twos.size() > 0 && bestLen > 2) {
            GraphNode inner = twos.get(0);
            GraphNode outer = inner.children.get(0);
            GraphNode third = outer.children.get(0);
            if (third == inner)
                third = outer.children.get(1);
            Edge e = new Edge(outer, third);
            result = e.getResult();
            return;
        }
        if (fours.size() > 0 && bestLen > 4) {
            GraphNode inner = twos.get(0);
            GraphNode outer = inner.children.get(0);
            GraphNode third = outer.children.get(0);
            if (third == inner)
                third = outer.children.get(1);
            Edge e = new Edge(outer, third);
            result = e.getResult();
            return;
        }
        if (twos.size() > 0 && bestLen == -1) {
            takeSquare(twos.get(0));
            return;
        }
        if (fours.size() > 0 && bestLen == -1) {
            takeSquare(fours.get(0));
            return;
        }
        
        if (bestBad != null)
        {
            result = bestBad.getResult();
            return;
        }
/*        
        // last resort pick random edge.
        if (result[0] == 0 && result[1] == 0)
        {
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
        */
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
        System.out.println(s.result[0] + " " + s.result[1]);
    }
    

    private static class GraphNode {
        int x, y;
        boolean outside;
        ArrayList<GraphNode> children;

        public GraphNode() {
            children = new ArrayList<GraphNode>();
        }

        public void addChild(GraphNode n) {
            children.add(n);
        }
    }
    
    private static class Edge
    {
       
       GraphNode node1;
       GraphNode node2;
       
       Edge (GraphNode e1, GraphNode e2)
       {
          node1= e1;
          node2 = e2;
       }
       
       int[] getResult() {
           int[] result = new int[2];
           result[0] = node1.y+node2.y+1;
           result[1] = node1.x+node2.x+1;
           return result;
       }
       
       public String toString() {
           return getResult()[0]+" "+getResult()[1];
       }
    }
    
    
    private static class Board 
    {
        static final int SIZE = Solution.GRID_SIZE;

        GraphNode[][] nodes;

        ArrayList<GraphNode> canBeTakens;

        public Board(int[][] input) {
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
                            nodes[i][j].addChild(makeOutsideNode(i-1,j));

                    if (input[2*i+2][2*j+1] == 0)
                        if (i<SIZE - 1)
                            nodes[i][j].addChild(nodes[i+1][j]);
                        else
                            nodes[i][j].addChild(makeOutsideNode(i+1,j));

                    if (input[2*i+1][2*j] == 0)
                        if (j>0)
                            nodes[i][j].addChild(nodes[i][j-1]);
                        else
                            nodes[i][j].addChild(makeOutsideNode(i,j-1));

                    if (input[2*i+1][2*j+2] == 0)
                        if (j<SIZE - 1)
                            nodes[i][j].addChild(nodes[i][j+1]);
                        else
                            nodes[i][j].addChild(makeOutsideNode(i,j+1));

                    if (nodes[i][j].children.size() == 1) {
                        canBeTakens.add(nodes[i][j]);
                    }
                }
            }
        }

        public GraphNode makeOutsideNode(int i, int j) {
            GraphNode node = new GraphNode();
            node.outside = true;
            node.x = i;
            node.y = j;
            return node;
        }
    }

    
}
