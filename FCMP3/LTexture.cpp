#include "LTexture.h"

LTexture::LTexture() { //Initialize 
	mTexture = NULL; 
	mWidth = 0; 
	mHeight = 0; 
} 
LTexture::~LTexture() 
{ //Deallocate 
	free(); 
}

bool LTexture::loadFromRenderedText(std::string fileName, std::string textureText, SDL_Color textColor, SDL_Renderer* gRenderer) { //Get rid of preexisting texture 
	TTF_Font *gFont = TTF_OpenFont(fileName.c_str(), 12);
	free(); //Render text surface 
	SDL_Surface* textSurface = TTF_RenderText_Solid( gFont, textureText.c_str(), textColor ); 
	if( textSurface == NULL ) 
	{ 
		printf( "Unable to render text surface! SDL_ttf Error: %s\n", TTF_GetError() ); 
	} 
	else 
	{ //Create texture from surface pixels 
		mTexture = SDL_CreateTextureFromSurface(gRenderer, textSurface );
		if( mTexture == NULL ) 
		{ 
			printf( "Unable to create texture from rendered text! SDL Error: %s\n", SDL_GetError() ); 
		} 
		else 
		{ //Get image dimensions 
			mWidth = textSurface->w; 
			mHeight = textSurface->h; 
		} //Get rid of old surface 
		SDL_FreeSurface( textSurface ); 
	} //Return success 
		return mTexture != NULL; 
}

void LTexture::free() { //Free texture if it exists 
	if( mTexture != NULL ) 
	{ 
		SDL_DestroyTexture( mTexture ); 
		mTexture = NULL; 
		mWidth = 0; 
		mHeight = 0; 
	} 
}

void LTexture::render(int x, int y, SDL_Renderer *gRenderer) { //Set rendering space and render to screen
	SDL_Rect* clip = NULL; double angle = 0.0; SDL_Point* center = NULL; SDL_RendererFlip flip = SDL_FLIP_NONE;
	SDL_Rect renderQuad = { x, y, mWidth, mHeight }; 
	SDL_RenderCopy( gRenderer, mTexture, NULL, &renderQuad ); 
}