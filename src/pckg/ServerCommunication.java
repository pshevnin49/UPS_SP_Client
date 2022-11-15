package pckg;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerCommunication extends Thread{

    String ip;
    int port;
    int roomNumber;
    Socket socket;
    BufferedReader br;
    PrintWriter pw;
    Game game;


    public ServerCommunication(String ip, int port) throws IOException {
        this.ip = "127.0.0.1"; // 192.168.56.101
        this.port = 5;
        //startServer();
    }

    public int[][] getStartedField() throws IOException {

        String input = new String();
        String output = "UPSconn";
        String nickname = Player.nickname;
        String nicknameLength = String.format("%04d", nickname.length());
        String allLength = String.format("%04d", nickname.length() + 18);
        String roomNumStr = String.format("%03d", roomNumber);

        output = output + allLength + nicknameLength + nickname + roomNumStr;

        System.out.println(output);

        pw.println("PT");
        pw.flush();

        input = br.readLine();
        System.out.println(input);

        this.start();

        int[][] fieldList = parsLine(input);

        return fieldList;
    }

    public void run(){
        String input;

        while(true){
            try {
                input = br.readLine();

                if(input != null){
                    CoordXY outFrom = new CoordXY(Character.digit(input.charAt(2), 10), Character.digit(input.charAt(3), 10));
                    CoordXY outTo = new CoordXY(Character.digit(input.charAt(4), 10), Character.digit(input.charAt(5), 10));

                    game.moveChecker(outFrom, outTo);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
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

    public void move(CoordXY from, CoordXY to) throws IOException {

        String output = "PT";
        output += from.getX();
        output += from.getY();
        output += to.getX();
        output += to.getY();

        pw.println(output);
        pw.flush();


    }

    public List<Room> getLobby(){
        List<Room> rooms = new ArrayList<>();
        for(int i = 0; i < 50; i++){
            rooms.add(new Room(i+1, 1));
        }
        return rooms;
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

    public void setGame(Game game){
        this.game = game;
    }
}
