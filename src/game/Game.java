package game;

public class Game implements Runnable
{
	/** The thread that game logic runs on */
	Thread gameThread;

	/** The status of the game, should only be false when opening or closing */
	boolean running = false;

	/** The JFrame descendant that displays the game */
	Display display;
	
	/** The current state the game is in */
	State currentState;
	
	GameState gameState;
	
	MenuState menuState;

	// Desired FPS, 30 by default
	public static int fps = 30;

	// Maximum time in nanoseconds between renders to meet desired FPS
	public static double timePerRender = 1000000000 / fps;

	// Maximum time in nanoseconds between updates to meet 30 updates per second
	public static double timePerUpdate = 1000000000 / 30;
	
	public Game()
	{
		menuState = new MenuState(this);
		gameState = new GameState(this);
		currentState = menuState;
		display = new Display(menuState);
	}
	
	@Override
	public void run()
	{
		System.out.println("Game Thread Created");
		

		double updateDelta = 0;
		double renderDelta = 0;

		// Current system time
		long now;

		// Time of last update
		long lastTime = System.nanoTime();

		// For FPS counter
		long timer = 0;
		int updateTicks = 0;
		int renderTicks = 0;

		while(running)
		{
			now = System.nanoTime();
			updateDelta += (now - lastTime) / timePerUpdate;
			renderDelta += (now - lastTime) / timePerRender;
			timer += now - lastTime;
			lastTime = now;

			if(updateDelta >= 1)
			{
				currentState.update();
				updateTicks++;
				updateDelta--;
			}

			if(renderDelta >= 1)
			{
				currentState.render();
				renderTicks++;
				renderDelta--;
			}

			// Console FPS counter
			if(timer >= 1000000000)
			{
				// System.out.println("Updates / Sec: " + updateTicks);
				// System.out.println("Renders / Sec: " + renderTicks);

				updateTicks = 0;
				renderTicks = 0;
				timer = 0;
			}
		}
		
		// Stop the game
		stop();
	}

	/**
	 * Starts the game's thread
	 */
	public synchronized void start()
	{
		if (running)
		{ // Shouldn't happen
			System.err.println("Attempted to start the game when it was already running");
			return;
		}

		// Update running
		running = true;

		// Create a new thread for game logic to run on
		gameThread = new Thread(this);
		gameThread.start(); // Automatically calls run()
	}

	/**
	 * Ends the game's thread
	 */
	public synchronized void stop()
	{
		if (!running)
		{ 	// Shouldn't happen
			System.err.println("Attempted to stop the game when it wasn't running");
			return;
		}
		
		try
		{
			gameThread.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public synchronized void switchToGameState()
	{
		System.out.println("switching to game state");
		
		currentState = gameState;
		display.changeState(currentState);
		gameState.reset();
		System.out.println("switched to game state");
	}
	
	public void switchToMenuState()
	{
		
	}
}
