import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends JMenuBar {
    private static Font font = new Font("Verdana", Font.PLAIN, 11);
    private static JMenu fileMenu = new JMenu("File");
    private static JMenuItem createGraph = new JMenuItem("Create graph");
    private static JMenuItem openItem = new JMenuItem("Open graph");
    private static JMenuItem saveItem = new JMenuItem("Save graph");
    private static JMenuItem exitItem = new JMenuItem("Exit");
    private static Graph graph = new Graph();

    public static JMenu doFileMenu(){
        fileMenu.setFont(font);

        createGraph.setFont(font);
        fileMenu.add(createGraph);

        openItem.setFont(font);
        fileMenu.add(openItem);
        openAction();

        saveItem.setFont(font);
        fileMenu.add(saveItem);
        saveAction();

        exitItem.setFont(font);
        fileMenu.add(exitItem);
        exitAction();
        return fileMenu;
    }

    public static void saveAction(){
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //saveImage(View.graphCP);// с этим работает

                saveImage(GraphControlPanel.graphPanel(graph));
            }
        });
    }

    public static void exitAction(){
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void openAction(){
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    /*todo*/
                }
            }
        });

    }

    public static void saveImage(JPanel panel){
        BufferedImage image =(BufferedImage)
                panel.createImage(panel.getWidth(), panel.getHeight());
        Graphics2D g2 = image.createGraphics();
        panel.paint(g2);
        g2.dispose();
        try {
            ImageIO.write(image, "jpeg", new File("MyImg.jpeg"));
        }
        catch(IOException io) { io.printStackTrace(); }
    }
}
