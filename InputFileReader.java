package com.aut;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * this class is used to read and return bytes of a file
 * @author mahdis safari
 * @since 10.1.1396
 */
public class InputFileReader {
    /**
     * it is used to keep bytes in the file
     */
    private byte[] bytesOfInput;
    /**
     * it contains input file
     */
    private File inputFile;
    private FileInputStream fileInput;
    /**
     * it shows the length of input file
     */
    int length;
    /**
     * in this constructor we just pass a string
     * which shows the name or the address of the input file.
     * @param pathOrName the name or the address of the input file
     */
    public InputFileReader(String pathOrName) {
        inputFile = new File(pathOrName);
        fileInput = null;
        length = (int) inputFile.length();
        bytesOfInput = new byte[length];
    }

    /**
     * name of the method is inputOfBytes
     * if it can,it reads bytes from the file and return them in an array of bytes
     * @return an array of bytes existing in the input file
     */
    public byte[] inputBytes() {
        try {
            fileInput = new FileInputStream(inputFile);
            fileInput.read(bytesOfInput);
        } catch (FileNotFoundException e)
        {
            System.out.println("File peyda nashod.");
        } catch (IOException e)
        {
            System.out.println("Khata dar khandane file.");
        } finally
        {
            try {
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException e) {
                System.out.println("Khata dar bastane file.");
            }
        }
        return bytesOfInput;
    }
}