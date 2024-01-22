public class DoublyLinkedList {
    Node head, tail = null;
    private int totElements = 0;

    class Node{
        int number;
        Node next;
        Node previous;

        public Node(int n){
            this.number = n;
        }
        public Node(){

        }
    }



    public void addNode(int n){
        Node newNode = new Node(n);
        //if head is not set, set head and tails to new node
        if(head == null){
            head = tail = newNode;
            //since newNode is the first element then set previous and next to null
            head.next = null;
            tail.next = null;
        }
        //if there is a head
        else{
            //the new node must come after tail
            tail.next = newNode;
            //the new node must also have a way backwards
            newNode.previous = tail;
            //Update tail to last element which is newNode
            tail = newNode;
            //this indicates that newNode/tail is the last element
            tail.next = null;
        }
        ++totElements;
    }

    public void deleteAllNodes() {
        Node temp = new Node();
        while(this.head != null) {
            temp = this.head;
            this.head = this.head.next;
            temp = null;
        }
        this.totElements = 0;
    }

    public int getTotElements(){
        return totElements;
    }
}
