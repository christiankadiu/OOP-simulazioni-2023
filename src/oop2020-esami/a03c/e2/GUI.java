package a03c.e2;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private final JButton next = new JButton(">");
    int count = 0;
    Logics logic;

    public GUI(int size) {
        logic = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);

        JPanel grid = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(BorderLayout.CENTER, grid);
        this.getContentPane().add(BorderLayout.SOUTH, next);

        next.addActionListener(e -> cells.forEach((b, p) -> {
            if (p.getX() == 0)
                b.setText("*");
        }));

        ActionListener el = e -> {
            var button = (JButton) e.getSource();
            var position = this.cells.get(button);
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Pair<>(j, i));
                grid.add(jb);
                jb.addActionListener(el);
            }
        }

        JButton head = get(logic.get());
        head.setText("" + count++);
        Pair<Integer, Integer> p = new Pair<Integer, Integer>(cells.get(head).getX(), cells.get(head).getY() + 1);
        get(p).setText("" + count++);

        this.setVisible(true);
    }

    private JButton get(Pair<Integer, Integer> pos) {
        for (Map.Entry<JButton, Pair<Integer, Integer>> entry : cells.entrySet()) {
            if (entry.getValue().equals(pos)) {
                return entry.getKey();
            }
        }
        return null;
    }

}
