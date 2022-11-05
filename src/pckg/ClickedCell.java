package pckg;

import java.util.ArrayList;
import java.util.List;

public class ClickedCell {

    CoordXY coord = new CoordXY();
    List<CoordXY> potencialPath = new ArrayList();


    public CoordXY getCoord() {
        return coord;
    }

    public void setCoord(CoordXY coord) {
        this.coord = coord;
    }

    public List<CoordXY> getPotencialPath() {
        return potencialPath;
    }

    public void setPotencialPath(List<CoordXY> potencialPath) {
        this.potencialPath = potencialPath;
    }
}
