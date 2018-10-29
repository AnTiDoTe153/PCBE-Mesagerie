package Client;

import Message.Topic;
import Events.TopicSubscriber;
import Interfaces.Receiver;
import Message.Message;
import Server.Server;

public class Client implements Receiver, TopicSubscriber {
    private Server server;
    private String username;


    public Client(String username){
        this.username = username;
        this.server = Server.getInstance();
    }

    @Override
    public void receive(Message message) {
        return;
    }

    @Override
    public void receiveTopic(Topic topic) {

    }
}
