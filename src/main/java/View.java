import javax.swing.*;
import java.awt.*;

public class View {
    private static final Graph graph = new Graph();
    private static final JTextField textFieldGraph = new JTextField(10);
    private static final GraphControlPanel graphCP = new GraphControlPanel(graph);

    public static void createGUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setVisible(true);

        //create panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(addComponentsToPane());
        panel.add(graphCP);
        frame.add(panel);

        frame.pack();

    }




    public static JPanel addComponentsToPane() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //for graph
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.NONE;//size const
        c.gridheight = 2;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10, 20, 0, 10);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        panel.add(graph, c);

        //for Implementation steps
        c.insets = new Insets(10, 25, 0, 25);
        c.gridx = 2;
        c.gridy = 3;
        c.gridheight = 1;
        panel.add(new Label("Implementation steps:"), c);

        //textFieldGraph.getPreferredSize(new Dimension())
        c.insets = new Insets(35, 25, 0, 25);
        panel.add(textFieldGraph, c);
        return panel;


    }
}
