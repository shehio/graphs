import java.util.Arrays;

public class BelmanFord {

	double[] distances;
	int vertices;
	double[][] adj_mat;
	double sentinal;
	int[] parents;

	public BelmanFord(DirectedGraph graph) {
		vertices = graph.vertices;
		distances = new double[vertices];
		parents = new int[vertices];
		Arrays.fill(parents, -1);
		Arrays.fill(distances, Double.MAX_VALUE);
		sentinal = 0;
		adj_mat = graph.adj_mat;
	}

	public boolean run(int source, boolean shortest) throws Exception {
		if (source < 0 || source > vertices)
			throw new Exception();
		distances[source] = 0;
		for (int i = 0; i < vertices; i++)
			for (int j = 0; j < vertices; j++)
				for (int k = 0; k < vertices; k++)
					if (adj_mat[j][k] != sentinal)
						if (shortest)
							relax(j, k);
						else
							antiRelax(j, k);
		for (int j = 0; j < vertices; j++)
			for (int k = 0; k < vertices; k++)
				if (adj_mat[j][k] != sentinal)
					if (shortest)
						if (relax(j, k))
							return false;
						else if (antiRelax(j, k))
							return false;
		return true;

	}

	private boolean antiRelax(int j, int k) {
		if (adj_mat[j][k] != sentinal && distances[j] < distances[k] + adj_mat[j][k]) {
			distances[j] = distances[k] + adj_mat[j][k];
			return true;
		}
		return false;

	}

	private boolean relax(int j, int k) {
		if (adj_mat[j][k] != sentinal && distances[k] > distances[j] + adj_mat[j][k]) {
			distances[k] = distances[j] + adj_mat[j][k];
			parents[k] = j;
			return true;
		}
		return false;

	}

	public static void main(String[] args) throws Exception {

		// example from: https://en.wikipedia.org/wiki/Shortest_path_problem
		DirectedGraph graph = new DirectedGraph(6);
		graph.addEdge(0, 1, 4);
		graph.addEdge(0, 2, 2);
		graph.addEdge(1, 2, 5);
		graph.addEdge(1, 3, 10);
		graph.addEdge(2, 4, 3);
		graph.addEdge(4, 3, 4);
		graph.addEdge(3, 5, 11);
		System.out.println(graph);

		graph.tranferToMatrix();
		BelmanFord bel = new BelmanFord(graph);
		bel.run(0, true);
		System.out.println(Arrays.toString(bel.distances));
		System.out.println(Arrays.toString(bel.parents));
	}

}
