package sorting;

/**
 * Node for the linked list classes.
 * 
 * @author Vishal Bedi
 * @author Daichi Mae
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
