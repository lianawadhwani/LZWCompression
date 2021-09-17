
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class LZW {
    private static List<Integer> nums = new ArrayList<Integer> (); 

    public static List<Integer> compress(String uncompressed) {
        // Build the dictionary.
        int dictSize = 256;
        Map<String,Integer> dictionary = new HashMap<String,Integer>();
        for (int i = 0; i < 256; i++)//load ascii table 
            dictionary.put("" + (char)i, i);
 
        String current = "";
        List<Integer> encodedValues = new ArrayList<Integer>();
        for (char next : uncompressed.toCharArray()) {
            String combined = current + next;
            if (dictionary.containsKey(combined))//checks if combined is already in dictionary
                current = combined;
            else {
                encodedValues.add(dictionary.get(current));
                // Add combined letters to the dictionary
                dictionary.put(combined, dictSize++);
                current = "" + next;
            }
        }
 
        // Output the ascii code for each character(s) in encodedValues.
        if (!current.equals(""))
            encodedValues.add(dictionary.get(current));
            nums=encodedValues; 
            System.out.println(nums); 
        return encodedValues;
    }
    
    public static String decompress(List<Integer>numbers){
        int counter=256; // keeps track of how big the hash map is so i know where to put the next string values
        HashMap <Integer, String> map = new HashMap <Integer,String> (); // intialized and delcres hash map 
        int current=0; 
        int next=0; 
        String word="";// this will eventually be the whole word 
        String combined ="";// string that contains current and next as one string 
        String wordC=""; // the string version of the number of the current value
        String wordN=""; // the string version of the number of the next value
        for (int i=0; i<256; i++){
            map.put(i, ""+(char)i); // created dictionary assigning ascii values to the first 255 characters
        }
        for (int i=0; i<numbers.size(); i++){
            if (numbers.size()>1){
                current=numbers.get(i); 
                next=numbers.get(i+1); 
                if (next<map.size()){
                    wordC=map.get(current); // converts the numbers into a string 
                    wordN=map.get(next); // same as above but for next 
                    combined=wordC+wordN.substring(0,1); // comhines current and next
                    map.put(counter,combined); // puts the new combined letters into the dictionary 
                    counter++;// increments counter to fit the new size of map
                }
                else{
                    int tracker= next; // keeps track of the numbers that next originally was 
                    next=counter-1; 
                    wordN=map.get(next); // sets WordN to the last dictionary entry 
                    String letter=wordN.substring(0,1); // gets the first letter of the next word 
                    wordN=wordN+letter; // sets wordN to wordN to the first letter of WordN
                    map.put(tracker,wordN); // adds to Hashmap
                    wordC=map.get(current); 
                    combined=wordC+wordN.substring(0,1); 
                    map.put(counter,combined); 
                    counter++; 
                }
               
                word=word+map.get(numbers.get(i)); // this line creates the original word 

            }
        }

        return (word); 
    }
    
    public static void main(String[] args) throws IOException {
        String filename = "lzw-file1.txt";
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(filename));
            System.out.println("Output:");
            while((line = br.readLine()) != null){//the bufferedreader reads each line, stores it, and passes it onto the "compress" method that performs LZW compression.
                List<Integer> compressed = compress(line); 
                System.out.println(compressed);
                System.out.println(decompress(compressed)); 

            }
        }finally {
            br.close();
        }
        
    }
} 