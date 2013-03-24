package DD.Network;

import DD.Message.Message;
import DD.Message.NetworkMessage;
import DD.Network.Client.ClientSystem;
import DD.Network.Server.ServerSystem;
import DD.System.DDSystem;


/*****************************************************************************************************
* NetworkSystem will create a singleton and be the interface through which GameSystem and ChatSystem 
* (along with any other systems) will communicate with ClientSystem or ServerSystem based on whether
* the user is a client or a server.
* 
* @author Carlos Vasquez
******************************************************************************************************/

public class NetworkSystem implements Network
{
	/************************************ Class Constants *************************************/
	private static int I = 0;
	public static enum NetworkType
	{
		CLIENT(),
		SERVER();
		
	} /* end Action enum */
	
	/************************************ Class Attributes *************************************/
	private NetworkType networkType;
	private Network network;
	
	/************************************ Class Methods *************************************/
	public NetworkSystem() {}
	
	@Override
	public boolean sendMessage(int sender, int receiver, Message message)
	{
		return network.sendMessage(sender, receiver, message);
	} /* end sendMessage method */
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	@Override
	public boolean getMessage(int listenerID, NetworkMessage message) 
	{ /* Get message from client. (This is how client passes messages to NetworkSyste/ServerSystem to interpret). */
		return network.getMessage(listenerID, message);
		
	} /* end getMessage method */
	
	public int getNetID()
	{
		Integer id = null;
		switch(networkType)
		{
			case SERVER:
				id = Network.GM_USER_ID;
				break;
			case CLIENT:
				id = ((ClientSystem)network).getClientID();
				break;
		} /* end switch */

		return id;
	} /* end getUserID */
	
	/******************************************************************************
	 ******************************* Setter Methods *******************************
	 ******************************************************************************/
	public void setNetworkType(NetworkType type)
	{
		this.networkType = type;
		switch(networkType)
		{
			case SERVER:
				network = new ServerSystem();
				break;
			case CLIENT:
				network = new ClientSystem();
				break;
		} /* end switch */

	} /* end setNetworkType */
	
} /* end Network class */
