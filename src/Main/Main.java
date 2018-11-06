package Main;

import Server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = Server.getInstance();

        FirstClientThread firstClient = new FirstClientThread(server);
        SecondClientThread secondClient = new SecondClientThread(server);
        ServerThread serverThread = new ServerThread(server);


        System.out.println("Starting the threads");

        try{
            System.out.println("Started server thread");
            (new Thread(serverThread)).start();
            Thread.sleep(1000 * 3);

            System.out.println("Started first client thread");
            (new Thread(firstClient)).start();
            Thread.sleep(1000 * 3);

            System.out.println("Started second client thread");
            (new Thread(secondClient)).start();
            Thread.sleep(1000 * 3);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
