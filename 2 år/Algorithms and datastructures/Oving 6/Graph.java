import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Graph {
    static int N;
    static int K;
    private LinkedList<Integer> adj[];
    static Node[] node;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./O6G2.txt"));
        newUnweighedGraph(br);
        System.out.println(N +" "+ K);
        dfs(node[0]);
        System.out.println(node[1].edge1.to + " " + node[1].edge1.next);
    }


    public static void newUnweighedGraph(BufferedReader br) throws IOException {
        StringTokenizer str = new StringTokenizer(br.readLine());
        N = Integer.parseInt(str.nextToken());
        node = new Node[N];
        for(int i = 0; i<N;++i){
            node[i] = new Node();
        }

        K = Integer.parseInt(str.nextToken());
        for(int i = 0; i < K; ++i){
            str = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(str.nextToken());
            int to = Integer.parseInt(str.nextToken());
            Edge k = new Edge(node[to], node[from].edge1);
            node[from].edge1 = k;
        }
    }

    public static void dfs_init(){
        for(int i = N; i-- > 0;){
            node[i].d = new DFS_pred();
        }
        DFS_pred.zero_time();
    }

    public static void df_search(Node n){

        DFS_pred nd = (DFS_pred) n.d;
        nd.find_time = DFS_pred.read_time();
        for(Edge k = n.edge1; k!= null; k = k.next){
            DFS_pred md = (DFS_pred) k.to.d;
            if(md.find_time == 0){
                md.pred = n;
                md.dist = nd.dist + 1;
                df_search(k.to);
            }
            nd.finished_time = DFS_pred.read_time();
        }
    }

    public static void dfs(Node s){
        dfs_init();
        ((DFS_pred) s.d).dist = 0;
        df_search(s);
    }



}
