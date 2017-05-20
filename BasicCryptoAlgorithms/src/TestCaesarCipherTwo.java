/*
 * Used to break encryptions done with caesar ciphers using two keys
 * This algorithm assumes that the most frequently occuring character in a message is the letter 'E'
 * Will not work if frequency of any other character is greater than frequency of 'e' 
 */

import edu.duke.FileResource;

public class TestCaesarCipherTwo {
	//Returns the count array consisting of number of times each letter occurs in the encrypted 
	private int[] countLetters(String message){
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		int[] counts = new int[26];
		
		for(int i =0; i<message.length(); i++){
			char ch = Character.toLowerCase(message.charAt(i));
			int index = alphabet.indexOf(ch);
			if(index!=-1)
				counts[index]++;
		}
		return counts;
	}
	
	//Returns index of the most frequent letter in the encrypted text
	private int getMaxIndex(int[] freqs){
		int maxIndex = 0;
		for(int i = 0; i<freqs.length; i++){
			if( freqs[maxIndex] < freqs[i])
				maxIndex = i;
		}
		return maxIndex;
	}
	
	//Returns a new String that is every other character from message starting with the start position
	public String halfOfString(String message, int start){
		StringBuilder sb = new StringBuilder();
		for(int i = start ; i<message.length(); i+=2){
			sb.append(message.charAt(i));
		}
		return sb.toString();
	}

	//Returns the key used to encrypt the string s
	public int getKey(String s){
		int freqs[] = countLetters(s);
		int maxIndex = getMaxIndex(freqs);
		//Get the decryption key by subtracting maxIndex by 4 (index of letter 'e')
		int dKey = -1;
		if(maxIndex < 4)
			dKey = 26 - (4 - maxIndex);
		else
			dKey = maxIndex - 4 ;
		return dKey;
	}
	
	//Breaks a message encrypted with caesar cipher using two keys
	public String decrypt(String encrypted){
		String firstString = halfOfString(encrypted, 0);
		String secondString = halfOfString(encrypted, 1);
		int key1 = getKey(firstString);
		int key2 = getKey(secondString);
		System.out.println("key1 :"+key1);
		System.out.println("key2 :"+key2);
		//Create an object of CaesarCipherTwo class
		CaesarCipherTwo cct = new CaesarCipherTwo(26-key1, 26-key2);
		return cct.encrypt(encrypted);
	}	
	
	
	//Test Client
	public static void main(String[] args) {
		TestCaesarCipherTwo tcct = new TestCaesarCipherTwo();
		FileResource fr = new FileResource();
		String fileContent = fr.asString();
		CaesarCipherTwo cct = new CaesarCipherTwo(14,24);
		String encrypted = cct.encrypt("Hfs cpwewloj loks cd Hoto kyg Cyy.");
		System.out.println("Encrypted message using two key:"+encrypted);
		System.out.println("Decrypted message using two key:"+cct.decrypt("Hfs cpwewloj loks cd Hoto kyg Cyy."));
		System.out.println("Decrypted message using auto generated key:"+tcct.decrypt(fileContent));
		
	}
}
