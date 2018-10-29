package Server;

import Events.EventManager;
import Events.TopicSubscriber;
import Interfaces.Sender;
import Message.Message;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

import Message.Topic;

public class Server implements Sender {
	private HashMap<String, ClientData> clientMap;
	private LinkedList<Topic> topicList;
	private Date maxDateExpiration;
	private int maxQueueLength;
	private EventManager eventManager;
	private static Server instance;
	private volatile boolean serverIsOn;

	
	private Server() {
		clientMap = new HashMap<>();
		topicList = new LinkedList<Topic>();
		eventManager = EventManager.getInstance();
		serverIsOn = true;
	}
	
	public static Server getInstance() {
		if(instance == null) {
			instance = new Server();
		}
		return instance;
	}

	private void serve(){
		while(serverIsOn){
			//do server stuff
		}
	}

	public void subscribeTopic(TopicSubscriber subscriber, String tag){
		this.eventManager.subscribe(subscriber, tag);
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

	public void setServerIsOn(boolean serverIsOn){
		this.serverIsOn = serverIsOn;
	}

}
