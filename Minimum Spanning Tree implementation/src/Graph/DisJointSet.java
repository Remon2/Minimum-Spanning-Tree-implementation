package Graph;

import java.util.Arrays;


public class DisJointSet {
	private int[] parent;
	private int[]rank;
	private int forests;

	public DisJointSet(int nodeCount) {
		parent=new int[nodeCount+1];
		rank=new int[nodeCount+1];
		forests=nodeCount;
		Arrays.fill(rank, 1);
		for (int i = 0; i < parent.length; i++) {
			parent[i]=i;
		}
	}
	
	public void link(int x,int y){
		if(rank[x]>rank[y]){
//			swap x and y
			int temp=x;
			x=y;
			y=temp;
		}
		parent[x]=y;
		if(rank[x]==rank[y])
			rank[y]++;
	}
	
	public int find(int x){
//		System.err.println("In find method");
//		System.err.println("x = "+x);
//		System.err.println("length = "+parent.length);
//		System.err.println("parent[x] = "+parent[x]);
//		if(x > parent.length) return -1;
//		System.err.println("x ========= "+x);
		if(x==parent[x])
			return x;
		return parent[x]=find(parent[x]);
	}
	
	public boolean unionSets(int x,int y){
		x=find(x);
		y=find(y);
		if(x!=y && x!=-1 && y!=-1){
			link(x, y);
			forests--;  //after merging two forests
		}
		return x!=y;
	}
}
