import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphControlPanel extends JPanel {
    private static Graph graph = null;
    private static final JButton butAdd = new JButton("Add");
    private static final JButton butDelete = new JButton("Delete");
    private static final JButton butEdge = new JButton("Create edge");
    private static final JButton butStartV = new JButton("Start Vertex");
    private static final JButton butEndV = new JButton("End Vertex");
    private static final JButton butStartAlgo = new JButton("Start Dijkstra");

    public GraphControlPanel(Graph graph) {
        super(null);
        GraphControlPanel.graph = graph;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = getGridBagConstraints();

        addPanel_1(gbc);
        addPanel_2(gbc);

        butAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GraphControlPanel.graph.addNewVertex();
            }
        });
    }

    private void addPanel_2(GridBagConstraints gbc) {
        JPanel dop2Panel = new JPanel();
        dop2Panel.setLayout(new FlowLayout());
        dop2Panel.add(butStartV);
        dop2Panel.add(butEndV);
        dop2Panel.add(butStartAlgo);

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

        add(new JLabel("Work with graph"), c);
        return c;
    }
}
