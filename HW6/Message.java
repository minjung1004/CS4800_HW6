import java.util.List;

public class Message {
    private String sender;
    private List<String> recievers;
    private long timestamp;
    private String messageContent;

    public Message(String sender, List<String> recievers, String messageContent){
        this.sender = sender;
        this.recievers = recievers;
        this.timestamp = System.currentTimeMillis();
        this.messageContent = messageContent;
    }

    public MessageMemento createMemento(){
        return new MessageMemento(messageContent, timestamp);
    }

    public void restoreFromMemento(MessageMemento memento){
        this.messageContent = memento.getContent();
        this.timestamp = memento.getTimestamp();
    }

    public String getSender() {
        return sender;
    }

    public List<String> getRecievers(){
        return recievers;
    }

    public long getTimestamp(){
        return timestamp;
    }

    public String getMessageContent(){
        return messageContent;
    }

}
