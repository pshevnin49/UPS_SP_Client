package pckg;

public class Client {

    public static int position;

    public static void main(String args[]){

        int[][] fieldList = {{0, 1, 0, 1, 0, 1, 0, 1},
                             {1, 0, 1, 0, 1, 0, 1, 0},
                             {0, 1, 0, 1, 0, 1, 0, 1},
                             {0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 0},
                             {2, 0, 2, 0, 2, 0, 2, 0},
                             {0, 2, 0, 2, 0, 2, 0, 2},
                             {2, 0, 2, 0, 2, 0, 2, 0}};

        StartForm startForm = new StartForm();
        System.out.println(Player.nickname + " - nickname");

        Player.side = 1; // 1 - white side (2 - dark side)

        Player.nickname = "nickname";

        //GameFieldThread thread = new GameFieldThread(fieldList, 2);
        //thread.start();

    }
}
