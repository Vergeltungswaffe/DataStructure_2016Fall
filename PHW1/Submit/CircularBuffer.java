/*
 * Name: Kim, Jin Seol
 * Student ID: 2011133024
 */

public class CircularBuffer
{
    /*
     * Our instance variables
     * size will hold the number of elements in the buffer
     * capacity will hold the max number of elements the buffer can have
     * head references the head node in the buffer
     */

    private int size;
    private int capacity;
    private CircularBufferNode head;
    private CircularBufferNode tail;
    private boolean overflow_flag;

    public CircularBuffer(int capacity)
    {
        /*
         * Our constructor
         * Should initalize the instance variables to their default value
         * Since it is empty at the start, head should be null, and overflow_flag should be false
         */
        size = 0;
        this.capacity = capacity;
        head = null;
        tail = null;
        overflow_flag = false;
    }

    public CircularBufferNode add(char data)
    {
        /*
         * Adds and returns a new node with the provided data to the end of the buffer
         */
        if (!overflow_flag)
        {
            CircularBufferNode newNode = new CircularBufferNode(data);
            if (size == 0)
            {
                head = newNode;
                tail = newNode;
                head.setNext(tail);
                tail.setNext(head);
            }
            else
            {
                tail.setNext(newNode);
                newNode.setNext(head);
                tail = newNode;
            }
            size++;
            overflow_flag = (size == capacity);
            return newNode;
        }
        else
        {
            head.setData(data);
            head = head.getNext();
            tail = tail.getNext();
            return tail;
        }
    }

    public CircularBufferNode delete()
    {
        /*
         * Deletes and returns the node at the front of the buffer
         */
        if (size == 0)
        {
            return null;
        }
        CircularBufferNode delNode = head;
        if (size == 1)
        {
            delNode = head;
            head = null;
            tail = null;
        }
        else
        {
            tail.setNext(head.getNext());
            head.clearNext();
            head = tail.getNext();
        }
        size--;
        overflow_flag = (size == capacity);
        return delNode;
    }

    public boolean contains(char data)
    {
        /*
         * Checks if the buffer contains a node with the specified data
         */
        return !(getIndex(data) == -1);
    }

    public int getSize()
    {
        /*
         * Returns the number of elements in the buffer
         */
        return size;
    }

    public int getCapacity()
    {
        /*
         * Returns the capacity of the buffer
         */
        return capacity;
    }

    public CircularBufferNode getHead()
    {
        /*
         * Returns the head of the buffer
         */
        return head;
    }

    public CircularBufferNode getTail()
    {
        /*
         * Returns the tail of the buffer
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

    public CircularBufferNode getNode(int index)
    {
        /*
         * Returns the node at the specified index
         * Returns null if the index does not exist
         */
        if (index < 0 || index >= size)
        {
            return null;
        }
        CircularBufferNode curNode = head;
        for (int i = 0; i < index; i++)
        {
            curNode = curNode.getNext();
        }
        return curNode;
    }

    public boolean isEmpty()
    {
        /*
         * Returns whether or not the buffer is empty
         */
        return size == 0;
    }

    public boolean overflow()
    {
        /*
         * Returns whether or not previous operation caused an overflow
         */
        return overflow_flag;
    }

    public void clear()
    {
        /*
         * Clears the buffer
         */
        int initialSize = size;
        for (int i = 0; i < initialSize; i++)
        {
            delete();
        }
    }

    public String toString()
    {
        /*
         * Returns the buffer in string form
         * The format is just the data from each node concatenated together
         * See the tests for an example
         * There should be no trailing whitespace
         */
        StringBuilder sb = new StringBuilder();
        CircularBufferNode curNode = head;
        for(int i=0;i<size;i++)
        {
            sb.append(curNode.getData());
            curNode = curNode.getNext();
        }
        return sb.toString();
    }
}

class CircularBufferNode
{
    /*
     * Our instance variables
     * data will hold a char
     * next is the reference to the next element after this node (null if there is none)
     */

    private char data;
    private CircularBufferNode next;

    public CircularBufferNode(char data)
    {
        /*
         * The constructor
         * Should initalize the instance variables to their default value
         */
        this.data = data;
        next = null;
    }

    public char getData()
    {
        /*
         * Returns our data
         */
        return data;
    }

    public CircularBufferNode getNext()
    {
        /*
         * Returns the CircularBufferNode referenced by next
         */
        return next;
    }

    public void setData(char data)
    {
        /*
         * Allows us to change the data stored in our CircularBufferNode
         */
        this.data = data;
    }

    public void setNext(CircularBufferNode node)
    {
        /*
         * Allows us to change the next CircularBufferNode
         */
        next = node;
    }

    public void clearNext()
    {
        /*
         * Removes the reference to the next CircularBufferNode, replacing it with null
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
