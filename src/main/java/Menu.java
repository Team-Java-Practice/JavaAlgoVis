import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Menu extends JFrame {
    private static Font font = new Font("Verdana", Font.ITALIC, 16);
    private static JMenu fileMenu = new JMenu("File");
    private static JMenu createGraph = new JMenu("Create graph");
    private static JMenuItem createList = new JMenuItem("List view");

    private static JMenuItem openItem = new JMenuItem("Open graph");
    private static JMenuItem saveItem = new JMenuItem("Save graph");
    private static JMenuItem exitItem = new JMenuItem("Exit");

    private static TextArea fileText;
    private static GraphStruct graphStruct = new GraphStruct();
    private static Graph graph= new Graph();
    private static List<State<Integer>> history = new ArrayList<>();
    private static int step;

    public static JMenu doFileMenu() {
        fileMenu.setFont(font);

        createGraph.setFont(font);
        fileMenu.add(createGraph);

        createList.setFont(font);
        createGraph.add(createList);
        createList();

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

    private static void createList() {
        createList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TextArea textArea = new TextArea("List");
            }
        });
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

    public static void openAction() {
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JFileChooser fileChooser = new JFileChooser();
                int returnVal = fileChooser.showDialog(null, "Открыть файл");
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String string = fileChooser.getSelectedFile().getPath();
                    fileText = new TextArea("Open");
                    fileText.area1.append(read(string));
                    }
            }
        });
    }

    public static String read(String fileName) {
        StringBuilder sb = new StringBuilder();
        File file = new File(fileName);
        try {
            FileInputStream fstream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;

            while ((strLine = br.readLine()) != null) {
                sb.append(strLine + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
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
