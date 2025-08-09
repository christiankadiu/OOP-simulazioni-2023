package a02b.e2;

import javax.swing.*;

import a05.e2.Position;
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
        JButton go = new JButton("go");
        main.add(BorderLayout.SOUTH, go);

        ActionListener check = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logic.get();
                draw();
                if (logic.restart()) {
                    logic = new LogicsImpl(size);
                    draw();
                }
            }
        };

        go.addActionListener(check);

        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                var button = (JButton) e.getSource();
                var position = cells.get(button);
                logic.hit(position);
                draw();
            }
        };

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
            if (logic.isPresent(entry.getValue())) {
                entry.getKey().setText("*");
            }
            if (logic.isDisabled(entry.getValue())) {
                entry.getKey().setEnabled(false);
            }
        }
    }
}
