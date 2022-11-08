package pckg;

public class ServerCommunication {

    String ip;
    int port;
    int roomNumber;

    public ServerCommunication(String ip, int port, int roomNumber){
        this.ip = ip;
        this.port = port;
        this.roomNumber = roomNumber;
    }

    public int[][] getField(){
        int[][] fieldList = {{0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {2, 0, 2, 0, 2, 0, 2, 0},
                {0, 2, 0, 2, 0, 2, 0, 2},
                {2, 0, 2, 0, 2, 0, 2, 0}};

        return fieldList;
    }
}
