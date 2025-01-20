package a01b.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<Position, JButton> cells = new HashMap<>();
    Logics logic;
    
    public GUI(int size) {
        logic = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
            var jb = (JButton)e.getSource();
            Position pos = new Position(size, size);
            for (Map.Entry<Position, JButton> entry : cells.entrySet()) {
                if (entry.getValue().equals(jb)){
                    pos = entry.getKey();
                }
            }
            logic.hit(pos);
            draw();
            if (logic.toQuit()){
                System.out.println("ciao bro");
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
            	var pos = new Position(j,i);
                final JButton jb = new JButton();
                this.cells.put(pos, jb);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

    private void draw(){
        int c = 1;
        for (JButton jb : cells.values()) {
            jb.setText("");
        }
        for (Position p : logic.get()) {
            cells.get(p).setText(""+c++);
        }
    }
    
}
