/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palindrome2;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Aaron
 */
public class Palindrome2 {

    /**
     * @param args the command line arguments
     */
    public static int dp[][];
    public static String middlechar;
    public static int freq[];

    public static int findLength(String str) {
        int len = str.length();
        dp = new int[len][len];

        for (int i = 0; i < len; Arrays.fill(dp[i], 0), i++);
        int maxval = 0;
        for (int i = len - 1; i >= 0; i--) {
            for (int ii = len - 1; ii >= 0; ii--) {
                if (str.charAt(i) == str.charAt((len - 1) - ii)) {
                    dp[i][ii] = i == len - 1 && ii == len - 1 ? 1 : i == len - 1 ? 1 : ii == len - 1 ? 1 + dp[i + 1][ii] : 1 + dp[i + 1][ii + 1];
                } else {
                    dp[i][ii] = i == len - 1 && ii == len - 1 ? 0 : i == len - 1 ? dp[i][ii + 1] : ii == len - 1 ? dp[i + 1][ii] : Math.max(dp[i + 1][ii], dp[i][ii + 1]);
                }
            }
        }

        for (int x = 0; x < len; x++) {
            //System.out.print(str.charAt(x));
            for (int xx = 0; xx < len; xx++) {
                //System.out.print(dp[x][xx]);
                maxval = Math.max(dp[x][xx], maxval);
            }
            //System.out.println();

        }
        return maxval;
    }

    public static void BestPalindrome(String str) {

        String sb;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (i < len - 1 && str.charAt(i) == str.charAt(i + 1)) {//if it finds two consecutive characters that are the same
                if (middlechar.length() == 2 && middlechar.compareTo(str.substring(i, i + 2)) > 0) {//if they come first alphabetically
                    middlechar = str.substring(i, i + 2);
                } else if (middlechar.length() == 1) { //or the string is only of length 1
                    middlechar = str.substring(i, i + 2);
                }
            } else if (middlechar.length() == 1 && middlechar.compareTo(str.substring(i, i + 1)) > 0) { //if the current single character comes first alphabetically
                middlechar = str.substring(i, i + 1);
            }
        }
    }

    public static void main(String[] args) {
        middlechar = "z";
        Scanner sc = new Scanner(System.in);
        String str = "";
        
        str = sc.next();
        int len = str.length();
        dp = new int[len][len];
        StringBuilder result = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        if (str.length() > 1000 || str.length() < 1) {
            System.out.println("That input is not valid.");
            System.exit(0);
        } else {
            int n = findLength(str);
            while (n > 2) {
                    if (n > 2) {
                            int minlook = 1; //this is the minimum amount we will look when comparing palindromes
                            for (int i = 0; i < len - 1; minlook++, i++) {
                                if (dp[0][i] > dp[0][i + 1]) {
                                    break;
                                }
                            }
                            int dists[] = new int[26]; //a distance array, finding the distance of the last character in the string from the start
                            for (int i = 0; i < 26; i++) {
                                dists[i] = str.indexOf(i + 97); //even though indexOf takes O(n) time, the outer loop is constant
                            }
                            
                            int mini = 0;
                            int temp = 0;
                            String thiscomesfirst = "";
                            for (int i = len-1; i >= len-(minlook+1); i--) { //we go through each possible palindrome end
                                if ((dists[str.charAt(i) - 97]+1) < i){ //to make sure we don't get strings that overlap due to there only being one index of a letter
                                    temp = findLength(str.substring(dists[str.charAt(i) - 97]+1, i)); //we find the length of the palindrome of that letter
                                    if (temp > mini) { //if its longer than the longest palindrome
                                        thiscomesfirst = str.substring(dists[str.charAt(i) - 97], i+1); //we set it to the string
                                        //System.out.println(thiscomesfirst);
                                        mini = temp; //and set it to the longest palindrome
                                        continue;
                                    } else if (temp < mini) {
                                        continue;
                                    } else { //if it is equal to the longest palindrome
                                        if (str.charAt(i) < thiscomesfirst.charAt(0)) { //if it comes before the first palindrome alphabetically
                                            thiscomesfirst = str.substring(dists[str.charAt(i) - 97], i+1);//set it to it
                                            //System.out.println(thiscomesfirst);
                                        } 
                                    }
                                }
                            }
                            str = thiscomesfirst; //setting the string to the compacted palindrome
                            result.append(str.charAt(0));//adding the palindrome character to our results
                            len = str.length(); //finding the new length
                            str = str.substring(1, len-1); //taking out the two border characters, which are the same
                            len-=2; //subtracting 2 from len
                            dp = new int[len][len]; //resetting dp so it fits
                            n = findLength(str); //finding the new n
                            
                    } else {
                        break;
                    }
                }
            BestPalindrome(str); //now that we have only one or two consecutive characters, we try to find the two (or one) that come first alphabetically and match
            int uptohere = result.length(); //the length of the stringbuilder that we need to copy to the other side
            result.append(middlechar); //adding the middle character
            StringBuilder rev = new StringBuilder();
            rev.append(result.substring(0, uptohere)); //we add the original palindrome characters
            rev.reverse();//reverse them
            result.append(rev);//then add them back
            System.out.println(result);

            System.exit(0);
            }

            
        }

    }

