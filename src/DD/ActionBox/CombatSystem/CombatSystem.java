package DD.ActionBox.CombatSystem;

import DD.Message.CombatMessage;

/*****************************************************************************************************
 * CombatSystem will be the Controller for DD combat. DD Combat will interpret the CombatMessage
 * sent by the Ability class and apply the appropriate action to the provided Character or Map.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class CombatSystem 
{
	/************************************ Class Attributes *************************************/
	
	/************************************ Class Methods *************************************/
	public CombatSystem()
	{
		
	} /* end constructor */
	
	public CombatMessage interpreter(CombatMessage cm)
	{/* Interpreter will take in all combat related messages
	 	and "interpret" or process them. It will decide on 
	 	actions should be taken and how to process said actions.*/
		CombatMessage returner = null;
		
		return (returner);
	} /* end the interpreter method */
	
} /* end CombatSystem class */
