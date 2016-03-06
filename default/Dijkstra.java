import java.util.Arrays;

public class Dijkstra {
	double[] distances;
	int vertices;
	boolean[] visited;
	double[][] adj_mat;
	double sentinal;
	int[] parents;

	public Dijkstra(DirectedGraph graph) {
		this.vertices = graph.vertices;
		this.adj_mat = graph.adj_mat;
		sentinal = 0;
		visited = new boolean[vertices];
		distances = new double[vertices];
		parents = new int[vertices];
		Arrays.fill(parents, -1);
		Arrays.fill(distances, Double.MAX_VALUE);
	}

	public void run(int source) throws Exception {
		if (source < 0 || source > vertices)
			throw new Exception();
		distances[source] = 0;
		int current = 0;
		while (unfinished()) {
			current = minimumUnvisited();
			visited[current] = true;
			evaluate(current);
		}
	}

	private void evaluate(int node) {
		for (int i = 0; i < vertices; i++)
			if (!visited[i])
				relax(node, i);
	}

	private boolean relax(int j, int k) {
		if (adj_mat[j][k] != sentinal && distances[k] > distances[j] + adj_mat[j][k]) {
			distances[k] = distances[j] + adj_mat[j][k];
			parents[k] = j;
			return true;
		}
		return false;

	}

	private int minimumUnvisited() {
		double min = Double.MAX_VALUE;
		int ret = -1;
		for (int i = 0; i < vertices; i++)
			if (!visited[i] && distances[i] < min) {
				min = distances[i];
				ret = i;
			}
		return ret;
	}

	private boolean unfinished() {
		for (int i = 0; i < vertices; i++)
			if (!visited[i])
				return true;
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
		Dijkstra dij = new Dijkstra(graph);
		dij.run(0);
		System.out.println(Arrays.toString(dij.distances));
		System.out.println(Arrays.toString(dij.parents));

	}

}
