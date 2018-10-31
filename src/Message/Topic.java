package Message;

import Message.Message;

import java.util.Date;

public class Topic extends Message {

    private static String MESSAGE_TYPE = "Topic";
    private String tag;
    private long lifeSpan;
    private Date creationDate;

    public Topic(String message, String tag, int lifeSpan){
        super(message, MESSAGE_TYPE);
        this.tag = tag;
        this.lifeSpan = lifeSpan * 1000 * 3600;
        this.creationDate = new Date();
    }

    public void displayMessage(){
        System.out.println("[" + this.getType() + "]" + this.getMessageText());
    }

    public String getTag() {
        return tag;
    }

    public long getLifeSpan() {
        return lifeSpan;
    }

    public Date getCreationDate(){ return creationDate; }
}
