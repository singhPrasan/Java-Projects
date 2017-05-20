/*
 * To implement the Caesar Cipher using one key encryption
 */
public class CaesarCipher {
	
	private String alphabet;
	private String shiftedAlphabet;
	private int mainKey;
	
	public CaesarCipher(int key){
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
		mainKey = key;
	}
	
	//To encrypt the message using one key
	public String encrypt(String input){
		StringBuilder encrypted = new StringBuilder(input);
				
		//Encrypt/Decrypt the input 
		for(int i = 0; i<input.length(); i++){
				encrypted = encryptChar(encrypted, i, shiftedAlphabet);
		}
		return encrypted.toString();
	}
	
	//Encryptes one character according to shiftedAplhabet and key
	public StringBuilder encryptChar(StringBuilder encrypted, int i, String shiftedAlphabet){
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
	
	
	//Returns the decrypted message
	public String decrypt(String encrypted){
		//Create an object of CaesarCipher class
		CaesarCipher cc = new CaesarCipher(26-mainKey);
		//Call the encrypt function in the CaesarCipher class with the decryption key to return the original message
		return cc.encrypt(encrypted);
	}
}
