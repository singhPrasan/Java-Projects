/*
 * Emulates String() class for a group of words
 * All required functions of the String class have been implemented in
 * this class for a group of words in the form of a String array
 * 
 * @author - Prasandeep Singh
 * @date created - 06/03/2017
 * @date modified - 06/05/2017
 */
package predictingWords;

public class WordGram {
    private String[] myWords;
    private int myHash;

    //Constructor to initialize instance variables	
    //Copies size number of words from the source and puts them into myWords
    //Also initializes myHash for Hashing in hashmaps
    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
        myHash = this.hashCode();
    }

    //Returns the index of a particular word in the array
    //Similar to str.charAt() function of String class
    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    //Returns the number of words in the object
    //Similar to String.length() which returns number of characters
    public int length(){
       return myWords.length;
    }

    //To print all the words in the object
    public String toString(){
        String ret = "";
        for(int i = 0; i<myWords.length; i++)
        	ret += myWords[i] + " ";

        return ret.trim();
    }

    //Checks whether the two word grams contain the same set of words of not
    //Similar to String.equals() function
    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if( this.length() != other.length() )	return false;
        for(int i = 0; i<myWords.length; i++){
        	if(! myWords[i].equals(other.wordAt(i)))
        		return false;
        }
        return true;

    }

    //Shifts every word in the object to the left to make space for new word
    //The first word is dropped from the object whenever this function is invoked
    public WordGram shiftAdd(String word) {	
        WordGram out = new WordGram(myWords, 0, myWords.length);
        for(int i =1; i<myWords.length; i++){
        	out.myWords[i-1] = myWords[i];
        }
        out.myWords[myWords.length-1] = word;	
        return out;
    }
    
    //Generates a hashcode for the object
    //Similar to String.hashCode() function
    public int hashCode(){
    	String wordGram = toString();
    	return wordGram.hashCode();
    }
    
    //Tests shiftAdd() function
    private void testShiftAdd(){
    	WordGram shifted = shiftAdd("yes");
    	System.out.println(shifted);
    }
    
    //Test Client
    public static void main(String[] args) {
    	String input = "this is a test";
    	String[] source = input.split("\\s+");
		WordGram wg = new WordGram(source, 0, 4);
		wg.testShiftAdd();
	}

}