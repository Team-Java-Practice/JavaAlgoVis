import javafx.util.Pair;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GraphStruct {
    private int numberOfVertexes;
    private List<Pair<Integer, Integer>> edges = new ArrayList<>();
    private List<Vertex<Integer>> listOfVertexes = new ArrayList<>();



    public void addVertex() {
        this.numberOfVertexes++;
        listOfVertexes.add(new Vertex<Integer>(numberOfVertexes));
    }

    public void removeVertex(int vrtx){
        if (this.numberOfVertexes !=1) {
            this.numberOfVertexes--;
        }

        List<Pair<Integer, Integer>> list = new ArrayList<>(edges);
        for (Vertex vertex: listOfVertexes
             ) {
            if(vertex.getValue().equals(vrtx)) {
                listOfVertexes.remove(vertex);
                break;
            }
        }

        for(Pair<Integer, Integer> pair: list){
            if(pair.getValue().equals(vrtx)) {
                edges.remove(pair);
            }

        }

        for(Pair<Integer, Integer> pair: list){
            if(pair.getKey().equals(vrtx)) {
                edges.remove(pair);
            }

        }

    }

    public boolean isVertexValue(int value){
        for (Vertex vertex: listOfVertexes
             ) {
            if(vertex.getValue().equals(value))
                return true;
        }
        return false;
    }


    public void addEdge(JSpinner from, JSpinner to) {
        this.edges.add(new Pair<>((Integer) from.getValue(), (Integer) to.getValue()));
    }

    public void addEdge(int from, int to) {
        this.edges.add(new Pair<>(from, to));
    }

    public ArrayList<Pair<Double, Double>> getVertexes() {  // размещения вершин на поле для основного графа
        ArrayList<Pair<Double, Double>> points = new ArrayList<>();
        double phi0 = 0;
        double phi = 2 * Math.PI / numberOfVertexes;
        int r = 200;
        for (int i = 0; i <numberOfVertexes ; i++) {
            points.add(new Pair<>(250 + r * Math.cos(phi0), 220 + r * Math.sin(phi0)));
            phi0 += phi;
        }
        return points;
    }

    public int getNumberOfVertexes() {
        return numberOfVertexes;
    }

    public List<Pair<Integer, Integer>> getEdges() {
        return edges;
    }


    public List<Vertex<Integer>> getListOfVertexes() {
        return listOfVertexes;
    }

    public void clear() {
        numberOfVertexes = 0;
        edges = new ArrayList<>();
    }
}
