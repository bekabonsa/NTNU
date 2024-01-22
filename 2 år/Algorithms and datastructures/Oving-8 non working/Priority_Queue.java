import java.util.Arrays;

public class Priority_Queue {
    int len;
    int[] node;

    public Priority_Queue(int[] n){
        node = n;
        len = n.length;
    }

    public void add(int x) {
        int i = len++;
        node[i] = x;
        prio_up(i, 0);

    }

    public void prio_up(int i, int p) {
        int f;
        node[i] += p;
        while (i > 0 && node[i] < node[f = over(i)]) {
            WeightedGraph.swap(node, i, f);
            i = f;
        }
    }

    public void fix_heap(int i) {
        int m = left(i);
        if (m < len) {
            int h = m + 1;
            if (h < len && node[h] < node[m]) m = h;
            if (node[m] < node[i]) {
                WeightedGraph.swap(node, i, m);
                fix_heap(m);
            }
        }
    }

   /** public void heapsort() {
        construct_heap();
        int l = len;
        while (len > 1) {
            int x = get_min();
            node[len] = x;
        }
        len = l;
    }
    */

    public void construct_heap() {
        int i = len / 2;

        while (i-- > 0) fix_heap(i);
    }

    int over(int i) {
        return (i - 1) >> 1;
    }

    int left(int i) {
        return (i << 1) + 1;
    }

    int right(int i) {
        return (i + 1) << 1;
    }

    int get_min() {
        int min = node[0];
        node[0] = node[--len];
        fix_heap(0);
        return min;
    }

    void get_min(int i, Node[] pr) {

    }
}
