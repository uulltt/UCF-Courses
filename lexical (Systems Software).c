/*
 * COP3402 - Spring 2018
 * System Software Assignment 2
 * Submitted by: Aaron Varkonyi, William White
 */

// includes
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>

#define SYMBOL_COUNT 17
#define SYM_COUNT 14
#define WORD_COUNT 13
#define INVALID_COUNT 8

typedef enum {
nulsym = 1, identsym, numbersym, plussym, minussym,
multsym,  slashsym, oddsym, eqsym, neqsym, lessym, leqsym,
gtrsym, geqsym, lparentsym, rparentsym, commasym, semicolonsym,
periodsym, becomessym, beginsym, endsym, ifsym, thensym,
whilesym, dosym, callsym, constsym, varsym, procsym, writesym, readsym, elsesym
} token_type;

typedef struct {
	char* name;
	int token;
} lex;

lex* code;

//special symbols
const char* const reswords[WORD_COUNT] = {"begin", "end", "if", "then", "while", "do", "call", "const", "var", "procedure", "write", "read", "else"};
const char spsym[SYM_COUNT] = {'+', '-', '*', '/', '%', '(', ')', '=', ',', '.', '<', '>', ';', ':'};
const char invalids[INVALID_COUNT] = {'$', '#', '&', '!', '_', '~', '`', '?'};
const char* const spsymstr[SYMBOL_COUNT] = {"+", "-", "*", "/", "%", "=",
"<>", "<", "<=", ">", ">=", "(", ")",",",";", ".",":="};
FILE* getFile(char* name, char* perm) {
	FILE* file = fopen(name, perm);
	if (file == NULL) {
		printf("Error: Could not open file! ( %s )\n", name);
		exit(1);
	}
	return file;
}


void printSource(char* filename) {
    printf("Source Program:%s\n", filename);
    FILE* inputFile = getFile(filename, "r");

    int count;
    char c = fgetc(inputFile);
    while(c != EOF) {
        printf("%c", c);
        c = fgetc(inputFile);
    }
    fclose(inputFile);
    //printf("wow");
    //printf("\n");
}

char* substring(char* original, int start, int len){
	char* temp = (char*)malloc(len);
	int c = 0;
	while(c < len){
		temp[c] = *(original+c+start);
		c++;
	}
	temp[c] = '\0';
	return temp;
}

int getToken(lex line) {
	int i;
	//certain words
	for(i = 0; i < WORD_COUNT; i++) {
		if (strcmp(line.name, reswords[i]) == 0) {
			return i + 21;

		}
	}
	//special symbols
	for(i = 0; i < SYMBOL_COUNT; i++) {
		if (strcmp(line.name, spsymstr[i]) == 0) {
			return i + 4;

		}
	}
	
	if (strcmp(line.name, "odd") == 0){
		return 8;
	}
	int c;
	int digitcount = 0;
	for(c = 0; line.name[c] != '\0'; c++){
		if (isdigit(line.name[c])){
			digitcount++;
		}
	}
	if (digitcount == c){
		return 3;
	}
	for(c = 0; line.name[c] != '\0'; c++){
	for(i = 0; i < SYM_COUNT; i++) {
		if (line.name[c] == spsym[i]) {
			return 1;

		}
	}
}
	return 2;
}

void printTableList(int lexTableIndex){
	
	printf("\nLexeme Table:\n");
	printf("lexeme\t\ttoken type\n");
	int i;
	for (i = 0; i < lexTableIndex; i++) {
		if (strlen(code[i].name) >= 8)
			printf("%s\t%d\n",code[i].name, code[i].token);
		else
			printf("%s\t\t%d\n",code[i].name, code[i].token);
	}

	printf("\nLexeme List:\n");
	for (i = 0; i < lexTableIndex; i++) {
		printf("%d ",code[i].token);
		if (code[i].token == 2 || code[i].token == 3) {
			printf("%s ", code[i].name);
		}
		free(code[i].name);
	}
	
}

void main(int argc, char** argv) {
	if (argc < 1) {
		printf("Error: Too few arguments provided!");
		exit(1);
	}
printSource(argv[1]);
	// fetch/create files
	code = (lex*)malloc(5000 * sizeof(lex));
	FILE* inputFile = getFile(argv[1], "r");
	// read input
	int lexTableIndex;
	int ignore = 0;
	for (lexTableIndex = 0; !feof(inputFile); lexTableIndex++) {
		//printf(" %d ", lexTableIndex);
		if (lexTableIndex > 0){
			if (strlen(code[lexTableIndex-1].name) == 0){
				lexTableIndex--;
			}
		}
		code[lexTableIndex].name = (char*)calloc(500,sizeof(char));
		
		if (!ignore){
		
		if (fscanf(inputFile, "%s", code[lexTableIndex].name)){
			if (lexTableIndex > 0){
			if (strlen(code[lexTableIndex].name) == 0){
				break;
			}
			}
			 
		}
		
		//ignoring comments
		if (strlen(code[lexTableIndex].name) > 1 && code[lexTableIndex].name[0] == '/' && code[lexTableIndex].name[1] == '/'){
			ignore = 1;
			lexTableIndex--;
			
			continue;
		}
 		else if (strlen(code[lexTableIndex].name) > 1 && code[lexTableIndex].name[0] == '/' && code[lexTableIndex].name[1] == '*'){
			ignore = 2;
			lexTableIndex--;
			
			continue;
		}
		} else {
 if (ignore == 1){
			char a;
			do{
				a = fgetc(inputFile);
				//printf("Previous String: %s %d\n", code[lexTableIndex-1].name, lexTableIndex);
			}while(a != '\n' && a != '\r' && a != EOF);
	} else {
         char a, b;
			do{
   b = a;
				a = fgetc(inputFile);
				//printf("Previous String: %s %d\n", code[lexTableIndex-1].name, lexTableIndex);
			}while(a != '/' && b != '*' && a != EOF);   
        }
			ignore = 0;
		
			continue;
			
		}
		int c;
		//checking for invalid symbols
		for (c = 0; *((code[lexTableIndex].name) + c) != '\0'; c++) {
			int i;
			for(i = 0; i < INVALID_COUNT; i++){
				if (*((code[lexTableIndex].name)+c) == invalids[i]){
					printf("Error: Invalid Symbol.");
					exit(1);
				}
			}
		}
		c = 0;
		int s;
		for(s = 0; s < SYMBOL_COUNT; s++) {

			if (strcmp(code[lexTableIndex].name, spsymstr[s]) == 0) {
				
				break;
			}
		}
		
		if (s == SYMBOL_COUNT) {
			char* oldname = code[lexTableIndex].name;
			int len;
			do{
				
		char firstchar = code[lexTableIndex].name[0];
		if (isalnum(firstchar)){
		for (c = 1; *((code[lexTableIndex].name) + c) != '\0'; c++) {
			
			int i;
			for (i = 0; i < SYM_COUNT; i++) {
				if (*((code[lexTableIndex].name)+c) == spsym[i]) {	
					oldname = code[lexTableIndex].name;
					len = (strlen(oldname) - c);
					code[lexTableIndex].name = substring(oldname, 0, c);
					code[lexTableIndex].token = getToken(code[lexTableIndex]);
					int token = getToken(code[lexTableIndex]);
					if ((token == 2 && (strlen(code[lexTableIndex].name) > 11 || (isdigit(firstchar)))) || (token == 3 && strlen(code[lexTableIndex].name) > 5)){
						if (token == 3)
							printf("Error: Number too large.");
						if (token == 2)
							if (isdigit(firstchar))
								printf("Error: Invalid identifier.");
							else
								printf("Error: Identifier too long.");
						exit(1);
					}
					lexTableIndex++;
					code[lexTableIndex].name = (char*)malloc(len+1);
					strncpy(code[lexTableIndex].name, substring(oldname, c, len), len+1);
					oldname = code[lexTableIndex].name;
					break;
				}
			 }
			 if (i < SYM_COUNT)
				break;
		  }
				}
				
		firstchar = code[lexTableIndex].name[0];
		if (!isalnum(firstchar)){
			//printf("len %d ", strlen(code[lexTableIndex].name));
		  //if (*((code[lexTableIndex].name)+c) != '\0'){
			int i;
			for(i = 0; i < SYMBOL_COUNT; i++){
				int c = strlen(spsymstr[i]);
				if (strcmp(substring(code[lexTableIndex].name, 0, c), spsymstr[i]) == 0 && c < strlen(code[lexTableIndex].name)){
					if ((i != 7 && i != 9) || code[lexTableIndex].name[c] != '='){ //checking to make sure there's not a = after the substring we're looking at if we are looking at < or >
					oldname = code[lexTableIndex].name;
					int len = (strlen(oldname) - c);
					
					code[lexTableIndex].name = substring(oldname, 0, c);
					code[lexTableIndex].token = getToken(code[lexTableIndex]);
					
					lexTableIndex++;
					code[lexTableIndex].name = (char*)malloc(len+1);
					strncpy(code[lexTableIndex].name, substring(oldname, c, len), len+1);
					oldname = code[lexTableIndex].name;
					break;
					}
			 }
		  //}
			 
		  }
		  if (i == SYMBOL_COUNT){
			  break;
		  }
		}
		}while(getToken(code[lexTableIndex]) == 1);
		}
		//if (lexTableIndex > 0)
		//printf("Previous String: %s %d\n", code[lexTableIndex-1].name, lexTableIndex);
		code[lexTableIndex].token = getToken(code[lexTableIndex]);
		//printf("%d", getToken(code[lexTableIndex]));
		int token = getToken(code[lexTableIndex]);
					if ((token == 2 && (strlen(code[lexTableIndex].name) > 11 || isdigit(code[lexTableIndex].name[0]))) || (token == 3 && strlen(code[lexTableIndex].name) > 5)){
						if (token == 3)
							printf("Error: Number too large.");
						if (token == 2)
							if (isdigit(code[lexTableIndex].name[0]))
								printf("Error: Invalid identifier.");
							else
								printf("Error: Identifier too long.");
						exit(1);
					}
		//printf("%d\n", strlen(code[lexTableIndex].name));
	}
	
	printf("\n");
	printTableList(lexTableIndex);
	free(code);
	fclose(inputFile);
}