package com.aut;

/**it is used to generate an encrypted String or decrypted one by an integer password
 * @author mahdis safari
 * @since 13.1.1396
 */
public class SimpleShiftCryptography extends BaseCryptography {
    /**
     * the password that each character change based on it
     */
    int k;
    /**
     * in this constructor we just pass a string
     * it initialize k ,the password.
     * @param k password that each character change based on it
     */
     SimpleShiftCryptography(String k) {
        this.k = Integer.parseInt(k);
     }

    /**
     * the name of method is encrypt
     * used to encrypt the encoded String input by changing each
     * character's code based on it's code and password that is an integer
     * and make that ready to become decoded
     * @param plainText a string that should become encrypted
     * @return an String
     */
    public String encrypt(String plainText) {
        char[] inputChars = plainText.toCharArray();
        String simpleShifted = "";

        for (int i = k; i < inputChars.length; i++) {
            int index = findByte(inputChars[i]) - k;
            if(index<0)
                index += 64;
            inputChars[i] = words[index];
        }
        for (char ic : inputChars) {
            simpleShifted += ic;
        }
        return simpleShifted;
    }
    /**the name of method is decrypt
     * used to decrypt the encoded String input by changing each
     * character's code and make that ready to become decoded
     * @param simple a string that should become decrypted
     * @return an String
     */
    public String decrypt(String simple) {
        char[] inputChars = simple.toCharArray();
        String encoded = "";

        for (int i = k; i < inputChars.length; i++) {
            int index = findByte(inputChars[i]) + k;
            if(index>63)
                index -= 64;
            inputChars[i] = words[index];
        }

        for (char ic : inputChars) {
            encoded += ic;
        }
        return encoded;
    }
}
