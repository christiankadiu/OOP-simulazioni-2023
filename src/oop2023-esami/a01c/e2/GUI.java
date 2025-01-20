package a01c.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Position> cells = new HashMap<>();
    Logics logic;
    int count = 1;
    
    public GUI(int size) {
        logic = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
            var jb = (JButton)e.getSource();
            Position pos = cells.get(jb);
            if (logic.hit(pos)){
                jb.setText(""+count++);
            }else{
                draw();
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
            	var pos = new Position(j,i);
                final JButton jb = new JButton();
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

    private void draw(){
        for (Map.Entry<JButton, Position> entry : cells.entrySet()) {
            if (logic.isPresent(entry.getValue())){
                if (!(entry.getKey().getText().equals("1") || entry.getKey().getText().equals("2"))){
                    entry.getKey().setText("0");
                }
            }
        }
    }
    
}
