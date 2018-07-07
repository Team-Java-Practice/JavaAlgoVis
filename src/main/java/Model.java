import com.google.common.graph.EndpointPair;
import com.google.common.graph.Graph;
import com.google.common.graph.ValueGraph;

import java.util.*;

public class Model {

    public static <T> List<State<T>> dijkstra(ValueGraph<Vertex<T>, Integer> graph, Vertex<T> startVertex) {
        HashMap<T, Integer> map = new HashMap<>(); // Map with weight corresponding to Vertexes.
        List<State<T>> history = new ArrayList<>();

        LinkedList<Vertex<T>> queue = new LinkedList<>();


        queue.add(startVertex);
        startVertex.setPath(0);
        startVertex.setInTime(0);

        int time = 0;
        map.put(startVertex.getValue(), 0);
        int i=1;

        while (!queue.isEmpty()) {
            time++;
            Vertex<T> current = getmin(queue);

            System.out.println("Начинам вершина " + current.getValue() + " " + current.getPath());
            saveToHistory(history, current, "Шаг "+ i +": %s");
            current.setInTime(time);
            i++;

            //Set<Vertex<T>> vertexes = new HashSet<>();
            Map<Vertex<T>, Integer> vrtx = new HashMap<>();

            Set<EndpointPair<Vertex<T>>> edges = graph.incidentEdges(current);
            for (EndpointPair<Vertex<T>> edge : edges) {
                if (edge.target() != current) {
                    vrtx.put(edge.target(), graph.edgeValue(current, edge.target()).get());
                }
            }

            for (Map.Entry<Vertex<T>, Integer> pair: vrtx.entrySet()) {
                System.out.println("Проверяем Вершина "+pair.getKey().getValue()+" значение " +pair.getKey().getPath() );
                if(current.getPath()+pair.getValue()<pair.getKey().getPath()){
                    pair.getKey().setPath(current.getPath()+pair.getValue());
                    if(queue.contains(pair.getKey())) {
                        System.out.println("Вершина " + queue.get(queue.indexOf(pair.getKey())).getValue() + " была в очереди" );
                        queue.remove(queue.indexOf(pair.getKey()));
                    }
                    queue.add(pair.getKey());
                    System.out.println("Вершина "+pair.getKey().getValue()+" в очередь со значание "+pair.getKey().getPath());
                    saveToHistory(history, pair.getKey(), "Шаг " + i + ": %s");
                    i++;
                }
            }
            queue.remove(current);
            saveToHistory(history, current, "Шаг "+i+"(выход из вершины): %s");
            i++;
        }

        return history;
    }

    private static <T> void saveToHistory(List<State<T>> history, Vertex<T> current, String s) {
        Vertex<T> toHistory = copy(current);
        //System.out.println(toHistory.getValue() + " " + toHistory.getPath());
        history.add(new State<>(String.format(s + "\n", toHistory), toHistory));
    }

    private static <T> Vertex<T> copy(Vertex<T> current) {
        Vertex<T> toHistory = new Vertex<>(current.getValue());
        toHistory.setInTime(current.getInTime());
        toHistory.setOutTime(current.getOutTime());
        toHistory.setPath(current.getPath());
        return toHistory;
    }

    private static <T>Vertex<T>  getmin(LinkedList<Vertex<T>> queue){
        if(queue.isEmpty())
            return null;
        Vertex<T> min = queue.get(0);
        for (int i = 0; i < queue.size(); i++) {
            if(queue.get(i).getPath()<min.getPath())
                min = queue.get(i);
        }
        return min;
    }
}


