/*
 * Name:
 * Student ID:
 * Don't forget to remove the package line (if it exists)
 */

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.ArrayList;

/*
 * Do not import anything else
 */

public class Graph
{

    private int numNodes, numEdges;
    
    /*
     * Our instance variables
     *
     * numNodes - int - holds the number of nodes currently in the graph
     * numEdges - int - holds the number of edges currently in the graph
     *
     * You may add any other instance variables that you wish
     */

    public Graph()
    {
        /*
         * Constructor for our Graph
         */
    }

    public Node addVertex(String name)
    {
        /*
         * Add a vertex with the given name to the graph
         * 
         * Return null if there is already a vertex with this name, otherwise
         * return the constructed vertex
         */
    }

    public Edge addEdge(Node u, Node v, int weight)
    {
        /*
         * Add an edge with the given endpoints and weight to the graph
         *
         * If u and/or v do not exist, create them and add them to the graph
         * and then add the edge
         * 
         * If an edge with these endpoints already exists, do not overwrite it
         *
         * Note that this graph is undirected, meaning an edge u,v is the same 
         * as v,u
         *
         *
         * Return null if there is already an edge with these endpoints or if u
         * and v are the same. Otherwise, return the edge
         */
    }

    public HashMap<Node, ArrayList<Edge>> getAdjacencyList()
    {
        /*
         * Return a hashmap with all of the nodes in the graph as the keys
         *     and the values being a list of all the edges that have the node
         *     as an endpoint
         */
    }

    public HashMap<Node, Integer> dijkstra(Node source)
    {
        /*
         * Return a hashmap with all of the nodes in the graph as the keys and
         * the value being the length of the shortest path from the source to
         * the node. Return null if there are negative weights in the graph
         */
    }

    public int shortestPathLength(Node source, Node target)
    {
        /*
         * Return the length of the shortest path from source to target
         * or return -1 if there are negative edge weights
         */
    }

    public ArrayList<Edge> minSpanningTree()
    {
        /*
         * Return a list of all of the edges in the minimum spanning tree of
         * the graph. The order does not matter.
         */
    }

    public int minSpanningTreeWeight()
    {
        /*
         * Return the total weight of the minimum spanning tree of the graph
         */
    }

    public int getNumNodes()
    {
        /*
         * Return the number of nodes in the graph
         */
    }

    public int getNumEdges()
    {
        /*
         * Return the number of edges in the graph
         */
    }

    public ArrayList<Node> getNodes()
    {
        /*
         * Return the nodes in the graph
         */
    }

    public ArrayList<Edge> getEdges()
    {
        /*
         * Return the edges in the graph
         */
    }

}

/*
 *  ================================
 *  Do not modify below this comment
 *  ================================
 */

class Node implements Comparable<Node>
{
    private String name;
    private int value;

    public Node(String name)
    {
        this.name = name;
        this.value = 0;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public String getName()
    {
        return name;
    }

    public int getValue()
    {
        return value;
    }

    public int compareTo(Node n)
    {
        return this.value - n.getValue();
    }
}

class Edge implements Comparable<Edge>
{
    private Node u,v;
    private int weight;

    public Edge(Node u, Node v, int weight)
    {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public void setU(Node u)
    {
        this.u = u;
    }

    public void setV(Node v)
    {
        this.v = v;
    }

    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    public Node getU()
    {
        return u;
    }

    public Node getV()
    {
        return v;
    }

    public int getWeight()
    {
        return weight;
    }

    public int compareTo(Edge e)
    {
        return this.weight - e.getWeight();
    }
}