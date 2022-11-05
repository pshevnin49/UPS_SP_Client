package pckg;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class GameFieldThread extends Thread {

    int[][] fieldList; // all figures on the field
    int side; // side of playng white or black (white is 1, black is 2)


    ImageIcon icon = new ImageIcon("icon.png");

    public GameFieldThread(int[][] fieldList, int side){
        this.fieldList = fieldList;
        this.side = side;
    }


    public void run(){

        JFrame frame = new JFrame();
        frame.setTitle("Checkers");

        frame.setSize(615, 635);
        frame.setResizable(false);
        frame.setIconImage(icon.getImage());

        DrawingPanel panel = new DrawingPanel(fieldList);

        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);// posice okna centrum
        frame.setVisible(true);

    }


}
