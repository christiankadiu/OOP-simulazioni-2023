package a06.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {

    private final Map<JButton, Position> cells = new HashMap<>();
    Logics logic;

    public GUI(int size) {
        logic = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);

        JPanel main = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(main);
        main.add(BorderLayout.CENTER, panel);
        JButton go = new JButton("Go");
        main.add(BorderLayout.SOUTH, go);

        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                var button = (JButton) e.getSource();
                var position = cells.get(button);
                button.setText("" + position);
            }
        };

        ActionListener exc = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logic.collapse();
                draw();
            }
        };

        go.addActionListener(exc);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton(" ");
                Position pos = new Position(i, j);
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        draw();
        this.setVisible(true);
    }

    private void draw() {
        for (Map.Entry<JButton, Position> entry : cells.entrySet()) {
            entry.getKey().setText("" + logic.getNumber(entry.getValue()));
        }
    }
}
