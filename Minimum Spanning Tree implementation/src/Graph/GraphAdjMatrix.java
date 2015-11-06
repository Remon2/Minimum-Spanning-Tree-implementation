package Graph;

import java.util.ArrayList;
import java.util.Arrays;

public class GraphAdjMatrix {
	private GraphNode root;
	private ArrayList<GraphNode> nodes;
	private double[][] adjMatrix;
	private boolean isDirected;
	private int size;
	private boolean visited[];
	private GraphNode prev[];
	ArrayList<Edge> edges;
	public static final int DEFAULT_SIZE = 5;
	// inTree[i] is true if the node i is already in the minimum
	// spanning tree; false otherwise
	private boolean inMST[];

	// dist[i] is the distance between node i and the minimum spanning
	// tree; this is initially infinity (MAX Integer); if i is already in
	// the tree, then dist[i] is undefined;
	// this is just a temporary variable. It's not necessary but speeds
	// up execution considerably (by a factor of n) */
	private double dist[];

	// whoTo[i] holds the index of the node i would have to be
	// linked to in order to get a distance of dist[i]
	private GraphNode[] whoTo;

	public GraphAdjMatrix() {
		nodes = new ArrayList<GraphNode>();
		adjMatrix = new double[size][size];
		inMST = new boolean[size];
		// Mark all nodes not in the MST with false.
		Arrays.fill(inMST, false);

		dist = new double[size];
		// Initialize Distances with Infinity.
		Arrays.fill(dist, Integer.MAX_VALUE);

		whoTo = new GraphNode[size];
		visited = new boolean[size];
		Arrays.fill(visited, false);
		prev = new GraphNode[size];
		edges=new ArrayList<Edge>();
	}

	
	public ArrayList<Edge> getEdges() {
		return edges;
	}


	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}


	public void setSize(int size) {
		this.size = size;
		adjMatrix = new double[size][size];
		inMST = new boolean[size];
		Arrays.fill(inMST, false);
		dist = new double[size];
		Arrays.fill(dist, Integer.MAX_VALUE);
		whoTo = new GraphNode[size];
		visited = new boolean[size];
		Arrays.fill(visited, false);
		prev = new GraphNode[size];
	}

	

	public void addNode(GraphNode node) {
		nodes.add(node);
	}

	public boolean connectNode(GraphNode g1, GraphNode g2, double weight) {
		int g1Idx = -1;
		int g2Idx = -1;
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getData() == g1.getData()) {
				g1Idx = i;
				break;
			}
		}
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getData() == g2.getData()) {
				g2Idx = i;
				break;
			}
		}
		if (g1Idx < 0 || g2Idx < 0 || g1Idx >= size || g2Idx >= size) {
			System.err.println("Error Not found nodes you want to connect (adjM).");
			return false;
		}
		adjMatrix[g1Idx][g2Idx] = weight;
		if (!isDirected)
			adjMatrix[g2Idx][g1Idx] = weight;
		return true;
	}

	public ArrayList<GraphNode> getAdjacents(GraphNode node) {
		int node_Index = -1;
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getData() == node.getData()) {
				node_Index = i;
				break;
			}
		}
		if (node_Index < 0 || node_Index >= size) {
			System.out.println("Error in DFS Not found node.");
			return null;
		}
		ArrayList<GraphNode> adjNodes = new ArrayList<GraphNode>();
		for (int j = 0; j < adjMatrix.length; j++) {
			if (adjMatrix[node_Index][j] != 0) {
				adjNodes.add(nodes.get(j));
			}
		}
		return adjNodes;
	}

	public void removeEdge(GraphNode g1, GraphNode g2) {
		int g1Idx = -1;
		int g2Idx = -1;
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getData() == g1.getData()) {
				g1Idx = i;
				break;
			}
		}
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getData() == g2.getData()) {
				g2Idx = i;
				break;
			}
		}

		if (g1Idx < 0 || g2Idx < 0 || g1Idx >= size || g2Idx >= size) {
			System.out.println("Error Not found node.");
			throw new NullPointerException("Error Not found node.");
		}

		adjMatrix[g1Idx][g2Idx] = 0;
		if (!isDirected) {
			adjMatrix[g2Idx][g1Idx] = 0;
		}
	}

	public void dfs_Print(GraphNode node) {
		int node_Index = -1;
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getData() == node.getData()) {
				node_Index = i;
				break;
			}
		}
		if (node_Index < 0) {
			return;
		}
		if (node.getState().equals(State.unvisited))
			System.out.println(node.getData());
		nodes.get(node_Index).setState(State.visited);
		ArrayList<GraphNode> children = getAdjacents(node);
		// System.out.println("Children of:"+node.getData()+" = ");
		// printArray(children);
		if (children != null) {
			for (int i = 0; i < children.size(); i++) {
				if (children.get(i).getState().equals(State.unvisited)) {
					dfs_Print(children.get(i));
				}
			}
		}
	}

	
	public void printGraph(){
		for (int i = 0; i < adjMatrix.length; i++) {
			System.out.println(Arrays.toString(adjMatrix[i]));
		}
	}
	public void printNodes() {
		printArray(nodes);
	}

	private void printArray(ArrayList<GraphNode> array) {
		if (array.size() == 0) {
			System.out.println("[]");
			return;
		}
		System.out.print("[");
		for (int i = 0; i < array.size() - 1; i++) {
			System.out.print(array.get(i).getData() + ", ");
		}
		System.out.println(array.get(array.size() - 1).getData() + "]");
	}

	public int connectedComponentNumbers() {
		int counter = 0;
		this.setVisitation();
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getState().equals(State.unvisited)) {
				dfs_Print(nodes.get(i));
				counter++;
			}
		}
		return counter;
	}

	public void setVisitation() {
		for (int i = 0; i < nodes.size(); i++) {
			nodes.get(i).setState(State.unvisited);
		}
	}

	public GraphNode getRoot() {
		return root;
	}

	public void setRoot(GraphNode root) {
		this.root = root;
	}

	public boolean isDirected() {
		return isDirected;
	}

	public void setDirected(boolean isDirected) {
		this.isDirected = isDirected;
	}

	public int getSize() {
		return size;
	}

	public ArrayList<GraphNode> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<GraphNode> nodes) {
		this.nodes = nodes;
	}

	public double[][] getAdjMatrix() {
		return adjMatrix;
	}

	public void setAdjMatrix(double[][] adjMatrix) {
		this.adjMatrix = adjMatrix;
	}

	public static int getDefaultSize() {
		return DEFAULT_SIZE;
	}


	public boolean[] getVisited() {
		return visited;
	}


	public void setVisited(boolean[] visited) {
		this.visited = visited;
	}


	public GraphNode[] getPrev() {
		return prev;
	}


	public void setPrev(GraphNode[] prev) {
		this.prev = prev;
	}

	public boolean[] getInMST() {
		return inMST;
	}


	public void setInMST(boolean[] inMST) {
		this.inMST = inMST;
	}


	public double[] getDist() {
		return dist;
	}


	public void setDist(double[] dist) {
		this.dist = dist;
	}


	public GraphNode[] getWhoTo() {
		return whoTo;
	}


	public void setWhoTo(GraphNode[] whoTo) {
		this.whoTo = whoTo;
	}
	
	

}
