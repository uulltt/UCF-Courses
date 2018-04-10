#include <stdio.h>
#include <stdlib.h>
/*
Aaron Varkonyi
Maze
March 20, 2016
*/

void EnterInt(short * i, short min, short max); //function to enter in integer, this time a short b/c they aren't larger or smaller than 32000
int InBounds(short X, short Y, short width, short height);//to make sure an x and y coordinate are within a grid
int CheckPuzzle(char** grid, short width, short height); //our function to check the puzzle
const int DX_SIZE = 4; //this is for our directions when we are looking through the maze
const int DX[] = {-1,0,0,1};
const int DY[] = {0,-1,1,0};

typedef struct Vector2 //our struct for Vector2s
{
    short x;
    short y;
} Vector2;

typedef struct V2List //this is our queue struct
{
    Vector2* elements; //the vector2s in our queue
    int numElements;
    int front;
    int queuesize;
} V2List;

Vector2 newV2(short X, short Y) //our function to make a new Vector2
{
    Vector2 temp;
    temp.x = X;
    temp.y = Y;
    return temp;
}

void initElements(struct V2List* qPtr) //our function to initialize our Vector2 Queue
{
    qPtr->elements = (Vector2*)malloc(sizeof(Vector2)*1);
    qPtr->front = 0;
    qPtr->numElements = 0;
    qPtr->queuesize = 0;
}

void increaseArray(struct Vector2** data, int size) //we have to use a double pointer here so that the value is assigned to the pointer
{
    struct Vector2 *temp; //creating a temporary person to allocate stuff to
    temp = realloc(*data, size * sizeof(struct Vector2)); //reallocating some memory to temp
    if (temp) //if it was successful
        *data = temp; //set data to temp
    else //otherwise
    {
        fprintf(stderr, "%s\n", __func__); //print out an error message
        exit(EXIT_FAILURE); //exit the void
    }
}

void enqueue(struct V2List* qPtr, Vector2 newpoint) //our function to add a new element to our queue
{
    qPtr->elements[qPtr->numElements] = newpoint;
    qPtr->numElements += 1;
    qPtr->queuesize += 1;
}

void dequeue(struct V2List* qPtr) //Our function to remove the top element from the queue
{
    if (qPtr->front < qPtr->numElements)
        qPtr->front += 1;
    else
        qPtr->front = 0;
    qPtr->queuesize -= 1;
}

int empty(struct V2List* qPtr) //our function to check if the queue is empty
{
    return qPtr->numElements-qPtr->front == 0;
}


int main()
{
    short grids = 0;
    EnterInt(&grids, 1, 100);
    short currentGrid;
    for(currentGrid = 0; currentGrid < grids; currentGrid++) //a for loop going through each maze
    {
        short s = 1; //our boolean to check if there is already an S in our maze
        short height = 0;
        short width = 0;
        EnterInt(&height, 3, 300);
        EnterInt(&width, 3, 300);
        char** puzzle = malloc(height * width * sizeof(char*)); //a 2d character array maze is created
        short i, ii;
        for(i = 0; i < height; i++)
        {
            *(puzzle + i) = malloc(width * sizeof(char)); //each row of the puzzle has memory allocated
            for(ii = 0; ii < width;) //we only use the first two parts of the for loop here since we only want it to increase under certain conditions
            {
                char c;
                scanf("%c", &c); //a character is scanned
                if (((i == 0 || ii == 0 || i == height - 1 || ii == width - 1) && c == '~') ^ (c == 'X' || c == '-' || (s && c == 'S'))) //if the character is a tilde and on the border exclusive or it isnt and its an X, dash, or S and there isnt already an S
                {
                    *(*(puzzle+i)+ii) = c; //it is read into the maze
                    if (s && c == 'S') s = 0; //if the character is an S, s is set to 0
                    ii++; //ii increases
                }
            }
        }
        printf("%i\n", CheckPuzzle(puzzle, width, height)); //the maze is checked and the number of moves needed to complete it is printed
        for(i = 0; i < height; i++)
        {
            free(*(puzzle + i)); //each row of the puzzle is freed
        }
        free(puzzle); //the puzzle itself is finally freed
    }
    return 0;
}

void EnterInt(short * i, short min, short max)
{
    while (*i < min || *i > max) //while the given integer is less than the minimum value or greater than the maximum value
    {
        scanf("%hi", &(*i)); //user enters in integer
    }
}

int InBounds(short X, short Y, short width, short height)
{
    if(X >= 0 && Y >= 0 && X < width && Y < height)
        return 1;
    else
        return 0;
}

int CheckPuzzle(char** grid, short width, short height)
{
    short i, ii; //the shorts for going through the height and width of the array
    short ** used = (short**)malloc(width * height * sizeof(short*)); //we malloc a bool array to see if we already checked a space
    int total, loop, iter; //these are used to assign elements to Vector2s in the queue
    Vector2 point; //the origin points of the maze
    V2List points; //the queue of all the points to check
    for(i = 0; i < height; i++)
    {
        used[i] = (short*)calloc(width, sizeof(short)); //we calloc the rows of the bool array
        for(ii = 1; ii < width-1; ii++)
        {
            if (*(*(grid+i)+ii) == 'S') //we search for the origin point and assign the coordinates to point
            {
                point.x = ii;
                point.y = i;
            }
        }
    }
    initElements(&points); //initialize the queue
    enqueue(&points, point); //enqueue the first point
    total = 0;
    iter = 0;
    while(!empty(&points))
    {
        if (iter == 0)
            loop = points.queuesize; //we set the loop point to the size of the queue
        increaseArray(&(points.elements), ((points.numElements) + 4) * sizeof(Vector2)); //we multiply the array by 4, so we dont have to increase memory every time we add an element to the queue
        Vector2 cur = points.elements[points.front]; //we set our current main vector 2 to the front element
        for(i = 0; i < DX_SIZE; i++) //going through all our directions
        {
            short nextX = DX[i];
            short nextY = DY[i];
            if(InBounds(nextX + cur.x, nextY + cur.y, width, height)) //if where we are checking is in bounds
            {
                if (*(*(grid+nextY+cur.y)+nextX+cur.x) == '-' && !used[nextY + cur.y][nextX + cur.x]) //if where we check has a dash and hasnt already been checked
                {
                    enqueue(&points, newV2(cur.x + nextX, cur.y + nextY)); //we enqueue that point and set its element to total plus one
                    used[nextY + cur.y][nextX + cur.x] = 1;
                }
                if (*(*(grid+nextY+cur.y)+nextX+cur.x) == '~' && !used[nextY + cur.y][nextX + cur.x]) //when our search finds a tilde
                {
                    return total + 1; //we return the total plus one
                }
            }
        }
        iter++; //we iterate iter
        if (iter >= loop) //if we have reached the loop point
        {
            total += 1; //we add one to total
            iter = 0; //the iter is reset to zero, meaning we are going to reset loop as well
        }
        dequeue(&points); //we remove the first point from our queue in order to check the next point

    }
	for(i = 0; i < height; i++){
		free(used[i]);
	}
	free(used);
    return -1; //if we cannot find a solution, we return -1
}

