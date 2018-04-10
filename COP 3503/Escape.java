/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escape;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Vark
 */
public class Escape {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        long q;
        n = sc.nextInt();
        q = sc.nextLong();
        if (n >= 1 && n <= 100000 && q > 0) {
            int[] li = new int[n];
            for (int i = 0; i < n; i++) {
                li[i] = sc.nextInt(); //inputing all the values for the locations of the rabbits
                if (li[i] < 1 || li[i] > 1000000) {
                    System.out.println("That input is not valid");
                    System.exit(0);
                }

            }
            int max = 1000001;
            int prefix[] = new int[max];//our array for finding how many rabbits are at or before a point
            int dist[] = new int[max];//the amount of distance each rabbit will have to travel
            for (int i = 0; i < n; prefix[li[i] + 1] += 1, i++);//turn prefix into a frequency array
            for (int i = 1; i < max; prefix[i] += prefix[i - 1], dist[i] = prefix[i], dist[i] += dist[i - 1], i++);//make prefix cumulative, add each of prefix's values to dist, and then make dist cumulative

            for (long i = 0; i < q; i++) {//going through each of the simulations
                int a = sc.nextInt();//input a and b
                int b = sc.nextInt();
                if (a < 1 || b > 1000000 || b < a) {//if a is less than 1 or b is greater than 10^6 or b is less than a
                    System.out.println("That input is not valid");
                    System.exit(0);
                }
                System.out.println(dist[b] - dist[a] - ((b - a) * prefix[a]));//use the inclusion-exclusion principle to find out the amount of distance travelled by the rabbits
            }

        } else {
            System.out.println("That input is not valid");
        }
    }

}
