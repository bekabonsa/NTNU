import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ALTMain {
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

        long start;
        long end;

        System.out.println("\nALT Kårvåg–Gjemnes");
        start = System.nanoTime();
        mapData.ALT(3292784, 7352330);
        end = System.nanoTime();
        System.out.println((end - start)/1000000000 + " seconds used");
        mapData.printTime(7352330);
        mapData.nodesInShortestPath(7352330);
        mapData.writeMapData(3292784, 7352330, "ALTMapKårvåg–Gjemnes.txt");


        System.out.println("\nALT Tampere–Ålesund");
        start = System.nanoTime();
        mapData.ALT(  232073, 2518780);
        end = System.nanoTime();
        System.out.println((end - start)/1000000000 + " seconds used");
        mapData.printTime(2518780);
        mapData.nodesInShortestPath(2518780);
        mapData.writeMapData(232073, 2518780, "ALTMapTampere–Ålesund.txt");

    }
}