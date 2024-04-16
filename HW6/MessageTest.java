import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    @DisplayName("Testing creating memento")
    public void testCreateMemento() {
        Message testMessage = new Message("Alice", List.of("Bob", "Cher"), "Hello, Bob and Cher!");
        MessageMemento testMemento = testMessage.createMemento();

        assertEquals("Hello, Bob and Cher!", testMemento.getContent());
        assertTrue(System.currentTimeMillis() >= testMemento.getTimestamp());
    }

    @Test
    @DisplayName("Testing restoring from memento")
    public void testRestoreFromMemento() {
        Message testMessage = new Message("Alice", List.of("Bob", "Cher"), "Hello, Bob and Cher!");
        MessageMemento testMemento = testMessage.createMemento();

        // Add 1000 milliseconds to ensure the timestamp is different
        testMessage.restoreFromMemento(new MessageMemento("Hi, Bob and Cher!", System.currentTimeMillis() + 1000));
        testMessage.restoreFromMemento(testMemento);

        assertEquals("Hello, Bob and Cher!", testMessage.getMessageContent());
        assertTrue(System.currentTimeMillis() >= testMessage.getTimestamp());
    }

}