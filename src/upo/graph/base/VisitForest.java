package upo.graph.base;

import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Classe che rappresenta una foresta di visita (o albero di visita, per determinati tipi di visite).
 * Deve essere usata per rappresentare l'output di una visita.
 * Questa classe non deve essere modificata. Se trovate errori contattate il docente.
 * 
 * @author Luca Piovesan
 *
 */
public class VisitForest {
	
	private final Graph graph;
	
	public static enum VisitType {BFS, DFS, DFS_TOT}
	public final VisitType visitType;
	
	public static enum Color {WHITE, GRAY, BLACK}
	private Color[] vertexColor;
	
	private Integer[] parent;
	private Double[] distance;
	
	private int[] startTime;
	private int[] endTime;
	
	/**
	 * Costruisce una nuova foresta di visita.
	 * 
	 * @param graph il grafo visitato.
	 * @param visitType il tipo di visita.
	 */
	public VisitForest(Graph graph, VisitType visitType) {
		this.graph = graph;
		this.visitType = visitType;
		this.initialize();
	}
	
	private void initialize() {
		vertexColor = new Color[graph.size()];
		Arrays.fill(vertexColor, Color.WHITE);
		parent = new Integer[graph.size()];
		Arrays.fill(parent, null);
		distance = new Double[graph.size()];
		Arrays.fill(distance, null);
		startTime = new int[graph.size()];
		Arrays.fill(startTime, -1);
		endTime = new int[graph.size()];
		Arrays.fill(endTime, -1);
	}
	
	/**
	 * Restituisce gli indici di tutti i vertici radice.
	 * @return un insieme di indici che rappresentano i vertici che non hanno un predecessore.
	 */
	public Set<Integer> getRoots() {
		Set<Integer> res = new HashSet<Integer>();
		for(int i = 0; i < parent.length; i++) {
			if(parent[i] == null) 
				res.add(i);
		}
		return res;
	}
	
	public Color getColor(int vertex) throws NoSuchElementException {
		if(vertex >= graph.size()) throw new NoSuchElementException("Il vertice di indice "+vertex+" non appartiene al grafo");
		return vertexColor[vertex];
	}
	
	public void setColor(int vertex, Color color) throws NoSuchElementException, IllegalArgumentException {
		if(vertex >= graph.size()) throw new NoSuchElementException("Il vertice di indice "+vertex+" non appartiene al grafo");
		if(vertexColor[vertex].compareTo(color) > 0) throw new IllegalArgumentException("Il colore di un vertice non puo' passare da GRAY a WHITE o da BLACK a GRAY o WHITE");
		vertexColor[vertex] = color;
	}
	
	public int getPartent(int vertex) throws NoSuchElementException {
		if(vertex >= graph.size()) throw new NoSuchElementException("Il vertice di indice "+vertex+" non appartiene al grafo");
		return parent[vertex];
	}
	
	public void setParent(int vertex, int parent) throws NoSuchElementException, IllegalArgumentException {
		if(vertex >= graph.size()) throw new NoSuchElementException("Il vertice di indice "+vertex+" non appartiene al grafo");
		if(parent >= graph.size()) throw new NoSuchElementException("Il vertice di indice "+parent+" non appartiene al grafo");
		this.parent[vertex] = parent;
	}
	
	public double getDistance(int vertex) throws NoSuchElementException {
		if(vertex >= graph.size()) throw new NoSuchElementException("Il vertice di indice "+vertex+" non appartiene al grafo");
		return distance[vertex];
	}
	
	public void setDistance(int vertex, double distance) throws NoSuchElementException, IllegalArgumentException {
		if(vertex >= graph.size()) throw new NoSuchElementException("Il vertice di indice "+vertex+" non appartiene al grafo");
		this.distance[vertex] = distance;
	}
	
	public int getStartTime(int vertex) throws NoSuchElementException {
		if(vertex >= graph.size()) throw new NoSuchElementException("Il vertice di indice "+vertex+" non appartiene al grafo");
		return startTime[vertex];
	}
	
	public void setStartTime(int vertex, int startTime) throws NoSuchElementException {
		if(vertex >= graph.size()) throw new NoSuchElementException("Il vertice di indice "+vertex+" non appartiene al grafo");
		this.startTime[vertex] = startTime;
	}
	
	public int getEndTime(int vertex) throws NoSuchElementException {
		if(vertex >= graph.size()) throw new NoSuchElementException("Il vertice di indice "+vertex+" non appartiene al grafo");
		return endTime[vertex];
	}
	
	public void setEndTime(int vertex, int endTime) throws NoSuchElementException {
		if(vertex >= graph.size()) throw new NoSuchElementException("Il vertice di indice "+vertex+" non appartiene al grafo");
		this.endTime[vertex] = endTime;
	}
}
