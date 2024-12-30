package a01b.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {

    private final Map<JButton, Pair<Integer, Integer>> mappa = new HashMap<>();
    Logics logic;

    public GUI(int size) {
        logic = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);

        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                var button = (JButton) e.getSource();
                Pair<Integer, Integer> pos = mappa.get(button);
                logic.hit(pos);
                updateCells();
                if (logic.toQuit()){
                    System.exit(0);
                }
            }
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton(" ");
                this.mappa.put(jb, new Pair<Integer, Integer>(i, j));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }
    private void updateCells() {
        for (Map.Entry<JButton, Pair<Integer, Integer>> entry : mappa.entrySet()) {
            if (logic.isEnabled(entry.getValue())){
                entry.getKey().setText("*");
            }else{
                entry.getKey().setText("");
            }
        }
    }
}
