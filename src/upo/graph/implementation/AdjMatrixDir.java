package upo.graph.implementation;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import upo.graph.base.Graph;
import upo.graph.base.VisitForest;
import upo.graph.base.VisitForest.Color;
import upo.graph.base.VisitForest.VisitType;

/**
 * Implementazione mediante <strong>matrice di adiacenza</strong> di un grafo <strong>orientato non pesato</strong>.
 * 
 * @author Riccardo Cecci 20023915
 *
 */
public class AdjMatrixDir implements Graph{
	
	int size;
	int[][] matrix;
	int time = 0;
	int t;
	
	public AdjMatrixDir(int size) {
		
		this.size = size;
		matrix = new int[size][size];
	}
	
	@Override
	public int addVertex() {
		
		int newIndex = matrix.length;
		
		int supMatrix[][] = new int[matrix.length + 1][matrix.length + 1];
		
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				
				supMatrix[i][j] = matrix[i][j];
				
			}
		}
		
		size++;
		matrix = new int[size][size];
		
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				matrix[i][j] = supMatrix[i][j];
			}
		}
		
		return newIndex;
	}

	@Override
	public boolean containsVertex(int index) {
		
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				
				if(i == index && j == index) {
					return true;
				}
				
			}
		}
		
		return false;
	}

	@Override
	public void removeVertex(int index) throws NoSuchElementException {
		
		
		if(index >= 0 && index < matrix.length) {
			
			int supMatrix[][] = new int[matrix.length - 1][matrix.length - 1];
			
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
			
			size--;
			matrix = new int[size][size];
			
			for(int i = 0; i < matrix.length; i++) {
				for(int j = 0; j < matrix.length; j++) {
					matrix[i][j] = supMatrix[i][j];
				}
			}
			
		}else
			throw new NoSuchElementException("Elemento non presente");
		
	}

	@Override
	public void addEdge(int sourceVertexIndex, int targetVertexIndex) throws IllegalArgumentException {
		if(sourceVertexIndex >= matrix.length || sourceVertexIndex < 0) 
			throw new IllegalArgumentException("Argomento sorgente (primo argomento) in input non valido");
		
		if(targetVertexIndex >= matrix.length || targetVertexIndex < 0) 
			throw new IllegalArgumentException("Argomento target (secondo argomento) in input non valido");
		
		if(containsEdge(sourceVertexIndex, targetVertexIndex) == false) {
			
			matrix[sourceVertexIndex][targetVertexIndex] = 1;
			
		}
	}

	@Override
	public boolean containsEdge(int sourceVertexIndex, int targetVertexIndex) throws IllegalArgumentException {
		
		if(sourceVertexIndex >= matrix.length || sourceVertexIndex < 0) 
			throw new IllegalArgumentException("Argomento sorgente (primo argomento) in input non valido");
		
		if(targetVertexIndex >= matrix.length || targetVertexIndex < 0) 
			throw new IllegalArgumentException("Argomento target (secondo argomento) in input non valido");
			
			
		if(matrix[sourceVertexIndex][targetVertexIndex] == 1) {
			return true;
		}
			
		return false;
	}

	@Override
	public void removeEdge(int sourceVertexIndex, int targetVertexIndex) throws IllegalArgumentException, NoSuchElementException {
		if(sourceVertexIndex >= matrix.length || sourceVertexIndex < 0) 
			throw new IllegalArgumentException("Argomento sorgente (primo argomento) in input non valido");
		
		if(targetVertexIndex >= matrix.length || targetVertexIndex < 0) 
			throw new IllegalArgumentException("Argomento target (secondo argomento) in input non valido");
		
		if(containsEdge(sourceVertexIndex, targetVertexIndex) == true) {
			matrix[sourceVertexIndex][targetVertexIndex] = 0;
		}
		
	}

	@Override
	public Set<Integer> getAdjacent(int vertexIndex) throws NoSuchElementException {
		if(containsVertex(vertexIndex) == false) 
			throw new NoSuchElementException("Vertice non presente");
		
		Set<Integer> setAdjVertex = new HashSet<Integer>();
		
		for(int i = 0; i < matrix.length; i++) {
			if(i == vertexIndex) {
				for(int j = 0; j < matrix.length; j++) {
					
					if(matrix[i][j] == 1) {
						setAdjVertex.add(j);
					}
				}
			}
		}
		
		return setAdjVertex;
	}

	@Override
	public boolean isAdjacent(int targetVertexIndex, int sourceVertexIndex) throws IllegalArgumentException {
		if(sourceVertexIndex >= matrix.length || sourceVertexIndex < 0) 
			throw new IllegalArgumentException("Argomento sorgente (primo argomento) in input non valido");
		
		if(targetVertexIndex >= matrix.length || targetVertexIndex < 0) 
			throw new IllegalArgumentException("Argomento target (secondo argomento) in input non valido");
		
		if(containsEdge(sourceVertexIndex, targetVertexIndex) == true) {
			
			return true;
		}
		
		return false;
	}

	@Override
	public int size() {
		return matrix.length;
	}

	@Override
	public boolean isDirected() {
		return true;
	}

	@Override
	public boolean isCyclic() {
		VisitForest graph = new VisitForest(this, null);
		for(int i = 0; i < this.size(); i++) {
			if(graph.getColor(i) == Color.WHITE && VisitaRicCyclic(graph, i)) {
				return true;
			}
		}
		return false;
	}

	private boolean VisitaRicCyclic(VisitForest graph, int u) {
		graph.setColor(u, Color.GRAY);
		Object[] ary = getAdjacent(u).toArray();
		for(int i = 0; i < this.getAdjacent(u).size(); i++) {
			if(graph.getColor((Integer) ary[i]) == Color.WHITE) {
				graph.setParent((Integer) ary[i], u);
				if(VisitaRicCyclic(graph, (Integer) ary[i])) {
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
		
		if(!isCyclic() && isDirected()) 
			return true;
		
		return false;
	}

	@Override
	public VisitForest getBFSTree(int startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
		if(startingVertex >= matrix.length || startingVertex < 0) 
			throw new IllegalArgumentException("Argomento sorgente (primo argomento) in input non valido");
		
		VisitForest visitBFS = new VisitForest(this, VisitType.BFS);
		
		BFSVisit(visitBFS, startingVertex);
		
		return visitBFS;
	}

	private void BFSVisit(VisitForest visitBFS, int startingVertex) {
		
		Queue<Integer> queue = new LinkedList<Integer>();
		visitBFS.setColor(startingVertex, Color.GRAY);
		visitBFS.setDistance(startingVertex, 0);
		queue.add(startingVertex);
		System.out.println(queue.toString());
		while(!queue.isEmpty()) {
			int u = queue.peek();
			Object[] ary = getAdjacent(u).toArray();
			System.out.println(u);
			for(int i = 0; i < this.getAdjacent(u).size(); i++) {
				if(visitBFS.getColor((Integer)ary[i]) == Color.WHITE) {
					visitBFS.setColor((Integer)ary[i], Color.GRAY);
					visitBFS.setParent((Integer)ary[i], u);
					visitBFS.setDistance((Integer)ary[i], visitBFS.getDistance(u) + 1);
					queue.add((Integer)ary[i]);
				}
			}
			visitBFS.setColor(u, Color.BLACK);
			queue.remove(u);
		}
		System.out.println(queue.toString());
		
	}

	@Override
	public VisitForest getDFSTree(int startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
		if(startingVertex >= matrix.length || startingVertex < 0) 
			throw new IllegalArgumentException("Argomento sorgente (primo argomento) in input non valido");
		
		VisitForest visitDFS = new VisitForest(this, VisitType.DFS);
		
		DFSVisitRic(visitDFS, startingVertex);
		
		return visitDFS;
	}
	
	
	private void DFSVisitRic(VisitForest visitDFS, int startingVertex) {
		
		visitDFS.setColor(startingVertex, Color.GRAY);
		visitDFS.setStartTime(startingVertex, time);
		System.out.println("Apertura: d[" + startingVertex + "] = "+time);
		time++;
		
		Object[] ary = getAdjacent(startingVertex).toArray();
		for(int i = 0; i< getAdjacent(startingVertex).size(); i++) {
			if(visitDFS.getColor((Integer)ary[i]) == Color.WHITE) {
				visitDFS.setParent((Integer)ary[i], startingVertex);
				DFSVisitRic(visitDFS, (Integer)ary[i]);
			}
		}
		
		visitDFS.setColor(startingVertex, Color.BLACK);
		System.out.println("Chiusura: f[" + startingVertex + "] = "+time);
		visitDFS.setEndTime(startingVertex, time);
		time++;
		
	}

	@Override
	public VisitForest getDFSTOTForest(int startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
		if(startingVertex >= matrix.length || startingVertex < 0) 
			throw new IllegalArgumentException("Argomento sorgente (primo argomento) in input non valido");
		
		
		VisitForest forest = new VisitForest(this, VisitType.DFS_TOT);
		DFSVisitRic(forest, startingVertex);
		for(int i = 0; i < matrix.length; i++) {
			 if(forest.getColor(i) == Color.WHITE) {
				DFSVisitRic(forest, i);
			}
		}
		
		return forest;
	}
	
	@Override
	public VisitForest getDFSTOTForest(int[] vertexOrdering)throws UnsupportedOperationException, IllegalArgumentException {
		
	
		// TODO su tutto il vertexOrdering per la illegalArgument
		VisitForest forest = new VisitForest(this, VisitType.DFS_TOT);
		for(int i = 0; i < vertexOrdering.length; i++) {
			if(forest.getColor(vertexOrdering[i]) == Color.WHITE) {
				DFSVisitRic(forest, vertexOrdering[i]);
			}
		}
		return forest;
	}

	
	
	
	@Override
	public int[] topologicalSort() throws UnsupportedOperationException {
		if(isDAG()) {
		
			int[] ord = new int[matrix.length];
			VisitForest forest = new VisitForest(this, VisitType.DFS_TOT);
			t = matrix.length - 1;
			for(int i = 0; i < matrix.length; i++) {
				if(forest.getColor(i) == Color.WHITE) {
					DFSTopological(forest, i, ord);
				}	
			}
			for(int i = 0; i < ord.length; i++) {
				System.out.println("ORD " +i +": "+ ord[i]);
			}
			
			return ord;
		}
		
		return null;
	}

	private void DFSTopological(VisitForest forest, int u, int[] ord) {
	
		forest.setColor(u, Color.GRAY);
		forest.setStartTime(u, time);
		System.out.println("Apertura: d[" + u + "] = "+time);
		time++;
		
		Object[] ary = getAdjacent(u).toArray();
		for(int i = 0; i< getAdjacent(u).size(); i++) {
			if(forest.getColor((Integer)ary[i]) == Color.WHITE) {
				forest.setParent((Integer)ary[i], u);
				DFSTopological(forest, (Integer)ary[i], ord);
			}
		}
		
		forest.setColor(u, Color.BLACK);
		System.out.println("Chiusura: f[" + u + "] = "+time);
		forest.setEndTime(u, time);
		time++;
		ord[t] = u;
		System.out.println(ord[t]);
		t--;
	}

	@Override
	public Set<Set<Integer>> stronglyConnectedComponents() throws UnsupportedOperationException {
		
		int[] sortedEndVisit = new int[matrix.length];
		
		Random random = new Random();
		int randomVertex = random.nextInt(matrix.length - 1);
		System.out.println("StartingVertex: " + randomVertex);
		
		//visita tutti i vertici dfs
		VisitForest forest = new VisitForest(this, VisitType.DFS_TOT);
		DFSVisitRic(forest, randomVertex);
		
		for(int i = 0; i < matrix.length; i++) {
			 sortedEndVisit[i] = i;
			 if(forest.getColor(i) == Color.WHITE) {
				DFSVisitRic(forest, i);
			 }
		}
		
		//ordinamento
		int a;
		for (int k = 0; k < matrix.length; k++){
	           for (int j = k + 1; j < matrix.length; j++){
	               if (forest.getEndTime(sortedEndVisit[k]) < forest.getEndTime(sortedEndVisit[j])){
	                   a = sortedEndVisit[k];
	                   sortedEndVisit[k] = sortedEndVisit[j];
	                   sortedEndVisit[j] = a;
	               }
	           }
	      }
		
		for(int n = 0; n < matrix.length; n ++) {
			System.out.println("Ordinato secondo f[] : " + sortedEndVisit[n]);
		}
		
		//grafo trasposto
		System.out.println("\nVISITA GRAFO TRASPOSTO");
		AdjMatrixDir transposedGraph = new AdjMatrixDir(matrix.length);
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				if(containsEdge(i, j)) {
					transposedGraph.addEdge(j, i);
				}
			}
			
		}
		System.out.println("FINE VISITA GRAFO TRASPOSTO");
		
		Set<Set<Integer>> set = new HashSet<Set<Integer>>();
		Set<Integer> setAux = new TreeSet<Integer>();
		VisitForest transposedForest = new VisitForest(transposedGraph, VisitType.DFS_TOT);
		for(int i = 0; i < sortedEndVisit.length; i++) {
			if(transposedForest.getColor(sortedEndVisit[i]) == Color.WHITE) {
				setAux = transposedGraph.getDFSTreeModified(sortedEndVisit[i], transposedForest);
				set.add(setAux);
			}
		}
		
		Object[] setArray= set.toArray();
		System.out.println("SET");
		for(int i = 0; i < set.size(); i++) {
			System.out.println(setArray[i] + " DIM=" + set.size());
		}
		return set;
	}
	
	private Set<Integer> getDFSTreeModified(int startingVertex, VisitForest transposedForest) {
		Set<Integer> setAux = new TreeSet<Integer>();
		DFSVisitRicModified(transposedForest, startingVertex, setAux);
		return setAux;
	}

	private void DFSVisitRicModified(VisitForest visitDFS, int startingVertex, Set<Integer> set) {
		visitDFS.setColor(startingVertex, Color.GRAY);
		//System.out.println("Vertice: "+startingVertex+" A "+time);
		visitDFS.setStartTime(startingVertex, time);
		time++;
		set.add(startingVertex);
		Object[] ary = getAdjacent(startingVertex).toArray();
 		for(int i = 0; i < getAdjacent(startingVertex).size(); i++) {
 			if(visitDFS.getColor((Integer)ary[i]) == Color.WHITE) {
 				visitDFS.setParent((Integer)ary[i], startingVertex);
 				//System.out.println("Partent of "+(Integer)ary[i]+" is "+startingVertex);
 				DFSVisitRicModified(visitDFS, (Integer)ary[i], set);
 			}
 		}
 		visitDFS.setColor(startingVertex, Color.BLACK);
 		visitDFS.setEndTime(startingVertex, time);
 		//System.out.println("Vertice: "+startingVertex+" C "+time);
		time++;
	}

	@Override
	public Set<Set<Integer>> connectedComponents() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Questa operazione non può essere effettuata su grafi diretti");
	}
}
