package a03b.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    Logics logic;

    public GUI(int width, int height) {
        logic = new a03b.e2.LogicsImpl(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70 * width, 70 * height);

        JPanel panel = new JPanel(new GridLayout(width, height));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();
            Pair<Integer, Integer> pos = cells.get(jb);
            if (logic.hit(pos)) {
                jb.setText("*");
            } else {
                updateCells();
            }
        };

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
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
