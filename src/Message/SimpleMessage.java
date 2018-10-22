package Message;

import Message.Message;

public class SimpleMessage extends Message {
    private final static String MESSAGE_TYPE = "SimpleMessage";
    private String userId;

    public SimpleMessage(String message, String userId){
        super(message, MESSAGE_TYPE);
        this.userId = userId;
    }

    public void displayMessage(){
        System.out.println("[" + this.getType() + "]" + this.getMessageText());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
