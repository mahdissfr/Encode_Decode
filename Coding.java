package com.aut;

import java.util.ArrayList;
import java.util.List;
/**
 * this class is used to change the usual order of bytes and vice versa
 * @author mahdis safari
 * @since 11.1.1396
 */
public class Coding {
    /**
     * the length of the input file
     */
    private int length;
    /**
     * the array which helps in recognizing each word's code
     */
    private char[] words = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    /**
     * name of the method is getParts
     * it seperate the string to strings containing some equal number of chars
     * number of chars in each string is partition size and return these strings in an arrayList
     *@param string should become seprated to a more little string
     * @param partitionSize number of chars in each string is partition size
     * @return an arrayList of strings
     */
    private static List<String> getParts(String string, int partitionSize) {
        List<String> parts = new ArrayList<>();
        int len = string.length();
        for (int i = 0; i < len; i += partitionSize) {
            parts.add(string.substring(i, Math.min(len, i + partitionSize)));
        }
        return parts;
    }

    /**
     * in this constructor we just pass an integer
     * which shows the length of the input file.
     *
     * @param length the length of the input file
     */
    public Coding(int length) {
        this.length = length;
    }
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
    /**
     * the name of method is byteToBinaryString
     * it converts a byte into a string of 8 bits
     * @param b the byte that the method convert into binary
     * @return String a string of 8 bits
     */
    public String byteToBinaryString(byte b) {
        StringBuilder binaryStringBuilder = new StringBuilder();
        for (int i = 0; i < 8; i++)
            binaryStringBuilder.append(((0x80 >>> i) & b) == 0 ? '0' : '1');
        return binaryStringBuilder.toString();
    }

    /**
     * name of the method is encode
     * it convert each byte into binary and then attach all 8 bits together and make a string
     * then it seperate the string into strings that each string is consist of 6 bits
     * and if the number of bits mod 6 is not zero it adds enough zero before last string
     * it convert new strings of bits to byte and replace each byte with appropriate character
     * after all these operation a string of characters will be returned
     *
     * @param inputBytes an array of bytes that keep bytes which were read from file
     * @return an String
     */
    public String encode(byte[] inputBytes) {
        List<String> sixBitList;
        String[] binaryOfEachByte = new String[length];
        String allBits = "";
        String help;
        for (int j = 0; j < length; j++) {
            binaryOfEachByte[j] = byteToBinaryString(inputBytes[j]);
            if (binaryOfEachByte[j].length() < 8) {
                help = "0";
                for (int i = 0; i < (7 - binaryOfEachByte[j].length() - 1); i++) {
                    help += "0";
                }
                String tmp = binaryOfEachByte[j];
                binaryOfEachByte[j] = help;
                binaryOfEachByte[j] += tmp;
            }
        }
        for (int k = 0; k < length; k++) {
            allBits += binaryOfEachByte[k];
        }
        sixBitList = getParts(allBits, 6);
        String codedBits = "";
        String[] sixBit = new String[sixBitList.size()];
        for (int i = 0; i < sixBitList.size() - 1; i++) {
            sixBit[i] = sixBitList.get(i);
        }
        if (sixBitList.get(sixBitList.size() - 1).length() < 6) {
            sixBit[sixBitList.size() - 1] = "0";
            for (int i = 0; i < (5 - sixBitList.get(sixBitList.size() - 1).length()); i++) {
                sixBit[sixBitList.size() - 1] += "0";
            }
            sixBit[sixBitList.size() - 1] += sixBitList.get(sixBitList.size() - 1);
        } else sixBit[sixBitList.size() - 1] = sixBitList.get(sixBitList.size() - 1);
        int[] intOfSixBits = new int[sixBitList.size()];
        for (int i = 0; i < sixBitList.size(); i++) {
            intOfSixBits[i] = Integer.parseInt(sixBit[i], 2);
        }

        for (int i = 0; i < sixBitList.size(); i++) {
            codedBits += words[intOfSixBits[i]];
        }
        return codedBits;
    }

    /**
     * name of the method is decode
     * first it replaceeach character with it's code and set each one in an element of array of bytes
     * then it convert each byte into binary and then attach all 6 bits together and make
     * a string which it's length mod 8 is zero
     * then it seperate the string into strings that each string is consist of 8 bits
     * if the number of bits mod 6 is not zero it removes the extra zero before last
     * string consist of 6 bits
     * it convert new strings of bits to byte and replace each byte with appropriate character
     * @param input a string that the methode should convert to bytes writeable for fileOutputStream
     * @return an array of bytes to be written in a file
     */
    public byte[] decode(String input) {
        char[] eachChar = input.toCharArray();
        int size = input.length();
        byte[] bytesOfChars = new byte[size];
        String[] binaryOfEachByte = new String[size];
        for (int i = 0; i < size; i++) {
            bytesOfChars[i] = (byte) findByte(eachChar[i]);
        }
        for (int j = 0; j < size; j++) {
            binaryOfEachByte[j] = byteToBinaryString(bytesOfChars[j]);
        }
        if (length % 6 != 0) {
            String[] lastElementsBits = binaryOfEachByte[size - 1].split("");
            binaryOfEachByte[size - 1] = "";
            for (int i = 6 - (length % 6); i < 6; i++) {
                binaryOfEachByte[size - 1] += lastElementsBits[i];
            }
        }
        String allBits = "";
        for (String str : binaryOfEachByte) {
            allBits += str;
        }
        List<String> eigthBitList = getParts(allBits, 8);
        byte[] decodingBytes = new byte[eigthBitList.size()];
        for (int i = 0; i < eigthBitList.size(); i++) {
            decodingBytes[i] = Byte.parseByte(eigthBitList.get(i), 2);
        }
        return decodingBytes;
    }

}