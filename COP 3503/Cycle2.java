/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cycle2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Vark
 */
public class Cycle2 {

    /**
     * @param args the command line arguments
     */
    public static int shortest;
    public static String cycleString;
    public static long startTime;
    
    public static void main(String[] args) {
        startTime = System.currentTimeMillis();
        int verts;
        short edges;
        Scanner in = new Scanner(System.in);
        verts = in.nextInt();
        edges = in.nextShort();
        cycleString = "";
        shortest = Integer.MAX_VALUE;
        ArrayList<Short>[] nodes = (ArrayList<Short>[]) new ArrayList[verts + 1];//adjacency list
        for (int i = 1; i <= verts; i++) {
            nodes[i] = new ArrayList<Short>();
        }
        
        for (short i = 0; i < edges; i++) {
            short x = in.nextShort();
            short y = in.nextShort();
            nodes[x].add(y); //adding each edge to our adjacency list
            nodes[y].add(x);
        }
        boolean[] visited = new boolean[verts + 1]; //an array for what nodes we have visited
        
        Arrays.fill(visited, false);
        for (short i = 1; i <= verts; i++) {
            DFS(visited, 1, "", nodes, i, i, (short) -1); //run a search for all of the vertices
            visited[i] = true;
        }
        if (shortest == Integer.MAX_VALUE) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
            System.out.println(shortest);
            System.out.println(cycleString);
        }
    }
    
    public static void DFS(boolean[] visited, int recs, String str, ArrayList<Short>[] nodes, short node, short origin, short last) {
        int len = nodes[node].size();
        int strlen = str.length();
        int cslen = cycleString.length();
        visited[node] = true;//we have visited this node
        int i;
        for (i = 0; i < len; i++) {
            if (nodes[node].get(i) != last) { //if we're not looking at the previous node
                if (nodes[node].get(i) == origin) { //if we found the original node
                    //we found a cycle
                    if (recs <= shortest) { //if the cycle is shorter than the current shortest cycle
                        str += ((Short) node).toString();
                        strlen++;
                        if (cycleString.equals("") || (strlen < cslen || (strlen == cslen && cycleString.compareTo(str) > 0))) {
                            //if the cycleString is blank, or the current length of our string is shorter, or their equal but our string comes first
                            cycleString = str;
                            
                        }
                        shortest = recs;
                        if (cycleString.equals("1 2 3")) { //there won't be a cycle shorter than this
                            System.out.println("Yes");
                            System.out.println(shortest);
                            System.out.println(cycleString);
                            System.exit(0);
                        }
                    }
                    
                    break;
                } else if (!visited[nodes[node].get(i)]) //if we havent visited the next node
                {
                    DFS(visited, recs + 1, str + ((Short) node).toString() + " ", nodes, nodes[node].get(i), origin, node);
                    
                }
            }
        }
        if (i != len) {
            visited[node] = false; //we have unvisited this node
        }
    }
    
}
