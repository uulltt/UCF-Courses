/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pairings;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Aaron
 */
public class Pairings {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        int f = sc.nextInt(); //the amount of female rabbits we will use
        int m = sc.nextInt(); //the amount of male rabbits we will use
        if (m > 0 && m <= 100000 && f > 0 && f <= 100000) {
            int fi[] = new int[f]; //the array that contains the height of the female rabbits
            int mi[] = new int[m]; //the array that contains the height of the male rabbits
            int max = 0;
            for (int i = 0; i < f; fi[i] = sc.nextInt(), f = fi[i] < 0 || fi[i] > 100000 ? fi[i] : f, max = Math.max(fi[i], max), i++);//adding all the female rabbit heights to the array and setting the max to the tallest female rabbit
            for (int i = 0; i < m; mi[i] = sc.nextInt(), m = mi[i] < 0 || mi[i] > 100000 ? mi[i] : m, max = Math.max(mi[i], max), i++);//adding all the male rabbit heights to the array and seeing if there is a taller male rabbit
            max += 1;//adding 1 to max for the cumulative frequency array
            if (f >= 1 && f <= 100000 && m >= 1 && m <= 100000) {
                int[] prefix = new int[max];
                for (int i = 0; i < m; prefix[mi[i] - 1] += 1, i++);//creating a frequency array of the heights of the male rabbits
                for (int i = max - 2; i >= 0; prefix[i] += prefix[i + 1], i--);//making said array cumulative but in reverse
                for (int i = 0; i < f; System.out.println(prefix[fi[i]]), i++); //seeing the prefix sum for each of the female rabbit
            } else {
                System.out.println("This data is not valid.");
            }
        } else {
            System.out.println("This data is not valid.");
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
//System.out.println();
//System.out.println(totalTime + " milliseconds");
    }
}
