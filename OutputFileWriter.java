package com.aut;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * this class is used to write bytes in a file
 * @author mahdis safari
 * @since 10.1.1396
 */
public class OutputFileWriter {
    /**
     * a string which contains the name or the address of a file that we should write in
     */
    private String path;
    /**
     * in this constructor we just pass an string
     * which shows the name or the address of the output file.
     * @param path the name or the address of the output file
     */
    public OutputFileWriter(String path){
        this.path=path;
    }
    /**
     * name of the method is write
     * @param toWrite is an array of bytes which should become written in the output file
     */
    public void write(byte[] toWrite){
        try
        {
            FileOutputStream fos = new FileOutputStream(path);
            for(byte eachByte:toWrite)
            fos.write(eachByte);
            fos.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("FileNotFoundException : " + ex);
        }
        catch(IOException ioe)
        {
            System.out.println("IOException : " + ioe);
        }

    }
}
