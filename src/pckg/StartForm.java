package pckg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartForm {

    JFrame frame;
    ImageIcon icon = new ImageIcon("icon.png");
    int[][] fieldList;

    public StartForm(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(111, 92, 78));

        JLabel errorLabel = new JLabel();
        errorLabel.setBounds(400/2 - 150/2,30,150, 40);
        frame.add(errorLabel);

        JLabel ipLabel = new JLabel("ip address:");
        ipLabel.setForeground(new Color(183, 165, 153));
        ipLabel.setBounds(400/2 - 65/2,60,65, 40);
        frame.add(ipLabel);

        JTextField ipTField = new JTextField();
        ipTField.setBackground(new Color(183, 165, 153));
        ipTField.setBounds(400/2 - 200/2,90,200, 20);
        frame.add(ipTField);

        JLabel portLabel = new JLabel("port:");
        portLabel.setForeground(new Color(183, 165, 153));
        portLabel.setBounds(400/2 - 30/2,100,30, 40);
        frame.add(portLabel);

        JTextField portTField = new JTextField();
        portTField.setBackground(new Color(183, 165, 153));
        portTField.setBounds(400/2 - 200/2,130,200, 20);
        frame.add(portTField);

        JLabel nickLabel = new JLabel("nickname:");
        nickLabel.setForeground(new Color(183, 165, 153));
        nickLabel.setBounds(400/2 - 60/2,140,60, 40);
        frame.add(nickLabel);

        JTextField nickTField = new JTextField();
        nickTField.setBackground(new Color(183, 165, 153));
        nickTField.setBounds(400/2 - 200/2,170,200, 20);
        frame.add(nickTField);

        JLabel roomLabel = new JLabel("room number:");
        roomLabel.setForeground(new Color(183, 165, 153));
        roomLabel.setBounds(400/2 - 90/2,180,90, 40);
        frame.add(roomLabel);

        JTextField roomTField = new JTextField();
        roomTField.setBackground(new Color(183, 165, 153));
        roomTField.setBounds(400/2 - 200/2,210,200, 20);
        frame.add(roomTField);

        JButton startButton = new JButton("Start");
        startButton.setBackground(new Color(183, 165, 153));
        startButton.setBounds(400/2 - 100/2,350,100, 40);
        frame.add(startButton);//adding button in JFrame

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String ip = ipTField.getText();
                    int port = Integer.parseInt(portTField.getText());
                    String nickname = nickTField.getText();
                    int roomNumber = Integer.parseInt(roomTField.getText());
                    Player.nickname = nickname;
                    ServerCommunication server = new ServerCommunication(ip, port, roomNumber);

                    fieldList = server.getStartedField();

                    Player.side = 1; // 1 - white side (2 - dark side)
                    frame.dispose();

                    GameFieldThread gameThread = new GameFieldThread(server);
                    gameThread.start();


                }catch (Exception ex){
                    errorLabel.setText("Error: something is wrong");
                    errorLabel.setForeground(Color.RED);
                }

            }
        });

        frame.setSize(400,500);//400 width and 500 height

        frame.setLayout(null);//using no layout managers
        frame.setLocationRelativeTo(null);// posice okna centrum
        frame.setVisible(true);//making the frame visible
        frame.setIconImage(icon.getImage());
        frame.setResizable(false);
    }

}
