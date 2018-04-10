#include <stdio.h>
#include <stdlib.h>
#include <string.h>
/*
Aaron Varkonyi
Boggle
March 26, 2016
*/
typedef struct trie //our main struct for the trie
{
    int isWord;
    struct trie *nextLetter[26];
} trie;

typedef struct vector2 //our struct for vector2s, for use in a used array
{
    short x,y;
} vector2;

void EnterInt(int * i, int min); //our function to enter in an integer
void CheckGrid(char grid[4][4], trie *dictionary); //functions that checks the current grid for words
void CheckDir(char grid[4][4], trie *dictionary, vector2 used[16], char*str, short index, short y, short x); //recursive function that further finds words and prints them
trie *createTrie(void); //various trie functions
trie *destroyTrie(trie *root);
trie *insertString(trie *root, char * word);
void ReadtoTrie(trie *t, FILE * filename, int maxStrings); //functions that reads strings from our dictionary to the trie

const int DX_SIZE = 8; //this is for our directions when we are looking through the grid
const int DX[] = {-1,0,0,1,-1,1,1,-1};
const int DY[] = {0,-1,1,0,1,-1,1,-1};

vector2 newV2(short x, short y)
{
    vector2 temp;
    temp.x = x;
    temp.y = y;
    return temp;
}


int main(void)
{
    FILE * words;
    int grids = 0;
    EnterInt(&grids, 1);
    int currentGrid;
    char * word = malloc(19 * sizeof(char)); //a temp string to store the word and put it into the trie
    char grid[4][4]; //the current grid of the puzzle
    short i, ii; //shorts used for entering stuff into the puzzle
    int w, max; //various ints used for reading words
    for(currentGrid = 0; currentGrid < grids; currentGrid++) //a for loop going through each grid
    {

        trie *dic = NULL; //we must reinstantiate the trie and file within the loop in order to not have words printed multiple times

        words = fopen("dictionary.txt", "r");

        fscanf(words, "%i", &max);
        for (w = 0; w <= max; w++)
        {
            fscanf(words, "%s", word); //the current string of filename is read into the temp string
            strtok(word, "\n"); //the trailing newline character at the end is taken out
            dic = insertString(dic, word); //the word is inserted into the trie
        }
        for(i = 0; i < 4; i++)
        {
            for(ii = 0; ii < 4;)
            {
                char c;
                scanf("%c", &c);
                if (c >= 'a' && c <= 'z')
                {
                    grid[i][ii] = c; //inputting letters into the grid
                    ii++;
                }
            }
        }
        printf("Words For Game #%i:\n", currentGrid+1);
        CheckGrid(grid, dic); //checks the current puzzle
        printf("\n");
        dic = destroyTrie(dic); //destroy the trie and close the file so we can restart them in the next loop
        fclose(words);
    }
    free(word);
    return 0;
}



void EnterInt(int * i, int min)
{
    while (*i < min) //while the given integer is less than the minimum value
    {
        scanf("%i", &(*i)); //user enters in integer
    }
}

trie *createTrie(void)
{
    int i;
    trie *n = malloc(sizeof(trie)); //memory is allocated to the new trie node
    n->isWord = 0;
    for (i = 0; i < 26; i++)
        n->nextLetter[i] = NULL; //all of the trie's children are set to null

    return n;
}


trie *destroyTrie(trie *root)
{
    int i;

    if (root == NULL)
        return root;

    // Destroy all the sub-tries before freeing this node.
    for (i = 0; i < 26; i++)
        destroyTrie(root->nextLetter[i]); //all of the tries children are destroyed
    free(root); //then the trie is destroyed

    return NULL;
}


trie *insertString(trie *root, char *str)
{
    int i, index, len = strlen(str);
    trie *temp = root;

    if (root == NULL) //if the root has nothing
        root = temp = createTrie(); //create a trie at the root


    for (i = 0; i < len; i++)
    {
        index = str[i] - 'a'; //set the index to whatever the current character of the string is

        if (temp->nextLetter[index] == NULL) //if the indexth child is null
            temp->nextLetter[index] = createTrie(); //create a trie there

        temp = temp->nextLetter[index]; //move down the chain to the next letter
    }

    temp->isWord++; //at the end of the loop, increment isword
    return root;
}


void CheckGrid(char grid[4][4], trie *dictionary)
{
    short x, y;
    char* tempword = (char*)malloc(20); //our temp string in order to print the word
    for(y = 0; y < 4; y++)
    {
        for(x = 0; x < 4; x++) //going through each cell in the array
        {
            vector2 used[16]; //we create a stack of cells that are already being used
            short i;
            for(i = 0; i < 16; i++)
            {
                used[i] = newV2(-1, -1); //we set each to a cell that cannot exist
            }
            CheckDir(grid, dictionary, used, tempword, 0, y, x); //we check in a given direction
        }
    }
    free(tempword); //free the temporary word
}

void CheckDir(char grid[4][4], trie *dictionary, vector2 used[16], char *str, short index, short y, short x)
{
    if (dictionary->isWord > 0)  //if the current isWord of the dictionary is greater than zero (the search reaches a word)
    {
        str[index] = '\0'; //set the last character of the temporary string to a termination
        printf("%s\n", str); //print out the string
        dictionary->isWord = 0; //set isword to zero so it is not printed twice; this will reset itself with each loop since the dictionary is deleted and readded each time
    }

    used[index] = newV2(x, y);
    short d,checkused;
    for(d = 0; d < DX_SIZE; d++) //going through each possible direcion
    {
        short go = 1; //our bool for checking whether to check a tile
        short nextX = x + DX[d]; //set nextX and nextY
        short nextY = y + DY[d];
        if (nextX >= 0 && nextY >= 0 && nextX < 4 && nextY < 4) //making sure the tile is in bounds
        {
            for(checkused = 0; checkused < index; checkused++)
            {
                if (used[checkused].x == nextX && used[checkused].y == nextY) //checking to make sure the currently checked cell isnt being used
                {
                    go = 0;
                }
            }
            if (go)
            {
                if (dictionary->nextLetter[grid[nextY][nextX] - 'a'] != NULL)  //if the current letter in the grid is not null in the dictionary
                {
                    str[index] = grid[nextY][nextX]; //the current cell of the string is set to that character of the grid
                    CheckDir(grid, dictionary->nextLetter[grid[nextY][nextX] - 'a'], used, str, index+1, nextY, nextX); //recursively call this loop, now starting from the next character in the string and using nextX and nextY and its X and Y
                }
            }

        }

    }
    for(d = 15; d >= index; d--) //clearing the stack up until the current point so that the checker can go in a new direction
    {
        used[d] = newV2(-1, -1);
    }
}
