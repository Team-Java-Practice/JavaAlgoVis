import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Graph extends JPanel {
    private static final long serialVersionUID = 1L;
    public mxGraph graph;
    public Object parent;



    private double angle = 0;


    Graph() {

        setSize(750,420);
        this.graph = new mxGraph();
        this.parent = graph.getDefaultParent();
        graph.getModel().beginUpdate();
        try {
//            Object v1 = graph.insertVertex(parent, null, "1", 20, 20, 80, 30);
//            Object v2 = graph.insertVertex(parent, null, "2", 300, 150, 80, 30);
//            graph.insertEdge(parent, null, "Edge", v1, v2);
        } finally {
            graph.getModel().endUpdate();
        }
        mxGraphComponent graphComponent = new mxGraphComponent(graph);


        this.setLayout(new BorderLayout());
        this.add(graphComponent, BorderLayout.CENTER);

    }

    public void addNewVertex(){
        angle +=Math.PI/6;
        int r = 190;
        if(GraphControlPanel.counter>12) {
            r /= 2;
            angle+=Math.PI/5;
        }

        graph.insertVertex(parent,GraphControlPanel.counter+"", GraphControlPanel.counter, 210 + r*Math.cos(angle) , 210 + r*Math.sin(angle), 50, 50, "shape=ellipse");
    }

    public void paintGraph(GraphStruct graphStruct, List<State<Integer>> history, int step, JTextArea info  ){
        removeAll();

        graph = new mxGraph();
        parent = graph.getDefaultParent();

        ArrayList<Pair<Double, Double>> nodes = graphStruct.getVertexes();
        ArrayList<Object> vertexes = new ArrayList<>();

        int i = 0;
        for (Pair<Double, Double> node : nodes) {
            ++i;
            vertexes.add(graph.insertVertex(parent, null, i, node.getKey(), node.getValue(), 50, 50, "shape=ellipse"));

        }

        info.selectAll();
        info.replaceSelection("");

        for (int j = 0; j < step; j++) {
            State<Integer> stepOne = history.get(j);
            mxCell cell = (mxCell) vertexes.get(stepOne.getVertex().getValue() - 1);
            cell.setValue(history.get(j).getVertex().getValue().toString() +
                    "\n" +"Way "+ history.get(j).getVertex().getPath());
            if (stepOne.getVertex().getInTime() != Integer.MAX_VALUE && stepOne.getVertex().getOutTime() == Integer.MAX_VALUE
                    ) {
                graph.setCellStyle("shape=ellipse;strokeColor=red;fillColor=blue", new Object[]{cell});
            } else if (stepOne.getVertex().getInTime() != Integer.MAX_VALUE && stepOne.getVertex().getOutTime() != Integer.MAX_VALUE) {
                graph.setCellStyle("shape=ellipse;strokeColor=#ff0000", new Object[]{cell});
            }
            if (stepOne.getInformation().startsWith("Текущий")) {
                graph.setCellStyle("shape=ellipse;strokeColor=#00ffff", new Object[]{cell});
            }
            info.append(stepOne.getInformation());
        }

//        for (Pair<Integer, Integer> edge : graphStruct.getEdges()) {
//            graph.insertEdge(parent, null, null, vertexes.get(edge.getKey() - 1), vertexes.get(edge.getValue() - 1));
//        }

        //Integer weight = Integer.parseInt(GraphControlPanel.weightText.getText());
        for (Map.Entry<Pair<Integer,Integer>, Integer> entry : graphStruct.getEdges().entrySet()) {
            graph.insertEdge(parent, null, entry.getValue(), vertexes.get(entry.getKey().getKey() - 1), vertexes.get(entry.getKey().getValue() - 1));
        }


        graph.getModel().endUpdate();

        mxGraphComponent graphComponent = new mxGraphComponent(graph);

        this.setLayout(new BorderLayout());
        this.add(graphComponent, BorderLayout.CENTER);
        this.revalidate();

    }

}