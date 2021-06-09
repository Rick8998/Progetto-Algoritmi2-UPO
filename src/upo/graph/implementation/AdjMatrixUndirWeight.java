package upo.graph.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import upo.graph.base.*;
import upo.graph.base.VisitForest.Color;

/**
 * Implementazione mediante <strong>matrice di adiacenza</strong> di un grafo <strong>non orientato pesato</strong>.
 * 
 * @author Andrea Tragno 20025491
 *
 */
public class AdjMatrixUndirWeight implements WeightedGraph{
	
	int nVertex;
	double [][] matrix;
	
	public AdjMatrixUndirWeight(int v) {
		this.nVertex = v;
		matrix = new double [nVertex][nVertex];
	}

	@Override
	public int addVertex() {

		int newVertexIndex = matrix.length;
		
		double supMatrix[][] = new double[matrix.length + 1][matrix.length + 1];
		
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				
				supMatrix[i][j] = matrix[i][j];
				
			}
		}
		
		nVertex++;
		matrix = new double[nVertex][nVertex];
		
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				matrix[i][j] = supMatrix[i][j];
			}
		}
		
		return newVertexIndex;
	}

	@Override
	public boolean containsVertex(int index) {
		
		for(int i = 0; i < matrix.length; i++) {
				if(i == index) 
					return true;
		}
		return false;
	}

	@Override
	public void removeVertex(int index) throws NoSuchElementException {

		double supMatrix[][] = new double[matrix.length - 1][matrix.length - 1];
		
		if(index >= 0 && index < matrix.length) {
		
			for(int i = 0; i < matrix.length; i ++) {
				for(int j = 0; j < matrix.length; j ++) {
					if(i != index && j != index) {
						
						if((i > index) && (j > index)) {
							supMatrix[i-1][j-1] = matrix[i][j];
						}
						else if(i > index) {
							supMatrix[i-1][j] = matrix[i][j];
						}
						else if(j > index) {
							supMatrix[i][j-1] = matrix[i][j];
						}
						else
							supMatrix[i][j] = matrix[i][j];
					}
				}
			}
			
			nVertex--;
			matrix = new double[nVertex][nVertex];
			
			for(int i = 0; i < matrix.length; i++) {
				for(int j = 0; j < matrix.length; j++) {
					matrix[i][j] = supMatrix[i][j];
				}
			}
			
		}else
			throw new NoSuchElementException("Vertice non appartenente al grafo");
	}

	@Override
	public void addEdge(int sourceVertexIndex, int targetVertexIndex) throws IllegalArgumentException {
		
		if(sourceVertexIndex > matrix.length || sourceVertexIndex < 0) 
			throw new IllegalArgumentException("Vertice sorgente (primo argomento) in input non appartenente al grafo");
		
		if(targetVertexIndex > matrix.length || targetVertexIndex < 0) 
			throw new IllegalArgumentException("Vertice target (secondo argomento) in input non appartenente al grafo");
		
		if(containsEdge(sourceVertexIndex, targetVertexIndex) == false) {
			this.matrix[sourceVertexIndex][targetVertexIndex] = defaultEdgeWeight;	
			this.matrix[targetVertexIndex][sourceVertexIndex] = defaultEdgeWeight;
		}
	}

	@Override
	public boolean containsEdge(int sourceVertexIndex, int targetVertexIndex) throws IllegalArgumentException {
		
		if(sourceVertexIndex > matrix.length || sourceVertexIndex < 0) 
			throw new IllegalArgumentException("Vertice sorgente (primo argomento) in input non appartenente al grafo");
		
		if(targetVertexIndex > matrix.length || targetVertexIndex < 0) 
			throw new IllegalArgumentException("Vertice target (secondo argomento) in input non appartenente al grafo");
			
		if(this.matrix[sourceVertexIndex][targetVertexIndex] >= defaultEdgeWeight)
			return true;
		
		return false;
	}

	@Override
	public void removeEdge(int sourceVertexIndex, int targetVertexIndex)
			throws IllegalArgumentException, NoSuchElementException {
		
		if(sourceVertexIndex > matrix.length || sourceVertexIndex < 0) 
			throw new IllegalArgumentException("Vertice sorgente (primo argomento) in input non appartenente al grafo");
		
		if(targetVertexIndex > matrix.length || targetVertexIndex < 0) 
			throw new IllegalArgumentException("Vertice target (secondo argomento) in input non appartenente al grafo");
		
		if(containsEdge(sourceVertexIndex, targetVertexIndex) == false)
			throw new NoSuchElementException("Arco non appartenente al grafo");	
		else {
			setEdgeWeight(sourceVertexIndex, targetVertexIndex, -1);
			setEdgeWeight(targetVertexIndex, sourceVertexIndex, -1);
		}
			
	}

	@Override
	public Set<Integer> getAdjacent(int vertexIndex) throws NoSuchElementException {
		
		if(containsVertex(vertexIndex) == false) 
			throw new NoSuchElementException("Vertice non appartenente al grafo");
		
		Set<Integer> setAdjVertex = new HashSet<Integer>();
		
		for(int i = 0; i < matrix.length; i++) {
			if(i == vertexIndex) {
				for(int j = 0; j < matrix.length; j++) {
					
					if(matrix[i][j] >= defaultEdgeWeight) {
						setAdjVertex.add(j);
					}
				}
			}
		}
		return setAdjVertex;
	}

	@Override
	public boolean isAdjacent(int targetVertexIndex, int sourceVertexIndex) throws IllegalArgumentException {
		
		if(sourceVertexIndex > matrix.length || sourceVertexIndex < 0) 
			throw new IllegalArgumentException("Vertice sorgente (primo argomento) in input non appartenente al grafo");
		
		if(targetVertexIndex > matrix.length || targetVertexIndex < 0) 
			throw new IllegalArgumentException("Vertice target (secondo argomento) in input non appartenente al grafo");
		
		if(containsEdge(sourceVertexIndex, targetVertexIndex) == false)
			return false;
		
		return true;
	}

	@Override
	public int size() {
		return this.nVertex;
	}

	@Override
	public boolean isDirected() {
		return false;
	}

	@Override
	public boolean isCyclic() {
		
		VisitForest graph = new VisitForest(this, null);
		for(int i = 0; i < this.size(); i++) {
			if(graph.getColor(i) == Color.WHITE && genericVisit(graph, i)) 
				return true;
		}
		return false;
	}
	
	private boolean genericVisit(VisitForest graph, int u) {
		
		graph.setColor(u, Color.GRAY);
		Object[] ary = getAdjacent(u).toArray();
		for(int i = 0; i < this.getAdjacent(u).size(); i++) {
			if(graph.getColor((Integer) ary[i]) == Color.WHITE) {
				graph.setParent((Integer) ary[i], u);
				if(genericVisit(graph, (Integer) ary[i])) {
					return true;
				}
			}
			else if(graph.getColor((Integer) ary[i]) == Color.GRAY) {
				return true;
			}
		}
		graph.setColor(u, Color.BLACK);
		return false;
	}

	@Override
	public boolean isDAG() {
		return false;
	}

	@Override
	public VisitForest getBFSTree(int startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
		throw new UnsupportedOperationException("Operazione non prevista per il tipo di grafo");
	}

	@Override
	public VisitForest getDFSTree(int startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
		throw new UnsupportedOperationException("Operazione non prevista per il tipo di grafo");
	}

	@Override
	public VisitForest getDFSTOTForest(int startingVertex)
			throws UnsupportedOperationException, IllegalArgumentException {
		throw new UnsupportedOperationException("Operazione non prevista per il tipo di grafo");
	}

	@Override
	public VisitForest getDFSTOTForest(int[] vertexOrdering)
			throws UnsupportedOperationException, IllegalArgumentException {
		throw new UnsupportedOperationException("Operazione non prevista per il tipo di grafo");
	}

	@Override
	public int[] topologicalSort() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Operazione non prevista per il tipo di grafo");
	}

	@Override
	public Set<Set<Integer>> stronglyConnectedComponents() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Operazione non prevista per il tipo di grafo");
	}

	@Override
	public Set<Set<Integer>> connectedComponents() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Operazione non prevista per il tipo di grafo");
	}

	@Override
	public double getEdgeWeight(int sourceVertexIndex, int targetVertexIndex)
			throws IllegalArgumentException, NoSuchElementException {
		
		if(sourceVertexIndex > matrix.length || sourceVertexIndex < 0) 
			throw new IllegalArgumentException("Vertice sorgente (primo argomento) in input non appartenente al grafo");
		
		if(targetVertexIndex > matrix.length || targetVertexIndex < 0) 
			throw new IllegalArgumentException("Vertice target (secondo argomento) in input non appartenente al grafo");
		
		if(containsEdge(sourceVertexIndex, targetVertexIndex) == false)
			throw new NoSuchElementException("Arco non appartenente al grafo");	
		
		return this.matrix[sourceVertexIndex][targetVertexIndex];
	}

	@Override
	public void setEdgeWeight(int sourceVertexIndex, int targetVertexIndex, double weight)
			throws IllegalArgumentException, NoSuchElementException {
		
		if(sourceVertexIndex > matrix.length || sourceVertexIndex < 0) 
			throw new IllegalArgumentException("Vertice sorgente (primo argomento) in input non appartenente al grafo");
		
		if(targetVertexIndex > matrix.length || targetVertexIndex < 0) 
			throw new IllegalArgumentException("Vertice target (secondo argomento) in input non appartenente al grafo");
		
		if(containsEdge(sourceVertexIndex, targetVertexIndex) == false)
			throw new NoSuchElementException("Arco non appartenente al grafo");	
		
		this.matrix[sourceVertexIndex][targetVertexIndex] = weight;
		
	}

	@Override
	public WeightedGraph getBellmanFordShortestPaths(int startingVertex)
			throws UnsupportedOperationException, IllegalArgumentException {
		throw new UnsupportedOperationException("Operazione non prevista per il tipo di grafo");
	}

	@Override
	public WeightedGraph getDijkstraShortestPaths(int startingVertex)
			throws UnsupportedOperationException, IllegalArgumentException {
		throw new UnsupportedOperationException("Operazione non prevista per il tipo di grafo");
	}

	@Override
	public WeightedGraph getPrimMST(int startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
		throw new UnsupportedOperationException("Operazione non prevista per il tipo di grafo");
	}

	@Override
	public WeightedGraph getKruskalMST() throws UnsupportedOperationException {
		
		// A <- insieme di archi vuoto
		WeightedForestKruskal A =  new WeightedForestKruskal(nVertex);
			
		// creo una sequenza in cui ordinerò gli archi per peso
		ArrayList<UndirEdgeKruskal> S = new ArrayList<>();
		
		// scorro la matrice di adiacenza del grafo
		for(int i = 0; i < nVertex; i++) {
			for(int j = 0; j < nVertex; j++) {
				//verifico che l'arco i-j faccia parte del grafo
				if(this.containsEdge(i, j)) {
					UndirEdgeKruskal edge = new UndirEdgeKruskal(i, j, this.getEdgeWeight(i, j));
					S.add(edge);
				}
			}
		}
		// ordina gli archi in una sequenza S in ordine crescente di peso
		mySort(S);
		
		// crea UnionFind contenente i vertici di G come insiemi iniziali
		UnionFindKruskal unionFind = new UnionFindKruskal();
		for (int i = 0; i < nVertex; i++ ) {
			unionFind.makeSet(i);
		}
		
		for(int i = 0; i < S.size(); i++) {
			if(unionFind.find(S.get(i).getVertex1()) != unionFind.find(S.get(i).getVertex2())) {
				A.addEdge(S.get(i).getVertex1(), S.get(i).getVertex2(), S.get(i).getWeight());
				unionFind.union(S.get(i).getVertex1(), S.get(i).getVertex2());
			}
		}
		
		return A.forest;
	}
	
	private void mySort(ArrayList<UndirEdgeKruskal> list) {
		boolean sorted = true;
		int v1, v2;
		double w;
		while(sorted) {
			sorted = false;
			for (int i = 0; i < list.size()-1; i++) {
				if(list.get(i).compareTo(list.get(i+1)) > 0) {
					v1 = list.get(i).getVertex1();
					v2 = list.get(i).getVertex2();
					w = list.get(i).getWeight();
					list.get(i).setVertex1(list.get(i+1).getVertex1());
					list.get(i).setVertex2(list.get(i+1).getVertex2());
					list.get(i).setWeight(list.get(i+1).getWeight());
					list.get(i+1).setVertex1(v1);
					list.get(i+1).setVertex2(v2);
					list.get(i+1).setWeight(w);
					sorted = true;
				}		
			}
		}
	}

	@Override
	public WeightedGraph getFloydWarshallShortestPaths() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Operazione non prevista per il tipo di grafo");
	}

}
