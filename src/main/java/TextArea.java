import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class TextArea extends JFrame {
    public JTextArea area1;
    private static Font font = new Font("Verdana", Font.PLAIN, 16);

    private JButton createGr;

    public TextArea(String title) {
        super(title);

        area1 = new JTextArea(10, 10);
        area1.setFont(font);
        area1.setTabSize(10);

        // Параметры переноса слов
        //setLineWrap(true);
        //setWrapStyleWord(true);

        // Добавим полe в окно
        JPanel panel = new JPanel();
        panel.add(new JScrollPane(area1));
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

       setContentPane(panel);

        createGr = new JButton("Create graph");

        //расположение кнопки по центру
        createGr.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(createGr);

        createGr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        // Выводим окно на экран
        setSize(250, 300);
        setVisible(true);
    }
}
