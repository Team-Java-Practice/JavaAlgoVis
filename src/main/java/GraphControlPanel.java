import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGraphModel;
import javafx.util.Pair;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GraphControlPanel extends JPanel {
    private static Graph graph = null;
    private static final JButton butAdd = new JButton("Add");
    private static final JButton butDelete = new JButton("Delete");
    private static final JButton butEdge = new JButton("Add edge");
    private static final JButton butStartV = new JButton("Start Vertex");
    private static final JButton butEndV = new JButton("End Vertex");
    private static final JButton butStartAlgo = new JButton("Start Dijkstra");

    private static final JButton butUndo = new JButton("Undo");
    private static final JButton butRedo = new JButton("Redo");
    private static Font font = new Font("Verdana", Font.BOLD, 16);
    private static JLabel label = new JLabel("Work with graph");


    private static JSpinner fromSpinner;
    private static JSpinner toSpinner;

    public static int counter = 1;
    private static int step;

    private static GraphStruct graphStruct = new GraphStruct();
    private static List<State<Integer>> history = new ArrayList<>();

    public GraphControlPanel(final Graph graph) {
        super(null);
        GraphControlPanel.graph = graph;
        setLayout(new GridBagLayout());
        final GridBagConstraints gbc = getGridBagConstraints();

        addPanel_1(gbc);
        addPanel_2(gbc);

        butAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                graphStruct.addVertex();
                graph.paintGraph(graphStruct, history, step, View.textArea );
            }
        });
        butDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                graph.graph.selectChildCell();
                Object[] cells = graph.graph.removeCells();


                for (int i = 0; i < cells.length; i++) {
                    if(cells[i] instanceof mxCell){

                        mxCell c = (mxCell)cells[i];
                        if((Integer)c.getValue()instanceof Integer) {


                            int vrtx = (Integer) c.getValue();

                            if (graphStruct.isVertexValue(vrtx)) {
                                graphStruct.removeVertex(vrtx);
                            }
                        }

                    }

                }

            }
        }
        );




        butEdge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (Pair<Integer, Integer> integerIntegerPair : graphStruct.getEdges()) {
                    if (integerIntegerPair.getKey() == fromSpinner.getValue() && integerIntegerPair.getValue() == toSpinner.getValue())
                        JOptionPane.showMessageDialog(null, "Некорректный ввод(такое ребро уже существует)", "Attention", JOptionPane.ERROR_MESSAGE);
                }
                graphStruct.addEdge(fromSpinner, toSpinner);

                graph.paintGraph(graphStruct, history, step, View.textArea );
            }
        });


        butStartAlgo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MutableGraph<Vertex<Integer>> graph_ = GraphBuilder.<Integer>directed().allowsSelfLoops(true).build();
                for (int i = 0; i < graphStruct.getNumberOfVertexes(); i++) {
                    graph_.addNode(new Vertex<>(i));
                }

                for (Pair<Integer, Integer> edge : graphStruct.getEdges()) {
                    graph_.putEdge(new Vertex<>(edge.getKey()), new Vertex<>(edge.getValue()));
                }

                history =Model.dijkstra(graph_, new Vertex<>(1));
                step = history.size();

                graph.paintGraph(graphStruct, history, step, View.textArea );
            }
        });

        butUndo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(step>1 && history!=null) {
                    step--;
                    graph.paintGraph(graphStruct, history, step, View.textArea);
                }
            }
        });

        butRedo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(step<history.size()){
                    step++;
                    graph.paintGraph(graphStruct, history, step, View.textArea);
                }
            }
        });
    }



    private void addPanel_2(GridBagConstraints gbc) {
        JPanel dop2Panel = new JPanel();
        dop2Panel.setLayout(new FlowLayout());
        dop2Panel.add(butStartV);
        dop2Panel.add(butEndV);
        dop2Panel.add(butStartAlgo);
        dop2Panel.add(butUndo);
        dop2Panel.add(butRedo);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(dop2Panel, gbc);
    }

    private void addPanel_1(GridBagConstraints gbc) {
        JPanel dop1Panel = new JPanel();
        dop1Panel.setLayout(new FlowLayout());
        dop1Panel.add(butAdd);
        dop1Panel.add(butDelete);
        dop1Panel.add(butEdge);

        //спиннер для начальной вершины
        SpinnerModel fromSpinnerModel = new SpinnerNumberModel(1, 1, 50, 1);
        fromSpinner = new JSpinner(fromSpinnerModel);
        fromSpinner.setSize(new Dimension(100, 50));
        fromSpinner.setLocation(10, 155);

        //cпиннер для конечной вершины
        SpinnerModel toSpinnerModel = new SpinnerNumberModel(1, 1, 50, 1);
        toSpinner = new JSpinner(toSpinnerModel);
        toSpinner.setSize(new Dimension(100, 50));
        toSpinner.setLocation(100, 155);

        dop1Panel.add(fromSpinner);
        dop1Panel.add(toSpinner);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(dop1Panel, gbc);
    }

    private GridBagConstraints getGridBagConstraints() {
        GridBagConstraints c = new GridBagConstraints();

        // Не изменяются далее
        c.fill = GridBagConstraints.NONE; //size const
        c.insets = new Insets(10, 20, 0, 20);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;

        // Далее будут изменяться
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;

        label.setFont(font);
        add(label, c);
        return c;
    }
}
