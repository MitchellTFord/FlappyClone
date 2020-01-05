package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Obstacle implements Collidable
{
	/** The width of this obstacle */
	public static final int WIDTH = 64;

	/** The height of this obstacle */
	public static final int HEIGHT = 192;

	/** The position of the center of this obstacle on the X axis */
	private double posX;

	/** The position of the center of this obstacle on the Y axis */
	private double posY;
	
	public Color color = Color.black;

	public Obstacle(int posX, int posY)
	{
		this.posX = posX;
		this.posY = posY;  
	}
	
	@Override
	public Rectangle getCollisionBox()
	{
		return new Rectangle((int) posX, (int) posY, WIDTH, HEIGHT);
	}
	
	public void render(Graphics g, Camera cam)
	{
		g.setColor(color);
		g.fillRect((int) (posX - cam.getPosX()), (int) (posY - cam.getPosY()), WIDTH, HEIGHT);
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	public String toString()
	{
		return "Obstacle: (" + (int) posX + ", " + posY + ")";
	}
}
