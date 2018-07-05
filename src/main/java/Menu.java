import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

public class Menu extends JFrame {
    private static Font font = new Font("Verdana", Font.ITALIC, 16);
    private static JMenu fileMenu = new JMenu("File");
    private static JMenu createGraph = new JMenu("Create graphPicture");
    private static JMenuItem createList = new JMenuItem("List view");
    private static JMenuItem createMatrix = new JMenuItem("Matrix view");

    private static JMenuItem openItem = new JMenuItem("Open graphPicture");
    private static JMenuItem saveItem = new JMenuItem("Save graphPicture");
    private static JMenuItem exitItem = new JMenuItem("Exit");

    private static TextArea fileText;

    public static JMenu doFileMenu() {
        fileMenu.setFont(font);

        createGraph.setFont(font);
        fileMenu.add(createGraph);

        createList.setFont(font);
        createGraph.add(createList);
        createList();

        createMatrix.setFont(font);
        createGraph.add(createMatrix);
        createMatrix();

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

    private static void createMatrix() {
        createMatrix.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TextArea textArea = new TextArea("Matrix");
            }
        });
    }

    public static void saveAction() {
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveImage(View.graphPicture);
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

                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showDialog(null, "Открыть файл");
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String str = chooser.getSelectedFile().getPath();
                    System.out.println(str);
                    fileText = new TextArea("Open");
                    fileText.area1.append(read(str));
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
