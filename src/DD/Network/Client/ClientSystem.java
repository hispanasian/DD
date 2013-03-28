package DD.Network.Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import DD.Chat.ChatSystem;
import DD.CombatSystem.CombatSystem;
import DD.Message.Message;
import DD.Message.NetworkMessage;
import DD.Network.MessageQueue;
import DD.Network.Network;
import DD.Network.NetworkInterface;
import DD.Network.Client.Interpreter.*;

/*****************************************************************************************************
 * ClientSystem will be in charge of routing the messages received by the server and processing them.
 * Furthermore, it will send messages from the client and send them through the socket to the server.
 * 
 * The client will use interpreters which will hold the server logic for different kinds of messages.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class ClientSystem extends Network implements NetworkInterface 
{	
	/************************************ Class Attributes *************************************/
	private PeerTable peerList;
	private ClientInterpreter[] system = null;
	@SuppressWarnings("unused")
	private MessageQueue queue = null;			/* reference to MessageQueue thread. Will need to be cleaned up */
	private ClientListener listener = null;
	private ClientSender sender = null;
	private int clientID;						/* ID provided by server */
	private InetAddress serverIP = null;		/* ip address of the server */
	
	
	/************************************ Class Methods *************************************/
	public ClientSystem() 
	{
		peerList = new PeerTable();
		ClientInterpreter.setClientSystem(this);
		system = new ClientInterpreter[Message.NUM_OF_MESSAGES];
		system[Message.COMBAT_MESSAGE] = new I_CombatMessage();
		system[Message.CHAT_MESSAGE] = new I_ChatMessage();
		system[Message.INITIAL_MESSAGE] = new I_InitialMessage();
		system[Message.NEW_LISTENER_MESSAGE] = new I_NewListenerMessage();
		system[Message.ADD_USER_MESSAGE] = new I_AddUserMessage();
		
	} /* end ServerSystem constructor */
	
	public void interpret(int listenerID, NetworkMessage message)
	{
		/* Assume all messages are of correct type and legally formatted.
		 * In any case, messages are always given by the ClientListener */
		system[message.getType()].interpret(message);
	} /* end interpret */
	
	@Override
	public boolean sendMessage(int sender, int receiver, Message message)
	{
		/* Send message to Server. */
		NetworkMessage send = new NetworkMessage(sender, receiver, message);
		this.sender.sendMessage(send);
		return true;
	} /* end sendMessage method */
	
	public static boolean validMessage(int type)
	{
		/* Check to see if the message is supported by the system. Used only for getMessage */
		boolean valid = false;
		
		if 
		(
			type == Message.COMBAT_MESSAGE ||
			type == Message.CHAT_MESSAGE ||
			type == Message.INITIAL_MESSAGE ||
			type == Message.NEW_LISTENER_MESSAGE ||
			type == Message.ADD_USER_MESSAGE
		)
		{
			valid = true;
		} /* end if */
		
		return valid;
	} /* end validMessage method */
	

	@Override
	public void start() 
	{
		try 
		{
			sender = new ClientSender(new Socket(serverIP, Network.PORT));
		} /* end try */
		catch (IOException e) 
		{
			/* failure to connect */
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /* end catch */
		
	} /* end start method */

	@Override
	public void stop() 
	{
		// TODO Auto-generated method stub
		
	} /* end stop method */

	@Override
	public void terminate() 
	{
		// TODO Auto-generated method stub
		
	} /* end terminate method */
	
	/************************************ peerList related methods**********************************/
	public boolean addUser(int peerID, String username, InetAddress ip)
	{
		return peerList.addPeer(peerID, username, ip);
	} /* end addUser method */
	
	public boolean removePeer(int peerID)
	{
		return peerList.removePeer(peerID);
	} /* end removePeer method*/
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	@Override
	public boolean getMessage(int listenerID, NetworkMessage message)
	{
		boolean error = false;
		/* get message from a client. Check for validity and if valid, interpret. */
		if (validMessage(message.getType()) && message.getMessageType() == Message.NETWORK_MESSAGE) interpret(listenerID, message);
		else error = true;
		
		return error;
	} /* end getMessage method */
	
	public PeerTable getClientList()
	{
		return peerList;
	} /* end getUserList */
	
	public Integer getClientID()
	{
		return clientID;
	} /* end getUserList */
	
	public InetAddress getServerIP()
	{
		return serverIP;
	} /* end getServerIP method */
	
	/******************************************************************************
	 ******************************* Setter Methods *******************************
	 ******************************************************************************/
	public void setListener(ClientListener listener)
	{
		this.listener = listener;
	} /* end setListener method */
	
	public void setSender(ClientSender sender)
	{
		this.sender = sender;
	} /* end setSender method */
	
	public void setClientID(int clientID)
	{
		this.clientID = clientID;
	} /* end setSender method */
	
	public void setServerIP(InetAddress serverIP)
	{
		this.serverIP = serverIP;
	} /* end setServerIP method */
	
	public boolean setServerIP(String ip)
	{
		boolean returner = false;
		try {
			setServerIP(InetAddress.getByName(ip));
			returner = true;
		} /* end try */
		catch (UnknownHostException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /* end catch */
		
		return returner;
	} /* end setServerIP method */


	@Override
	public void setCombatSystem(CombatSystem cs) 
	{
		// TODO Auto-generated method stub
		
	} /* end setCombatSystem method */

	@Override
	public void setChatSystem(ChatSystem chat)
	{
		// TODO Auto-generated method stub
		
	} /* end setChatSystem method */
	
} /* end ServerSystem class */
