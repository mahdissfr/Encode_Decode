package com.aut;

/**it is used to generate an encrypted String or decrypted one by a string password
 * @author mahdis safari
 * @since 13.1.1396
 */
public class ComplexShiftCryptography extends BaseCryptography {
    /**
     * the password that each character change based on it's characters
     */
    String password;
    /**
     * in this constructor we just pass a string
     * it initialize the password
     * @param password that each character change based on it's characters
     */
    public ComplexShiftCryptography(String password) {
        this.password = password;
        }

    /**
     * the name of method is encrypt
     * used to encrypt the encoded String input by changing each
     * character's code based on it's code and password's character's code
     * and make that ready to become decoded
     * @param plainText a string that should become encrypted
     * @return an String
     */
    public String encrypt(String plainText) {
        char[] inputChars = plainText.toCharArray();
        int lengthOfKey = password.length();
        char[] shifter = new char[plainText.length()];
        char[] charsOfPassword = password.toCharArray();
        for (int i = 0; i < plainText.length(); i += lengthOfKey) {
            for (int j = 0; j < lengthOfKey; j++) {
                shifter[j] = charsOfPassword[j];
            }
        }
        int eachIndex;
        for (int i = 0; i < inputChars.length; i++) {
            eachIndex = findByte(inputChars[i]) + findByte(shifter[i]);
            if(eachIndex>63)
                eachIndex -= 64;
            inputChars[i] = words[eachIndex];
        }
        String complexShifted = "";
        for (char eachChar : inputChars) {
            complexShifted += eachChar;
        }
        return complexShifted;
    }
    /**the name of method is decrypt
     * used to decrypt the encoded String input by changing each
     * character's code and make that ready to become decoded
     * @param complex a string that should become decrypted
     * @return an String
     */
    public String decrypt(String complex) {
        char[] inputChars = complex.toCharArray();
        int lengthOfKey = password.length();
        char[] shifter = new char[complex.length()];
        char[] charsOfPassword = password.toCharArray();
        for (int i = 0; i < password.length(); i += lengthOfKey) {
            for (int j = 0; j < lengthOfKey; j++) {
                shifter[j] = charsOfPassword[j];
            }
        }
        int eachIndex;
        for (int i = 0; i < inputChars.length; i++) {
            eachIndex = findByte(inputChars[i]) - findByte(shifter[i]);
            if(eachIndex<0)
                eachIndex += 64;
            inputChars[i] = words[eachIndex];
        }
        String simpleShifted = "";
        for (char eachChar : inputChars) {
            simpleShifted += eachChar;
        }
        return simpleShifted;
    }
}
