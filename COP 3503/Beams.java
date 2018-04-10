/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beams;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Aaron
 */
public class Beams {

    public static void printarray(int[] wow) {
        for (int j = 0; j < wow.length; j++) {
            System.out.print(wow[j]);
        }
        System.out.println();
    }
    
    public static boolean isBlue(char c){
        return c == 'b' || c == 'B';
    }
    
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String bridges = sc.nextLine();
       
            StringBuilder sb = new StringBuilder();
           sb.append(bridges);
            
            int len = sb.length();
            if (len < 1 || len > 100000){
                System.out.println("that input is not valid");
                System.exit(0);
            }
            int paths = 0;
            for (int i = 0; i < len-1; i++) { //the iterator skips over any numbers that are taken, and automatically marks a cell as taken when it goes over it
              if (sb.charAt(i) == sb.charAt(i+1)){ //if two adjacent characters are the same
                  paths++; //we found a path
                  sb.delete(i, i+2); //delete the two adjacent characters from the string
                  len -= 2; //we just took away two characters, so we subtract 2 from the length;
                  i -= i == 0 ? 1 : 2; //set i two steps back to check the one behind it (as it will iterate forward), unless it is 0, where it will only go one step back
              }
            } //after running this for loop, we will be left with a string that alternates between blue and red at every step
            int left = (sb.length()-1)/2;//the amount of paths we have left is the length of that string minus one divided by 2. See proof for more info
            paths += left; //we add the amount of paths left to the total paths
            System.out.println(paths);
        }
    //}

}
