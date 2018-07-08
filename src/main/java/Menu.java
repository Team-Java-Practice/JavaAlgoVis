import javafx.util.Pair;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Menu extends JFrame {
    private static Font font = new Font("Verdana", Font.ITALIC, 16);
    private static JMenu fileMenu = new JMenu("File");

    private static JMenuItem saveItem = new JMenuItem("Save graph(.jpeg)");
    private static JMenuItem exitItem = new JMenuItem("Exit");

    private static GraphStruct graphStruct = new GraphStruct();

    public static JMenu doFileMenu() {
        fileMenu.setFont(font);

        saveItem.setFont(font);
        fileMenu.add(saveItem);
        saveAction();

        exitItem.setFont(font);
        fileMenu.add(exitItem);
        exitAction();
        return fileMenu;
    }

    public static void saveAction() {
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveImage(View.graph);
            }
        });
    }


    public static void exitAction() {
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void saveImage(JPanel panel) {
        BufferedImage image = (BufferedImage)
                panel.createImage(panel.getWidth(), panel.getHeight());
        Graphics2D g2 = image.createGraphics();
        panel.paint(g2);
        g2.dispose();
        try {
            ImageIO.write(image, "jpeg", new File("MyImg.jpeg"));
        } catch (IOException io) {
            io.printStackTrace();
        }
    }


}