/*
 * @sarqq
 * Improved ASCIIArt
 */

import java.util.*;
import java.io.*;

public class ASCIIArtV2{
    public static final char[] CHARACTERS = new char[]{'#','@','&','$','%','x','*','o','|','!',';',':','\'',',','.',' '};
    public static final String PRINT = "print";
    public static final String STATS = "info";
    public static final String ROTATER = "rotater";
    public static final String ROTATEL = "rotatel";
    public static final String COLOUR = "recolour";
    public static final String RESET = "reset";
    public static final String QUIT = "quit";

    public static ArrayList<String> readFile(String filename){
        ArrayList<String> image = null;
        
        try{
            Scanner reader = new Scanner(new File(filename));
            image = new ArrayList<>();

            while(reader.hasNextLine()){
                String line = reader.nextLine();
                image.add(line);
            }

            reader.close();
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }

        return image;
    }

    public static void print(ArrayList<String> image){
        //print image if not null
        if(image != null){
            System.out.println();
            for(String line : image){
                System.out.println(line);
            }
        }
    }

    public static boolean stats(ArrayList<String> image){
        if(image == null) return false;

        //initialize hashmap for character occurrences
        HashMap<Character, Integer> occurrences = new HashMap<>();
        for(char c : CHARACTERS){
            if(!occurrences.containsKey(c)){
                occurrences.put(c, 0);
            }
        }

        for(String line : image){
            for(char c : line.toCharArray()){
                for(char key : occurrences.keySet()){
                    if(c == key){
                        occurrences.put(c, occurrences.get(c)+1);
                    }
                }
            }
        }

        //print stats
        System.out.printf("Image size: %d x %d\n", image.size(), image.get(0).length());
        occurrences.forEach((k, v) -> System.out.printf("%c: %d times\n", k, v));

        return true;
    }

    public static ArrayList<String> colour(ArrayList<String> image, String oldColour, String newColour){
        ArrayList<String> colouredImage = null;

        if(image != null){
            colouredImage = new ArrayList<>();

            for(String line : image){
                colouredImage.add(line.replace(oldColour, newColour));
            }
        }

        return colouredImage;
    }

    public static ArrayList<String> rotate(ArrayList<String> image, char direction){
        ArrayList<String> rotatedImage = null;
        
        //TODO: rotate, maybe use matrixes?
        //if(image != null && (direction == 'R'|| direction == 'L')){
            //new width is old height, new height is old width
            //int newHeight = image.get(0).length();
            //int newWidth = image.size();
            //char[][] rotatedChars = new char[newWidth][newHeight];
            //rotatedImage = new ArrayList<>();
            
            //for(int i = 0; i<rotatedChars.length; i++){
                //for(int j = 0; j<rotatedChars[0].length; j++){
                    //rotate right
                    //if(direction == 'R'){
                    //    rotatedChars[j][newHeight-1-i] = image.get(i).charAt(j);
                    //}
                    //rotate left
                    //if(direction == 'L'){
                    //    rotatedChars[newWidth-1-i][j] = image.get(i).charAt(j);
                    //}
                //}
                //rotatedImage.add(new String(rotatedChars[i]));
            //}
        //}
        
        return rotatedImage;
    }

    public static void UI(ArrayList<String> originalImage, ArrayList<String> editedImage){
        Scanner reader = new Scanner(System.in);
            
        while(true){
            System.out.println("\nPlease, enter a command: ");
            String[] command = reader.nextLine().split("\\s+");

            switch (command[0]){
                case PRINT:
                    print(editedImage);
                    break;
                case STATS:
                    stats(editedImage);
                    break;
                case ROTATER:
                    editedImage = rotate(editedImage, 'R');
                    break;
                case ROTATEL:
                    editedImage = rotate(editedImage, 'L');
                    break;
                case COLOUR:
                    String oldColour = command[1];
                    String newColour = command[2];
                    editedImage = colour(editedImage, oldColour, newColour);
                    break;
                case RESET:
                    editedImage = new ArrayList<String>(List.copyOf(originalImage));
                    break;
                case QUIT:
                    return;
                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }
    }

    public static void main(String[] args){
        System.out.println("-------------------");
        System.out.println("| A S C I I A r t |");
        System.out.println("-------------------");
        
        try{
            String filename = args[0];
            
            if(filename == null){
                throw new FileNotFoundException();
            }

            ArrayList<String> originalImage = readFile(filename);

            if(originalImage != null){
                ArrayList<String> editedImage = new ArrayList<String>(List.copyOf(originalImage));
        
                //run UI
                UI(originalImage, editedImage); 
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("Bye, see you soon.");
    }
}