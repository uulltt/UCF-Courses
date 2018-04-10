/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cycle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Vark
 */
public class Cycle {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
        int verts;
        short edges;
        Scanner in = new Scanner(System.in);
        verts = in.nextInt();
        edges = in.nextShort();
        ArrayList<Short>[] nodes = (ArrayList<Short>[])new ArrayList[verts+1]; //this is an adjacency list
        for(int i = 1; i <= verts; i++){
            nodes[i] = new ArrayList<Short>();
        }
        
        for(short i = 0; i < edges; i++){
            short x = in.nextShort();
            short y = in.nextShort();
            nodes[x].add(y); //adding each edge to our adjacency list
            nodes[y].add(x); //along with the edge in the other direction
        }
        boolean[] visited = new boolean[verts+1];
        Arrays.fill(visited, false);
        for(short i = 1; i <= verts; i++){
            DFS(visited, nodes, i, i, (short)-1); //running DFS on all of the nodes
            visited[i] = true;
        }
        
        System.out.println("No");
       
    }
    
    public static void DFS(boolean[] visited, ArrayList<Short>[] nodes, short node, short origin, short last){
        int len = nodes[node].size();
        visited[node] = true;//we have visited this node
        int i;
        for(i = 0; i < len; i++){ //going to each of the edges attached to the current node
            if (nodes[node].get(i) != last){ //if we're not looking at the previous node
                if (nodes[node].get(i) == origin){ //if it's the original node
                    System.out.println("Yes"); //we found a cycle
                    System.exit(0);
                } else {
                    if(!visited[nodes[node].get(i)]) //if we haven't visited this node
                    DFS(visited, nodes, nodes[node].get(i), origin, node); //run this again for that node
                }
            }
        }
        if (i != len)
        visited[node] = false; //we have unvisited this node
    }
    
}
