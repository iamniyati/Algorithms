/**
 * Created by niyatishah
 */
public class linkedlist {

    Node head,tail;
    int capacity;
    int size;

    linkedlist(int size){
        this.size = size;
    }

    public void add(Object e){
        Node n = new Node(e,null,null);
        capacity = capacity+1;
        if(head==null)
        {
            head = n;
            tail = head;

        }
        else
        {
            n.setLink(head);
            head = n;
        }

    }

    // Returns the current capacity of this list.
    public int	size()
    {
        return capacity;
    }

    public Object pop() {
        Object E;
        if (head == null){
            return null;
    }else {
            E = head.data;
            head = head.next;
            return E;
        }

    }
}


