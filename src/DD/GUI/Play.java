package DD.GUI;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
public class Play extends BasicGameState{
	
	Animation bucky, movingUp,movingDown,movingLeft, movingRight;
	Image worldMap;
	boolean quit = false;
	int[] duration = {200,200};
	float buckyPositionX = 0;
	float buckyPositionY = 0;
	float shiftX = buckyPositionX + 320;
	float shiftY = buckyPositionY + 160;
	
	Image screen;
	private String mouse = "No input yet!";
	Image play;
	Image options;
	Image about;
	//Sound button;
	
	public Play(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
	{
		/*
		worldMap = new Image("res/world.png");
		Image[] walkUp = {new Image("res/buckysBack.png"), new Image("res/buckysBack.png")};
		Image[] walkDown = {new Image("res/buckysFront.png"), new Image("res/buckysFront.png")};
		Image[] walkLeft = {new Image("res/buckysLeft.png"), new Image("res/buckysLeft.png")};
		Image[] walkRight = {new Image("res/buckysRight.png"), new Image("res/buckysRight.png")};
		
		movingUp = new Animation(walkUp,duration,false);
		movingDown = new Animation(walkDown,duration,false);
		movingLeft = new Animation(walkLeft,duration,false);
		movingRight = new Animation(walkRight,duration,false);
		*/
		screen = new Image("Images/Menus/DD1.jpg");
		
		
		play = new Image("Images/Menus/play button.png");
		//options = new Image("res/options button.png");
		about = new Image("Images/Menus/about button.png");

		//button = new Sound("res/button-9.wav");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
	{
		screen.draw(0,0);
		play.draw(100,100);
		//options.draw(100, 200);
		about.draw(100, 300);
		g.drawString(mouse, 100, 400);
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg,int delta)throws SlickException
	{
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		//play now button
		if((posX > 100 && posX < 244) && (posY > 497 && posY < 549))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				sbg.enterState(0);
			}
		}
		//about
		if((posX > 100 && posX < 241) && (posY > 310 && posY < 349))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				//button.play();
				sbg.enterState(2);
			}
		}
		
		
		 
	      mouse = "Mouse position x: " + posX + " y: " + posY;
	      

		
	}
	
	public int getID()
	{
		return 1;
	}

}
