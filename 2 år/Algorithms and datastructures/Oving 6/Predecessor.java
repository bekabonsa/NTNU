public class Predecessor {
    int dist;
    Node pred;

    static int infinity = 1000000000;
    public int find_distance(){return dist;}
    public Predecessor(){
        dist = infinity;
    }
}
