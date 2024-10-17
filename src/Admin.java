import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Admin extends User {

    private static ArrayList<Report> reports=new ArrayList<>();
    private static ArrayList<EditSuggest> editSuggests=new ArrayList<>();
    private static ArrayList<PeopleEditSuggest> peopleEditSuggests=new ArrayList<>();
    private static ArrayList<People> craftsMen=new ArrayList<>();
    private static ArrayList<Movie> allMovies=new ArrayList<>();
    private static ArrayList<User> allUsers=new ArrayList<>();
    private static ArrayList<Forum> forums=new ArrayList<>();
    private static ArrayList<String> rules=new ArrayList<>();

    public Admin(String username,String password){
        setUsername(username);
        setPassword(password);
    }

    public void approveRejectEdit(){
        Scanner input=new Scanner(System.in);
        int count=0;
        for(EditSuggest editSuggest:editSuggests){
            System.out.print(count+": ");
            System.out.println(editSuggest.getOriginalMovie().getMovieName());
            ++count;
        }
        System.out.println("which movie do you want to see its edit suggest?");
        int choose = Integer.parseInt(input.nextLine());
        editSuggests.get(choose).getSuggestMovie().showMovieDetails();
        System.out.println("Do you approve it or reject it? press y to approve and any other key to not");
        String chooseYorN=input.nextLine();
        if (chooseYorN.equals("y")){
            editSuggests.get(choose).setOriginalMovie(editSuggests.get(choose).getSuggestMovie());
        }
        else{
            editSuggests.remove(choose);
        }
    }

    public void approveRejectEditPeople(){
        Scanner input=new Scanner(System.in);
        int count=0;
        for(PeopleEditSuggest peopleEditSuggest:peopleEditSuggests){
            System.out.print(count+": ");
            System.out.println(peopleEditSuggest.getOriginalPerson().getName());
            ++count;
        }
        System.out.println("which person do you want to see its edit suggest?");
        int choose = Integer.parseInt(input.nextLine());
        peopleEditSuggests.get(choose).getSuggestPerson().showPeopleDetails();
        System.out.println("Do you approve it or reject it? press y to approve and any other key to not");
        String chooseYorN=input.nextLine();
        if (chooseYorN.equals("y")){
            peopleEditSuggests.get(choose).setOriginalPerson(peopleEditSuggests.get(choose).getSuggestPerson());
        }
        else{
            peopleEditSuggests.remove(choose);
        }
    }

    public void deleteUser(String username){
        for(User user:allUsers){
            if (user.getUsername().equals(username)){
                allUsers.remove(user);
                break;
            }
        }
    }

    public void banUser(String username){
        for(User user:allUsers){
            if (user.getUsername().equals(username)){
                user.setIsBan(IsBan.BAN);
                break;
            }
        }
    }
    public void unBanUser(String username){
        for(User user:allUsers){
            if (user.getUsername().equals(username)){
                user.setIsBan(IsBan.NOTBAN);
                break;
            }
        }
    }

    public static User searchUser(String username){
        for (User user:allUsers){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    public void showAdminProfile(){
        System.out.println("username: "+getUsername());
        System.out.println("Name: "+getName());
        System.out.println("email: "+getEmail());
        System.out.println("Date of birth: "+getDateOfBirth());
        System.out.println("Roll: Admin");
    }

    public void addUser(){
        Scanner input=new Scanner(System.in);
        System.out.println("what type of user do you want to add?");
        System.out.println("1:member\n2:editor\n3:admin");
        int whichOne=Integer.parseInt(input.nextLine());
        if(whichOne==1){
            while(true){
                System.out.println("enter a name");
                String name=input.nextLine();
                System.out.println("enter a username");
                String username=input.nextLine();
                if(searchUser(username)==null){
                    System.out.println("enter the password");
                    String password = input.nextLine();
                    allUsers.add(new Member(username,password,name));
                    break;
                }
                else {
                    System.out.println("this username has used try again!");
                }
            }
        }
        else if(whichOne==2){
            while(true){
                System.out.println("enter a username");
                String username=input.nextLine();
                if(searchUser(username)==null){
                    System.out.println("enter the password");
                    String password = input.nextLine();
                    allUsers.add(new Editor(username,password));
                    break;
                }
                else {
                    System.out.println("this username has used try again!");
                }
            }
        }
        else if(whichOne==3){
            while(true){
                System.out.println("enter a username");
                String username=input.nextLine();
                if(searchUser(username)==null){
                    System.out.println("enter the password");
                    String password = input.nextLine();
                    allUsers.add(new Admin(username,password));
                    break;
                }
                else {
                    System.out.println("this username has used try again!");
                }
            }
        }
        else{
            System.out.println("you entered a wrong number!");
        }
    }
    public void  deleteForum(int index){
        forums.remove(index);
    }

    public void deleteReview(Movie movie,Review review){
        movie.deleteReview(review);
    }

    public void addNewPeople(String name,int age){
        craftsMen.add(new People(name,age));
    }

    public void deletePeople(int index){
        craftsMen.remove(index);
    }

    public static void editPeople(People people){
        Scanner input = new Scanner(System.in);
        System.out.println("what part do you want to edit?\npress 1 for name\npress 2 for age\npress 3 for job");
        int whichChoose=Integer.parseInt(input.nextLine());
        if (whichChoose==1){
            System.out.println("enter the new name");
            String newName=input.nextLine();
            people.setName(newName);
        }
        else if (whichChoose==2){
            System.out.println("enter the new age");
            int newAge=Integer.parseInt(input.nextLine());
            people.setAge(newAge);
        }
        else if (whichChoose==3){
            while (true) {
                System.out.println("press 1 if you want to add actor to his/her types");
                System.out.println("press 2 if you want to add director to his/her types");
                System.out.println("press 3 if you want to add writer to his/her types");
                System.out.println("press 0 to not adding any other type");
                int whichType = Integer.parseInt(input.nextLine());
                if (whichType == 0) {
                    break;
                } else if (whichType == 1) {
                    people.addPeopleType(PeopleType.ACTOR);
                } else if (whichType == 2) {
                    people.addPeopleType(PeopleType.DIRECTOR);
                } else if (whichType == 3) {
                    people.addPeopleType(PeopleType.WRITER);
                }
            }
        }
    }

    public void addNewMovie(String movieName){
        allMovies.add(new Movie(movieName));
    }
    public void deleteMovie(int index){
        allMovies.remove(index);
    }
    public static void editMovie(Movie movie){
        Scanner input = new Scanner(System.in);
        System.out.println("which part do you want to edit?");
        System.out.println("press 1 to change movie name!");
        System.out.println("press 2 to change movie title!");
        System.out.println("press 3 to change movie plot summary!");
        System.out.println("press 4 to change movie poster!");
        System.out.println("press 5 to change movie trailer!");
        System.out.println("press 6 to change movie craftsMen!");
        System.out.println("press 7 to change movie genre!");
        System.out.println("press 8 to change movie language!");
        System.out.println("press 9 to change movie release date!");
        int whichPartToEdit=Integer.parseInt(input.nextLine());
        switch (whichPartToEdit){
            case 1:
                System.out.println("enter the movie name");
                String newMovieName=input.nextLine();
                movie.setMovieName(newMovieName);
                break;
            case 2:
                System.out.println("enter the movie title");
                String newMovieTitle=input.nextLine();
                movie.setTitle(newMovieTitle);
                break;
            case 3:
                System.out.println("enter the movie plot summary");
                String newMoviePlotSummary=input.nextLine();
                movie.setPlotSummary(newMoviePlotSummary);
                break;
            case 4:
                System.out.println("which poster do you want to edit or remove?");
                int whichPoster=Integer.parseInt(input.nextLine());
                System.out.println("press 1 to suggest edit and any other key to suggest delete");
                int whatSuggest=Integer.parseInt(input.nextLine());
                if(whatSuggest==1){
                    System.out.println("enter the movie poster");
                    String newMoviePoster=input.nextLine();
                    movie.editPoster(whichPoster,newMoviePoster);
                }
                else{
                    movie.getPosters().remove(whichPoster);
                }
                break;
            case 5:
                System.out.println("which trailer do you want to edit or remove?");
                int whichTrailer=Integer.parseInt(input.nextLine());
                System.out.println("press 1 to suggest edit and any other key to suggest delete");
                int whatTrailerSuggest=Integer.parseInt(input.nextLine());
                if(whatTrailerSuggest==1){
                    System.out.println("enter the movie trailer");
                    String newMovieTrailer=input.nextLine();
                    movie.editTrailer(whichTrailer,newMovieTrailer);
                }
                else{
                    movie.getTrailers().remove(whichTrailer);
                }
                break;
            case 6:
                System.out.println("which craftMan do you want to edit or remove?");
                int whichCraftMan=Integer.parseInt(input.nextLine());
                System.out.println("press 1 to suggest edit and any other key to suggest delete");
                int whatCraftManSuggest=Integer.parseInt(input.nextLine());
                if(whatCraftManSuggest==1){
                    System.out.println("enter the craftMan name");
                    String craftManName=input.nextLine();
                    int flag=0;
                    for (People people:Admin.getCraftsMen()){
                        if (people.getName().equals(craftManName)){
                            PeopleAndTypeInMovie newPeople=new PeopleAndTypeInMovie(people);
                            while(true) {
                                System.out.println("press 1 if you want to add actor to his/her types");
                                System.out.println("press 2 if you want to add director to his/her types");
                                System.out.println("press 3 if you want to add writer to his/her types");
                                System.out.println("press 0 to not adding any other type");
                                int whichRoll = Integer.parseInt(input.nextLine());
                                if (whichRoll == 0) {
                                    break;
                                } else if (whichRoll == 1) {
                                    newPeople.addNewType(PeopleType.ACTOR);
                                    break;
                                } else if (whichRoll == 2) {
                                    newPeople.addNewType(PeopleType.DIRECTOR);
                                    break;
                                } else if (whichRoll == 3) {
                                    newPeople.addNewType(PeopleType.WRITER);
                                    break;
                                }
                            }
                            movie.addCraftsmen(newPeople);
                            flag=1;
                            break;
                        }
                    }
                    if (flag==0) {
                        System.out.println("there is no such a people!");
                        System.out.println("press 1 to add new people and 0 to not");
                        int wantsToAdd = Integer.parseInt(input.nextLine());
                        if (wantsToAdd == 1) {
                            System.out.println("enter the age");
                            int craftsManAge = Integer.parseInt(input.nextLine());
                            People newCraftsMan = new People(craftManName, craftsManAge);
                            PeopleAndTypeInMovie newPeople = new PeopleAndTypeInMovie(newCraftsMan);
                            while (true) {
                                System.out.println("press 1 if you want to add actor to his/her types");
                                System.out.println("press 2 if you want to add director to his/her types");
                                System.out.println("press 3 if you want to add writer to his/her types");
                                System.out.println("press 0 to not adding any other type");
                                int whichType = Integer.parseInt(input.nextLine());
                                if (whichType == 0) {
                                    break;
                                } else if (whichType == 1) {
                                    newCraftsMan.addPeopleType(PeopleType.ACTOR);
                                    break;
                                } else if (whichType == 2) {
                                    newCraftsMan.addPeopleType(PeopleType.DIRECTOR);
                                    break;
                                } else if (whichType == 3) {
                                    newCraftsMan.addPeopleType(PeopleType.WRITER);
                                    break;
                                }
                            }
                            movie.editCraftsmen(whichCraftMan, newPeople);
                        } else {
                            break;
                        }
                    }
                }
                else{
                    movie.getCraftsmen().remove(whichCraftMan);
                }
                break;
            case 7:
                System.out.println("which genre do you want to edit?");
                System.out.println("press 1 if you want to change the genre to action");
                System.out.println("press 2 if you want to change the genre to comedy");
                System.out.println("press 3 if you want to change the genre to drama");
                System.out.println("press 4 if you want to change the genre to fantasy");
                System.out.println("press 5 if you want to change the genre to historical");
                System.out.println("press 6 if you want to change the genre to horror");
                System.out.println("press 7 if you want to change the genre to mystery");
                System.out.println("press 8 if you want to change the genre to romance");
                int whichGenre=Integer.parseInt(input.nextLine());
                switch (whichGenre){
                    case 1:
                        movie.setMovieGenre(Genre.ACTION);
                        break;
                    case 2:
                        movie.setMovieGenre(Genre.COMEDY);
                        break;
                    case 3:
                        movie.setMovieGenre(Genre.DRAMA);
                        break;
                    case 4:
                        movie.setMovieGenre(Genre.FANTASY);
                        break;
                    case 5:
                        movie.setMovieGenre(Genre.HISTORICAL);
                        break;
                    case 6:
                        movie.setMovieGenre(Genre.HORROR);
                        break;
                    case 7:
                        movie.setMovieGenre(Genre.MYSTERY);
                        break;
                    case 8:
                        movie.setMovieGenre(Genre.ROMANCE);
                        break;
                }
                break;
            case 8:
                System.out.println("press 1 if you want to change the language to arabic");
                System.out.println("press 2 if you want to change the language to english");
                System.out.println("press 3 if you want to change the language to french");
                System.out.println("press 4 if you want to change the language to italian");
                System.out.println("press 5 if you want to change the language to persian");
                System.out.println("press 6 if you want to change the language to russian");
                int whichLanguage=Integer.parseInt(input.nextLine());

                switch (whichLanguage) {

                    case 1:
                        movie.setMovieLanguage(Language.ARABIC);
                        break;
                    case 2:
                        movie.setMovieLanguage(Language.ENGLISH);
                        break;
                    case 3:
                        movie.setMovieLanguage(Language.FRENCH);
                        break;
                    case 4:
                        movie.setMovieLanguage(Language.ITALIAN);
                        break;
                    case 5:
                        movie.setMovieLanguage(Language.PERSIAN);
                        break;
                    case 6:
                        movie.setMovieLanguage(Language.RUSSIAN);
                        break;
                }
                break;
            case 9:
                System.out.println("enter the release date year");
                int year=Integer.parseInt(input.nextLine());
                System.out.println("enter the release date month");
                int month=Integer.parseInt(input.nextLine());
                System.out.println("enter the release date date");
                int date=Integer.parseInt(input.nextLine());
                movie.setReleaseDate(new Date(year,month,date));
                break;
        }
    }

    public static ArrayList<Forum> getForums() {
        return forums;
    }

    public static void setForums(ArrayList<Forum> forums) {
        Admin.forums = forums;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static void setAllUsers(ArrayList<User> allUsers) {
        Admin.allUsers = allUsers;
    }

    public static ArrayList<Movie> getAllMovies() {
        return allMovies;
    }

    public static void setAllMovies(ArrayList<Movie> allMovies) {
        Admin.allMovies = allMovies;
    }

    public static ArrayList<People> getCraftsMen() {
        return craftsMen;
    }

    public static void setCraftsMen(ArrayList<People> craftsMen) {
        Admin.craftsMen = craftsMen;
    }

    public static ArrayList<EditSuggest> getEditSuggests() {
        return editSuggests;
    }

    public static void setEditSuggests(ArrayList<EditSuggest> editSuggests) {
        Admin.editSuggests = editSuggests;
    }


    public static ArrayList<Report> getReports() {
        return reports;
    }

    public static void addReport(String text,Object object){
        reports.add(new Report(text,object));
    }

    public static void setReports(ArrayList<Report> reports) {
        Admin.reports = reports;
    }

    public static ArrayList<String> getRules() {
        return rules;
    }

    public static void setRules(ArrayList<String> rules) {
        Admin.rules = rules;
    }
    public static void addRule(String rule){
        Admin.rules.add(rule);
    }

    public static ArrayList<PeopleEditSuggest> getPeopleEditSuggests() {
        return peopleEditSuggests;
    }

    public static void setPeopleEditSuggests(ArrayList<PeopleEditSuggest> peopleEditSuggests) {
        Admin.peopleEditSuggests = peopleEditSuggests;
    }
}
