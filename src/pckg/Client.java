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

        Player.side = 2; // white (2 - black)

        System.out.println(fieldList[0][0] + " - 0 0");
        System.out.println(fieldList[0][1] + " - 0 1");

        Player.nickname = "nickname";

        GameFieldThread thread = new GameFieldThread(fieldList, 2);
        thread.start();

    }
}
