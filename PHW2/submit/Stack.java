/*
 * Name: Kim, Jin Seol
 * Student ID: 2011133024
 */

public class Stack
{
    /*
     * Our instance variables
     * capacity is the maximum number of elements allowed in the stack
     * ll is the (singly) linked list you must use to build your stack
     */

    private int capacity;
    private SinglyLinkedList ll;

    public Stack(int capacity)
    {
        /*
         * Our constructor
         * Should initalize the instance variables to their default value
         */
        ll = new SinglyLinkedList();
        this.capacity = capacity;
    }

    public int push(int data)
    {
        /*
         * Push the data to the top of the stack
         * Return the data
         * If the stack is full, return -1
         */
        if (ll.getSize() == capacity)
        {
            return -1;
        }
        ll.add(data, 0);
        return data;
    }

    public int peek()
    {
        /*
         * Peek from the data at the top of the stack
         * If the stack is empty, return -1
         */
        if (ll.isEmpty())
        {
            return -1;
        }
        return ll.getHead().getData();
    }

    public int pop()
    {
        /*
         * Pop the data at the top of the stack
         * If the stack is empty, return -1
         */
        if (ll.isEmpty())
        {
            return -1;
        }
        return ll.delete(0).getData();
    }

    public void clear()
    {
        /*
         * Clear the stack
         */
        while (ll.getSize() != 0)
        {
            pop();
        }
    }

    public int getSize()
    {
        /*
         * Return the number of elements in the stack
         */
        return ll.getSize();
    }

    public int getCapacity()
    {
        /*
         * Return the capacity of the stack
         */
        return capacity;
    }

    public boolean isFull()
    {
        /*
         * Return whether or not the stack is full
         */
        return ll.getSize() == capacity;
    }

    public boolean isEmpty()
    {
        /*
         * Return whether or not the stack is empty
         */
        return ll.isEmpty();
    }

    public String toString()
    {
        /*
         * Return the string representation of the stack
         * The string should be in order from the top of the stack down with
         * commas between each element
         * There should be no spaces between elements or commas
         * This should not alter the stack in any way
         */
        String output = "";
        for (int i = 0; i < ll.getSize() - 1; i++)
        {
            output += (ll.getNode(i).toString() + ",");
        }
        output += ll.getNode(ll.getSize() - 1).toString();
        return output;
    }
}
