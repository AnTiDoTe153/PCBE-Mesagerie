package Message;

public abstract class Message {
	private String messageText;
	private String type;

	public Message(String messageText, String type){
		this.messageText = messageText;
		this.type = type;
	}

	public abstract void displayMessage();

	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public String getType() {
		return type;
	}
}
