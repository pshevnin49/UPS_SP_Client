package pckg;

public class SessionInf {
    int port;
    String ip;
    int roomNumber;
    String nickname;

    public SessionInf(int port, String ip, int roomNumber, String nickname) {
        this.port = port;
        this.ip = ip;
        this.roomNumber = roomNumber;
        this.nickname = nickname;
    }

    public int getPort() {
        return port;
    }

    public String getIp() {
        return ip;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
