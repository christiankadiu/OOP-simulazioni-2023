package a03a.e2;

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
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
        	    var position = cells.get(button);
            }
        };
        
        Random r = new Random();
        Pair<Integer, Integer> giocatore = new Pair<Integer,Integer>(r.nextInt(size) + 1, r.nextInt(size) + 1);
        Pair<Integer, Integer> computer = new Pair<Integer,Integer>(r.nextInt(size) + 1, r.nextInt(size) + 1);
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                Pair<Integer, Integer> pos = new Pair<Integer,Integer>(i, j);
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }    
}
