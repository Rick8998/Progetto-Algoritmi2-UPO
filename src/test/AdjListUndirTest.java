package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import upo.graph.base.VisitForest;
import upo.graph.base.VisitForest.VisitType;
import upo.graph.implementation.AdjListUndir;

/**
 * Classe di test per l'implementazione mediante <strong>liste 
 * di adiacenza</strong> di un grafo <strong>non orientato non 
 * pesato</strong>.
 * 
 * @author Roberto Patera 20026277
 *
 */
class AdjListUndirTest {

	AdjListUndir graph;
	Set<Set<Integer>> set;
	
	@BeforeEach
	public void init() {
		graph = new AdjListUndir();
	}
	
	@Test
	public void testAddVertex() {
		populateGraph();
		assertEquals(4, graph.size());
	}
	
	@Test
	public void testContainsVertex() {
		populateGraph();
		assertTrue(graph.containsVertex(3));
		assertTrue(!graph.containsVertex(4));
		assertTrue(!graph.containsVertex(7));	
	}
	
	@Test
	public void testRemoveVertex() {
		populateGraph();
		graph.removeVertex(3);
		assertEquals(3, graph.size());
	}
	
	@Test
	public void testAddEdge() {
		populateGraph();
		populateOfEdge();
		assertTrue(graph.containsEdge(0, 1));
		assertTrue(graph.containsEdge(0, 2));
		//test su un arco che non c'è
		assertTrue(!graph.containsEdge(1, 2)); 
	}
	
	@Test
	public void testRemoveEdge() {
		populateGraph();
		graph.removeEdge(0, 2);
		assertTrue(!graph.containsEdge(2, 0));
	}
	
	@Test 
	public void testGetAdjacent() {
		populateGraph();
		populateOfEdge();
		Set<Integer> list = new TreeSet<Integer>();
		list.add(1);
		list.add(2);
		assertEquals(list, graph.getAdjacent(0));		
	}
	
	@Test
	public void testIsAdjacent() {
		populateGraph();
		populateOfEdge();
		assertTrue(graph.isAdjacent(0, 2));
	}

	@Test
	public void testGetBFSTree() {
		int[] expectedDistance = {0, 1, 1, 2};
		VisitForest aux = new VisitForest(graph, VisitType.BFS);
		
		populateGraph();
		populateOfEdge();
		System.out.println("********************");
		System.out.println("VISITA BFS PER UN"+"\n"+"GRAFO CON LISTE DI\n"+"ADIACENZA");
		System.out.println("********************");
		System.out.println("Stato di evoluzione\n"+"della coda per una BFS");
		aux = graph.getBFSTree(0);
		System.out.println("********************");
		
		for(int i = 0; i < graph.size(); i++)
			assertEquals(expectedDistance[i], aux.getDistance(i));
	}
	
	@Test
	public void testGetDFSTree() {
		int[] expectedStartTime = {2, 1, 3, 0};
		int[] expectedEndTime = {5, 6, 4, 7};
		VisitForest aux = new VisitForest(graph, VisitType.DFS);
		
		populateGraph();
		populateOfEdge();
		System.out.println("********************");
		System.out.println("VISITA DFS PER UN"+"\n"+"GRAFO CON LISTE DI\n"+"ADIACENZA");
		System.out.println("********************");
		aux = graph.getDFSTree(3);
		System.out.println("********************");
		
		for(int i = 0; i < graph.size(); i++) {
			assertEquals(expectedStartTime[i], aux.getStartTime(i));
			assertEquals(expectedEndTime[i], aux.getEndTime(i));
		}
	}
	
	@Test
	public void testIsCyclic() {
		populateGraph();
		populateOfEdge();
		assertTrue(graph.isCyclic());
	}
	
	@Test
	public void testDFSTOTForest() {
		int[] expectedStartTime = {2, 3, 5, 0};
		int[] expectedEndTime = {7, 4, 6, 1};
		VisitForest aux = new VisitForest(graph, VisitType.DFS_TOT);
		
		populateGraph();
		populateOfEdgeForest();
		System.out.println("********************");
		System.out.println("VISITA DFSTOT PER UN"+"\n"+"GRAFO CON LISTE DI\n"+"ADIACENZA");
		System.out.println("********************");
		aux = graph.getDFSTOTForest(3);
		System.out.println("********************");

		for(int i = 0; i < graph.size(); i++) {
			assertEquals(expectedStartTime[i], aux.getStartTime(i));
			assertEquals(expectedEndTime[i], aux.getEndTime(i));
		}
	}
	
	@Test 
	public void testDFSTOTForestOrdered() {
		int[] expectedStartTime = {3, 2, 4, 0};
		int[] expectedEndTime = {6, 7, 5, 1};
		VisitForest aux = new VisitForest(graph, VisitType.DFS_TOT);
		int[] ary = {3, 1, 0, 2};
		
		populateGraph();
		populateOfEdgeForest();
		System.out.println("********************");
		System.out.println("VISITA DFSTOT PER UN"+"\n"+"GRAFO CON LISTE DI\n"+"ADIACENZA CON ARRAY");
		System.out.println("********************");
		aux = graph.getDFSTOTForest(ary);
		System.out.println("********************");
		
		for(int i = 0; i < graph.size(); i++) {
			assertEquals(expectedStartTime[i], aux.getStartTime(i));
			assertEquals(expectedEndTime[i], aux.getEndTime(i));
		}
		
		assertTrue(isTrue(expectedStartTime, ary));
	}

	@Test
	public void testConnectedComponents() {

		populateGraph();
		populateOfEdgeForest();
		
		System.out.println("********************");
		System.out.println("COMPONENTI CONNESSE DI\n"+"UNA LISTA DI ADIACENZA");
		System.out.println("********************");
		set = graph.connectedComponents();
		
		Object[] ary1 = set.toArray();
		System.out.println("\n");
		System.out.println("RISULTATO COMPONENTI\n"+"CONNESSE:");
		for(int j = 0; j < ary1.length; j++) {
			System.out.println(ary1[j].toString());
		}
		System.out.println("********************");
		
		assertEquals(true, connComp());
	}
	
	private void populateGraph() {
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
	}
	
	private void populateOfEdge() {
		graph.addEdge(0, 1); 
		graph.addEdge(1, 3);
		graph.addEdge(0, 2);
		graph.addEdge(2, 3);
	}
	
	private void populateOfEdgeForest() {
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
	}
	
	private boolean isTrue(int[] expectedStartTime, int[] ary) {
		boolean flag = true;
		for(int i = 0; i < graph.size()-1; i++) {
			if(expectedStartTime[ary[i]] > expectedStartTime[ary[i+1]]) {
				flag = false;
				return flag;
			}
		}
		return flag;
	}
	
	public boolean connComp() {
		for(Set<Integer> s : set) {
			if(s.contains(0)) {
				if(s.contains(1))
					if(s.contains(2))
						return true;
			}
			else if(s.contains(3))
				return true;
		}
		return false;
	}
}
