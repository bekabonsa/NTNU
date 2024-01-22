import java.io.*;
import java.util.*;

public class ShortestPathBetweenPoints {
    public static void main(String[] args) throws IOException {
        File f1 = new File("PreFileFrom.txt");
        File f2 = new File("PreFileTo.txt");
        MapData mapData = new MapData();

        InputStream inputStream = new FileInputStream("./noder.txt");
        InputStream inputStream1 = new FileInputStream("./kanter.txt");
        InputStream inputStream2 = new FileInputStream("./interessepkt.txt");
        mapData.inputNode(inputStream);
        System.out.println("Nodes read");
        mapData.inputEdge(inputStream1);
        System.out.println("Vertexes read");
        mapData.readInterestPoint(inputStream2);
        System.out.println("Interest points added");

        if (!f1.exists() && !f2.exists()) {
            System.out.println("Preprocessing files not found, creating new files");

            PreProcess pp = new PreProcess(7509994, mapData.getArrayOfNodes());
            pp.createAdjacentList();
            pp.createFromNodeFile("PreFileFrom.txt");
            pp.createToNodeFile("PreFileTo.txt");
        }
        System.out.println("Preprocessing files found, reading files");

        InputStream inputStream3 = new FileInputStream("./PreFileFrom.txt");
        InputStream inputStream4 = new FileInputStream("./PreFileTo.txt");
        mapData.readPreProcessedFileFromLandmark(inputStream3);
        mapData.readPreProcessedFileToLandmark(inputStream4);
        System.out.println("Preprocessed files read");

        Scanner in = new Scanner(System.in);
        System.out.println("""
                                        
                Welcome to the shortest path between two points program.
                Please choose what you want to do:
                1. Find the shortest path between two points
                2. Find all interest points within a certain distance from a point
                3. Exit program
                """);
        int choice = Integer.parseInt(in.nextLine());
        switch (choice) {
            case 1 -> {
                System.out.println("""
                                                                
                            Please choose which points you want to find the shortest path between:
                            1. Kårvåg – Gjemnes
                            2. Tampere – Ålesund
                            """);
                int choice2 = Integer.parseInt(in.nextLine());
                switch (choice2) {
                    case 1 -> {
                        long start;
                        long end;

                        System.out.println("\nShortest path between Kårvåg and Gjemnes with ALT");
                        start = System.nanoTime();
                        mapData.ALT(3292784, 7352330);
                        end = System.nanoTime();
                        System.out.println((end - start) / 1000000000 + " seconds used");
                        mapData.printDrivingTime(7352330);
                        mapData.numberOfNodesInShortestPath(7352330);
                        mapData.writeMapData(3292784, 7352330, "Kårvåg-Gjemnes_map_ALT.txt");

                        System.out.println("\nShortest path between Kårvåg and Gjemnes with Dijkstra");
                        start = System.nanoTime();
                        mapData.dijkstraWithTargetNode(3292784, 7352330);
                        end = System.nanoTime();
                        System.out.println((end - start) / 1000000000 + " seconds used");
                        mapData.printDrivingTime(7352330);
                        mapData.numberOfNodesInShortestPath(7352330);
                        mapData.writeMapData(3292784, 7352330, "Kårvåg-Gjemnes_map_DIJKSTRA.txt");
                    }
                    case 2 -> {
                        long start;
                        long end;

                        System.out.println("\nShortest path between Tampere and Ålesund with ALT");
                        start = System.nanoTime();
                        mapData.ALT(232073, 2518780);
                        end = System.nanoTime();
                        System.out.println((end - start) / 1000000000 + " seconds used");
                        mapData.printDrivingTime(2518780);
                        mapData.numberOfNodesInShortestPath(2518780);
                        mapData.writeMapData(232073, 2518780, "Tampere-Ålesund_map_ALT.txt");

                        System.out.println("\nShortest path between Tampere and Ålesund with Dijkstra");
                        start = System.nanoTime();
                        mapData.dijkstraWithTargetNode(232073, 2518780);
                        end = System.nanoTime();
                        System.out.println((end - start) / 1000000000 + " seconds used");
                        mapData.printDrivingTime(2518780);
                        mapData.numberOfNodesInShortestPath(2518780);
                        mapData.writeMapData(232073, 2518780, "Tampere-Ålesund_map_DIIJKSTRA.txt");
                    }
                }
            }
            case 2 -> {
                System.out.println("""
                                                                
                            Please choose which point you want to find interest points within a certain distance from:
                            1. Charging Stations near Trondheim Airport, Værnes
                            2. Drinking Places near Trondheim Torg
                            3. Eating Places near Hemsedal
                            """);
                int choice3 = Integer.parseInt(in.nextLine());
                switch (choice3) {
                    case 1 -> {
                        System.out.println("How many interest points do you want to check for?");
                        int numberOfInterestPoints = Integer.parseInt(in.nextLine());
                        System.out.println("\nCharging stations near Trondheim Airport, Værnes:");
                        mapData.interestPointsToMapData(mapData.dijkstraDistanceInterestPoints(7172108, 4, numberOfInterestPoints), "VærnesChargingStations.txt");
                    }
                    case 2 -> {
                        System.out.println("How many interest points do you want to check for?");
                        int numberOfInterestPoints = Integer.parseInt(in.nextLine());
                        System.out.println("\nDrinking places near Trondheim Torg:");
                        mapData.interestPointsToMapData(mapData.dijkstraDistanceInterestPoints(4546048, 16, numberOfInterestPoints), "TrondheimTorgDrinkingPlaces.txt");
                    }
                    case 3 -> {
                        System.out.println("How many interest points do you want to check for?");
                        int numberOfInterestPoints = Integer.parseInt(in.nextLine());
                        System.out.println("\nEating places near Hemsedal:");
                        mapData.interestPointsToMapData(mapData.dijkstraDistanceInterestPoints(3509663, 8, numberOfInterestPoints), "HemsedalEatingPlaces.txt");
                    }
                }
            }
            case 3 -> {
                System.out.println("Exiting...");
                System.exit(0);
            }
        }
    }

}




class PreProcess {

    private static final int POS_INF = Integer.MAX_VALUE;
    private final int numOfNodes;
    private Integer[] previous;
    private final Node[] arrayOfNodes;

    private final int[] landmarks = {3007953, 2383502, 6600989};
    List<int[]> fromLandMark = new ArrayList<>();
    List<int[]> toLandMark = new ArrayList<>();

    public PreProcess(int numOfNodes, Node[] arrayOfNodes) {
        this.numOfNodes = numOfNodes;
        this.arrayOfNodes = arrayOfNodes;
    }

    public int[] dijkstra(int startNode) {

        MapData.MinBinaryHeap<Integer> PQ = new MapData.MinBinaryHeap<>(numOfNodes);
        PQ.insert(startNode, 0);

        int[] distance = new int[numOfNodes];
        Arrays.fill(distance, POS_INF);
        distance[startNode] = 0;

        boolean[] visited = new boolean[numOfNodes];
        previous = new Integer[numOfNodes];

        while (!PQ.isEmpty()) {
            int nodeNr = PQ.peekMinObj();

            visited[nodeNr] = true;
            int minimumValue = PQ.pollMin();

            if (minimumValue > distance[nodeNr]) continue;

            for (Edge edge : arrayOfNodes[nodeNr].edges) {

                if (visited[edge.toNode]) continue;

                int newDistance = distance[nodeNr] + edge.weight;
                if (newDistance < distance[edge.toNode]) {
                    previous[edge.toNode] = nodeNr;
                    distance[edge.toNode] = newDistance;
                    arrayOfNodes[edge.toNode].nodeParent = nodeNr;

                    if (!PQ.contains(edge.toNode)) PQ.insert(edge.toNode, newDistance);
                    else PQ.decrease(edge.toNode, newDistance);
                }
            }
        }

        return distance;
    }

    public int[] dijkstraFlipped(int startNode) {

        MapData.MinBinaryHeap<Integer> PQ = new MapData.MinBinaryHeap<>(numOfNodes);
        PQ.insert(startNode, 0);

        int[] distance = new int[numOfNodes];
        Arrays.fill(distance, POS_INF);
        distance[startNode] = 0;

        boolean[] visited = new boolean[numOfNodes];
        previous = new Integer[numOfNodes];

        while (!PQ.isEmpty()) {
            int nodeNr = PQ.peekMinObj();

            visited[nodeNr] = true;
            int minimumValue = PQ.pollMin();

            if (minimumValue > distance[nodeNr]) continue;

            for (Edge edge : arrayOfNodes[nodeNr].flippedEdges) {

                if (visited[edge.toNode]) continue;

                int newDist = distance[nodeNr] + edge.weight;
                if (newDist < distance[edge.toNode]) {
                    previous[edge.toNode] = nodeNr;
                    distance[edge.toNode] = newDist;

                    if (!PQ.contains(edge.toNode)) PQ.insert(edge.toNode, newDist);
                    else PQ.decrease(edge.toNode, newDist);
                }
            }
        }

        return distance;
    }

    public void createAdjacentList(){
        for (int landmark : landmarks) {
            fromLandMark.add(dijkstra(landmark));
            System.out.println("Landmark: "+ landmark +" to node processed");

        }

        flipGraph();
        System.out.println("Flipped graph");

        for (int landmark : landmarks){
            toLandMark.add(dijkstraFlipped(landmark));
            System.out.println("Node to landmark: " + landmark + " processed");
        }
    }


    public void flipGraph(){
        for(int i = 0; i< numOfNodes; i++){
            for (int j = 0; j< arrayOfNodes[i].edges.size(); j++){
                Edge edge = new Edge(arrayOfNodes[i].edges.get(j).fromNode, arrayOfNodes[i].edges.get(j).toNode,
                        arrayOfNodes[i].edges.get(j).weight);
                int oldFromNode = edge.fromNode;
                edge.fromNode = edge.toNode;
                edge.toNode = oldFromNode;
                arrayOfNodes[edge.fromNode].flippedEdges.add(edge);
            }
        }
    }

    public void createFromNodeFile(String outFile) throws IOException {
        writeOutputFile(outFile, fromLandMark);
    }

    public void createToNodeFile(String outFile) throws IOException{
        writeOutputFile(outFile, toLandMark);
    }

    private void writeOutputFile(String outFile, List<int[]> toLandMark) throws IOException {
        FileWriter fileWriter = new FileWriter((outFile));
        for (int i = 0; i < numOfNodes; i++) {
            fileWriter.write(toLandMark.get(0)[i] + "," + toLandMark.get(1)[i] + "," + toLandMark.get(2)[i] + "\n");
        }
        fileWriter.close();
    }
}

class Node implements Comparable<Node>{
    int nodeNumber;
    String nodeName;
    int nodeCode;
    int nodeCost = Integer.MAX_VALUE;
    int nodeParent = -1;

    boolean foundNode;

    double latitude;
    double longitude;

    int distanceToTarget = -1;
    int sumOfValues = -1;

    List<Edge> edges = new ArrayList<>();
    List<Edge> flippedEdges = new ArrayList<>();


    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.sumOfValues, other.sumOfValues);
    }

    public Node(int nr, double longitude, double latitude) {
        this.nodeNumber = nr;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getNodeCost() {
        return nodeCost;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public void setNodeCode(int nodeCode) {
        this.nodeCode = nodeCode;
    }

    @Override
    public String toString() {
        return """
                Node nr: %d | nodeName: %s | %f minutes away | nodeCode: %d | (latitude , longitude): (%f , %f)
                """.formatted(nodeNumber, nodeName, (float) nodeCost /6000, nodeCode, latitude, longitude);
    }
}

class Edge {
    int weight;
    int fromNode, toNode;

    public Edge(int from, int to, int cost) {
        this.fromNode = from;
        this.toNode = to;
        this.weight = cost;
    }

}

class MapData {
    private static final int POS_INF = Integer.MAX_VALUE;
    private int numberOfNodes;
    private Integer[] previousNodes;
    private Node[] arrayOfNodes;

    private final int[] landmarks = {2151398, 1236417, 3225427};
    List<int[]> fromLandMark = new ArrayList<>();
    List<int[]> toLandMark = new ArrayList<>();

    public MapData() {}

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public Node[] getArrayOfNodes() {
        return arrayOfNodes;
    }

    public void ALT(int startNode, int targetNode){
        getArrayOfNodes()[startNode].nodeCost = 0;
        getArrayOfNodes()[startNode].distanceToTarget = estDistanceToTarget(startNode,targetNode);
        getArrayOfNodes()[startNode].sumOfValues = sumOfValues(startNode);
        getArrayOfNodes()[startNode].foundNode = true;

        HashSet<Integer> openHashSet = new HashSet<>();
        HashSet<Integer> closedHashSet = new HashSet<>();

        PriorityQueue<Node> PQ = new PriorityQueue<>(getNumberOfNodes(), Comparator.comparingInt(a -> a.sumOfValues));
        PQ.add(getArrayOfNodes()[startNode]);
        openHashSet.add(startNode);
        int count = 0;

        while(!PQ.isEmpty()){
            count++;

            Node node = PQ.poll();
            openHashSet.remove(node.nodeNumber);
            closedHashSet.add(node.nodeNumber);

            if (node.nodeNumber == targetNode) {
                System.out.println("Number of nodes processed: " + count);
                return;
            }

            List<Edge> edges = node.edges;
            for(Edge edge : edges){
                if (closedHashSet.contains(edge.toNode)) continue;
                int newCost = node.nodeCost + edge.weight;
                if(newCost < getArrayOfNodes()[edge.toNode].nodeCost){

                    getArrayOfNodes()[edge.toNode].nodeCost = newCost;
                    getArrayOfNodes()[edge.toNode].nodeParent = node.nodeNumber;

                    if(openHashSet.contains(edge.toNode)){
                        PQ.remove(getArrayOfNodes()[edge.toNode]);
                        getArrayOfNodes()[edge.toNode].sumOfValues = sumOfValues(edge.toNode);
                        PQ.offer(getArrayOfNodes()[edge.toNode]);
                    }
                    else{
                        getArrayOfNodes()[edge.toNode].distanceToTarget = estDistanceToTarget(getArrayOfNodes()[edge.toNode].nodeNumber, targetNode);
                        getArrayOfNodes()[edge.toNode].sumOfValues = sumOfValues(edge.toNode);
                        PQ.offer(getArrayOfNodes()[edge.toNode]);
                        openHashSet.add(edge.toNode);
                    }
                }
            }
        }
    }

    public int sumOfValues(int node){
        return arrayOfNodes[node].nodeCost + arrayOfNodes[node].distanceToTarget;
    }

    public int estDistanceToTarget(int startNode, int targetNode){
        int estDistance = 0;
        for (int i = 0; i < landmarks.length; i++) {
            int fromLandmarkDistanceToTarget = fromLandMark.get(i)[targetNode] - fromLandMark.get(i)[startNode];
            int toLandmarkDistanceToTarget = toLandMark.get(i)[startNode] - toLandMark.get(i)[targetNode];

            if(fromLandmarkDistanceToTarget>estDistance) estDistance = fromLandmarkDistanceToTarget;
            if(toLandmarkDistanceToTarget>estDistance) estDistance = toLandmarkDistanceToTarget;
        }
        return estDistance;
    }

    public int[] dijkstra(int startNode) {

        MinBinaryHeap<Integer> PQ = new MinBinaryHeap<>(numberOfNodes);
        PQ.insert(startNode, 0);

        int[] distance = new int[numberOfNodes];
        Arrays.fill(distance, POS_INF);
        distance[startNode] = 0;

        boolean[] visited = new boolean[numberOfNodes];
        previousNodes = new Integer[numberOfNodes];

        while (!PQ.isEmpty()) {
            int nodeNr = PQ.peekMinObj();

            visited[nodeNr] = true;
            int minimumValue = PQ.pollMin();

            checkDistance(PQ, distance, visited, nodeNr, minimumValue);
        }
        return distance;
    }

    public void dijkstraWithTargetNode(int startNode, int targetNode) {

        MinBinaryHeap<Integer> PQ = new MinBinaryHeap<>(numberOfNodes);
        PQ.insert(startNode, 0);

        int[] distance = new int[numberOfNodes];
        Arrays.fill(distance, POS_INF);
        distance[startNode] = 0;

        boolean[] visited = new boolean[numberOfNodes];
        previousNodes = new Integer[numberOfNodes];

        int index = 0;
        while (!PQ.isEmpty()) {
            index++;
            int nodeNr = PQ.peekMinObj();

            visited[nodeNr] = true;
            int minimumValue = PQ.pollMin();
            if (nodeNr == targetNode){
                System.out.println("Number of nodes processed: " + index);
                return;
            }
            checkDistance(PQ, distance, visited, nodeNr, minimumValue);
        }
    }

    private void checkDistance(MinBinaryHeap<Integer> PQ, int[] distance, boolean[] visited, int nodeNr, int minimumValue) {
        if (minimumValue > distance[nodeNr]) return;

        for (Edge edge : arrayOfNodes[nodeNr].edges) {

            if (visited[edge.toNode]) continue;

            int newDistance = distance[nodeNr] + edge.weight;
            if (newDistance < distance[edge.toNode]) {
                previousNodes[edge.toNode] = nodeNr;
                distance[edge.toNode] = newDistance;
                arrayOfNodes[edge.toNode].nodeParent = nodeNr;
                arrayOfNodes[edge.toNode].nodeCost = newDistance;

                if (!PQ.contains(edge.toNode)) PQ.insert(edge.toNode, newDistance);
                else PQ.decrease(edge.toNode, newDistance);
            }
        }
    }

    public void printDrivingTime(int target){
        int cost = arrayOfNodes[target].nodeCost;

        int hours = cost / 360000;
        cost-= hours*360000;
        int minutes = cost / 6000;
        cost-= minutes*6000;
        int seconds = cost/100;


        System.out.println("Driving time in Hours:Minutes:Seconds");
        System.out.println(hours+" : "+ minutes + " : " + seconds);
    }

    public void numberOfNodesInShortestPath(int targetNode){
        int count = 0;
        Node node = arrayOfNodes[targetNode];
        while(node.nodeParent != -1){
            count++;
            node = arrayOfNodes[node.nodeParent];
        }
        count++;
        System.out.println("Number of nodes in shortest path: " + count);
    }

    public List<Node> dijkstraDistanceInterestPoints(int startPlace, int nodeCode, int numOfInterestPts) {
        ArrayList<Node> interestPointList = new ArrayList<>();
        int[] cost = dijkstra(startPlace);
        List<Node> arrayOfNodes = new ArrayList<>(Arrays.asList(this.arrayOfNodes));
        for (int i = 0; i < numberOfNodes; i++) {
            arrayOfNodes.get(i).nodeCost = cost[i];
        }
        arrayOfNodes.sort(Comparator.comparing(Node::getNodeCost));
        for (Node station : arrayOfNodes) {
            if ((station.nodeCode & nodeCode) == nodeCode) {
                interestPointList.add(station);
            }
            if (interestPointList.size() == numOfInterestPts) break;
        }
        System.out.println(interestPointList);
        return interestPointList;
    }

    public void readPreProcessedFileFromLandmark(InputStream inputStream) throws IOException{
        readInput(inputStream, fromLandMark);
    }

    public void readPreProcessedFileToLandmark(InputStream inputStream) throws IOException{
        readInput(inputStream, toLandMark);
    }

    private void readInput(InputStream inputStream, List<int[]> toLandMark) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringTokenizer st;
        int[] array0 = new int[numberOfNodes];
        int[] array1 = new int[numberOfNodes];
        int[] array2 = new int[numberOfNodes];
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
    }

    public void interestPointsToMapData(List<Node> stationList, String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        for (Node node : stationList) {
            fileWriter.write(node.latitude + "," + node.longitude + "\n");
        }
        fileWriter.close();
    }

    public void writeMapData(int startNode, int targetNode, String file) throws IOException {
        Node node = arrayOfNodes[targetNode];

        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        while(node.nodeParent != -1) {
            pw.println(String.format("%s,%s", node.latitude, node.longitude));
            node = arrayOfNodes[node.nodeParent];
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
            arrayOfNodes[nodeNr].setNodeCode(Integer.parseInt(st.nextToken()));
            StringBuilder interestName = new StringBuilder(String.valueOf(st.nextToken()));
            while (st.hasMoreTokens()) {
                interestName.append(" ").append(st.nextToken());
            }

            arrayOfNodes[nodeNr].setNodeName(interestName.toString());
        }
    }

    public void inputNode(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringTokenizer st = new StringTokenizer(br.readLine());
        this.numberOfNodes = Integer.parseInt(st.nextToken());
        this.arrayOfNodes = new Node[numberOfNodes];
        for (int i = 0; i < numberOfNodes; ++i) {
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            double longitude = Double.parseDouble(st.nextToken());
            double latitude = Double.parseDouble(st.nextToken());
            arrayOfNodes[index] = new Node(index,latitude,longitude);
        }
    }

    public void inputEdge(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nrOfEdges = Integer.parseInt(st.nextToken());
        for (int i = 0; i < nrOfEdges; ++i) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            Edge newEdge = new Edge(from, to, weight);
            arrayOfNodes[from].edges.add(newEdge);
        }
    }


    public static class MinBinaryHeap<T extends Comparable<T>> {

        private int sizeOfHeap;
        private final int numOfElements;
        private final int degree = 2;

        private final int[] child, parent;
        public final int[] posMap;
        public final int[] mapInversed;

        public final Object[] obj;

        public MinBinaryHeap(int maxSize) {
            if (maxSize <= 0) throw new IllegalArgumentException("maxSize <= 0");

            numOfElements = Math.max(degree + 1, maxSize);

            mapInversed = new int[numOfElements];
            posMap = new int[numOfElements];
            child = new int[numOfElements];
            parent = new int[numOfElements];
            obj = new Object[numOfElements];

            for (int i = 0; i < numOfElements; i++) {
                parent[i] = (i - 1) / degree;
                child[i] = i * degree + 1;
                posMap[i] = mapInversed[i] = -1;
            }
        }

        public boolean isEmpty() {
            return sizeOfHeap == 0;
        }

        public boolean contains(int k) {
            if (k < 0 || k >= numOfElements)
                throw new IllegalArgumentException("Key index out of bounds: " + k);
            return posMap[k] != -1;
        }

        public int peekMinObj() {
            if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
            return mapInversed[0];
        }

        public T peekMin() {
            if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
            return (T) obj[mapInversed[0]];
        }

        public T pollMin() {
            T minValue = peekMin();
            remove(peekMinObj());
            return minValue;
        }

        public void insert(int k, T value) {
            if (contains(k)) throw new IllegalArgumentException("index already exists: " + k);
            if (value == null) throw new IllegalArgumentException("Value cannot be null");
            posMap[k] = sizeOfHeap;
            mapInversed[sizeOfHeap] = k;
            obj[k] = value;
            swim(sizeOfHeap++);
        }

        public void remove(int k) {
            if (!contains(k)) throw new NoSuchElementException("Index does not exist: " + k);
            final int i = posMap[k];
            swap(i, --sizeOfHeap);
            sink(i);
            swim(i);
            obj[k] = null;
            posMap[k] = -1;
            mapInversed[sizeOfHeap] = -1;
        }

        public void decrease(int k, T value) {
            if (!contains(k)) throw new NoSuchElementException("Index does not exist: " + k);
            if (value == null) throw new IllegalArgumentException("Value cannot be null");
            if (lessThan(value, obj[k])) {
                obj[k] = value;
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
            int index = -1, from = child[i], to = Math.min(sizeOfHeap, from + degree);
            for (int j = from; j < to; j++) if (lessThan(j, i)) index = i = j;
            return index;
        }

        private void swap(int i, int j) {
            posMap[mapInversed[j]] = i;
            posMap[mapInversed[i]] = j;
            int temp = mapInversed[i];
            mapInversed[i] = mapInversed[j];
            mapInversed[j] = temp;
        }

        private boolean lessThan(int i, int j) {
            return ((Comparable<? super T>) obj[mapInversed[i]]).compareTo((T) obj[mapInversed[j]]) < 0;
        }


        private boolean lessThan(Object obj1, Object obj2) {
            return ((Comparable<? super T>) obj1).compareTo((T) obj2) < 0;
        }
    }
}