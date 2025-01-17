package a05.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
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
            var pos = cells.get(jb);
            logic.hit(pos);
            draw();
            if (logic.toQuit()) {
                disableAll();
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
            entry.getKey().setText("");
        }
        for (Map.Entry<JButton, Position> entry : cells.entrySet()) {
            entry.getKey().setText(logic.isSomething(entry.getValue()) == 1 ? "P"
                    : logic.isSomething(entry.getValue()) == 2 ? "E" : "");
        }
    }

    private void disableAll() {
        for (Map.Entry<JButton, Position> entry : cells.entrySet()) {
            entry.getKey().setEnabled(false);
        }
    }

}
