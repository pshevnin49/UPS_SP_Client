package pckg;

public class MoveInf {

    CoordXY from;
    CoordXY to;

    public MoveInf(CoordXY from, CoordXY to){
        this.from = from;
        this.to = to;
    }

    public CoordXY getFrom() {
        return from;
    }

    public void setFrom(CoordXY from) {
        this.from = from;
    }

    public CoordXY getTo() {
        return to;
    }

    public void setTo(CoordXY to) {
        this.to = to;
    }
}
