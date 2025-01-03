package a06.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {

    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    Logics logic;

    public GUI(int size) {
        logic = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);

        JPanel main = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(main);
        main.add(BorderLayout.CENTER, panel);
        JButton go = new JButton("fire");
        main.add(BorderLayout.SOUTH, go);

        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logic.hit();
                updateCells();
            }
        };

        go.addActionListener(al);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton(Integer.toString(logic.getNumber(i, j)));
                this.cells.put(jb, new Pair<Integer, Integer>(i, j));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }
    void updateCells(){
        for (Map.Entry<JButton, Pair<Integer, Integer>> entry : cells.entrySet()) {
            entry.getKey().setText(""+logic.getNumber(entry.getValue().getX(), entry.getValue().getY()));
        }
    }
}
