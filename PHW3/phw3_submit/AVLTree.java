/*
 * Name: Kim, Jin Seol
 * Student ID: 2011133024
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
        root = null;
        size = 0;
    }

    public AVLTreeNode insert(int data)
    {
        /*
         * Constructs a new AVLTreeNode and inserts it into our AVLTree
         *
         * return the inserted AVLTreeNode or null if a node with the same data
         * already exists
         */
        AVLTreeNode newNode = new AVLTreeNode(data);
        if (size == 0)
        {
            root = newNode;
            size++;
            return newNode;
        }
        AVLTreeNode curNode = treesearch(root, data);
        if (curNode.getData() == data)
            return null;
        else if (curNode.getData() > data)
        {
            curNode.setLeft(newNode);
            size++;
            rebalance(curNode);
        }
        else
        {
            curNode.setRight(newNode);
            size++;
            rebalance(curNode);
        }
        return newNode;
    }

    public AVLTreeNode retrieve(int data)
    {
        /*
         * returns the node in the tree containing the desired data
         *
         * return null if it is not in the tree
         */
        AVLTreeNode node = treesearch(root, data);
        return (node.getData() == data) ? node : null;
    }

    public boolean contains(int data)
    {
        /*
         * return whether or not the tree contains a node with the desired data
         */
        return retrieve(data) != null;
    }

    public AVLTreeNode delete(int data)
    {
        /*
         * remove and return the AVLTreeNode with the desired data
         *
         * return null if the data is not in the AVLTree
         */
        AVLTreeNode curNode = treesearch(root, data);
        AVLTreeNode delNode = null;
        if (curNode.getData() > data)
        {
            if (curNode.getLeft() == null)
                return null;
            else
                delNode = curNode.getLeft();
        }
        else if (curNode.getData() < data)
        {
            if (curNode.getRight() == null)
                return null;
            else
                delNode = curNode.getLeft();
        }
        else
        {
            delNode = curNode;
        }


        if (isExternal(delNode))// delNode is External
        {
            if (delNode == root)
            {
                root = null;
                size--;
                return delNode;
            }
            AVLTreeNode subRoot = parent(delNode);
            if (subRoot.getRight() == delNode)
                subRoot.clearRight();
            else
                subRoot.clearLeft();
            rebalance(subRoot);
            size--;
        }
        else if ((delNode.getRight() == null) != (delNode.getLeft() == null))//delNode has one child
        {
            AVLTreeNode childOfDel = delNode.getLeft() == null ? delNode.getRight() : delNode.getLeft();
            if (delNode == root)
            {
                root = childOfDel;
                size--;
                return delNode;
            }
            if (parent(delNode).getLeft() == delNode)
                parent(delNode).setLeft(childOfDel);
            else
                parent(delNode).setRight(childOfDel);
            rebalance(parent(childOfDel));
            size--;
        }
        else //delnode has two child
        {
            AVLTreeNode newSubRoot;
            AVLTreeNode rightMost = delNode.getLeft();
            while (rightMost.getRight() != null)
            {
                rightMost = rightMost.getRight();
            }
            newSubRoot = delete(rightMost.getData());
            if (delNode == root)
            {
                if (root.getLeft() == null)
                {
                    newSubRoot.clearLeft();
                    newSubRoot.setRight(root.getRight());
                }
                else
                    newSubRoot.setLeft(root.getLeft());
                if (root.getRight() == null)
                {
                    newSubRoot.clearRight();
                    newSubRoot.setLeft(root.getLeft());
                }
                else
                    newSubRoot.setRight(root.getRight());
                root = rightMost;
                rebalance(root);
                return delNode;
            }
            if (parent(delNode).getLeft() == delNode)
                parent(delNode).setLeft(newSubRoot);
            else
                parent(delNode).setRight(newSubRoot);
            if (root.getLeft() == newSubRoot)
                newSubRoot.clearLeft();
            else
                newSubRoot.setLeft(delNode.getLeft());
            if (root.getRight() == newSubRoot)
                newSubRoot.clearRight();
            else
                newSubRoot.setRight(delNode.getRight());
            rebalance(newSubRoot);
        }
        return delNode;
    }

    public AVLTreeNode leftRotate(AVLTreeNode node)
    {
        /*
         * Perform a left rotate on the subtree rooted at node
         *
         * return the new root of the subtree
         */
        AVLTreeNode x = node.getRight();
        AVLTreeNode y = node;
        AVLTreeNode z = parent(y);
        if (z == null)
        {
            root = x;
        }
        else
        {
            if (y == z.getLeft())
                relinkLeft(z, x);
            else
                relinkRight(z, x);
        }
        relinkRight(y, x.getLeft());
        relinkLeft(x, y);
        return x;
    }

    public AVLTreeNode rightRotate(AVLTreeNode node)
    {
        /*
         * Perform a right rotate on the subtree rooted at node
         *
         * return the new root of the subtree
         */
        AVLTreeNode x = node.getLeft();
        AVLTreeNode y = node;
        AVLTreeNode z = parent(y);
        if (z == null)
        {
            root = x;
        }
        else
        {
            if (y == z.getLeft())
                relinkLeft(z, x);
            else
                relinkRight(z, x);
        }
        relinkLeft(y, x.getRight());
        relinkRight(x, y);
        return x;
    }

    public AVLTreeNode[] preorder()
    {
        /*
         * return an array of AVLTreeNodes in preorder
         */
        orderOutput = new AVLTreeNode[size];
        preorder(root);
        orderCur = 0;
        return orderOutput;
    }

    public AVLTreeNode[] postorder()
    {
        /*
         * return an array of AVLTreeNodes in postorder
         */
        orderOutput = new AVLTreeNode[size];
        postorder(root);
        orderCur = 0;
        return orderOutput;
    }

    public AVLTreeNode[] inorder()
    {
        /*
         * return an array of AVLTreeNodes in inorder
         */
        orderOutput = new AVLTreeNode[size];
        inorder(root);
        orderCur = 0;
        return orderOutput;
    }

    public void clear()
    {
        /*
         * clear the AVLTree
         */
        root = null;
        size = 0;
    }

    public boolean isEmpty()
    {
        /*
         * return whether or not the AVLTree is empty
         */
        return size == 0;
    }

    public AVLTreeNode getRoot()
    {
        /*
         * return the root of the AVLTree
         */
        return root;
    }

    public int getHeight()
    {
        /*
         * return the height of the AVLTree
         */
        return root.getHeight();
    }

    public String toString()
    {
        /*
         * return a string representation of the AVLTree.
         *
         * The format is the elements of the tree in preorder, each separated
         * by a comma. There should be no spaces and no trailing commas.
         */
        String output = "";
        AVLTreeNode[] preorderArray = preorder();
        for (int i = 0; i < size; i++)
        {
            output = output.concat(preorderArray[i].toString() + ",");
        }
        orderCur = 0;
        if (output.length() == 0)
            return output;
        return output.substring(0, output.length() - 1);
    }

    //////////////////////////////
    // Helper variable, methods //
    //////////////////////////////
    private AVLTreeNode parent(AVLTreeNode n)
    {
        if (n == root)
            return null;
        AVLTreeNode curNode = root;
        while (!(curNode.getLeft() == n || curNode.getRight() == n))
        {
            if (n.getData() < curNode.getData())
            {
                curNode = curNode.getLeft();
            }
            else if (n.getData() > curNode.getData())
            {
                curNode = curNode.getRight();
            }
        }
        return curNode;
    }

    private boolean isExternal(AVLTreeNode n)
    {
        return (n.getLeft() == null && n.getRight() == null);
    }

    private AVLTreeNode treesearch(AVLTreeNode n, int data)
    {
        if (isExternal(n))
            return n;
        if (data == n.getData())
            return n;
        else if (data < n.getData())
        {
            if (n.getLeft() == null)
                return n;
            return treesearch(n.getLeft(), data);
        }
        else
        {
            if (n.getRight() == null)
                return n;
            return treesearch(n.getRight(), data);
        }

    }

    private void relinkLeft(AVLTreeNode parent, AVLTreeNode child)
    {
        parent.setLeft(child);
    }

    private void relinkRight(AVLTreeNode parent, AVLTreeNode child)
    {
        parent.setRight(child);
    }

    private AVLTreeNode restructure(AVLTreeNode x)
    {
        AVLTreeNode y = parent(x);
        AVLTreeNode z = parent(y);
        if ((x == y.getRight()) == (y == z.getRight()))
        {
            if ((x == y.getRight()))
                leftRotate(z);
            else
                rightRotate(z);
            if (root == z)
                root = y;
            return y;
        }
        else
        {
            if ((x == y.getRight()))
            {
                z.setLeft(leftRotate(y));
                rightRotate(z);
            }
            else
            {
                z.setRight(rightRotate(y));
                leftRotate(z);
            }
            if (root == z)
                root = x;
            return x;
        }
    }

    private AVLTreeNode tallerChild(AVLTreeNode n)
    {
        if (n.getBalanceFactor() < 0)
            return n.getLeft();
        if (n.getBalanceFactor() > 0)
            return n.getRight();
        if (root == n)
        {
            if (n.getLeft() == null)
                return null;
            return n.getLeft();
        }
        if (n == parent(n).getLeft())
            return n.getLeft();
        else
            return n.getRight();
    }

    private boolean isBalanced(AVLTreeNode n)
    {
        int leftHeight, rightHeight;
        if (n.getLeft() == null)
            leftHeight = -1;
        else
            leftHeight = n.getLeft().getHeight();
        if (n.getRight() == null)
            rightHeight = -1;
        else
            rightHeight = n.getRight().getHeight();
        return Math.abs(rightHeight - leftHeight) <= 1;
    }

    private void rebalance(AVLTreeNode n)
    {
        int oldHeight, newHeight;
        do
        {
            oldHeight = n.getHeight();
            if (!isBalanced(n))
            {
                n = restructure(tallerChild(tallerChild(n)));
                recomputeHeight(n.getLeft());
                recomputeBalanceFactor(n.getLeft());
                recomputeHeight(n.getRight());
                recomputeBalanceFactor(n.getRight());
            }
            recomputeHeight(n);
            recomputeBalanceFactor(n);
            newHeight = n.getHeight();
            n = parent(n);
        } while (oldHeight != newHeight && n != null);
    }

    private void recomputeHeight(AVLTreeNode n)
    {
        if (isExternal(n))
        {
            n.setHeight(0);
            return;
        }
        if ((n.getLeft() == null) != (n.getRight() == null))
        {
            if (n.getLeft() == null)
                n.setHeight(n.getRight().getHeight() + 1);
            else
                n.setHeight(n.getLeft().getHeight() + 1);
            return;
        }
        n.setHeight(Math.max(n.getLeft().getHeight(), n.getRight().getHeight()) + 1);
    }

    private void recomputeBalanceFactor(AVLTreeNode n)
    {
        int leftHeight, rightHeight;
        if (n.getLeft() == null)
            leftHeight = -1;
        else
            leftHeight = n.getLeft().getHeight();
        if (n.getRight() == null)
            rightHeight = -1;
        else
            rightHeight = n.getRight().getHeight();
        n.setBalanceFactor(rightHeight - leftHeight);
    }

    private AVLTreeNode[] orderOutput;
    private int orderCur = 0;

    private void preorder(AVLTreeNode n)
    {
        if (n == null)
            return;
        orderOutput[orderCur++] = n;
        preorder(n.getLeft());
        preorder(n.getRight());
    }

    private void postorder(AVLTreeNode n)
    {
        if (n == null)
            return;
        postorder(n.getLeft());
        postorder(n.getRight());
        orderOutput[orderCur++] = n;
    }

    private void inorder(AVLTreeNode n)
    {
        if (n == null)
            return;
        if (!(n.getLeft() == null))
            inorder(n.getLeft());
        orderOutput[orderCur++] = n;
        if (!(n.getRight() == null))
            inorder(n.getRight());
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
        this.data = data;
        height = 0;
        balanceFactor = 0;
        left = null;
        right = null;
    }

    public void setData(int data)
    {
        /*
         * Set the value stored in data
         */
        this.data = data;
    }

    public void setHeight(int height)
    {
        /*
         * Set the value stored in height
         */
        this.height = height;
    }

    public void setBalanceFactor(int balanceFactor)
    {
        /*
         * Set the value stored in balanceFactor
         */
        this.balanceFactor = balanceFactor;
    }

    public void setLeft(AVLTreeNode left)
    {
        /*
         * Set the left child
         */
        this.left = left;
    }

    public void setRight(AVLTreeNode right)
    {
        /*
         * Set the right child
         */
        this.right = right;
    }

    public void clearLeft()
    {
        /*
         * clear the left child
         */
        left = null;
    }

    public void clearRight()
    {
        /*
         * clear the right child
         */
        right = null;
    }

    public int getData()
    {
        /*
         * get the data stored in the AVLTreeNode
         */
        return data;
    }

    public int getHeight()
    {
        /*
         * get the height of the AVLTreeNode
         */
        return height;
    }

    public int getBalanceFactor()
    {
        /*
         * get the balanceFactor of the AVLTreeNode
         */
        return balanceFactor;
    }

    public AVLTreeNode getLeft()
    {
        /*
         * get the left child
         */
        return left;
    }

    public AVLTreeNode getRight()
    {
        /*
         * get the right child
         */
        return right;
    }

    public String toString()
    {
        /*
         * return the string value of the data stored in the node
         */
        return Integer.toString(data);
    }
}
