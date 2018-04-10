/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peaks;

import java.util.Scanner;

/**
 *
 * @author Aaron
 */
public class Peaks {

    public static long[] prefixTree, suffixTree;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n >= 1 && n <= 100000) {
            long numPeaks = 0;

            prefixTree = new long[n + 1]; //these are our prefixtree and our suffixtree, used for searching the array
            suffixTree = new long[n + 1];
            int si[] = new int[n];
            for (int i = 0; i < n; i++) {
                si[i] = sc.nextInt(); //we input all of the values into si
                if (si[i] <= 0 || si[i] > n) {
                    System.out.println("That Input is not valid");
                    System.exit(0);
                }
            }
            for (int i : si) {
                //we add values to the suffix tree
                addValue(i, 1, suffixTree);
            }

            for (int i : si) {
                //we move some values away from the suffix tree
                addValue(i, -1, suffixTree);
                //this multiplies the amount of numbers before the current integer that are less than i-1 by the amount of numbers after it that are between 1 and i-1
                numPeaks += prefixSum(i - 1, prefixTree) * rangeSum(1, i - 1, suffixTree);
                //we add those values to the prefix tree
                addValue(i, 1, prefixTree);

            }
            System.out.println(numPeaks);
        } else {
            System.out.println("That Input is not valid");
        }
    }

    public static long rangeSum(int low, int high, long[] tree) {//these are from the lectures
        return prefixSum(high, tree) - prefixSum(low - 1, tree);
    }

    public static long prefixSum(int index, long[] tree) {
        long prefixSumResult = 0;
        while (index > 0) {
            prefixSumResult += tree[index];
            index -= Integer.lowestOneBit(index);
        }
        return prefixSumResult;
    }

    public static void addValue(int index, long amount, long[] tree) {
        while (index < tree.length) {
            tree[index] += amount;
            index += Integer.lowestOneBit(index);
        }
    }

}
