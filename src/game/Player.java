package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player implements Collidable
{
	/** The width of the player character */
	public static final int WIDTH = 32;

	/** The height of the player character */
	public static final int HEIGHT = 32;

	/** The position of the center of the player character on the X axis */
	private double posX;

	/** The position of the center of the player character on the Y axis */
	private double posY;

	/** The velocity of the player character in the positive X direction */
	private double velX;

	/** The velocity of the player character in the positive Y direction */
	private double velY;

	@Override
	public Rectangle getCollisionBox()
	{
		return new Rectangle((int) posX, (int) posY, WIDTH, HEIGHT);
	}

	/**
	 * Move the player character according to their current velocity.
	 */
	public void update()
	{
		posX += velX;
		posY += velY;
	}

	/**
	 * Accelerate the player character.
	 * 
	 * @param accX the amount to accelerate in the position X direction
	 * @param accY the amount to accelerate in the position Y direction
	 */
	public void accelerate(double accX, double accY)
	{
		velX += accX;
		velY += accY;
	}

	/**
	 * Sets the position of the player character
	 * 
	 * @param posX the X position to set
	 * @param posY the Y position to set
	 */
	public void setPosition(double posX, double posY)
	{
		this.posX = posX;
		this.posY = posY;
	}
	
	/**
	 * Sets the velocity of the player character in the positive X direction
	 * 
	 * @param velX the velocity in the positive X direction to set
	 */
	public void setXVelocity(double velX)
	{
		this.velX = velX;
	}
	
	/**
	 * Sets the velocity of the player character in the positive Y direction
	 * 
	 * @param velY the velocity in the positive Y direction to set
	 */
	public void setYVelocity(double velY)
	{
		this.velY = velY;
	}
	
	public void render(Graphics g, Camera cam)
	{
		g.setColor(Color.black);
		g.fillRect((int) (posX - cam.getPosX()), (int) (posY - cam.getPosY()), WIDTH, HEIGHT);
		//g.fillRect((int) (posX - WIDTH/2), (int) (posY - HEIGHT/2), WIDTH, HEIGHT);

	}
	
	public String toString()
	{
		return "Player: (" + (int) posX + ", " + (int) posY + ")";
	}
}
