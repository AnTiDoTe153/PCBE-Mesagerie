package Server;

import Client.Client;
import Message.SimpleMessage;
import java.util.LinkedList;

public class ClientData {
    private Client client;
    private LinkedList<SimpleMessage> messageQueue;

    public ClientData(Client client){
        this.client = client;
        this.messageQueue = new LinkedList<SimpleMessage>();
    }

    public Client getClient(){
        return client;
    }

    public void addMessageToQueue(SimpleMessage message){
        this.messageQueue.addFirst(message);
    }

    public SimpleMessage popMessageFromQueue(SimpleMessage message){
        return this.messageQueue.removeLast();
    }
}
