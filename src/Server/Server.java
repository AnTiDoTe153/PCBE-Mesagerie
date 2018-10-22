package Server;

import Client.ClientData;
import Interfaces.Sender;
import Message.Message;
import java.util.Date;
import java.util.HashMap;

public class Server implements Sender {
	private HashMap<String, ClientData> clientMap;
	private Date maxDateExpiration;
	private int maxQueueLength;
	private static Server instance;
	
	private Server() {
		clientMap = new HashMap<>();
	}
	
	public static Server getInstance() {
		if(instance == null) {
			instance = new Server();
		}
		return instance;
	}

	public void serve(){
		// do the server logic here
	}

	public boolean setup(Date maxDate, int maxQueue){
		this.maxDateExpiration = maxDate;
		this.maxQueueLength = maxQueue;

		return true;
	}

	@Override
	public synchronized void send(Message message) {
		// TODO Auto-generated method stub
		// Only one message can be sent at a time
		
	}

}
