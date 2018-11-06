package Main;

import Message.Topic;
import Server.Server;
import Client.Client;

public class FirstClientThread implements Runnable{
    private Server server;


    public FirstClientThread(Server server){
        this.server = server;
    }
    @Override
    public void run() {
        Server server = Server.getInstance();
        Client client = new Client("AnTiDoTe");
        server.logIn(client);

        Topic topic = new Topic("Chestii trestii, vraja marii...", "asmr", 1);
        server.publishTopic(topic);

        server.subscribeTopic(client, "asmr");
    }
}
