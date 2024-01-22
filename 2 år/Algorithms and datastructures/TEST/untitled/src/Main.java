import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ShortestPathBetweenPoints {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("./noder.txt");
        InputStream inputStream1 = new FileInputStream("./kanter.txt");
        InputStream inputStream2 = new FileInputStream("./interessepkt.txt");
        MapData mapData = new MapData();
        mapData.nodeInput(inputStream);
        System.out.println("Nodes read");
        mapData.edgeInput(inputStream1);
        System.out.println("Vertexes read");
        mapData.readInterestPoint(inputStream2);
        System.out.println("Interest points added");

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
                                System.out.println("The shortest path between Kårvåg and Gjemnes is:");
                                long start;
                                long end;

                                System.out.println("\nALT Kårvåg–Gjemnes");
                                start = System.nanoTime();
                                mapData.ALT(3292784, 7352330);
                                end = System.nanoTime();
                                System.out.println((end - start) / 1000000000 + " seconds used");
                                mapData.printTime(7352330);
                                mapData.nodesInShortestPath(7352330);
                                mapData.writeMapData(7352330, "ALTMapKårvåg–Gjemnes.txt");

                                System.out.println("\nDijkstra Kårvåg–Gjemnes");
                                start = System.nanoTime();
                                mapData.dijkstraWithEnd(3292784, 7352330);
                                end = System.nanoTime();
                                System.out.println((end - start) / 1000000000 + " seconds used");
                                mapData.printTime(7352330);
                                mapData.nodesInShortestPath(7352330);
                                mapData.writeMapData(7352330, "DijkstraMapKårvåg–Gjemnes.txt");
                            }
                            case 2 -> {
                                System.out.println("The shortest path between Tampere and Ålesund is:");
                                long start;
                                long end;

                                System.out.println("\nALT Tampere–Ålesund");
                                start = System.nanoTime();
                                mapData.ALT(232073, 2518780);
                                end = System.nanoTime();
                                System.out.println((end - start) / 1000000000 + " seconds used");
                                mapData.printTime(2518780);
                                mapData.nodesInShortestPath(2518780);
                                mapData.writeMapData(2518780, "ALTMapTampere–Ålesund.txt");

                                System.out.println("\nDijkstra Tampere to Ålesund");
                                start = System.nanoTime();
                                mapData.dijkstraWithEnd(232073, 2518780);
                                end = System.nanoTime();
                                System.out.println((end - start) / 1000000000 + " seconds used");
                                mapData.printTime(2518780);
                                mapData.nodesInShortestPath(2518780);
                                mapData.writeMapData(2518780, "DijkstrasMapTampere-Ålesund.txt");
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
                                System.out.println("\nCharging stations near Trondheim Airport, Værnes:");
                                mapData.stationsToMapData(mapData.dijkstraDistStations(7172108, 4), "VærnesChargeStations.txt");//Værnes Charging Stations
                            }
                            case 2 -> {
                                System.out.println("\nDrinking places near Trondheim Torg:");
                                mapData.stationsToMapData(mapData.dijkstraDistStations(4546048, 16), "TrondheimTorgDrinkingPlaces.txt");//Trondheim Torg Drinking Places
                            }
                            case 3 -> {
                                System.out.println("\nEating places near Hemsedal:");
                                mapData.stationsToMapData(mapData.dijkstraDistStations(3509663, 8), "HemsedalEatingPlaces.txt");//Hemsedal Eating Places
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