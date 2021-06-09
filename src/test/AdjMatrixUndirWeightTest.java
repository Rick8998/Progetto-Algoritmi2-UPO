package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import upo.graph.base.WeightedGraph;
import upo.graph.implementation.AdjMatrixUndirWeight;

/**
 * Classe di test di una <strong>matrice di adiacenza</strong> di un grafo <strong>non orientato pesato</strong>.
 * 
 * @author Andrea Tragno 20025491
 *
 */

class AdjMatrixUndirWeightTest {
	
	AdjMatrixUndirWeight graph;

	@BeforeEach
	public void setUp() {
		graph = new AdjMatrixUndirWeight(0);
	}
	
	@Test
	void removeVertexTest() {
		
		populateGraph();
		
		graph.removeVertex(3);
		assertEquals(4, graph.size());
		
		assertTrue(graph.containsVertex(0));
		assertTrue(graph.containsVertex(1));
		assertTrue(graph.containsVertex(2));
		
		graph.removeVertex(1);
		assertEquals(3, graph.size());
		
		assertTrue(graph.containsVertex(0));
		assertTrue(graph.containsVertex(1));
	}

	@Test
	void testAddVertex() {
		
		populateGraph();
		
		assertEquals(5, graph.size());
		
		assertTrue(graph.containsVertex(0));
		assertTrue(graph.containsVertex(1));
		assertTrue(graph.containsVertex(2));
		assertTrue(graph.containsVertex(3));
	}

	@Test
	void removeEdgeTest() {
		
		populateGraph();
		populateEdge();
	
		graph.removeEdge(0, 1);
		
		assertFalse(graph.containsEdge(0, 1));
		assertFalse(graph.containsEdge(1, 0));
	}
	
	@Test
	void addEdgeTest() {
		
		populateGraph();
		populateEdge();
		
		
		assertTrue(graph.containsEdge(0, 1));
		assertTrue(graph.containsEdge(1, 0));
		assertTrue(graph.containsEdge(2, 3));
		assertFalse(graph.containsEdge(1, 4));
	}
	

	@Test
	void getAdjacentTest() {
		
		populateGraph();
		populateEdge();
		
		Set<Integer> testSetAdjVertex = new HashSet<Integer>();
		testSetAdjVertex.add(1);
		testSetAdjVertex.add(2);
		testSetAdjVertex.add(3);

		assertEquals(testSetAdjVertex, graph.getAdjacent(0));
	}

	@Test
	void isAdjacentTest() {
		
		populateGraph();
		populateEdge();
		
		assertFalse(graph.isAdjacent(0, 4));
	}
	
	@Test
	void isCyclicTest() {
		
		populateGraph();
		populateEdge();
		
		assertTrue(graph.isCyclic());
		
		graph.removeEdge(0, 1);
		graph.removeEdge(0, 2);
		graph.removeEdge(3, 0);
		graph.removeEdge(1, 2);
		graph.removeEdge(1, 3);
		graph.removeEdge(2, 3);
		
		assertFalse(graph.isCyclic());
		
		graph.addEdge(2, 3);
		
		assertTrue(graph.isCyclic());
		
	}
	
	@Test
	void getKruskalMSTTest() {
		populateGraph();
		populateEdge();
		graph.addEdge(4, 1);
		graph.addEdge(3, 4);
		graph.setEdgeWeight(0, 3, 4);
		graph.setEdgeWeight(1, 3, 2);
		graph.setEdgeWeight(2, 3, 3);
		graph.setEdgeWeight(0, 2, 7);
		
		WeightedGraph mar = graph.getKruskalMST();
		
		if(mar.containsEdge(0, 1)) {
			if(mar.containsEdge(2, 1)) {
				if(mar.containsEdge(4, 1)) {
					if(mar.containsEdge(3, 4)) {
						assertTrue(true);
					}
				}
			}
		}
		
		assertFalse(mar.containsEdge(0, 2));
	}
	
	private void populateGraph() {
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
	}
	
	private void populateEdge() {
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(3, 0);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 3);
	}
	
	
}
