/*
 * Name:
 * Student ID:
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
    }

    public int push(int data)
    {
        /*
         * Push the data to the top of the stack
         * Return the data
         * If the stack is full, return -1
         */
    }

    public int peek()
    {
        /*
         * Peek from the data at the top of the stack
         * If the stack is empty, return -1
         */
    }

    public int pop()
    {
        /*
         * Pop the data at the top of the stack
         * If the stack is empty, return -1
         */
    }

    public void clear()
    {
        /*
         * Clear the stack
         */
    }

    public int getSize()
    {
        /*
         * Return the number of elements in the stack
         */
    }

    public int getCapacity()
    {
        /*
         * Return the capacity of the stack
         */
    }

    public boolean isFull()
    {
        /*
         * Return whether or not the stack is full
         */
    }

    public boolean isEmpty()
    {
        /*
         * Return whether or not the stack is empty
         */
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
    }
}