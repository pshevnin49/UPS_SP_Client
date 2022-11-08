package pckg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartForm {

    JFrame frame;
    SessionInf information;
    ImageIcon icon = new ImageIcon("icon.png");

    public StartForm(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);// posice okna centrum

        JLabel errorLabel = new JLabel();
        errorLabel.setBounds(400/2 - 150/2,30,150, 40);
        frame.add(errorLabel);

        JLabel ipLabel = new JLabel("ip address:");
        ipLabel.setBounds(400/2 - 65/2,60,65, 40);
        frame.add(ipLabel);

        JTextField ipTField = new JTextField();
        ipTField.setBounds(400/2 - 200/2,90,200, 20);
        frame.add(ipTField);

        JLabel portLabel = new JLabel("port:");
        portLabel.setBounds(400/2 - 30/2,100,30, 40);
        frame.add(portLabel);

        JTextField portTField = new JTextField();
        portTField.setBounds(400/2 - 200/2,130,200, 20);
        frame.add(portTField);

        JLabel nickLabel = new JLabel("nickname:");
        nickLabel.setBounds(400/2 - 60/2,140,60, 40);
        frame.add(nickLabel);

        JTextField nickTField = new JTextField();
        nickTField.setBounds(400/2 - 200/2,170,200, 20);
        frame.add(nickTField);

        JLabel roomLabel = new JLabel("room number:");
        roomLabel.setBounds(400/2 - 90/2,180,90, 40);
        frame.add(roomLabel);

        JTextField roomTField = new JTextField();
        roomTField.setBounds(400/2 - 200/2,210,200, 20);
        frame.add(roomTField);

        JButton startButton = new JButton("Start");
        startButton.setBounds(400/2 - 100/2,400,100, 40);
        frame.add(startButton);//adding button in JFrame

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String ip = ipTField.getText();
                    int port = Integer.parseInt(portTField.getText());
                    String nickname = nickTField.getText();
                    int roomNumber = Integer.parseInt(roomTField.getText());

                    information = new SessionInf(port, ip, roomNumber, nickname);
                    Player.nickname = nickname;
                    frame.dispose();

                }catch (Exception ex){
                    errorLabel.setText("Error: something is wrong");
                    errorLabel.setForeground(Color.RED);
                }

            }
        });


        frame.setSize(400,500);//400 width and 500 height
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
        frame.setIconImage(icon.getImage());
        frame.setResizable(false);
    }

}
