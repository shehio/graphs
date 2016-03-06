
public class DirectedEdge {
	int from;
	int to;
	double weight;

	public DirectedEdge(int from, int to, double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public String toString() {
		return from + "->" + to + ":" + weight;
	}

	public DirectedEdge clone() {
		return new DirectedEdge(this.from, this.to, this.weight);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
