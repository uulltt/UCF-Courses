

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author Aaron Varkonyi, CIS-3360, Spring 2017, Assignment 1
 */
public class hillcipher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File file1 = new File(args[0]);
        File file2 = new File(args[1]);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file1)); //looking at the key
            String key = br.readLine();
            char square = key.charAt(0);//looking at the first character
            int size = square - '0';//setting the size
            int matrix[][] = new int[size][size];//making a 2d square matrix of the size

            for (int i = 0; i < size; i++) {
                Scanner in = new Scanner(br.readLine()); //looking at each line of the key
                for (int ii = 0; ii < size; ii++) {
                    matrix[i][ii] = in.nextInt();//inputting the values of the line of the key into the matrix
                }
            }
            System.out.println("Key matrix:\n");
            for (int i = 0; i < size; i++) {
                for (int ii = 0; ii < size; ii++) {
                    System.out.print(matrix[i][ii] + " "); //printing out the matrix
                }
                System.out.println();
            }
            br.close();
            br = new BufferedReader(new FileReader(file2)); //looking at the text

            int value = 0;
            StringBuilder sb = new StringBuilder();
            // reads to the end of the stream 
            while ((value = br.read()) != -1) { //while we can still read characters
                // converts int to character
                char c = (char) value;
                if (c < 97 || c > 97 + 25) { //if the character is not a lowercase letter
                    if (c >= 65 && c <= 65 + 25) { //if it is an uppercase letter
                        c += 32; //turn it into a lowercase letter
                    } else { //otherwise
                        continue; //skip this letter
                    }
                }
                sb.append(c); //add this lowercase letter to the stringbuilder
            }
            String plaintext = sb.toString(); //turn this stringbuilder into a string
            while (plaintext.length() % size != 0) {
                plaintext = plaintext + "x"; //adding extra x's so that the plaintext length is a multiple of the size
            }
            int len = plaintext.length();
            System.out.println("\nPlaintext:\n");
            for (int i = 0; i < len; i += 80) {
                System.out.println(plaintext.substring(i, Math.min(i + 80, len))); //printing out the plaintext, 80 characters a line
            }
            StringBuilder ciphertext = new StringBuilder();

            for (int i = 0; i < len; i += size) {
                String textpart = plaintext.substring(i, i + size); //looking at a certain section of the plaintext
                for (int j = 0; j < size; j++) {
                    int sum = 0;
                    for (int k = 0; k < size; k++) {
                        sum += matrix[j][k] * (textpart.charAt(k) - 'a'); //follow the hill cipher
                    }
                    sum %= 26; //modulo sum by 26
                    sum += 'a'; //add the base value for the character
                    char c = (char) sum; //turn sum into a character
                    ciphertext.append(c); //add the character to the stringbuilder
                }
            }
            System.out.println("\nCiphertext:\n");
            for (int i = 0; i < len; i += 80) {
                System.out.println(ciphertext.substring(i, Math.min(i + 80, len))); //printing out the plaintext, 80 characters a line
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
