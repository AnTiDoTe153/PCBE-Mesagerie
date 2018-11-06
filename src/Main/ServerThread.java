package Main;
import Server.Server;

public class ServerThread implements Runnable{
    private Server server;

    public ServerThread(Server server){
        this.server = server;
    }

    public void run() {

        server.serve();

    }
}
