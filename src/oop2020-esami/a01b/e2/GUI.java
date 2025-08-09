package a01b.e2;

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
            var button = (JButton) e.getSource();
            var position = cells.get(button);
            logic.hit(position);
            updateCells();
            if (logic.toQuit()) {
                System.exit(0);
            }
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton();
                Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, j);
                this.cells.put(jb, p);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        updateCells();
        this.setVisible(true);
    }

    public void updateCells() {
        for (Map.Entry<JButton, Pair<Integer, Integer>> entry : cells.entrySet()) {
            entry.getKey()
                    .setText(logic.get(entry.getValue()) == 0 ? " " : logic.get(entry.getValue()) == 1 ? "K" : "P");
        }
    }
}
