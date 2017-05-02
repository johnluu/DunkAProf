package game;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Font;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.Animation;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.Color;


/**
 * Game class is the main class of our game Dunk-a-prof.
 * It is responsible for bringing everything together.
 *
 *  It declares the following main objects:
 *   Scoreboard - This is where we keep track of the score and display
 *                some useful information. We put this object in the top
 *                left corner of our window.
 *   Target - This is the dunk tank and the target. Whenever ball hits
 *            the target a character sitting on the bench is dunked into
 *            the tank.
 *   Ball - This object is to be thrown at the target. If the ball hits
 *          the target a character is dunked and score is increased by
 *          some value.
 *   Timer - The timer keeps track of how much time is left for the
 *           human player to throw the ball. When timer runs out its
 *           game over.
 *  Game class also contains some miscelaneous objects such as:
 *   image for the background.
 *   image for the gameover message.
 *   boolean variables that keep track of game states.
 *   Animations and threads for the timer, the ball and the music.
 *
 */
public class Game extends BasicGame {
    private Scoreboard scoreboard;
    private Target target;
    private Ball ball;
    private Timer timer;
    private Player player;
    private Powerbar powerbar;
    private GameMusic gamemusic=null;
    private boolean entPressed=false;
    private boolean gameStarted=false;
    private boolean gameOver=false;
    private Image over,back,popup, splash;
    private Thread timerT;
    private Thread trusteeT=null;
    private TrueTypeFont font;
    private Font awtFont;
    private Trustee trustee;


    public Game(String gamename){
        super(gamename);
    }

    /**
     * This method initializes the graphics and presets any objects that
     * need to be preset.
     * init() also starts our timer() and gamemusic threads.
     * You only want to start them at
     * the beggining of the game. Whenever timer runs out
     * and player gets another chance the game calls gc.reinit() method.
     * @param gc GameContainer is where Slick2D draws the graphics.
     */
    @Override
    public void init(GameContainer gc) throws SlickException{
		awtFont = new Font("Times New Roman", Font.BOLD, 30);
		font = new TrueTypeFont(awtFont, true);
        splash = new Image("game/res/splash.png");
		
		//Spawning gamemusic thread, we only want to create it once.
		if(gamemusic==null){
			gamemusic = new GameMusic();
			gamemusic.start();
		}
		
		//Spawning timer thread.
		timer = new Timer(85,113);
		timerT = new Thread(timer);
		timerT.start();
		 
		//Creating Game objects below.
		over = new Image("game/res/gameover.png");
        back = new Image("game/res/back.png");
        popup = new Image("game/res/hit_popup.png");
       	target = new Target(600, Constants.HORIZON);
       	ball = new Ball(0,Constants.RESOLUTION_Y-130);
       	scoreboard = new Scoreboard(0,0);
     	player = new Player(0,Constants.RESOLUTION_Y-180);
     	powerbar = new Powerbar(250, 0);
		trustee = new Trustee(600,280);
     	
     	resetWorld();
    }

    /**
     * This method is usually used for updating the graphics.
     * The following keys are defined:
     *
     * UP increases ANGLE
     * DOWN decreases ANGLE
     * LEFT decreases GRAVITY
     * RIGHT increases GRAVITY
     * ENTER charges the power of the strike
     *     second push of ENTER releases the ball.
     * Controls are disabled once the ball is released.
     */
    @SuppressWarnings("static-access")
	@Override
    public void update(GameContainer gc, int i) throws SlickException{
        if(gameStarted && !ball.inMotion && !gameOver){
            if(gc.getInput().isKeyDown(Input.KEY_UP))
                player.setAngle(player.getAngle()+1);
            if(gc.getInput().isKeyDown(Input.KEY_DOWN))
                player.setAngle(player.getAngle()-1);
            if(gc.getInput().isKeyPressed(Input.KEY_ENTER)){
                //If ENTER is pressed second time, release the ball.
                if(entPressed){
                    ball.inMotion=true;
                    player.start();         //if ball is thrown start animation
                }
                entPressed=!entPressed;
				player.prepareThrow();
            }
        }
        
        if(!gameStarted && gc.getInput().isKeyPressed(Input.KEY_ESCAPE)){
            splash.destroy();
            gameStarted=true;
           // gc.reinit(); dont need to reinit() here it seems.
           //this messes with sound thread if thread is not done loading
           //by the time we reinit.
        }

        //If ENTER is pressed once keep increasing velocity.
        if(entPressed)
			powerbar.velocityOscillate();

        //Detect if the target was hit.
        if(ball.hitTarget(target)){
			resetWorld();
			Constants.GRAVITY=Math.random()*15;
        	gamemusic.playHitSound();
            scoreboard.upScore(1);
			trustee.animate();
            trusteeT = new Thread(trustee);
            trusteeT.start();
            Constants.HIT_TIME=0;
            Constants.GRAVITY_TIME=0;
            System.out.println("GRAVITY="+Constants.GRAVITY);
        }

		//The code above and below is ugly as sin.
		//Check if trustee animation is done playing,
		//if so redraw trustee in original view.
		if(trusteeT!=null && !trusteeT.isAlive())
			trustee.resetAnimation();
			
			
        //Check if the ball is off screen
        if(ball.offScreen()){
			resetWorld();
            gameStarted=true;
        }


        //If ball is in motion throw it
        if(ball.inMotion){
        	gamemusic.playBallSound();
            ball.throwBall(player.getAngle(),powerbar.getVelocity());
         }

        //If timer has run out then its game over
        if(!timerT.isAlive()){
            gameOver=true;
            entPressed=false;
        }

        //If player is done throwing redraw him in the idle position
        if(player.doneThrow())
		    player.resetAnimation();
		Constants.HIT_TIME+=i;
		Constants.GRAVITY_TIME+=i;

    }

    /**
     * This method renders the graphics.
     * @param gc GameContainer is where we draw things, listen for key
     * events etc.
     * @param g Graphics is where we draw everything in the game.
     */
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException{
        g.setFont(font);
        g.setColor(Color.black);
        if(!gameStarted){
            splash.draw(0,0);
        }else{
			back.draw(0,0);
			scoreboard.render(g);
			target.render(g);
			ball.render(g);
			timer.render(g);
			player.render(g);
			powerbar.render(g);
			trustee.render(g);
			if(gameOver){
				over.draw(100,200);
				if(gc.getInput().isKeyPressed(Input.KEY_ESCAPE)){
					gameOver=false;
					gc.reinit();
				}
			}
            
			if(Constants.HIT_TIME<1000)
				popup.draw(600,100);
            
			if(Constants.GRAVITY_TIME<1000){
				g.setColor(Color.white);
				g.drawString("Gravity changed, try to hit it now!",100,500);
				g.setColor(Color.black);
			}
		}
	}

    /**
     * Main method is responsible for drawing the window and all other
     * housekeeping. This is program's entry point.
     */
    public static void main(String[] args){
        try{
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Game("Dunk a professor"));
            appgc.setDisplayMode(Constants.RESOLUTION_X,
                                 Constants.RESOLUTION_Y, false);
            appgc.setVSync(true);
            appgc.setTargetFrameRate(60);
			appgc.setShowFPS(false);
            appgc.start();
        }
        catch (SlickException ex){
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void resetWorld(){
		double temp;
		
		ball.reset();
        temp=Constants.GRAVITY;
        Constants.reset();
        Constants.GRAVITY=temp;
        player.reset();
	}

}
