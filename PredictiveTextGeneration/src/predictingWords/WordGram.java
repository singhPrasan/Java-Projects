package predictingWords;

import java.util.Arrays;

public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
        myHash = this.hashCode();
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
       return myWords.length;
    }

    public String toString(){
        String ret = "";
        for(int i = 0; i<myWords.length; i++)
        	ret += myWords[i] + " ";

        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if( this.length() != other.length() )	return false;
        for(int i = 0; i<myWords.length; i++){
        	if(! myWords[i].equals(other.wordAt(i)))
        		return false;
        }
        return true;

    }

    public WordGram shiftAdd(String word) {	
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        for(int i =1; i<myWords.length; i++){
        	out.myWords[i-1] = myWords[i];
        }
        out.myWords[myWords.length-1] = word;	
        return out;
    }
    
    public int hashCode(){
    	String wordGram = toString();
    	return wordGram.hashCode();
    }
    
    public int getMyHash(){
    	return myHash;
    }
    private void testShiftAdd(){
    	WordGram shifted = shiftAdd("yes");
    	System.out.println(shifted);
    }
    
    
    public static void main(String[] args) {
    	String input = "this is a test";
    	String[] source = input.split("\\s+");
		WordGram wg = new WordGram(source, 0, 4);
		wg.testShiftAdd();
	}

}