import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PreProcess {

    private static final int POSITIVE_INFINITY = 1000000000;
    private final int nrOfNodes;
    private Integer[] prev;
    private Node[] nodeArray;

    private final int[] landmarks = {3007953, 2383502, 6600989}; // {Nordkapp, Lindesnes fyr, Åre}
    List<int[]> fromLandMark = new ArrayList<>();
    List<int[]> toLandMark = new ArrayList<>();

    public PreProcess(int nrOfNodes, Node[] nodeArray) {
        this.nrOfNodes = nrOfNodes;
        this.nodeArray = nodeArray;
    }

    public int[] dijkstra(int start) {

        MapData.MinBinaryHeap<Integer> priorityQueue = new MapData.MinBinaryHeap<>(nrOfNodes);
        priorityQueue.insert(start, 0);

        int[] dist = new int[nrOfNodes];
        Arrays.fill(dist, POSITIVE_INFINITY);
        dist[start] = 0;

        boolean[] visited = new boolean[nrOfNodes];
        prev = new Integer[nrOfNodes];

        while (!priorityQueue.isEmpty()) {
            int nodeNr = priorityQueue.peekMinVal();

            visited[nodeNr] = true;
            int minValue = priorityQueue.pollMin();

            if (minValue > dist[nodeNr]) continue;

            for (Edge edge : nodeArray[nodeNr].edges) {

                if (visited[edge.to]) continue;

                int newDist = dist[nodeNr] + edge.weight;
                if (newDist < dist[edge.to]) {
                    prev[edge.to] = nodeNr;
                    dist[edge.to] = newDist;
                    nodeArray[edge.to].parent = nodeNr;

                    if (!priorityQueue.contains(edge.to)) priorityQueue.insert(edge.to, newDist);
                    else priorityQueue.decrease(edge.to, newDist);
                }
            }
        }

        return dist;
    }

    public int[] flippedDijkstra(int start) {

        MapData.MinBinaryHeap<Integer> priorityQueue = new MapData.MinBinaryHeap<>(nrOfNodes);
        priorityQueue.insert(start, 0);

        int[] dist = new int[nrOfNodes];
        Arrays.fill(dist, POSITIVE_INFINITY);
        dist[start] = 0;

        boolean[] visited = new boolean[nrOfNodes];
        prev = new Integer[nrOfNodes];

        while (!priorityQueue.isEmpty()) {
            int nodeNr = priorityQueue.peekMinVal();

            visited[nodeNr] = true;
            int minValue = priorityQueue.pollMin();

            if (minValue > dist[nodeNr]) continue;

            for (Edge edge : nodeArray[nodeNr].flippedEdges) {

                if (visited[edge.to]) continue;

                int newDist = dist[nodeNr] + edge.weight;
                if (newDist < dist[edge.to]) {
                    prev[edge.to] = nodeNr;
                    dist[edge.to] = newDist;

                    if (!priorityQueue.contains(edge.to)) priorityQueue.insert(edge.to, newDist);
                    else priorityQueue.decrease(edge.to, newDist);
                }
            }
        }

        return dist;
    }

    public void makeAdjLists(){
        for (int landmark : landmarks) {
            fromLandMark.add(dijkstra(landmark));
            System.out.println("Done with landmark: "+ landmark +" to node");

        }

        flipGraph();
        System.out.println("Flipped graph");

        for (int landmark : landmarks){
            toLandMark.add(flippedDijkstra(landmark));
            System.out.println("Done with node to landmark: " + landmark);
        }
    }


    public void flipGraph(){
        for(int i = 0; i<nrOfNodes;i++){
            for (int j = 0; j< nodeArray[i].edges.size(); j++){
                Edge edge = new Edge(nodeArray[i].edges.get(j).from, nodeArray[i].edges.get(j).to,
                        nodeArray[i].edges.get(j).weight);
                int oldFrom = edge.from;
                edge.from = edge.to;
                edge.to = oldFrom;
                nodeArray[edge.from].flippedEdges.add(edge);
            }
        }
    }

    public void createFileFrom(String outFile) throws IOException {
        FileWriter fileWriter = new FileWriter((outFile));
        for (int i = 0; i < nrOfNodes; i++) {
            fileWriter.write(fromLandMark.get(0)[i] + "," + fromLandMark.get(1)[i] + "," + fromLandMark.get(2)[i] +
                    "\n");
        }
        fileWriter.close();
    }

    public void createFileTo(String outFile) throws IOException{
        FileWriter fileWriter = new FileWriter((outFile));
        for (int i = 0; i < nrOfNodes; i++) {
            fileWriter.write(toLandMark.get(0)[i] + "," + toLandMark.get(1)[i] + "," + toLandMark.get(2)[i] + "\n");
        }
        fileWriter.close();
    }
}