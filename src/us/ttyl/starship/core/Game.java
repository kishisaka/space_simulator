package us.ttyl.starship.core;

import us.ttyl.starship.display.MapDisplay;
import us.ttyl.starship.display.MovementDisplay;
import us.ttyl.starship.listener.GameStateListener;
import us.ttyl.starship.movement.FreeEngine;
import us.ttyl.starship.movement.MovementEngine;

/**
 * sprite size is 36x36. 
 * 
 * keys: 
 * -------------------------
 * a - fire gun
 * d - fire missile
 * w - accelerate forward
 * mouse movement - turn ship
 * p - reset game
 * -------------------------
 *  
 * @author Kurt Ishisaka
 *
 */
public class Game extends Thread
{
	//MovementDisplay mMovementDisplay = null;
	MapDisplay mMapDisplay = null;
	MainLoop mMainLoop = null;
	
	public Game()
	{
		init();
		GameStateListener gsl = new GameStateListener()
		{
			@Override
			public void onPlayerDied() 
			{	
				GameState._lives = GameState._lives - 1;
				if (GameState._lives  >= 0)
				{
					MovementEngine player = new FreeEngine(0, 0, 0d, 0d, 0d, 0d, 5, .1d, 0, "player", -1);
					GameState._weapons.set(0, player);
				}
				else
				{
					GameState._weapons.remove(0);
				}
				//remove all enemy guns and missiles
				for(MovementEngine enemyWeapon : GameState._weapons)
				{
					if (enemyWeapon.getWeaponName().equals("enenmy_gun") || enemyWeapon.getWeaponName().equals("enenmy_missile") )
					{
						enemyWeapon.setEndurance(Integer.MAX_VALUE);
					}
				}
			}
		};
		mMainLoop = new MainLoop(gsl);		
		mMapDisplay = new MapDisplay();
		//mMovementDisplay = new MovementDisplay(mMapDisplay);
		start();
	}
	
	public void init()
	{		
		// GameState._sprites = GameUtils.getTilesFromFile();
		
		SpeedController controller = new SpeedController();
		controller.start();
		
//		GameState._audioPlayerShot = new AudioPlayer("./sounds/gun2.wav");
//		GameState._audioPlayerEnemyShot = new AudioPlayer("./sounds/trek1.wav");
//		GameState._audioPlayerEnemyDeath = new AudioPlayer("./sounds/enemy_death.wav");
//		GameState._audioPlayerMissile = new AudioPlayer("./sounds/trek2.wav");
		MovementEngine player = new FreeEngine(0, 0, 0d, 0d, 0d, 0d, 5, .1d, 0, "player", -1);
		GameState._weapons.add(player);		 			
	}
	
	public void run()
	{
		while (true)
	    {
	    	try
	      	{
	    		//mMovementDisplay.updateDisplay();
	    		mMapDisplay.repaint();
	        	sleep(16);
	      	}
	      	catch (Exception e)
	      	{
	      		
	        	e.printStackTrace();
	      	}
	    }
	}
	
	public static void main(String args[])
	{
		new Game();
	}
}
