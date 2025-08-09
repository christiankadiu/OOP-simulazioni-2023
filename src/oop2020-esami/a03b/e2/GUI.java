package a03b.e2;

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

        JPanel grid = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(BorderLayout.CENTER, grid);

        ActionListener el = e -> {
            var button = (JButton) e.getSource();
            var position = this.cells.get(button);
            logic.hit(position);
            draw();
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton();
                this.cells.put(jb, new Pair<>(j, i));
                grid.add(jb);
                jb.addActionListener(el);
            }
        }
        draw();
        this.setVisible(true);
    }

    private void draw() {
        for (Map.Entry<JButton, Pair<Integer, Integer>> entry : cells.entrySet()) {
            entry.getKey().setEnabled(true);
        }
        for (Map.Entry<JButton, Pair<Integer, Integer>> entry : cells.entrySet()) {
            entry.getKey()
                    .setText(logic.get(entry.getValue()) == 1 ? "B" : logic.get(entry.getValue()) == 2 ? "*" : " ");
            if (!logic.isEnabled(entry.getValue())) {
                entry.getKey().setEnabled(false);
            }
        }
    }

}
