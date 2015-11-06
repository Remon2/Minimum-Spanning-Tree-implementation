package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class MST_Algorithms {

	//kruskal Algorithm
	
	public ArrayList<Edge> getMinimumSpanningTreeKruskal(GraphAdjMatrix graph){
		double cost=0;
		ArrayList<Edge> MST=new ArrayList<Edge>();
		//use priority queue to sort the edges.
		PriorityQueue<Edge> minPriorityQueue=new PriorityQueue<Edge>();
		minPriorityQueue.addAll(graph.getEdges());
		DisJointSet unionFind=new DisJointSet(graph.getNodes().size()+1);
		while (!minPriorityQueue.isEmpty()) {
			Edge edge=minPriorityQueue.poll();
			if(unionFind.unionSets(edge.getFrom().getData(), edge.getTo().getData())){
				cost+=edge.getWeight();
				MST.add(edge);
			}
		}
		System.out.println("Minimum Cost (Kruskal) = "+cost);
		return MST;
	}


		//prim Algorithm  O(n^2)
		public ArrayList<Edge> getMinimumSpanningTreePrim(GraphAdjMatrix graph) {
			ArrayList<Edge> mst_Edges = new ArrayList<Edge>();
			double cost = 0;
			// Step 1 : Add first nodes of the graph in the MST.
			graph.getInMST()[0] = true;
			updateDistances(graph,graph.getNodes().get(0));
			for (int mstSize = 0; mstSize < graph.getNodes().size() - 1; mstSize++) {
				// find the node with the smallest distance in the tree
				int min = -1;
				
				//searching for the smallest edge
				for (int i = 1; i < graph.getNodes().size(); i++) {
					if (!graph.getInMST()[i]) {
						if (min == -1 || graph.getDist()[min] > graph.getDist()[i])
							min = i;
					}
				}
				mst_Edges.add(new Edge(graph.getWhoTo()[min], graph.getNodes().get(min), graph.getDist()[min]));
				graph.getInMST()[min]=true;
				cost+=graph.getDist()[min];
				updateDistances(graph,graph.getNodes().get(min));
			}
			System.out.println("Minimum Cost = "+cost);
			return mst_Edges;
		}
		
		public void updateDistances(GraphAdjMatrix graph,GraphNode node) {
			int nodeIndex = -1;
			for (int i = 0; i < graph.getNodes().size(); i++) {
				if (graph.getNodes().get(i).getData() == node.getData()) {
					nodeIndex = i;
					break;
				}
			}
			for (int i = 0; i < graph.getNodes().size(); i++) {
				// relaxation
				if (graph.getAdjMatrix()[nodeIndex][i] != 0
						&& graph.getDist()[i] > graph.getAdjMatrix()[nodeIndex][i]) {
					if (nodeIndex != i) { // not a self loop
						graph.getDist()[i] = graph.getAdjMatrix()[nodeIndex][i];
						graph.getWhoTo()[i] = node;
					}
				}
			}
		}
		
		
		//O(ElogV)
		public ArrayList<Edge> getMinimumSpanningTreePrim(GraphAdjList graph) {
			ArrayList<Edge> mst_Edges = new ArrayList<Edge>();
			double cost = 0;
			boolean visited[]=new boolean[graph.getSize()+1];
			PriorityQueue<Edge> priorityQueue=new PriorityQueue<>();
			priorityQueue.add(new Edge(new GraphNode(-1), graph.getEdges().get(0).getFrom(),0));
			ArrayList<GraphNode> adjacents=new ArrayList<GraphNode>();
			int nodesNum_In_MST=0;
			while (!priorityQueue.isEmpty()) {
				Edge edge=priorityQueue.poll();
				if(visited[edge.getTo().getData()]){
					continue;	
				}
				edge.setVisited(true);
				visited[edge.getTo().getData()]=true;
				cost+=edge.getWeight();
				if(edge.getWeight()!=0){
					mst_Edges.add(edge);
					nodesNum_In_MST++;
				}
				if(nodesNum_In_MST == graph.getSize()-1) break;
				adjacents=graph.getAdjacents(edge.getTo().getData());
			for (int i = 0; i < adjacents.size(); i++) {
				if (!visited[adjacents.get(i).getData()]) {
					priorityQueue.add(new Edge(edge.getTo()
							, adjacents.get(i),
							graph.getWeight(edge.getTo().getData(), adjacents.get(i).getData())));
				}
			}
		}
			System.out.println("Minimum Cost = "+cost);
			return mst_Edges;
		}



		private void printEdge(Edge e) {
			System.err.println(e.getTo().getData()+"<-->"+e.getFrom().getData()+"  weight = "+e.getWeight());
		}
		
		private void updateDistances(GraphAdjList graph, GraphNode node) {
			ArrayList<GraphNode> adjacents=graph.getAdjacents(node.getData());
			for (int i = 0; i < adjacents.size(); i++) {
				// relaxation
				if (graph.getDist()[i] > graph.getWeights()[node.getData()].get(i)) {
						graph.getDist()[i] = graph.getWeights()[node.getData()].get(i);
						graph.getWhoTo()[i] = node;
				}
			}
		}


		public ArrayList<Edge> getMinimumSpanningTreeKruskal(GraphAdjList graphAdjList) {
			double cost=0;
			ArrayList<Edge> MST=new ArrayList<Edge>();
			//use priority queue to sort the edges.
			PriorityQueue<Edge> minPriorityQueue=new PriorityQueue<Edge>();
			minPriorityQueue.addAll(graphAdjList.getEdges());
			DisJointSet unionFind=new DisJointSet(graphAdjList.getSize());
			while (!minPriorityQueue.isEmpty()) {
				Edge edge=minPriorityQueue.poll();
				if(unionFind.unionSets(edge.getFrom().getData(), edge.getTo().getData())){
					cost+=edge.getWeight();
					MST.add(edge);
				}
			}
			System.out.println("Minimum Cost (Kruskal) = "+cost);
			return MST;
		}

}
