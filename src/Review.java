import java.util.ArrayList;

public class Review {
    private String text;
    private IsSpoiler isSpoiler;
    private ArrayList<IsHelpful> membersOpinions=new ArrayList<>();
    private ArrayList<Like> likes=new ArrayList<>();
    private Member member;
    private Movie movie;
    private Review reReview=null;

    public Review(String text, Member member,Movie movie,IsSpoiler isSpoiler){
        setText(text);
        setMember(member);
        setMovie(movie);
        setIsSpoiler(isSpoiler);
    }

    public Review getReReview() {
        return reReview;
    }

    public void setReReview(Review review) {
        this.reReview = review;
    }

    public ArrayList<Like> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Like> likes) {
        this.likes = likes;
    }

    public void addHelpfulOrNot(IsHelpful isHelpful){
        membersOpinions.add(isHelpful);
    }

    public ArrayList<IsHelpful> getMembersOpinions() {
        return membersOpinions;
    }

    public void setMembersOpinions(ArrayList<IsHelpful> membersOpinions) {
        this.membersOpinions = membersOpinions;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public IsSpoiler getIsSpoiler() {
        return isSpoiler;
    }

    public void setIsSpoiler(IsSpoiler isSpoiler) {
        this.isSpoiler = isSpoiler;
    }

    public void showReviewDetails(){
        System.out.println("movie:  "+getMovie().getMovieName());
        System.out.println("member who wrote:  "+getMember().getUsername());
        if(getIsSpoiler().equals(IsSpoiler.YES)){
            System.out.println("this review has spoiler alert");
        }
        else{
            System.out.println("this review doesnt have spoiler alert");
        }
        int helpful=0;
        int notHelpful=0;
        for(IsHelpful isHelpful:getMembersOpinions()){
            if(isHelpful.equals(IsHelpful.YES)){
                helpful++;
            }
            else{
                notHelpful++;
            }
        }
        if(helpful>=notHelpful){
            System.out.println("this review is helpful");
        }
        else{
            System.out.println("this review is not helpful");
        }
        if(reReview!=null){
            reReview.showReviewDetails();
        }
        System.out.println(getText());
    }
}
