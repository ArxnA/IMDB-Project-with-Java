import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Movie {
    private String movieName;
    private String title;
    private String plotSummary;
    private ArrayList<String> posters=new ArrayList<>();
    private ArrayList<Comment> comments=new ArrayList<>();
    private ArrayList<String> trailers=new ArrayList<>();
    private ArrayList<Rate> rates=new ArrayList<>();
    private ArrayList<Review> reviews=new ArrayList<>();
    private ArrayList<PeopleAndTypeInMovie> Craftsmen=new ArrayList<>();
    private ArrayList<Like> likes=new ArrayList<>();
    private Genre movieGenre;
    private Language movieLanguage;
    private Date releaseDate;
    public Movie(){
    }

    public Movie(String movieName, Genre genre, Language language, Date date){
        setMovieName(movieName);
        setMovieGenre(genre);
        setMovieLanguage(language);
        setReleaseDate(date);
    }

    public Movie(String movieName, Genre genre, Language language){
        setMovieName(movieName);
        setMovieGenre(genre);
        setMovieLanguage(language);
    }
    public Movie(String movieName){
        setMovieName(movieName);
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
    public ArrayList<Like> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Like> likes) {
        this.likes = likes;
    }

    public Genre getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(Genre movieGenre) {
        this.movieGenre = movieGenre;
    }

    public Language getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(Language movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlotSummary() {
        return plotSummary;
    }

    public void setPlotSummary(String plotSummary) {
        this.plotSummary = plotSummary;
    }

    public ArrayList<String> getPosters() {
        return posters;
    }

    public void setPosters(ArrayList<String> poster) {
        this.posters = poster;
    }

    public ArrayList<String> getTrailers() {
        return trailers;
    }

    public void setTrailers(ArrayList<String> trailer) {
        this.trailers = trailer;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<PeopleAndTypeInMovie> getCraftsmen() {
        return Craftsmen;
    }

    public void setCraftsmen(ArrayList<PeopleAndTypeInMovie> craftsmen) {
        Craftsmen = craftsmen;
    }

    public void addRating(Rate Rates){
        int isOk=1;
        for(Rate rate:rates){
            if(rate.getMemberWhoRate().equals(Rates.getMemberWhoRate())){
                rate.setScore(Rates.getScore());
                isOk=0;
                break;
            }
        }
        if(isOk==0){
            System.out.println("you rated this movie! you cant rate again!");
        }
        else{
            rates.add(Rates);
        }
    }

    public void addReview(Review review){
        reviews.add(review);
    }
    public void deleteReview(Review review){
        reviews.remove(review);
    }

    public void addCraftsmen(PeopleAndTypeInMovie people){
        getCraftsmen().add(people);
    }

    public ArrayList<Rate> getRates() {
        return rates;
    }

    public void setRates(ArrayList<Rate> membersWhoRates) {
        this.rates = membersWhoRates;
    }

    public int getMovieScore(){
        int count=0;
        int rateSum=0;
        for(Rate rate:rates){
            rateSum+=rate.getScore();
            ++count;
        }
        if(count!=0){
            return rateSum/count;
        }
        return 0;
    }

    public void addPoster(String poster){
        posters.add(poster);
    }
    public void addTrailer(String trailer){
        posters.add(trailer);
    }

    public static void seeMostPopularMovies(){
        for(Movie movie:Admin.getAllMovies()){
            int score= movie.getMovieScore();
            if(score>=8.5){
                System.out.println(movie.getMovieName());
            }
        }
    }
    public static void seeGenreCategory(Genre movieGenre){
        for(Movie movie:Admin.getAllMovies()){
            if(movie.getMovieGenre().equals(movieGenre)){
                System.out.println(movie.getMovieName());
            }
        }
    }
    public static void seeLanguageCategory(Language movieLanguage){
        for(Movie movie:Admin.getAllMovies()){
            if(movie.getMovieLanguage().equals(movieLanguage)){
                System.out.println(movie.getMovieName());
            }
        }
    }
    public static void seeReleaseDateCategory(int year){
        for(Movie movie:Admin.getAllMovies()){
            if(movie.getReleaseDate().getYear()==year){
                System.out.println(movie.getMovieName());
            }
        }
    }

    public static void addToAllMovies(Movie movie){
        Admin.getAllMovies().add(movie);
    }

    public void editPoster(int whichPoster,String poster){
        posters.set(whichPoster,poster);
    }
    public void editTrailer(int whichTrailer,String trailer){
        trailers.set(whichTrailer,trailer);
    }
    public void editCraftsmen(int whichCraftsmen,PeopleAndTypeInMovie craftMan){
        Craftsmen.set(whichCraftsmen,craftMan);
    }

    public void showMovieDetails(){
        System.out.println("name: "+getMovieName());
        System.out.println("title: "+getTitle());
        System.out.println("genre: "+getMovieGenre());
        System.out.println("language: "+getMovieLanguage());
        System.out.println("release date: "+getReleaseDate());
        System.out.println("plot summary: "+getPlotSummary());
        System.out.println("rate: ");
        System.out.println(getMovieScore());
        System.out.println("posters: ");
        for(String poster:getPosters()){
            System.out.println(poster);
        }
        System.out.println("trailers: ");
        for(String trailer:getTrailers()){
            System.out.println(trailer);
        }
        System.out.println("craftsmen: ");
        for(PeopleAndTypeInMovie craftMan:getCraftsmen()){
            System.out.println(craftMan.getPeople());
            System.out.println("roll in this movie");
            for(PeopleType peopleType:craftMan.getTypeInMovie()){
                System.out.println(peopleType);
            }
        }
        //System.out.println(editSuggest.getOriginalMovie().getPosters()+" to "+editSuggest.getSuggestMovie().getPosters());
        //System.out.println(editSuggest.getOriginalMovie().getTrailers()+" to "+editSuggest.getSuggestMovie().getTrailers());
        //System.out.println(editSuggest.getOriginalMovie().getCraftsmen()+" to "+editSuggest.getSuggestMovie().getCraftsmen());

    }
    public static void whatToDoToAMovie(Movie chooseMovie, Scanner input,User user) {
        chooseMovie.showMovieDetails();
        ((Member) user).addToList(ListType.RECENTLYSEEN, chooseMovie);
        while (true) {
            System.out.println("press a to rate this movie\npress b to report this movie\npress c to add this movie to your personal list or your member list\npress d to see movie's reviews or write a review\npress e to see movie's comments or write a comment\npress f to see likes or like this movie\npress any other key to exit");
            String whichOne = input.nextLine();
            if (whichOne.equals("a")) {
                while (true) {
                    int score;
                    System.out.println("enter a number out of 10");
                    try {
                        score = Integer.parseInt(input.nextLine());
                    } catch (Exception e) {
                        System.out.println("enter the number!");
                        continue;
                    }
                    if (score <= 10 && score >= 0) {
                        chooseMovie.addRating(new Rate((Member) user, score, chooseMovie));
                        break;
                    } else {
                        System.out.println("you entered the wrong number!");
                    }
                }
            } else if (whichOne.equals("b")) {
                System.out.println("enter the report text");
                String text = input.nextLine();
                Admin.getReports().add(new Report(text, chooseMovie));
            } else if (whichOne.equals("c")) {
                System.out.println("which list do you want to add this movie to it?\npress a for favorites\npress b for watchlist\npress any other key to add to your personal list");
                String whichToChoose = input.nextLine();
                if (whichToChoose.equals("a")) {
                    ((Member) user).addToList(ListType.FAVORITES, chooseMovie);
                } else if (whichToChoose.equals("b")) {
                    ((Member) user).addToList(ListType.WATCHLIST, chooseMovie);
                } else {
                    System.out.println("enter the name of the personal list\n if you have one with this name it will be add ti it else it will make you a new one with this name!");
                    String name = input.nextLine();
                    ((Member) user).addMovieToPersonalList(name, chooseMovie);
                }
            } else if (whichOne.equals("d")) {
                System.out.println("press a to write a review and any other key to see movie reviews");
                if (input.nextLine().equals("a")) {
                    System.out.println("write your review's text");
                    String reviewText = input.nextLine();
                    System.out.println("does your review has spoil?\n press y for yes and any other key to no");
                    IsSpoiler isSpoiler = IsSpoiler.NO;
                    if (input.nextLine().equals("y")) {
                        isSpoiler = IsSpoiler.YES;
                    }
                    ((Member) user).writeReview(new Review(reviewText, (Member) user, chooseMovie, isSpoiler), chooseMovie);
                } else {
                    int reviewCount = 0;
                    for (Review review : chooseMovie.getReviews()) {
                        System.out.println(reviewCount);
                        ++reviewCount;
                        review.showReviewDetails();
                    }
                    System.out.println("choose a review if you want(enter the number of review or any other number to not");
                    int chooseReview;
                    try {
                        chooseReview = Integer.parseInt(input.nextLine());
                    } catch (Exception e) {
                        System.out.println("enter a number!");
                        continue;
                    }
                    if (chooseReview <= chooseMovie.getReviews().size()) {
                        Review reviewThatChosen = chooseMovie.getReviews().get(chooseReview);
                        System.out.println("press a to report this review\npress b to mark as is helpful or not\npress c to like this review or see likes\npress d to reReview this review\npress any other key to do nothing");
                        String nextChoose = input.nextLine();
                        if (nextChoose.equals("a")) {
                            System.out.println("enter the report text");
                            String reportText = input.nextLine();
                            Admin.getReports().add(new Report(reportText, reviewThatChosen));
                        } else if (nextChoose.equals("b")) {
                            System.out.println("press a if it was helpful\nany other key if it was not helpful\n");
                            if (input.nextLine().equals("a")) {
                                reviewThatChosen.getMembersOpinions().add(IsHelpful.YES);
                            } else {
                                reviewThatChosen.getMembersOpinions().add(IsHelpful.NO);
                            }
                        }
                        else if (nextChoose.equals("c")){
                            System.out.println("press a to like this review or any other key to see likes");
                            if(input.nextLine().equals("a")){
                                reviewThatChosen.getLikes().add(new Like(((Member) user),chooseMovie ));
                            }
                            else{
                                for(Like like:reviewThatChosen.getLikes()){
                                    System.out.println(like.getLiker().getName());
                                    if(like.getContent() instanceof Review){
                                        System.out.println("liked: ");
                                        ((Review) like.getContent()).showReviewDetails();
                                    }
                                }
                            }
                        }
                        else if (nextChoose.equals("d")){
                            System.out.println("enter your text!");
                            String text= input.nextLine();
                            System.out.println("press a if your review has spoiler or any other key if it has not");
                            IsSpoiler isSpoiler=IsSpoiler.NO;
                            if(input.nextLine().equals("a")){
                                isSpoiler=IsSpoiler.YES;
                            }
                            Review reReview=new Review(text,((Member) user),chooseMovie,isSpoiler);
                            reReview.setReReview(reviewThatChosen);
                            ((Member) user).getMemberReviews().add(reReview);
                            chooseMovie.getReviews().add(reReview);
                        }
                    }
                }
            }
            else if (whichOne.equals("e")) {
                System.out.println("press a to write a comment and any other key to see movie comments");
                if (input.nextLine().equals("a")) {
                    System.out.println("write your comment's text");
                    String commentText = input.nextLine();
                    chooseMovie.getComments().add(new Comment(commentText, ((Member) user), chooseMovie));
                } else {
                    int commentCount = 0;
                    for (Comment comment : chooseMovie.getComments()) {
                        System.out.println(commentCount);
                        ++commentCount;
                        comment.showCommentDetails();
                    }
                }
            }
            else if (whichOne.equals("f")){
                System.out.println("press a to like this movie or any other key to see likes");
                if(input.nextLine().equals("a")){
                    chooseMovie.getLikes().add(new Like(((Member) user),chooseMovie ));
                }
                else{
                    for(Like like:chooseMovie.getLikes()){
                        System.out.println(like.getLiker().getName());
                        if(like.getContent() instanceof Movie){
                            System.out.println("liked: "+((Movie) like.getContent()).getMovieName());
                        }
                    }
                }
            }
            else {
                break;
            }
        }
    }
}

