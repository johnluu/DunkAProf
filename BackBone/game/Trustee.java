package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

/**
 * This class describes the Trustee object for the game Dunk-a-Prof. This
 * class is an extension of the Characters class.
 */

public class Trustee extends GameObject implements Runnable{
	private volatile Animation sprite; 
	private volatile Image[] trusteeImages;       //Animations go in here
	private volatile int yPos;

	//the Trustee subclass has one constructor
	public Trustee(int x, int y) throws SlickException {
		super(x,y);
		yPos=super.yPos;
		trusteeImages = new Image[16];
		trusteeImages[0] = new Image("game/res/Trustee_Jump.png");
		trusteeImages[1] = new Image("game/res/TD1.png");
		trusteeImages[2] = new Image("game/res/TD2.png");
		trusteeImages[3] = new Image("game/res/TD3.png");
		trusteeImages[4] = new Image("game/res/TD4.png");
		trusteeImages[5] = new Image("game/res/TD5.png");
		trusteeImages[6] = new Image("game/res/TD6.png");
		trusteeImages[7] = new Image("game/res/TD7.png");
		trusteeImages[8] = new Image("game/res/TS1.png");
		trusteeImages[9] = new Image("game/res/TS2.png");
		trusteeImages[10] = new Image("game/res/TS3.png");
		trusteeImages[11] = new Image("game/res/TS4.png");
		trusteeImages[12] = new Image("game/res/TS5.png");
		trusteeImages[13] = new Image("game/res/TS6.png");
		trusteeImages[14] = new Image("game/res/TS7.png");
		trusteeImages[15] = new Image("game/res/TS8.png");
		sprite = new Animation(trusteeImages,150);
		sprite.stop();
		sprite.setLooping(false);
	}

	public void render(Graphics g) throws SlickException{
		sprite.draw(xPos,yPos);
	}

	public void run(){
		int original;
		try{
			original=yPos;
			while (sprite.getFrame()<=7){
				yPos+=5;
				Thread.sleep(50);
			}
			while (sprite.getFrame()<15){
				yPos-=5;
				Thread.sleep(50);
			}
			yPos=original;
        } 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void animate(){
		sprite.start();
	}
	public void resetAnimation(){
		sprite.setCurrentFrame(0);
		sprite.stop();
	}


/** You dont need this method, Trustee inherits render(Graphics g) that
 * is exactly the same. from GameObject. When you add animations and 
 * such you will need it though
 *
	public void render(Graphics g){
		sprite.draw(xPos,yPos);
	}
	//private int xPos=0;
    //private int yPos=0;
    //private float size=1f;
    //private final Image trusteePic;*/
}

//methods
