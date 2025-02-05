/**
 * Make a madlibs! A custom madlibs created from a short block of user-supplied text. 
 *
 * @author Anne Farrell
 * @version 05-03-2024
 */
import java.util.Scanner;
import java.util.HashSet;
import java.util.Random;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MadLibsMain
{
    public static void main(String[] args)
    {
        //A series of HashSets created to store a series of words w/o duplicates
        HashSet<String> regNouns = new HashSet<String>();
        HashSet<String> propNouns = new HashSet<String>();
        HashSet<String> ingVerbs = new HashSet<String>();
        HashSet<String> edVerbs = new HashSet<String>();
        
        String fileName = "";
        
        try
        {
            //get the list of nouns
            NounScanner nounScanner = new NounScanner(); 
            ArrayList<String> nounList = nounScanner.getNounList(); 
            
            //Get the file from the user
            Scanner in = new Scanner(System.in);
            System.out.println("~IT'S MADLIBS TIME~");
            System.out.println("Please enter your text file below (max 200 words)");
            fileName = in.next();
        
            File inputFile = new File(fileName);
            Scanner in1 = new Scanner(inputFile);
        
            //Scan the file to determine where the nouns, proper nouns, and verbs are
            while (in1.hasNext())
            {
                //grabbing our first word variable
                String word1 = in1.next();
                
                //if there's more text following it...
                if(in1.hasNext())
                {
                    //we grab our second variable
                    String word2 = in1.next();
                
                    //is the word on the nouns list? it's a reg noun.
                    if (nounList.contains(word1.toLowerCase()))
                    {
                        regNouns.add(word1.trim());
                    }
                
                    //is the word longer than 3 chars & ends in -ing or -ed? tis a verb
                    else if (word1.length() > 3)
                    {
                        if (word1.endsWith("ing")&& !nounList.contains(word1.toLowerCase()))
                        {
                            ingVerbs.add(word1.trim());
                        }
                        else if (word1.endsWith("ed"))
                        {
                            edVerbs.add(word1.trim());
                        }
                    }
            
                    //is the word capitalized and not following a "."? PROPER NOUN TIME.
                    else if (Character.isUpperCase(word2.charAt(0)) && !word1.contains("."))
                    {
                         propNouns.add(word2.trim());
                    }
                }
            }
            //close the original scanner
            in1.close();
            
            //convert our hashsets to array lists so we can work with them
            ArrayList<String> regNounsList = new ArrayList<>(regNouns);
            ArrayList<String> propNounsList = new ArrayList<>(propNouns);
            ArrayList<String> edVerbsList = new ArrayList<>(edVerbs);
            ArrayList<String> ingVerbsList = new ArrayList<>(ingVerbs);
            
            //combining array lists into one
            ArrayList<String> originalWords = new ArrayList<String>();
            originalWords.addAll(regNounsList);
            originalWords.addAll(propNounsList);
            originalWords.addAll(edVerbsList);
            originalWords.addAll(ingVerbsList);
            
            
            //Here I was printing out all the nouns/verbs the program found to make sure
            //they are being stored properly. They are!
            
            //System.out.println("found reg nouns:");
            //System.out.println(regNounsList);
            //System.out.println("found proper nouns:");
            //System.out.println(propNounsList);
            //System.out.println("found -ed verbs:");
            //System.out.println(edVerbsList);
            //System.out.println("found -ing verbs:");
            //System.out.println(ingVerbsList);
            
            //I'm storing the user's nouns/verbs into separate arrays to track that they're 
            //being properly stored. They also are!
            //ArrayList<String> userRegNouns = new ArrayList<String>();
            //ArrayList<String> userPropNouns = new ArrayList<String>();
            //ArrayList<String> userEdVerbs = new ArrayList<String>();
            //ArrayList<String> userIngVerbs = new ArrayList<String>();
            
            //tracking the user generated words in an array
            ArrayList<String> userWords = new ArrayList<String>();
            
            //We loop through all sets of words to get user picks.
            if (regNounsList.size() > 0)
            {
                for (int i = 0; i < regNounsList.size(); i++)
                {
                    //prompt for a new noun from the user and store in the main array
                    System.out.println("Please enter a singular noun: ");
                    String newRegNoun = in.next();
                    
                    //userRegNouns.add(newRegNoun); 
                    userWords.add(newRegNoun);
                }
            }
            
            if (propNounsList.size() > 0)
            {
                for (int i = 0; i < propNounsList.size(); i++)
                {   
                    //prompt for a new proper noun from the user and store in the main array
                    System.out.println("Please enter a proper noun (no spaces): ");
                    String newPropNoun = in.next();
                    //Note that there was something funky here. It kept either eating a proper 
                    //noun or skipping a line and jumping to "-ed verbs" so there's that xtra
                    //in.next() in there to hold its place. 
                    in.next();

                    //userPropNouns.add(newPropNoun);
                    userWords.add(newPropNoun);
                }
            }
            
            if (edVerbsList.size() > 0)
            {
                for (int i = 0; i < edVerbsList.size(); i++)
                {    
                    //prompt for a new -ed verb from the user and store in the main array
                    System.out.println("Please enter a verb ending in -ed: ");
                    String newEdVerb = in.next();
                    
                    //userEdVerbs.add(newEdVerb);
                    userWords.add(newEdVerb);
                }
            }

            if (ingVerbsList.size() > 0)
            {
                for (int i = 0; i < ingVerbsList.size(); i++)
                {
                    //prompt for a new -ing verb from the user and store in the main array
                    System.out.println("Please enter a verb ending in -ing: ");
                    String newIngVerb = in.next();
                    
                    //userIngVerbs.add(newIngVerb);
                    userWords.add(newIngVerb);
                }
            }
            //printing out my user generated words to make sure they were captured correctly.
            //They were! 
            //System.out.println("Created reg nouns:");
            //System.out.println(userRegNouns);
            //System.out.println("Created prop nouns:");
            //System.out.println(userPropNouns);
            //System.out.println("Created -ed verbs:");
            //System.out.println(userEdVerbs);
            //System.out.println("Created -ing verbs:");
            //System.out.println(userIngVerbs);
            
            //new scanner and printwriter
            Scanner in2 = new Scanner(inputFile);
            PrintWriter out1 = new PrintWriter("madLibs.txt");
            
            //scan through the document, replace the words with user selects, and format
            int wordcount = 0;
            while (in2.hasNext())
            {
                String word = in2.next().trim();
                boolean replaced = false;
                
                for (int i = 0; i < originalWords.size(); i++)
                {
                    //is the word in the original array of words to replace?
                    if (word.equals(originalWords.get(i)))
                    {
                        //as long as our wordcount is < 12, stay on the same line
                        if (wordcount % 12 != 0)
                        {
                           out1.print(userWords.get(i) + " "); 
                        }
                        //otherwise move to a new line
                        else
                        {
                            out1.println(userWords.get(i)); 

                        }
                        //up the wordcount and break out of the loop
                        wordcount++;
                        replaced = true;
                        break;   
                    }
                }
                
                //if we aren't replacing the word, same thing but print the original & not the
                //user-generated replacement
                if (!replaced)
                {
                    if (wordcount % 12 != 0)
                    {
                        out1.print(word + " "); 
                    }
                    else
                    {
                        out1.println(word); 
                    }
                    wordcount++;
                }
            } 
        
            //closing the printwriter and scanner
            in2.close();
            out1.close();
            
            //pithy commentary
            System.out.println("Processing...");
            System.out.println("Complete! Have a very (ADJECTIVE) day.");
        }
        //catchin exceptionsss
        catch (FileNotFoundException e) 
        {
            System.err.println("Error saving modified text: " + e.getMessage());
        }
    }
}