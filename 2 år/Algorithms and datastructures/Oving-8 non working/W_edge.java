public class W_edge extends Edge {
    int weight;

    public W_edge(Node n, W_edge nxt, int wgt) {
        super(n, nxt);
        weight = wgt;
    }

    public int getWeight() {
        return weight;
    }
}
