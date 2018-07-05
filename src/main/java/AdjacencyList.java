import com.sun.javafx.geom.Edge;

import java.util.ArrayList;

public class AdjacencyList {
    private Node[] List = null;
    GraphRepresentation repr = null;
    private int vertexNumber;

    class Node {
        Vertex RootVertex;
        ArrayList<EdgeVertex> AdjOfRoot = new ArrayList<>();

        public Node(Vertex rootVertex) {
            RootVertex = rootVertex;
        }
    }

    public AdjacencyList(GraphRepresentation repr) {
        this.repr = repr;
        vertexNumber = repr.getVertexNumber();
        List = new Node[vertexNumber];
        for (int i = 0; i < vertexNumber; i++)
            List[i] = new Node(repr.Verteces.get(i));


        for (VertexEdgeVertex currEdge : repr.Edges){
            int indexOfNode = repr.getIndexByName(currEdge.getSource());
            List[indexOfNode].AdjOfRoot.add(new EdgeVertex(currEdge.getLength(), currEdge.getDestination()));
        }
    }

    public ArrayList<EdgeVertex> getAdjVertecesList(Vertex vertex) {
        return List[repr.getIndexByName(vertex)].AdjOfRoot;
    }

    public int getVertexNumber(){
        return vertexNumber;
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
