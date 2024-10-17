public class EditSuggest {
    private Movie originalMovie;
    private Movie suggestMovie;

    public EditSuggest(Movie originalMovie, Movie suggestMovie){
        setOriginalMovie(originalMovie);
        setSuggestMovie(suggestMovie);
    }

    public Movie getOriginalMovie() {
        return originalMovie;
    }

    public void setOriginalMovie(Movie originalMovie) {
        this.originalMovie = originalMovie;
    }

    public Movie getSuggestMovie() {
        return suggestMovie;
    }

    public void setSuggestMovie(Movie suggestMovie) {
        this.suggestMovie = suggestMovie;
    }
//    private String suggestText;
//    private Movie movie;
//    private SuggestType suggestType;
//
//    public EditSuggest(Movie movie,String suggestText,SuggestType suggestType){
//        setMovie(movie);
//        setSuggestText(suggestText);
//        setSuggestType(suggestType);
//    }
//
//    public String getSuggestText() {
//        return suggestText;
//    }
//
//    public void setSuggestText(String suggestText) {
//        this.suggestText = suggestText;
//    }
//
//    public Movie getMovie() {
//        return movie;
//    }
//
//    public void setMovie(Movie movie) {
//        this.movie = movie;
//    }
//
//    public SuggestType getSuggestType() {
//        return suggestType;
//    }
//
//    public void setSuggestType(SuggestType suggestType) {
//        this.suggestType = suggestType;
//    }
}
