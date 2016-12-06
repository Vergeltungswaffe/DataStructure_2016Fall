/*
 * Name: Kim, Jin Seol
 * Student ID: 2011133024
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
        numNodes = 0;
        numEdges = 0;
        nodeList = new ArrayList<>();
        edgeList = new ArrayList<>();
    }

    public Node addVertex(String name)
    {
        /*
         * Add a vertex with the given name to the graph
         *
         * Return null if there is already a vertex with this name, otherwise
         * return the constructed vertex
         */
        for (Node n : nodeList)
        {
            if (n.getName().equals(name))
                return null;
        }
        Node newNode = new Node(name);
        nodeList.add(newNode);
        numNodes++;
        return newNode;
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
        if(u==v)
            return null;
        for(Edge e : edgeList)
        {
            if((e.getU()==u||e.getV()==u)&&(e.getU()==v||e.getV()==v))
                return null;
        }
        if(!nodeList.contains(u))
        {
            nodeList.add(u);
        }
        if(!nodeList.contains(v))
        {
            nodeList.add(v);
        }
        Edge newEdge = new Edge(u,v,weight);
        edgeList.add(newEdge);
        numEdges++;
        return newEdge;
    }

    public HashMap<Node, ArrayList<Edge>> getAdjacencyList()
    {
        /*
         * Return a hashmap with all of the nodes in the graph as the keys
         *     and the values being a list of all the edges that have the node
         *     as an endpoint
         */
        HashMap<Node,ArrayList<Edge>> adjList = new HashMap<>();
        ArrayList<ArrayList<Edge>> incidCol = new ArrayList<>(numNodes);
        for(int i=0;i<numNodes;i++)
        {
            incidCol.add(new ArrayList<>());
        }
        for(Edge e : edgeList)
        {
            incidCol.get(nodeList.indexOf(e.getU())).add(e);
            incidCol.get(nodeList.indexOf(e.getV())).add(e);
        }
        for(int i=0;i<numNodes;i++)
        {
            adjList.put(nodeList.get(i),incidCol.get(i));
        }
        return adjList;
    }

    public HashMap<Node, Integer> dijkstra(Node source)
    {
        /*
         * Return a hashmap with all of the nodes in the graph as the keys and
         * the value being the length of the shortest path from the source to
         * the node. Return null if there are negative weights in the graph
         */
        for(Edge e : edgeList)
        {
            if(e.getWeight()<0)
                return null;
        }
        HashMap<Node,Integer> d = new HashMap<>();
        HashMap<Node,Integer> cloud = new HashMap<>();
        HashMap<Node,ArrayList<Edge>> adjList = getAdjacencyList();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(Node n : nodeList)
        {
            if(n==source)
            {
                n.setValue(0);
                d.put(n, 0);
            }
            else
            {
                n.setValue(Integer.MAX_VALUE);
                d.put(n,Integer.MAX_VALUE);
            }
            pq.add(n);
        }
        while(!pq.isEmpty())
        {
            Node u = pq.remove();
            int sumWeight = u.getValue();
            cloud.put(u,sumWeight);

            ArrayList<Edge> edges = adjList.get(u);
            for(Edge e : edges)
            {
                Node v = e.getU()==u?e.getV():e.getU();
                if(cloud.get(v)==null)
                {
                    int wgt = e.getWeight();
                    if(d.get(u)+wgt<d.get(v))
                    {
                        d.put(v,d.get(u)+wgt);
                        v.setValue(d.get(u)+wgt);
                    }
                }
            }
        }
        return cloud;
    }

    public int shortestPathLength(Node source, Node target)
    {
        /*
         * Return the length of the shortest path from source to target
         * or return -1 if there are negative edge weights
         */
        if(dijkstra(source)==null)
            return -1;
        return dijkstra(source).get(target);
    }

    public ArrayList<Edge> minSpanningTree()
    {
        /*
         * Return a list of all of the edges in the minimum spanning tree of
         * the graph. The order does not matter.
         */
        ArrayList<Edge> minSpanTree = new ArrayList<>();
        HashMap<Node,Integer> cloud = new HashMap<>();
        HashMap<Node,ArrayList<Edge>> adjList = getAdjacencyList();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        if(numNodes==0)
            return minSpanTree;
        Node start = nodeList.get(0);
        for(Node n : nodeList)
        {
            if(n==start)
            {
                n.setValue(0);
            }
            else
            {
                n.setValue(Integer.MAX_VALUE);
            }
            pq.add(n);
        }

        while(!pq.isEmpty())
        {
            Node u = pq.remove();
            int weight = u.getValue();
            cloud.put(u,weight);

            ArrayList<Edge> edges = adjList.get(u);
            for(Edge e : edges)
            {
                if(e.getWeight()==weight)
                    minSpanTree.add(e);
                Node v = e.getU()==u?e.getV():e.getU();
                if(cloud.get(v)==null)
                {
                    int wgt = e.getWeight();
                    if(wgt<v.getValue())
                    {
                        v.setValue(wgt);
                    }
                }
            }
        }
        return minSpanTree;
    }

    public int minSpanningTreeWeight()
    {
        /*
         * Return the total weight of the minimum spanning tree of the graph
         */
        ArrayList<Edge> minSpanningEdges = minSpanningTree();
        int minTotalWeight=0;
        for(Edge e : minSpanningEdges)
        {
            minTotalWeight+=e.getWeight();
        }
        return minTotalWeight;
    }

    public int getNumNodes()
    {
        /*
         * Return the number of nodes in the graph
         */
        return nodeList.size();
    }

    public int getNumEdges()
    {
        /*
         * Return the number of edges in the graph
         */
        return edgeList.size();
    }

    public ArrayList<Node> getNodes()
    {
        /*
         * Return the nodes in the graph
         */
        return nodeList;
    }

    public ArrayList<Edge> getEdges()
    {
        /*
         * Return the edges in the graph
         */
        return edgeList;
    }

    //////////////////////////////
    // Helper variable, methods //
    //////////////////////////////
    private ArrayList<Node> nodeList;
    private ArrayList<Edge> edgeList;

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
