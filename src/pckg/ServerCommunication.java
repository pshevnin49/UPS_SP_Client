package pckg;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ServerCommunication {

    String ip;
    int port;
    int roomNumber;
    Socket socket;
    BufferedReader br;
    PrintWriter pw;

    public ServerCommunication(String ip, int port, int roomNumber) throws IOException {
        this.ip = String.valueOf(InetAddress.getByName("localhost"));
        this.port = 4;
        this.roomNumber = roomNumber;
        //startServer();

    }

    public int[][] getStartedField() throws IOException {
        int[][] fieldList = {{0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {2, 0, 2, 0, 2, 0, 2, 0},
                {0, 2, 0, 2, 0, 2, 0, 2},
                {2, 0, 2, 0, 2, 0, 2, 0}};

//        pw.println("PT");
//        pw.flush();


        //System.out.println(br.read());


        //closeServer();
        return fieldList;
    }

    private void startServer() throws IOException {
        this.socket = new Socket(ip, port);

        this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

    }

    private void closeServer() throws IOException {
        br.close();
        pw.close();
        socket.close();
    }
}
