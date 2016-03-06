import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class DirectedGraph {
	int vertices;
	int edges;
	ArrayList<DirectedEdge>[] edgesList;
	double[][] adj_mat;

	DirectedGraph(int vertices) {
		this.vertices = vertices;
		edgesList = (ArrayList<DirectedEdge>[]) new ArrayList[vertices];
	}

	public DirectedGraph(double[][] adj_mat) {
		this.adj_mat = adj_mat;
		vertices = adj_mat.length;
	}

	void addEdge(int from, int to, double weight) {
		DirectedEdge edge = new DirectedEdge(from, to, weight);
		if (edgesList[edge.from] == null)
			edgesList[edge.from] = new ArrayList<DirectedEdge>();
		int i = 0;
		for (i = 0; i < edgesList[edge.from].size(); i++) {
			if (edgesList[edge.from].get(i).to == to) {
				edgesList[edge.from].set(i, edge);
				return;
			} else if (edgesList[edge.from].get(i).to > to)
				break;
		}
		edgesList[edge.from].add(i, edge);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (ArrayList<DirectedEdge> AL : edgesList) {
			if (AL == null)
				continue;
			for (DirectedEdge edge : AL)
				sb.append(", " + edge.toString());
		}
		return sb.toString().substring(2);
	}

	public DirectedGraph clone() {
		DirectedGraph ret = new DirectedGraph(this.vertices);
		int i = 0;
		for (ArrayList<DirectedEdge> AL : edgesList) {
			if (AL == null)
				continue;
			ret.edgesList[i] = new ArrayList<DirectedEdge>();
			for (DirectedEdge edge : AL) {
				ret.edgesList[i].add(edge.clone());
			}
			i++;
		}
		return ret;

	}

	public HashSet<DirectedEdge> AllEdges() {
		HashSet<DirectedEdge> ret = new HashSet<DirectedEdge>();
		for (ArrayList<DirectedEdge> AL : edgesList)
			for (DirectedEdge edge : AL)
				ret.add(edge);
		return ret;
	}

	public static void main(String[] args) {
		DirectedGraph graph = new DirectedGraph(5);
		graph.addEdge(1, 0, 1);
		graph.addEdge(0, 3, 1);
		graph.addEdge(3, 4, 1);
		graph.addEdge(4, 0, 1);
		graph.addEdge(0, 2, 1);
		graph.addEdge(2, 1, 1);
		System.out.println(graph);
	}

	public void tranferToMatrix() {
		adj_mat = new double[vertices][vertices];
		for (ArrayList<DirectedEdge> list : edgesList) {
			if (list == null)
				continue;
			for (DirectedEdge edge : list) {
				adj_mat[edge.from][edge.to] = edge.weight;
			}
		}

	}

	public void removeEdge(int i, int j) {
		if (i < 0 || i > edgesList.length)
			return;
		for (int itr = 0; itr < edgesList[i].size(); i++)
			if (edgesList[i].get(itr).to == j) {
				edgesList[i].remove(itr);
				return;
			}
	}

}
