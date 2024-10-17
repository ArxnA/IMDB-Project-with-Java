import java.util.ArrayList;

public class People {
    private ArrayList<PeopleType> peopleTypes=new ArrayList<>();
    private String name;
    private int age;
    private String bio;

    public People(){

    }

    public People(String name,String bio,int Age){
        setAge(age);
        setName(name);
        setBio(bio);
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public People(String name, int age){
        setAge(age);
        setName(name);
    }

    public void addPeopleType(PeopleType peopleType){
        peopleTypes.add(peopleType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<PeopleType> getPeopleTypes() {
        return peopleTypes;
    }

    public void setPeopleTypes(ArrayList<PeopleType> peopleTypes) {
        this.peopleTypes = peopleTypes;
    }

    public void showPeopleDetails(){
        System.out.println("name: "+getName());
        System.out.println("age: "+getAge());
        System.out.println("bio: "+getBio());
        System.out.println("his/her rolls");
        for(PeopleType peopleType:getPeopleTypes()){
            System.out.println(peopleType);
        }
    }
}
