package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

/**
 * This class describes threaded timer for Dunk-a-Prof. Its purpose is
 * to count down from a preset number of seconds. When time runs out
 * the game is over.
 * 
 * Timer has the following properties:
 *  int time - time to countdown.
 *
 * And methods:
 * 	Timer(int x, int y) - constructor that creates a timer with given
 *  					  x and y coordinates.
 * 	void run() - this method is part of java threads and it is run 
 * 				 whenever a new thread is spawned.
 *  void render(Graphics g) - renders the timer to a Graphics context g.
 *  
 */
public class Timer extends GameObject implements Runnable{
    private int time=30;
    
    /**
     * Constructor that creates a timer with x,y coordinates
     * @param  x x coordinate of the timer in the game window.
	 * @param  y y coordinate of the timer in the game window.     
     */
    public Timer(int x, int y) throws SlickException{
		super(x,y);
	}
	
	/**
	 * run() method is part of java Thread class. It is run whenever a
	 * new thread is spawned. 
	 * This particular thread decrements the timer and sleeps for 1000ms
	 * until timer is at 0. 
	 * Throws InterruptedException whenever the thread is stopped 
	 * abnormally.
	 */
    public void run() {
		try{
			while(time>0){
				time--;
				Thread.sleep(1000);
			}
        } 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return;
    }
   
	/**
	 * This method renders our timer to a given graphics context g.
	 * It throws SlickException.
	 * @param g Graphics object on which the timer will be rendered.
	 */
    public void render(Graphics g) throws SlickException {
        g.drawString(Integer.toString(time), xPos, yPos);
		return;
    }
}
