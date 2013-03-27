package DD.Network;

import java.io.IOException;
import java.net.Socket;

import DD.Message.NetworkMessage;

/*****************************************************************************************************
* Listener will be used to listen for messages from the Server or client. This will provide the basic 
* tools used by ServerListener and ClientListener.
* 
* Listener will need to utilize ServerSocket
* 
* @author Carlos Vasquez
******************************************************************************************************/

public abstract class Listener extends NetworkSocket
{	
	/************************************ Class Methods *************************************/
	public Listener(Socket socket)
	{
		super(socket);
	} /* end Listener constructor */
	
	public void run() {} /* end run method */
	
	protected NetworkMessage getSocketMessage()
	{
		NetworkMessage message = null;
		
		try
		{
			message = (NetworkMessage) input.readObject();
		} /* end try */
		catch(IOException e)
		{
			e.printStackTrace();
		} /* end catch */
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		} /* end catch */
		
		return(message);
	} /* end getSocketMessage method */

} /* end Listener class */
