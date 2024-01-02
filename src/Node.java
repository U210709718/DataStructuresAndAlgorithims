public class Node <T extends Comparable<T>> implements Comparable<Node<T>> {
    public char character; //character type
    public T frequency; // T type to hold the frequency
    public Node<T> next , left , right; //three pointers : left and right will be used in Huffman Tree , and next pointer in linkedList class
    //public int code;

    public Node( char character , T frequency ) {
        this.character = character;
        this.frequency = frequency;
        this.next = null;
        this.left = null;
        this.right = null;
       // this.code = code;
    }
    public boolean isLeaf() { //this method to check whether a node is a leaf or not by checking its left and right pointers if they point to null means its a leaf
        return left == null && right == null;
    }

    @Override
    public String toString() {
        return String.valueOf(character) + ":" + frequency;
    }
    @Override
    public int compareTo(Node<T> otherNode) {
        return this.frequency.compareTo(otherNode.frequency);
    }
}
