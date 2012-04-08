public class Edge
{
   
   GraphNode node1;
   GraphNode node2;
   
   Edge (GraphNode e1, GraphNode e2)
   {
      node1= e1;
      node2 = e2;
   }
   
   GraphNode n1()
   {
      return node1;
   }
   
   GraphNode n2()
   {
      return node2;
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