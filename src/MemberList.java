import java.util.ArrayList;

public class MemberList {
    ListType listType;
    ArrayList<Movie> listMovies=new ArrayList<>();

    public MemberList(ListType listType){
        setListType(listType);
    }

    public ArrayList<Movie> getListMovies() {
        return listMovies;
    }

    public void setListMovies(ArrayList<Movie> listMovies) {
        this.listMovies = listMovies;
    }

    public ListType getListType() {
        return listType;
    }

    public void setListType(ListType listType) {
        this.listType = listType;
    }
    public void addToListMovies(Movie movie){
        listMovies.add(movie);
    }
}
