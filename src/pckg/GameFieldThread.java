package pckg;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class GameFieldThread extends Thread {

    int[][] fieldList; // all figures on the field



    ImageIcon icon = new ImageIcon("icon.png");

    public GameFieldThread(ServerCommunication server){
        this.fieldList = server.getField();

    }


    public void run(){

        JFrame frame = new JFrame();
        frame.setTitle("Checkers");

        frame.setSize(613, 635);
        frame.setResizable(false);
        frame.setIconImage(icon.getImage());

        DrawingPanel panel = new DrawingPanel(fieldList);

        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);// posice okna centrum
        frame.setVisible(true);

    }


}
