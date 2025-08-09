package a05.e2;

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
            draw();
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, j);
                final JButton jb = new JButton("" + logic.getNumber(p));
                this.cells.put(jb, p);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

    private void draw() {
        for (Map.Entry<JButton, Pair<Integer, Integer>> entry : cells.entrySet()) {
            if (logic.getNumber(entry.getValue()) == 10) {
                entry.getKey().setText("X");
            } else {
                entry.getKey().setText("" + logic.getNumber(entry.getValue()));
            }
        }
    }

}
