import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

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

//        AdjacencyList adjacencyList1 = new AdjacencyList(myGraph);
//        System.out.println(adjacencyList1);

        Algo answer = new Algo(myGraph, new Vertex("A"));
        System.out.println("Number of steps: " + answer.getNumberOfSteps());


        Vertex destination  = new Vertex("C");
        System.out.println("Step of reaching vertex " + destination + ": " + answer.getStepOfDestination(destination));


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

        // Более хитро
         Edges.removeIf(x -> x.getSource().equals(vertex) || x.getDestination().equals(vertex));
    }

    public void addEdge(VertexEdgeVertex edge) {
        Edges.add(new VertexEdgeVertex(edge.getSource(), edge.getDestination(), edge.getLength()));
    }


    @Override
    public void deleteEdge(Vertex source, Vertex destination) {
        if (!Edges.removeIf(x -> x.getSource().equals(source) && x.getDestination().equals(destination)))
            throw new NoSuchElementException();
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
        int index = Verteces.indexOf(vertex);
        if (index == -1)
            throw new NoSuchElementException();
        else
            return  index;
    }

    Vertex getVertexByName(int index){
        return Verteces.get(index);
    }

}


class Vertex {
    private String name;

    public Vertex(String name) {
        this.name = name;
    }

    public Vertex(Vertex vertex) {
        this.name = vertex.name;
    }

    private String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(name, vertex.name);
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

    public Vertex getDestination() {
        return vertex;
    }

    @Override
    public String toString() {
        return " -> " + vertex + "(" + length + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EdgeVertex)) return false;
        EdgeVertex that = (EdgeVertex) o;
        return length == that.length &&
                Objects.equals(vertex, that.vertex);
    }

    @Override
    public int hashCode() {

        return Objects.hash(length, vertex);
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

    @Override
    public String toString() {
        return source + "--" + length + "--" + destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VertexEdgeVertex)) return false;
        VertexEdgeVertex that = (VertexEdgeVertex) o;
        return length == that.length &&
                Objects.equals(source, that.source) &&
                Objects.equals(destination, that.destination);
    }

    @Override
    public int hashCode() {

        return Objects.hash(source, destination, length);
    }
}


