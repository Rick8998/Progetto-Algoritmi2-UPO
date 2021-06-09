package upo.progdin;

public class DynamicProgramming {
	
	/** Calcola la LCS tra <code>s1</code> e <code>s2</code> utilizzando l'algoritmo visto a lezione.
	 * </br>CONSIGLIO: potete usare i metodi di String per accedere alle posizioni di s1 ed s2.
	 * </br>CONSIGLIO2: potete costruire l'output come un array di caratteri, e poi trasformarlo in stringa,
	 * oppure usare le concatenazioni di stringhe nelle chiamate ricorsive (vedi slide).
	 * 
	 * @author Roberto Patera 20026277
	 * 
	 * @param s1 una sequenza di caratteri
	 * @param s2 una sequenza di caratteri
	 * @return una LCS di <code>s1</code> e <code>s2</code>
	 */
	public static String LongestCommonSubsequence(String s1, String s2) {
		String[][] LCS = new String[s1.length() + 1][s2.length() + 1];
	    Integer[][] L = new Integer[s1.length() + 1][s2.length() + 1];
	    
	    //inizializzazione
	    for(int i = 0; i <= s1.length(); i++) { 
	    	LCS[i][0] = "";
	    	L[i][0] = 0;
	    }
	    for(int j = 0; j <= s2.length(); j++) {
	    	LCS[0][j] = "";
	    	L[0][j] = 0;
	    }
	    
	    //riempimento della matrice
	    for(int i = 1; i <= s1.length(); i++) {
	    	for(int j = 1; j <= s2.length(); j++) {
	    		//uguaglianza del carattere nella stringa orginale per pos corrispondenti
	    		if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
	    			LCS[i][j] = LCS[i - 1][j - 1] + s1.charAt(i - 1);
	    			L[i][j] = L[i - 1][j - 1] + 1;
	    		}
	    		//mi chiedo se la cella a sx ha peso maggiore di quella sopra
	    		else if(L[i - 1][j] > L[i][j - 1]) {
	    			LCS[i][j] = LCS[i - 1][j];
	    			L[i][j] = L[i - 1][j];
	    		}
	    		//allora riempio con la cella sopra
	    		else {
	    			LCS[i][j] = LCS[i][j - 1];
	    			L[i][j] = L[i][j - 1];
	    		}
	    	}
	    }
	    
		return LCS[s1.length()][s2.length()];
	}

	/** Risolve il problema dello zaino 0-1 con l'algoritmo di programmazione dinamica visto a lezione.
	 * 
	 * @author Riccardo Cecci 20023915
	 * 
	 * @param weights un vettore contenente in posizione i-esima, per ogni oggetto oi, il suo peso. 
	 * @param values un vettore contenente in posizione i-esima, per ogni oggetto oi, il suo valore. 
	 * @param maxWeight la capienza dello zaino.
	 * @return un vettore di boolean che contiene, in posizione i-esima, true se l'oggetto i-esimo �
	 * incluso nella soluzione, false altrimenti.
	 */
	public static boolean[] getKnapsack01(int[] weights, int[] values, int maxWeight) {
		/*weights = p(i) --- values = v(i) --- maxWeight = P*/
		int n = values.length;
		int v[][] = new int[n + 1][maxWeight + 1];
		boolean k[][] = new boolean[n + 1][maxWeight + 1];
		boolean sol[] = new boolean[n];
		
		for(int i = 0; i <= n; i++) {
			v[i][0] = 0;
			k[i][0] = false;
		}
		
		for(int j = 0; j <= maxWeight; j++) {
			v[0][j] = 0;
			k[0][j] = false;
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= maxWeight; j++) {
				v[i][j] = v[i - 1][j];
				k[i][j] = false;
				if(j >= weights[i -1]) {
					if(v[i][j] < v[i-1][ j - weights[i -1]] + values[i - 1]) {
						v[i][j] = v[i-1][ j - weights[i - 1]] + values[i - 1];
						k[i][j] = true;
					}
				}
			}
		}
		
		int d = maxWeight;
		int i = n;
		
		while (i > 0) {
			if(k[i][d] == true) {
				sol[i-1] = true;
				d = d - weights[i - 1];
			}
			i = i - 1;
		}
		return sol;
	}
	
}

