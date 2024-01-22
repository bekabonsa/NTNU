import java.io.*;
import java.util.*;

public class MapData {
    private static final int POSITIVE_INFINITY = Integer.MAX_VALUE;
    private int nrOfNodes;
    private int nrOfEdges;
    private Integer[] prevNodes;
    private Node[] nodeList;

    private final int[] landmarks = {2151398, 1236417, 3225427}; // {Nordkapp, Lindesnes fyr, Åre}
    List<int[]> fromLandMark = new ArrayList<>();
    List<int[]> toLandMark = new ArrayList<>();

    public MapData() {}

    public int getNrOfNodes() {
        return nrOfNodes;
    }

    public Node[] getNodeList() {
        return nodeList;
    }

    public void ALT(int start, int target){
        getNodeList()[start].cost = 0;
        getNodeList()[start].distanceToTarget = estimatedDistanceToTarget(start,target);
        getNodeList()[start].sumOfValues = sumOfValues(start);
        getNodeList()[start].found = true;

        Set<Integer> openSet = new HashSet<>();
        Set<Integer> closedSet = new HashSet<>();

        PriorityQueue<Node> pq = new PriorityQueue<>(getNrOfNodes(), Comparator.comparingInt(a -> a.sumOfValues));
        pq.add(getNodeList()[start]);
        openSet.add(start);
        int counter=0;

        while(!pq.isEmpty()){
            counter++;

            Node node = pq.poll();
            openSet.remove(node.nr);
            closedSet.add(node.nr);

            if (node.nr == target) {
                System.out.println("Number of nodes processed: " +counter);
                return;
            }


            List<Edge> edges = node.edges;
            for(Edge edge : edges){
                if (closedSet.contains(edge.to)) continue;
                int newCost = node.cost + edge.weight;
                int dist = estimatedDistanceToTarget(node.nr, target);
                if(newCost < getNodeList()[edge.to].cost){

                    getNodeList()[edge.to].cost = newCost;
                    getNodeList()[edge.to].parent = node.nr;

                    if(openSet.contains(edge.to)){
                        pq.remove(getNodeList()[edge.to]);
                        getNodeList()[edge.to].sumOfValues = sumOfValues(edge.to);
                        pq.offer(getNodeList()[edge.to]);
                    }
                    else{
                        getNodeList()[edge.to].distanceToTarget = estimatedDistanceToTarget(getNodeList()[edge.to].nr, target);
                        getNodeList()[edge.to].sumOfValues = sumOfValues(edge.to);
                        pq.offer(getNodeList()[edge.to]);
                        openSet.add(edge.to);
                    }
                }
            }

        }

    }

    public int sumOfValues(int node){
        return nodeList[node].cost + nodeList[node].distanceToTarget;
    }

    public int estimatedDistanceToTarget(int thisNode, int target){
        int estimatedDistance = 0;
        for (int i = 0; i < landmarks.length; i++) {
            int distanceToTargetFromNodeFL = fromLandMark.get(i)[target] - fromLandMark.get(i)[thisNode];
            int distanceToTargetFromNodeTL = toLandMark.get(i)[thisNode] - toLandMark.get(i)[target];

            if(distanceToTargetFromNodeFL>estimatedDistance) estimatedDistance = distanceToTargetFromNodeFL;
            if(distanceToTargetFromNodeTL>estimatedDistance) estimatedDistance = distanceToTargetFromNodeTL;
        }
        return estimatedDistance;
    }

    public int[] dijkstra(int start) {

        MinBinaryHeap<Integer> priorityQueue = new MinBinaryHeap<>(nrOfNodes);
        priorityQueue.insert(start, 0);

        int[] distance = new int[nrOfNodes];
        Arrays.fill(distance, POSITIVE_INFINITY);
        distance[start] = 0;

        boolean[] visited = new boolean[nrOfNodes];
        prevNodes = new Integer[nrOfNodes];

        while (!priorityQueue.isEmpty()) {
            int nodeNr = priorityQueue.peekMinVal();

            visited[nodeNr] = true;
            int minValue = priorityQueue.pollMin();

            if (minValue > distance[nodeNr]) continue;

            for (Edge edge : nodeList[nodeNr].edges) {

                if (visited[edge.to]) continue;

                int newDistance = distance[nodeNr] + edge.weight;
                if (newDistance < distance[edge.to]) {
                    prevNodes[edge.to] = nodeNr;
                    distance[edge.to] = newDistance;
                    nodeList[edge.to].parent = nodeNr;
                    nodeList[edge.to].cost = newDistance;

                    if (!priorityQueue.contains(edge.to)) priorityQueue.insert(edge.to, newDistance);
                    else priorityQueue.decrease(edge.to, newDistance);
                }
            }
        }
        return distance;
    }

    public void dijkstraWithEnd(int start, int target) {

        MinBinaryHeap<Integer> priorityQueue = new MinBinaryHeap<>(nrOfNodes);
        priorityQueue.insert(start, 0);

        int[] distance = new int[nrOfNodes];
        Arrays.fill(distance, POSITIVE_INFINITY);
        distance[start] = 0;

        boolean[] visited = new boolean[nrOfNodes];
        prevNodes = new Integer[nrOfNodes];

        int index = 0;
        while (!priorityQueue.isEmpty()) {
            index++;
            int nodeNr = priorityQueue.peekMinVal();

            visited[nodeNr] = true;
            int minValue = priorityQueue.pollMin();
            if (nodeNr == target){
                System.out.println("Number of nodes processed: " + index);
                return;
            }
            if (minValue > distance[nodeNr]) continue;

            for (Edge edge : nodeList[nodeNr].edges) {

                if (visited[edge.to]) continue;

                int newDistance = distance[nodeNr] + edge.weight;
                if (newDistance < distance[edge.to]) {
                    prevNodes[edge.to] = nodeNr;
                    distance[edge.to] = newDistance;
                    nodeList[edge.to].parent = nodeNr;
                    nodeList[edge.to].cost = newDistance;

                    if (!priorityQueue.contains(edge.to)) priorityQueue.insert(edge.to, newDistance);
                    else priorityQueue.decrease(edge.to, newDistance);
                }
            }
        }
    }

    public void printTime(int target){
        int cost = nodeList[target].cost;

        int hours = cost / 360000;
        cost-= hours*360000;
        int minutes = cost / 6000;
        cost-= minutes*6000;
        int seconds = cost/100;


        System.out.println("Driving time in Hours:Minutes:Seconds");
        System.out.println(hours+" : "+ minutes + " : " + seconds);
    }

    public void nodesInShortestPath( int target){
        int counter = 0;
        Node node = nodeList[target];
        while(node.parent != -1){
            counter++;
            node = nodeList[node.parent];
        }
        counter++;
        System.out.println("Nodes in shortest path: " + counter);
    }

    public List<Node> dijkstraDistStations(int startPlace, int code, int numOfInterestPts) throws IOException {
        ArrayList<Node> stationList = new ArrayList<>();
        int[] cost = dijkstra(startPlace);
        List<Node> nodeArr = new ArrayList<>(Arrays.asList(nodeList));
        for (int i = 0; i < nrOfNodes; i++) {
            nodeArr.get(i).cost = cost[i];
        }
        nodeArr.sort(Comparator.comparing(Node::getCost));
        for (Node station : nodeArr) {
            if ((station.code & code) == code) {
                stationList.add(station);
            }
            if (stationList.size() == numOfInterestPts) break;
        }
        System.out.println(stationList);
        return stationList;
    }

    public void readPreProcessedFileFromLandmark(InputStream inputStream) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringTokenizer st;
        int[] array0 = new int[nrOfNodes];
        int[] array1 = new int[nrOfNodes];
        int[] array2 = new int[nrOfNodes];
        fromLandMark.add(0, array0);
        fromLandMark.add(1, array1);
        fromLandMark.add(2, array2);
        int index = 0;
        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line, ",");
            while (st.hasMoreTokens() && st.countTokens() == 3) {
                fromLandMark.get(0)[index] =  Integer.parseInt(st.nextToken());
                fromLandMark.get(1)[index] = Integer.parseInt(st.nextToken());
                fromLandMark.get(2)[index] = Integer.parseInt(st.nextToken());
            }
            index++;
        }
        br.close();
//        int[] array0 = new int[arr0.size()];
//        int[] array1 = new int[arr1.size()];
//        int[] array2 = new int[arr2.size()];

//        fromLandMark.add(0, array0);
//        fromLandMark.add(1, array1);
//        fromLandMark.add(2, array2);
    }

    public void readPreProcessedFileToLandmark(InputStream inputStream) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringTokenizer st;
        int[] array0 = new int[nrOfNodes];
        int[] array1 = new int[nrOfNodes];
        int[] array2 = new int[nrOfNodes];
        toLandMark.add(0, array0);
        toLandMark.add(1, array1);
        toLandMark.add(2, array2);
        int index = 0;
        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line, ",");
            while (st.hasMoreTokens() && st.countTokens() == 3) {
                toLandMark.get(0)[index] = Integer.parseInt(st.nextToken());
                toLandMark.get(1)[index] = Integer.parseInt(st.nextToken());
                toLandMark.get(2)[index] = Integer.parseInt(st.nextToken());
            }
            index++;
        }
        br.close();

//        toLandMark.add(0, array0);
//        toLandMark.add(1, array1);
//        toLandMark.add(2, array2);

    }

    public void stationsToMapData(List<Node> stationList, String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        for (int i = 0; i < stationList.size(); i++) {
            fileWriter.write(stationList.get(i).latitude + "," + stationList.get(i).longitude + "\n");
        }
        fileWriter.close();
    }

    public void writeMapData(int start, int target, String file) throws IOException {
        Node node = nodeList[target];

        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        while(node.parent != -1) {
            pw.println(String.format("%s,%s", node.latitude, node.longitude));
            node = nodeList[node.parent];
        }

        pw.println(String.format("%s,%s", node.latitude, node.longitude));

        pw.flush();
        pw.close();
    }

    public void readInterestPoint(InputStream inputStream) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nrOfIntPoints = Integer.parseInt(st.nextToken());
        for (int i = 0; i < nrOfIntPoints; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeNr = Integer.parseInt(st.nextToken());
            nodeList[nodeNr].setCode(Integer.parseInt(st.nextToken()));
            StringBuilder interestName = new StringBuilder(String.valueOf(st.nextToken()));
            while (st.hasMoreTokens()) {
                interestName.append(" ").append(st.nextToken());
            }

            nodeList[nodeNr].setName(interestName.toString());
        }
    }

    public void nodeInput(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringTokenizer st = new StringTokenizer(br.readLine());
        this.nrOfNodes = Integer.parseInt(st.nextToken());
        this.nodeList = new Node[nrOfNodes];
        for (int i = 0; i < nrOfNodes; ++i) {
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            double longitude = Double.parseDouble(st.nextToken());
            double latitude = Double.parseDouble(st.nextToken());
            nodeList[index] = new Node(index,latitude,longitude);
        }
    }

    public void edgeInput(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringTokenizer st = new StringTokenizer(br.readLine());
        this.nrOfEdges = Integer.parseInt(st.nextToken());
        for (int i = 0; i < nrOfEdges; ++i) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            Edge newEdge = new Edge(from, to, weight);
            nodeList[from].edges.add(newEdge);
        }
    }


    public static class MinBinaryHeap<T extends Comparable<T>> {

        private int size;
        private final int NROFELEMENTS;
        private final int DEGREE = 2;

        private final int[] child, parent;
        public final int[] posMap;
        public final int[] inverseMap;

        public final Object[] val;

        public MinBinaryHeap(int maxSize) {
            if (maxSize <= 0) throw new IllegalArgumentException("maxSize <= 0");

            NROFELEMENTS = Math.max(DEGREE + 1, maxSize);

            inverseMap = new int[NROFELEMENTS];
            posMap = new int[NROFELEMENTS];
            child = new int[NROFELEMENTS];
            parent = new int[NROFELEMENTS];
            val = new Object[NROFELEMENTS];

            for (int i = 0; i < NROFELEMENTS; i++) {
                parent[i] = (i - 1) / DEGREE;
                child[i] = i * DEGREE + 1;
                posMap[i] = inverseMap[i] = -1;
            }
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean contains(int k) {
            if (k < 0 || k >= NROFELEMENTS)
                throw new IllegalArgumentException("Key index out of bounds: " + k);
            return posMap[k] != -1;
        }

        public int peekMinVal() {
            if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
            return inverseMap[0];
        }

        public T peekMin() {
            if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
            return (T) val[inverseMap[0]];
        }

        public T pollMin() {
            T minValue = peekMin();
            remove(peekMinVal());
            return minValue;
        }

        public void insert(int k, T value) {
            if (contains(k)) throw new IllegalArgumentException("index already exists: " + k);
            if (value == null) throw new IllegalArgumentException("Value cannot be null");
            posMap[k] = size;
            inverseMap[size] = k;
            val[k] = value;
            swim(size++);
        }

        public T remove(int k) {
            if (!contains(k)) throw new NoSuchElementException("Index does not exist: " + k);
            final int i = posMap[k];
            swap(i, --size);
            sink(i);
            swim(i);
            T value = (T) val[k];
            val[k] = null;
            posMap[k] = -1;
            inverseMap[size] = -1;
            return value;
        }

        public void decrease(int k, T value) {
            if (!contains(k)) throw new NoSuchElementException("Index does not exist: " + k);
            if (value == null) throw new IllegalArgumentException("Value cannot be null");
            if (lessThan(value, val[k])) {
                val[k] = value;
                swim(posMap[k]);
            }
        }

        private void sink(int i) {
            for (int j = minChild(i); j != -1; ) {
                swap(i, j);
                i = j;
                j = minChild(i);
            }
        }

        private void swim(int i) {
            while (lessThan(i, parent[i])) {
                swap(i, parent[i]);
                i = parent[i];
            }
        }

        private int minChild(int i) {
            int index = -1, from = child[i], to = Math.min(size, from + DEGREE);
            for (int j = from; j < to; j++) if (lessThan(j, i)) index = i = j;
            return index;
        }

        private void swap(int i, int j) {
            posMap[inverseMap[j]] = i;
            posMap[inverseMap[i]] = j;
            int temp = inverseMap[i];
            inverseMap[i] = inverseMap[j];
            inverseMap[j] = temp;
        }

        private boolean lessThan(int i, int j) {
            return ((Comparable<? super T>) val[inverseMap[i]]).compareTo((T) val[inverseMap[j]]) < 0;
        }


        private boolean lessThan(Object obj1, Object obj2) {
            return ((Comparable<? super T>) obj1).compareTo((T) obj2) < 0;
        }
    }
}