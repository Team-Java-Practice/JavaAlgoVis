import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class GraphStructTest {

    @BeforeClass
    public static void printBeginMes(){
        System.out.println("Begin Tests for GraphStruct!");
    }

    @AfterClass
    public static void printExitMes(){
        System.out.println("End Tests for GraphStruct!");
    }

    @Before
    public void initGraphStruct() {
    }

    @Test
    public void addVertex() {
        System.out.print("    Test addVertex...");

        GraphStruct graphStruct = new GraphStruct();
        assertEquals("New graph should contain 0 verteces after remove.",
                0, graphStruct.getNumberOfVertexes());

        graphStruct.addVertex();
        graphStruct.addVertex();
        assertEquals("Graph should contain 2 verteces.",
                2, graphStruct.getNumberOfVertexes());

        System.out.println(" done!");
    }

    @Test
    public void removeVertex() {
        System.out.print("    Test removeVertex...");

        GraphStruct graphStruct = new GraphStruct();
        graphStruct.removeVertex(1);
        assertEquals("Empty graph should contain 0 verteces.", 0, graphStruct.getNumberOfVertexes());

        graphStruct.addVertex();
        graphStruct.addVertex();
        assertEquals("Graph should contain 2 verteces.", 2, graphStruct.getNumberOfVertexes());


        graphStruct.removeVertex(2);
        graphStruct.removeVertex(1);
        assertEquals("Deleted all. Should contain 0.", 0, graphStruct.getNumberOfVertexes());

        System.out.println(" done!");
    }

    @Test
    public void checkValueTest() {
        System.out.print("    Test checkValue...");

        GraphStruct graphStruct = new GraphStruct();

        graphStruct.addVertex();
        assertTrue("Container should contain vertexes with values equaled those position when adding.",
                graphStruct.checkValue(1));
        assertFalse("Container should contain vertexes with values equaled those position when adding.",
                graphStruct.checkValue(2));
        assertFalse("Container should contain vertexes with values equaled those position when adding.",
                graphStruct.checkValue(10));
        assertFalse("Container should contain vertexes with values equaled those position when adding.",
                graphStruct.checkValue(0));

        graphStruct.addVertex();
        graphStruct.addVertex();
        graphStruct.addVertex();
        assertTrue("Container should contain vertexes with values equaled those position when adding.",
                graphStruct.checkValue(2));
        assertTrue("Container should contain vertexes with values equaled those position when adding.",
                graphStruct.checkValue(4));

        graphStruct.removeVertex(4);
        graphStruct.removeVertex(2);
        assertTrue("Container should contain vertexes with values equaled those position when adding.",
                graphStruct.checkValue(3));
        assertTrue("Container should contain vertexes with values equaled those position when adding.",
                graphStruct.checkValue(1));
        assertFalse("Container should contain vertexes with values equaled those position when adding.",
                graphStruct.checkValue(4));
        assertFalse("Container should contain vertexes with values equaled those position when adding.",
                graphStruct.checkValue(2));

        System.out.println(" done!");
    }

    @Test
    public void addEdge() {
        System.out.print("    Test addEdge...");

        GraphStruct graphStruct = new GraphStruct();
        assertEquals("Empty graph should contain 0 edge.", 0, graphStruct.getNumberOfEdges());

        graphStruct.addEdge(1,2, 10);
        assertEquals("Graph should contain 1 edge.", 1, graphStruct.getNumberOfEdges());

        graphStruct.addEdge(2,3, 1);
        graphStruct.addEdge(3,1, 20);
        assertEquals("Graph should contain 3 edges.", 3, graphStruct.getNumberOfEdges());

        graphStruct.addEdge(2,3, 1);
        graphStruct.addEdge(3,1, 20);
        assertEquals("Graph should contain the same number of edges like before.", 3, graphStruct.getNumberOfEdges());

        System.out.println(" done!");
    }

    @Rule
    public ExpectedException thrownWeight = ExpectedException.none();

    @Test
    public void addEdgeWithNullWeight() throws NumberFormatException {
        System.out.println("    Test addEdgeWithNullWeight (should throw).");

        GraphStruct graphStruct = new GraphStruct();
        thrownWeight.expect(NumberFormatException.class);
        thrownWeight.expectMessage("weight must be natural");

        graphStruct.addEdge(5,6, 0);
    }

    @Test
    public void addEdgeWithNegativeWeight() throws NumberFormatException {
        System.out.println("    Test addEdgeWithNegativeWeight (should throw).");

        GraphStruct graphStruct = new GraphStruct();
        thrownWeight.expect(NumberFormatException.class);
        thrownWeight.expectMessage("weight must be natural");

        graphStruct.addEdge(5,6, -10);
    }

    @Test
    public void clear() {
        System.out.print("    Test clear... ");

        GraphStruct graphStruct = new GraphStruct();
        graphStruct.addEdge(1,2, 10);
        graphStruct.addEdge(2,3, 20);
        graphStruct.addEdge(3,4, 30);
        graphStruct.addEdge(4,1, 40);
        graphStruct.clear();

        assertEquals("Must be null", 0, graphStruct.getNumberOfVertexes());
        assertEquals("Must be null", 0, graphStruct.getNumberOfEdges());

        System.out.println(" done!");
    }




    @Test
    public void getNumberOfEdges() {
        System.out.print("    Test getNumberOfEdges...");

        GraphStruct graphStruct = new GraphStruct();
        graphStruct.addEdge(1,2, 10);
        graphStruct.addEdge(2,3, 20);
        graphStruct.addEdge(3,4, 30);
        graphStruct.addEdge(4,1, 40);
        assertEquals("Should be 4 verteces.", 4, graphStruct.getNumberOfEdges());


        System.out.println(" done!");
    }
}