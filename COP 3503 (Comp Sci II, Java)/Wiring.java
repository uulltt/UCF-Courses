/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiring;

import java.util.Scanner;

/**
 *
 * @author Aaron
 */
public class Wiring {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        short wires = in.nextShort();
        int[] wireX1 = new int[wires];
        int[] wireY1 = new int[wires];
        int[] wireX2 = new int[wires];
        int[] wireY2 = new int[wires];
        short[] check = new short[wires];
        short[] checked = new short[wires];
        for (short i = 0; i < wires; i++) {
            wireX1[i] = in.nextInt();
            wireY1[i] = in.nextInt();
            wireX2[i] = in.nextInt();
            wireY2[i] = in.nextInt();
            check[i] = 0;
            if (i > 0) {
                for (int j = i - 1; j >= 0; j--) {
                    
                    if (wireY1[i] <= Math.max(wireY1[j], wireY2[j]) && wireY1[i] >= Math.min(wireY1[j], wireY2[j])
                            && wireY2[i] <= Math.max(wireY1[j], wireY2[j]) && wireY2[i] >= Math.min(wireY1[j], wireY2[j])) {
                        int maxX = Math.max(wireX1[j], wireX2[j]);
                        //System.out.println("AAAAAAAAAAAAAAAa" + j);
                        if (checked[j] != i){
                        check[j] |= 1;
                        checked[j] = i;
                        }
                        if ((wireX1[i] > maxX && wireX2[i] < maxX) ^ (wireX1[i] < maxX && wireX2[i] > maxX)) {
                            System.out.println("No");
                            System.exit(0);
                        }
                    }
                    //System.out.println(checked[j] + " " + i);
                    //System.out.println(Math.max(wireX1[j], wireX2[j]) + " " + Math.min(wireX1[j], wireX2[j]));
                    if (wireX1[i] <= Math.max(wireX1[j], wireX2[j]) && wireX1[i] >= Math.min(wireX1[j], wireX2[j])
                            && wireX2[i] <= Math.max(wireX1[j], wireX2[j]) && wireX2[i] >= Math.min(wireX1[j], wireX2[j])) {
                        int maxY = Math.max(wireY1[j], wireY2[j]);
                        if (checked[j] != i){
                        check[j] |= 2;
                        checked[j] = i;
                        }
                        if ((wireY1[i] > maxY && wireY2[i] < maxY) ^ (wireY1[i] < maxY && wireY2[i] > maxY)) {
                            System.out.println("No");
                            System.exit(0);
                        }
                        
                    }
                }
            }
        }
        for(int i = 0; i < wires; i++){
            if (check[i] == 3){
                System.out.println("No");
                System.exit(0);
            }
        }
        System.out.println("Yes");
    }

}
