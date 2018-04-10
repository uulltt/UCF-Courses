/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palindrome;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Vark
 */
public class Palindrome {

    public static int[][] dp;

    public static int FindLength(String str) {
        int len = str.length();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.reverse();
        String str2 = sb.toString();
        dp = new int[len][len];

        for (int i = 0; i < len;Arrays.fill(dp[i], 0), i++);
        int maxval = 0;
        for(int i = len-1; i >= 0; i--){ //these are going through in reverse since i felt it better fit the bottom-up concept
            for(int ii = len-1; ii >= 0; ii--){
               if (str.charAt(i) == str2.charAt(ii)){
                   dp[i][ii] = i == len-1 && ii == len-1 ? 1 : i == len-1 ? 1 : ii == len-1 ? 1+dp[i+1][ii] : 1+dp[i+1][ii+1]; //our dp array being filled if we find a match
               } else {
                    dp[i][ii] = i == len-1 && ii == len-1 ? 0 : i == len-1 ? dp[i][ii+1] : ii == len-1 ? dp[i+1][ii] : Math.max(dp[i+1][ii], dp[i][ii+1]); //if we dont find a match
               } 
            }
        }
        
        //System.out.println(" " + str2);
        return dp[0][0];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        if (str.length() < 1 || str.length() > 1000){
            System.out.println("that input is not valid");
            System.exit(0);
        }
        System.out.println(FindLength(str));
    }
}
