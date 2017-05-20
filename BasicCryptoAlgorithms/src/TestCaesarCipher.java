/*
 * Class to break the code encrypted via Caesar Cipher using only one key
 * This algorithm assumes that the most frequently occuring character in a message is the letter 'E'
 * Will not work if frequency of any other character is greater than frequency of 'e' 
 */


import edu.duke.FileResource;

public class TestCaesarCipher {
	
	//Returns the decrypted message
	public String decrypt(String encrypted){
		//Count the frequency of each letter occurring in the encrypted text
		int[] freqs = countLetters(encrypted);
		//Find the most commonly occurring alphabet in the encrypted text and return its index
		//This is the position where 'e' was shifted to as 'e' is the most commonly occurring alphabet in the English language
		int maxIndex = getMaxIndex(freqs);
		//Get the decryption key by subtracting maxIndex by 4 (index of letter 'e')
		int dKey = -1;
		if(maxIndex < 4)
			dKey = 26 - (4 - maxIndex);
		else
			dKey = maxIndex - 4 ;
		//Call the encrypt function in the CaesarCipher class with the decryption key to return the original message
		CaesarCipher cc = new CaesarCipher(26 - dKey);
		return cc.encrypt(encrypted);
	}
	
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
	
	
	//Test Client
	public static void main(String[] args) {
		TestCaesarCipher tcc = new TestCaesarCipher();
		FileResource fr = new FileResource();
		String fileContent = fr.asString();
		CaesarCipher cc = new CaesarCipher(15);
		String encrypted = cc.encrypt(fileContent);
		System.out.println("Encrypted message using one key:"+encrypted);
		System.out.println("Decrypted message using one key:"+cc.decrypt(encrypted));
		System.out.println("Decrypted message using auto generated key:"+tcc.decrypt(encrypted));
		
	}
}
