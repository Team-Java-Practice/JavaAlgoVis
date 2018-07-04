import java.util.ArrayList;

public class AdjacencyList {
    class Node {
        Vertex RootVertex;
        ArrayList<EdgeVertex> AdjOfRoot = new ArrayList<>();
    }
    ArrayList<Node> List = null;


    public AdjacencyList(GraphRepresentation repr) {
        List = new ArrayList<>(repr.getVertexNumber());

        for (VertexEdgeVertex currEdge : repr.Edges){
            int indexOfVertex = repr.getIndexByName(currEdge.getSource());
            List.get(indexOfVertex).RootVertex = currEdge.getSource();
            List.get(indexOfVertex).AdjOfRoot.add(new EdgeVertex(currEdge.getLength(), currEdge.getDestination()));
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Node node : List){
            builder.append(node.RootVertex);
            for (EdgeVertex elem : node.AdjOfRoot)
                builder.append(elem);
            builder.append("\n");
        }
        return new String(builder);
    }
}
