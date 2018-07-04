import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Model {

    public static void main(String[] args) {
        GraphRepresentation myGraph = new GraphRepresentation();
        myGraph.addVertex(new Vertex("A"));
        myGraph.addVertex(new Vertex("B"));
        myGraph.addVertex(new Vertex("C"));
        myGraph.addVertex(new Vertex("D"));
        myGraph.addVertex(new Vertex("E"));

        myGraph.addEdge(new VertexEdgeVertex(new Vertex("A"), new Vertex("B"), 3));
        myGraph.addEdge(new VertexEdgeVertex(new Vertex("A"), new Vertex("D"), 4));
        myGraph.addEdge(new VertexEdgeVertex(new Vertex("C"), new Vertex("D"), 1));
        myGraph.addEdge(new VertexEdgeVertex(new Vertex("C"), new Vertex("B"), 2));
        myGraph.addEdge(new VertexEdgeVertex(new Vertex("B"), new Vertex("C"), 2));
        myGraph.addEdge(new VertexEdgeVertex(new Vertex("C"), new Vertex("E"), 3));
        myGraph.addEdge(new VertexEdgeVertex(new Vertex("D"), new Vertex("E"), 7));


        AdjacencyList adjacencyList = new AdjacencyList(myGraph);
        System.out.println(adjacencyList);

        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix(myGraph);
        System.out.println(adjacencyMatrix);
    }
}


class GraphRepresentation implements GraphInterface {
    ArrayList<Vertex> Verteces = new ArrayList<Vertex>();
    ArrayList<VertexEdgeVertex> Edges = new ArrayList<VertexEdgeVertex>();

    /* default constructor */

    public void addVertex(Vertex vertex) {
        Verteces.add(vertex);
    }

    public void deleteVertex(final Vertex vertex) {
        if (!Verteces.remove(vertex)) throw new NoSuchElementException();

        // В лоб
        for (VertexEdgeVertex elem : Edges)
            if (elem.getDestination().equals(vertex))
                Edges.remove(elem);
            else if (elem.getSource().equals(vertex))
                Edges.remove(elem);

        // Более хитро
//         Edges.removeIf(x -> x.getSource().equals(vertex) || x.getDestination().equals(vertex));
    }

    public void addEdge(VertexEdgeVertex edge) {
        Edges.add(new VertexEdgeVertex(edge.getSource(), edge.getDestination(), edge.getLength()));
    }

    public void deleteEdge(VertexEdgeVertex edge) {
        if (!Edges.remove(edge)) throw new NoSuchElementException();
    }

    public Vertex findNearestVertex(Vertex source) {
        return null;
        /* maybe todo */
    }

    int getVertexNumber() {
        return Verteces.size();
    }

    int getEdgeNumber() {
        return Edges.size();
    }

    int getIndexByName(Vertex vertex){
        int index = Edges.indexOf(vertex);
        if (index == -1)
            throw new NoSuchElementException();
        else
            return  index;

    }

}


class Vertex {
    private String name;

    public Vertex(String name) {
        this.name = name;
    }

    private String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class EdgeVertex {
    private int length;
    private Vertex vertex;

    EdgeVertex(int length, Vertex vertex) {
        this.length = length;
        this.vertex = vertex;
    }

    public int getLength() {
        return length;
    }

    public Vertex getVertex() {
        return vertex;
    }

    @Override
    public String toString() {
        return " -" + vertex + "(" + length + ")";
    }
}

class VertexEdgeVertex {
    private Vertex source;
    private Vertex destination;
    private int length;

    public VertexEdgeVertex(Vertex source, Vertex destination, int length) {
        this.source = source;
        this.destination = destination;
        this.length = length;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getDestination() {
        return destination;
    }

    public int getLength() {
        return length;
    }
}


