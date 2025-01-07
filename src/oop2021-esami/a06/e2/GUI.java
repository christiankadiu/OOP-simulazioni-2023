package a06.e2;

import javax.swing.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private final JButton quit = new JButton("QUIT");
    Logics logic;

    public GUI(int size) {
        logic = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);

        JPanel grid = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(BorderLayout.CENTER, grid);
        this.getContentPane().add(BorderLayout.SOUTH, quit);

        quit.addActionListener(e -> {
            if (logic.compute()) {
                for (JButton button : cells.keySet()) {
                    button.setEnabled(false);
                }
            } else {
                updateCells();
            }
        });

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();
            Pair<Integer, Integer> pos = cells.get(jb);
            if (logic.hit(pos)) {
                jb.setText("O");
            }
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton("  ");
                Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, j);
                this.cells.put(jb, p);
                grid.add(jb);
                jb.addActionListener(al);
            }
        }
        this.setVisible(true);
    }

    public void updateCells() {
        for (Map.Entry<JButton, Pair<Integer, Integer>> entry : cells.entrySet()) {
            if (logic.is(entry.getValue())) {
                entry.getKey().setText("*");
            }
        }
    }
}