package Main;

import Client.Client;
import Message.SimpleMessage;
import Server.Server;
import org.omg.PortableServer.SERVANT_RETENTION_POLICY_ID;

public class SecondClientThread implements Runnable{
    private Server server;

    public SecondClientThread(Server server){
        this.server = server;
    }

    @Override
    public void run() {
        Server server = Server.getInstance();
        Client client = new Client("Arin cel divin");

        server.logIn(client);
        SimpleMessage message = new SimpleMessage("cmf?", client.getUsername(), "AnTiDoTe");
        server.send(message);
        server.subscribeTopic(client, "asmr");
    }
}
