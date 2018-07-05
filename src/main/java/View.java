import javax.swing.*;
import java.awt.*;

public class View {
    private static JFrame frame;
    public static final Graph graphPicture = new Graph();
    private static final JTextArea textArea = new JTextArea(10,18);
    private static final GraphControlPanel graphCP = new GraphControlPanel(graphPicture);
    private static Font font = new Font("Verdana", Font.PLAIN, 16);
    private static JLabel label = new JLabel("          Implementation steps:");

    public static void createGUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(Menu.doFileMenu());
        frame.setJMenuBar(menuBar);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(addComponentsToPane());
        panel.add(graphCP);
        frame.add(panel);

        frame.setSize(900, 700);
        frame.setVisible(true);
        frame.setResizable(false);
        //frame.pack();
    }

    public static JPanel addComponentsToPane(){
        JPanel dopPanel = new JPanel();
        dopPanel.setLayout(new BoxLayout(dopPanel,BoxLayout.Y_AXIS));
        label.setFont(font);
        dopPanel.add(label);
        dopPanel.add(textArea);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        graphPicture.setPreferredSize(new Dimension(700,400));
        panel.add(graphPicture);
        panel.add(dopPanel);

        return panel;
    }
}
