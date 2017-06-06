/*
 * Tester class that tests code on a small input size
 * 
 * @author - Prasandeep Singh
 * @Date created - 06/02/2017
 * @Date modified - 06/05/2017
 */


package predictingWords;
import java.util.*;

public class WordGramTester {
	
	//Tests WordGram class on small inputs
	//Checks whether tthe object has been created correctly or not
	public void testWordGram(){
		String source = "this is a test this is a test this is a test of words";
		String[] words = source.split("\\s+");
		int size = 4;
		for(int index = 0; index <= words.length - size; index += 1) {
			WordGram wg = new WordGram(words,index,size);
			System.out.println(index+"\t"+wg.length()+"\t"+wg);
		}
	}
	
	//Tests equals function of the WordGram class
	public void testWordGramEquals(){
		String source = "this is a test this is a test this is a test of words";
		String[] words = source.split("\\s+");
		ArrayList<WordGram> list = new ArrayList<WordGram>();
		int size = 4;
		for(int index = 0; index <= words.length - size; index += 1) {
			WordGram wg = new WordGram(words,index,size);
			list.add(wg);
		}
		WordGram first = list.get(0);
		System.out.println("checking "+first);
		for(int k=0; k < list.size(); k++){
			//if (first == list.get(k)) {
			  if (first.equals(list.get(k))) {
				System.out.println("matched at "+k+" "+list.get(k));
			}
		}
	}
	
	//Test Client
	public static void main(String[] args) {
		WordGramTester wgt = new WordGramTester();
	//	wgt.testWordGram();
		wgt.testWordGramEquals();
	}
}
