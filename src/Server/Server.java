package Server;

import Client.Client;
import CustomSemaphores.AdjustableSemaphore;
import Events.EventManager;
import Events.TopicSubscriber;
import Interfaces.Sender;
import Message.SimpleMessage;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import Message.Topic;

public class Server implements Sender {
	private static final long DEFAULT_LIFE_SPAN = 3600 * 1000;
	private static final boolean DEFAULT_SERVER_STATE = true;

	private HashMap<String, ClientData> clientMap;
	private LinkedList<Topic> topicList;
	private long maxLifeSpan;
	private EventManager eventManager;
	private static Server instance;
	private volatile boolean serverIsOn;
	private Semaphore topicsLock;

	private Predicate<Topic> topicIsAlivePredicate = topic -> {
		Date now = new Date();
		Date creationDate = topic.getCreationDate();
		long currentLifeSpan = now.getTime() - creationDate.getTime();
		if(currentLifeSpan >= maxLifeSpan) {
			return false;
		}
		if(currentLifeSpan > topic.getLifeSpan()){
			return false;
		}
		return true;
	};
	
	private Server() {
		clientMap = new HashMap<>();
		topicList = new LinkedList<Topic>();
		eventManager = EventManager.getInstance();
		serverIsOn = DEFAULT_SERVER_STATE;
		maxLifeSpan = DEFAULT_LIFE_SPAN;
		topicsLock = new Semaphore(1, true);
	}
	
	public static Server getInstance() {
		if(instance == null) {
			instance = new Server();
		}
		return instance;
	}

	private void serve(){
		while(serverIsOn){
			//This part is for topics
			try{
				topicsLock.acquire();
				topicList = (LinkedList<Topic>)topicList.stream()
						.filter(topicIsAlivePredicate)
						.collect(Collectors.toList());
				topicList.stream().forEach(topic -> eventManager.publishTopic(topic));
			}catch(InterruptedException e){
				e.printStackTrace();
			}finally{
				topicsLock.release();
			}


			//This part is with message queue
			clientMap.entrySet().stream().forEach(item -> {
				ClientData data = item.getValue();
				Client client = data.getClient();
				LinkedList<SimpleMessage> messageList = data.popMessagesFromQueue();
				messageList.stream().forEach(message -> client.receive(message));
			});


			try{
				Thread.sleep(1000 * 10);
			}catch(InterruptedException e){
				e.printStackTrace();
			}

		}
	}

	public void subscribeTopic(TopicSubscriber subscriber, String tag){
		this.eventManager.subscribe(subscriber, tag);
	}

	public synchronized void setup(int maxLifeSpan, int maxLength){
		this.maxLifeSpan = maxLifeSpan;
		ClientData.setMaxLength(maxLength);
	}

	public void publishTopic(Topic topic){
		try{
			topicsLock.acquire();
			topicList.add(topic);
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			topicsLock.release();
		}


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
