import java.util.ArrayList;

public class AdjacencyList {
    Node[] List = null;
    class Node {
        Vertex RootVertex;
        ArrayList<EdgeVertex> AdjOfRoot = new ArrayList<>();

        public Node(Vertex rootVertex) {
            RootVertex = rootVertex;
        }
    }

    public AdjacencyList(GraphRepresentation repr) {
        List = new Node[repr.getVertexNumber()];
        for (int i = 0; i < repr.getVertexNumber(); i++)
            List[i] = new Node(repr.Verteces.get(i));


        for (VertexEdgeVertex currEdge : repr.Edges){
            int indexOfNode = repr.getIndexByName(currEdge.getSource());
            List[indexOfNode].AdjOfRoot.add(new EdgeVertex(currEdge.getLength(), currEdge.getDestination()));
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
