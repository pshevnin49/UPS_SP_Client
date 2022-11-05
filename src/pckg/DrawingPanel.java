package pckg;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

public class DrawingPanel extends JPanel {

    int cellSize = 600/8;
    int[][] checkers;
    boolean isClicked = false;
    ClickedCell clickedCell = new ClickedCell();


    public DrawingPanel(int[][] checkers){
        this.checkers = checkers;


        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

                mouseClick(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.setFocusable(true);
    }




    public void paint (Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D)g;
        drawField(g2);

        try {
            drawCheckers(checkers, g2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void drawField(Graphics2D g){
        //int cellSize = 600/8; // 800 will be changed on real window size
        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){

                if(x % 2 == 0 && y % 2 == 0 || x % 2 == 1 && y % 2 == 1){
                    g.setColor(new Color(183, 165, 153));
                }else{
                    g.setColor(new Color(111, 92, 78));
                }

                g.fillRect(cellSize * x, cellSize * y, cellSize, cellSize);
            }
        }
    }

    private void drawCheckers(int[][] checkers, Graphics2D g2) throws IOException {

        int nullX = 5;
        int nullY = 5;
        int coordX = 0;
        int coordY = 0;
        int[][] newCheckers = checkers;

        if(Player.side == 1){
            newCheckers = invCheckers(checkers);
        }

        for(int x = 0; x < newCheckers.length; x++){
            for(int y = 0; y < newCheckers[x].length; y++){

                if(newCheckers[x][y] == 1){
                    coordX = nullX + y * cellSize;
                    coordY = nullY + x * cellSize;
                    drawChecker(g2, coordX, coordY, "white_checker.png");
                }

                if(newCheckers[x][y] == 2){
                    coordX = nullX + y * cellSize;
                    coordY = nullY + x * cellSize;
                    drawChecker(g2, coordX, coordY, "black_checker.png");
                }

            }
        }
    }

    private int[][] invCheckers(int[][] checkers){ // invert checkers field in y coords
        int[][] newCheckers = new int[8][8];

        int newX = 7;
        for(int x = 0; x < checkers.length; x++) {
            int newY = 7;
            for (int y = 0; y < checkers[0].length; y++) {
                newCheckers[newX][newY] = checkers[x][y];
                newY--;
            }
            newX--;

        }

        return newCheckers;

    }

    private void drawChecker(Graphics2D g2, int x, int y, String imgName) throws IOException {

        BufferedImage white = ImageIO.read(new File(imgName));
        g2.drawImage(white, x, y, 65, 65, null);

    }

    public void mouseClick(int x, int y){
        System.out.println(x + " - x, " + y + " - y");
        int[] mirrowList = {7, 6, 5, 4, 3, 2, 1, 0};

        int xCell = x/75;
        int yCell = y/75;

        if(Player.side == 1){
            xCell = mirrowList[xCell];
            yCell = mirrowList[yCell];
        }

        if(checkers[xCell][yCell] == Player.side){
            isClicked = true;
            clickedCell.setX(xCell);
            clickedCell.setY(yCell);
            System.out.println("Your check");
        }

        System.out.println(checkers[xCell][yCell]);

        System.out.println(xCell + " - x, " + yCell + " - y");
    }



}
