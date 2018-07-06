import com.google.common.graph.EndpointPair;
import com.google.common.graph.Graph;
import com.google.common.graph.ValueGraph;

import java.util.*;

public class Model {
    public static <T> List<State<T>> dijkstra(Graph<Vertex<T>> graph, Vertex<T> startVertex) {
        HashMap<T, Integer> map = new HashMap<>(); // Map with weight corresponding to Vertexes.
        List<State<T>> history = new ArrayList<>();
        Deque<Vertex<T>> deque = new LinkedList<>();

        deque.addLast(startVertex);
        startVertex.setInTime(0);
        map.put(startVertex.getValue(), 0);
        int i=1;

        while (!deque.isEmpty()) {
            Vertex<T> current = deque.getFirst();
            saveToHistory(history, current, "Шаг "+ i +": текущая вершина %s");
            i++;

            Set<Vertex<T>> vertexes = new HashSet<>();
            Set<EndpointPair<Vertex<T>>> edges = graph.incidentEdges(current);
            for (EndpointPair<Vertex<T>> edge : edges) {
                if (edge.target() != current) {
                    vertexes.add(edge.target());
                }
            }

            for (Vertex<T> vertex : vertexes) {

                // Compare weights
                if (map.getOrDefault(vertex.getValue(), Integer.MAX_VALUE) > map.get(current.getValue()) + 1) {
                    deque.addLast(vertex);
                    // Put them to the map
                    map.put(vertex.getValue(), current.getInTime() + 1);
                    vertex.setInTime(map.get(vertex.getValue()));

                    saveToHistory(history, vertex, "Шаг "+i+": посещается вершина %s");
                    i++;
                }
            }

            deque.removeFirst();

            saveToHistory(history, current, "Шаг "+i+": выход из вершины %s");
            i++;
        }

        return history;
    }




//    public static <T> List<State<T>> dijkstra(ValueGraph<Vertex<T>, Integer> graph, Vertex<T> startVertex) {
//        HashMap<T, Integer> map = new HashMap<>(); // Map with weight corresponding to Vertexes.
//        List<State<T>> history = new ArrayList<>();
//        Deque<Vertex<T>> deque = new LinkedList<>();
//
//        deque.addLast(startVertex);
//        startVertex.setInTime(0);
//        map.put(startVertex.getValue(), 0);
//        int i=1;
//
//        while (!deque.isEmpty()) {
//            Vertex<T> current = deque.getFirst();
//            saveToHistory(history, current, "Шаг "+ i +": текущая вершина %s");
//            i++;
//
//            Set<Vertex<T>> vertexes = new HashSet<>();
//            Set<EndpointPair<Vertex<T>>> edges = graph.incidentEdges(current);
//            for (EndpointPair<Vertex<T>> edge : edges) {
//                if (edge.target() != current) {
//                    vertexes.add(edge.target());
//                }
//            }
//
//            for (Vertex<T> vertex : vertexes) {
//
//                // Compare weights
//                if (map.getOrDefault(vertex.getValue(), Integer.MAX_VALUE) > map.get(current.getValue()) + 1) {
//                    deque.addLast(vertex);
//                    // Put them to the map
//                    map.put(vertex.getValue(), current.getInTime() + 1);
//                    vertex.setInTime(map.get(vertex.getValue()));
//
//                    saveToHistory(history, vertex, "Шаг "+i+": посещается вершина %s");
//                    i++;
//                }
//            }
//
//            deque.removeFirst();
//
//            saveToHistory(history, current, "Шаг "+i+": выход из вершины %s");
//            i++;
//        }
//
//        return history;
//    }

    private static <T> void saveToHistory(List<State<T>> history, Vertex<T> current, String s) {
        Vertex<T> toHistory = copy(current);
        history.add(new State<>(String.format(s + "\n", toHistory), toHistory));
    }

    private static <T> Vertex<T> copy(Vertex<T> current) {
        Vertex<T> toHistory = new Vertex<>(current.getValue());
        toHistory.setInTime(current.getInTime());
        toHistory.setOutTime(current.getOutTime());
        return toHistory;
    }
}


