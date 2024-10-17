import java.util.ArrayList;

public class Forum {
    String forumName;
    ArrayList<Member> forumMembers=new ArrayList<>();
    ArrayList<Message> forumMessages=new ArrayList<>();

    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

    public Forum(String name){
        setForumName(name);
    }

    public void addMember(Member member){
        forumMembers.add(member);
    }
    public void addMessage(Message message){
        forumMessages.add(message);
    }

    public ArrayList<Member> getForumMembers() {
        return forumMembers;
    }

    public void setForumMembers(ArrayList<Member> forumMembers) {
        this.forumMembers = forumMembers;
    }

    public ArrayList<Message> getForumMessages() {
        return forumMessages;
    }

    public void setForumMessages(ArrayList<Message> forumMessages) {
        this.forumMessages = forumMessages;
    }

    public static boolean IsForumNameOk(String name){
        for(Forum forum:Admin.getForums()){
            if(forum.getForumName().equals(name)){
                return false;
            }
        }
        return true;
    }
}
