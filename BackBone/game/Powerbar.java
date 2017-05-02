package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

public class Powerbar extends GameObject {
	private int velocity=0;
	private double PI=3.1415926;
	
	public Powerbar(int x, int y) throws SlickException{
		super(x,y);
		sprite = new Image("game/res/power_bar.png");
	}
	
	public void render(Graphics g) throws SlickException{
		super.render(g);
		g.fillRect((float)(xPos+velocity*2), yPos, 5, 25);
		g.drawString("Throw power",xPos+50, yPos+30);
	}
	
	public int getVelocity(){
		return velocity;
	}
	
	public void setVelocity(int velocity){
		this.velocity=velocity;
	}
	
	public void velocityOscillate(){
		int velLimit=150;
		double step=0.02;
		
		velocity = (int)(Math.abs(Math.sin(PI))*velLimit);
		PI+=step;
	}
}
