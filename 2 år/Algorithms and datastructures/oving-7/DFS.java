import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.LinkedList;


 class Graph {
    private int number;
    private LinkedList<Integer>[] adjacentList;

    // Create a graph
    public Graph(int n) {
        this.number = n;
        adjacentList = new LinkedList[n];
        //linked lists inside a linked list
        for (int i = 0; i < n; i++) adjacentList[i] = new LinkedList<>();
    }

    public Graph() {
        Graph graph = new Graph(this.number);
    }

    public void addEdgeToGraph(int n, int d) {
        adjacentList[n].add(d);
    }


    public void depthFirstSearch(boolean[] visitedTable, int s) {
        visitedTable[s] = true;
        System.out.println(s + " ");
        int n;

        for (Integer integer : adjacentList[s]) {
            n = integer;
            if (!visitedTable[n])
                depthFirstSearch(visitedTable, n);
        }
    }


    public Graph Transpose() {
        Graph graph = new Graph(number);
        for (int s = 0; s < number; s++) {
            for (Integer integer : adjacentList[s]) graph.adjacentList[integer].add(s);
        }
        return graph;
    }

    public void fillOrder(int s, boolean[] visitedVertices, Stack<Integer> stack) {
        visitedVertices[s] = true;

        for (int n : adjacentList[s]) {
            if (!visitedVertices[n])
                fillOrder(n, visitedVertices, stack);
        }
        stack.push(s);
    }


    public void printGraph() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visitedVertices = new boolean[this.number];
        for (int i = 0; i < this.number; i++)
            visitedVertices[i] = false;

        for (int i = 0; i < this.number; i++) {
            if (!visitedVertices[i]) {
                fillOrder(i, visitedVertices, stack);
            }
        }
        Graph graph = Transpose();

        for (int i = 0; i < this.number; i++)
            visitedVertices[i] = false;

        while (!stack.empty()) {
            int s = stack.pop();

            if (!visitedVertices[s]) {
                graph.depthFirstSearch(visitedVertices, s);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String fName1 = "O6G1.txt";
        String fName2 = "O6G2.txt";
        String fName3 = "O6G5.txt";
        String fName4 = "O6G6.txt";
        for (String s : Arrays.asList(fName1, fName2, fName3, fName4)) printGraph(s);
    }

    public static void printGraph(String name) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./" + name));
        StringTokenizer str = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(str.nextToken());
        int K = Integer.parseInt(str.nextToken());
        Graph graph = new Graph(N);

        for(int i = 0; i < K; ++i){
            str = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(str.nextToken());
            int to = Integer.parseInt(str.nextToken());
            graph.addEdgeToGraph(from, to);
        }

        System.out.println("The Strongly Connected Components for graph " + name + " are :");
        graph.printGraph();
    }

}