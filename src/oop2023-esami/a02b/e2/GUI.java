package a02b.e2;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    Logics logic;

    public GUI(int size) {
        logic = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);

        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();
            Pair<Integer, Integer> p = cells.get(jb);
            logic.hit(p);
            updateCells();
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                var pos = new Pair<>(j, i);
                final JButton jb = new JButton();
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

    public void updateCells() {
        for (Map.Entry<JButton, Pair<Integer, Integer>> entry : cells.entrySet()) {
            if (logic.isPresent(entry.getValue())) {
                entry.getKey().setText("*");
            }
        }
    }
}
