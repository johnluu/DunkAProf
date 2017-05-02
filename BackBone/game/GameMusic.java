/**
 * This class store data about Music&Sounds.
 * It has the following variables:
 * 	backgroundmusic - stores .wav file for the background music.
 *  ballsound - stores .wav file for the ball sound flying.
 *  targetsound - stores .wav file for hitting the target.
 *  playBallSound - boolean flag that controls whether ballsound is 
 * 					played.
 *  playHitSound - boolean flag that controls whether targetsound is
 * 				   played.
 * 	playBackgroundSound - boolean flag that controls whether background
 * 						  music is played. Set to true by default so 
 * 						  that music is played when object is created.
 * 
 * And methods:
 * 	public void playBackgroundSound() - sets the playBackgroundSound
 * 										   to true.
 *  public void playBallSound() - sets the playBallSound to true.
 *  public void playHitSound() - sets playHitSound to true.
 *  public void run() - main thread method that checks all the flags
 * 						in the loop and plays appropriate music.
 * 
 */
package game;

import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SlickException;

public class GameMusic extends Thread {
	private Music backgroundMusic = null;
	private Sound ballSound = null;
	private Sound hitSound = null;
	private volatile boolean playBallSound = false;
	private volatile boolean playHitSound = false;
	private volatile boolean playBackgroundSound = true;
		
	/** 
	 * Sets playBackGroundMusic flag to true.
	 * run() method checks all the flags in a loop and plays the sound
	 * if the appropriate flag is set to true.
	 */
	public void playBackgroundSound() {
		playBackgroundSound = true;
	}
	
	/**
	 * Sets playBallSound flag to true.
	 * run() method checks all the flags in a loop and plays the sound
	 * if the appropriate flag is set to true.
	 */
	public void playBallSound() {
		playBallSound = true;
	}

	/**
	 * Sets playHitSound flag to true.
	 * run() method checks all the flags in a loop and plays the sound
	 * if the appropriate flag is set to true.
	 */
	public void playHitSound() {
		playHitSound = true;
	}
	
	/**
	 * Threaded code follows. We load music and sounds in the thread
	 * instead of the constructor to avoid blocking the Game until all
	 * the sounds load. This way sounds load in the background and the
	 * game is playable right away.
	 */
	public void run(){
		try{
			backgroundMusic = new Music("game/res/Game.wav");
			ballSound = new Sound("game/res/ball.wav");
			hitSound = new Sound("game/res/target.wav");
		}catch(SlickException e){
			e.printStackTrace();
		}
		while(true){
			if(playBackgroundSound){
				backgroundMusic.setVolume(.7f);
				backgroundMusic.loop();
				playBackgroundSound=false;
			}
			if(playHitSound){
				hitSound.play();
				playHitSound=false;
			}
			if(playBallSound){
				ballSound.play();
				playBallSound=false;
			}
			try{
				Thread.sleep(500);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
