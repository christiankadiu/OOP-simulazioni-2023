package a04.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
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
                button.setText(""+position);
            }
        };

        Random r = new Random();
        int uno;
        int due;
        do{
            uno = r.nextInt(size*size) + 1;
            due = r.nextInt(size*size) + 1;
        }while(uno == due);
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Pair<>(i, j));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        System.out.println("uno vale: "+uno);
        System.out.println("due vale: "+due);
        int i = 0;
        for (Map.Entry<JButton, Pair<Integer, Integer>> entry : cells.entrySet()) {
            if (i == uno){
                entry.getKey().setText("R");
            }
            if (i == due){
                entry.getKey().setText("K");
            }
            i++;
        }
        this.setVisible(true);
    }    
}
