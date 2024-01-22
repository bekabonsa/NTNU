import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ShortestPathBetweenPoints {
    public static void main(String[] args) throws IOException {
        File f1 = new File("PreFileFrom.txt");
        File f2 = new File("PreFileTo.txt");
        MapData mapData = new MapData();

        InputStream inputStream = new FileInputStream("./noder.txt");
        InputStream inputStream1 = new FileInputStream("./kanter.txt");
        InputStream inputStream2 = new FileInputStream("./interessepkt.txt");
        mapData.nodeInput(inputStream);
        System.out.println("Nodes read");
        mapData.edgeInput(inputStream1);
        System.out.println("Vertexes read");
        mapData.readInterestPoint(inputStream2);
        System.out.println("Interest points added");

        if (!f1.exists() && !f2.exists()) {
            System.out.println("Preprocessing files not found, creating new files");

            PreProcess pp = new PreProcess(7509994, mapData.getNodeList());
            pp.makeAdjLists();
            pp.createFileFrom("PreFileFrom.txt");
            pp.createFileTo("PreFileTo.txt");
        } else {
            System.out.println("Preprocessing files found, reading files");

            InputStream inputStream3 = new FileInputStream("./PreFileFrom.txt");
            InputStream inputStream4 = new FileInputStream("./PreFileTo.txt");
            mapData.readPreProcessedFileFromLandmark(inputStream3);
            mapData.readPreProcessedFileToLandmark(inputStream4);
            System.out.println("Preprocessed files read");

            while (true) {
                Scanner in = new Scanner(System.in);
                System.out.println("""
                        \nWelcome to the shortest path between two points program.
                        Please choose what you want to do:
                        1. Find the shortest path between two points
                        2. Find all interest points within a certain distance from a point
                        3. Exit program
                        """);
                int choice = Integer.parseInt(in.nextLine());
                switch (choice) {
                    case 1 -> {
                        boolean finished = false;
                        while (!finished) {
                            System.out.println("""
                                    \nPlease choose which points you want to find the shortest path between:
                                    1. Kårvåg – Gjemnes
                                    2. Tampere – Ålesund
                                    3. Exit to main menu
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
                                    System.out.println((end - start) / 1000000000 + " seconds used for calculation");
                                    mapData.printTime(7352330);
                                    mapData.nodesInShortestPath(7352330);
                                    mapData.writeMapData(3292784, 7352330, "Kårvåg-Gjemnes_map_ALT.txt");

                                    System.out.println("\nShortest path between Kårvåg and Gjemnes with Dijkstra");
                                    start = System.nanoTime();
                                    mapData.dijkstraWithEnd(3292784, 7352330);
                                    end = System.nanoTime();
                                    System.out.println((end - start) / 1000000000 + " seconds used for calculation");
                                    mapData.printTime(7352330);
                                    mapData.nodesInShortestPath(7352330);
                                    mapData.writeMapData(3292784, 7352330, "Kårvåg-Gjemnes_map_DIJKSTRA.txt");
                                }
                                case 2 -> {
                                    long start;
                                    long end;

                                    System.out.println("\nShortest path between Tampere and Ålesund with ALT");
                                    start = System.nanoTime();
                                    mapData.ALT(232073, 2518780);
                                    end = System.nanoTime();
                                    System.out.println((end - start) / 1000000000 + " seconds used for calculation");
                                    mapData.printTime(2518780);
                                    mapData.nodesInShortestPath(2518780);
                                    mapData.writeMapData(232073, 2518780, "Tampere-Ålesund_map_ALT.txt");

                                    System.out.println("\nShortest path between Tampere and Ålesund with Dijkstra");
                                    start = System.nanoTime();
                                    mapData.dijkstraWithEnd(232073, 2518780);
                                    end = System.nanoTime();
                                    System.out.println((end - start) / 1000000000 + " seconds used for calculation");
                                    mapData.printTime(2518780);
                                    mapData.nodesInShortestPath(2518780);
                                    mapData.writeMapData(232073, 2518780, "Tampere-Ålesund_map_DIIJKSTRA.txt");
                                }
                                case 3 -> finished = true;
                            }
                        }
                    }
                    case 2 -> {
                        boolean finished = false;
                        while (!finished) {
                            System.out.println("""
                                    \nPlease choose which point you want to find interest points within a certain distance from:
                                    1. Charging Stations near Trondheim Airport, Værnes
                                    2. Drinking Places near Trondheim Torg
                                    3. Eating Places near Hemsedal    
                                    4. Exit to main menu                                   
                                    """);
                            int choice3 = Integer.parseInt(in.nextLine());
                            switch (choice3) {
                                case 1 -> {
                                    System.out.println("How many interest points do you want to check for?");
                                    int numberOfInterestPoints = Integer.parseInt(in.nextLine());
                                    System.out.println("\nCharging stations near Trondheim Airport, Værnes:");
                                    mapData.stationsToMapData(mapData.dijkstraDistStations(7172108, 4, numberOfInterestPoints), "VærnesChargingStations.txt");//Værnes Charging Stations
                                }
                                case 2 -> {
                                    System.out.println("How many interest points do you want to check for?");
                                    int numberOfInterestPoints = Integer.parseInt(in.nextLine());
                                    System.out.println("\nDrinking places near Trondheim Torg:");
                                    mapData.stationsToMapData(mapData.dijkstraDistStations(4546048, 16, numberOfInterestPoints), "TrondheimTorgDrinkingPlaces.txt");//Trondheim Torg Drinking Places
                                }
                                case 3 -> {
                                    System.out.println("How many interest points do you want to check for?");
                                    int numberOfInterestPoints = Integer.parseInt(in.nextLine());
                                    System.out.println("\nEating places near Hemsedal:");
                                    mapData.stationsToMapData(mapData.dijkstraDistStations(3509663, 8, numberOfInterestPoints), "HemsedalEatingPlaces.txt");//Hemsedal Eating Places
                                }
                                case 4 -> finished = true;
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
    }
}