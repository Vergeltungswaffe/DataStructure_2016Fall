/*
 * Name: Kim, Jin Seol
 * Student ID: 2011133024
 * Don't forget to remove the package line.
 */

import java.util.ArrayList;
import java.util.Random;

/* 
 * java.util.ArrayList is for the ArrayList
 * java.util.Random is so you can generate your own hash functions
 * You should not import anything else
 */

public class CuckooHash
{
    private int a1, b1, a2, b2, n, numElements, chainLength;
    private double threshold;
    private int[] array1, array2;
    private boolean resized;
    private ArrayList<Integer> elements;

    /* 
     * Our instance variables.
     *
     * a1 - int, a in the first hash function
     * b1 - int, b in the first hash function
     * a2 - int, a in the second hash function
     * b2 - int, b in the second hash function
     * n - int, the initial size of each array
     * numElements - int, the number of elements that have been inserted
     * chainLength - int, the length of the allowed chain before we resize
     * threshold - double, the point at which our arrays are too full and we resize
     * array1 - int[], the first hash table
     * array2 - int[], the second hash table
     * resized - boolean, set to true if the previous insertion caused a resize
     *           and false otherwise
     */

    public CuckooHash(int a1, int b1, int a2, int b2, int n, int chainLength, double threshold)
    {
        /*
         * Our constructor. Initialize the instance variables to their default
         * value or the value passed as a parameter.
         *
         * array1, array2 should initially be filled with 0's
         */
        this.a1 = a1;
        this.b1 = b1;
        this.a2 = a2;
        this.b2 = b2;
        this.n = n;
        numElements = 0;
        this.chainLength = chainLength;
        this.threshold = threshold;
        array1 = new int[n];
        array2 = new int[n];
        resized = false;
        elements = new ArrayList<>();
    }

    public int insert(int data, int a1, int b1, int a2, int b2)
    {
        /*
         * insert data into our CuckooHash if it is not already contained
         * be sure to update resized if necessary
         *
         * a1, b1, a2, b2 are parameters used to specify the constants in the
         * new hash function if there is a resize operation.
         * 
         * return the value that is inserted or -1 if it already exists
         */
        resized = false;
        int numChain = 0;
        if (contains(data))
            return -1;
        int X = data;
        int indexX = hash1(data);
        while (true)
        {
            if (array1[indexX] == 0)
            {
                array1[indexX] = X;
                elements.add(X);
                numElements++;
                if ((double) numElements / (2 * n) >= threshold)
                {
                    resize(a1, b1, a2, b2);
                    resized = true;
                }
                break;
            }
            else
            {
                numChain++;
                if (chainLength == numChain)
                {
                    resize(a1, b1, a2, b2);
                    resized = true;
                    numChain = 0;
                }
                int Y = array1[indexX];
                int indexY = hash2(Y);
                array1[indexX] = X;
                if (array2[indexY] == 0)
                {
                    array2[indexY] = Y;
                    elements.add(Y);
                    numElements++;
                    if ((double) numElements / (2 * n) >= threshold)
                    {
                        resize(a1, b1, a2, b2);
                        resized = true;
                    }
                    break;
                }
                else
                {
                    numChain++;
                    if (chainLength == numChain)
                    {
                        resize(a1, b1, a2, b2);
                        resized = true;
                        numChain = 0;
                    }
                    X = array2[indexY];
                    indexX = hash1(X);
                    array2[indexY] = Y;
                }
            }
        }
        return data;
    }

    public int delete(int data)
    {
        /*
         * delete data from our CuckooHash
         *
         * return the deleted value or -1 if it is not in the CuckooHash
         */
        for (int i = 0; i < array1.length; i++)
        {
            if (array1[i] == data)
            {
                array1[i] = 0;
            }
            if (array2[i] == data)
            {
                array2[i] = 0;
            }
        }
        return elements.remove((Integer) data) ? data : -1;
    }

    public boolean contains(int data)
    {
        /*
         * checks containment
         *
         * return true if data is in the CuckooHash
         */
        return elements.contains(data);
    }

    public int hash1(int x)
    {
        /*
         * Our first hash function
         * Remember, hashes are defined as (a,b,N) = ax+b (mod N)
         *
         * return the value computed by the first hash function
         */
        return (a1 * x + b1) % n;
    }

    public int hash2(int x)
    {
        /*
         * Our second hash function
         * Remember, hashes are defined as (a,b,N) = ax+b (mod N)
         *
         * return the value computed by the second hash function
         */
        return (a2 * x + b2) % n;
    }

    public void resize(int a1, int b1, int a2, int b2)
    {
        /*
         * resize our CuckooHash and make new hash functions
         */
        n *= 2;
        setA1(a1);
        setB1(b1);
        setA2(a2);
        setB2(b2);
        array1 = new int[n];
        array2 = new int[n];
        ArrayList<Integer> oldElements = elements;
        elements = new ArrayList<>();
        numElements = 0;
        for (int i = 0; i < oldElements.size(); i++)
        {
            insert(oldElements.get(i), a1, b1, a2, b2);
        }
    }

    public void setA1(int a1)
    {
        /*
         * set the value a1
         */
        this.a1 = a1;
    }

    public void setB1(int b1)
    {
        /*
         * set the value b1
         */
        this.b1 = b1;
    }

    public void setA2(int a2)
    {
        /*
         * set the value a2
         */
        this.a2 = a2;
    }

    public void setB2(int b2)
    {
        /*
         * set the value b2
         */
        this.b2 = b2;
    }

    public void setThreshold(double t)
    {
        /*
         * set the threshold
         */
        this.threshold = t;
    }

    public void setChainLength(int c)
    {
        /*
         * set the chainLength
         */
        this.chainLength = c;
    }

    public int getElementArray1(int index)
    {
        /*
         * return element at index from array1
         */
        return array1[index];
    }

    public int getElementArray2(int index)
    {
        /*
         * return element at index from array2
         */
        return array2[index];
    }

    public int getA1()
    {
        /*
         * return a1
         */
        return a1;
    }

    public int getB1()
    {
        /*
         * return b1
         */
        return b1;
    }

    public int getA2()
    {
        /*
         * return a2
         */
        return a2;
    }

    public int getB2()
    {
        /*
         * return b2
         */
        return b2;
    }

    public int getN()
    {
        /*
         * return n
         */
        return n;
    }

    public double getThreshold()
    {
        /*
         * return threshold
         */
        return threshold;
    }

    public int getChainLength()
    {
        /*
         * return chainLength
         */
        return chainLength;
    }

    public int[] getArray1()
    {
        /*
         * return array1
         */
        return array1;
    }

    public int[] getArray2()
    {
        /*
         * return array2
         */
        return array2;
    }

    public int getNumElements()
    {
        /*
         * return the number of elements in the CuckooHash
         */
        return numElements;
    }

    public ArrayList<Integer> getElements()
    {
        /*
         * return all of the elements that are in the CuckooHash in their
         * inserted order
         */
        return elements;
    }

    public boolean getResized()
    {
        /*
         * return the resized variable
         */
        return resized;
    }

    public String toString()
    {
        /*
         * return the string version of the CuckooHash
         *
         * the required format is: 
         * all values in array1 (including 0's) separated by commas followed by
         * a bar | followed by all values of array2 (including 0's) separated by
         * commas
         *
         * there should be no spaces or trailing commas
         */
        String output = "";
        for (int i = 0; i < array1.length - 1; i++)
        {
            output = output.concat(Integer.toString(array1[i]));
            output = output.concat(",");
        }
        output = output.concat(Integer.toString(array1[array1.length - 1]));
        output = output.concat("|");
        for (int i = 0; i < array2.length - 1; i++)
        {
            output = output.concat(Integer.toString(array2[i]));
            output = output.concat(",");
        }
        output = output.concat(Integer.toString(array2[array2.length - 1]));
        return output;
    }
}
