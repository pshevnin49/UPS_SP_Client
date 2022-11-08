package pckg;

import java.util.ArrayList;
import java.util.List;

public class CoordXY {

    int x;
    int y;

    CoordXY deletedChecker = null;

    public CoordXY(){

    }

    public CoordXY(int x, int y){
        this.x = x;
        this.y = y;

    }

    public CoordXY getDeletedCecker(){
        return deletedChecker;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setDelChecker(CoordXY checker){
        deletedChecker = checker;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
