#include "loader.h"
#include "LTexture.h"
#include <iostream>

loader* loader::s_pInstance = 0;

bool loader::init(const char* title, int xpos, int ypos, int width,
	int height, int flags, bool fullscreen)
{
	flags = 0;
	if (fullscreen)
	{
		flags = SDL_WINDOW_FULLSCREEN;
	}
	// attempt to initialize SDL
	if (SDL_Init(SDL_INIT_EVERYTHING) == 0)
	{
		std::cout << "SDL init success\n";
		// init the window
		m_pWindow = SDL_CreateWindow(title, xpos, ypos, width, height, flags);
		if (m_pWindow != 0) // window init success
		{
			std::cout << "window creation success\n";
			m_pRenderer = SDL_CreateRenderer(m_pWindow, -1, 0);
			if (m_pRenderer != 0) // renderer init success
			{

				std::cout << "renderer creation success\n";
				SDL_SetRenderDrawColor(m_pRenderer, 0, 0, 0, 255);
				SDL_Color textColor = { 0, 255, 0 };
				if (!text.loadFromRenderedText("assets/font.ttf",
					"animate", textColor, m_pRenderer))
				{
					return false;
				}
				/*if (!TheTextureManager::Instance()->load("assets/rider.png",
					"animate2", m_pRenderer))
				{
					return false;
				}
				for (int i = 0; i < 5; i++) {
					m_gameObjects.push_back(new Enemy(new LoaderParams(RandomRange(0, 960), RandomRange(0, 540), 72, 144, "animate2")));
				}
				m_gameObjects.push_back(new Player(new LoaderParams(480, 270, 80, 80, "animate")));*/

			}
			else
			{
				std::cout << "renderer init fail\n";
				return false; // renderer init fail
			}
		}
		else
		{
			std::cout << "window init fail\n";
			return false; // window init fail
		}
	}
	else
	{
		std::cout << "SDL init fail\n";
		return false; // SDL init fail
	}
	std::cout << "init success\n";
	m_bRunning = true; // everything inited successfully, start the main loop
	return true;
}
void loader::render()
{
	SDL_RenderClear(m_pRenderer); // clear to the draw colour
	/*for (std::vector<GameObject*>::size_type i = 0; i !=
		m_gameObjects.size(); i++)
	{
		m_gameObjects[i]->draw();
	}*/
	text.render(0, 0, m_pRenderer);
	SDL_RenderPresent(m_pRenderer); // draw to the screen
}

void loader::clean()
{
	std::cout << "cleaning game\n";
	SDL_DestroyWindow(m_pWindow);
	SDL_DestroyRenderer(m_pRenderer);
	SDL_Quit();
}

void loader::handleEvents()
{
	SDL_Event event;
	if (SDL_PollEvent(&event))
	{
		switch (event.type)
		{
		case SDL_QUIT:
			m_bRunning = false;
			break;
		default:
			break;
		}
	}
}

void loader::update()
{
	SDL_Color textColor = { 0, 255, 0 };
	if (!text.loadFromRenderedText("Assets/font.ttf", "The quick brown fox jumps over the lazy dog", textColor, m_pRenderer)) {
		printf("Failed to render text texture!\n");
	}
}