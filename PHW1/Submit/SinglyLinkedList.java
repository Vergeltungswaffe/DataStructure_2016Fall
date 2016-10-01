/*
 * Name: Kim, Jin Seol
 * Student ID: 2011133024
 */

public class SinglyLinkedList
{
    /*
     * Our instance variables
     * size will hold the number of elements in the linked list
     * head references the head node in the linked list
     */

    private int size;
    private SinglyLinkedListNode head;

    public SinglyLinkedList()
    {
        /*
         * Our constructor
         * Should initalize the instance variables to their default value
         * Since it is empty at the start, head should be null
         */
        size = 0;
        head = null;
    }

    public SinglyLinkedListNode add(char data, int index)
    {
        /*
         * Adds and returns a new node with the provided data at the specified index
         * Returns null if the index is not possible
         */
        if (index < 0 || index > size)
        {
            return null;
        }
        SinglyLinkedListNode addNode = new SinglyLinkedListNode(data);
        if (index == 0)
        {
            addNode.setNext(head);
            head = addNode;
        }
        else
        {
            SinglyLinkedListNode prevNewNode = getNode(index-1);
            SinglyLinkedListNode nextNewNode = prevNewNode.getNext();
            addNode.setNext(nextNewNode);
            prevNewNode.setNext(addNode);
        }
        size++;
        return addNode;
    }

    public SinglyLinkedListNode delete(int index)
    {
        /*
         * Deletes and returns the node at the index specified
         * Returns null if the index does not exist
         */
        if (index < 0 || index >= size)
        {
            return null;
        }
        SinglyLinkedListNode delNode = getNode(index);
        if (index == 0)
        {
            head = delNode.getNext();
            delNode.clearNext();
        }
        else
        {
            SinglyLinkedListNode prevDelNode = getNode(index-1);
            SinglyLinkedListNode nextDelNode = delNode.getNext();
            prevDelNode.setNext(nextDelNode);
            delNode.clearNext();
        }
        size--;
        return delNode;
    }

    public SinglyLinkedListNode deleteItem(char data)
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

    public SinglyLinkedListNode getHead()
    {
        /*
         * Returns the head of the linked list
         */
        return head;
    }

    public int getIndex(char data)
    {
        /*
         * Returns the index of the first node with the specified data
         * Returns -1 if the index does not exist
         */
        SinglyLinkedListNode curNode = head;
        for (int i = 0; i < size; i++)
        {
            if (curNode.getData() == data)
            {
                return i;
            }
            curNode = curNode.getNext();
        }
        return -1;
    }

    public SinglyLinkedListNode getNode(int index)
    {
        /*
         * Returns the node at the specified index
         * Returns null if the index does not exist
         */
        if (index < 0 || index >= size)
        {
            return null;
        }
        SinglyLinkedListNode curNode = head;
        for (int i = 0; i < index; i++)
        {
            curNode = curNode.getNext();
        }
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
        {
            delete(0);
        }
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
        SinglyLinkedListNode curNode = head;
        for(int i=0;i<size;i++)
        {
            sb.append(curNode.getData());
            curNode = curNode.getNext();
        }
        return sb.toString();
    }
}

class SinglyLinkedListNode
{
    /*
     * Our instance variables
     * data will hold a char
     * next is the reference to the next element after this node (null if there is none)
     */

    private char data;
    private SinglyLinkedListNode next;

    public SinglyLinkedListNode(char data)
    {
        /*
         * The constructor
         * Should initalize the instance variables to their default value
         */
        this.data = data;
        this.next = null;
    }

    public char getData()
    {
        /*
         * Returns our data
         */
        return data;
    }

    public SinglyLinkedListNode getNext()
    {
        /*
         * Returns the SinglyLinkedListNode referenced by next
         */
        return next;
    }

    public void setData(char data)
    {
        /*
         * Allows us to change the data stored in our SinglyLinkedListNode
         */
        this.data = data;
    }

    public void setNext(SinglyLinkedListNode node)
    {
        /*
         * Allows us to change the next SinglyLinkedListNode
         */
        next = node;
    }

    public void clearNext()
    {
        /*
         * Removes the reference to the next SinglyLinkedListNode, replacing it with null
         */
        next = null;
    }

    public String toString()
    {
        /*
         * Returns our data in string form
         */
        return String.valueOf(data);
    }
}
