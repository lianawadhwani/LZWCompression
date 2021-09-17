import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections; 

public class Decompress{

	private HashMap <Integer, String> dictionary = new HashMap <Integer, String>(); 
	private String word; 

	public Decompress () throw IO Exception{
		word=""; 
		BufferReader reader=new BufferReader(new InputStreamReader(new FileInputStream("test.txt"))); // delcares and intialized buffer reader 
		String line = reader.readLine(); 
			while (line != null) { // reads the file line by line 
				word+=line; 
				line= reader.readLine();
			}
			System.out.print (word); 
	}
}