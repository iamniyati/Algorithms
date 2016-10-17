package sorting;

/**
 * Node for the linked list classes.
 * 
 * 
 */

public class Node<E>{
	E data;
	Node<E> next;
	Node(E data, Node<E> link){
		this.data = data;
		this.next = link;
	}
}
