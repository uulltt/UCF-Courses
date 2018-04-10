#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define mil (int)pow(10,6) //shortcut for 1 million in integer value
#define bil (int)pow(10,9) //shortcut for 1 billion in integer value

/*Aaron Varkonyi
COP-3502H
Risk 1 million
February 28, 2016*/

void EnterInt(int * i, int min, int max); //a specialized function to enter an integer between two set values
void EnterArray(int * array, int SIZE); //the function to enter into the array
void Sort(int * array, int low, int high);
void Merge(int * array, int low, int mid, int high); //our two functions to sort the arrays
int MinMax(int * player, int * opponent, int SIZE); //the final function used to minmax the values on the board

int main()
{
    int cases; //the amount of puzzles we will be going through
    EnterInt(&cases, 1, 100); //user enters in the amount of cases to go through, being between 1 and 100
    int currentcase; //the current case that the puzzle is going through
    for (currentcase = 0; currentcase < cases; currentcase++) //a for loop to go through each of the test cases
    {
        int armies = 0;
        EnterInt(&armies, 1, mil); //user enters in amount of moves in board, between 1 and 1mil
        int * attackvalue = (int*)calloc(armies, sizeof(int)); //the attacking armies are created
        int * defendvalue = (int*)calloc(armies, sizeof(int)); //the defending armies are created
        EnterArray(attackvalue, armies); //user enters in attacking and defending values
        EnterArray(defendvalue, armies);
        Sort(attackvalue, 0, armies-1); //attacking and defending values are sorted
        Sort(defendvalue, 0, armies-1);
        printf("%i\n", MinMax(defendvalue, attackvalue, armies)); //defending values are minmaxed and printed out
        free(attackvalue); //the armies are freed and can go home to their families
        free(defendvalue);
    }
    return 0;
}

void EnterInt(int * i, int min, int max)
{
    while (*i < min || *i > max) //while the given integer is less than the minimum value or greater than the maximum value
    {
        scanf("%i", &(*i)); //user enters in integer
    }
}

void EnterArray(int * array, int SIZE)
{
    int i;
    for (i = 0; i < SIZE; i++) //going from zero to size
    {
        EnterInt(&*(array + i), 1, bil); //enter in each value in the given array, with that value between 1 and 1 billion
    }
}

void Sort(int * array, int low, int high) //we are using a merge sort for our sorting algorithm
{
    int mid;
    if (low < high)//while low and high do not cross over
    {
        mid = (low+high)/2; //mid is the space between low and high
        Sort(array, low, mid); //we sort the left part of the array
        Sort(array, mid+1, high); //we sort the right part of the array
        Merge(array, low, mid+1, high); //and then merge them together each time
    }
}

void Merge(int * array, int low, int mid, int high)
{
    int length = (high - low)/2; //length here is similar to mid, but instead is high minus low divided by 2
    int * left = (int*)calloc(length+1, sizeof(int)); //we create two dynamic arrays known as left and right, which the array that needs to be sorted will be split into
    int * right = (int*)calloc(length+1, sizeof(int)); //each of them is length + 1, to account for if the array happens to be odd and one is bigger than the other
    int i = 0, lenl = 0, lenr = -1; //our various ints are set up. lenl and lenr are the actual variables used to determine the lengths of the left and right arrays
    for (i = low; i < mid; i++) //going from low to mid
    {
        left[i-low] = array[i]; //we add the values on the left part of the main array to the left array
        lenl++; //the length of the left array is increased
    }
    for (i = mid; i <= high; i++) //going from mid to high
    {
        right[i-mid] = array[i]; //we do the same for the right array
        lenr++; //the length of the right array is increased
    }
    i = 0; //i is reset to 0
    int countl = 0, countr = 0; //these new ints are used to add the left and right values back into the array
    while ((countl < lenl) || (countr <= lenr))
    {
        if ((left[countl] < right[countr] && countl < lenl) || countr > lenr) //if the value in the left array is less than that in the right array
        {
            array[i+low] = left[countl]; //add it to the array
            countl++;//increase countl
            i++; //increase i
        }
        else //otherwise
        {
            array[i+low] = right[countr]; //add a value from the right side of the array
            countr++; //and increase that value
            i++;
        }
    }
    free(left); //free both the left and right arrays
    free(right);
}

int MinMax(int * player, int * opponent, int SIZE) //now that our arrays are sorted, there's one more step we need to do to minmax the values
{
    int i, ii, temp, wins = 0;
    for(i = SIZE - 1; i >= 0; i--) //going through the array in reverse order
    {
        if (player[i] < opponent[i]) //if the value of the player is less than that of the opponent
        {
            temp = player[0]; //store the first value of the array
            for(ii = 1; ii <= i; ii++)
            {
                player[ii - 1] = player[ii]; //move everything else down one step
            }
            player[i] = temp; //put the first value at the end
        }
        wins += player[i] > opponent[i]; //add the amount of times player beats the opponent
    }
    return wins;//return that value
}





