/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sticks;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Vark
 */
public class Sticks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        int notches[] = new int[n];
        for(int i = 0; i < n; notches[i] = sc.nextInt(), i++);
        int sum = 0;
        for(int i = n - 1; i >= 0; i--){ //going in reverse through each of the notches
            sum += notches[i] * (l-notches[i]); //adding the product of the notch and the length minus the notch
            l = notches[i]; //setting the length to the notch b/c we just made the stick shorter.
        }
        System.out.println(sum);
    }
    
}
