package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import upo.progdin.DynamicProgramming;

class DynamicProgrammingTest {
	
	int[] weights;
	int[] values;
	int maxWeight;
	
	@BeforeEach
	void setUp() {
		weights = new int[5];
		values = new int[5];
		maxWeight = 10;
		weights[0] = 2;
		weights[1] = 7;
		weights[2] = 6;
		weights[3] = 4;
		weights[4] = 8;
		
		values[0] = 12;
		values[1] = 6;
		values[2] = 3;
		values[3] = 1;
		values[4] = 8;
	}
	
	
	@Test
	void testGetKnapsack01() {
		boolean sol[] = new boolean[weights.length];
		sol = DynamicProgramming.getKnapsack01(weights, values, maxWeight);
		
		assertTrue(sol[0]);
		assertFalse(sol[1]);
		assertFalse(sol[2]);
		assertFalse(sol[3]);
		assertTrue(sol[4]);
	}
	
	@Test
	void testLCS() {
		String s1 = "ATCBAB";
		String s2 = "BACATBA";
		String sol = "ACAB";
		String resFunction;
		
		resFunction = DynamicProgramming.LongestCommonSubsequence(s1, s2);
		
		assertEquals(sol, resFunction);
	}
}
