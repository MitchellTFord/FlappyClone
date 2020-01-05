package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameState extends State
{
	public static int PANEL_WIDTH = 600;
	public static int PANEL_HEIGHT = 400;

	/** The downward acceleration the player character experiences */
	private static final double GRAVITY = .2;

	/** The upward acceleration the player character experiences when flapping */
	private static final double FLAP_FORCE = 6;
	
	private static final double FLAP_COOLDOWN = .5;
	
	private static final double OBSTACLE_COOLDOWN = 3;
	
	private static int ticksSinceLastObstacleSpawn = (int) (OBSTACLE_COOLDOWN * Game.fps);

	private static final int MAX_OBSTACLES = 5;
	
	/** The rate at which the camera viewport should move to the right */
	public static int CAMERA_SPEED = 2;

	private Player player;

	private ArrayList<Obstacle> obstacles;

	private Camera camera;
	
	private CanvasComponent canvas;
	
	private int ticksSinceLastFlap = (int) (FLAP_COOLDOWN * Game.fps);
	
	/**
	 * GameState constructor.
	 * 
	 * @param game
	 */
	public GameState(Game game)
	{
		super(game);

		initPanel();

		camera = new Camera(canvas.getWidth(), canvas.getHeight());

		// Create the player
		player = new Player();

		// Create the obstacles ArrayList
		obstacles = new ArrayList<Obstacle>();

		reset();
		
		// TODO Auto-generated constructor stub
	}

	private void initPanel()
	{
		BorderLayout layout = new BorderLayout();
		layout.setHgap(0);
		layout.setVgap(0);
		
		contentPane = new JPanel(layout);
		contentPane.setSize(PANEL_WIDTH, PANEL_HEIGHT);

		canvas = new CanvasComponent();
		contentPane.add(canvas);
		canvas.setPreferredSize(contentPane.getSize());
	}

	@Override
	public void update()
	{
		player.update();

		// Apply gravity
		player.accelerate(0, GRAVITY);
		
		// Check to see if the player is colliding with any obstacles
		for (Obstacle obstacle : obstacles)
		{
			if (Collidable.areColliding(player, obstacle))
			{
				// TODO game over
				System.out.println("game over: player collided with obstacle");
				obstacle.setColor(Color.red);
			}
			else
			{
				obstacle.setColor(Color.black);
			}
		}
		
		// Check to see if the player has left the camera area
		if(!Collidable.areColliding(player, camera))
		{
			//TODO game over
			System.out.println("game over: player left camera area");
		}
		
		// Remove any obstacles that have left the camera area
		obstacles.removeIf(el -> !Collidable.areColliding(el, camera));
		
		ticksSinceLastObstacleSpawn = Math.min(ticksSinceLastObstacleSpawn + 1, Integer.MAX_VALUE);
		
		//TODO create more obstacles
		if(obstacles.size() < MAX_OBSTACLES && ticksSinceLastObstacleSpawn >= OBSTACLE_COOLDOWN * Game.fps)
		{
			if(Math.random() > 0.5)
			{
				obstacles.add(new Obstacle((int) (camera.getPosX() + PANEL_WIDTH - 1), 0));
			}
			else
			{
				obstacles.add(new Obstacle((int) (camera.getPosX() + PANEL_WIDTH - 1), PANEL_HEIGHT - Obstacle.HEIGHT));
			}
			
			ticksSinceLastObstacleSpawn = 0;
		}
		
		ticksSinceLastFlap = Math.min(ticksSinceLastFlap + 1, Integer.MAX_VALUE);
		
		// Advance the camera viewport to the right
		camera.setPosX(camera.getPosX() + CAMERA_SPEED * getTickFactor());
	}

	@Override
	public void render()
	{
		canvas.repaint();
	}

	/**
	 * Helper method to accelerate the player character upward
	 */
	private void flap()
	{
		if(ticksSinceLastFlap >= FLAP_COOLDOWN * game.fps)
		{
			//System.out.println("flap");
			player.accelerate(0, -FLAP_FORCE * getTickFactor());
			ticksSinceLastFlap = 0;
		}
	}

	/**
	 * Resets the player position and generates new obstacles
	 */
	public void reset()
	{
		player.setPosition(100, 200);
		player.setXVelocity(CAMERA_SPEED * getTickFactor());
		player.setYVelocity(0);
		camera.setPosX(0);
		camera.setPosY(0);
		obstacles.removeIf(el -> true);
	}

	private double getTickFactor()
	{
		return 30 / Game.fps;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
		case KeyEvent.VK_SPACE:
			flap();
			break;
		default:
			return;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	private class CanvasComponent extends JComponent
	{
		@Override
		public void paintComponent(Graphics g)
		{
			//System.out.println("render");

			// Draw background
			g.setColor(Color.white);
			g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
			
			// Render the obstacles
			for (Obstacle obstacle : obstacles)
			{
				obstacle.render(g, camera);
			}

			// Render the player character
			player.render(g, camera);

			
			g.setColor(Color.magenta);
			g.drawString(player.toString(), 10, 10);
			g.drawString(camera.toString(), 10, 25);
			
			if(obstacles.size() > 0)
			{
				int i = 1;
				g.drawString("Obstacles: ", 10, 25 + 15 * i++);
				for(Obstacle el : obstacles)
				{
					g.drawString(el.toString(), 25, 25 + 15 * i++);
				}
			}
		}
	}
}
