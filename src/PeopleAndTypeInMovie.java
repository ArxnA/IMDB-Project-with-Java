import java.util.ArrayList;

public class PeopleAndTypeInMovie {
    People people;
    ArrayList<PeopleType> typeInMovie;

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public ArrayList<PeopleType> getTypeInMovie() {
        return typeInMovie;
    }

    public void setTypeInMovie(ArrayList<PeopleType> typeInMovie) {
        this.typeInMovie = typeInMovie;
    }

    public void addNewType(PeopleType peopleType){
        typeInMovie.add(peopleType);
    }

    public PeopleAndTypeInMovie(People people){
        setPeople(people);
    }
}
