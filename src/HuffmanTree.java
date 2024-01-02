import java.util.HashMap;

public class HuffmanTree<T extends Number & Comparable<T>> {
    // Attributes
    private Node<T> root;
    //In this class :
    //Find the code of every character and display them.
    // Method to encode a text using the Huffman codes
    // Method to decode a Huffman-encoded text


    public Node<T> getRoot() { //getter since root is private
        return root;
    }

    public void setRoot(Node<T> root) { //setter to re set the root
        this.root = root;
    }
    public void buildHuffTree(LinkedList<T> myNodes) {     // Method to construct the Huffman tree from a SortedLinked list
        /*
        the way that the method works :
        1- I will take first node from the sorted linked list by using getValueOfFirst in my linkedList class
        then get the second node, every time I'm taking the head and remove it the head is updating !

       2- combine the frequency of these nodes and make a new node which will be root and its value will be frequency of first node + frequency of second node, then
       update the left and right pointers to the 2 nodes !
       3- put the root back to the first of the linked list and update the head

       DO THE SAME OPERATIONS AGIAN UNTIL THE CONDITION OF MY WHILE LOOP IS FALSE WHICH IS THE SIZE IS GREATER THAN ONE !

       4-  After going out of the loop, there is exactly one node left in the linked list, it becomes the root of the Huffman tree.

         */
        while (myNodes.size() > 1) {
            //These nodes have the lowest frequencies in the sorted list thats why we are taking them!
            Node<T> node1 = myNodes.getValOfFirst(); // get the first value from the linked list
            Node<T> node2 = myNodes.getValOfFirst(); // get the second value from the linked list

            if (node2 == null) { //If node2 is null, it means there is only one node left, which becomes the root of the Huffman tree!
                setRoot(node1);
                break;
            }
            // Make the parent of these two leaf nodes that we take by summing their frequency!
            T frequencySum = (T) getNumericType(node1.frequency.intValue() + node2.frequency.intValue());
            //int sum = node1.frequency.intValue() + node2.frequency.intValue();
           // T frequencySum = (T) getNumericType(sum);

            //  \0 represent the null
            Node<T> parent = new Node<>('\0', frequencySum); //the sum of frequency of the two siblings is the freq of the parent

            //(char) (node1.character+ node2.character) CASTING DOESN'T WORK

            //set the left and the right of the parent !
            parent.left = node1;
            parent.right = node2;

            myNodes.add(parent); //The parent node is added back to the first of the linked list using myNodes.add(parent).
            //The loop continues until there is only one node remaining in the linked list.
        }
        //After the loop, if there is exactly one node left in the linked list, it becomes the root of the Huffman tree.
        if (myNodes.size() == 1) { //last node is the parent of all nodes
            setRoot(myNodes.getValOfFirst()); // Update the root node
        }
    }
    private Number getNumericType(double value) { //to solve the T value problem, I wasn't able to add T types !
        if (value == (int) value) {
            return (int) value;
        } else if (value == (long) value) {
            return (long) value;
        } else if (value == (float) value) {
            return (float) value;
        } else {
            return value;
        }
    }

    public void PreOrderTraverse(){
        PreOrderTraverse(root);
    }
    public void PreOrderTraverse(Node<T> root){ //recursive method to treverse the HUffmanTree
        if(root!=null){
            System.out.println(root.toString());
            PreOrderTraverse(root.left);
            PreOrderTraverse(root.right);
        }
    }

    public void traverseCode(Node<T> node , String code, HashMap<Character,String> huffmanCodes){  //Encoding
        // if we go to left then add "0" .
        // if we go to the right add"1" .
        // recursive calls for left and right of root , until the node is leaf so put the code and char in the hash map

        //I used here hash maps to store the string and its code !

        if(node == null) //node will be the root
            return;
        if (node.isLeaf()) {
            huffmanCodes.put(node.character, code);
        }
        traverseCode(node.left, code + "0", huffmanCodes);
        traverseCode(node.right, code + "1", huffmanCodes);


    }
    public void traverseCode(Node<T> node, String code) { //method overloading but here we will not store it with hash maps
        if (node == null)
            return;
        if (node.isLeaf()) {
            System.out.println(node.character + " - " + code);
        }
        traverseCode(node.left, code + "0");
        traverseCode(node.right, code + "1");
    }
    public String decode(String encodedSymbol) { //deCode a given text we will start from the root if the string was 0 means go to the left then if its 1 go to right until I reach a char ! then I will print it
        String decodedSymbol = ""; //this variable will hold the decoded symbol
        Node<T> iterator = root; //variable represents the current position in the Huffman tree and is initially set to the root node

        // Traverse the encoded symbol
        for (int i = 0; i < encodedSymbol.length(); i++) { //The for loop iterates over each character in the encodedSymbol string
            char currentChar = encodedSymbol.charAt(i);
            //At each iteration, the current character (currentChar) is obtained !
            //Check the current character:
            //If currentChar is '0', it means we need to move to the left child of the current node. So, the iterator is updated to its left child.
            //If currentChar is '1', it means we need to move to the right child of the current node. So, the iterator is updated to its right child.

            if (currentChar == '0') {
                iterator = iterator.left;
            } else if (currentChar == '1') {
                iterator = iterator.right;
            }

            if (iterator.isLeaf()) { //Check if the iterator is at a leaf node
                decodedSymbol += iterator.character;
                iterator = root;
               // If iterator.isLeaf() returns true, it means we have reached a leaf node, which represents a character in the original text
                // In that case, we append the character (iterator.character) to the decodedSymbol string.
                //Additionally, we reset the iterator to the root node so that we can continue decoding the next part of the encoded symbol.
            }
        }

        return decodedSymbol;
    }


}
