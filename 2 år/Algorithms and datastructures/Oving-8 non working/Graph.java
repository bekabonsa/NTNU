import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Graph {
    static int N;
    static int K;
    public static Node[] node;

    public void initPred(Node s) {
        for (int i = N; i-- > 0; ) {
            node[i].value = new Pred();
        }
        ((Pred) s.value).dist = 0;
    }

    void simplify(Node n, W_edge k) {
        Pred nd = (Pred) n.value, md = (Pred) k.to.value;
        if (md.dist > nd.dist + k.weight) {
            md.dist = nd.dist + k.weight;
            md.pred = n;
        }
    }

    public void dijkstra(Node s) {
        initPred(s);
        Node[] pri = new Node[N];
        int[] weight = new int[pri.length];
        ArrayList<Integer> cost = new ArrayList<>();
        Heap heap = new Heap();

        for(int i = 0; i<pri.length;i++){
            Heap.heapify(cost, pri[i].getEdge1().getWeight());
        }

        for (int i = N; i > 1; --i) {
            Node n = cost.get(0);
            for (W_edge e = (W_edge) n.edge1; e != null; e = (W_edge) e.next) {
                simplify(n, e);
            }
        }
    }

    public static void new_W_graph(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer((br.readLine()));
        N = Integer.parseInt(st.nextToken());
        node = new Node[N];
        for (int i = 0; i < N; ++i) node[i] = new Node();

        K = Integer.parseInt((st.nextToken()));
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            W_edge e = new W_edge(node[to], node[from].edge1, weight);
            node[from].edge1 = e;

        }

    }
}
