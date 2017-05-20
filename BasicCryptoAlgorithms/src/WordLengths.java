import edu.duke.FileResource;

public class WordLengths {
	
	//Returns index of the maximum entry in the values array
	public int indexOfMax(int[] values){
		int maxIndex = 0;
		for(int i = 0; i<values.length; i++){
			if( values[i] > values[maxIndex])
				maxIndex = i;
		}
		return maxIndex;
	}
	
	//Returns the counts array consisting of number of times a word of particular length occurs
	public void countWordLengths(FileResource resource, int[] counts){
		for(String word : resource.words()){
			//Store the length of the current word
			int len = word.length();
			//Get the first character of the current word
			char firstChar = word.charAt(0);
			if( !Character.isLetter(firstChar))		len--;
			
			//Get the last character of the current word
			char lastChar = word.charAt(word.length()-1);
			if( !Character.isLetter(lastChar))		len--;
			
			if(len >= counts.length-1) len = counts.length-1;
			if(len>=0)	counts[len]++;
		}
	}
	
	//Finds the number of times each word length occurs in a file and also prints the most common word length in the file
	public void testCountResource(){
		FileResource fr = new FileResource();
		int[] counts = new int[31];
		countWordLengths(fr, counts);
		for(int i = 0; i<counts.length; i++){
			if(counts[i] != 0)
				System.out.println(counts[i] +" words with length "+i);
		}
		int maxIndex = indexOfMax(counts);
		System.out.println("Most common word length in the file :"+maxIndex);
	}
	
	public static void main(String[] args) {
		WordLengths wl = new WordLengths();
		wl.testCountResource();
	}
}
