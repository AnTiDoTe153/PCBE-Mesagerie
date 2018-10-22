package Client;

import Events.TopicSubscriber;
import Interfaces.Receiver;
import Message.Message;
import Server.Server;

public class Client implements Receiver, TopicSubscriber {
    private Server server;


    public Client(){
        this.server = Server.getInstance();
    }

    @Override
    public void receive(Message message) {
        return;
    }

    public void receiveTopic(){

    }
}
