package a02b.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    Logics logic;
    
    public GUI(int size) {
        logic = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel grid = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,grid);
        JButton north = new JButton("NORTH");
        JButton south = new JButton("SOUTH");
        this.getContentPane().add(BorderLayout.NORTH,north);
        this.getContentPane().add(BorderLayout.SOUTH,new JButton("south"));
        
        north.addActionListener(e -> {
        	System.out.println("north");
        });
        
        south.addActionListener(e -> {
        	logic.setSouth();
        });
        
        ActionListener al = e -> {
        	var jb = (JButton)e.getSource();
        	Pair<Integer, Integer> pos = this.cells.get(jb);
            if (logic.hit(pos)){
                jb.setText("*");
            }
        	
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Pair<>(i, j));
                grid.add(jb);
                jb.addActionListener(al);
            }
        }
        this.setVisible(true);
    }    
}
