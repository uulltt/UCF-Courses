/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mars;

import java.awt.Point;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Vark
 */
public class Mars {

    /**
     * @param args the command line arguments
     */
    public static Integer minMoves;
    public static Double programs;
    public static short h, w;

    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        minMoves = Integer.MAX_VALUE;
        h = in.nextShort();
        w = in.nextShort();
        String[] map = new String[h];
        Point start = new Point(-1, -1);
        boolean foundR = false;
        for (short i = 0; i < h; i++) {
            map[i] = in.next();
            if (!foundR) {
                int startPos = map[i].indexOf('R'); //finding the start position;
                if (startPos != -1) {
                    start = new Point(startPos, i);
                    foundR = true;
                }
                
            }
        }
        ArrayList<Integer>[] nodes = (ArrayList<Integer>[]) new ArrayList[Short.MAX_VALUE * 2];
        for (int i = 0; i < Short.MAX_VALUE * 2; i++) {
            nodes[i] = new ArrayList<Integer>();
        }
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (x > 0 && map[y].charAt(x - 1) != '#') {
                    nodes[(y << 8) + (x)].add((y << 8) + (x - 1));
                }
                if (x < w - 1 && map[y].charAt(x + 1) != '#') {
                    nodes[(y << 8) + (x)].add((y << 8) + (x + 1));
                }
                if (y > 0 && map[y - 1].charAt(x) != '#') {
                    nodes[(y << 8) + (x)].add(((y - 1) << 8) + (x));
                }
                if (y < h - 1 && map[y + 1].charAt(x) != '#') {
                    nodes[(y << 8) + (x)].add(((y + 1) << 8) + (x));
                }
            }
        }
        boolean[] visited = new boolean[Short.MAX_VALUE * 2];
        Arrays.fill(visited, false);
        Queue<Integer> q = new ArrayDeque<Integer>();
        int[] dist = new int[Short.MAX_VALUE * 2];
        double[] pVal = new double[Short.MAX_VALUE * 2];
        Arrays.fill(pVal, 0);
        
        Arrays.fill(dist, 0);
        //System.out.println(start);
        int pos = (start.y << 8) + start.x;
        pVal[pos] = 1;
        q.offer(pos);
//programs = 1;
        while (!q.isEmpty()) {
            int current = q.poll();
            
            
            if (map[current >> 8].charAt(current & 255) == 'G') {
                if (dist[current] == minMoves) { //if our moves are the same as minmoves
                    programs++; //add a program
                } else if (dist[current] < minMoves) { //if it is less
                    programs = pVal[current];
                    minMoves = dist[current]; //set the new minmoves
                }
            } else {
                //int x = q.peek();
                while(!q.isEmpty() && current == q.peek()){
                   
                    current = q.poll();
                    
                }
                visited[current] = true;
                int len = nodes[current].size();
                for (int i = 0; i < len; i++) {
                    if (!visited[nodes[current].get(i)] || dist[current] + 1 <= dist[nodes[current].get(i)]) {
                       
                        if (visited[nodes[current].get(i)] && dist[current] + 1 == dist[nodes[current].get(i)]){
                            pVal[nodes[current].get(i)] += pVal[current];
                            //p++;
                            continue;
                        }
                        
                        
                        q.offer(nodes[current].get(i));
                        dist[nodes[current].get(i)] = dist[current] + 1;
                        pVal[nodes[current].get(i)] += pVal[current];
                        visited[nodes[current].get(i)] = true;
                    }
                }
            }
            //System.out.println();
            //TimeUnit.MILLISECONDS.sleep(1000);
        }
        
        System.out.println(minMoves + " " + programs.toString().split("\\.")[0]);
    }
}
