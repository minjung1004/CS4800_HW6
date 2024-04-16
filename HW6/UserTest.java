import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private ChatServer mockMediator;
    private User user1;
    private User user2;
    private User user3;
    private List<String> receivers;

    @BeforeEach
    public void setUp(){
        mockMediator = new ChatServer();

        user1 = new User( mockMediator, "Alice");
        user2 = new User( mockMediator, "Bob");
        user3 = new User( mockMediator, "Cher");

        mockMediator.registerUser(user1);
        mockMediator.registerUser(user2);
        mockMediator.registerUser(user3);

        receivers = new ArrayList<>();
        receivers.add("Bob");
        receivers.add("Cher");
    }

    @Test
    @DisplayName("Testing Send Message")
    public void testSendMessage() {
        assertTrue(user1.sendMessage(receivers, "Hello, Bob!"));
    }

    @Test
    @DisplayName("Testing Blocking Users")
    public void testBlockUsers() {
        user2.blockUsers("Cher");
        assertTrue(user2.getBlockedUsers().contains("Cher"));
    }

    @Test
    @DisplayName("Testing Unblocking Users")
    public void testUnblockUsers() {
        user2.unblockUsers("Cher");
        assertFalse(user2.getBlockedUsers().contains("Cher"));
    }

    @Test
    @DisplayName("Testing Undo Last Message")
    public void testUndoLastMessage(){
        Message message = new Message("Bob", Collections.singletonList("Alice"), "Hello, Alice!");
        user1.getChatHistory().addMessage(message);
        MessageMemento messageMemento = new MessageMemento(message.getMessageContent(), message.getTimestamp());
        user1.undoLastMessage(messageMemento);
        assertEquals(0, user1.getChatHistory().getMessagesByUser("ALice").size());
    }


}