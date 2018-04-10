package GUI;

import java.io.*;
import java.util.*;

public class Filing {
	
	public void checkFile(){
		File x = new File("C:\\test.txt");
		
		if (x.exists()){
			System.out.println(x.getName() + " exists!");
		}
		else {
			System.out.println("File not found.");
		}
	}
	
	public void createFile(String s){
		final Formatter y;
		
		try {
			y = new Formatter(s + ".txt");
			
			System.out.println(s + ".txt created");
			y.flush();
			y.close();
		}
		catch(){
			
		}
	}

}
