package Graph;

import java.awt.Adjustable;
import java.awt.PrintGraphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class GraphAdjList {
	private ArrayList<GraphNode>[] graphList;
	private ArrayList<Double>[] weights;
	private ArrayList<Edge> edges;
	private int size = 0;
	private boolean[] inMST;
	private double[] dist;
	private GraphNode[] whoTo;
	private ArrayList<GraphNode> nodes;
	private boolean visited[];

	public GraphAdjList() {
		graphList = new ArrayList[size + 1];
		weights = new ArrayList[size + 1];
		nodes = new ArrayList<>();
		inMST = new boolean[size + 1];
		Arrays.fill(inMST, false);
		dist = new double[size + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		whoTo = new GraphNode[size + 1];
		visited = new boolean[size + 1];
		Arrays.fill(visited, false);
		for (int i = 0; i < size + 1; i++) {
			graphList[i] = new ArrayList<GraphNode>();
			weights[i] = new ArrayList<Double>();
		}
		edges = new ArrayList<Edge>();
	}

	public int getSize() {
		return size;
	}

	public boolean connectNode(GraphNode node1, GraphNode node2, double weight) {
		if (node1.getData() > size || node1.getData() < 0
				|| node2.getData() > size || node2.getData() < 0) {
			System.err
					.println("Error Not found nodes you want to connect (adjL).");
			System.err.println("g1.getData()  = " + node1);
			System.err.println("g2.getData()  = " + node2);
			return false;
		}

		boolean added1 = false;
		boolean added2 = false;

		// System.out.println("Now we want to connect "+node1.getData()+" and "+node2.getData());

		if (!graphList[node1.getData()].contains(node2)) {
			graphList[node1.getData()].add(node2);
			weights[node1.getData()].add(weight);
			// System.err.println(node1.getData()+" is connected to "+node2.getData());
			added1 = true;
		} else {
			// System.err.println(node1+" and "+node2+" is already connected.");
			// System.out.println(graphList[node1.getData()].toString());
		}

		// because it is undirected graph
		if (!graphList[node2.getData()].contains(node1)) {
			graphList[node2.getData()].add(node1);
			weights[node2.getData()].add(weight);
			// System.err.println(node2.getData()+" is connected to "+node1.getData());
			added1 = true;
		} else {
			// System.err.println(node2+" and "+node1+" is already connected.");
			// System.out.println(graphList[node2.getData()].toString());
		}

		if (added1 || added2) {
			edges.add(new Edge(node1, node2, weight));
			edges.add(new Edge(node2, node1, weight));
		}
		return true;
	}

	public ArrayList<GraphNode> getAdjacents(int node) {
		if (node < 0 || node > size) {
			System.err
					.println("Error Not found the node that you want to get adjacents (adjL).");
			return null;
		}
		return graphList[node];
	}

	public void setSize(int size) {
		this.size = size;
		graphList = new ArrayList[size + 1];
		weights = new ArrayList[size + 1];
		for (int i = 0; i < size + 1; i++) {
			graphList[i] = new ArrayList<GraphNode>();
			weights[i] = new ArrayList<Double>();
		}
		inMST = new boolean[size + 1];
		Arrays.fill(inMST, false);
		dist = new double[size + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		whoTo = new GraphNode[size + 1];
		visited = new boolean[size + 1];
		Arrays.fill(visited, false);
	}

	public Edge getMinimumEdge() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.addAll(edges);
		return pq.peek();
	}

	public double getWeight(int node1, int node2) {
		int index=-1;
		for (int i = 0; i < graphList[node1].size(); i++) {
			if(graphList[node1].get(i).getData()==node2){
				index=i;
				break;
			}
		}
		if(index!=-1)
			return weights[node1].get(index);
		return 0;
	}

	public void printWeights(){
		for (int i = 0; i < weights.length; i++) {
			System.out.println(i+")"+ weights[i].toString());
		}
	}
	
	public void printGraph() {
		for (int i = 0; i < graphList.length; i++) {
			System.out.println("Adj[" + i + "]");
			for (int j = 0; j < graphList[i].size(); j++) {
				System.out.print(graphList[i].get(j).getData() + "  ");
			}
			System.out.println();
		}
	}

	public ArrayList<Double>[] getWeights() {
		return weights;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public ArrayList<GraphNode>[] getGraphList() {
		return graphList;
	}

	public void setGraphList(ArrayList<GraphNode>[] graphList) {
		this.graphList = graphList;
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

	public ArrayList<GraphNode> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<GraphNode> nodes) {
		this.nodes = nodes;
	}

	public boolean[] getVisited() {
		return visited;
	}

	public void setVisited(boolean[] visited) {
		this.visited = visited;
	}

	public void setWeights(ArrayList<Double>[] weights) {
		this.weights = weights;
	}

	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}

}
