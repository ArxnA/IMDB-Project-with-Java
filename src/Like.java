public class Like {
    private Member liker;
    private Object content;

    public Like(Member member, Object content){
        setContent(content);
        setLiker(liker);
    }

    public Member getLiker() {
        return liker;
    }

    public void setLiker(Member liker) {
        this.liker = liker;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
