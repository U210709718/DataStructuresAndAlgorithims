import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        try { //Read letter.txt file
            File file = new File("C:\\Users\\seham\\IdeaProjects\\HomeWork2\\src\\letter.txt");
            Scanner scanner = new Scanner(file); //Creates a new Scanner object to read data from the file

            StringBuilder sb = new StringBuilder(); //  create a StringBuilder object to build the text from the file line by line

            while (scanner.hasNextLine()) { //this while loop is basically loops until there are no more lines in the file
                String line = scanner.nextLine();
                sb.append(line);
            }

            String text = sb.toString();

            //___________________________________________________________________________________________________________________________________________________


            //Task 1 - Find the characters and their frequencies of a given text which will be read from letter.txt file.  ## DONE

            /* Useful Functions in HashMaps
             1- put() to add key value pair   , 2- replace() replace key value pair
            3- get() to get a specific value ,  4- containsKey()  , containsValue() to check if its there

             */

            // map general interFace --> hash map implements from map!
            HashMap<Character, Integer> CharFrequeMap = new HashMap<>(); //Creates a new HashMap object , key - value pair (Character , Integer )
            //make the text into array of chars for example :  cinnamon -->  ['c','i','n','n','a','m','o','n']
            for (char character : text.toCharArray()) {
                CharFrequeMap.put(character, CharFrequeMap.getOrDefault(character, 0) + 1); //Increments the frequency of the current character in the frequencies map.
            }
            System.out.println("Task 1: The characters and their frequencies which have been read from letter.txt file :");
            for (Map.Entry<Character, Integer> entry : CharFrequeMap.entrySet()) { //Loop through each entry (key-value pair) in the frequencies map by using entrySet().
                char character = entry.getKey();
                int frequency = entry.getValue();
                System.out.println(character + ": " + frequency);
            }
            System.out.println("\t"); //make a space!


            //___________________________________________________________________________________________________________________________________________________


            // Task 2 - Keep these in a sorted linked list (use your own linked list class).        ## DONE

            LinkedList myLinkedList = new LinkedList<>();
            for (Map.Entry<Character, Integer> entry : CharFrequeMap.entrySet()) {
                char character = entry.getKey();
                int frequency = entry.getValue();
                myLinkedList.addCharacter(character, frequency);
            }
            System.out.println(" Task 2 - Keep characters and their frequencies in a sorted linked list.");
            myLinkedList.displayFrequencies();

            //___________________________________________________________________________________________________________________________________________________


            // Task 3- Construct the huffman tree using this alphabet(characters).                 ## DONE

            HuffmanTree myHuffTree = new HuffmanTree<>();
            myHuffTree.buildHuffTree(myLinkedList);

            System.out.println("Task 3- Construct the huffman tree using this alphabet(characters).");
            myHuffTree.PreOrderTraverse();
            System.out.println();

            //___________________________________________________________________________________________________________________________________________________


            //Task 4- Find the code of every character and display them.                            ##  DONE

            System.out.println("Task 4- (Encoding) Find the code of every character and display them");
            myHuffTree.traverseCode(myHuffTree.getRoot(), "");

            System.out.println();

            //___________________________________________________________________________________________________________________________________________________


            //Task 5- Encode the given text(read it from a source.txt) and write the coded text into encoded.txt file.  ## DONE


            File sourceFile = new File("C:\\Users\\seham\\IdeaProjects\\HomeWork2\\src\\source.txt");
            Scanner sorceScanner = new Scanner(sourceFile); //Creates a new Scanner object to read data from the file

            StringBuilder sourceBuilder = new StringBuilder(); //  create a StringBuilder object to build the text from the file line by line

            while (sorceScanner.hasNextLine()) { //this while loop is basically loops until there are no more lines in the file
                String Sline = sorceScanner.nextLine();
                sourceBuilder.append(Sline);
            }

            String textS =sourceBuilder.toString();

            HashMap <Character, Integer> CharFrequeMapSource = new HashMap<>(); //Creates a new HashMap object , key - value pair (Character , Integer )
            //make the text into array of chars for example :  cinnamon -->  ['c','i','n','n','a','m','o','n']
            for (char character : textS.toCharArray()) {
                CharFrequeMapSource.put(character, CharFrequeMapSource.getOrDefault(character, 0) + 1); //Increments the frequency of the current character in the frequencies map.
            }
            System.out.println("Related to task 5 , chars and their frequencies :");
            for (Map.Entry<Character, Integer> entry : CharFrequeMapSource.entrySet()) { //Loop through each entry (key-value pair) in the frequencies map by using entrySet().
                char character = entry.getKey();
                int frequency = entry.getValue();
                System.out.println(character + ": " + frequency);
            }

            LinkedList mySourceLinkedList = new LinkedList<>();
            for (Map.Entry<Character, Integer> entry : CharFrequeMapSource.entrySet()) {
                char character = entry.getKey();
                int frequency = entry.getValue();
                mySourceLinkedList.addCharacter(character, frequency);
            }
            HuffmanTree mySourceHuffTree = new HuffmanTree<>();
            mySourceHuffTree.buildHuffTree(mySourceLinkedList);

            HashMap<Character, String> huffmanCodes = new HashMap<>(); //is created to store the Huffman codes for each character.
            mySourceHuffTree.traverseCode(mySourceHuffTree.getRoot(), "", huffmanCodes); //encoding

            StringBuilder encodedTextBuilder = new StringBuilder();
            for (char character : textS.toCharArray()) {
                encodedTextBuilder.append(huffmanCodes.get(character));
            }
            String encodedText = encodedTextBuilder.toString();
            // After generating the encoded text...
            try (FileWriter encodedFile = new FileWriter("C:\\Users\\seham\\IdeaProjects\\HomeWork2\\src\\encoded.txt")) {
                encodedFile.write(encodedText); //writing the encoded text in the encoded.txt file
                System.out.println("Task 5: Encoded text has been written to encoded.txt file.");
            } catch (IOException e) {
                e.printStackTrace();
            }
            sorceScanner.close();
            System.out.println();




        //___________________________________________________________________________________________________________________________________________________


            //Task 6- Decode a given text(read it from an encoded.txt) and write the decoded text into decoded.txt file   ## DONE

            // Read encoded text from "encoded.txt"
            File encoded = new File("C:\\Users\\seham\\IdeaProjects\\HomeWork2\\src\\encoded.txt");
            Scanner encodedScanner = new Scanner(encoded);

            StringBuilder encodedBuilder = new StringBuilder();

            while (encodedScanner.hasNextLine()) {
                String line = encodedScanner.nextLine();
                encodedBuilder.append(line);
            }

            encodedScanner.close(); // Close the scanner

            String enCo = encodedBuilder.toString();
            String decodedText = mySourceHuffTree.decode(enCo); //decoding the encoded text and write it to decoded.txt

            try (FileWriter decodedFile = new FileWriter("C:\\Users\\seham\\IdeaProjects\\HomeWork2\\src\\decoded.txt")) {
                decodedFile.write(decodedText);
                System.out.println("Task 6: Decoded text has been written to decoded.txt file.");
            } catch (IOException e) {
                System.out.println("An error occurred while writing to decoded.txt: " + e.getMessage());
            }


            encodedScanner.close();




            scanner.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}
