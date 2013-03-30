package DD.Network.Server;

import java.net.Socket;
import DD.Message.NetworkMessage;
import DD.Message.NewListenerMessage;
import DD.Network.Listener;
import DD.Network.MessageQueue;
import DD.Network.NetworkSystem;

/*****************************************************************************************************
 * ServerListener will be a thread dedicated to Listening for messages from a client and giving the
 * message to MessageQueue to be sent to the Server.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class ServerListener extends Listener
{
	/************************************ Class Methods *************************************/
	public ServerListener(Socket socket) 
	{
		super(socket);
	} /* end ServerListener constructor */

	protected void alertNetwork()
	{
		this.message = new NetworkMessage(NetworkSystem.GM_USER_ID, this.socketID, new NewListenerMessage(this.socketID, this, this.socket.getInetAddress()));
		MessageQueue.getInstance().enqueuMessage(this.socketID, message);
	} /* end alertNetwork method */
	
} /* end ServerListener class */
