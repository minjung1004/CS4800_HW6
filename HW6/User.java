import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class User implements IterableByUser {
    private ChatServer mediator;
    protected String name;
    private ChatHistory chatHistory;

    private Set<String> blockedUsers;

    public User(ChatServer mediator, String name) {
        this.mediator = mediator;
        this.name = name;
        this.chatHistory = new ChatHistory();
        this.blockedUsers = new HashSet<>();
    }

    public String getName(){
        return name;
    }

    public boolean sendMessage(List<String> receivers, String messageContent) {
        Message message = new Message(name, receivers, messageContent);
        mediator.sendMessage(this, receivers, messageContent);
        chatHistory.addMessage(message);
        return true;
    }

    public void recieveMessage(Message message) {
        if(!blockedUsers.contains(message.getSender())){
            chatHistory.addMessage(message);
            System.out.println(name + " recieved message from " + message.getSender());
        }else {
            System.out.println(name + " has blocked messages from " + message.getSender());
        }

    };

    public void undoLastMessage(MessageMemento messageMemento){
        if(messageMemento != null){
            Message lastMessage = chatHistory.getLastMessage();
            lastMessage.restoreFromMemento(messageMemento);
            chatHistory.removeLastMessage();
            System.out.println(name + " undid last message: " + lastMessage.getMessageContent());
        }else{
            System.out.println(name + " has no message to undo.");
        }
    }

    public void blockUsers(String blockUser){
        blockedUsers.add(blockUser);
        System.out.println(name + " has blocked " + blockUser);
    }

    public Set<String> getBlockedUsers() {
        return blockedUsers;
    }

    public void unblockUsers(String unblockUser){
        blockedUsers.remove(unblockUser);
        System.out.println(name + " has unblocked " + unblockUser);
    }


    public ChatHistory getChatHistory(){
        return chatHistory;
    }

    public void viewChatHistory(String name){
        List<Message> userMessages = getChatHistory().getMessagesByUser(name);
        System.out.println("Chat history for user: " + name);
        for (Message message : userMessages) {
            System.out.println("Sender: " + message.getSender());
            System.out.println("Receivers: " + message.getRecievers());
            System.out.println("Message Contents: " + message.getMessageContent());
            System.out.println("Timestamp: " + message.getTimestamp() + " ms");
            System.out.println();
        }
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith){
        return chatHistory.iterator(userToSearchWith);
    }
}
