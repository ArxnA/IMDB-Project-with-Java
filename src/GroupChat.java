import java.util.ArrayList;

public class GroupChat extends Chat{
    private String groupName;
    private Member Admin;
    private ArrayList<Member> groupMembers=new ArrayList<>();

    public GroupChat(Member admin,String groupName){
        setAdmin(admin);
        getGroupMembers().add(admin);
        setGroupName(groupName);
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Member getAdmin() {
        return Admin;
    }

    public void setAdmin(Member admin) {
        Admin = admin;
    }

    public ArrayList<Member> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(ArrayList<Member> groupMembers) {
        this.groupMembers = groupMembers;
    }
}
