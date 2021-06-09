package upo.additional.structures;

/** ATTENZIONE: chi avesse bisogno delle priority queue per il proprio algoritmo le deve implementare seguendo
 * questa interfaccia. Non è però necessario che siano implementazioni efficienti. Anche l'implementazione con un array da 0 a n,
 * dove la posizione i-esima rappresenta la priorità del verice i-esimo, come spiegato nel video, va benissimo
 * (anzi, abbiamo anche spiegato essere la più efficiente in alcuni casi).
 * NB: questa PriorityQueue può essere usata anche per Huffman. 
 * NB2: in caso di implementazioni come descritto sopra, per indicare che un elemento non è contenuto nella 
 * priority queue, potete usare un valore di priorità fuori range (ad es. -1 se tutte le priorità sono non negative). 
 * 
 * @author Luca Piovesan
 *
 */
public interface PriorityQueue {
	
	/** Aggiunge l'elemento el alla coda, con priorità priority.
	 * 
	 * @param el l'elemento (o l'indice dell'elemento) da inserire.
	 * @param priority la priorità dell'elemento.
	 */
	public void enqueue(int el, int priority);
	
	/** Rimuove e restituisce l'elemento con priorità maggiore (o minore, a seconda dell'implementazione) di
	 * questa coda con priorità.
	 * 
	 * @return l'elemento con priorità maggiore (o minore, a seconda dell'implementazione) di
	 * questa coda con priorità.
	 */
	public int dequeue();
	
	/** Cambia la priorità di el.
	 * 
	 * @param el l'elemento (o l'indice dell'elemento).
	 * @param newPriority la nuova priorità di el.
	 */
	public void modify_priority(int el, int newPriority);
	

}
