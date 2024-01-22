public class Pred {
    int dist;
    Node pred;
    static int infinite = 1000000000;

    public int find_dist() {
        return dist;
    }

    public Node find_pred() {
        return pred;
    }

    public Pred() {
        dist = infinite;
    }
}
