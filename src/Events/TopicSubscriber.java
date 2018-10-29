package Events;

import Message.Topic;

public interface TopicSubscriber {
    public void receiveTopic(Topic topic);
}
