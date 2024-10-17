public class Rate {
    private Member memberWhoRate;
    private Movie movieThatRated;
    private int score;
    public Rate(Member member,int score,Movie movie){
        setScore(score);
        setMemberWhoRate(member);
        setMovieThatRated(movie);
    }

    public Movie getMovieThatRated() {
        return movieThatRated;
    }

    public void setMovieThatRated(Movie movieThatRated) {
        this.movieThatRated = movieThatRated;
    }

    public Member getMemberWhoRate() {
        return memberWhoRate;
    }

    public void setMemberWhoRate(Member memberWhoRate) {
        this.memberWhoRate = memberWhoRate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
