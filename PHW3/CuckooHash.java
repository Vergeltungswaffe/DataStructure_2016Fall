/* 
 * Name:
 * Student ID:
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
    }

    public int delete(int data)
    {
        /*
         * delete data from our CuckooHash
         *
         * return the deleted value or -1 if it is not in the CuckooHash
         */
    }

    public boolean contains(int data)
    {
        /*
         * checks containment
         *
         * return true if data is in the CuckooHash
         */
    }

    public int hash1(int x)
    {
        /*
         * Our first hash function
         * Remember, hashes are defined as (a,b,N) = ax+b (mod N)
         *
         * return the value computed by the first hash function
         */
    }

    public int hash2(int x)
    {
        /*
         * Our second hash function
         * Remember, hashes are defined as (a,b,N) = ax+b (mod N)
         *
         * return the value computed by the second hash function
         */
    }

    public void resize(int a1, int b1, int a2, int b2)
    {
        /*
         * resize our CuckooHash and make new hash functions
         */
    }

    public void setA1(int a1)
    {
        /*
         * set the value a1
         */
    }

    public void setB1(int b1)
    {
        /*
         * set the value b1
         */
    }

    public void setA2(int a2)
    {
        /*
         * set the value a2
         */
    }

    public void setB2(int b2)
    {
        /*
         * set the value b2
         */
    }

    public void setThreshold(double t)
    {
        /*
         * set the threshold
         */
    }

    public void setChainLength(int c)
    {
        /*
         * set the chainLength
         */
    }

    public int getElementArray1(int index)
    {
        /*
         * return element at index from array1
         */
    }

    public int getElementArray2(int index)
    {
        /*
         * return element at index from array2
         */
    }

    public int getA1()
    {
        /*
         * return a1
         */
    }

    public int getB1()
    {
        /*
         * return b1
         */
    }

    public int getA2()
    {
        /*
         * return a2
         */   
    }

    public int getB2()
    {
        /*
         * return b2
         */        
    }

    public int getN()
    {
        /*
         * return n
         */
    }

    public double getThreshold()
    {
        /*
         * return threshold
         */
    }

    public int getChainLength()
    {
        /*
         * return chainLength
         */
    }

    public int[] getArray1()
    {
        /*
         * return array1
         */
    }

    public int[] getArray2()
    {
        /*
         * return array2
         */
    }

    public int getNumElements()
    {
        /*
         * return the number of elements in the CuckooHash
         */
    }

    public ArrayList<Integer> getElements()
    {
        /*
         * return all of the elements that are in the CuckooHash in their
         * inserted order
         */
    }

    public boolean getResized()
    {
        /*
         * return the resized variable
         */
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
    }
}