import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGraphModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphControlPanel extends JPanel {
    private static Graph graph = null;
    private static final JButton butAdd = new JButton("Add");
    private static final JButton butDelete = new JButton("Delete");
    private static final JButton butEdge = new JButton("Delete edge");
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

    public GraphControlPanel(final Graph graph) {
        super(null);
        GraphControlPanel.graph = graph;
        setLayout(new GridBagLayout());
        final GridBagConstraints gbc = getGridBagConstraints();

        addPanel_1(gbc);
        addPanel_2(gbc);

        butAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GraphControlPanel.graph.addNewVertex();
                counter++;
            }
        });
        butDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                graph.graph.selectChildCell();
                graph.graph.removeCells();
                counter--;
            }
        }
        );

        butEdge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /*int vertexFrom = (Integer) fromSpinner.getValue();
                int vertexTo = (Integer) toSpinner.getValue();



                mxCell cellFrom = (mxCell) ((mxGraphModel)graph.graph.getModel()).getCell(vertexFrom+"");
                mxCell cellTo = (mxCell) ((mxGraphModel)graph.graph.getModel()).getCell(vertexTo+"");
                System.out.println(cellFrom.getId());
                System.out.println(cellTo.getId());
               graph.graph.insertEdge(graph.graph.getDefaultParent(), null, "Edge", cellFrom, cellTo)*/;
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
