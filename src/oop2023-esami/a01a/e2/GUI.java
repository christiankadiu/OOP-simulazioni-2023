package a01a.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<Pair<Integer, Integer>, JButton> cells = new HashMap<>();
    Logics logic;

    public GUI(int size) {
        logic = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);

        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();
            Pair<Integer, Integer> pos = new Pair<Integer, Integer>(null, null);
            for (Map.Entry<Pair<Integer, Integer>, JButton> entry : cells.entrySet()) {
                if (entry.getValue().equals(jb)) {
                    pos = entry.getKey();
                }
            }
            logic.hit(pos);
            draw();
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                var pos = new Pair<>(j, i);
                final JButton jb = new JButton();
                this.cells.put(pos, jb);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

    private void draw() {
        int c = 0;
        for (Map.Entry<Pair<Integer, Integer>, JButton> entry : cells.entrySet()) {
            entry.getValue().setText("");
        }
        for (Pair<Integer, Integer> p : logic.getNumbers()) {
            cells.get(p).setText("" + c++);
        }
    }

}
