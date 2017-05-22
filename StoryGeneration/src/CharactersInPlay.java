/*
 * To display name of the characters who have more dialogues than the rest of the people in the play
 */

import java.util.ArrayList;

import edu.duke.FileResource;

public class CharactersInPlay {
	private ArrayList<String> names;
	private ArrayList<Integer> counts;
	
	public CharactersInPlay(){
		names = new ArrayList<>();
		counts = new ArrayList<>();
	}
	
	//Updates the number of occurences of each person
	public void update(String person){
		int index = names.indexOf(person);
		if(index == -1){
			names.add(person);
			counts.add(1);
		}else{
			int value = counts.get(index);
			counts.set(index, value + 1);
		}
	}
	
	//Finds	all new characters
	public void findAllCharacters(){
		names.clear();
		counts.clear();
		FileResource fr = new FileResource();
		for(String line : fr.lines()){
			if(line.contains(".")){
				String characterName = getName(line);
				update(characterName);
			}
		}
	}
	//Returns the name of the character
	private String getName(String line){
		StringBuilder sb = new StringBuilder();
		int index = line.indexOf('.');
		for(int i = index-1; i>0; i--)
			sb.append(line.charAt(i));
		return sb.reverse().toString();
	}
	
	//Prints the name of the person with number of dialogues in the range of num1 to num2
	public void characterWithNumParts(int num1, int num2){
		for(int i = 0 ; i<names.size(); i++){
			if( counts.get(i) >= num1 && counts.get(i) <= num2)
				System.out.println(names.get(i));
		}
	}
	
	//Prints the name and number of times the person with most dialogues spoke
	public void characterWithMostDialogues(){
		int maxIndex = 0;
		for(int i = 0; i<names.size(); i++){
			
			if( counts.get(maxIndex) < counts.get(i))
				maxIndex = i;
		}
		System.out.println("Character with most dialogues :"+names.get(maxIndex)+"\t"+counts.get(maxIndex));
	}
	
	//Test Client
	public static void main(String[] args) {
		CharactersInPlay cip = new CharactersInPlay();
		cip.findAllCharacters();
		for(int i = 0; i<cip.names.size(); i++){
			if(cip.counts.get(i) > 2){
				System.out.println( cip.counts.get(i) + "\t" +cip.names.get(i));
			}
		}
		cip.characterWithNumParts(10, 15);
		cip.characterWithMostDialogues();
	}
}
