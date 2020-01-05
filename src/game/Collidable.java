package game;

import java.awt.Rectangle;

public interface Collidable
{
	
	/**
	 * Determines if two Collidable objects have intersection collision boxes
	 * @param o1 the first object
	 * @param o2 the second object
	 * @return true if the boxes are intersecting
	 */
	public static boolean areColliding(Collidable o1, Collidable o2)
	{
		return o1.getCollisionBox().intersects(o2.getCollisionBox());
	}
	
	public Rectangle getCollisionBox();
}
