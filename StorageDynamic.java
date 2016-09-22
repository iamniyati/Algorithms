public class StorageDynamic<E,V>
{
	Node head,tail;
    int capacity;
	// Appends the specified element to the end of this storage.
	// Returns true, if the element could be added, else false 
	public boolean	add(E e)
	{
		Node n = new Node(e,null,null);
		capacity = capacity+1;
		if(head==null)
		{
			head = n;
			tail = head;
			return true;
		}
		else
		{
			n.setLink(head);
			head = n;
			return true;
		}
		}

	// Inserts the specified element at the specified position in this Storage.
	// return true, if the element could be added at position index, else false 
	public void add(int index, E element)
	{
		Node toAdd = new Node(element,null,null);
		index = index - 1;
		Node ref = head;
		
		for(int i = 1;i<capacity;i++)
		{
			if(i==index)
			{
				Node temp = ref.getLink();		//Going to index+1th element and keeping a link ready
				ref.setLink(toAdd);				//Add new node to the desired position
				toAdd.setLink(temp);			//Add the link to the end
			}
			ref = ref.getLink();				//Paring to indexth position
		}
		
		capacity = capacity + 1;
	}

	// Adds the specified component to the end of this storage, increasing its size by one.
	public void	addElement(E obj)
	{
		Node n = new Node(obj, null,null);
		capacity = capacity+1;
		if(head==null)
		{
			head = n;
			tail = head;
			
		}
		else
		{
			n.setLink(tail);
			tail= n;
		
		}
	}

	// Adds the specified component to the end of this storage, increasing its size by one.
	public void	addElement(E obj, V elem)
	{
	

		Node n = new Node(obj, null,null);
		capacity = capacity+1;
		if(head==null)
		{
			head = n;
			tail = head;
			
		}
		else
		{
			n.setLink(tail);
			tail= n;
		
		}
	
	}

	// Returns the current capacity of this storage.
	public int	capacity()
	{
		return capacity;
	}

	// Removes all of the elements from this storage.
	public void	clear()
	{
		head = null;
		tail = head;
	}

	// Returns a clone of this storage.
	public Object clone()
	{
		Object[] o = new Object[capacity];
		Node t = head;
		o [1] = t.getData();
		int i = 1;
		while(t.getLink()!=null)
		{
			o[i] = t.getData();
			t= t.getLink();
		}
		return o;
	}

	// Returns the first component (the item at index 0) of this storage.
	public E firstElement()
	{
	 	if(capacity == 0)
	 	{
	 		System.out.println("Empty!!");
	 		return null; 
	 	}
	 	else
	 	{
	 		//Node t = head;	
	 		return (E) head.getData();
	 	}
	}

	// Returns the element at the specified position in this storage.
	public E get(int index)
	{
 		Node t = head;
 		
 		for(int i = 1;i<capacity;i++)
 		{
 			if(i==index)
 			{
 				return (E) t.getData();
 			}
 			t = t.getLink();
 		}
		return null;
	}

	// Returns the last component of the storage.
	public E lastElement()
	{
		if(tail!=null)
		{
			return (E) tail.getData();
		}
		else
		{
			return null;
		}
		
	}
}