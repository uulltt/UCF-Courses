/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package destroyer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Vark
 */
public class Destroyer {

    public static int totalWeight;
    public static String cycleString;
    public static int[][] weight;
    public static boolean[][] removed;
    public static long startTime;

    public static void main(String[] args) {
        startTime = System.currentTimeMillis();
        int verts;
        int edges;
        Scanner in = new Scanner(System.in);
        verts = in.nextInt();
        edges = in.nextInt();
        cycleString = "";

        ArrayList<Integer>[] nodes = (ArrayList<Integer>[]) new ArrayList[verts + 1];//adjacency list
        for (int i = 1; i <= verts; i++) {
            nodes[i] = new ArrayList<Integer>();
        }
        weight = new int[verts + 1][verts + 1]; //a weight matrix
        totalWeight = 0;
        for (int i = 0; i < edges; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int w = in.nextInt();
            nodes[x].add(y); //we add each edge to the adjacency list
            nodes[y].add(x);
            weight[x][y] = w; //we add each weight to the weight matrix
            weight[y][x] = w;
        }
        weight[1][1] = 10000000;
        boolean[] visited = new boolean[verts + 1];
        removed = new boolean[verts+1][verts+1]; //this is our checked matrix
        for(int i = 1; i <= verts; i++){
            Arrays.fill(removed[i], false);
        }
        Arrays.fill(visited, false);
        Stack<Integer>[] weights = (Stack<Integer>[]) new Stack[3];//a stack to hold all of our current weights, along with the positions of each of the two vertices of each edge
        for (int i = 0; i < 3; i++) {
            weights[i] = new Stack<Integer>();
        }
        for (int i = 1; i <= verts; i++) {
            DFS(weights, visited, nodes, i, i, (int) -1);
            visited[i] = true;
        }
        System.out.println(totalWeight);
    }

    public static void DFS(Stack<Integer>[] weights, boolean[] visited, ArrayList<Integer>[] nodes, int node, int origin, int last) {
        int len = nodes[node].size();
        visited[node] = true;//we have visited this node
        int i;
        for (i = 0; i < len; i++) {

            if (nodes[node].get(i) != last && !removed[node][nodes[node].get(i)]) {//if we're not looking at the previous one and we didn't remove this node from being checked
                if (nodes[node].get(i) == origin) { //if we are looking at the original node
                    weights[0].push((int) node);//we add the first vertex
                    weights[1].push((int) nodes[node].get(i)); //we add the second verted
                    weights[2].push(weight[node][nodes[node].get(i)]); //we add the weight
                    int minweight = 0;
                    int stacklen = weights[2].size();
                    for (int ii = 1; ii < stacklen; ii++) { //we go through each weight
                        if (weights[2].get(ii) == weights[2].get(minweight)) {
                            if (nodes[weights[0].get(ii)].size() > nodes[weights[0].get(minweight)].size() || nodes[weights[1].get(ii)].size() > nodes[weights[1].get(minweight)].size()) {
                                minweight = ii; //calculating to find the smallest weight in the cycle
                            }
                        }
                        if (weights[2].get(i) < weights[2].get(minweight)) {
                            minweight = ii; //same
                        }
                    }
                    totalWeight += weights[2].get(minweight); //we add the smallest weight in the cycle to the total weight
                    removed[weights[0].get(minweight)][weights[1].get(minweight)] = true; //we remove the edge from being checked
                    removed[weights[1].get(minweight)][weights[0].get(minweight)] = true;
                    weights[0].pop();//we pop this off of the stack
                    weights[1].pop();
                    weights[2].pop();
                    break;
                } else if (!visited[nodes[node].get(i)]) {//check to see if node was visited
                    weights[0].push((int) node); //we add both vertices onto the stacks
                    weights[1].push((int) nodes[node].get(i));
                    weights[2].push(weight[node][nodes[node].get(i)]); //we add the weight onto the stack
                    DFS(weights, visited, nodes, nodes[node].get(i), origin, node); //we check the next node
                    weights[0].pop();
                    weights[1].pop();//pop it off the stack
                    weights[2].pop();
                }
            }
        }
        if (i != len)
        visited[node] = false;//we have unvisited this node
    }

}
