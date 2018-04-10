//Using SDL and standard IO 
#include <SDL.h> 
#include "loader.h"


loader* l_loader = 0;
bool g_bRunning = false;


FILE _iob[] = { *stdin, *stdout, *stderr };

extern "C" FILE * __cdecl __iob_func(void)
{
	return _iob;
}

int main(int argc, char* args[]) { //The window we'll be rendering to 
	//srand(time(NULL));
	//std::cout << "game init attempt...\n";
	if (theLoader::Instance()->init("Chapter 1", 100, 100, 360, 180, 0,
		false))
	{
		//std::cout << "game init success!\n";
		while (theLoader::Instance()->running())
		{
			theLoader::Instance()->handleEvents();
			theLoader::Instance()->update();
			theLoader::Instance()->render();
			SDL_Delay(10);
		}
	}
	else
	{
		//std::cout << "game init failure - " << SDL_GetError() << "\n";
		return -1;
	}
	//std::cout << "game closing...\n";
	theLoader::Instance()->clean();
	return 0;
}