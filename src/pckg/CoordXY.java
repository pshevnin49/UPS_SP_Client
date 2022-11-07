package pckg;

import java.util.ArrayList;
import java.util.List;

public class CoordXY {

    int x;
    int y;

    List<CoordXY> deletedCeckers;

    public CoordXY(){

    }

    public CoordXY(int x, int y){
        this.x = x;
        this.y = y;
        deletedCeckers = new ArrayList<>();
    }

    public List<CoordXY> getDeletedCeckers(){
        return deletedCeckers;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addChecker(CoordXY checker){
        deletedCeckers.add(checker);
    }

    public void addCheckers(List<CoordXY> checkers){
        deletedCeckers.addAll(checkers);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
