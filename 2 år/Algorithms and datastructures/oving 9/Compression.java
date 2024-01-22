import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;


public class Compression {
    public static void main(String[] args) throws IOException {
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.println("""
                    Do you want to compress or decompress a file?
                    1. Compress
                    2. Decompress
                    3. Stop program""");
            int choice = in.nextInt();
            switch (choice) {
                case 1 -> {
                    in.reset();
                    boolean finished = false;
                    while (!finished) {
                        System.out.println("""
                                Choose what file to encode:
                                1. diverse.lyx
                                2. diverse.txt
                                3. diverse.pdf
                                4. opg8-kompr.pdf
                                5. enwik8 (WARNING: This will take a long time)
                                6. Stop compressing (back to main menu)""");
                        choice = in.nextInt();
                        String input1 = "";
                        String output1 = "";
                        String input2 = "";
                        String output2 = "";
                        switch (choice) {
                            case 1 -> {
                                input1 = "./diverse.lyx";
                                output1 = "./diverseComp.lyx";
                                input2 = output1;
                                output2 = "./diverseCompFinished.lyx";
                            }
                            case 2 -> {
                                input1 = "./diverse.txt";
                                output1 = "./diverseComp.txt";
                                input2 = output1;
                                output2 = "./diverseCompFinished.txt";
                            }
                            case 3 -> {
                                input1 = "./diverse.pdf";
                                output1 = "./diverseComp.pdf";
                                input2 = output1;
                                output2 = "./diverseCompFinished.pdf";
                            }
                            case 4 -> {
                                input1 = "./opg8-kompr.pdf";
                                output1 = "./opg8-komprComp.pdf";
                                input2 = output1;
                                output2 = "./opg8-komprCompFinished.pdf";
                            }
                            case 5 -> {
                                input1 = "./enwik8";
                                output1 = "./enwik8Comp.txt";
                                input2 = output1;
                                output2 = "./enwik8CompFinished.txt";
                            }
                            case 6 -> {
                                System.out.println("Back to main menu");
                                finished = true;
                                break;
                            }
                            default -> System.out.println("Invalid choice");
                        }
                        if (choice == 6) {
                            break;
                        }


                        LZHandler fb = new LZHandler();
                        fb.compress(new File(input1), new File(output1));

                        int[] frequencies = new int[256];                        //Making array of size 256

                        try {
                            FileInputStream inputStream = new FileInputStream(input2);
                            int b;
                            while ((b = inputStream.read()) != -1)            //While there's input, count the frequency of every byte.
                                frequencies[b]++;
                            inputStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Node root = Huffman.huffman(frequencies);                //Huffman with the array of frequencies
                        String[] codes = Huffman.orderedTraversal(root);

                        try {
                            FileInputStream inputStream = new FileInputStream(input2);
                            FileOutputStream outputStream = new FileOutputStream(output2);
                            BitOutputStream bitOutputStream = new BitOutputStream(outputStream);

                            for (int frequency : frequencies) bitOutputStream.writeInt(frequency);

                            int b;
                            while ((b = inputStream.read()) != -1) {        //Going through the input again, associating-
                                String code = codes[b];                        //     each input with a code from the array.
                                for (int i = 0; i < code.length(); i++) {
                                    char bit = code.charAt(i);
                                    if (bit == '0')
                                        bitOutputStream.writeBit(0);
                                    else                                    // Assuming it's a '1'
                                        bitOutputStream.writeBit(1);
                                }
                            }

                            bitOutputStream.close();
                            outputStream.close();
                            inputStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        File f = new File(input2);
                        File f2 = new File(output1);
                        if (f.exists())
                            f.delete();
                        if (f2.exists())
                            f2.delete();

                        System.out.println("Done");
                    }
                }
                case 2 -> {
                    in.reset();
                    boolean finished = false;
                    while (!finished) {
                        System.out.println("""
                                Choose what file to decode:
                                1. diverseComp.lyx
                                2. diverseComp.txt
                                3. diverseComp.pdf
                                4. opg8-komprComp.pdf
                                5. enwik8Comp (WARNING: This will take a long time)
                                6. Stop decompressing (back to main menu)""");
                        choice = in.nextInt();
                        String original = "";
                        String input1 = "";
                        String output1 = "";
                        String input2 = "";
                        String output2 = "";
                        switch (choice) {
                            case 1 -> {
                                input1 = "./diverseCompFinished.lyx";
                                output1 = "./diverseDecomp.lyx";
                                input2 = output1;
                                output2 = "./diverseDecompFinished.lyx";
                                original = "./diverse.lyx";

                            }
                            case 2 -> {
                                input1 = "./diverseCompFinished.txt";
                                output1 = "./diverseDecomp.txt";
                                input2 = output1;
                                output2 = "./diverseDecompFinished.txt";
                                original = "./diverse.txt";
                            }
                            case 3 -> {
                                input1 = "./diverseCompFinished.pdf";
                                output1 = "./diverseDecomp.pdf";
                                input2 = output1;
                                output2 = "./diverseDecompFinished.pdf";
                                original = "./diverse.pdf";
                            }
                            case 4 -> {
                                input1 = "./opg8-komprCompFinished.pdf";
                                output1 = "./opg8-komprDecomp.pdf";
                                input2 = output1;
                                output2 = "./opg8-komprDecompFinished.pdf";
                                original = "./opg8-kompr.pdf";
                            }
                            case 5 -> {
                                input1 = "./enwik8CompFinished.txt";
                                output1 = "./enwik8Decomp.txt";
                                input2 = output1;
                                output2 = "./enwik8DecompFinished";
                                original = "./enwik8";
                            }
                            case 6 -> {
                                System.out.println("Back to main menu");
                                finished = true;
                                break;
                            }
                            default -> System.out.println("Invalid choice");
                        }
                        if (choice == 6) {
                            break;
                        }

                        int totalByteCount = 0;
                        int[] frequencies = new int[256];                        //Making array of size 256


                        try {
                            FileInputStream inputStream = new FileInputStream(input1);
                            FileOutputStream outputStream = new FileOutputStream(output1);
                            BitInputStream bitInputStream = new BitInputStream(inputStream);

                            for (int i = 0; i < frequencies.length; i++) {        //Go through the input, filling the array
                                frequencies[i] = bitInputStream.readInt();
                                totalByteCount += frequencies[i];
                            }

                            Node root = Huffman.huffman(frequencies);            //Running huffman with array of frequencies

                            int byteCount = 0;

                            int nextByte;
                            while ((nextByte = readNextByte(root, bitInputStream)) != -1) {    //While there's input, use  -
                                if (byteCount >= totalByteCount)                                    // readNextByte to write out-
                                    break;                                                        // the decoded data.
                                outputStream.write(nextByte);
                                byteCount++;
                            }

                            bitInputStream.close();
                            inputStream.close();
                            outputStream.close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        LZHandler fb = new LZHandler();
                        fb.decompress(new File(input2), new File(output2));

                        File f = new File(input2);
                        if (f.exists()) {
                            f.delete();
                        }
                        File f2 = new File(output1);
                        if (f2.exists()) {
                            f2.delete();
                        }
                        File f3 = new File(original);
                        File f4 = new File(output2);
                        if (Files.mismatch(f3.toPath(), f4.toPath()) == -1) {
                            System.out.println(original + " and " + output2 + " are identical");
                        } else {
                            System.out.println(original + " and " + output2 + " are NOT identical");
                        }
                        System.out.println("Done");
                    }
                }
                case 3 -> System.exit(0);
                default -> throw new IllegalStateException("Unexpected value: " + choice);
            }
        }
    }

    private static int readNextByte(Node node, BitInputStream bitInputStream) throws IOException
    {
        int bit = bitInputStream.readBit();

        if(bit == -1)
            return -1;

        Node nextNode;
        if(bit == 0)
            nextNode = node.leftChild;
        else 													// Assuming it's a '1'
            nextNode = node.rightChild;

        if(nextNode.data == -1)
            return readNextByte(nextNode, bitInputStream);

        return nextNode.data;

    }
}




final class LZHandler {
    private int index;
    private int endIndex;
    public byte[] dataLZ;
    public byte[] bufferLZ;
    public final int referenceMinLength = 6;
    public final int size = 32768;

    public void compress(File inputFile, File outputFile) throws IOException {
        this.lzcompress(inputFile, outputFile);
    }

    public void decompress(File inputFile, File outputFile) throws IOException {
        this.lzdecompress(inputFile, outputFile);
    }

    public void lzcompress(File inputFile, File outputFile) throws IOException {
        DataInputStream in = new DataInputStream(new FileInputStream(inputFile));
        DataOutputStream out = new DataOutputStream(new FileOutputStream(outputFile));
        this.dataLZ = in.readAllBytes();
        in.close();
        this.bufferLZ = new byte[size];
        this.index = 0;
        this.endIndex = 0;
        int lastMatch = 0;
        int whileIndex = 0;

        while (whileIndex < this.dataLZ.length) {
            int indexBuffer = findInBuffer(this.dataLZ[whileIndex], endIndex);

            if (indexBuffer == -1) {
                addReferenceToBuffer(this.dataLZ[whileIndex]);
                this.index++;
            } else {
                int max = createWord(indexBuffer, whileIndex);
                int maxIndex = indexBuffer;
                while (indexBuffer != -1) {
                    indexBuffer = findInBuffer(dataLZ[whileIndex], indexBuffer - 1);

                    if (indexBuffer == -1) {
                        break;
                    }
                    if (createWord(indexBuffer, whileIndex) > max) {
                        max = createWord(indexBuffer, whileIndex);
                        maxIndex = indexBuffer;
                    }
                }

                if (max > referenceMinLength) {
                    out.writeShort(this.index - lastMatch);
                    int count = 0;
                    byte[] tempByteArray = new byte[this.index - lastMatch];

                    for (int n = lastMatch; n < this.index; n++) {
                        try {

                            tempByteArray[count++] = this.dataLZ[n];
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("ArrayIndexOutOfBoundsException");
                            System.exit(0);
                        }
                    }
                    out.write(tempByteArray);
                    out.writeShort(-(whileIndex - maxIndex));
                    out.writeShort(max);
                    for (int n = 0; n < max; n++) {
                        addReferenceToBuffer(this.dataLZ[whileIndex++]);
                        this.index++;
                    }
                    lastMatch = whileIndex;
                    whileIndex--;
                } else {
                    addReferenceToBuffer(this.dataLZ[whileIndex]);
                    this.index++;
                }
            }
            whileIndex++;
        }
        out.writeShort(this.index - lastMatch);
        for (int n = lastMatch; n < this.index; n++) {
            out.writeByte((int) this.dataLZ[n]);
        }
        out.close();
    }

    public void lzdecompress(File inputFile, File outputFile) throws IOException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(outputFile));
        DataInputStream in = new DataInputStream(new FileInputStream(inputFile));
        this.index = 0;
        this.endIndex = 0;
        this.bufferLZ = new byte[size];
        int start = in.readShort();
        this.dataLZ = new byte[(int) start];

        in.readFully(this.dataLZ);
        out.write(this.dataLZ);

        for (int i = 0; i < start; i++) {
            this.addReferenceToBuffer(this.dataLZ[i]);
            this.index++;
        }

        while (in.available() > 0) {
            int back = in.readShort();
            int copy = in.readShort();
            int end = endIndex;
            this.dataLZ = new byte[(int) copy];
            int localIndex = 0;
            int backAndEnd = back + end;

            for (int n = backAndEnd; n < backAndEnd + copy; n++) {
                byte localByteIndex = getByteFromBuffer(n);
                this.dataLZ[localIndex++] = localByteIndex;
                addReferenceToBuffer(localByteIndex);
                this.index++;
            }
            out.write(this.dataLZ);
            start = in.readShort();
            this.dataLZ = new byte[(int) start];
            in.readFully(this.dataLZ);
            for (int n = 0; n < start; n++) {
                addReferenceToBuffer(this.dataLZ[n]);
                this.index++;
            }
            out.write(this.dataLZ);
        }
        out.close();
        in.close();
    }

    private int findInBuffer(byte b, int position) {
        for(int i = position ; i >= 0; i--) {
            if (this.bufferLZ[i] == b) {
                return i;
            }
        }

        return -1;
    }

    private void addReferenceToBuffer(byte b) {
        if (this.index >= this.bufferLZ.length) {
            this.endIndex = 0;
        }
        this.bufferLZ[this.endIndex] = b;
        this.endIndex = (this.index + 1) % this.bufferLZ.length;
    }

    private byte getByteFromBuffer(int localIndex) {
        int i = localIndex % this.bufferLZ.length;

        if (localIndex >= this.bufferLZ.length) {
            return this.bufferLZ[i];
        } else if (localIndex < 0) {
            i += this.bufferLZ.length;

            if (i == this.bufferLZ.length) {
                return this.bufferLZ[0];
            } else {
                return this.bufferLZ[i];
            }
        } else {
            return this.bufferLZ[localIndex];
        }
    }

    private int createWord(int indexBuffer, int indexByte) {
        byte buffer = this.getByteFromBuffer(indexBuffer);
        int b = this.dataLZ[indexByte];

        int wordLength = 0;
        int indexBufferModified = indexBuffer;
        int indexByteModified = indexByte;

        while (buffer == b && indexByteModified != this.dataLZ.length - 1) {
            b = this.dataLZ[++indexByteModified];
            buffer = this.getByteFromBuffer(++indexBufferModified);
            wordLength++;
        }
        return wordLength;
    }
}



class Huffman
{
    private static String[] orderedArray; 						//Array
    private static int size = 0;								//Size

    public static Node huffman(int[] C) {						//Implementation of huffman
        PQ Q = new PQHeap(C.length);							//lag instance av PQ Heap

        for(int i = 0; i < C.length; i++) {						//Fyll Heap'en
            Q.insert(new Element(C[i], new Node(i)));
        }

        for(int i = 1; i < C.length; i++) {						//building up the structure using extract-min
            Element z = new Element(0, new Node());
            Element x = Q.extractMin();
            Element y = Q.extractMin();

            ((Node) z.data).leftChild = (Node) x.data;
            ((Node) z.data).rightChild = (Node) y.data;

            z.key = x.key + y.key;								//Adding the frequencies together

            Q.insert(z);
        }

        size = C.length;

        return (Node) Q.extractMin().data;						//Returning the root of the tree
    }

    public static String[] orderedTraversal(Node root) {
        orderedArray = new String[size];
        inorderTreeWalk(root, "");
        return orderedArray;
    }

    //Inorder-Tree_walk(x) implemented directly from pseudo code from the book(page 288)
    private static void inorderTreeWalk(Node node, String code) {
        if(node != null) {
            inorderTreeWalk(node.leftChild, code+"0");
            if(node.data > -1) {
                orderedArray[node.data] = code;
            }
            inorderTreeWalk(node.rightChild, code+"1");
        }
    }
}


// Node for å representere koblinger i treet.
class Node {
    // setter variabler: key, høyre & venstre barn.
    public static int NULL = -1;

    public Node leftChild;
    public Node rightChild;
    public int data;	//endret da det gir bedre mening til huffman

    public Node() {
        this.data = NULL;
    }

    public Node(int data) {
        this.data = data;
    }
}

class Element {

    public int key;
    public Object data;

    public Element(int i, Object o){
        this.key = i;
        this.data = o;
    }
}


interface PQ {
    Element extractMin();
    void insert(Element e);
}

class PQHeap implements PQ {

    // Opretter et nytt Array av Element.
    static Element[] elemArr;
    // Opretter en heapsize variabel, for å kunne holde styr på
    // størrelsen heapen (Array).
    static int heapsize;

    // Instantierer Arrayet.
    public PQHeap(int maxElms)
    {
        elemArr = new Element[maxElms +1];
    }

    // Extracter det minste element.

    public Element extractMin()
    {
        // Opretter ny variabel min og tildeler den element fra index 1,
        // Arrayet.
        Element min = elemArr[1];
        // Flytter siste element fra arrayet til index 1.
        elemArr[1] = elemArr[heapsize];
        // Minsker størrelsen av heapsize med én.
        heapsize--;
        // Kjører minHeapy igen på index 1.
        minHeapy(1);
        // Returnerer min
        return min;
    }

    // setter inn et element i Array
    public void insert( Element e )
    {
        // Oppretter variabel i og tildeler størrelsen av heapen +1
        int i = heapsize +1;
        // setter inn e på index i, i Array-listen.
        elemArr[i] = e;
        // øker størrelsen af heapsize med én.
        heapsize++;
        // Lager en while løkke, til exchange nåværende index plass, med
        // dens parent index plass.
        while ( i > 1 && elemArr[i/2].key > elemArr[i].key)
        {
            // Lager en temp variabel til å oppbecare parent.
            Element tmp = elemArr[i/2];
            // setter inn child på parent.
            elemArr[i/2] = elemArr[i];
            // setter inn tidligere parent, på child.
            elemArr[i] = tmp;
            // Halverer index for ny parent.
            i = i/2;
        }
    }

    // begynner minimum heapify med parameteret myIndex
    public static void minHeapy( int myIndex )
    {
        // Oppretter left variabel, som er venstre child.
        int left = 2 * myIndex;
        // Oppretter right variabel, som er høyre child.
        int right = 2 * myIndex +1;
        // Oppretter int til minste element.
        int smallest;

        // sjekker om left er mindre eller lik med heapsize
        // og sjekker om element med index 'left' er mindre enn element
        // med index myIndex
        if(left <= heapsize && (elemArr[left].key < elemArr[myIndex].key))
        {
            //Setter smallest til left.
            smallest = left;
        }

        else
        {
            //Ellers setter smallest til myIndex.
            smallest = myIndex;
        }

        //Samme som før, bare med sjekk til høyre
        if(right <= heapsize && elemArr[right].key < elemArr[smallest].key)
        {
            smallest = right;
        }

        // Hvis smallest fortsatt er myIndex, kører koden ferdig, da tingene
        // er i orden.
        if (smallest != myIndex)
        {
            // Hvis smallest er endret, lager exchange mellom de to
            // elementer, og kjører mimHeapy med smallest som utgangspunkt.
            Element tmp = elemArr[myIndex];
            elemArr[myIndex] = elemArr[smallest];
            elemArr[smallest] = tmp;
            minHeapy(smallest);
        }
    }
}



/**
 * Methods to transform an input byte stream into a stream of
 * bits.
 */

class BitInputStream {

    // Underlying byte stream to read from.
    private final InputStream input;

    // Buffer of up to 8 bits from the most recently read byte of the
    // underlying byte input stream. Is an int in the range 0 to 255
    // if bits are available, or is -1 if the end of stream is
    // reached.
    private int nextBits;

    // Always between 0 and 8, inclusive.
    private int numBitsRemaining;

    private boolean isEndOfStream;


    // Creates a bit input stream based on the given byte input stream.
    public BitInputStream(InputStream in) {
        if (in == null)
            throw new NullPointerException("No input stream given");
        input = in;
        numBitsRemaining = 0;
        isEndOfStream = false;
    }


    // Reads a bit from the stream. Returns 0 or 1 if a bit is
    // available, or -1 if the end of stream is reached. The end of
    // stream always occurs on a byte boundary.
    public int readBit() throws IOException {
        if (isEndOfStream)
            return -1;
        if (numBitsRemaining == 0) {
            nextBits = input.read();
            if (nextBits == -1) {
                isEndOfStream = true;
                return -1;
            }
            numBitsRemaining = 8;
        }
        numBitsRemaining--;
        return (nextBits >>> numBitsRemaining) & 1;
    }


    // Reads an int from the stream. Throws IOException if 32 bits are
    // not available.
    public int readInt() throws IOException {
        int output = 0;
        int nextBit;
        int bitsAdded = 0;
        while(bitsAdded < 32){
            nextBit = readBit();
            if (nextBit == -1)
                throw new IOException("Not enough bits while trying to read int");
            output = output << 1 | nextBit;
            bitsAdded++;
        }
        return output;
    }


    // Closes this stream and the underlying InputStream.
    public void close() throws IOException {
        input.close();
    }

}

/**
 * Methods to transform an output byte stream into a stream of
 * bits. Because they are written to an underlying byte stream, the
 * end of the stream is padded with 0's up to a multiple of 8 bits.
 */

final class BitOutputStream {

    // Underlying byte stream to write to.
    private final OutputStream output;

    // The accumulated bits for the current byte. Always an int in the
    // range 0 to 255.
    private int currentByte;

    // The number of accumulated bits in the current byte. Always
    // between 0 and 8, inclusive.
    private int numBitsInCurrentByte;


    // Creates a bit output stream based on the given byte output
    // stream.
    public BitOutputStream(OutputStream out) {
        if (out == null)
            throw new NullPointerException("No output stream given");
        output = out;
        currentByte = 0;
        numBitsInCurrentByte = 0;
    }


    // Writes a bit to the stream. The specified bit must be 0 or 1.
    public void writeBit(int b) throws IOException {
        if (!(b == 0 || b == 1))
            throw new IllegalArgumentException("Argument must be 0 or 1");
        currentByte = currentByte << 1 | b;
        numBitsInCurrentByte++;
        if (numBitsInCurrentByte == 8) {
            output.write(currentByte);
            numBitsInCurrentByte = 0;
        }
    }


    // Writes an int to the stream.
    public void writeInt(int b) throws IOException {

        int bitsWritten = 0;
        while (bitsWritten < 32){
            writeBit(b >>> (31-bitsWritten) & 1);
            bitsWritten++;
        }
    }


    // Closes this stream and the underlying OutputStream. If called
    // when this bit stream is not at a byte boundary, then the
    // minimum number of "0" bits (between 0 and 7 of them) are
    // written as padding to reach the next byte boundary.
    public void close() throws IOException {
        while (numBitsInCurrentByte != 0)
            writeBit(0);
        output.close();
    }

}
