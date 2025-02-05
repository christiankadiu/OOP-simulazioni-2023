package a05.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Position> cells = new HashMap<>();
    Logics logic;

    public GUI(int width) {
        logic = new LogicsImpl(width);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70 * width, 70 * width);

        JPanel panel = new JPanel(new GridLayout(width, width));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();
            Position pos = cells.get(jb);
            logic.hit(pos);
            draw();
            if (logic.toQuit()) {
                System.out.println("you lost");
            }
        };

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                var pos = new Position(j, i);
                final JButton jb = new JButton();
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        draw();
        this.setVisible(true);
    }

    private void draw() {
        for (Map.Entry<JButton, Position> entry : cells.entrySet()) {
            if (logic.get(entry.getValue()) == 1) {
                entry.getKey().setText("P");
            } else if (logic.get(entry.getValue()) == 2) {
                entry.getKey().setText("C");
            } else {
                entry.getKey().setText("");
            }
        }
    }

}
