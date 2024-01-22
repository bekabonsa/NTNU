import java.io.*;
import java.security.Permission;
import java.util.ArrayList;

public class PreFile {
    public static void main(String[] args) throws IOException {

        /*
        Node[] nodes = new Node[7509994];
        BufferedReader br = new BufferedReader(new FileReader("noder.txt"));
        var i = 0;
        String line = br.readLine();
        while(line!=null){
                line = br.readLine();
                if(line == null) break;
                String[] values = line.split("\\s+");
                nodes[i] = new Node(Integer.parseInt(values[0]), Double.parseDouble(values[1]), Double.parseDouble(values[2]));
                nodes[i].edges = null;
                i++;
        }
        br.close();
         */

        MapData md = new MapData();
        InputStream isN = new FileInputStream("noder.txt");
        InputStream isK = new FileInputStream("kanter.txt");
        md.nodeInput(isN);
        md.edgeInput(isK);

        PreProcess pp = new PreProcess(7509994, md.getNodeList());


        //distance from
        pp.makeAdjLists();
        pp.createFileFrom("PreFileFrom.txt");
        pp.createFileTo("PreFileTo.txt");
    }
}
