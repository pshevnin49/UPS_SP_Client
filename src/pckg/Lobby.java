package pckg;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Lobby {

    JFrame frame;
    ImageIcon icon = new ImageIcon("icon.png");
    List<Room> rooms;

    public Lobby(ServerCommunication server){
        frame = new JFrame();
        frame.setTitle("Lobby");
        frame.getContentPane().setBackground(new Color(111, 92, 78));
        rooms = server.getLobby();

        DefaultListModel<String> l1 = new DefaultListModel<>();

        for(int i = 0; i < rooms.size(); i++){
            l1.addElement(rooms.get(i).toString());
        }

        JList<String> list = new JList<>(l1);

        list.setBackground(new Color(183, 165, 153));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);

        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setBounds(400/2 - 181,30, 350,350);
        frame.add(listScroller);


//        GameFieldThread gameThread = new GameFieldThread(server);
//        gameThread.drawField();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,500);//400 width and 500 height
        frame.setLayout(null);//using no layout managers
        frame.setLocationRelativeTo(null);// posice okna centrum
        frame.setVisible(true);//making the frame visible
        frame.setIconImage(icon.getImage());
        frame.setResizable(false);

    }




}
