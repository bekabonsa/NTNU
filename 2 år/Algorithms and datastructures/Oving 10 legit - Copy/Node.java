import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node>{
    int nr;
    String name;
    int value;
    int code;
    int cost = Integer.MAX_VALUE;
    int parent = -1;

    boolean found;
    boolean visited;

    double latitude;
    double longitude;

    int distanceToTarget = -1;
    int sumOfValues = -1;

    List<Edge> edges = new ArrayList<>();
    List<Edge> flippedEdges = new ArrayList<>();

    private static final double EPS = 1e-7;

    @Override
    public int compareTo(Node other) {
        if(this.sumOfValues < other.sumOfValues) return -1;
        if(this.sumOfValues > other.sumOfValues) return 1;
//        if(this.sumOfValues==other.sumOfValues)
        return 0;
    }

    public Node(int nr) {
        this.nr = nr;
        this.value = 0;
    }

    public Node(int nr, int value) {
        this.nr = nr;
        this.value = value;
    }

    public Node(int nr, double longitude, double latitude) {
        this.nr = nr;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Node: " + name +
                ", nr " + nr +
                ", " + (float)cost/6000 + " minutes away " +
                ", code " + code +
                ", (latitude, longitude) " + latitude +
                ", " + longitude + "\n";
    }

}