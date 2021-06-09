package test;

import static org.junit.jupiter.api.Assertions.*;
import upo.graph.implementation.UnionFindKruskal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class UnionFindKruskalTest {
	
	private static UnionFindKruskal unionFind;
	
	@BeforeAll
	static void setUp() {
		unionFind = new UnionFindKruskal();
	}

	@Test
	void testMakeSet() {
		unionFind.makeSet(1);
	}

	@Test
	void testUnion() {
		fail("Not yet implemented");
	}

	@Test
	void testFind() {
		fail("Not yet implemented");
	}

}
