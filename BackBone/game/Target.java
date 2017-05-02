package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Ellipse;

/**
 * This class describes basic target for the game Dunk-a-prof.
 * Our target has the following properties:
 * 	hitbox - this is needed for collision detection. It is basically
 * 			 a shape that closely resembles our target. Shape
 * 			 objects have intersects() method that makes it easy
 * 			 to check if the target collided with the ball.
 * And methods:
 * 	public Target(int x, int y) - creates a target with x,y coordinates.
 * 	public Shape getHitbox() - returns hitbox.
 */
public class Target extends GameObject{
    private Shape hitbox;
    
    /**
     * This constructor creates a target with x and y coordinates
     * and draws hitbox enclosing the target for collision detection.
     */
    public Target(int x, int y) throws SlickException{
		super(x,y);
        sprite = new Image("game/res/target.png");
		hitbox = new Ellipse(x+120, y+100, 42, 58);
	}
    
    public Shape getHitbox(){
    	return hitbox;
    }
    
}
