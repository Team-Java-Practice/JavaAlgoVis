import javafx.util.Pair;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphStruct {
    private int numberOfVertexes;

    private List<Vertex<Integer>> listOfVertexes = new ArrayList<>();

    private Map<Pair<Integer,Integer>, Integer> edges = new HashMap<>();

    public void addVertex() {
        this.numberOfVertexes++;
        listOfVertexes.add(new Vertex<Integer>(numberOfVertexes));
    }

    public void removeVertex(int vrtx){
        if (this.numberOfVertexes == 0) {
            return;
        }

//        Map<Pair<Integer, Integer>, Integer> map = new HashMap<>(edges);
        for (Vertex vertex: listOfVertexes) {
            if(vertex.getValue().equals(vrtx)) {
                listOfVertexes.remove(vertex);
                this.numberOfVertexes--;

                for(Map.Entry<Pair<Integer, Integer>, Integer> pair : edges.entrySet()){
                    if(pair.getKey().getValue().equals(vrtx)) {
                        System.out.println("Сравниваем с " + pair.getKey().getValue());
                        edges.remove(pair.getKey());
                    }
                }

                for(Map.Entry<Pair<Integer, Integer>, Integer> pair : edges.entrySet()){
                    if(pair.getKey().getKey().equals(vrtx)) {
                        edges.remove(pair.getKey());
                    }
                }

                break;
            }
        }
    }

    public boolean checkValue(int value){
        for (Vertex vertex: listOfVertexes) {
            if(vertex.getValue().equals(value))
                return true;
        }
        return false;
    }


    public void addEdge(JSpinner from, JSpinner to, JSpinner weight) {
        edges.put(new Pair<Integer, Integer>((Integer) from.getValue(), (Integer) to.getValue()),(Integer) weight.getValue());

    }

    public void addEdge(int from, int to, int weight) {
        if (weight < 1)
            throw new NumberFormatException("weight must be natural");
        edges.put(new Pair<Integer, Integer>(from, to),weight);
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

    public int getNumberOfEdges(){
        return edges.size();
    }

    public Map<Pair<Integer, Integer>, Integer> getEdges() {
        return edges;
    }


    public List<Vertex<Integer>> getListOfVertexes() {
        return listOfVertexes;
    }

    public void clear() {
//        old
//        numberOfVertexes = 0;
//        edges = new HashMap<>();

//        new
        edges = new HashMap<>();
        listOfVertexes = new ArrayList<>();
        numberOfVertexes = listOfVertexes.size();
    }
}
