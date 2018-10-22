package Message;

import Message.Message;

import java.util.Date;

public class Topic extends Message {

    private static String MESSAGE_TYPE = "Topic";
    private String tag;
    private Date expirationDate;

    public Topic(String message, String tag, Date expirationDate){
        super(message, MESSAGE_TYPE);
        this.tag = tag;
        this.expirationDate = expirationDate;
    }

    public void displayMessage(){
        System.out.println("[" + this.getType() + "]" + this.getMessageText());
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
