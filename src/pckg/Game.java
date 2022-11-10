package pckg;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {

    int[][] checkersField;
    boolean isClicked;
    ClickedCell clickedCell;
    ServerCommunication server;
    DrawingPanel panel;
    int movingSide;
    JFrame frame;

    public Game(ServerCommunication server) throws IOException {

        this.server = server;
        isClicked = false;
        clickedCell = new ClickedCell();
        movingSide = 2;
        this.checkersField = server.getStartedField();

    }

    public void mouseClick(int x, int y) throws IOException {

        int xCell = x / 75;
        int yCell = y / 75;

        CoordXY mirrorCoord = mirroredCoord(xCell, yCell);

        if (isClicked) {
            List<CoordXY> path = clickedCell.getPotencialPath();

            for (CoordXY coordXY : path) {
                if (coordXY.getX() == mirrorCoord.getX() && coordXY.getY() == mirrorCoord.getY()) {

                    CoordXY endXY = new CoordXY(coordXY.getX(), coordXY.getY());
                    CoordXY startXY = new CoordXY(clickedCell.getCoord().getX(), clickedCell.getCoord().getY());

                    if (coordXY.getDeletedCecker() != null) {
                        deleteChecker(coordXY.getDeletedCecker());
                    }

                    System.out.println("Move checker");
                    moveChecker(startXY, endXY);

                    panel.repaint();

                    server.move(startXY, endXY);

                    clickedCell = new ClickedCell();
                    isClicked = false;
                    break;

                }
            }
        }

        if (checkersField[mirrorCoord.getY()][mirrorCoord.getX()] == Player.side) {
            isClicked = true;
            CoordXY coordXY = new CoordXY();
            coordXY.setX(xCell);
            coordXY.setY(yCell);
            clickedCell.setCoord(mirrorCoord);
            possibleMoves(clickedCell);
            panel.repaint();
        }

        System.out.println(mirrorCoord.getX() + " - x, " + mirrorCoord.getY() + " - y");
    }

    public int[][] invCheckers(int[][] checkers) { // invert checkers field in y coords
        int[][] newCheckers = new int[8][8];

        int newX = 7;
        for (int x = 0; x < checkers.length; x++) {
            int newY = 7;
            for (int y = 0; y < checkers[0].length; y++) {
                newCheckers[newY][newX] = checkers[y][x];
                newY--;
            }
            newX--;

        }

        return newCheckers;
    }

    private List<CoordXY> processingCell(CoordXY coordXY) {
        int[][] nbsCoords = {{1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

        System.out.println(coordXY.getX() + " x, " + coordXY.getY() + " y - processingCell");

        List<CoordXY> coords = new ArrayList<>();
        for (int i = 0; i < nbsCoords.length; i++) {

            CoordXY neighborXY = new CoordXY(coordXY.getX() + nbsCoords[i][0], coordXY.getY() + nbsCoords[i][1]);
            int nbChecker;

            try {
                nbChecker = checkersField[neighborXY.getY()][neighborXY.getX()];
            } catch (Exception e) {
                System.out.println("Exception ");
                continue;
            }

            System.out.println(nbChecker + " checker");
            if (nbChecker == 0) {
                coords.add(neighborXY);
            } else if (nbChecker != Player.side) { // if on cell is an enemy

                CoordXY movingCellXY = new CoordXY(neighborXY.getX() + nbsCoords[i][0], neighborXY.getY() + nbsCoords[i][1]);
                if (isOnField(movingCellXY) && checkersField[movingCellXY.getY()][movingCellXY.getX()] == 0) {
                    movingCellXY.setDelChecker(neighborXY);
                    coords.add(movingCellXY);
                }

            }
        }
        return coords;
    }

    /**
     * On server coords are stored in matrix. This matrix is one for both sides
     * thats why if player is on white side, whe need to send on server mirrored coords
     * this method has on input CoordsXY, and on output mirrored CoordsXY on game field
     *
     * @return
     */
    public CoordXY mirroredCoord(int coordX, int coordY) {

        int[] mirrowList = {7, 6, 5, 4, 3, 2, 1, 0};
        CoordXY mirrorCoord = new CoordXY();
        if (Player.side == 1) {
            mirrorCoord.setX(mirrowList[coordX]);
            mirrorCoord.setY(mirrowList[coordY]);
        } else {
            mirrorCoord.setX(coordX);
            mirrorCoord.setY(coordY);
        }

        return mirrorCoord;

    }

    private void possibleMoves(ClickedCell clickedCell) {
        CoordXY coord = clickedCell.getCoord();
        List<CoordXY> coords = processingCell(coord);

        clickedCell.setPotencialPath(coords);

    }

    private boolean isOnField(CoordXY coords) {
        if (coords.getX() <= 7 && coords.getX() >= 0) {
            if (coords.getY() <= 7 && coords.getY() >= 0) {
                return true;
            }
        }
        return false;
    }

    private void deleteChecker(CoordXY checkerXY){

        checkersField[checkerXY.getY()][checkerXY.getX()] = 0;

    }

    public synchronized void moveChecker(CoordXY start, CoordXY end) {

        checkersField[start.getY()][start.getX()] = 0;
        checkersField[end.getY()][end.getX()] = movingSide;
        switchMovingSide();
        isClicked = false;
        panel.repaint();

    }

    public void switchMovingSide(){
        if(movingSide == 1){
            movingSide = 2;
        }else{
            movingSide = 1;
        }
    }

    public int getMovingSide(){
        return movingSide;
    }

    public void setMovingSide(int movingSide){
        this.movingSide = movingSide;
    }

    public void setJFrame(JFrame frame){
        this.frame = frame;
    }

    public void setPanel(DrawingPanel panel){
        this.panel = panel;
    }

    public int[][] getCheckersField() {
        return checkersField;
    }

    public void setCheckersField(int[][] checkersField) {
        this.checkersField = checkersField;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public ClickedCell getClickedCell() {
        return clickedCell;
    }

    public void setClickedCell(ClickedCell clickedCell) {
        this.clickedCell = clickedCell;
    }
}
