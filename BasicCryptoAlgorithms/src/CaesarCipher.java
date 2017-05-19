/*
 * To implement the Caesar Cipher
 */
public class CaesarCipher {
	
	
	//To encrypt the message using one key
	public String encrypt(String input, int key){
		StringBuilder encrypted = new StringBuilder(input);
		
		//Set up the shifted alphabet
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String shiftedAlphabet = generateShifted(alphabet, key);
		
		//Encrypt/Decrypt the input 
		for(int i = 0; i<input.length(); i++){
				encrypted = encrypt(encrypted, i, shiftedAlphabet, key);
		}
		return encrypted.toString();
	}
	
	
	//Encryptes the message in accordance to two separate keys
	public String encryptTwoKeys(String input, int key1, int key2){
		StringBuilder encrypted = new StringBuilder(input);
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String shiftedAlphabet1 = generateShifted(alphabet, key1);
		String shiftedAlphabet2 = generateShifted(alphabet, key2);
		
		for(int i = 0; i<input.length(); i++){
			if(i % 2 == 0){
				encrypted = encrypt(encrypted, i, shiftedAlphabet1, key1);
			}else{
				encrypted = encrypt(encrypted, i, shiftedAlphabet2, key2);
			}
		}
		return encrypted.toString();
	}
	
	
	//To shift the alphabet according to the key specified
	private String generateShifted(String alphabet, int key){
		String shiftedAlphabet = alphabet.substring(key);
		shiftedAlphabet = shiftedAlphabet + alphabet.substring(0, key);
		return shiftedAlphabet;
	}
	
	//Encryptes one character according to shiftedAplhabet and key
	private StringBuilder encrypt(StringBuilder encrypted, int i, String shiftedAlphabet, int key){
		boolean isLowerCase = false;
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		//Build the encryption
		//Get the character from the input message
		char ch = encrypted.charAt(i);
				
		if(Character.isLowerCase(ch)){
			isLowerCase = true;
			ch = Character.toUpperCase(ch);
		}
				
		//Get the index of this character in alphabet
		int indexInAlphabet = alphabet.indexOf(ch);
		
		//If this character is not present in the alphabet, go to next character
		if(indexInAlphabet == -1)
			return encrypted;
			
		//find the character at this index in shiftedAlphabet
		char charToReplaceWith = shiftedAlphabet.charAt(indexInAlphabet);
		
		//Check for lower case
		if(isLowerCase){
			charToReplaceWith = Character.toLowerCase(charToReplaceWith);
		}
		//Replace the current character with 
		encrypted.setCharAt(i,charToReplaceWith );
		
		return encrypted;
	}
	
	
	
	//Test client
	public static void main(String[] args) {
		CaesarCipher cc = new CaesarCipher();
	//	String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
		String message = "THERE ARE FIVE TREES, AND THREE FLOWERS IN THE MEADOW OF DREAMS IN TEXAS.";
		int key = 20;
		String encrypted = cc.encrypt(message, key);
		System.out.println("Encrypted message using one key:"+encrypted);
		System.out.println("Decrypted message using one key:"+cc.encrypt(encrypted, 26-key));
		System.out.println("Encrypted message using two keys:"+cc.encryptTwoKeys(message, 8, 21));
	}
}
