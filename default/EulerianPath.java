import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class EulerianPath {

	int vertices;
	Stack<Integer> stack;
	DirectedGraph graph;
	ArrayList<Integer> path;
	int[] inNodes;
	int[] outNodes;

	public EulerianPath(DirectedGraph graph) {
		this.graph = graph.clone();
		vertices = graph.vertices;
		path = new ArrayList<Integer>();
		inNodes = new int[vertices];
		outNodes = new int[vertices];
		stack = new Stack<Integer>();
	}

	private void count() {
		for (ArrayList<DirectedEdge> AL : graph.edgesList)
			for (DirectedEdge edge : AL) {
				outNodes[edge.from]++;
				inNodes[edge.to]++;
			}
//		System.out.println(Arrays.toString(inNodes));
//		System.out.println(Arrays.toString(outNodes));
	}

	private int source() {
		int no = 0;
		int chosen = 0;
		boolean largerOut = false;
		boolean largerIn = false;
		for (int i = 0; i < vertices; i++) {
			if (inNodes[i] == outNodes[i])
				continue;
			else
				no++;
			if (no > 2)
				return -1;
			if (outNodes[i] > inNodes[i] && !largerOut) {
				chosen = i;
				largerOut = true;
			} else
				return -1;
			if (inNodes[i] > outNodes[i] && !largerIn) {
				largerIn = true;
			} else
				return -1;
		}
		return chosen; // or 0?
	}

	public ArrayList<Integer> run() {
		count();
		int current = source();
		if(current == -1)
			return path;
		stack.add(current);
		int old = 0;
		while (!stack.isEmpty()) {
			if (!graph.edgesList[current].isEmpty()) {
//				System.out.println("current: " + current + ", and graph: "  + graph);
				old = current;
				current = graph.edgesList[current].get(0).to;
				graph.edgesList[old].remove(0);
				stack.add(old);
			} else {
				path.add(stack.pop());
				current = (stack.isEmpty()) ? 0 : stack.peek();
			}
		}
		path.remove(path.size()-1);
		Collections.reverse(path);
		return path;
	}


	private void swap(int i, int j) {
		int temp = path.get(i);
		path.set(i, path.get(j));
		path.set(j, temp);

	}

	public static void main(String[] args) {
		
		// example from: http://www.geeksforgeeks.org/euler-circuit-directed-graph/
		DirectedGraph graph = new DirectedGraph(5);
		graph.addEdge(1, 0, 1);
		graph.addEdge(0, 3, 1);
		graph.addEdge(3, 4, 1);
		graph.addEdge(4, 0, 1);
		graph.addEdge(0, 2, 1);
		graph.addEdge(2, 1, 1);
		System.out.println(graph);

		EulerianPath instance = new EulerianPath(graph);
		System.out.println(instance.run());
	}

}
