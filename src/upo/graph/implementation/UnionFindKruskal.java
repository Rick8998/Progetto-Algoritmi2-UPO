package upo.graph.implementation;

import java.util.ArrayList;
import upo.additional.structures.UnionFind;

/**
 * 
 * @author Andrea Tragno 20025491
 *
 */

public class UnionFindKruskal implements UnionFind<Integer> {
	
	ArrayList<ArrayList<Integer>> kruskalUf;
	
	public UnionFindKruskal() {
		kruskalUf = new ArrayList<>();
	}

	@Override
	public void makeSet(Integer element) {
		
		/*controllo che element non sia già contenuto nella union find*/
		for(ArrayList<Integer> curr: kruskalUf) {
			if(curr.contains(element))
				throw new IllegalArgumentException(element + "Elemento già presente nella union find");
		}
		
		/* creo un insieme vuoto*/
		ArrayList<Integer> singoletto = new ArrayList<Integer>(); 
		
		/* lo faccio diventare un singoletto*/
		singoletto.add(element);
		
		/* aggiungo il singoletto alla union find*/
		kruskalUf.add(singoletto);
		
	}

	@Override
	public void union(Integer el1, Integer el2) {
		
		int rapEl1 = find(el1);
		int rapEl2 = find(el2);
		int indexRapEl1 = -1;
		int indexRapEl2 = -1;
		
		if(rapEl1 != rapEl2) {
			while(indexRapEl1 == -1 && indexRapEl2 == -1) {
				for (ArrayList<Integer> arrayList : kruskalUf) {
					if(arrayList.get(0) == rapEl2) 
						indexRapEl2 = kruskalUf.indexOf(arrayList);
					else if(arrayList.get(0) == rapEl1)
						indexRapEl1 = kruskalUf.indexOf(arrayList);
				}
			}
			
			while(!kruskalUf.get(indexRapEl2).isEmpty()) {
				kruskalUf.get(indexRapEl1).add(kruskalUf.get(indexRapEl2).get(0));
				kruskalUf.get(indexRapEl2).remove(0);	
			}
		}
		kruskalUf.remove(indexRapEl2);
	}

	@Override
	public Integer find(Integer el) {
		for(ArrayList<Integer> curr: kruskalUf) {
			if(curr != null) {
				for (Integer integer : curr) {
					if(integer == el)
						return curr.get(0);
				}	
			}
		}
		return null;
	}
}
