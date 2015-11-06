package Graph;


import java.awt.print.Printable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestGraph {
	static GraphAdjMatrix graphAdjMatrix;
	static GraphAdjList graphAdjList;
	static MST_Algorithms mst_Algorithms;
	static int graphSize=0;
	
	private static boolean isValidEdge(String line) {
		String []edge=line.split(" ");
		if(edge.length!=3)
			return false;
		for (int i = 0; i < edge.length; i++) {
			if(!isInteger(edge[i]))
				return false;
		}
		return true;
	}

	private static boolean isInteger(String num) {
		if(num=="") return false;
		for (int i = 0; i < num.length(); i++) {
			if (num.charAt(i)<'0'||num.charAt(i)>'9') {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		graphAdjMatrix=new GraphAdjMatrix();
		graphAdjList=new GraphAdjList();
		mst_Algorithms=new MST_Algorithms();
		graphAdjMatrix.setDirected(false);
		String wayToInput="";
		Scanner input=new Scanner(System.in);
		System.out.println("To read the graph from file   enter 1:");
		System.out.println("To enter the graph in console enter 2:");
		while (true) {
			wayToInput=input.nextLine();
			if(wayToInput.equals("1")){
				readFromFile();
				break;
			}else if(wayToInput.equals("2")){
				System.out.println("Enter the number of nodes:");
				String nodeNum;
				String edgeNum;
				while (true) {
					nodeNum=input.nextLine();
					if(isInteger(nodeNum)){
						break;
					}
					System.out.println("Invalid Number of nodes!!");
					System.out.println("Please enter an integer:");
				}
				int size=Integer.parseInt(nodeNum);
				graphSize=size;
				graphAdjMatrix.setSize(size);
				graphAdjList.setSize(size);
				System.out.println("Enter the number of edges:");
				while (true) {
					edgeNum=input.nextLine();
					if(isInteger(edgeNum)){
						break;
					}
					System.out.println("Invalid Number of edges!!");
					System.out.println("Please enter an integer:");
				}
				int firstNode;
				int secondNode;
				int weight;
				String line;
				for (int i = 0; i < Integer.parseInt(edgeNum); i++) {
					System.out.println("Enter Edge number("+i+"):");
					while (true) {
						line=input.nextLine();
						if(isValidEdge(line)){
							break;
						}
						System.out.println("Invalid Edge Format Please Enter Again:");
					}
					String []edgeEntry=line.split(" ");
					firstNode=Integer.parseInt(edgeEntry[0]);
					secondNode=Integer.parseInt(edgeEntry[1]);
					if(firstNode<1 || firstNode > graphSize || secondNode<1 || secondNode > graphSize){
						System.err.println("OutOfBound Node Value!!!");
						System.exit(0);
					}
					boolean exist1=false;
					boolean exist2=false;
					for (int j = 0; j < graphAdjMatrix.getNodes().size(); j++) {
						if(graphAdjMatrix.getNodes().get(j).getData()==firstNode){
							exist1=true;
						}
						System.out.println("------------------------------");
						if(graphAdjMatrix.getNodes().get(j).getData()==secondNode){
							exist2=true;
						}
						if (exist1&&exist2) {
							break;
						}
					}
					graphAdjMatrix.printNodes();
					weight=Integer.parseInt(edgeEntry[2]);
					GraphNode first_Node=new GraphNode(firstNode);
					GraphNode second_Node=new GraphNode(secondNode);
					if(!exist1){
						graphAdjMatrix.addNode(first_Node);
					}
					if(!exist2){
						graphAdjMatrix.addNode(second_Node);
					}
					graphAdjMatrix.connectNode(first_Node,second_Node,weight);
					graphAdjMatrix.getEdges().add(new Edge(first_Node, second_Node, weight));
					
					graphAdjList.connectNode(first_Node, second_Node, weight);
				}
				break;
			}else {
				System.err.println("Invalid Input!!!");
				System.out.println("Please enter again!!!");
			}	
		}
		double startTime;
		double endTime;
		double elapsedTime;
		
		System.out.println("Adjacency Matrix Representation:");
		System.out.println("Prim");
		ArrayList<Edge>mst_Edges;
		startTime=System.nanoTime();
		mst_Edges=mst_Algorithms.getMinimumSpanningTreePrim(graphAdjMatrix);
		endTime=System.nanoTime();
		elapsedTime=endTime-startTime;
		System.out.println("Time of execution = "+elapsedTime+" NS.");
		System.out.println("Time of execution = "+(elapsedTime*Math.pow(10, -6))+" MS.");
//		System.out.println("Edges from MST (prim Algorithm):");
//		Print(mst_Edges);
		System.out.println("--------------------------------------------------");
		System.out.println("Kruskal");
//		System.out.println("Edges from MST (Kruskal Algorithm):");
		startTime=System.nanoTime();
		mst_Edges=mst_Algorithms.getMinimumSpanningTreeKruskal(graphAdjMatrix);
		endTime=System.nanoTime();
		elapsedTime=endTime-startTime;
		System.out.println("Time of execution = "+elapsedTime+" NS.");
		System.out.println("Time of execution = "+(elapsedTime*Math.pow(10, -6))+" MS.");
//		Print(mst_Edges);
		
		System.out.println("==================================================");
		System.out.println("Adjacency List Representation:");
		System.out.println("Prim");
		startTime=System.nanoTime();
		mst_Edges=mst_Algorithms.getMinimumSpanningTreePrim(graphAdjList);
		endTime=System.nanoTime();
		elapsedTime=endTime-startTime;
		System.out.println("Time of execution = "+elapsedTime+" NS.");
		System.out.println("Time of execution = "+(elapsedTime*Math.pow(10, -6))+" MS.");
//		System.out.println("Edges from MST (prim Algorithm):");
//		Print(mst_Edges);
		System.out.println("--------------------------------------------------");
		System.out.println("Kruskal");
		startTime=System.nanoTime();
		mst_Edges=mst_Algorithms.getMinimumSpanningTreeKruskal(graphAdjList);
		endTime=System.nanoTime();
		elapsedTime=endTime-startTime;
		System.out.println("Time of execution = "+elapsedTime+" NS.");
		System.out.println("Time of execution = "+(elapsedTime*Math.pow(10, -6))+" MS.");
//		System.out.println("Edges from MST (Kruskal Algorithm):");
//		Print(mst_Edges);
	}

	private static void Print(ArrayList<Edge> mst_Edges) {
		for (int i = 0; i <mst_Edges.size() ; i++) {
			System.out.print("Edge("+i+") "+mst_Edges.get(i).getFrom().getData()
					+"<--->"+mst_Edges.get(i).getTo().getData()
					+"  weight = "+mst_Edges.get(i).getWeight()+"\n");
		}
	}

	private static void readFromFile() throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("input.txt"));
		String line=br.readLine();
		if(line==null || line==""){
			System.err.println("Invalid data in the file!!!");
			System.exit(0);
		}
		if(isInteger(line)){
			int size=Integer.parseInt(line);
			graphAdjMatrix.setSize(size);
			graphAdjList.setSize(size);
			graphSize=size;
		}else {
			System.err.println("Invalid data in the file!!!");
			System.exit(0);
		}
		
		line=br.readLine();
		int edgesNumber = 0;
		if(isInteger(line))
			edgesNumber=Integer.parseInt(line);
		else {
			System.err.println("Invalid data in the file!!!");
			System.exit(0);
		}
		
		for (int i = 0; i < edgesNumber; i++) {
			line=br.readLine();
//			System.err.println(line);
			if (!validLine(line)) {
				System.err.println("Invalid line format!!!");
				System.exit(0);
			}
			
			String[] split=line.split(",");
			if(!isInteger(split[0]) || !isInteger(split[1]) || !isDouble(split[2])){
				System.err.println("Invalid line format!!!");
				System.exit(0);
			}
			int firstNode=Integer.parseInt(split[0]);
			int secondNode=Integer.parseInt(split[1]);
			
			//zero based or one based
			if(firstNode<0 || firstNode > graphSize || secondNode<0 || secondNode > graphSize){
				System.err.println("OutOfBound Node Value!!!");
				System.out.println("First Node = "+firstNode);
				System.out.println("Second Node = "+secondNode);
				System.exit(0);
			}
			boolean exist1=false;
			boolean exist2=false;
			for (int j = 0; j < graphAdjMatrix.getNodes().size(); j++) {
				if(graphAdjMatrix.getNodes().get(j).getData()==firstNode){
					exist1=true;
				}
				if(graphAdjMatrix.getNodes().get(j).getData()==secondNode){
					exist2=true;
				}
				if (exist1&&exist2) {
					break;
				}
			}
//			g.printNodes();
			double weight=Double.parseDouble(split[2]);
			GraphNode first_Node=new GraphNode(firstNode);
			GraphNode second_Node=new GraphNode(secondNode);
			if(!exist1){
				graphAdjMatrix.addNode(first_Node);
			}
			if(!exist2){
				graphAdjMatrix.addNode(second_Node);
			}
			graphAdjMatrix.connectNode(first_Node,second_Node,weight);
			graphAdjMatrix.getEdges().add(new Edge(first_Node, second_Node, weight));
			
			graphAdjList.connectNode(first_Node, second_Node, weight);
		}
	}

	private static boolean isDouble(String num) {
		int dotNum = 0;
		for (int i = 0; i < num.length(); i++) {
			if((num.charAt(i)<'0' || num.charAt(i)>'9') && num.charAt(i)!='.')
				return false;
			if(num.charAt(i)=='.') dotNum++;
		}
		if(dotNum>1) return false;
		return true;
	}

	private static boolean validLine(String line) {
		if(line==null||line.length()==0) return false;
		int commaNum = 0;
		for (int i = 0; i < line.length(); i++) {
			if(line.charAt(i)==',')
				commaNum++;
			else if ((line.charAt(i)<'0' || line.charAt(i)>'9') && line.charAt(i)!='.') {
				return false;
			}
		}
		if(commaNum!=2)
			return false;
		return true;
	}
}
