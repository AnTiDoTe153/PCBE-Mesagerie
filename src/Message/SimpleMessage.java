package Message;

import Message.Message;

public class SimpleMessage extends Message {
    private final static String MESSAGE_TYPE = "SimpleMessage";
    private String receiverUserId;
    private String senderUserId;

    public SimpleMessage(String message, String senderUserId, String receiverUserId){
        super(message, MESSAGE_TYPE);
        this.receiverUserId = receiverUserId;
        this.senderUserId = senderUserId;
    }

    public void displayMessage(){
        System.out.println("[" + this.getType() + "]" + this.getMessageText());
    }

    public String getReceiverUserId() {
        return receiverUserId;
    }

    public String getSenderUserId() {
        return senderUserId;
    }

}
