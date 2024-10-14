/**
 * @sarqq
 * Improved version of StringStats.java
 * 
 */

import java.util.Scanner;
import java.util.regex.*;

public class StringStatsV2 {
    public static final String NO = "n";
    public static final String YES = "y";

    public static String scrubCharacters(String s){
        String scrubbed = "";
        
        if(s != null){
            scrubbed = s.replaceAll("[^a-zA-Z0-9\s]", "");
        }

        return scrubbed;
    }

    public static boolean analyzer(String s){
        if(s == null || s.isEmpty()) return false;

        int shortest, shortest2, longest, longest2, totalLength;
        shortest = shortest2 = longest = longest2 = totalLength = 0;
        
        String[] words = s.split("\\s+");
        shortest = shortest2 = words[0].length();

        //find out lengths
        for(String word : words){
            if(word.length()>=longest){
                longest2 = longest;
                longest = word.length();
            }
            else if(word.length()<=shortest){
                shortest2 = shortest;
                shortest = word.length();
            }

            totalLength += word.length();
        }
        
        double mean = totalLength / (double) words.length;
                
        //print results
        System.out.printf("\"%s\"\n", s);
        System.out.printf("- The number of parts is %d.\n", words.length);
        System.out.printf("- The sum of part lengths is %d.\n", totalLength);
        System.out.printf("- The average length of parts is %.2f.\n", mean);
        System.out.printf("- The length of the shortest part is %d.\n", shortest);
        System.out.printf("- The length of the second shortest part is %d.\n", shortest2);
        System.out.printf("- The length of the second longest part is %d.\n", longest2);
        System.out.printf("- The length of the longest part is %d.\n", longest);
        
        return true;
    }

    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);

        System.out.println("Hello! I calculate some string statistics.");
        while(true){
            System.out.println("Please, enter a string: ");
            String input = reader.nextLine().trim();
            
            if(!analyzer(scrubCharacters(input))){
                System.out.println("Error!");
            }

            //ask if user wants to continue
            while(true){
                System.out.println("Continue (y/n)?");
                String command = reader.nextLine().trim();
                
                //quit
                if(command.equals(NO)){
                    System.out.println("See you soon.");
                    return;
                }
                
                //continue
                if(command.equals(YES)) break;
                //invalid input
                else System.out.println("Error!");
            }
        }
    }
}