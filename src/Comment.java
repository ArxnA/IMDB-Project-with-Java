public class Comment {
    private String text;
    private Movie movie;
    private Member member;

    public Comment(String text,Member member,Movie movie){
        setText(text);
        setMember(member);
        setMovie(movie);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void showCommentDetails() {
        System.out.println("the comment text is:  "+getText());
        System.out.println("the movie that comment is for:  "+getMovie().getMovieName());
        System.out.println("the member that wrote the comment:  "+getMember().getUsername());
    }
}
