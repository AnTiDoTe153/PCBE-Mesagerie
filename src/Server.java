import java.util.LinkedList;

public class Server implements Receiver, Sender{
	private static LinkedList<Message> messageQueue;
	private static Server instance;
	
	private Server() {
		messageQueue = new LinkedList<Message>();
	}
	
	public static Server getInstance() {
		if(instance == null) {
			instance = new Server();
		}
		return instance;
	}
	
	//Resolve consumer-producer related problems

	@Override
	public synchronized void send(Message message) {
		// TODO Auto-generated method stub
		// Only one message can be sent at a time
		
	}

	@Override
	public synchronized Message receive() {
		// TODO Auto-generated method stub
		// Only one message can be received and added to the queue at a time
		return null;
	}
}
