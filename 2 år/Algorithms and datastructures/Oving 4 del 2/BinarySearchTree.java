import java.util.*;
import java.util.LinkedList;

public class BinarySearchTree {
    public static void main(String[] args) {
        boolean finished = false;
        Scanner in = new Scanner(System.in);

        while (!finished) {
            System.out.println("Enter text you'd like sorted into a Binary Search Tree\n" +
                    "Write exit to exit program");
            System.out.print("Enter words: \n");
            String writtenWords = in.nextLine();
            String[] listWords = writtenWords.trim().split("\\s+");
            if (writtenWords.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                finished = true;
                System.exit(0);
            } else if (listWords.length > 0) {
                TreeNode.printBinaryTree(TreeNode.binarySearchTree(listWords).get(0));
            } else {
                System.out.println("Exiting...");
                in.close();
                finished = true;
                System.exit(0);
            }
        }
    }
}

class TreeNode {
    private String key;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(String key) {
        this.key = key;
    }

    public void setLeftNode(TreeNode left) {
        this.left = left;
    }

    public void setRightNode(TreeNode right) {
        this.right = right;
    }

    public String getKey() {
        return key;
    }

    public TreeNode getLeftNode() {
        return left;
    }

    public TreeNode getRightNode() {
        return right;
    }

    public static LinkedList<TreeNode> binarySearchTree(String[] wordList) {
        ArrayList<String> wordString = new ArrayList<>(List.of(wordList));
        LinkedList<TreeNode> listOfWords = new LinkedList<>();
        listOfWords.add(new TreeNode(wordString.get(0)));

        for (int i = 1; i < wordString.size(); i++) {
            TreeNode parent = listOfWords.get(0);
            TreeNode newNode = new TreeNode(wordString.get(i));
            boolean sorted = false;
            while (!sorted) {
                if (newNode.getKey().compareToIgnoreCase(parent.getKey()) < 1) {
                    if (parent.getLeftNode() == null) {
                        parent.setLeftNode(newNode);
                        listOfWords.add(newNode);
                        sorted = true;
                    } else {
                        parent = parent.getLeftNode();
                    }
                } else {
                    if (parent.getRightNode() == null) {
                        parent.setRightNode(newNode);
                        listOfWords.add(newNode);
                        sorted = true;
                    } else {
                        parent = parent.getRightNode();
                    }
                }
            }
        }
        return listOfWords;
    }

    /**
     * Takes in a root node
     * uses LinkedList both for level of depth / height, and for a temporary TreeNode
     * which is used as a placeholder to add nodes into binary tree
     *
     * Copied from: https://www.geeksforgeeks.org/print-binary-tree-2-dimensions/
     * Small adjustments done, i.e. encompass int height to conform to our method of finding height
     * @param root to be printed
     */
    public static void printBinaryTree(TreeNode root) {
        LinkedList<TreeNode> treeLevel = new LinkedList<>();
        treeLevel.add(root);
        LinkedList<TreeNode> temp = new LinkedList<>();
        int counter = 0;
        int height = findHeight(root) + 1;

        double numberOfElements = (Math.pow(2, (height + 1) - 1));

        while (counter <= height) {
            TreeNode removed = treeLevel.removeFirst();
            if (temp.isEmpty()) {
                printSpace(numberOfElements / Math.pow(2, counter + 1), removed);
            } else {
                printSpace(numberOfElements / Math.pow(2, counter), removed);
            }
            if (removed == null) {
                temp.add(null);
                temp.add(null);
            } else {
                temp.add(removed.left);
                temp.add(removed.right);
            }

            if (treeLevel.isEmpty()) {
                System.out.println();
                System.out.println();
                treeLevel = temp;
                temp = new LinkedList<>();
                counter++;
            }

        }
    }

    /**
     * Method for adding spaces in binary tree
     * Copied from: https://www.geeksforgeeks.org/print-binary-tree-2-dimensions/
     * @param n used to add tabs in between nodes in a binary tree
     * @param removed removes a node from an object, and then places it into the tree. If it's null, it simply adds a space
     */
    public static void printSpace(double n, TreeNode removed){
        for(; n > 0; n--) {
            System.out.print("\t");
        }
        if(removed == null){
            System.out.print(" ");
        }
        else {
            System.out.print(removed.key);
        }
    }

    public static int findHeight(TreeNode n) {
        if (n == null) return -1;
        else {
            int lh = findHeight(n.left);
            int rh = findHeight(n.right);

            if (lh >= rh) return lh + 1;
            else return rh + 1;
        }
    }
}