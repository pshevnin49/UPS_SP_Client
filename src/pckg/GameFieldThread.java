package pckg;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;

public class GameFieldThread extends Thread {

    Game game;
    ImageIcon icon = new ImageIcon("icon.png");
    ServerCommunication server;

    public GameFieldThread(ServerCommunication server) throws IOException {

        this.server = server;
        this.game = new Game(server);

    }

    public void run(){

        JFrame frame = new JFrame();
        frame.setTitle("Checkers");

        frame.setSize(613, 635);
        frame.setResizable(false);
        frame.setIconImage(icon.getImage());

        DrawingPanel panel = new DrawingPanel(game);
        game.setPanel(panel);
        game.setJFrame(frame);
        server.setGame(game);
        frame.add(panel);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);// posice okna centrum
        frame.setVisible(true);

    }


}
