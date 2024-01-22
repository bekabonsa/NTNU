import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WeightedGraph {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./vg1.txt" ));
        Graph wg = new Graph();
        wg.new_W_graph(br);
        wg.dijkstra(wg.node[0]);

    }


    public static void swap(int[] n, int i, int m){
    int a = n[i];
    n[i] = n[m];
    n[m] = a;
    }

}
