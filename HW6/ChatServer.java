import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatServer {
    private Map<String, User> users;

    public ChatServer(){
        this.users = new HashMap<>();
    }

    public void registerUser(User user){
        users.put(user.name, user);
    }

    public void unregisterUser(User user){
        users.remove(user.name);
    }

    public void sendMessage(User sender, List<String> receivers, String messageContent){
        Message message = new Message(sender.name, receivers, messageContent);
        for(String receiver : receivers) {
            User user = users.get(receiver);
            if(user != null){
                user.recieveMessage(message);
            }else {
                System.out.println("Receiver " + receiver + " not found.");
            }
        }
    }
}
