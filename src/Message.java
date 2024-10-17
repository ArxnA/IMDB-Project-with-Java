import java.util.ArrayList;

public class Message {
    private Member sender;
    private String text;
    private ArrayList<Like> likes=new ArrayList<>();


    public  Message(String text,Member member){
        setSender(member);
        setText(text);
    }

    public ArrayList<Like> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Like> likes) {
        this.likes = likes;
    }

    public Member getSender() {
        return sender;
    }

    public void setSender(Member sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void showMessageDetails(){
        System.out.println("sender: "+getSender().getUsername());
        System.out.println("text: "+getText());
        System.out.println("likers:");
        for(Like like:getLikes()){
            System.out.println(like.getLiker().getUsername());
        }
    }
}
