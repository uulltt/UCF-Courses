package mining;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Vark
 */
public class Mining {

    public static Integer maxScore, currentScore;
    public static short h, w;
    public static boolean[][] beenHere;
    public static int[][] scoreBoard;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        maxScore = 0;
        h = in.nextShort();
        w = in.nextShort();
        String[] map = new String[h];
        int[][] vals = new int[h][w]; //a value array for width and height
        short[][] startPos = new short[h][w]; //an array for our start positions
        beenHere = new boolean[h][w];
        scoreBoard = new int[h][w];
        int[] checkhere = new int[w]; //an array to tell where to check on the top row
        short checkindex = 0;
        boolean found = false;
        for (short y = 0; y < h; y++) {
            map[y] = in.next(); //we enter in each line of the map
            Arrays.fill(beenHere[y], false);
            for (short x = 0, valstart = 0; x < w; x++) {
                vals[y][x] = 0;
                if (map[y].charAt(x) == '#') { //if we found a rock
                    vals[y][x] = -1;
                    valstart = (short) (x + 1); //move the value starting position to the right of it
                    if (y == 0 && found) { //if we are on the top row and already found a value
                        checkindex++;//move the checkindex up
                    }
                } else {
                    found = true;
                    if (y == 0) {
                        checkhere[checkindex] = valstart;
                    }
                    startPos[y][x] = valstart; //we set the start position of this cell to where the current start position it
                    if (map[y].charAt(x) >= '0' && map[y].charAt(x) <= '9') { //if we found a gem
                        vals[y][valstart] += map[y].charAt(x) - '0';//add that gem's value to the starting position
                    }
                }
            }
        }
        for (int i = 0; i <= checkindex; i++) {//going through each of the starting positions
            currentScore = 0;
            Search(vals, checkhere[i], 0, 0, startPos); //checking at each of the starting positions
            maxScore = Math.max(currentScore, maxScore);//setting the max score to be the max of it and currentscore
        }
        
        System.out.println(maxScore);//print out the max value of the gems you can get
    }

    public static void Search(int[][] values, int x, int y, int score, short[][] start) {
        if (beenHere[y][x]) {
            score += scoreBoard[y][x];
            currentScore = Math.max(score, currentScore);
            return;
        }
        if (values[y][x] == -1) { //if we are at a rock
            return;//return
        }
        scoreBoard[y][x] = values[y][x];
        int m = 0;
        score += values[y][x];//add the score of the current cell
        boolean goAgain = true;
        if (y < h - 1) {//if we are not at the bottom row
            for (int i = start[y][x]; i < w; i++) {
                if (values[y][i] == -1) { //if we hit a rock next to us
                    break;//stop running this for loop; we reached the end
                }
                if (values[y + 1][i] != -1 && goAgain) { //if the value below us is not a rock
                    Search(values, start[y + 1][i], y + 1, score, start);//check there
                    scoreBoard[y][x] = Math.max(values[y][x] + scoreBoard[y + 1][start[y + 1][i]], scoreBoard[y][x]);
                    goAgain = false;//dont check the value next to it until we hit a rock
                }
                if (values[y + 1][i] == -1 && !goAgain) {//if the value below is is a rock
                    goAgain = true;//check the value next time there isn't a rock
                }
            }
        }
        beenHere[y][x] = true;
        currentScore = Math.max(score, currentScore);//setting the currentscore to be the max of score or currentscore
    }

}
