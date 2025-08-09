package a04.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {

    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    Logics logic;
    int size;

    public GUI(int size) {
        this.size = size;
        logic = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);

        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                var button = (JButton) e.getSource();
                var position = cells.get(button);
                if (logic.hit(position)) {
                    button.setText("K");
                }
                updateCells();
                if (logic.toQuit()) {
                    if (logic.win()) {
                        System.out.println("sconfitta");
                    } else {
                        System.out.println("vittoria");
                    }
                    logic = new LogicsImpl(size);
                }
            }
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton();
                Pair<Integer, Integer> pos = new Pair<Integer, Integer>(i, j);
                switch (logic.isSomething(pos)) {
                    case 1:
                        jb.setText("K");
                        break;
                    case 2:
                        jb.setText("R");
                        break;
                    case 0:
                        jb.setText(" ");
                }
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

    void updateCells() {
        for (Map.Entry<JButton, Pair<Integer, Integer>> entry : cells.entrySet()) {
            switch (logic.isSomething(entry.getValue())) {
                case 1:
                    entry.getKey().setText("K");
                    break;
                case 2:
                    entry.getKey().setText("R");
                    break;
                case 0:
                    entry.getKey().setText(" ");
            }
        }
    }
}
