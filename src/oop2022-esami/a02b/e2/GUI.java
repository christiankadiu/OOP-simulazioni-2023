package a02b.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {

    private final Map<Pair<Integer, Integer>, JButton> cells = new HashMap<>();
    Logics logic;
    int size;

    public GUI(int size) {
        this.size = size;
        logic = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);

        JPanel main = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(main);
        main.add(BorderLayout.CENTER, panel);
        JButton check = new JButton("check > restart");
        main.add(BorderLayout.SOUTH, check);

        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                var button = (JButton) e.getSource();
                Pair<Integer, Integer> position = new Pair<>(null, null);
                for (Map.Entry<Pair<Integer, Integer>, JButton> entry : cells.entrySet()) {
                    if (entry.getValue().equals(button)) {
                        position = entry.getKey();
                    }
                }
                if (logic.get(position)) {
                    button.setText("*");
                } else {
                    button.setText("");
                }
            }
        };

        ActionListener go = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (logic.toQuit()) {
                    update();
                    logic = new LogicsImpl(size);
                } else {
                    for (Pair<Integer, Integer> pair : logic.checkDiagonals()) {
                        if (cells.containsKey(pair)) {
                            cells.get(pair).setEnabled(false);
                        }
                    }
                }
            }
        };

        check.addActionListener(go);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton(" ");
                this.cells.put(new Pair<>(i, j), jb);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

    public void update() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                Pair<Integer, Integer> pos = new Pair<Integer, Integer>(i, j);
                this.cells.get(pos).setEnabled(true);
                this.cells.get(pos).setText("");
            }
        }
    }
}
