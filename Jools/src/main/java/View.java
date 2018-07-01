import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;

public class View {

    public static void createGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton butAdd = createButton("Add", 100, 150);
        JButton butDelete = createButton("Delete", 150, 150);

        panel.add(new Graph(), BorderLayout.CENTER);
        panel.add(butAdd, BorderLayout.NORTH);
        panel.add(butDelete, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.setPreferredSize(new Dimension(500, 400));

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private static JButton createButton(String text, int x, int y) {
        JButton left = new JButton(text);
        left.setBounds(x, y, 50, 50);
        left.setFocusPainted(false);
        left.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        return left;
    }


}
