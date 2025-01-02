package a05.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {

    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    Logics logic;

    public GUI(int size) {
        logic = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);

        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                var button = (JButton) e.getSource();
                var position = cells.get(button);
                logic.hit(position);
                updateCells();
                if (logic.toQuit()) {
                    System.exit(0);
                }
            }
        };
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton();
                Pair<Integer, Integer> pos = new Pair<Integer, Integer>(i, j);
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
                if (i % 2 == 0) {
                    if (j % 2 != 0) {
                        jb.setEnabled(false);
                    }
                } else {
                    if (j % 2 == 0) {
                        jb.setEnabled(false);
                    }
                }

                if (jb.isEnabled()) {
                    switch (logic.isSomething(pos)) {
                        case 0:
                            jb.setText("");
                            break;
                        case 1:
                            jb.setText("H");
                            break;
                        case 2:
                            jb.setText("C");
                            break;
                    }
                }
            }
        }
        this.setVisible(true);
    }

    void updateCells() {
        for (Map.Entry<JButton, Pair<Integer, Integer>> entry : cells.entrySet()) {
            switch (logic.isSomething(entry.getValue())) {
                case 0:
                    entry.getKey().setText("");
                    break;
                case 1:
                    entry.getKey().setText("H");
                    break;
                case 2:
                    entry.getKey().setText("C");
                    break;
            }
        }
    }
}
