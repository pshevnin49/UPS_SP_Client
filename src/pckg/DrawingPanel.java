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
import java.util.ArrayList;
import java.util.List;

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

    public void mouseClick(int x, int y){

        int xCell = x/75;
        int yCell = y/75;

        CoordXY mirrorCoord = mirroredCoord(xCell, yCell);

        if(isClicked){
            List<CoordXY> path = clickedCell.getPotencialPath();

            for(int i = 0; i < path.size(); i++){
                if(path.get(i).getX() == mirrorCoord.getX() && path.get(i).getY() == mirrorCoord.getY()){

                    System.out.println("Pokus chodit ");
                    // mirroring of 'end' because here we working with matrix on "server"
                    CoordXY endXY = new CoordXY(path.get(i).getX(), path.get(i).getY());
                    CoordXY startXY = new CoordXY(clickedCell.getCoord().getX(), clickedCell.getCoord().getY());

                    moveChecker(startXY, endXY);
                    clickedCell = new ClickedCell();
                    isClicked = false;
                    continue;

                }
            }
        }

        if(checkers[mirrorCoord.getY()][mirrorCoord.getX()] == Player.side){

            isClicked = true;
            CoordXY coordXY = new CoordXY();
            coordXY.setX(xCell);
            coordXY.setY(yCell);
            clickedCell.setCoord(mirrorCoord);
            possibleMoves(clickedCell);
            repaint();

        }

        System.out.println(mirrorCoord.getX() + " - x, " + mirrorCoord.getY() + " - y");
    }

    public void paint (Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D)g;
        drawField(g2);

        drawClicked(g2);


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

                if(newCheckers[y][x] == 1){
                    coordX = nullX + x * cellSize;
                    coordY = nullY + y * cellSize;
                    drawChecker(g2, coordX, coordY, "white_checker.png");
                }

                if(newCheckers[y][x] == 2){
                    coordX = nullX + x * cellSize;
                    coordY = nullY + y * cellSize;
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
                newCheckers[newY][newX] = checkers[y][x];
                newY--;
            }
            newX--;

        }

        return newCheckers;
    }

    private void drawClicked(Graphics2D g2){

        if(isClicked){
            g2.setColor(new Color(79, 177, 105));
            System.out.println("Print clicked ");
            CoordXY mirrorRect = mirroredCoord(clickedCell.getCoord().getX(), clickedCell.getCoord().getY());
            g2.fillRect(cellSize * mirrorRect.getX(), cellSize * mirrorRect.getY(), cellSize, cellSize);

            for(int i = 0; i < clickedCell.getPotencialPath().size(); i++){
                CoordXY coord = mirroredCoord(clickedCell.getPotencialPath().get(i).getX(), clickedCell.getPotencialPath().get(i).getY());
                g2.fillRect(cellSize * coord.getX(), cellSize * coord.getY(), cellSize, cellSize);
            }

        }
        else{
            System.out.println("Not clicked ");
        }

    }

    private void drawChecker(Graphics2D g2, int x, int y, String imgName) throws IOException {

        BufferedImage white = ImageIO.read(new File(imgName));
        g2.drawImage(white, x, y, 65, 65, null);

    }

    private void moveChecker(CoordXY start, CoordXY end){

        System.out.println(start.getX() + " x " +  start.getY() + " y  odstranen");
        System.out.println(end.getX() + " x " +  end.getY() + " y  pridan");

        checkers[start.getY()][start.getX()] = 0;
        checkers[end.getY()][end.getX()] = Player.side;

    }

    /**
     * On server coords are stored in matrix. This matrix is one for both sides
     * thats why if player is on white side, whe need to send on server mirrored coords
     * this method has on input CoordsXY, and on output mirrored CoordsXY on game field
     * @return
     */
    private CoordXY mirroredCoord(int coordX, int coordY){

        int[] mirrowList = {7, 6, 5, 4, 3, 2, 1, 0};
        CoordXY mirrorCoord = new CoordXY();
        if(Player.side == 1){
            mirrorCoord.setX(mirrowList[coordX]);
            mirrorCoord.setY(mirrowList[coordY]);
        }else{
            mirrorCoord.setX(coordX);
            mirrorCoord.setY(coordY);
        }

        return mirrorCoord;

    }

    private void possibleMoves(ClickedCell clickedCell){
        CoordXY coord = clickedCell.getCoord();
        List<CoordXY> coords = processingCell(coord);

        clickedCell.setPotencialPath(coords);
    }

    private List<CoordXY> processingCell(CoordXY coordXY){ // нужно сохранить удаляемые фишки, придумать, как не создавать лишние ходы, скорее всего ерунда
        List<CoordXY> coords = new ArrayList<>(); // посмотреть гитхаб

        try {
//            if(checkers[coordXY.getY() + 1][coordXY.getX() - 1] == 0){
//                coords.add(new CoordXY(coordXY.getX() - 1, coordXY.getY() + 1));
//            }
//            if(checkers[coordXY.getY() + 1][coordXY.getX() - 1] != 0 && checkers[coordXY.getY() + 1][coordXY.getX() - 1] != Player.side){
//                coords.addAll(processingCell(new CoordXY(coordXY.getX() - 2, coordXY.getY() + 2)));
//            }
        }catch (Exception e){

        }
        try {
//            if(checkers[coordXY.getY() + 1][coordXY.getX() + 1] == 0){
//                coords.add(new CoordXY(coordXY.getX() + 1, coordXY.getY() + 1));
//            }
//            if(checkers[coordXY.getY() + 1][coordXY.getX() + 1] != 0 && checkers[coordXY.getY() + 1][coordXY.getX() + 1] != Player.side){
//                coords.addAll(processingCell(new CoordXY(coordXY.getX() + 2, coordXY.getY() + 2)));
//            }
        }catch (Exception e){

        }
        try {
            if(checkers[coordXY.getY() - 1][coordXY.getX() - 1] == 0){
                coords.add(new CoordXY(coordXY.getX() - 1, coordXY.getY() - 1));
            }

            if(checkers[coordXY.getY() - 1][coordXY.getX() - 1] != 0 && checkers[coordXY.getY() - 1][coordXY.getX() - 1] != Player.side){// добавить лист и проверять длину, если ноль, то рисовать клетку
                CoordXY newCoord = new CoordXY(coordXY.getX() - 2, coordXY.getY() - 2);
                CoordXY checkerCoord = new CoordXY(coordXY.getX() - 1, coordXY.getY() - 1);

                List<CoordXY> newCoords = processingCell(newCoord);
                coords.addAll(newCoords);

                newCoord.addChecker(checkerCoord);
                System.out.println(coordXY.getDeletedCeckers().size() + " size");
                if(coordXY.getDeletedCeckers().size() > 0){
                    System.out.println(coordXY.getDeletedCeckers().size() + " size");
                    newCoord.addCheckers(coordXY.getDeletedCeckers());
                }

                if(checkers[coordXY.getY() - 2][coordXY.getX() - 2] == 0 && newCoords.size() == 0){
                    coords.add(new CoordXY(coordXY.getX() - 2, coordXY.getY() - 2));
                }
            }

        }catch (Exception e){
            System.out.println(e.toString());
        }
        try {
            if(checkers[coordXY.getY() - 1][coordXY.getX() + 1] == 0){
                coords.add(new CoordXY(coordXY.getX() + 1, coordXY.getY() - 1));
            }

            if(checkers[coordXY.getY() - 1][coordXY.getX() + 1] != 0 && checkers[coordXY.getY() - 1][coordXY.getX() + 1] != Player.side){


                List<CoordXY> newCoords = processingCell(new CoordXY(coordXY.getX() + 2, coordXY.getY() - 2));
                coords.addAll(newCoords);

                if(checkers[coordXY.getY() - 2][coordXY.getX() + 2] == 0 && newCoords.size() == 0){
                    coords.add(new CoordXY(coordXY.getX() + 2, coordXY.getY() - 2));
                }
            }

        }catch (Exception e){

        }

        return coords;

    }

}
