import java.util.ArrayList;

public class Chat {
    private ArrayList<Message> messages=new ArrayList<>();

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
