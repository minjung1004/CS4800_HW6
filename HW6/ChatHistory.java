import java.util.*;

public class ChatHistory implements IterableByUser{
    private List<Message> messages;

    public ChatHistory() {
        this.messages = new ArrayList<>();
    }

    public void addMessage(Message message){
        messages.add(message);
    }

    public Message getLastMessage(){
        if(!messages.isEmpty()){
            return messages.get(messages.size() - 1);
        }
        return null;
    }

    public void removeLastMessage(){
        if(!messages.isEmpty()){
            messages.remove(messages.size() - 1);
        }
    }

    public List<Message> getMessagesByUser(String name){
        List<Message> userMessages = new ArrayList<>();
        for(Message message : messages) {
            if(message.getSender().equals(name) || message.getRecievers().contains(name)){
                userMessages.add(message);
            }
        }
        return userMessages;
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith){
        return new searchMessagesByUser(userToSearchWith, messages);
    }

    private class searchMessagesByUser implements Iterator<Message> {
        private User userToSearchWith;
        private ListIterator<Message> iterator;

        public searchMessagesByUser(User userToSearchWith, List<Message> messages){
            this.userToSearchWith = userToSearchWith;
            this.iterator = messages.listIterator();
        }

        @Override
        public boolean hasNext(){
            while (iterator.hasNext()){
                Message message = iterator.next();
                if(message.getSender().equals(userToSearchWith.getName()) || message.getRecievers().contains((userToSearchWith.getName()))){
                    iterator.previous();
                    return true;
                }
            }
            return false;
        }

        @Override
        public Message next() {
            if(hasNext()){
                return iterator.next();
            }else {
                throw new NoSuchElementException("No more message by user: " + userToSearchWith.getName());
            }
        }
    }
}
