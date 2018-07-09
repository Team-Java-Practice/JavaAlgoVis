import com.google.common.graph.*;
import com.mxgraph.model.mxCell;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class GraphControlPanel extends JPanel {
    private static Graph graph = null;
    private static final JButton butAdd = new JButton("Add");
    private static final JButton butDelete = new JButton("Delete");
    private static final JButton butEdge = new JButton("Add edge");
    private static final JButton butOpen = new JButton("Open file");

    private static final JButton butStartAlgo = new JButton("Start Dijkstra");
    private static final JButton butClearField = new JButton("Clear Field");

    private static final JButton butUndo = new JButton("Undo");
    private static final JButton butRedo = new JButton("Redo");
    private static Font font = new Font("Verdana", Font.BOLD, 16);
    private static JLabel label = new JLabel("Work with graph");

    private static JSpinner fromSpinner;
    private static JSpinner toSpinner;
    public static JSpinner weightSpinner;

    public static int counter = 1;
    public static int step;

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
                graph.paintGraph(graphStruct, history, step, View.textArea);

                butStartAlgo.setEnabled(true);
                butStartAlgo.setEnabled(true);
                butClearField.setEnabled(true);
                butDelete.setEnabled(true);
                butEdge.setEnabled(true);
            }
        });

        butDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                graph.graph.selectChildCell();
                Object[] cells = graph.graph.removeCells();

                if (cells.length>0) {
                    for (int i = 0; i < 1; i++) {

                        if (cells[i] instanceof mxCell) {
                            mxCell c = (mxCell) cells[i];
                            System.out.println(c.getValue()+" i="+i);
                            if (c.getValue() != null) {
                                int vrtx = (Integer) c.getValue();
                                if (graphStruct.checkValue(vrtx)) {
                                    graphStruct.removeVertex(vrtx);
                                }
                            }
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Вершин больше нет", "Attention", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        butClearField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphStruct.clear();
                history = new ArrayList<>();
                step = 0;
                graph.paintGraph(graphStruct, history, step, View.textArea);

                butDelete.setEnabled(false);
                butEdge.setEnabled(false);
                butStartAlgo.setEnabled(false);

                butAdd.setEnabled(true);
                butUndo.setEnabled(true);
                butRedo.setEnabled(true);
            }
        });

        butEdge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean firstVertex = false;
                boolean secondVertex = false;
                for (Vertex<Integer> list :  graphStruct.getListOfVertexes()) {
                    if(list.getValue() == fromSpinner.getValue()){
                        firstVertex=true;
                    }
                    if(list.getValue() == toSpinner.getValue()) {
                        secondVertex = true;
                    }
                }
                if(firstVertex && secondVertex){
                    for (Map.Entry<Pair<Integer,Integer>, Integer> entry : graphStruct.getEdges().entrySet()) {
                        if (entry.getKey().getKey() == fromSpinner.getValue() && entry.getKey().getValue() == toSpinner.getValue())
                            JOptionPane.showMessageDialog(null, "Ребро уже существует", "Attention", JOptionPane.ERROR_MESSAGE);
                    }
                    graphStruct.addEdge(fromSpinner, toSpinner,weightSpinner);

                    graph.paintGraph(graphStruct, history, step, View.textArea );
                }
                else{
                    JOptionPane.showMessageDialog(null, "Введите другие вершины", "Attention", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });

        butStartAlgo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                butRedo.setEnabled(true);
                butUndo.setEnabled(true);

                butDelete.setEnabled(false);
                butEdge.setEnabled(false);
                butAdd.setEnabled(false);

                MutableValueGraph<Vertex<Integer>, Integer> gr = ValueGraphBuilder.directed().allowsSelfLoops(true).build();

                for (int i = 0; i < graphStruct.getNumberOfVertexes(); i++) {
                    gr.addNode(new Vertex<>(i));
                }

                for (Map.Entry<Pair<Integer, Integer>, Integer> edge : graphStruct.getEdges().entrySet()) {
                    gr.putEdgeValue(new Vertex<>(edge.getKey().getKey()), new Vertex<>(edge.getKey().getValue()), edge.getValue());
                }

                history = Model.dijkstra(gr, new Vertex<>(1));
                step = 1;

                graph.paintGraph(graphStruct, history, step, View.textArea);
            }
        });

        butUndo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (step > 1 && history != null) {
                    step--;
                    graph.paintGraph(graphStruct, history, step, View.textArea);
                } else {
                    JOptionPane.showMessageDialog(null, "Первый шаг алгоритма", "Attention", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        butRedo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (step < history.size()) {
                    step++;
                    graph.paintGraph(graphStruct, history, step, View.textArea);
                } else {
                    JOptionPane.showMessageDialog(null, "Алгоритм закончил работу", "Attention", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        butOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphStruct.clear();
                JFileChooser fileChooser = new JFileChooser();
                int returnVal = fileChooser.showDialog(null, "Открыть файл");
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        HashSet<Integer> vertexes = new HashSet<>();
                        File selectedFile = fileChooser.getSelectedFile();
                        for (String str : Files.readAllLines(Paths.get(selectedFile.getPath()))) {
                            String[] s = str.split(" ");
                            int v = Integer.parseInt(s[0]);
                            int w = Integer.parseInt(s[1]);
                            int weight = Integer.parseInt(s[2]);
                            vertexes.add(v);
                            vertexes.add(w);
                            for (Map.Entry<Pair<Integer, Integer>, Integer> entry : graphStruct.getEdges().entrySet()) {
                                if (entry.getKey().getKey() == v && entry.getKey().getValue() == w) {
                                    JOptionPane.showMessageDialog(null, "Некорректный ввод", "Attention", JOptionPane.ERROR_MESSAGE);
                                    graphStruct.clear();
                                    history = new ArrayList<>();
                                    step = 0;
                                    graph.paintGraph(graphStruct, history, step, View.textArea);
                                    View.frame.repaint();
                                    return;
                                }
                            }
                            graphStruct.addEdge(v, w, weight);
                        }
                        vertexes.forEach(integer -> graphStruct.addVertex());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Некорректный данные!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } catch (NullPointerException ex) {
                        //число пар меньше чем задано во 2 поле
                        JOptionPane.showMessageDialog(null, "Несоответствие заданного и фактического количества ребер!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } catch (IndexOutOfBoundsException ex) {
                        //связываются не существующие вершины
                        JOptionPane.showMessageDialog(null, "Попытка связать несуществующие вершины!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                graph.paintGraph(graphStruct, history, step, View.textArea);
                butStartAlgo.setEnabled(true);
                butStartAlgo.setEnabled(true);
                butClearField.setEnabled(true);
                butDelete.setEnabled(true);
                butEdge.setEnabled(true);
            }
        });
    }

    private void addPanel_2(GridBagConstraints gbc) {
        JPanel dop2Panel = new JPanel();
        dop2Panel.setLayout(new FlowLayout());
        dop2Panel.add(butOpen);
        dop2Panel.add(butClearField);
        dop2Panel.add(butStartAlgo);
        dop2Panel.add(butUndo);
        dop2Panel.add(butRedo);

        butClearField.setEnabled(false);
        butUndo.setEnabled(false);
        butRedo.setEnabled(false);
        butStartAlgo.setEnabled(false);

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

        butDelete.setEnabled(false);
        butEdge.setEnabled(false);

        //спиннер для начальной вершины
        SpinnerModel fromSpinnerModel = new SpinnerNumberModel(1, 1, 50, 1);
        fromSpinner = new JSpinner(fromSpinnerModel);
        fromSpinner.setSize(new Dimension(100, 50));
        fromSpinner.setLocation(10, 155);
        fromSpinner.setToolTipText("from");

        //cпиннер для конечной вершины
        SpinnerModel toSpinnerModel = new SpinnerNumberModel(1, 1, 50, 1);
        toSpinner = new JSpinner(toSpinnerModel);
        toSpinner.setSize(new Dimension(100, 50));
        toSpinner.setLocation(10, 155);

        //cпиннер для веса
        SpinnerModel weightSpinnerModel = new SpinnerNumberModel(1, 1, 50, 1);
        weightSpinner = new JSpinner(weightSpinnerModel);
        weightSpinner.setSize(new Dimension(100, 50));
        weightSpinner.setLocation(10, 155);

        JPanel panelFromSp = new JPanel();
        panelFromSp.setLayout(new BoxLayout(panelFromSp,BoxLayout.Y_AXIS));
        panelFromSp.add(new JLabel("from"));
        panelFromSp.add(fromSpinner);

        JPanel panelToSp = new JPanel();
        panelToSp.setLayout(new BoxLayout(panelToSp,BoxLayout.Y_AXIS));
        panelToSp.add(new JLabel("to"));
        panelToSp.add(toSpinner);

        JPanel panelWeightSp = new JPanel();
        panelWeightSp.setLayout(new BoxLayout(panelWeightSp,BoxLayout.Y_AXIS));
        panelWeightSp.add(new JLabel("weight"));
        panelWeightSp.add(weightSpinner);

        dop1Panel.add(panelFromSp);
        dop1Panel.add(panelToSp);
        dop1Panel.add(panelWeightSp);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(dop1Panel, gbc);
    }

    private GridBagConstraints getGridBagConstraints() {
        GridBagConstraints c = new GridBagConstraints();

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

        label = new JLabel("Work with graph");
        font = new Font("Verdana", Font.BOLD, 16);
        label.setFont(font);
        add(label, c);
        return c;
    }
}