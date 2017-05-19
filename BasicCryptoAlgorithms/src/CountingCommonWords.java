import edu.duke.FileResource;

/*
 * To count how many times the most common words occur in the given files
 */


public class CountingCommonWords {
	
	//Count number of times the most common words occur in Shakespeare's plays
	public void countShakespeare(){
		String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt", "likeit.txt", "macbeth.txt", "romeo.txt"};
		
		//Get all the common words
		String[] common = getCommon();
		int counts[] = new int[common.length];
			
		//Count all the common words in all the mentioned files
		for(int i = 0; i<plays.length; i++){
			FileResource fr = new FileResource("data/"+plays[i]);
			countWords(fr, common, counts);
			System.out.println("File scan of "+plays[i]+" complete");
		}
		
		//Print out the results
		for(int i =0; i<common.length; i++){
			System.out.println(common[i]+"\t"+counts[i]);
		}
	}
	
	//Returns the 20 most common words in the English language already written into a text file
	private String[] getCommon(){
		FileResource fr = new FileResource("data/common.txt");
		String[] common = new String[20];
		int index = 0;
		for(String word : fr.words()){
			common[index] = word;
			index++;
		}
		return common;
	}
	
	//Counts the number of time each common word occurs in the given file
	private void countWords(FileResource fr, String[] common, int[] counts){
		for(String word : fr.words()){
			word = word.toLowerCase();
			int index = indexOf(common, word);
			if(index != -1)
				counts[index]++;
			
		}
	}
	
	//Returns the index position of the requested word in the common words array
	private int indexOf(String[] common, String word){
		for(int i =0; i<common.length; i++){
			if(common[i].equals(word))
				return i;
		}
		return -1;
	}
	
	
	//Test Client
	public static void main(String[] args) {
		CountingCommonWords ccw = new CountingCommonWords();
		ccw.countShakespeare();
	}
}
