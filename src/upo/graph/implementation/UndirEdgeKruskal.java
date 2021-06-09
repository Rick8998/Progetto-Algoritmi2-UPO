package upo.graph.implementation;

/**
 * Implementazione di un oggetto arco per Kruskal.
 * 
 * @author Andrea Tragno 20025491
 *
 */

public class UndirEdgeKruskal implements Comparable<UndirEdgeKruskal>{
	public int vertex1, vertex2;
	public double weight;
	
	public UndirEdgeKruskal(int v1, int v2, double w) {
		vertex1 = v1;
		vertex2 = v2;
		weight = w;
	}

	public int getVertex1() {
		return vertex1;
	}

	public void setVertex1(int vertex1) {
		this.vertex1 = vertex1;
	}

	public int getVertex2() {
		return vertex2;
	}

	public void setVertex2(int vertex2) {
		this.vertex2 = vertex2;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(UndirEdgeKruskal o) {
		if((weight - o.weight) > 0)
			return 1;
		else if((weight - o.weight) < 0)
			return -1;
		else
			return 0;
	}
}
