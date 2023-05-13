package edu.guilford;
// The WordCount class counts the number of words that 
// appears in the file
public class WordCount implements Comparable<WordCount>{

    // attributes
    private String word;
    private int count;

    // constructor
    public WordCount(String word, int count) {
        this.word = word;
        this.count = count;
    }

    // getters and setters
    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setCount(int count) {
        this.count = count;
    }

    // The compareTo method

    // Use the compareTo method to compare the words as well 
    // as count how many times they appear
    public int compareTo(WordCount wordCount) {
        if (this.count > wordCount.count) {
            return 1;
        } else if (this.count < wordCount.count) {
            return -1;
        } else {
            return 0;
        }
    }
   
}
