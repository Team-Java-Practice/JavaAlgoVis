import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;

class Graph extends JPanel {
    private static final long serialVersionUID = 1L;
    private mxGraph graph;
    private Object parent;
    private int angle = 0;

    Graph() {
        this.graph = new mxGraph();
        this.parent = graph.getDefaultParent();
        graph.getModel().beginUpdate();
        try {
            Object v1 = graph.insertVertex(parent, null, "1", 20, 20, 80, 30);
            Object v2 = graph.insertVertex(parent, null, "2", 300, 150, 80, 30);
            graph.insertEdge(parent, null, "Edge", v1, v2);
        } finally {
            graph.getModel().endUpdate();
        }
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        this.setLayout(new BorderLayout());
        this.add(graphComponent, BorderLayout.CENTER);
    }

    public void addNewVertex(){
        findEmptyZone();
        angle +=20;

        graph.insertVertex(parent,null, "new", 160 + 70*Math.cos(angle) , 75 + 70*Math.sin(angle), 80, 30);
    }

    private void findEmptyZone(){
        /* todo */
    }
}