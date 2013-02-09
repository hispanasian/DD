package DD.ActionBox;

import java.util.ArrayList; 
import java.util.Queue;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.sun.corba.se.spi.orbutil.fsm.Action;

import DD.Character.Character;
import DD.Character.Abilities.Ability;
import DD.SlickTools.BoxInterface;
import DD.SlickTools.Component;
import DD.SlickTools.Entity;
import DD.SlickTools.RenderComponent;

/*****************************************************************************************************
 * The ActionBox class represents the Action Box in the GUI. It will hold possible player actions as well
 * as game logic. It is here where the combat system will be implemented. As per Pathfinder combat, the
 * actions that the player is allowed to perform are as follows:
 * 1. Standard Action
 * 2. Movement Action
 * 3. Full-round Action
 * 4. Swift Action
 * 5. Immediate Action
 * 6. Free Action
 * 
 * More information can be found about Pathfinder combat here:
 * http://www.d20pfsrd.com/gamemastering/combat
 * 
 * The class will depend heavily on drawing the Action choices from the Character class which should
 * provide an interface to obtain "default" actions as well as "sub" actions. That is, a default
 * action a player might have would be attack. However, a feat would not be a default action. These 
 * actions, however, need to be available in the ActionBox for the player to be able to decide on an
 * action to perform. ActionBox will be the View component for DD combat. It will communicate with 
 * CombatSystem (the controller).
 * 
 * The "extra" actions will be some sub-type of the Component class that will be added to the SubAction
 * array list so that they can be updated and rendered accordingly, based on the action chosen. 
 ******************************************************************************************************/

public class ActionBox extends BoxInterface 
{
	/************************************ Class Constants *************************************/
	public static final int STANDARD_ACTION = 1;
	public static final int MOVE_ACTION = 2;
	public static final int FULL_ROUND_ACTION = 3;
	public static final int SWIFT_ACTION = 4;
	public static final int IMMEDIATE_ACTION = 5;
	public static final int FREE_ACTION = 6;
	public static final int NUM_OF_ACTIONS = 6;
	
	/************************************ Class Attributes *************************************/
	protected ArrayList<Integer> subActions;		/* integer array list that holds the id of the subActions */
	protected static Character character = null;
	
	/************************************ Button Images *************************************/
	Image freeAction = null;
	Image fullRoundAction = null;
	Image immediateAction = null;
	Image moveAction = null;
	Image standardAction = null;
	Image swiftAction = null;
	
	public ActionBox(int id, float length, float width, Character character, Vector2f position) throws SlickException
	{
		super(id, length, width, position);
		components = new ArrayList<Component>();
		this.character = character;
		subActions = null;
		
		freeAction = new Image("Images/ActionBox/FreeAction.png");
		fullRoundAction = new Image("Images/ActionBox/FullRoundAction.png");
		immediateAction = new Image("Images/ActionBox/ImmediateAction.png");
		moveAction = new Image("Images/ActionBox/MoveAction.png");
		standardAction = new Image("Images/ActionBox/StandardAction.png");
		swiftAction = new Image("Images/ActionBox/SwiftAction.png");
		
		/* To begin with, the basic ActionChoices need to be available. */
		this.addComponent(new ActionChoice(this.id, STANDARD_ACTION, "Standard Action", standardAction, position.x, position.y));
		this.addComponent(new ActionChoice(this.id, MOVE_ACTION, "Move Action", moveAction, position.x + 20, position.y + 20));
		this.addComponent(new ActionChoice(this.id, FULL_ROUND_ACTION, "Full Round Action", fullRoundAction, position.x + 40, position.y + 40));
		this.addComponent(new ActionChoice(this.id, SWIFT_ACTION, "Swift Action", swiftAction, position.x + 60, position.y + 60));
		this.addComponent(new ActionChoice(this.id, IMMEDIATE_ACTION, "Immediate Action", immediateAction, position.x + 80, position.y + 60));
		this.addComponent(new ActionChoice(this.id, FREE_ACTION, "Free Action", freeAction, position.x + 100, position.y + 100));
		
	} /* end ActionBox constructor */
	
	public ArrayList<Component> getComponentList() 
	{
		return components;
	}
	
	public void addSubAction(Ability ability)
	{ /* Convert the provided Ability into a SubAction and add it to the components ArrayList*/
		SubAction subAction = new SubAction(this.id);	/* the id is set to any arbitrary id. It will be reset in the addComponent method */
		subActions.add(this.addComponent(subAction));	/* Add the SubAction and add it's id to the subActions ArrayList */
	} /* end addSubAction method */
	
	public void removeSubAction(int id)
	{ /* remove the provided subAction from the components array list */
		this.removeComponent(id);
	} /* end removeSubAction method */
	
	public void clearSubActions()
	{ /* removes all SubActions from subAction */
		for (Integer subAction : subActions) this.removeComponent(subAction);
	} /* end clearSubACtions method */
	
	public void unclickSubActions()
	{ /* Set abilityClicked to false for all subActions in ActionBox. */
		Component holder = null;
		for (Integer subAction : subActions) 
		{ /* unclick */
			holder = getComponent(subAction);
			((SubAction)holder).unclick();
		} /* end for loop */
		
	} /* end unclickSubACtions method */

} /* end ActionBox method */
