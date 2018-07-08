import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import javafx.util.Pair;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ModelTest {

    private static final int VertexNum = 10;

    @Test
    public void HistoryTest() {
        MutableGraph<Vertex<Integer>> graph_ = GraphBuilder.<Integer>directed().allowsSelfLoops(true).build();
        MutableValueGraph<Vertex<Integer>, Integer> gr = ValueGraphBuilder.directed().allowsSelfLoops(true).build();
        GraphStruct graphStruct = new GraphStruct();

        initGraph(graphStruct);

        for (int i = 0; i < graphStruct.getNumberOfVertexes(); i++) {
            graph_.addNode(new Vertex<>(i));
        }

        for (Map.Entry<Pair<Integer, Integer>, Integer> edge : graphStruct.getEdges().entrySet()) {
            graph_.putEdge(new Vertex<>(edge.getKey().getKey()), new Vertex<>(edge.getKey().getValue()));
        }

        for (int i = 0; i < graphStruct.getNumberOfVertexes(); i++) {
            gr.addNode(new Vertex<>(i));
        }

        for (Map.Entry<Pair<Integer, Integer>, Integer> edge : graphStruct.getEdges().entrySet()) {
            gr.putEdgeValue(new Vertex<>(edge.getKey().getKey()), new Vertex<>(edge.getKey().getValue()), edge.getValue());
        }

        List<State<Integer>> history = new ArrayList<>();
        history = Model.dijkstra(gr, new Vertex<>(1));

        assertEquals("Check step 1", new Vertex(1), history.get(0).getVertex());
        assertEquals("Check step 2", new Vertex(2), history.get(1).getVertex());
        assertEquals("Check step 3", new Vertex(4), history.get(2).getVertex());
        assertEquals("Check step 4", new Vertex(1), history.get(3).getVertex());
        assertEquals("Check step 5", new Vertex(2), history.get(4).getVertex());
        assertEquals("Check step 6", new Vertex(3), history.get(5).getVertex());
        assertEquals("Check step 7", new Vertex(2), history.get(6).getVertex());
        assertEquals("Check step 8", new Vertex(4), history.get(7).getVertex());
        assertEquals("Check step 9", new Vertex(5), history.get(8).getVertex());
        assertEquals("Check step 10", new Vertex(4), history.get(9).getVertex());
        assertEquals("Check step 11", new Vertex(3), history.get(10).getVertex());
        assertEquals("Check step 12", new Vertex(2), history.get(11).getVertex());
        // ...
        assertEquals("Check step 19", new Vertex(2), history.get(18).getVertex());
        assertEquals("Check step 20", new Vertex(5), history.get(19).getVertex());
        assertEquals("Check step 21", new Vertex(5), history.get(20).getVertex());

        assertEquals("Test", 21, history.size());
    }

    @Ignore
    private void initGraph(GraphStruct graphStruct){
        graphStruct.addVertex();
        graphStruct.addVertex();
        graphStruct.addVertex();
        graphStruct.addVertex();
        graphStruct.addVertex();

        graphStruct.addEdge(1,2, 3);
        graphStruct.addEdge(1,4, 4);
        graphStruct.addEdge(2,3, 2);
        graphStruct.addEdge(3,2, 2);
        graphStruct.addEdge(3,4, 1);
        graphStruct.addEdge(3,5, 3);
        graphStruct.addEdge(4,5, 7);
    }

}
