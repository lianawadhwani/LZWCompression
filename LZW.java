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
private static List<Integer> nums= new ArrayList <Integer> (); 
private static Map<Integer,String> dic = new HashMap<Integer, String> (); 
	
	public static List<Integer> compress(String uncompressed) {
        // Build the dictionary.
        int dictSize = 256;
        Map<String,Integer> dictionary = new HashMap<String,Integer>();
        for (int i = 0; i < 256; i++){//load ascii table 
            dictionary.put("" + (char)i, i);
            dic.put(i, ""+(char)i); 
        }
       
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
        return encodedValues;
    }

    public String decompress(){
        int current=0; 
        int next=0; 
        String word=""; 
        for (int i=0; i<nums.size(); i++){
            current=nums.get(i); 
            next=nums.get(i+1); 
            String c=dic.get(current); 
            String n=dic.get(next); 
            String+=c+n; 
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
            }
        }finally {
        	br.close();
        }
        
    }
}
