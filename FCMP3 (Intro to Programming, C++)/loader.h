#ifndef __loader__
#define __loader__
#include <SDL.h>
#include <cstdlib>
#include <ctime>
#include <array>
#include "LTexture.h"

class loader
{
public:
	void init() { m_bRunning = true; }
	bool init(const char* title, int xpos, int ypos, int width, int height, int flags, bool fullscreen);
	void render();
	void update();
	void handleEvents();
	void clean();
	bool running() { return m_bRunning; }
	LTexture text;
	static loader* Instance() {
		if (s_pInstance == 0)
		{
			s_pInstance = new loader();
			return s_pInstance;
		}
		return s_pInstance;
	}
	SDL_Renderer* getRenderer() const { return m_pRenderer; }
private:
	static loader* s_pInstance;
	// create the typedef
	//TextureManager m_textureManager;
	SDL_Window* m_pWindow;
	SDL_Renderer* m_pRenderer;
	bool m_bRunning;
};
typedef loader theLoader;

#endif