import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ChatServer server = new ChatServer();

        User user1 = new User(server, "Ollie");
        User user2 = new User(server, "Twinkle");
        User user3 = new User(server, "Theo");

        server.registerUser(user1);
        server.registerUser(user2);
        server.registerUser(user3);

        List<String> recievers = new ArrayList<>();
        recievers.add("Twinkle");
        recievers.add("Theo");

        user1.sendMessage(recievers, "Hello everyone!");
        user2.sendMessage(Collections.singletonList("Ollie"), "Hi Ollie!");

        // Iterate over messages sent or received by User 1
        Iterator<Message> user1MessagesIterator = user1.iterator(user1);
        while (user1MessagesIterator.hasNext()) {
            Message message = user1MessagesIterator.next();
            System.out.println("Sender: " + message.getSender());
            System.out.println("Receivers: " + message.getRecievers());
            System.out.println("Message Content: " + message.getMessageContent());
            System.out.println("Timestamp: " + message.getTimestamp() + " ms");
            System.out.println();
        }

//        MessageMemento messageMemento = null;
//        // 1. Users can send messages to one or more other users through the chat server.
//        if (user1.sendMessage(recievers, "Hello everyone!")) {
//            messageMemento = user1.getChatHistory().getLastMessage().createMemento();
//        }
//
//        //4. Users can receive messages from other users and view the chat history for a specific user.
//        user2.sendMessage(Collections.singletonList("Theo"), "How are you?");
//        user2.viewChatHistory("Theo");
//
//        //2. Users can undo the last message they sent using the Memento design pattern.
//        if (messageMemento != null) {
//            user1.undoLastMessage(messageMemento);
//        }
//
//        //3. Users can block messages from specific users using the Mediator design pattern.
//        user2.blockUsers("Theo");
//        user1.unblockUsers("Theo");

    }
}