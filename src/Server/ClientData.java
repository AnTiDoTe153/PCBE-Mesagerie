package Server;

import Client.Client;
import Message.SimpleMessage;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class ClientData {
    private static int maxLength = 10;
    private static Semaphore mutex = new Semaphore(1, true);
    private Client client;
    private LinkedList<SimpleMessage> messageQueue;

    public ClientData(Client client){
        this.client = client;
        this.messageQueue = new LinkedList<SimpleMessage>();
    }

    public Client getClient(){
        return client;
    }

    public static void setMaxLength(int maxLength){
        try{
            mutex.acquire();
            ClientData.maxLength = maxLength;
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            mutex.release();
        }
    }

    public synchronized void addMessageToQueue(SimpleMessage message){
        int maxLengthValue = 0;
        try{
            mutex.acquire();
            maxLengthValue = maxLength;
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            mutex.release();
        }

        if(this.messageQueue.size() < maxLengthValue){
            this.messageQueue.addFirst(message);
        }
    }

    public synchronized LinkedList<SimpleMessage> popMessagesFromQueue(){
        LinkedList<SimpleMessage> resultList = new LinkedList<>();

            while(!messageQueue.isEmpty()){
                resultList.add(messageQueue.removeLast());
            }

        return resultList;
    }
}
