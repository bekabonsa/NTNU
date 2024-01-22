
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class DijkstraMinHeap {
    static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class HeapNode{
        int vertex;
        int distance;
    }
    static class Graph {
        int vertices;
        LinkedList<Edge>[] adjacencylist;

        Graph(int vertices) {
            this.vertices = vertices;
            adjacencylist = new LinkedList[vertices];
            for (int i = 0; i <vertices ; i++) {
                adjacencylist[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            adjacencylist[source].addFirst(edge);

            /**edge = new Edge(destination, source, weight);
            adjacencylist[destination].addFirst(edge); //for undirected graph*/
        }

        public void dijkstra_GetMinDistances(int sourceVertex, int[] prev){
            int INFINITY = Integer.MAX_VALUE;
            boolean[] SPT = new boolean[vertices];


// //create heapNode for all the vertices
            HeapNode [] heapNodes = new HeapNode[vertices];
            for (int i = 0; i <vertices ; i++) {
                heapNodes[i] = new HeapNode();
                heapNodes[i].vertex = i;
                heapNodes[i].distance = INFINITY;
                prev[i] = -1;
            }
            //decrease the distance for the first index
            heapNodes[sourceVertex].distance = 0;

            //add all the vertices to the DijkstraMinHeap.MinHeap
            MinHeap minHeap = new MinHeap(vertices);
            for (int i = 0; i <vertices ; i++) {
                minHeap.insert(heapNodes[i]);
            }

            //while minHeap is not empty
            while(!minHeap.isEmpty()){
                //extract the min
                HeapNode extractedNode = minHeap.extractMin();

                //extracted vertex
                int extractedVertex = extractedNode.vertex;
                SPT[extractedVertex] = true;

                //iterate through all the adjacent vertices
                LinkedList<Edge> list = adjacencylist[extractedVertex];
                for (int i = 0; i <list.size() ; i++) {
                    Edge edge = list.get(i);
                    int destination = edge.destination;
                    //only if destination vertex is not present in SPT
                    if(SPT[destination]==false) {
                        ///check if distance needs an update or not
                        //means check total weight from source to vertex_V is less than
                        //the current distance value, if yes then update the distance
                        int newKey = heapNodes[extractedVertex].distance + edge.weight ;
                        int currentKey = heapNodes[destination].distance;
                        if(currentKey>newKey){

                            decreaseKey(minHeap, newKey, destination);
                            heapNodes[destination].distance = newKey;
                            prev[destination] = extractedVertex;
                        }
                    }
                }
            }
            //print SPT
            printDijkstra(heapNodes, sourceVertex, prev);
        }

        public void decreaseKey(MinHeap minHeap, int newKey, int vertex){

            //get the index which distance's needs a decrease;
            int index = minHeap.indexes[vertex];

            //get the node and update its value
            HeapNode node = minHeap.mH[index];
            node.distance = newKey;
            minHeap.bubbleUp(index);
        }

        public void printDijkstra(HeapNode[] resultSet, int sourceVertex, int[] prev){
            System.out.println("Dijkstra's Algorithm: (using Adjacency List and Min Heap). Ø = none, S = start");
            for (int i = 0; i <vertices ; i++) {
                String x = String.valueOf(resultSet[i].distance);
                if(resultSet[i].distance == Integer.MAX_VALUE || resultSet[i].distance < 0){
                    x = "unreachable";
                }
                String pred = String.valueOf(prev[i]);
                if(prev[i] < 0 && resultSet[i].distance == 0) pred = "S";
                else if(prev[i] < 0){
                    pred = "Ø";
                }
                System.out.println("Node: " + i + " | predecessor " + pred +
                        " | distance: " + x);
            }
        }
    }

    public static void main(String[] args) throws IOException {



        System.out.println("---- vg4.txt ---- ");
        BufferedReader br4 = new BufferedReader(new FileReader("./vg4.txt"));
        printResult(br4);
        System.out.println("---- vg2.txt ---- ");
        BufferedReader br1 = new BufferedReader(new FileReader("./vg2.txt"));
        printResult(br1);
        System.out.println("---- vg3.txt ---- ");
        BufferedReader br3 = new BufferedReader(new FileReader("./vg3.txt"));
        printResult(br3);
        System.out.println("---- vg5.txt ---- ");
        BufferedReader br5 = new BufferedReader(new FileReader("./vg5.txt"));
        printResult(br5);
        System.out.println("---- vg1.txt ---- ");
        BufferedReader br = new BufferedReader(new FileReader("./vg1.txt"));
        printResult(br);


    }

    public static void printResult(BufferedReader br) throws IOException {

        StringTokenizer st = new StringTokenizer((br.readLine()));
        int vertices = Integer.parseInt(st.nextToken());
        int[] prev = new int[vertices];
        Graph graph = new Graph(vertices);


        int K = Integer.parseInt((st.nextToken()));
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.addEdge(from, to, weight);
        }
        int source_vertex = 1;
        graph.dijkstra_GetMinDistances(source_vertex, prev);
    }


    static class MinHeap {
        int capacity;
        int currentSize;
        HeapNode[] mH;
        int[] indexes; //will be used to decrease the distance


        public MinHeap(int capacity) {
            this.capacity = capacity;
            mH = new HeapNode[capacity + 1];
            indexes = new int[capacity];
            mH[0] = new HeapNode();
            mH[0].distance = Integer.MIN_VALUE;
            mH[0].vertex = -1;
            currentSize = 0;
        }

        public void display() {
            for (int i = 0; i <= currentSize; i++) {
                System.out.println(" " + mH[i].vertex + " distance " + mH[i].distance);
            }
            System.out.println("________________________");
        }

        public void insert(HeapNode x) {
            currentSize++;
            int idx = currentSize;
            mH[idx] = x;
            indexes[x.vertex] = idx;
            bubbleUp(idx);
        }

        public void bubbleUp(int pos) {
            int parentIdx = pos / 2;
            int currentIdx = pos;
            while (currentIdx > 0 && mH[parentIdx].distance > mH[currentIdx].distance) {
                HeapNode currentNode = mH[currentIdx];
                HeapNode parentNode = mH[parentIdx];

                //swap the positions
                indexes[currentNode.vertex] = parentIdx;
                indexes[parentNode.vertex] = currentIdx;
                swap(currentIdx, parentIdx);
                currentIdx = parentIdx;
                parentIdx = parentIdx / 2;
            }
        }

        public HeapNode extractMin() {
            HeapNode min = mH[1];
            HeapNode lastNode = mH[currentSize];
    // update the indexes[] and move the last node to the top
            indexes[lastNode.vertex] = 1;
            mH[1] = lastNode;
            mH[currentSize] = null;
            sinkDown(1);
            currentSize--;
            return min;
        }

        public void sinkDown(int k) {
            int smallest = k;
            int leftChildIdx = 2 * k;
            int rightChildIdx = 2 * k + 1;
            if (leftChildIdx < heapSize() && mH[smallest].distance > mH[leftChildIdx].distance) {
                smallest = leftChildIdx;
            }
            if (rightChildIdx < heapSize() && mH[smallest].distance > mH[rightChildIdx].distance) {
                smallest = rightChildIdx;
            }
            if (smallest != k) {

                HeapNode smallestNode = mH[smallest];
                HeapNode kNode = mH[k];

                //swap the positions
                indexes[smallestNode.vertex] = k;
                indexes[kNode.vertex] = smallest;
                swap(k, smallest);
                sinkDown(smallest);
            }
        }

        public void swap(int a, int b) {
            HeapNode temp = mH[a];
            mH[a] = mH[b];
            mH[b] = temp;
        }

        public boolean isEmpty() {
            return currentSize == 0;
        }

        public int heapSize() {
            return currentSize;
        }
    }
}