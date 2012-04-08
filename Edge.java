public class Edge
{
   
   GraphNode edge1;
   GraphNode edge2;
   
   Edge (GraphNode e1, GraphNode e2)
   {
      edge1 = e1;
      edge2 = e2;
   }
   
   GraphNode n1()
   {
      return edge1;
   }
   
   GraphNode n2()
   {
      return edge2;
   }
}