/*
 * Name: Kim, Jin Seol
 * Student ID: 2011133024
 */

public class Solver
{
    /*
     * Our instance variables
     * left, middle, and right are the three stacks you can use to solve this
     * problem
     * Note that left and right have the same capacity
     */

    private Stack left, middle, right;
    private int[] data;

    public Solver(int leftRightCapacity, int middleCapacity, int[] data)
    {
        /*
         * Our constructor for the solver
         * leftRightCapacity is the capacity for the left and right stacks
         * middleCapacity is the capacity for the middle stack
         * data holds the permutation of integers that we want to reach at the end
         * the first element in data is what we want at the bottom of the right stack
         * the last element in data is what we want at the top of the right stack
         * Initialize the three stacks here and push the integers from 0 to N-1
         * into the left stack (as described in the handout)
         */
        left = new Stack(leftRightCapacity);
        middle = new Stack(middleCapacity);
        right = new Stack(leftRightCapacity);
        this.data = data;
        for (int i = leftRightCapacity - 1; i >= 0; i--)
        {
            left.push(i);
        }
    }

    public String solve()
    {
        /*
         * The solver method
         * Using the three stacks and the data, return the result of the problem
         * described in the handout
         */
        boolean isOverflow = false;
        for (int i = 0; i < data.length; i++)
        {
            if (data[i] > right.peek())
            {
                for (int j = left.peek(); j <= data[i]; j++)
                {
                    if (middle.isFull())
                    {
                        middleExpansion();
                        isOverflow = true;
                    }
                    operation1();
                }
            }
            if (data[i] == middle.peek())
            {
                operation2();
            }
            else
            {
                return "NO";
            }
        }
        if (isOverflow)
            return "OVERFLOW";
        return "YES";
    }

    public void operation1()
    {
        /*
         * Implement operation 1 as defined in the handout
         */
        if (left.isEmpty() || middle.isFull())
        {
            return;
        }
        middle.push(left.pop());
    }

    public void operation2()
    {
        /*
         * Implement operation 2 as defined in the handout
         */
        if (middle.isEmpty())
        {
            return;
        }
        right.push(middle.pop());
    }

    public Stack getLeft()
    {
        /*
         * Return the left stack
         */
        return left;
    }

    public Stack getMiddle()
    {
        /*
         * Return the middle stack
         */
        return middle;
    }

    public Stack getRight()
    {
        /*
         * Return the right stack
         */
        return right;
    }

    // help func
    private void middleExpansion()
    {
        Stack midTemp = new Stack(left.getCapacity());
        Stack midTempTemp = new Stack(left.getCapacity());
        while (!middle.isEmpty())
        {
            midTemp.push(middle.pop());
        }
        while (!midTemp.isEmpty())
        {
            midTempTemp.push(midTemp.pop());
        }
        this.middle = midTempTemp;
    }

}
