
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.AfterClass;

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
}
