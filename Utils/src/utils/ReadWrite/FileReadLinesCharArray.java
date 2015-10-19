/**
 * SPDXVersion: SPDX-1.1
 * Creator: Person: Nuno Brito (nuno.brito@triplecheck.de)
 * Creator: Organization: TripleCheck (contact@triplecheck.de)
 * Created: 2015-01-10T10:21:23Z
 * LicenseName: AGPL-3.0+
 * FileName: FileReadLinesSimple.java  
 * FileCopyrightText: <text> Copyright 2015 Nuno Brito, TripleCheck </text>
 * FileComment: <text> A model to read a large text file from disk, processing
 *  each line at maximum speed.</text> 
 */

package utils.ReadWrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nuno Brito, 10th of January 2015 in Darmstadt, Germany
 */
public class FileReadLinesCharArray {
  
    // internal variables
    private BufferedReaderReadLineChar reader = null;
    private InputStreamReader fileReader = null;
    private File fileInput = null;
    
    // mark the offset on disk
    private long 
            currentOffset = 0,
            currentLine = 0;
    
    
    public FileReadLinesCharArray(final File textFileTarget){
        try {
            // get the file where we want to store or read the variables from
            fileInput = textFileTarget;
           
            // doublecheck if the file was really created
            if(fileInput.exists() == false){
                System.out.println("FRLS52 - Critical error, unable to create"
                        + " variable file: " + fileInput.getAbsolutePath());
                return;
            }
           
            // initialise the objects from where to read text
            fileReader = new InputStreamReader(new FileInputStream(fileInput), "UTF-8");
            reader = new BufferedReaderReadLineChar(fileReader);
            
            
        } catch (Exception ex) {
            Logger.getLogger(FileReadLinesCharArray.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        }
    }
      
    /**
     * Provides the next line on our text file
     * @return The next line that was available, or null when nothing was read
     * @throws java.io.IOException
     */
    public char[] getNextLine() throws IOException{
           final char[] line = reader.readLine();
           
            // increase the line count
           if(line != null){
                currentLine++;
                currentOffset += line.length + 1;
           }
           return line;
    }

    public long getCurrentOffset() {
        return currentOffset;
    }

    public long getCurrentLine() {
        return currentLine;
    }
    
    /**
     * Close all the files that were open
     */
    public void close(){
        try {
            // close the streams
            reader.close();
            fileReader.close();
            
        } catch (IOException ex) {
            Logger.getLogger(FileReadLinesCharArray.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
    /**
     * Test if this implementation is working as expected.
     * @param args 
     * @throws java.io.IOException 
     */
    public static void main(String[] args) throws IOException{
        
        File testFile = new File("build.xml");
        
        FileReadLinesCharArray test = new FileReadLinesCharArray(testFile);
        char[] line = null;
        
        while((line = test.getNextLine())!= null){
            StringBuilder result = new StringBuilder();
            result.append(line);
            System.out.println(result);
        }
    }
    
    
}
