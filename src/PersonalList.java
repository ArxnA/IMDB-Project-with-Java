import java.util.ArrayList;

public class PersonalList {
    private String listName;
    private ArrayList<Movie> personalListMovies=new ArrayList<>();

    public PersonalList(String listName){
        setListName(listName);
    }


    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public ArrayList<Movie> getPersonalListMovies() {
        return personalListMovies;
    }

    public void setPersonalListMovies(ArrayList<Movie> personalListMovies) {
        this.personalListMovies = personalListMovies;
    }

    public void addMovie(Movie movie){
        personalListMovies.add(movie);
    }
}
