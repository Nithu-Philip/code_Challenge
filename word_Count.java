// Java Program to Find the 
//Count of the words
// Most Repeated top 10 Words in a Text File and Last sentence of the top word.

// Importing File classes
import java.io.File;
import java.io.FileNotFoundException;
// Importing Map and HashMap class from
// java.util package
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.*;

// Importing Scanner class to
// take input from the user
import java.util.Scanner;

// Class
// To find maximum occurrences
public class word_Count {

	// Method 1 - getWords()
	// Reading out words from the file and
	// mapping key value pair corresponding to
	// each different word
	static int getWords(String fileName,Map<String, Integer> words)
		throws FileNotFoundException
	{
		// Creating a Scanner class object
		Scanner file = new Scanner(new File(fileName));
		int totalWordCount=0;
		// Condition check using hasNext() method which
		// holds true till there is word being read from the
		// file.
	// As the end of file content,condition violates
	    
		while (file.hasNext()) {
		    
		   
			// Reading word using next() method
			String word = file.next();
			 ++totalWordCount;
			// Frequency count variable
			Integer count = words.get(word);

			// If the same word is repeating
			if (count != null) {

				// Incrementing corresponding count by unity
				// every time it repeats
			// while reading from the file
				count++;
			}
			else

				// If word never occurred after occurring
				// once, set count as unity
				count = 1;
			words.put(word, count);
		}
		
        
		// Close the file and free up the resources
		file.close();
		return totalWordCount;    
	}
	// Method 2 - getLastSentence()
	// To get last sentence based on top key word.
    static String getLastSentence(String filePath, String searchQuery) throws FileNotFoundException
    {
        searchQuery = searchQuery.trim();
        String str="";
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                if (line.contains(searchQuery))
                {
                   
                   String[] split = line.trim().split("\\. "); 
                    for(String s : split){
                        if (s.contains(searchQuery))
                        {
                            str=s;
                        }
                      
                    }   
                   
                }
                else
                {
                }
            }
        }
        finally
        {
            try
            {
                if (scanner != null)
                    scanner.close();
            }
            catch (Exception e)
            {
                System.err.println("Exception while closing scanner " + e.toString());
            }
        }

        return str;
    }
	// Method 3 - getMaxOccurrence()
	// To get maximum occurred Word
	static int getMaxOccurance(Map<String, Integer> words)
	{
		// Initially set maximum count as unity
		int max = 1;

		// Iterating over above Map using for-each loop
		for (Entry<String, Integer> word :
			words.entrySet()) {

			// Condition check
			// Update current max value with the value
			// exceeding unity in Map while traversing
			if (word.getValue() > max) {
				max = word.getValue();
			}
		}

		// Return the maximum value from the Map
		return max;
	}

	// Method 4
	// Main driver method
	public static void main(String[] args)
		throws FileNotFoundException
	{
		// Creating an object of type Map
		// Declaring object of String and Integer types
		Map<String, Integer> words
			= new HashMap<String, Integer>();
			
        String filepath=args[0]; //"//uploads//test1.txt";
        
		// Retrieving the path as parameter to Method1()
		// above to get the file to be read
        int totalWordCount=getWords(filepath, words);
        String str;
        	// Variable holding the maximum
		// repeated word count in a file
		int max = getMaxOccurance(words);
		int min=max-10;//top 10
		//	System.out.println(max);
		
		//LinkedHashMap preserve the ordering of elements in which they are inserted
        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
        words.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
        .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

		// Traversing using for-each loop
		// Creating a set out of same elements
		// contained in a HashMap
		int x; //count of total words
		x=0;
		
		System.out.println("Top 10 repeated words (words and count) :");
		System.out.println("----------------------------");
		for (Entry<String, Integer> word :
			reverseSortedMap.entrySet()) {
		 		    ++x;
			// Comparing values using geValue() method
			if (word.getValue() >= min && x<=10)  {

				// Print and display word-count pair
				//System.out.println(word);
				 System.out.println(word.getKey()+"-"+word.getValue());
			}
			
			}	 
		System.out.println("----------------------------");
		System.out.println("Last sentence on the file contains the most repeated word : ");
		System.out.println("----------------------------");
		//Last sentence from the most repeated word.		
		for (Entry<String, Integer> sentence :
			reverseSortedMap.entrySet()) {
		   if (sentence.getValue() == max) {
     			str=getLastSentence(filepath, sentence.getKey().toString());
        	    System.out.println(str);
        	    break;
			}
		}
		
		System.out.println("----------------------------");
			
		System.out.println("Total word count :"+totalWordCount);
			
	}
}
