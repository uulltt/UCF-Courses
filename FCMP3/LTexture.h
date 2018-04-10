#ifndef _LTexture_
#define _LTexture_
#include <SDL.h>
#include <iostream>
#include <SDL_ttf.h>
#include <SDL_image.h>

//Texture wrapper class 
class LTexture 
{ 
public: //Initializes variables 
	LTexture(); //Deallocates memory 
	~LTexture(); //Loads image at specified path 
	bool loadFromFile( std::string path ); //Creates image from font string 
	bool loadFromRenderedText(std::string fileName, std::string textureText, SDL_Color textColor, SDL_Renderer* gRenderer); //Deallocates texture 
	void free(); //Set color modulation 
	void setColor( Uint8 red, Uint8 green, Uint8 blue ); //Set blending 
	void setBlendMode( SDL_BlendMode blending ); //Set alpha modulation 
	void setAlpha( Uint8 alpha ); //Renders texture at given point 
	void render( int x, int y, SDL_Renderer *gRenderer); //Gets image dimensions 
	int getWidth(); 
	int getHeight(); 
private: //The actual hardware texture 
	SDL_Texture* mTexture; //Image dimensions 
	int mWidth; 
	int mHeight; 
};

#endif