package Server;

import Client.Client;
import Events.EventManager;
import Events.TopicSubscriber;
import Interfaces.Sender;
import Message.SimpleMessage;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

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
			topicList.stream().forEach(topic -> eventManager.publishTopic(topic));
			clientMap.entrySet().stream().forEach(item -> {
				ClientData data = item.getValue();
				Client client = data.getClient();
				SimpleMessage message = data.popMessageFromQueue();

				client.receive(message);
			});
		}
	}

	public void subscribeTopic(TopicSubscriber subscriber, String tag){
		this.eventManager.subscribe(subscriber, tag);
	}



	public void setup(Date maxDate, int maxQueue){
		this.maxDateExpiration = maxDate;
		this.maxQueueLength = maxQueue;
	}

	public void publishTopic(Topic topic){
		topicList.add(topic);
	}

	public void logIn(Client client){
		ClientData clientData = new ClientData(client);
		clientMap.put(client.getUsername(), clientData);
	}

	public void logOut(Client client){
		clientMap.remove(client.getUsername());
	}

	@Override
	public void send(SimpleMessage message) {
		if(clientMap.containsKey(message.getReceiverUserId())){
			ClientData clientData = clientMap.get(message.getReceiverUserId());
			clientData.addMessageToQueue(message);
		}
	}

	public void setServerIsOn(boolean serverIsOn){
		this.serverIsOn = serverIsOn;
	}

}
