package Graph;
public class GraphNode {
	private int data;
	private State state;

	public GraphNode(int d) {
		this.data=d;
		state=State.unvisited;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}

enum State {
	visited, unvisited, processed
}
