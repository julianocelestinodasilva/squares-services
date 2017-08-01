package vittahealth.hiring.challenge.domain;


import java.io.Serializable;

public class Node implements Serializable {

    private int x;
    private int y;
    private boolean painted;

    public void painted(boolean painted) {
        this.painted = painted;
    }

    public boolean isPainted() {
        return painted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        if (x != node.x) return false;
        return y == node.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public long area() {
        return x * y;
    }

    public Node() {

    }

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
