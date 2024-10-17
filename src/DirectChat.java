public class DirectChat extends Chat{
    private Member firstMember;
    private Member secondMember;

    public DirectChat(Member firstMember,Member secondMember){
        setFirstMember(firstMember);
        setSecondMember(secondMember);
    }

    public Member getFirstMember() {
        return firstMember;
    }

    public void setFirstMember(Member firstMember) {
        this.firstMember = firstMember;
    }

    public Member getSecondMember() {
        return secondMember;
    }

    public void setSecondMember(Member secondMember) {
        this.secondMember = secondMember;
    }
}
