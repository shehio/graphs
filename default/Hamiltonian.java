import java.util.ArrayList;

public class Hamiltonian {
	int vertices;
	double sentinal = 0;
	ArrayList<Integer> path;
	int index;
	private double[][] adj_mat;

	public Hamiltonian(DirectedGraph graph) {
		this.adj_mat = graph.adj_mat;
		vertices = adj_mat.length;
		path = new ArrayList<>();
	}

	public ArrayList<Integer> run() {
		DFS(0);
		return path;
	}

	private boolean DFS(int index) {
		if (index == vertices)
			return adj_mat[path.get(index - 1)][0] != sentinal;
		for (int i = 0; i < vertices; i++)
			if (isSafe(i)) {
				path.add(i);
				index++;
				if (DFS(index))
					return true;
				index--;
				path.remove(path.size() - 1);
			}
		return false;
	}

	private boolean isSafe(int i) {
		return (!path.contains(i) && (path.isEmpty() || adj_mat[path.get(path.size() - 1)][i] != sentinal));
	}

	public static void main(String[] args) {
		DirectedGraph graph = new DirectedGraph(5);
		graph.addEdge(0, 1, 1);
		graph.addEdge(1, 2, 1);
		graph.addEdge(1, 3, 1);
		graph.addEdge(1, 4, 1);
		graph.addEdge(2, 4, 1);
		graph.addEdge(4, 3, 1);
		graph.addEdge(3, 0, 1);

		graph.tranferToMatrix();
		Hamiltonian hmt = new Hamiltonian(graph);
		System.out.println(hmt.run());

		graph.removeEdge(4, 3);
		graph.tranferToMatrix();
		hmt = new Hamiltonian(graph);
		System.out.println(hmt.run());

	}

}
