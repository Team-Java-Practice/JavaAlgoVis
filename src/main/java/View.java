import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class View {
    private static Graph graph =new Graph();
    private static JTextField textFieldGraph = new JTextField(10);
    private static JButton butAdd = new JButton("Add");
    private static JButton butDelete = new JButton("Delete");
    private static JButton butEdge = new JButton("Create edge");
    private static JButton butStartV = new JButton("Start Vertex");
    private static JButton butEndV = new JButton("End Vertex");
    private static JButton butStartAlgo = new JButton("Start Dijkstra");

    public static void createGUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setVisible(true);

        //create panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(addComponentsToPane());
        panel.add(createGraphPanel());
        frame.add(panel);


        butAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                graph.graph.insertVertex(graph.parent,null, "new", 100, 100, 80, 30);
            }
        });
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

    private static JPanel createGraphPanel() {
        JPanel graphPanel = new JPanel();
        graphPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.NONE;//size const
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 20, 0, 20);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
//        BufferedImage image= ImageIO.read("E:\git_project\GraphVis\src\main\resources\arrow.png");
//        JLabel label = new JLabel(icon1);
//        panel.add(label).setBounds(10,10,27,30);
        graphPanel.add(new JLabel("Work with graph"), c);



        c.gridx = 1;
        c.gridheight = 1;

        JPanel dop1Panel = new JPanel();
        dop1Panel.setLayout(new FlowLayout());
        dop1Panel.add(butAdd);
        dop1Panel.add(butDelete);
        dop1Panel.add(butEdge);

        graphPanel.add(dop1Panel, c);

        c.gridy = 1;

        JPanel dop2Panel = new JPanel();
        dop2Panel.setLayout(new FlowLayout());
        dop2Panel.add(butStartV);
        dop2Panel.add(butEndV);
        dop2Panel.add(butStartAlgo);

        graphPanel.add(dop2Panel, c);
        return graphPanel;
    }


}
