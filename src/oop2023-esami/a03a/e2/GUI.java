package a03a.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    Logics logic;

    public GUI(int width, int height) {
        logic = new LogicsImpl(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * width, 100 * height);

        JPanel panel = new JPanel(new GridLayout(width, height));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();
            Pair<Integer, Integer> pos = cells.get(jb);
            logic.hit(pos);
            draw();
        };

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                var pos = new Pair<>(j, i);
                final JButton jb = new JButton();
                this.cells.put(jb, pos);
                if (logic.get(pos)) {
                    jb.setText("O");
                }
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

    private void draw() {
        for (Map.Entry<JButton, Pair<Integer, Integer>> entry : cells.entrySet()) {
            if (logic.isPresent(entry.getValue())) {
                entry.getKey().setText("*");
            }
        }
    }

}
