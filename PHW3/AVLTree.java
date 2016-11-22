/* 
 * Name:
 * Student ID:
 * Don't forget to remove the package line.
 */

public class AVLTree
{
    private AVLTreeNode root;
    private int size;

    /* 
     * Our instance variables.
     *
     * root - AVLTreeNode, root of our AVLTree
     * size - int, the number of elements in our AVLTree
     */

    public AVLTree()
    {
        /*
         * Our constructor. 
         * Initialize the instance variables to their default values
         */
    }

    public AVLTreeNode insert(int data)
    {
        /*
         * Constructs a new AVLTreeNode and inserts it into our AVLTree
         *
         * return the inserted AVLTreeNode or null if a node with the same data
         * already exists
         */
    }

    public AVLTreeNode retrieve(int data)
    {
        /*
         * returns the node in the tree containing the desired data
         *
         * return null if it is not in the tree
         */
    }

    public boolean contains(int data)
    {
        /*
         * return whether or not the tree contains a node with the desired data
         */
    }

    public AVLTreeNode delete(int data)
    {
        /*
         * remove and return the AVLTreeNode with the desired data
         *
         * return null if the data is not in the AVLTree
         */
    }

    public AVLTreeNode leftRotate(AVLTreeNode node)
    {
        /*
         * Perform a left rotate on the subtree rooted at node
         *
         * return the new root of the subtree
         */
    }

    public AVLTreeNode rightRotate(AVLTreeNode node)
    {
        /*
         * Perform a right rotate on the subtree rooted at node
         *
         * return the new root of the subtree
         */
    }

    public AVLTreeNode[] preorder()
    {
        /*
         * return an array of AVLTreeNodes in preorder
         */
    }

    public AVLTreeNode[] postorder()
    {
        /*
         * return an array of AVLTreeNodes in postorder
         */
    }

    public AVLTreeNode[] inorder()
    {
        /*
         * return an array of AVLTreeNodes in inorder
         */
    }

    public void clear()
    {
        /*
         * clear the AVLTree
         */
    }

    public boolean isEmpty()
    {
        /*
         * return whether or not the AVLTree is empty
         */
    }

    public AVLTreeNode getRoot()
    {
        /*
         * return the root of the AVLTree
         */
    }

    public int getHeight()
    {
        /*
         * return the height of the AVLTree
         */
    }

    public String toString()
    {
        /*
         * return a string representation of the AVLTree.
         *
         * The format is the elements of the tree in preorder, each separated
         * by a comma. There should be no spaces and no trailing commas.
         */
    }
}

class AVLTreeNode
{
    private int data, height, balanceFactor;
    private AVLTreeNode left, right;

    /* 
     * Our instance variables.
     *
     * data - int, the data the AVLTreeNode will hold
     * height - int, the height of the AVLTreeNode
     * balanceFactor - int, the balance factor of the node
     * left - AVLTreeNode, the left child of the node
     * right - AVLTreeNode, the right child of the node
     */

    public AVLTreeNode(int data)
    {
        /*
         * Our constructor. 
         * Initialize the instance variables to their default values or the
         * values passed as paramters
         */
    }

    public void setData(int data)
    {
        /*
         * Set the value stored in data
         */
    }

    public void setHeight(int height)
    {
        /*
         * Set the value stored in height
         */
    }

    public void setBalanceFactor(int balanceFactor)
    {
        /*
         * Set the value stored in balanceFactor
         */
    }

    public void setLeft(AVLTreeNode left)
    {
        /*
         * Set the left child
         */
    }

    public void setRight(AVLTreeNode right)
    {
        /*
         * Set the right child
         */
    }

    public void clearLeft()
    {
        /*
         * clear the left child
         */
    }

    public void clearRight()
    {
        /*
         * clear the right child
         */
    }

    public int getData()
    {
        /*
         * get the data stored in the AVLTreeNode
         */
    }

    public int getHeight()
    {
        /*
         * get the height of the AVLTreeNode
         */
    }

    public int getBalanceFactor()
    {
        /*
         * get the balanceFactor of the AVLTreeNode
         */
    }

    public AVLTreeNode getLeft()
    {
        /*
         * get the left child
         */
    }

    public AVLTreeNode getRight()
    {
        /*
         * get the right child
         */
    }

    public String toString()
    {
        /*
         * return the string value of the data stored in the node
         */
    }
}