package game;

import java.awt.Rectangle;

public class Camera implements Collidable
{
	/** The position of the center of the camera's viewport on the X axis */
	private double posX;
	
	/** The position of the center of the camera's viewport on the Y axis */
	private double posY;
	
	private double width;
	
	private double height;
	
	public Camera(double width, double height)
	{
		this.width = width;
		this.height = height;
	}
	
	@Override
	public Rectangle getCollisionBox()
	{
		return new Rectangle((int) posX, (int) posY, (int) GameState.PANEL_WIDTH, (int) GameState.PANEL_HEIGHT);
	}

	public double getPosX()
	{
		return posX;
	}

	public double getPosY()
	{
		return posY;
	}

	public void setPosX(double posX)
	{
		this.posX = posX;
	}

	public void setPosY(double posY)
	{
		this.posY = posY;
	}

	public void update()
	{
		posX += GameState.CAMERA_SPEED;
	}
	
	public void reset()
	{
		// Reset position to the top left corner
		posX = posY = 0;
	}
	
	public String toString()
	{
		return "Camera: (" + (int) posX + ", " + posY + ")";
	}
}
