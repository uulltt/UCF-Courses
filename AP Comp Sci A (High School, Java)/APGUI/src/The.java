import java.util.ArrayList;
import java.util.List;


public class The {
	
	public static void printSomething(String s){
		int n = s.length();
		if (n < 1)
			return;
		String s1 = s.substring(1, n);
		printSomething(s1);
		System.out.println(s);
		printSomething(s1);
	}
	
	public static void main (String[] args){
		printSomething("ABCD");
		}
	}

