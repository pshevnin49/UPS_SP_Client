package pckg;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DrawingPanel extends JPanel {

    int cellSize = 600 / 8;
    Game game;

    public DrawingPanel(Game game) {
        this.game = game;

        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                game.mouseClick(e.getX(), e.getY());
                repaint();
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


    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        drawField(g2);

        drawClicked(g2);

        try {
            drawCheckers(g2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void drawField(Graphics2D g) {
        //int cellSize = 600/8; // 800 will be changed on real window size
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {

                if (x % 2 == 0 && y % 2 == 0 || x % 2 == 1 && y % 2 == 1) {
                    g.setColor(new Color(183, 165, 153));
                } else {
                    g.setColor(new Color(111, 92, 78));
                }

                g.fillRect(cellSize * x, cellSize * y, cellSize, cellSize);
            }
        }
    }

    private void drawCheckers(Graphics2D g2) throws IOException {

        int nullX = 5;
        int nullY = 5;
        int coordX = 0;
        int coordY = 0;

        int[][] newCheckers = game.getCheckersField();

        if (Player.side == 1) {
            newCheckers = game.invCheckers(newCheckers);//???
        }

        for (int x = 0; x < newCheckers.length; x++) {
            for (int y = 0; y < newCheckers[x].length; y++) {

                if (newCheckers[y][x] == 1) {
                    coordX = nullX + x * cellSize;
                    coordY = nullY + y * cellSize;
                    drawChecker(g2, coordX, coordY, "white_checker.png");
                }

                if (newCheckers[y][x] == 2) {
                    coordX = nullX + x * cellSize;
                    coordY = nullY + y * cellSize;
                    drawChecker(g2, coordX, coordY, "black_checker.png");
                }

            }
        }
    }

    private void drawClicked(Graphics2D g2) {

        if (game.isClicked()) {
            g2.setColor(new Color(79, 177, 105));
            System.out.println("Print clicked ");
            CoordXY mirrorRect = game.mirroredCoord(game.getClickedCell().getCoord().getX(), game.getClickedCell().getCoord().getY());
            g2.fillRect(cellSize * mirrorRect.getX(), cellSize * mirrorRect.getY(), cellSize, cellSize);

            for (int i = 0; i < game.getClickedCell().getPotencialPath().size(); i++) {
                CoordXY coord = game.mirroredCoord(game.getClickedCell().getPotencialPath().get(i).getX(), game.getClickedCell().getPotencialPath().get(i).getY());
                g2.fillRect(cellSize * coord.getX(), cellSize * coord.getY(), cellSize, cellSize);
            }

        } else {

        }

    }

    private void drawChecker(Graphics2D g2, int x, int y, String imgName) throws IOException {

        BufferedImage white = ImageIO.read(new File(imgName));
        g2.drawImage(white, x, y, 65, 65, null);

    }


}
