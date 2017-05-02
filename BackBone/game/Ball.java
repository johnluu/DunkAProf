package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Circle;

/**
 * This class describes the ball that will be thrown at the target.
 * The ball has the following properties:
 *  boolean inMotion - a flag that tells us if the ball is at rest or
 * 					   flying through the stage.
 *  Shape hitbox - this is shape that encloses our ball. It is needed
 * 				   for collision detection.
 */
public class Ball extends GameObject{
    public static boolean inMotion=false;
	private Shape hitbox;   //hitbox for ball
      
    /**
     * Constructor that creates the ball object with given x and y
     * coordinates.
	 * @param x x coordinate of where to place the ball in our game.
	 * @param y y coordinate of where to place the ball in our game.
     * Constructor throws SlickException.
     */
    public Ball(int x, int y) throws SlickException{
		super(x,y);
        sprite = new Image("game/res/ball.png");
		hitbox = new Circle(xPos,yPos,10);
	}
	
	/**
	 * This method is responsible for calculating the ball elevation at
	 * any given moment.
	 * If the ball is in motion we will call this method continiously.
	 * It will calculate the y coordinate based on current x coordinate,
	 * the angle of throw and velocity. Then it will increment x by 
	 * Constants.ANIMATION_SPEED and return.
	 * @param angle initial angle of throw.
	 * @param velocity initial velocity of the ball. The formula doesn't
	 *        take into account changes in velocity during the flight.
	 * 		  It assumes the velocity stays constant to keep calculation
	 * 		  simple.
	 */
	public void throwBall(int angle, int vel){
		double rad=Math.toRadians(angle);
		double deviation;
	
		deviation=(Constants.GRAVITY*xPos*xPos)/(2*Math.pow(vel*Math.cos(rad),2.0));
		yPos=(int)(Constants.RESOLUTION_Y-130-xPos*Math.tan(rad)+deviation);
		incxPos(Constants.ANIMATION_SPEED);
		hitbox.setLocation(xPos, yPos);
		return;
	}
	
	/**
	 * Increments X position by x;
	 * @param x how much to increment the x position by.
	 */
	public void incxPos(int x){
		xPos+=x;
		return;
	}
	
	/**
	 * This method is almost the same as withinBounds()
	 * except it takes object Target as a parameter instead of
	 * individual coordinates
	 */
	public boolean hitTarget(Target tar){
		if (hitbox.intersects(tar.getHitbox()))
			return true;
		return false;
	}

	/**
	 * Returns true if ball is outside of our stage.
	 * returns false otherwise.
	 */
	public boolean offScreen(){
		if(xPos>Constants.RESOLUTION_X || yPos>Constants.RESOLUTION_Y)
			return true;
		return false;
	}
	
	/**
	 * Returns the ball to its intial position, after we thrown it.
	 */
	public void reset(){
		xPos=0;
        	yPos=Constants.RESOLUTION_Y-130;
		inMotion=false;
		hitbox.setLocation(xPos, yPos);
		return;
	}
}

