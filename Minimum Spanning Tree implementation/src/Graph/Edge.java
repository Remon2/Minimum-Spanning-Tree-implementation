package Graph;

public class Edge implements Comparable<Edge> {
	private GraphNode from;
	private GraphNode to;
	private double weight;
	private boolean visited;
	
	public Edge(GraphNode from, GraphNode to,double weight) {
		this.from=from;
		this.to=to;
		this.weight=weight;
		this.setVisited(false);
	}

	public GraphNode getFrom() {
		return from;
	}

	public void setFrom(GraphNode from) {
		this.from = from;
	}

	public GraphNode getTo() {
		return to;
	}

	public void setTo(GraphNode to) {
		this.to = to;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		if(this.weight > o.weight)
			return 1;
		else if (this.weight < o.weight)
			return -1;
		return 0;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
}
