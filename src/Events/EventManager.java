package Events;

import java.util.LinkedList;

public class EventManager {
    private static EventManager instance;
    private LinkedList<TopicSubscriber> subscriberList;

    private EventManager(){
        subscriberList = new LinkedList<>();
    }

    private static EventManager getInstance(){
        if(instance == null){
            instance = new EventManager();
        }
        return instance;
    }

    public void subscribe(TopicSubscriber subscriber){
        this.subscriberList.add(subscriber);
    }
}
