#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define pegcount 2

/*Aaron Varkonyi
COP-3502 H
Arup Guha
February 14, 2016*/

void EnterInt(int * i, int min, int max); //a specialized function to enter an integer between two set values
void EnterIntMin(int * i, int min); //a specialized function to enter an integer greater than a value
void EnterIntMax(int * i, int max); //a specialized function to enter an integer less than a value
void ChangeColor(int ** board, int ** pegs, int * solution, int slot);
void CheckSolution(int ** board, int ** pegs, int * solution);
void Free2DArray(int **Array, int SIZE);

int total;
int slots, colors, moves; //these four variables are global due to being needed in nearly every function relating to this program

int main()
{
    int cases; //the amount of puzzles we will be going through
    EnterInt(&cases, 1, 100); //user enters in the amount of cases to go through, being between 1 and 100
    int currentcase; //the current case that the puzzle is going through
    for (currentcase = 0; currentcase < cases; currentcase++) //a for loop to go through each of the test cases
    {
        int tempslots = 0, tempcolors = 0, tempmoves = 0; //since our slots/colors/moves variables are global, we need to make temp variables to assign them values
        EnterIntMin(&tempslots, 1); //user enters in amount of slots, with at least 1
        EnterIntMin(&tempcolors, 2); //user enters in amount of colors in the puzzle, with at least 2
        EnterInt(&tempmoves, 1, 20); //user enters in amount of moves in board, between 1 and 20
        slots = tempslots; //the temp variables have their value assigned to the global variables
        colors = tempcolors;
        moves = tempmoves;
        int** board = (int**)malloc(moves * slots * sizeof(int*)); //we initialize our arrays for the board and pegs
        int** pegs = (int**)malloc(moves * pegcount * sizeof(int*));
        int i, ii; //the two ints for our for loops
        for(i = 0; i < moves; i++) //going through each of the moves of the board
        {
            *(board+i) = (int*)malloc(slots * sizeof(int)); //initialize the individual subarrays of the board and pegs arrays
            *(pegs+i) = (int*)malloc(pegcount * sizeof(int));
            for(ii = 0; ii < slots; ii++) //going through each of the slots
            {

                scanf("%i", (*(board+i)+ii)); //scans standard input for the colors to put into the board
                *(*(board+i)+ii) %= colors; //modulo the board by the amount of colors so it does not choose a color that is not allowed
            }
            for (ii = 0; ii < pegcount; ii++) //because there are only black pegs and white pegs for each of the board, we go through this
            {
                scanf("%i", (*(pegs+i)+ii)); //entering in the amount of black pegs and white pegs
                *(*(pegs+i)+ii) %= slots; //modulo the pegs by the amount of slots in the board
            }
        }
        total = pow(colors, slots); //total is equal to colors to the power of slots
        int * solution = (int*)calloc(slots,sizeof(int)); //the solution array we will be using to test each solution
        ChangeColor(board, pegs, solution, 0); //our permutation function to go through each possible color combination, with the solution checker inside it
        printf("%i\n", total); //print the new total
        free(solution); //freeing all the arrays
        Free2DArray(board, moves);
        Free2DArray(pegs, moves);
    }
    return 0;
}

void ChangeColor(int ** board, int ** pegs, int * solution, int slot) //this is a permutation loop that loops through all possible solutions
{

    if (slot == slots) //at the end of each loop
    {
        CheckSolution(board, pegs, solution); //check each permutation with the solution checker
    }
    else //otherwise
    {
        int color;
        for(color = 0; color < colors; color++) //going through all the colors
        {
            solution[slot] = color; //set each slot of the solution to the color
            ChangeColor(board, pegs, solution, slot + 1); //recursive function to keep the permutation loop going
        }
    }

}

void CheckSolution(int ** board, int ** pegs, int * solution) //the other main part of our program, used for checking the solutions in each possible combination
{
   int move, wpegs, bpegs, point1, point2; //various ints for use in our problem
    short valid = 0; //these are valid points, to see how valid the solution is to the rest of the problem
    for(move = 0; move < moves; move++) //going through each of the moves of the board
    {
        int * checked1 = (int*)calloc(slots, sizeof(int)); //create two new int arrays to see if a part of the board has bee checked or not
        int * checked2 = (int*)calloc(slots, sizeof(int));
        bpegs = 0; //the variables we will be using to count the amount of black pegs and white pegs
        wpegs = 0;
        for(point1 = 0; point1 < slots; point1++) //going through each point on the solution board
        {
            for(point2 = 0; point2 < slots; point2++) //going through each point on the current move of the regular board as well
            {
                if (*(solution + point1) == *(*(board + move)+point2)) //if the two points are equal to each other
                {
                    if(*(checked2 + point2) == 0) { //and that place on the main board hasn't been checked yet
                        if(point1 == point2) { //if the points are also equal
                            bpegs++; //add a black peg
                            *(checked1 + point1) = 1; //set where point1 is to already have been checked
                            *(checked2 + point2) = 1; //along with the same for point2
                        }
                    }
                }
            }
        }

        for(point1 = 0; point1 < slots; point1++) //we do this again for the white pegs, and we can't make this its own function due to the inner two if statements being different.
        {
            for(point2 = 0; point2 < slots; point2++) //we also need point1 and point2 to restart at the beginning again
            {
                if (*(solution + point1) == *(*(board + move)+point2))
                {
                    if(*(checked2 + point2) == 0 && *(checked1 + point1) == 0) { //checking to see if neither point1 or point2 has been checked
                        if(point1 != point2) { //if they are not equal to each other
                            wpegs++; //add a white peg
                            *(checked1 + point1) = 1; //dont check this place again
                            *(checked2 + point2) = 1;
                        }
                    }
                }
            }
        }
        if (bpegs == *(*(pegs + move)) && wpegs == *(*(pegs + move)+ 1)) //if the amount of black pegs counted is equal to the amount of black pegs on the board, along with the same for the white pegs
        {
            valid++; //add a valid point
        }
        free(checked1); //free the two checked arrays for the next loop
        free(checked2);
    }
    if ((int)valid != moves) //if the tested solution isn't valid with the whole puzzle
    {
        total -= 1; //take one away from the total amount of solutions
    }
}


void EnterInt(int * i, int min, int max)
{
    while (*i < min || *i > max) //while the given integer is less than the minimum value or greater than the maximum value
    {
        scanf("%i", &(*i)); //user enters in integer
    }
}

void EnterIntMin(int * i, int min)
{
    *i = min - 1;
    while (*i < min) //while the given integer is less than the minimum value or greater than the maximum value
    {
        scanf("%i", &(*i)); //user enters in integer
    }
}

void EnterIntMax(int * i, int max)
{
    while (*i > max) //while the given integer is less than the minimum value or greater than the maximum value
    {
        scanf("%i", &(*i)); //user enters in integer
    }
}

void Free2DArray(int **Array, int SIZE)
{
    int i;
    for (i = 0; i < SIZE; i++)
    {
        free(*(Array+i));
    }
    free(Array);
}
