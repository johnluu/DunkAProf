package game;

/**
 * Constants class. This class contains all global variables
 * used by the Game. 
 * It defines the following:
 * 	double GRAVITY - used for calculating ball trajectory in Ball.java
 *  int RESOLUTION_X - width of the game window.
 *  int RESOLUTION_Y - height of the game window.
 *  int HORIZON - this defines the imaginary line on which we place
 *                our ball and target. Its basically y coordinate.
 *  int ANIMATION_SPEED - defines how fast the ball moves. I.E. we 
 * 						  update the ball position by 9 pixels at a time
 *  int ANGLE - defines the angle at which the ball is thrown. It is
 * 				set by Game.java.
 *  int VELOCITY - defines the velocity at which the ball is thrown.
 * 				   Set by Game.java
 *  reset() method - resets all constants to their defaults.
 */
public class Constants {
	public static double GRAVITY=9.8;
	public static int RESOLUTION_X=800;
	public static int RESOLUTION_Y=600;
	public static int HORIZON=200;
	public static int ANIMATION_SPEED=9;
	public static int PLAYER_X=0;
    	public static int PLAYER_Y=130;
    	public static int HIT_TIME=1001;
    	public static int GRAVITY_TIME=1001;
    
	public static void reset(){
		GRAVITY=9.8;
		RESOLUTION_X=800;
		RESOLUTION_Y=600;
		HORIZON=200;
		ANIMATION_SPEED=9;
		PLAYER_X=0;
		PLAYER_Y=80;
		HIT_TIME=1001;
		GRAVITY_TIME=1001;
		
		return;
	}
	
		
}
