package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

public class Player extends GameObject{
	private Image[] playerImages;       //Animations go in here
    private Animation sprite;           //and here
    private Image arrow;
    private int angle=0;

	public Player(int x, int y) throws SlickException{
		super(x,y);
		arrow = new Image("game/res/arrow.png");
		playerImages = new Image[5];
		playerImages[0] = new Image("game/res/1.png");
		playerImages[1] = new Image("game/res/2.png");
		playerImages[2] = new Image("game/res/3.png");
		playerImages[3] = new Image("game/res/4.png");
		playerImages[4] = new Image("game/res/5.png");
		sprite = new Animation(playerImages,150);
		sprite.stop();
		sprite.setLooping(false);
	}

	public void resetAnimation(){
		sprite.setCurrentFrame(0);
		sprite.stop();
	}
	
	public void reset(){
		resetAnimation();
		angle=0;
	}

	public void prepareThrow(){
		sprite.setCurrentFrame(1);
	}

	public void render(Graphics g) throws SlickException{
		sprite.draw(xPos,yPos);
		arrow.draw(xPos+50, yPos+40);
		arrow.setCenterOfRotation(0, arrow.getHeight()/2);
		arrow.setRotation(-angle);
	}
	
	public boolean doneThrow(){
		if (sprite.getFrame()==4)
			return true;
		return false;
	}
	
	public int getFrame(){
		return sprite.getFrame();
	}
	
	public void start(){
		sprite.start();
	}
	
	public int getAngle(){
		return angle;
	}
	
	public void setAngle(int angle){
		if(angle>90)
			this.angle=90;
		else if(angle<0)
			this.angle=0;
		else
			this.angle=angle;
	}	
}
