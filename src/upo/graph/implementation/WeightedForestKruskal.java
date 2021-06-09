package upo.graph.implementation;

/**
 * Implementazione di un WeightedGraph da modellare secondo l'algoritmo di kruskal.
 * 
 * @author Andrea Tragno 20025491
 *
 */

public class WeightedForestKruskal {

	protected AdjMatrixUndirWeight forest;
	
	
	public WeightedForestKruskal(int nVertex) {
		
		forest = new AdjMatrixUndirWeight(nVertex);
		for(int i = 0; i < nVertex; i++) {
			forest.addVertex();
		}
	}
	
	public void addEdge(int sourceVertex, int targetVertex, double weight) {
		try {
			forest.addEdge(sourceVertex, targetVertex);
		}
		catch(Exception e) {
			throw new IllegalArgumentException("Non puoi aggiungere questo arco", e);
		}
		forest.setEdgeWeight(sourceVertex, targetVertex, weight);
	}
}
