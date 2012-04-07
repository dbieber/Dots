import java.util.ArrayList;


public class GraphNode {
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
