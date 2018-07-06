import javax.swing.*;
import java.awt.*;

public class View {
    private static JFrame frame;
    public static final Graph graph = new Graph();
    public static final JTextArea textArea = new JTextArea(10,18);
    private static final GraphControlPanel graphCP = new GraphControlPanel(graph);
    private static Font font = new Font("Verdana", Font.PLAIN, 16);
    private static JLabel label = new JLabel("Steps:");

    public static void createGUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea.setEditable(false);
        textArea.setLineWrap(true);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(Menu.doFileMenu());
        frame.setJMenuBar(menuBar);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(addComponentsToPane());
        panel.add(graphCP);
        frame.add(panel);

        frame.setSize(1200, 700);
        frame.setVisible(true);
        frame.setResizable(false);
        //frame.pack();
    }

    public static JPanel addComponentsToPane(){
        JPanel dopPanel = new JPanel();
        dopPanel.setLayout(new BoxLayout(dopPanel,BoxLayout.Y_AXIS));
        label.setFont(font);
        dopPanel.add(label);
        textArea.setPreferredSize(new Dimension(200,400));
        textArea.setLineWrap(true);
        dopPanel.add(textArea);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        graph.setPreferredSize(new Dimension(700,400));
        panel.add(graph);
        panel.add(dopPanel);

        return panel;
    }
}
