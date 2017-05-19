import edu.duke.FileResource;

/*
 * Class to break the code encrypted via Caesar Cipher 
 */

public class BreakCaesar {
	
	//Returns the decrypted message
	public String decrypt(String encrypted){
		//Create an object of CaesarCipher class
		CaesarCipher cc = new CaesarCipher();
		
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
		return cc.encrypt(encrypted, 26 - dKey);
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
	
	
	//Test client
	public static void main(String[] args) {
		BreakCaesar bc = new BreakCaesar();
		System.out.println(bc.decrypt("NBYLY ULY ZCPY NLYYM, UHX NBLYY ZFIQYLM CH NBY GYUXIQ IZ XLYUGM CH NYRUM."));		
	}
}
