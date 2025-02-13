package a06.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private static final long serialVersionUID = -6218820567019985015L;
    private final HashMap<JButton, Position> cells = new HashMap<>();
    Logics logic;
    int c = 0;

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
            jb.setText("" + logic.getNumber(pos));
            c++;
            draw(pos);
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
        this.setVisible(true);
    }

    private void draw(Position pos) {
        if (c == 3) {
            for (Map.Entry<JButton, Position> entry : cells.entrySet()) {
                if (entry.getValue() != pos) {
                    entry.getKey().setText("");
                }
            }
            c = 0;
        }
        for (Map.Entry<JButton, Position> entry : cells.entrySet()) {
            if (logic.isDisabled(entry.getValue())) {
                entry.getKey().setEnabled(false);
            }
        }
    }
}
