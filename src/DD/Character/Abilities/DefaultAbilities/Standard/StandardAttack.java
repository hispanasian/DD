package DD.Character.Abilities.DefaultAbilities.Standard;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import DD.ActionBox.Dice;
import DD.ActionBox.CombatSystem.TargetingSystem.TargetingSystem;
import DD.Character.Abilities.Ability;
import DD.CombatSystem.CombatSystem;
import DD.CombatSystem.Interpreter.Standard.I_StandardAttack;
import DD.Message.ChooseTargetMessage;
import DD.Message.CombatMessage;
import DD.Message.TargetSelectedMessage;

/*****************************************************************************************************
 * The StandardAttack Ability is one of the default abilities available to all players in game. It 
 * consumes one attack and, by definition, allows for a movement action. The update method should render
 * a FF tactics style square selection for selecting targets. The selected target will need to be passed
 * to the action method.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class StandardAttack extends Ability
{	
	/************************************ Class Methods *************************************/
	public StandardAttack(int id) 
	{
		super(id, CombatSystem.ActionType.STANDARD, "Standard Attack", "Perform an attack with main hand weapon as a standard action");
	} /* end StandardAttack constructor */

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		// TODO Auto-generated method stub
		
	} /* end update method */
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) {
		// TODO Auto-generated method stub
		
	} /* end render method */
	
	@Override
	protected void action() throws SlickException
	{ /* This method needs to be used in update */
		/* TODO: Check for sneak attacks, flat footed, etc. */
		ChooseTargetMessage tcm = new ChooseTargetMessage
				(
					TargetingSystem.TargetCount.SINGLE,
					TargetingSystem.TargetShape.CIRCLE,
					TargetingSystem.TargetSelection.SELECTED,
					false,
					character.getCoordinate(),
					character.getWeaponReach(),
					this
				);
		ts.chooseTarget(tcm);
		
	} /* end action method */
	
	@Override
	public void obtainTarget(TargetSelectedMessage tsm) throws SlickException
	{ 
		Dice attackRoll = new Dice(character.getAttackDie().size);
		Dice damageRoll = new Dice(character.getDamageDie().size);
		int attack = attackRoll.roll(1);				/* This variable will hold the roll and the sum of the attack bonuses */
		int damage = damageRoll.roll(1);				/* This variable will hold the roll and sum of the damage bonuses */
		
		/* create the combat message that will take care of this ability */
		Integer[] target = null;
		if (tsm.getTargets() != null )
		{
			target = new Integer[1];		/* There is only ever one target */
			target[0] = tsm.getTargets()[0].getCharacterID();
		}
		
		int[] body = new int[I_StandardAttack.BODY_SIZE];
		body[I_StandardAttack.ATTACK_ROLL] = attack;
		body[I_StandardAttack.DAMAGE_ROLL] = damage;
		
		CombatMessage cm = new CombatMessage
				(
					character.getCharacterID(),
					target,
					CombatSystem.ActionType.STANDARD,
					CombatSystem.Action.STANDARD_ATTACK,
					body
				);
		done = true;
		sendToInterpreter(cm);
	} /* end obtainTarget method */
	
} /* end StandardAttack method */
