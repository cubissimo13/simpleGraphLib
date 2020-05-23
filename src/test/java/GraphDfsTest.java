import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class GraphDfsTest {

    @Test
    public void testAddVertexInGraph() {
        GraphDfs<Object> testGraph = new GraphDfs<>(true);
        Object testVertex = new Object();
        testGraph.addVertex(testVertex);
        assertEquals(1, testGraph.getVertexCollection().size());
        assertNotNull(testGraph.getVertexCollection().get(testVertex));
        assertTrue(testGraph.getVertexCollection().get(testVertex).isEmpty());
    }

    @Test
    public void testWhenGraphIsUndirectedShouldAddEdgeToBothVertex() {
        GraphDfs<Object> testGraph = new GraphDfs<>(true);
        Object testVertex1 = new Object();
        Object testVertex2 = new Object();
        testGraph.addVertex(testVertex1);
        testGraph.addVertex(testVertex2);
        testGraph.addEdge(testVertex1, Set.of(testVertex2));
        assertTrue(testGraph.getVertexCollection().get(testVertex1).contains(testVertex2));
        assertTrue(testGraph.getVertexCollection().get(testVertex2).contains(testVertex1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowIllegalArgumentWhenPutNullDestinationInGetPath() {
        GraphDfs<Object> testGraph = new GraphDfs<>(false);
        testGraph.getPath(new Object(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowIllegalArgumentWhenPutNullSourceInGetPath() {
        GraphDfs<Object> testGraph = new GraphDfs<>(false);
        testGraph.getPath(null, new Object());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowIllegalArgumentWhenPutSourceThatNotContainedInGraph() {
        GraphDfs<Object> testGraph = new GraphDfs<>(false);
        Object testVertex = new Object();
        testGraph.addVertex(testVertex);
        testGraph.getPath(new Object(), testVertex);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowIllegalArgumentWhenPutDestinationThatNotContainedInGraph() {
        GraphDfs<Object> testGraph = new GraphDfs<>(false);
        Object testVertex = new Object();
        testGraph.addVertex(testVertex);
        testGraph.getPath(testVertex, new Object());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowIllegalArgumentWhenPutSameObject() {
        GraphDfs<Object> testGraph = new GraphDfs<>(false);
        Object testVertex = new Object();
        testGraph.getPath(testVertex, testVertex);
    }

    @Test
    public void testShouldReturnTrueWhenWasConstructedUndirectedGraph() {
        GraphDfs<Object> testGraph = new GraphDfs<>(true);
        assertTrue(testGraph.isUndirected());
    }

    @Test
    public void testWhenGraphIsNotUndirectedShouldNotAddEdgeToBothVertex() {
        GraphDfs<Object> testGraph = new GraphDfs<>(false);
        Object testVertex1 = new Object();
        Object testVertex2 = new Object();
        testGraph.addVertex(testVertex1);
        testGraph.addVertex(testVertex2);
        testGraph.addEdge(testVertex1, Set.of(testVertex2));
        assertTrue(testGraph.getVertexCollection().get(testVertex1).contains(testVertex2));
        assertFalse(testGraph.getVertexCollection().get(testVertex2).contains(testVertex1));
    }

    @Test
    public void testShouldHaveOnePathBothWaysInUndirectedGraph() {
        GraphDfs<Object> testGraph = new GraphDfs<>(true);
        Object testVertex1 = new Object();
        Object testVertex2 = new Object();
        Object testVertex3 = new Object();
        testGraph.addVertex(testVertex1);
        testGraph.addVertex(testVertex2);
        testGraph.addVertex(testVertex3);
        testGraph.addEdge(testVertex1, Set.of(testVertex2));
        testGraph.addEdge(testVertex2, Set.of(testVertex3));
        assertEquals(1, testGraph.getPath(testVertex1, testVertex3).size());
        assertEquals(1, testGraph.getPath(testVertex3, testVertex1).size());
        assertEquals(3, testGraph.getPath(testVertex1, testVertex3).get(0).size());
        assertEquals(3, testGraph.getPath(testVertex3, testVertex1).get(0).size());
    }

    @Test
    public void testShouldHaveOneWayPathInDirectedGraph() {
        GraphDfs<Object> testGraph = new GraphDfs<>(false);
        Object testVertex1 = new Object();
        Object testVertex2 = new Object();
        Object testVertex3 = new Object();
        testGraph.addVertex(testVertex1);
        testGraph.addVertex(testVertex2);
        testGraph.addVertex(testVertex3);
        testGraph.addEdge(testVertex1, Set.of(testVertex2));
        testGraph.addEdge(testVertex2, Set.of(testVertex3));
        assertEquals(1, testGraph.getPath(testVertex1, testVertex3).size());
        assertEquals(0, testGraph.getPath(testVertex3, testVertex1).size());
        assertEquals(3, testGraph.getPath(testVertex1, testVertex3).get(0).size());
    }

    @Test
    public void testShouldHaveTwoPaths() {
        GraphDfs<Object> testGraph = new GraphDfs<>(true);
        Object testVertex1 = new Object();
        Object testVertex2 = new Object();
        Object testVertex3 = new Object();
        Object testVertex4 = new Object();
        Object testVertex5 = new Object();
        testGraph.addVertex(testVertex1);
        testGraph.addVertex(testVertex2);
        testGraph.addVertex(testVertex3);
        testGraph.addVertex(testVertex4);
        testGraph.addVertex(testVertex5);
        testGraph.addEdge(testVertex1, Set.of(testVertex2, testVertex4));
        testGraph.addEdge(testVertex2, Set.of(testVertex3));
        testGraph.addEdge(testVertex5, Set.of(testVertex3, testVertex4));
        assertEquals(2, testGraph.getPath(testVertex1, testVertex5).size());
        assertEquals(2, testGraph.getPath(testVertex5, testVertex1).size());
    }
}