package com.aut;
/**
 * {@inheritDoc}
 * the name of class id BaseCryptography
 * @author mahdis safari
 * @since 12.1.1396
 */
public abstract class BaseCryptography {
    /**
     * the array which helps in recognizing each word's code
     */
    char[] words = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    /**
     * the name of method is findByte
     * it looks for the input character's code
     * @param lost the character that we should look for it's code
     * @return an integer that is the character's code
     */
    public int findByte(char lost) {
        int i;
        for (i = 0; i < 63; i++) {
            if (words[i] == lost)
                break;
        }
        return i;
    }
    public abstract String encrypt(String plainText);
    public abstract String decrypt(String plainText);
}
