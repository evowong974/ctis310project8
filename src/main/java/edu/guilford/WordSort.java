package edu.guilford;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Hello world!
 *
 */
public class WordSort{

    public static void main( String[] args )
    {
        Scanner input = new Scanner(System.in);
        Scanner filescan = null;

        Path dataLocation = null; 
        String fileName = null;
        String[][] words = null;

        try {
            System.out.println("Enter the name of the file to be sorted: ");
            fileName = input.nextLine();
            dataLocation = Paths.get(WordSort.class.getResource("/"
                + fileName).toURI());
                FileReader fileReader = new FileReader(dataLocation.toString());
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                filescan = new Scanner(bufferedReader);
                words = readData(filescan);
         } catch (URISyntaxException | FileNotFoundException | NullPointerException e) {
                    e.printStackTrace();
                    System.out.println("File not found.");
                    System.exit(1);
                }

        // This try-catch will make the words and put them 
        // into a new file as well as catch any errors that may occur.

        try{
            writeData(words, "output.txt");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            System.out.println("File not found.");
            System.exit(1);
        }

        // Here the program will prompt the user for a word to search for
        // and then it will search for the word and if found, it returns
        // the number of times it was found, otherwise it returns the message
        // that the word was not found in the file.

        System.out.println("Enter a word to search for: ");
        String searchWord = input.nextLine();
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i][0].equals(searchWord)) {
                count = Integer.parseInt(words[i][1]);
            }
        }
        if (count > 0) {
            System.out.println("The word " + searchWord + " was found " + count + " times within this file.");
        } else {
            System.out.println("The word " + searchWord + " was not found in this file.");
        }
            
}
// Stack is used for the program to read the file and store
// it where the program can access it.
// TreeSet is used to sort the temps in alphabetical order automatically post
// modification. 
    public static void writeData(String[][] words, String location) throws URISyntaxException, IOException {
    // Recalling from class, "throws" refers that these errors are not the program's problems 
    // but the user who had initiated to run this method on the program.
    // Grab the path of the correct file
    Path locationPath = Paths.get(WordSort.class.getResource("/edu/guilford/").toURI());

    // Make and open a file in that folder
    FileWriter fileLocation = new FileWriter(locationPath.toString() + "/" + location);
    BufferedWriter bufferedWriter = new BufferedWriter(fileLocation);
    // This will write the data into the file
    for (int i = 0; i < words[0].length; i++) {
        for (int j = 0; j < words[0].length; j++) {
            bufferedWriter.write(words[i][j] + " ");
    }
    bufferedWriter.newLine();
}
    // This will close the file
    bufferedWriter.close();

}
    public static String[][] readData(Scanner filescan) {
        Stack<String> stack = new Stack<String>();
        TreeSet<String> sortedWords = new TreeSet<String>();

        // This while loop is used to read the file as well as modify based
        // on the specifications

        while (filescan.hasNext()) {
            String temp = filescan.next();
            // Remove all punctuation from the string
            temp = temp.replaceAll("[^a-zA-Z0-9]", "");
            // Remove all numbers from the string
            temp = temp.replaceAll("[0-9]", "");
            // Convert all letters to lowercase
            temp = temp.toLowerCase();
            // Push the modified string to the stack
            stack.push(temp);
              }
              // Uses the WordCount class to count the number of times a word appears
              // inside the file.

              while (!stack.isEmpty()) {
                  String word = stack.pop();
                  int count = 1; // This is the base as the word/element has to appear at least once
                  
                  // loop through the stack to see if the word appears again
                  for (int i = 0; i < stack.size(); i++) {
                      if (word.equals(stack.get(i))) {
                          count++;
        
                      }
                  }
             // Make a new instance of the WordCount class and add
             // it to the TreeSet
                WordCount wordCount = new WordCount(word, count);
                sortedWords.add(wordCount.getWord() + " " +
                String.valueOf(wordCount.getCount()));

            // Shall there be any duplicates of the same word, it will be removed
            // from the stack
            while (stack.contains(word)) {
                stack.remove(word);
            }
        }

        

        // Print the sorted words from the TreeSet
        String[][] words = new String[sortedWords.size()][2];
        int i = 0;

        for (String word : sortedWords) {
            words[i][0] = word.substring(0, word.indexOf(" "));
            words[i][1] = word.substring(word.indexOf(" ") + 1);
            i++;
        }
        return words;
             
    } 
}



    

