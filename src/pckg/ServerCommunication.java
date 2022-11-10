package pckg;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ServerCommunication extends Thread{

    String ip;
    int port;
    int roomNumber;
    Socket socket;
    BufferedReader br;
    PrintWriter pw;

    public ServerCommunication(String ip, int port, int roomNumber) throws IOException {
        this.ip = "127.0.0.1";
        this.port = 4;
        this.roomNumber = roomNumber;
        startServer();

    }

    public int[][] getStartedField() throws IOException {
//        int[][] fieldList = {{0, 1, 0, 1, 0, 1, 0, 1},
//                {1, 0, 1, 0, 1, 0, 1, 0},
//                {0, 1, 0, 1, 0, 1, 0, 1},
//                {0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0},
//                {2, 0, 2, 0, 2, 0, 2, 0},
//                {0, 2, 0, 2, 0, 2, 0, 2},
//                {2, 0, 2, 0, 2, 0, 2, 0}};

        String input = new String();

        pw.println("PT");
        pw.flush();

        input = br.readLine();
        System.out.println(input);
        int[][] fieldList = parsLine(input);

        return fieldList;
    }

    private void startServer() throws IOException {
        this.socket = new Socket(ip, port);

        this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

    }

    public void closeServer() throws IOException {
        br.close();
        pw.close();
        socket.close();
    }

    public MoveInf move(CoordXY from, CoordXY to) throws IOException {
        MoveInf move;
        String output = "PT";
        output += from.getX();
        output += from.getY();
        output += to.getX();
        output += to.getY();

        pw.println(output);
        pw.flush();

        String input = br.readLine();

        CoordXY outFrom = new CoordXY(Character.digit(input.charAt(2), 10), Character.digit(input.charAt(3), 10));
        CoordXY outTo = new CoordXY(Character.digit(input.charAt(4), 10), Character.digit(input.charAt(5), 10));

        move = new MoveInf(outFrom, outTo);
        return move;
    }

    private int[][] parsLine(String line){
        int index = 0;
        int[][] matrix = new int[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                matrix[i][j] = Character.digit(line.charAt(index), 10);
                System.out.println(matrix[i][j] + " matrix");
                System.out.println(line.charAt(index));
                index++;
            }
        }
        return matrix;
    }
}
