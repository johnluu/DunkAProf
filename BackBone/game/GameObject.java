package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

/**
 * This class describes a generic object for the game Dunk-a-prof.
 * Our genric object has the following properties:
 * 	xPos - x coordinate of the object in our Game window.
 * 	yPos - y coordinate of the object in our Game window.
 *  sprite - the image for our object.
 * And methods that will be shared by all actors in the scene.
 *  protected int getxPos() - returns x position of the top-left corner
 * 							  of the sprite
 *  protected int getyPos() - returns y position of the top left corner
 * 							  of the sprite
 *  protected void setxPos() - sets x position of -------------
 *  protected void setyPos() - sets y position of -------------
 *  protected void render(Graphics g) - render the sprite to graphics
 * 										context g.
 */
public class GameObject {
	protected int xPos=0;
    protected int yPos=0;
    protected Image sprite;

    protected GameObject(int x, int y) throws SlickException{
		xPos = x;
		yPos = y;
		sprite = new Image("game/res/ball.png");
	}
   
    protected void render(Graphics g) throws SlickException {
        sprite.draw(xPos, yPos);
    }

    protected int getxPos(){
        return xPos;
    }
    
    protected int getyPos(){
        return yPos;
    }
    
    protected void setxPos(int x){
		xPos=x;
		return;
	}
	
	protected void setyPos(int y){
		yPos=y;
		return;
	}
}
