package Events;

import Message.Topic;

import java.util.HashMap;
import java.util.LinkedList;

public class EventManager {
    private static EventManager instance;
    private HashMap<String, LinkedList<TopicSubscriber>> subscriberMap;

    private EventManager(){
        subscriberMap = new HashMap<>();
    }

    public static EventManager getInstance(){
        if(instance == null){
            instance = new EventManager();
        }
        return instance;
    }

    public void publishTopic(Topic topic){
        if(subscriberMap.containsKey(topic.getTag())){
            subscriberMap.get(topic.getTag()).stream()
                    .forEach(sub -> {sub.receiveTopic(topic);});
        }
    }

    public void subscribe(TopicSubscriber subscriber, String topicType){
        if(!subscriberMap.containsKey(topicType)){
            subscriberMap.put(topicType, new LinkedList<>());
        }
        subscriberMap.get(topicType).addFirst(subscriber);
    }
}


