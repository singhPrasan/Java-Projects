import java.util.HashMap;

import edu.duke.FileResource;

public class CodonCount {
	
	private HashMap<String, Integer> map ;
	
	public CodonCount(){
		map = new HashMap<String, Integer>();
	}
	
	
	//Builds a map of codon and its count for a given reading frame
	public void buildCodonMap(int start, String dna){
		String codon = "";
		for(int i = start; i<dna.length(); i+=3){
			if(i+3 < dna.length())
				codon = dna.substring(i, i+3);
			else 
				continue;
			if(!map.containsKey(codon)){
				map.put(codon, 1);
			}else{
				map.put(codon, map.get(codon)+1);
			}
		}
	}
	
	//Returns codon with the maximum occurrence
	public String getMostCommonCodon(){
		int mostCounts = 0;
		String mostCommon = "";
		for(String codon : map.keySet()){
			if( mostCounts < map.get(codon)){
				mostCounts = map.get(codon);
				mostCommon = codon;
			}
		}
		return mostCommon;
	}
	
	//Prints the codon and its count in a particular range for a given reading frame
	public void printCodonCounts(int start, int end){
		System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:");
		for(String codon : map.keySet()){
			int count = map.get(codon);
			if( count >= start && count <= end)
				System.out.println(codon +"\t"+map.get(codon));
		}
	}
	
	
	
	//Test client
	public static void main(String[] args) {
		CodonCount[] cc = new CodonCount[3];
		for(int i = 0 ; i <cc.length; i++)
			cc[i] = new CodonCount();
		
		FileResource fr = new FileResource();
		String dna = fr.asString().toUpperCase();
		int start = 7;
		int end = 7;
		
		for(int readingFrame = 0; readingFrame<3; readingFrame++){
			cc[readingFrame].buildCodonMap(readingFrame, dna);
			System.out.println("Total number of unique codons in readingFrame ["+readingFrame+"] : "+cc[readingFrame].map.keySet().size());
			String mostCommonCodon = cc[readingFrame].getMostCommonCodon();
			System.out.println("Most common codon and its count in readingFrame ["+readingFrame+"] : "+mostCommonCodon+"\t"+cc[readingFrame].map.get(mostCommonCodon));
			cc[readingFrame].printCodonCounts(start, end);
			System.out.println();
		}
		
	}
}
