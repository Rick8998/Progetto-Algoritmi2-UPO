package upo.graph.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import upo.graph.base.Graph;
import upo.graph.base.VisitForest;
import upo.graph.base.VisitForest.Color;
import upo.graph.base.VisitForest.VisitType;

/**
 * Implementazione mediante <strong>liste di adiacenza</strong> di 
 * un grafo <strong>non orientato non pesato</strong>.
 * 
 * @author Roberto Patera 20026277
 *
 */
public class AdjListUndir implements Graph{

	ArrayList<VertexAdjList> list;
	int time = 0;
	
	public AdjListUndir() {
		list = new ArrayList<VertexAdjList>();
	}

	@Override
	public int addVertex() {
		list.add(new VertexAdjList());
		return list.size();
	}

	@Override
	public boolean containsVertex(int index) {
		for(int i = 0; i < list.size(); i++) {
			if(i == index)
				return true;
		}
		return false;
	}

	@Override
	public void removeVertex(int index) throws NoSuchElementException {
		if(list.size() <= index)
			throw new NoSuchElementException("Non esiste il vertice di indice "+index);
		if(!containsVertex(index))
			return;
		for(int i = 0; i < list.size(); i++) {
			if(i == index)
				list.remove(i);
		}
		for(int i = 0; i < list.size(); i++) {
			for(int j = 0; j < list.get(i).getAdjVertex().size(); j++) {
				if(list.get(i).getAdjVertex().get(j) == index)
					list.get(i).getAdjVertex().remove(j);
			}
		}	
	}

	@Override
	public void addEdge(int sourceVertexIndex, int targetVertexIndex) throws IllegalArgumentException {
		if(sourceVertexIndex < 0 || list.size() <= sourceVertexIndex)
			throw new IllegalArgumentException("L'argomento "+sourceVertexIndex+" non è valido");
		if(targetVertexIndex < 0 || list.size() <= targetVertexIndex)
			throw new IllegalArgumentException("L'argomento "+targetVertexIndex+" non è valido");
		if(containsEdge(sourceVertexIndex, targetVertexIndex))
			return;
		for(int i = 0; i < list.size(); i++) {
			if(i == sourceVertexIndex) {
				list.get(i).getAdjVertex().add(targetVertexIndex);
				break;
			}
		}
		for(int i = 0; i < list.size(); i++) {
			if(i == targetVertexIndex) {
				list.get(i).getAdjVertex().add(sourceVertexIndex);
				break;
			}		
		}
	}

	@Override
	public boolean containsEdge(int sourceVertexIndex, int targetVertexIndex) throws IllegalArgumentException {
		if(sourceVertexIndex < 0 || list.size() <= sourceVertexIndex)
			throw new IllegalArgumentException("L'argomento "+sourceVertexIndex+" non è valido");
		if(targetVertexIndex < 0 || list.size() <= targetVertexIndex)
			throw new IllegalArgumentException("L'argomento "+targetVertexIndex+" non è valido");
		
		boolean fSource = false;
		boolean fTarget  = false;
		for(int i = 0; i < list.size(); i++) {
			if(i == sourceVertexIndex) {
				for(int j = 0; j < list.get(i).getAdjVertex().size(); j++) {
					if(list.get(i).getAdjVertex().get(j) == targetVertexIndex)
						fSource = true;
				}
				break;
			}
		}
		for(int i = 0; i < list.size(); i++) {
			if(i == targetVertexIndex) {
				for(int j = 0; j < list.get(i).getAdjVertex().size(); j++) {
					if(list.get(i).getAdjVertex().get(j) == sourceVertexIndex)
						fTarget = true;
				}
				break;
			}
		}
		if(fSource && fTarget)
			return true;
		return false;
	}

	@Override
	public void removeEdge(int sourceVertexIndex, int targetVertexIndex)
			throws IllegalArgumentException, NoSuchElementException {
		if(list.size() <= sourceVertexIndex)
			throw new NoSuchElementException("Non esiste il vertice di indice "+sourceVertexIndex);
		if(list.size() <= targetVertexIndex)
			throw new NoSuchElementException("Non esiste il vertice di indice "+targetVertexIndex);
		if(sourceVertexIndex < 0)
			throw new IllegalArgumentException("L'argomento "+sourceVertexIndex+" non è valido");
		if(targetVertexIndex < 0)
			throw new IllegalArgumentException("L'argomento "+targetVertexIndex+" non è valido");
		if(!containsEdge(sourceVertexIndex, targetVertexIndex))
			return;
		for(int i = 0; i < list.size(); i++) {
			if(i == sourceVertexIndex) {
				for(int j = 0; j < list.get(i).getAdjVertex().size(); j++) {
					if(list.get(i).getAdjVertex().get(j) == targetVertexIndex)
						list.get(i).getAdjVertex().remove(j);
				}
				break;
			}
		}
		for(int i = 0; i < list.size(); i++) {
			if(i == targetVertexIndex) {
				for(int j = 0; j < list.get(i).getAdjVertex().size(); j++) {
					if(list.get(i).getAdjVertex().get(j) == sourceVertexIndex)
						list.get(i).getAdjVertex().remove(j);
				}
				break;
			}
		}
	}

	@Override
	public Set<Integer> getAdjacent(int vertexIndex) throws NoSuchElementException {
		if(list.size() <= vertexIndex)
			throw new NoSuchElementException("Non esiste il vertice di indice "+vertexIndex);
		Set<Integer> setAdjVertex =  new TreeSet<Integer>();
		for(int i = 0; i < list.size(); i++) {
			if(i == vertexIndex) {
				for(int j = 0; j < list.get(i).getAdjVertex().size(); j++)
					setAdjVertex.add(list.get(i).getAdjVertex().get(j));
				break;
			}
		}
		return setAdjVertex;
	}

	@Override
	public boolean isAdjacent(int targetVertexIndex, int sourceVertexIndex) throws IllegalArgumentException {
		if(sourceVertexIndex < 0)
			throw new IllegalArgumentException("L'argomento "+sourceVertexIndex+" non è valido");
		if(targetVertexIndex < 0)
			throw new IllegalArgumentException("L'argomento "+targetVertexIndex+" non è valido");
		
		boolean fSource = false;
		boolean fTarget = false;
		for(int i = 0; i < list.size(); i++) {
			if(i == sourceVertexIndex) {
				for(int j = 0; j < list.get(i).getAdjVertex().size(); j++) {
					if(list.get(i).getAdjVertex().get(j) == targetVertexIndex)
						fSource = true;
				}
			}
		}
		for(int i = 0; i < list.size(); i++) {
			if(i == targetVertexIndex) {
				for(int j = 0; j < list.get(i).getAdjVertex().size(); j++) {
					if(list.get(i).getAdjVertex().get(j) == sourceVertexIndex)
						fTarget = true;
				}
			}
		}
		if(fSource && fTarget)
			return true;
		return false;
	}

	@Override
	public int size() {
		return list.size();
	} 

	@Override
	public boolean isDirected() {
		return false;
	}

	@Override
	public boolean isCyclic() {
		VisitForest graph = new VisitForest(this, null);
		for(int i = 0; i < this.size(); i++) {
			if((graph.getColor(i) == Color.WHITE) && VisitRicCycle(graph, i))
				return true;
		}
		return false;
	}

	private boolean VisitRicCycle(VisitForest graph, int u) {
		graph.setColor(u, Color.GRAY);
		Object[] ary = getAdjacent(u).toArray();
		for(int i = 0; i < getAdjacent(u).size(); i++) {
			if(graph.getColor((Integer)ary[i]) == Color.WHITE) {
				graph.setParent((Integer)ary[i], u);
				if(VisitRicCycle(graph, (Integer)ary[i]))
					return true;
			} else if((Integer)ary[i] != graph.getPartent(u))
				return true;
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
		if(startingVertex < 0 || list.size() <= startingVertex)
			throw new IllegalArgumentException("L'argomento "+startingVertex+" non è valido");
		VisitForest visitBFS = new VisitForest(this, VisitType.BFS);
		BFSVisit(visitBFS, startingVertex);
		return visitBFS;
	}
 
	private void BFSVisit(VisitForest visitBFS, int startingVertex) {
		Queue<Integer> queue = new LinkedList<Integer>();
		visitBFS.setColor(startingVertex, Color.GRAY);
		visitBFS.setDistance(startingVertex, 0);
		System.out.println("\t"+queue.toString());
		queue.add(startingVertex);
		while(!queue.isEmpty()) {
			System.out.println("\t"+queue.toString());
			int u = queue.peek();
			Object[] ary = getAdjacent(u).toArray();
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
		System.out.println("\t"+queue.toString());
	}

	@Override
	public VisitForest getDFSTree(int startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
		VisitForest visitDFS = new VisitForest(this, VisitType.DFS);
		DFSVisitRic(visitDFS, startingVertex);
		return visitDFS;
	}

	private void DFSVisitRic(VisitForest visitDFS, int startingVertex) {
		visitDFS.setColor(startingVertex, Color.GRAY);
		System.out.println("Vertice: "+startingVertex+" A "+time);
		visitDFS.setStartTime(startingVertex, time);
		time++;
		Object[] ary = getAdjacent(startingVertex).toArray();
 		for(int i = 0; i < getAdjacent(startingVertex).size(); i++) {
 			if(visitDFS.getColor((Integer)ary[i]) == Color.WHITE) {
 				visitDFS.setParent((Integer)ary[i], startingVertex);
 				DFSVisitRic(visitDFS, (Integer)ary[i]);
 			}
 		}
 		visitDFS.setColor(startingVertex, Color.BLACK);
 		visitDFS.setEndTime(startingVertex, time);
 		System.out.println("Vertice: "+startingVertex+" C "+time);
		time++;
	}

	@Override
	public VisitForest getDFSTOTForest(int startingVertex)
			throws UnsupportedOperationException, IllegalArgumentException {
		if(startingVertex < 0 || list.size() <= startingVertex)
			throw new IllegalArgumentException("L'argomento "+startingVertex+" non è valido");
		VisitForest forest = new VisitForest(this, VisitType.DFS_TOT);
		DFSVisitRic(forest, startingVertex);
		for(int i = 0; i < list.size(); i++) {
			if(forest.getColor(i) == Color.WHITE)
				DFSVisitRic(forest, i);
		}
		return forest;
	}

	@Override
	public VisitForest getDFSTOTForest(int[] vertexOrdering)
			throws UnsupportedOperationException, IllegalArgumentException {
		// TODO per verificare che gli elementi siano legali nel vertice
		VisitForest forest = new VisitForest(this, VisitType.DFS_TOT);
		for(int i = 0; i < vertexOrdering.length; i++) {
			if(forest.getColor(vertexOrdering[i]) == Color.WHITE)
				DFSVisitRic(forest, vertexOrdering[i]);
		}
		return forest;
	} 

	@Override
	public int[] topologicalSort() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("L'operazione di scoperta dell'ordinamento topologico"+"\n"+"non è supportata per un grafo non orintato\n");
	}

	@Override
	public Set<Set<Integer>> stronglyConnectedComponents() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Questa operazione non può essere fatta su un grafo non orientato");
	}

	// TODO controlla che forse la isConnected non serve
	@Override
	public Set<Set<Integer>> connectedComponents() throws UnsupportedOperationException {
		Set<Set<Integer>> set = new HashSet<Set<Integer>>();
		Set<Integer> set1 = new TreeSet<Integer>();
		VisitForest visit = new VisitForest(this, VisitType.DFS);
		for(int i = 0; i < size(); i++) {
			if(visit.getColor(i) == Color.WHITE) {
				set1 = getDFSTreeModified(i, visit);
				set.add(set1);
			}
		}
		return set;
	}

	private Set<Integer> getDFSTreeModified(int startingVertex, VisitForest visit) {
		Set<Integer> setAux = new TreeSet<Integer>();
		DFSVisitRicModified(visit, startingVertex, setAux);
		return setAux;
	}

	private void DFSVisitRicModified(VisitForest visit, int startingVertex, Set<Integer> set) {
		visit.setColor(startingVertex, Color.GRAY);
		System.out.println("Vertice: "+startingVertex+" A "+time);
		visit.setStartTime(startingVertex, time);
		time++;
		set.add(startingVertex);
		Object[] ary = getAdjacent(startingVertex).toArray();
 		for(int i = 0; i < getAdjacent(startingVertex).size(); i++) {
 			if(visit.getColor((Integer)ary[i]) == Color.WHITE) {
 				visit.setParent((Integer)ary[i], startingVertex);
 				DFSVisitRicModified(visit, (Integer)ary[i], set);
 			}
 		}
 		visit.setColor(startingVertex, Color.BLACK);
 		visit.setEndTime(startingVertex, time);
 		System.out.println("Vertice: "+startingVertex+" C "+time);
		time++;
	}
}
