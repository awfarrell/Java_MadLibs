/**
 * A pre-loaded list of nouns
 *
 * @author Anne Farrell
 * @version 05-02-2024
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class NounScanner 
{
    //an array list for the nouns to go into
    private ArrayList<String> nounList; 

    public NounScanner() 
    {
        //initialize the array list
        nounList = new ArrayList<>();
        
        //just TRY, will you!
        try 
        {
            //scan the text file. This file name will prob have to be adjusted
            Scanner in = new Scanner(new File("C:\\Users\\annew\\OneDrive\\Desktop\\A170_Farrell_MadLibs\\nouns.txt"));
            
            while (in.hasNextLine()) 
            {
                //format the noun and add it to the array list
                String noun = in.nextLine().trim().toLowerCase();
                nounList.add(noun);
            }
            
            //close the scanner
            in.close();
            
        } 
        //catching exceptionessss
        catch (FileNotFoundException e) 
        {
            System.err.println("Error reading nouns file: " + e.getMessage());
        }
    }
    
    /**
     * getNounList, a method to grab the list of nouns
     * @return the list of nouns, an Array List of type String
     */
    public ArrayList<String> getNounList()
    {
        return nounList;
    }
}
