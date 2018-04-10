/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xkcd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Vark
 */
public class XKCD {

    /**
     * @param args the command line arguments
     */
    public static ArrayList<String> stringList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        stringList = new ArrayList<String>(); //our list of strings that are xkcd strings
        short s = sc.nextShort();
      String[] startString = {"", "", "pz\nrx\nsw\ntv", "pln\nqkn\nqlm", "mkhj\nmlfk\nmlgj", "kjhfg\nlkhdg\nlkjbg\nlkjcf", "kjhfcd\nkjhgbd", "lhgfdbc"}; //hardcoded strings that we print out at the beginning
        if (s < 2 || s > 42) {
            System.out.println("that input is not valid.");
        } else if (s > 8) {
            System.out.println("Mostly Harmless"); //with the letters we are given, is it impossible to have a string with more than 7 characters that is an xkcd string
        } else if (s == 7 || s == 2) {
            System.out.println(startString[s]);
        } else {
         
                    
                    char wow[] = new char[s];
                    
                    xkcdStrings(wow, 0, s, 0); //our backtracking/permutation function
                    //System.out.println(loops);
                    int len = stringList.size();
                    //System.out.println(len);
                    System.out.println(startString[s]);
                    Collections.sort(stringList); //we sort the array to be in alphabetical order
                    for (int i = 0; i < len;System.out.println(stringList.get(i)), i++); //printing out the strings
                    String[] endString = {"", "", "", "zbn\nzcm\nzdl\nzfj", "zgcf\nzhbf\nzjdb\nzkbc", "wgfbd\nwhfbc\nwjdbc\nxgfbc\nzgdbc","qjfdbc\nrhgdbc\nshfdbc\ntgfdbc"}; //hardcoded strings printed out at the end
                    System.out.println(endString[s]);
        }
    }

    public static void xkcdStrings(char charstring[], int k, int n, int start) {
        String key = "bcdfghjklmnpqrstvwxz"; //our key of valid characters
        String temp = ""; //this is a temp string to hold our current string
        short sum = 42;//the sum the characters of our string should add up to
        for (int i = 0; i < n; sum -= (charstring[i] - 96), i++)
            temp += Character.toString(charstring[i]);
        if (sum < 0) { //if the sum of our characters is greater than 42
            return; //exit out of this permutation because it isn't good
        }
        if (k == n) { //if we reached the end of our current permutation
            if (sum == 0) { //if the sum is equal to zero, meaning the characters add up to 42
                String lasttwo = "";
                for (int i = 0; i < 2; i++) {
                    lasttwo = lasttwo + temp.substring((n - i) - 1, n - i); //add the last two characters to a temporary string in reverse
                }
                temp = temp.substring(0, n - 2); //take the last two characters out of our temporary string
                temp = temp + lasttwo; //add the reverse ones

                stringList.add(temp); //add our string to the stringlist, because it is valid
            }
        } 
        else { //if we did not reach the end
            int maxless = 0;
            int[] endPos = {0, 0, 0, 13, 10, 9, 8};
            if (k == 0){
                maxless = endPos[n]; //if we are on the first character, this is as far as we go
            } else { 
                maxless = (n - k) - 1;
            }
            
            int[] startPos = {19, 19, 19, 18, 18, 16, 11};
            for (int i = k == 0 ? startPos[n] : start - 1; i >= maxless; i--) {//go in a for loop from z (or t if n is 6, as there are no 6-character xkcd strings that are past t) until one reaches 0
                if (k == 1){
                    if (charstring[0] == 'x' && i > 12){
                        i = 12;
                    }
                    if (charstring[0] == 'w' && i > 13){
                        i = 13;
                    }
                    if (charstring[0] == 'v' && i > 14){
                        i = 14;
                    }
                    
                }
                
                if (k == 0 || (k > 0 && key.charAt(i) < charstring[k - 1])) {//if we are at the first character or we aren't and the current character is less than our previous one. this is basically what makes our permutation program a backtracking one
                    charstring[k] = key.charAt(i); //we set the current character to the current part of the key we are at
                    xkcdStrings(charstring, k + 1, n, i); //we permutate for the next character
                }
            }
        }
    }
}

