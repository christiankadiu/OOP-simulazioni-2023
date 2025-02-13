package a02a.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private static final long serialVersionUID = -6218820567019985015L;
    private final List<JButton> cells = new ArrayList<>();

    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);

        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();
            jb.setText(String.valueOf(cells.indexOf(jb)));
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                var pos = new Pair<>(j, i);
                final JButton jb = new JButton(pos.toString());
                this.cells.add(jb);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

}
