/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inversions;

import java.util.Scanner;

/**
 *
 * @author Aaron
 */
public class Inversions {

    public static long invcounter;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        invcounter = 0;
        if (n > 0 && n <= 100000) {
            int digits[] = new int[n];//our array for permutation
            boolean freq[] = new boolean[n];//our array to check if a number has been used already
            for (int i = 0; i < n; freq[i++] = false);//setting everything in the frequency array to be false
            for (int i = 0; i < n; i++) {
                int thevalue = sc.nextInt();
                if (thevalue >= 1 && thevalue <= n) {//if the number is between 1 and the max number
                    if (freq[thevalue - 1]) {//if it has already been used
                        System.out.println("That is not valid input!");
                        System.exit(0);
                    } else {
                        digits[i] = thevalue;//otherwise put it into the digits array
                        freq[thevalue - 1] = true;//set its frequency to true as it has already been used
                    }
                } else {
                    System.out.println("That is not valid input!");
                    System.exit(0);
                }
            }
            if (n >= 1 && n <= 100000) {
                digits = mergeSort(digits);//sort the array
                System.out.println((invcounter));//print out the amount of inversions
            }
        } else {
            System.out.println("That is not valid input!");
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
//System.out.println();
//System.out.println(totalTime + " milliseconds");
    }

    public static int[] mergeSort(int[] array) { //using a merge sort for this problem
        int[] left = new int[array.length >> 1];
        int[] right = new int[(array.length >> 1) + (array.length & 1)];

        for (int s = 0; s < left.length; left[s] = array[s++]);
        for (int s = 0; s < right.length; right[s] = array[s + left.length], s++);

        return merge(left, right);
    }

    public static int[] merge(int[] l, int[] r) { //this is basically just a regular merge sort function

        l = l.length > 1 ? mergeSort(l) : l;
        r = r.length > 1 ? mergeSort(r) : r;
        int li = 0, ri = 0;
        int[] result = new int[l.length + r.length];
        for (int counter = 0; counter < result.length;) {
            while (li < l.length && ri < r.length) {
                invcounter += l[li] <= r[ri] ? 0 : l.length - li; //this is the main reason why we cannot use Array.sort, as we need to keep counting how many inversions there are while sorting
                result[counter++] = l[li] < r[ri] ? l[li++] : r[ri++];
            }
            result[counter++] = li < l.length && ri == r.length ? l[li++]
                    : li == l.length && ri < r.length ? r[ri++] : 0;
        }
        return result;
    }

}
