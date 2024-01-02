public class LinkedList <T extends Comparable<T>> {
    //Best practice to make class variables private
    private Node<T> head; //the first pointer we have is: head , first node of the linked list
    private Node<T> tail; //the last pointer we have is tail, which is the last node in linked list
    private int size ; //variable to track adding or deleting from linked list!

    public LinkedList(){ //constructor
        head = null;
        tail = null;
        size =0; //we started this at null, no new node that's why its = zero

    }
    public int size(){
        return size;
    }
    public Node getHead(){ //getter to get the head since its private
        return head;
    }

    public void addCharacter(char character, T frequency) {  //this method will add the characters and their frequencies sorted! based on the frequency
        Node<T> newNode = new Node<>(character, frequency); //making a new node

        if (head == null) {
            // Linked list is empty, so new node will be head and tail
            head = newNode;
            tail = newNode;
            // New node has lower frequency, insert at head , this new node becomes new head
        } else if (newNode.frequency.compareTo(head.frequency)==-1) {
            newNode.next = head;
            head = newNode;
        }
        else if (newNode.frequency.compareTo(tail.frequency) > 0) { // New node has higher frequency than the tail, add to end and make it new tail
            tail.next = newNode;
            tail = newNode;
        } else {
            // Find the correct position based on frequency
            Node<T> current = head;
            Node<T> previous = null;
            while (current != null && newNode.frequency.compareTo(current.frequency) >= 0) { //the new node freq is greater than the values listed so keep going!until you find the correct position
                previous = current;
                current = current.next;
            }
            newNode.next = current;
            if (previous != null) {
                previous.next = newNode;
            }
        }


        size++; //increment the size !
    }

    public  Node<T> getValOfFirst() { //getting the value of head and return it (REMOVE it from the linked list)
        if (head == null) {
            return null;  // Handle empty list
        }
        Node<T> removedOne = head;
        head = head.next;
        size--;
        // if we have one node we delete it we also have to update the tail!!!
        if (head == null) {
            tail = null;
        }
        return removedOne;
    }

    public void add(Node<T> newNode) { //adding a new node to the first of the list
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }




    public void displayFrequencies() { //display method!
        Node iterator = head;
        while (iterator != null) {
            System.out.println(iterator.character + ": " + iterator.frequency);
            iterator = iterator.next;
        }
        System.out.println(); // Add a new line after printing the list
    }


}

