import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.AfterClass;

import java.util.HashMap;

public class GraphTest
{
    @Test(timeout=100)
    public void testBasic()
    {
        Graph g = new Graph();
        Node a=g.addVertex("a");
        Node b=g.addVertex("b");
        Node c=g.addVertex("c");
        Node d=g.addVertex("d");
        g.addEdge(a,b,5);
        g.addEdge(b,c,10);
        g.addEdge(c,d,3);
        g.addEdge(d,a,1);
        g.addEdge(b,d,7);
        assertEquals(9,g.minSpanningTreeWeight());
    }

    @Test(timeout=100)
    public void test1()
    {
        Graph g = new Graph();
        Node a=g.addVertex("a");
        Node b=g.addVertex("b");
        Node c=g.addVertex("c");
        Node d=g.addVertex("d");
        Node e=g.addVertex("e");
        g.addEdge(a,e,10);
        g.addEdge(a,b,10);
        g.addEdge(a,d,10);
        g.addEdge(a,c,10);
        g.addEdge(b,c,20);
        g.addEdge(c,d,20);
        assertEquals(40,g.minSpanningTreeWeight());
    }

    @Test(timeout=100)
    public void test2()
    {
        Graph g = new Graph();
        Node a=g.addVertex("a");
        Node b=g.addVertex("b");
        Node c=g.addVertex("c");
        Node d=g.addVertex("d");
        Node e=g.addVertex("e");
        g.addEdge(a,e,10);
        g.addEdge(a,b,10);
        g.addEdge(a,c,20);
        g.addEdge(b,c,10);
        g.addEdge(c,d,10);
        g.addEdge(e,d,10);
        assertEquals(40,g.minSpanningTreeWeight());
    }

    @Test(timeout=100)
    public void testDijkstra()
    {
        Graph g = new Graph();
        Node bos=g.addVertex("bos");
        Node pvd=g.addVertex("pvd");
        Node jfk=g.addVertex("jfk");
        Node ord=g.addVertex("ord");
        Node bwi=g.addVertex("bwi");
        Node mia=g.addVertex("mia");
        Node dfw=g.addVertex("dfw");
        Node sfo=g.addVertex("sfo");
        Node lax=g.addVertex("lax");

        g.addEdge(bos,sfo,2704);
        g.addEdge(bos,ord,867);
        g.addEdge(bos,jfk,187);
        g.addEdge(bos,mia,1258);

        g.addEdge(pvd,ord,849);
        g.addEdge(pvd,jfk,144);

        g.addEdge(jfk,ord,740);
        g.addEdge(jfk,dfw,1391);
        g.addEdge(jfk,bwi,184);
        g.addEdge(jfk,mia,1090);

        g.addEdge(ord,sfo,1846);
        g.addEdge(ord,dfw,802);
        g.addEdge(ord,bwi,621);

        g.addEdge(bwi,mia,946);

        g.addEdge(mia,dfw,1121);
        g.addEdge(mia,lax,2342);

        g.addEdge(dfw,sfo,1464);
        g.addEdge(dfw,lax,1235);

        g.addEdge(sfo,lax,337);

        HashMap<Node, Integer> list = g.dijkstra(bwi);
        for(Node n : list.keySet())
        {
            System.out.print(n.getName()+": ");
            System.out.println(list.get(n));
        }

    }

    @Test(timeout=100)
    public void testPrims()
    {
        Graph g = new Graph();
        Node bos=g.addVertex("bos");
        Node pvd=g.addVertex("pvd");
        Node jfk=g.addVertex("jfk");
        Node ord=g.addVertex("ord");
        Node bwi=g.addVertex("bwi");
        Node mia=g.addVertex("mia");
        Node dfw=g.addVertex("dfw");
        Node sfo=g.addVertex("sfo");
        Node lax=g.addVertex("lax");

        g.addEdge(bos,sfo,2704);
        g.addEdge(bos,ord,867);
        g.addEdge(bos,jfk,187);
        g.addEdge(bos,mia,1258);

        g.addEdge(pvd,ord,849);
        g.addEdge(pvd,jfk,144);

        g.addEdge(jfk,ord,740);
        g.addEdge(jfk,dfw,1391);
        g.addEdge(jfk,bwi,184);
        g.addEdge(jfk,mia,1090);

        g.addEdge(ord,sfo,1846);
        g.addEdge(ord,dfw,802);
        g.addEdge(ord,bwi,621);

        g.addEdge(bwi,mia,946);

        g.addEdge(mia,dfw,1121);
        g.addEdge(mia,lax,2342);

        g.addEdge(dfw,sfo,1464);
        g.addEdge(dfw,lax,1235);

        g.addEdge(sfo,lax,337);

        assertEquals(4456,g.minSpanningTreeWeight());
    }


}
