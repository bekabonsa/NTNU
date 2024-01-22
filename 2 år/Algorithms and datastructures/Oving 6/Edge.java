
public class Edge {
    Edge next;
    Node to;

    public Edge(Node n, Edge nxt){
        to = n;
        next = nxt ;
    }
}

class Node{
    Edge edge1;
    Object d;
}


