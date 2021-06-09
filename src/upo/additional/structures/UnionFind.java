package upo.additional.structures;

/** Interfaccia che rappresenta il tipo UnionFindi. Da usare per le UnionFind
 * per chi ha da implementare l'algoritmo di Kruskal.
 * 
 * @author Luca Piovesan
 *
 * @param <T> il tipo di dato contenuto negli insiemi. Per Kruskal, potete usare un'implementazione con
 * Integer.
 */
public interface UnionFind<T> {
	
	/** Crea un insieme singoletto contenente element.
	 * 
	 * @param element l'elemento da inserire nella UnionFind.
	 */
	public void makeSet(T element);
	
	/** Unisce i due insiemi find(el1) e find(el2).
	 * 
	 * @param el1 un elemento del primo insieme da unire.
	 * @param el2 un elemento del secondo insieme da unire.
	 */
	public void union(T el1, T el2);
	
	/** Restituisce il rappresentante dell'insieme che contiene el.
	 * 
	 * @param el l'elemento da cercare.
	 * @return il rappresentante dell'insieme che contiene el.
	 */
	public T find(T el);

}
