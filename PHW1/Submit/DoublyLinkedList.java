/*
 * Name: Kim, Jin Seol
 * Student ID: 2011133024
 */

public class DoublyLinkedList
{
    /*
     * Our instance variables
     * size will hold the number of elements in the linked list
     * head references the head node in the linked list
     * tail references the tail node in the linked list
     */

    private int size;
    private DoublyLinkedListNode head;
    private DoublyLinkedListNode tail;

    public DoublyLinkedList()
    {
        /*
         * Our constructor
         * Should initalize the instance variables to their default value
         * Since it is empty at the start, head and tail should be null
         */
        size = 0;
        head = null;
        tail = null;
    }

    public DoublyLinkedListNode add(char data, int index)
    {
        /*
         * Adds and returns a new node with the provided data at the specified index
         * Returns null if the index is not possible
         */
        if (index < 0 || index > size)
        {
            return null;
        }
        DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);
        if (size == 0)
        {
            newNode.setNext(null);
            newNode.setPrev(null);
            head = newNode;
            tail = newNode;
        }
        else if (index == 0)
        {
            newNode.setNext(head);
            newNode.setPrev(null);
            head.setPrev(newNode);
            head = newNode;
        }
        else if (index == size)
        {
            newNode.setNext(null);
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
        else
        {
            DoublyLinkedListNode prevNewNode = getNode(index-1);
            DoublyLinkedListNode nextNewNode = prevNewNode.getNext();
            newNode.setNext(nextNewNode);
            newNode.setPrev(prevNewNode);
            prevNewNode.clearNext();
            nextNewNode.clearPrev();
        }
        size++;
        return newNode;
    }

    public DoublyLinkedListNode delete(int index)
    {
        /*
         * Deletes and returns the node at the index specified
         * Returns null if the index does not exist
         */
        if (index < 0 || index >= size)
        {
            return null;
        }
        DoublyLinkedListNode delNode = getNode(index);
        if (size == 1)
        {
            delNode = head;
            head = null;
            tail = null;
        }
        else if (index == 0)
        {
            head = head.getNext();
            delNode.clearPrev();
            delNode.clearNext();
            head.setPrev(null);
        }
        else if (index == size - 1)
        {
            tail = tail.getPrev();
            delNode.clearPrev();
            delNode.clearNext();
            tail.setNext(null);
        }
        else
        {
            DoublyLinkedListNode prevDelNode = delNode.getPrev();
            DoublyLinkedListNode nextDelNode = delNode.getNext();
            prevDelNode.setNext(nextDelNode);
            nextDelNode.setPrev(prevDelNode);
            delNode.clearPrev();
            delNode.clearNext();
        }
        size--;
        return delNode;
    }

    public DoublyLinkedListNode deleteItem(char data)
    {
        /*
         * Deletes and returns the first node with the specified data in the linked list
         * Returns null if the item doesn't exist
         */
        return delete(getIndex(data));
    }

    public boolean contains(char data)
    {
        /*
         * Checks if the linked list contains a node with the specified data
         */
        return !(getIndex(data) == -1);
    }

    public int getSize()
    {
        /*
         * Returns the number of elements in the linked list
         */
        return size;
    }

    public DoublyLinkedListNode getHead()
    {
        /*
         * Returns the head of the linked list
         */
        return head;
    }

    public DoublyLinkedListNode getTail()
    {
        /*
         * Returns the tail of the linked list
         */
        return tail;
    }

    public int getIndex(char data)
    {
        /*
         * Returns the index of the first node with the specified data
         * Returns -1 if the index does not exist
         */
        for (int i = 0; i < size; i++)
        {
            if (getNode(i).getData() == data)
            {
                return i;
            }
        }
        return -1;
    }

    public DoublyLinkedListNode getNode(int index)
    {
        /*
         * Returns the node at the specified index
         * Returns null if the index does not exist
         */
        if (index < 0 || index >= size)
        {
            return null;
        }
        DoublyLinkedListNode curNode = head;
        for (int i = 0; i < index; i++)
            curNode = curNode.getNext();
        return curNode;
    }

    public boolean isEmpty()
    {
        /*
         * Returns whether or not the linked list is empty
         */
        return size == 0;
    }

    public void clear()
    {
        /*
         * Clears the linked list
         */
        int initialSize = size;
        for (int i = 0; i < initialSize; i++)
            delete(0);
    }

    public String toString()
    {
        /*
         * Returns the linked list in string form
         * The format is just the data from each node concatenated together
         * See the tests for an example
         * There should be no trailing whitespace
         */
        StringBuilder sb = new StringBuilder();
        DoublyLinkedListNode curNode = head;
        for(int i=0;i<size;i++)
        {
            sb.append(curNode.getData());
            curNode = curNode.getNext();
        }
        return sb.toString();
    }

    public String toStringReverse()
    {
        /*
         * Returns the linked list in string form in reverse
         * The format is just the data from each node concatenated together
         * There should be no trailing whitespace
         * Do not just call toString and reverse it, this will receive no points
         */
        StringBuilder sb = new StringBuilder();
        DoublyLinkedListNode curNode = tail;
        for(int i=0;i<size;i++)
        {
            sb.append(curNode.getData());
            curNode = curNode.getPrev();
        }
        return sb.toString();
    }
}

class DoublyLinkedListNode
{
    /*
     * Our instance variables
     * data will hold a char
     * next is the reference to the next element after this node (null if there is none)
     */

    private char data;
    private DoublyLinkedListNode next;
    private DoublyLinkedListNode prev;

    public DoublyLinkedListNode(char data)
    {
        /*
         * The constructor
         * Should initalize the instance variables to their default value
         */
        this.data = data;
        next = null;
        prev = null;
    }

    public char getData()
    {
        /*
         * Returns our data
         */
        return data;
    }

    public DoublyLinkedListNode getNext()
    {
        /*
         * Returns the DoublyLinkedListNode referenced by next
         */
        return next;
    }

    public DoublyLinkedListNode getPrev()
    {
        /*
         * Returns the DoublyLinkedListNode referenced by prev
         */
        return prev;
    }

    public void setData(char data)
    {
        /*
         * Allows us to change the data stored in our DoublyLinkedListNode
         */
        this.data = data;
    }

    public void setNext(DoublyLinkedListNode node)
    {
        /*
         * Allows us to change the next DoublyLinkedListNode
         */
        next = node;
    }

    public void setPrev(DoublyLinkedListNode node)
    {
        /*
         * Allows us to change the prev DoublyLinkedListNode
         */
        prev = node;
    }

    public void clearNext()
    {
        /*
         * Removes the reference to the next DoublyLinkedListNode, replacing it with null
         */
        next = null;
    }

    public void clearPrev()
    {
        /*
         * Removes the reference to the prev DoublyLinkedListNode, replacing it with null
         */
        prev = null;
    }

    public String toString()
    {
        /*
         * Returns our data in string form
         */
        return String.valueOf(data);
    }
}
