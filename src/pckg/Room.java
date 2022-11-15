package pckg;

public class Room {
    int number;
    int numberOfPlayers;

    public Room(int number, int numberOfPlayers){
        this.number = number;
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    @Override
    public String toString() {
        String line = "Room " + number + "   " + numberOfPlayers + "/2";
        return line;
    }
}
