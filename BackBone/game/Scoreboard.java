package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

/**
 * This class creates a scoreboard and keeps tracks of the score.
 * It defines the following properties:
 *  score - keeps track of the score.
 * And methods:
 *  public Scoreboard(int x, int y) - constructor that creates 
 * 									  scoreboard with x,y coordinates.
 *  public void render(Graphics g) - renders the board to a given 
 * 									 Graphics g, overrides parent.
 *  public void upScore(int score) - increases the score by x.
 *  public int getscore() - returns current score.
 */
public class Scoreboard extends GameObject {
    private int score = 0;

    public Scoreboard(int x, int y) throws SlickException{
		super(x,y);
        sprite = new Image("game/res/scoreboard.png");
	}
   
    public void render(Graphics g) throws SlickException {
        super.render(g);
		g.drawString(Integer.toString(score),xPos+105, yPos+42);
    }
    
    public void upScore(int x){
		score = score + x;
	}
	
    public int getScore(){
    	return score;
    }
}
