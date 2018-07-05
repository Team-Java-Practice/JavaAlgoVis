import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;

class Graph extends JPanel {
    private static final long serialVersionUID = 1L;
    public mxGraph graph;
    public Object parent;
    private double angle = 0;


    Graph() {
        setSize(750,400);
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


}