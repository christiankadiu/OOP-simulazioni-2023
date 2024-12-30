package a01b.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {

    private final Map<Pair<Integer, Integer>, JButton> mappa = new HashMap<>();
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
                Pair<Integer, Integer> pos = new Pair<>(null, null);
                for (Map.Entry<Pair<Integer, Integer>, JButton> entry : mappa.entrySet()) {
                    if (entry.getValue().equals(button)) {
                        pos = entry.getKey();
                    }
                }
                for (Pair<Integer, Integer> p : logic.get(pos)) {
                    if (mappa.get(p).getText().isEmpty()) {
                        mappa.get(p).setText("*");
                    } else {
                        mappa.get(p).setText("");
                    }
                }
            }
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton(" ");
                this.mappa.put(new Pair<Integer, Integer>(i, j), jb);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }
}
