package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import upo.graph.base.VisitForest;
import upo.graph.base.VisitForest.VisitType;
import upo.graph.implementation.AdjMatrixDir;


/**
 * Implementazione della classe di test per la classe <strong>matrice di adiacenza</strong> di un grafo <strong>orientato non pesato</strong>.
 * 
 * @author Riccardo Cecci 20023915
 *
 */
class AdjMatrixDirTest {

	AdjMatrixDir graph;
	
	@BeforeEach
	public void init() {
		graph = new AdjMatrixDir(0);
	}
	
	@Test
	void testAddVertex() {
		populateGraph();
		assertEquals(4, graph.size());
		
	}
	
	private void populateGraph() {
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
	}
	
	private void populateEdge() {
		graph.addEdge(0, 1);
		graph.addEdge(1, 3);
		graph.addEdge(0, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 0);
	}
	
	@Test
	void containsVertexTest() {
		populateGraph();
		
		assertTrue(graph.containsVertex(0));
		assertTrue(graph.containsVertex(1));
		assertTrue(graph.containsVertex(2));
		assertTrue(graph.containsVertex(3));
		
	}
	
	@Test
	void removeVertexTest() {
		populateGraph();
		
		graph.removeVertex(3);
		assertEquals(3, graph.size());
		
		
		assertTrue(graph.containsVertex(0));
		assertTrue(graph.containsVertex(1));
		assertTrue(graph.containsVertex(2));
		
		graph.removeVertex(2);
		assertEquals(2, graph.size());
		
		assertTrue(graph.containsVertex(0));
		assertTrue(graph.containsVertex(1));
	}
	
	@Test
	void addEdgeTest() {
		populateGraph();
		
		graph.addEdge(0, 1);
		
		assertTrue(graph.containsEdge(0, 1));
		
		assertFalse(graph.containsEdge(1, 2));
	}
	
	@Test
	void containsEdgeTest() {
		populateGraph();
		
		graph.addEdge(0, 1);
		
		assertTrue(graph.containsEdge(0, 1));
		
		assertFalse(graph.containsEdge(1, 2));
	}
	
	@Test
	void removeEdgeTest() {
		populateGraph();
		
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		
		assertTrue(graph.containsEdge(0, 1));
		assertTrue(graph.containsEdge(1, 2));
		
		graph.removeEdge(0, 1);
		
		assertFalse(graph.containsEdge(0, 1));
		assertTrue(graph.containsEdge(1, 2));
	}

	@Test
	void getAdjacentTest() {
		
		Set<Integer> setAdjVertex = new HashSet<Integer>();
		setAdjVertex.add(1);
		setAdjVertex.add(3);
		setAdjVertex.add(2);
		
		populateGraph();
		
		graph.addEdge(0, 1);
		graph.addEdge(0, 3);
		graph.addEdge(0, 2);
		
		assertEquals(setAdjVertex, graph.getAdjacent(0));
	}
	
	@Test
	void isAdjacentTest() {
		
		populateGraph();
		
		graph.addEdge(0, 1);
		graph.addEdge(2, 3);
		
		assertTrue(graph.isAdjacent(1, 0));
		assertTrue(graph.isAdjacent(3, 2));
		
		assertFalse(graph.isAdjacent(0, 1));
		assertFalse(graph.isAdjacent(2, 3));
	}
	
	@Test
	void sizeTest() {
		populateGraph();
		assertEquals(4, graph.size());
	}
	
	@Test
	void isCyclicTest() {
		populateGraph();
		populateEdgeCyclic();
		assertTrue(graph.isCyclic());
	}
	
	private void populateEdgeCyclic() {
		graph.addEdge(0, 1);
		graph.addEdge(1, 3);
		graph.addEdge(2, 1);
		graph.addEdge(3, 2);
	}
	
	@Test
	void isDAGTest() {
		populateGraph();
		populateEdge();
		assertFalse(graph.isDAG());
	}

	@Test
	void getBFSTreeTest() {
		populateGraph();
		populateEdge();
		System.out.println("TEST BFS");
		VisitForest visitDFSTest = new VisitForest(graph, VisitType.BFS);
		visitDFSTest = graph.getBFSTree(0);
		int[] expectedDistance = {0, 1, 1, 2};
		
		for(int i = 0; i < graph.size(); i++) {
			assertEquals(expectedDistance[i], visitDFSTest.getDistance(i));
		}
	}

	@Test
	void getDFSTreeTest() {
		System.out.println("TEST DFS");
		populateGraph();
		populateEdge();
		
		int[] expectedStartTime = {0, 1, 5, 2};
		int[] expectedEndTime = {7, 4, 6, 3};
		
		VisitForest visitDFSTest = new VisitForest(graph, VisitType.DFS);
		visitDFSTest = graph.getDFSTree(0);
		
		for(int i = 0; i < graph.size(); i++) {
			assertEquals(expectedStartTime[i], visitDFSTest.getStartTime(i));
			assertEquals(expectedEndTime[i], visitDFSTest.getEndTime(i));
		}
	}
	
	@Test
	void getDFSTOTForestTest() {
		populateGraph();
		populateEdgeForest();
	
		System.out.println("\nTEST DFS_TOT");
		VisitForest visitDFSTest = new VisitForest(graph, VisitType.DFS_TOT);
		visitDFSTest = graph.getDFSTOTForest(3);
		
		int[] expectedStartTime = {2, 3, 6, 0};
		int[] expectedEndTime = {5, 4, 7, 1};
		
		for(int i = 0; i < graph.size(); i++) {
			assertEquals(expectedStartTime[i], visitDFSTest.getStartTime(i));
			assertEquals(expectedEndTime[i], visitDFSTest.getEndTime(i));
		}
		System.out.println();
	}

	private void populateEdgeForest() {
		graph.addEdge(0, 1);
		graph.addEdge(2, 3);
	}
	
	@Test
	void getDFSTOTForestOreder() {
		int[] ary = {3, 1, 0, 2};
		populateGraph();
		populateEdgeForest();
		System.out.println("\nTEST DFS_TOT_ORDER");
		
		//System.out.println("\nTEST DFS_TOT");
		VisitForest visitDFSTest = new VisitForest(graph, VisitType.DFS_TOT);
		visitDFSTest = graph.getDFSTOTForest(ary);
		
		int[] expectedStartTime = {4, 2, 6, 0};
		int[] expectedEndTime = {5, 3, 7, 1};
		
		for(int i = 0; i < graph.size(); i++) {
			assertEquals(expectedStartTime[i], visitDFSTest.getStartTime(i));
			assertEquals(expectedEndTime[i], visitDFSTest.getEndTime(i));			
		}
		
		assertTrue(isTrue(expectedStartTime, ary));
		
		System.out.println();
	}
	
	private boolean isTrue(int[] expectedStartTime, int[] ary) {
		boolean minor = true;
		for(int i = 0; i < graph.size()-1; i++) {
			if(expectedStartTime[ary[i]] > expectedStartTime[ary[i+1]]) {
				minor = false;
				return minor;
			}
		}
		return minor;
	}

	@Test
	void topologicalSortTest() {
		System.out.println("TOPOLOGICAL_SORT");
		populateGraph();
		populateEdgeTop();
		int[] aux = {0, 2, 1, 3};
		int[] ord = graph.topologicalSort();
		
		//graph.topologicalSort();
		for(int i = 0; i < graph.size(); i++) {
			assertEquals(aux[i], ord[i]);
		}
		
	}
	
	private void populateEdgeTop() {
		graph.addEdge(0, 1);
		graph.addEdge(1, 3);
		graph.addEdge(0, 2);
		graph.addEdge(2, 3);
		
	}
	
	@Test
	void stronglyConnected() {
		System.out.println("\nSTRONGLY CONNECTED SIMPLE");
		populateGraph3();
		populateStronglyConnected();
		
		Set<Set<Integer>> set = graph.stronglyConnectedComponents();
		assertEquals(4, set.size());
	}
	
	private void populateGraph3() {
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		
	}
 
	private void populateStronglyConnected() {
		graph.addEdge(1, 0);
		graph.addEdge(0, 4);
		graph.addEdge(4, 0);
		graph.addEdge(5, 4);
		graph.addEdge(5, 1);
		graph.addEdge(0, 5);
		graph.addEdge(4, 7);
		graph.addEdge(5, 7);
		graph.addEdge(2, 3);
		graph.addEdge(3, 2);
		graph.addEdge(2, 6);
		graph.addEdge(6, 2);
		graph.addEdge(6, 8);
		graph.addEdge(8, 7);
		graph.addEdge(8, 9);
		graph.addEdge(9, 8);
		graph.addEdge(2, 1);
		graph.addEdge(6, 5);
		
		
		/*graph.addEdge(0, 3);
		graph.addEdge(1, 0);
		graph.addEdge(0, 2);
		graph.addEdge(2, 0);
		graph.addEdge(1, 3);
		graph.addEdge(3, 1);*/
	}
}
