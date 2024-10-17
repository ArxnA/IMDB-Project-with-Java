import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Editor extends User {

    public Editor(String username,String password){
        setUsername(username);
        setPassword(password);
    }

    public void suggestPeopleEdit(People originalPeople){
        People suggestPeople=new People();
        suggestPeople.setName(originalPeople.getName());
        suggestPeople.setBio(originalPeople.getBio());
        suggestPeople.setAge(originalPeople.getAge());
        suggestPeople.setPeopleTypes(originalPeople.getPeopleTypes());
        Admin.editPeople(suggestPeople);
        Admin.getPeopleEditSuggests().add(new PeopleEditSuggest(originalPeople,suggestPeople));
    }

    public void suggestEdit(Movie originalMovie){
        Movie suggestMovie=new Movie();
        suggestMovie.setMovieName(originalMovie.getMovieName());
        suggestMovie.setTitle(originalMovie.getTitle());
        suggestMovie.setPlotSummary(originalMovie.getPlotSummary());
        suggestMovie.setPosters(originalMovie.getPosters());
        suggestMovie.setTrailers(originalMovie.getTrailers());
        suggestMovie.setCraftsmen(originalMovie.getCraftsmen());
        suggestMovie.setMovieGenre(originalMovie.getMovieGenre());
        suggestMovie.setMovieLanguage(originalMovie.getMovieLanguage());
        suggestMovie.setReleaseDate(originalMovie.getReleaseDate());
        Admin.editMovie(suggestMovie);
        Admin.getEditSuggests().add(new EditSuggest(originalMovie,suggestMovie));

//        Scanner suggestInput = new Scanner(System.in);
//        System.out.println("which part do you want to edit?");
//        System.out.println("press 1 to change movie name!");
//        System.out.println("press 2 to change movie title!");
//        System.out.println("press 3 to change movie plot summary!");
//        System.out.println("press 4 to change movie poster!");
//        System.out.println("press 5 to change movie trailer!");
//        System.out.println("press 6 to change movie craftsMen!");
//        System.out.println("press 7 to change movie genre!");
//        System.out.println("press 8 to change movie language!");
//        System.out.println("press 9 to change movie release date!");
//        int whichPartToEdit=Integer.parseInt(suggestInput.nextLine());
//        switch (whichPartToEdit){
//            case 1:
//                System.out.println("enter the movie name");
//                String newMovieName=suggestInput.nextLine();
//                suggestMovie.setMovieName(newMovieName);
//                break;
//            case 2:
//                System.out.println("enter the movie title");
//                String newMovieTitle=suggestInput.nextLine();
//                suggestMovie.setTitle(newMovieTitle);
//                break;
//            case 3:
//                System.out.println("enter the movie plot summary");
//                String newMoviePlotSummary=suggestInput.nextLine();
//                suggestMovie.setPlotSummary(newMoviePlotSummary);
//                break;
//            case 4:
//                System.out.println("which poster do you want to edit or remove?");
//                int whichPoster=Integer.parseInt(suggestInput.nextLine());
//                System.out.println("press 1 to suggest edit and any other key to suggest delete");
//                int whatSuggest=Integer.parseInt(suggestInput.nextLine());
//                if(whatSuggest==1){
//                    System.out.println("enter the movie poster");
//                    String newMoviePoster=suggestInput.nextLine();
//                    suggestMovie.editPoster(whichPoster,newMoviePoster);
//                }
//                else{
//                    suggestMovie.getPosters().remove(whichPoster);
//                }
//                break;
//            case 5:
//                System.out.println("which trailer do you want to edit or remove?");
//                int whichTrailer=Integer.parseInt(suggestInput.nextLine());
//                System.out.println("press 1 to suggest edit and any other key to suggest delete");
//                int whatTrailerSuggest=Integer.parseInt(suggestInput.nextLine());
//                if(whatTrailerSuggest==1){
//                    System.out.println("enter the movie trailer");
//                    String newMovieTrailer=suggestInput.nextLine();
//                    suggestMovie.editTrailer(whichTrailer,newMovieTrailer);
//                }
//                else{
//                    suggestMovie.getTrailers().remove(whichTrailer);
//                }
//                break;
//            case 6:
//                System.out.println("which craftMan do you want to edit or remove?");
//                int whichCraftMan=Integer.parseInt(suggestInput.nextLine());
//                System.out.println("press 1 to suggest edit and any other key to suggest delete");
//                int whatCraftManSuggest=Integer.parseInt(suggestInput.nextLine());
//                if(whatCraftManSuggest==1){
//                    System.out.println("enter the craftMan name");
//                    String craftManName=suggestInput.nextLine();
//                    int flag=0;
//                    for (People people:Admin.getCraftsMen()){
//                        if (people.getName().equals(craftManName)){
//                            suggestMovie.editCraftsmen(whichCraftMan,people);
//                            flag=1;
//                            break;
//                        }
//                    }
//                    if (flag==0) {
//                        System.out.println("there is no such a people!");
//                        System.out.println("press 1 to add new people and 0 to not");
//                        int wantsToAdd = Integer.parseInt(suggestInput.nextLine());
//                        if (wantsToAdd == 1) {
//                            System.out.println("enter the age");
//                            int craftsManAge = Integer.parseInt(suggestInput.nextLine());
//                            People newCraftsMan = new People(craftManName, craftsManAge);
//                            while (true) {
//                                System.out.println("press 1 if you want to add actor to his/her types");
//                                System.out.println("press 2 if you want to add director to his/her types");
//                                System.out.println("press 3 if you want to add writer to his/her types");
//                                System.out.println("press 0 to not adding any other type");
//                                int whichType = Integer.parseInt(suggestInput.nextLine());
//                                if (whichType == 0) {
//                                    break;
//                                } else if (whichType == 1) {
//                                    newCraftsMan.addPeopleType(PeopleType.ACTOR);
//                                    break;
//                                } else if (whichType == 2) {
//                                    newCraftsMan.addPeopleType(PeopleType.DIRECTOR);
//                                    break;
//                                } else if (whichType == 3) {
//                                    newCraftsMan.addPeopleType(PeopleType.WRITER);
//                                    break;
//                                }
//                            }
//                            suggestMovie.editCraftsmen(whichCraftMan, newCraftsMan);
//                        } else {
//                            break;
//                        }
//                    }
//                }
//                else{
//                    suggestMovie.getCraftsmen().remove(whichCraftMan);
//                }
//                break;
//            case 7:
//                System.out.println("which genre do you want to edit?");
//                int whichGenre=Integer.parseInt(suggestInput.nextLine());
//                System.out.println("press 1 if you want to change the genre to action");
//                System.out.println("press 2 if you want to change the genre to comedy");
//                System.out.println("press 3 if you want to change the genre to drama");
//                System.out.println("press 4 if you want to change the genre to fantasy");
//                System.out.println("press 5 if you want to change the genre to historical");
//                System.out.println("press 6 if you want to change the genre to horror");
//                System.out.println("press 7 if you want to change the genre to mystery");
//                System.out.println("press 8 if you want to change the genre to romance");
//                switch (whichGenre){
//                    case 1:
//                        suggestMovie.setMovieGenre(Genre.ACTION);
//                        break;
//                    case 2:
//                        suggestMovie.setMovieGenre(Genre.COMEDY);
//                        break;
//                    case 3:
//                        suggestMovie.setMovieGenre(Genre.DRAMA);
//                        break;
//                    case 4:
//                        suggestMovie.setMovieGenre(Genre.FANTASY);
//                        break;
//                    case 5:
//                        suggestMovie.setMovieGenre(Genre.HISTORICAL);
//                        break;
//                    case 6:
//                        suggestMovie.setMovieGenre(Genre.HORROR);
//                        break;
//                    case 7:
//                        suggestMovie.setMovieGenre(Genre.MYSTERY);
//                        break;
//                    case 8:
//                        suggestMovie.setMovieGenre(Genre.ROMANCE);
//                        break;
//                }
//                break;
//            case 8:
//                System.out.println("press 1 if you want to change the language to arabic");
//                System.out.println("press 2 if you want to change the language to english");
//                System.out.println("press 3 if you want to change the language to french");
//                System.out.println("press 4 if you want to change the language to italian");
//                System.out.println("press 5 if you want to change the language to persian");
//                System.out.println("press 6 if you want to change the language to russian");
//                int whichLanguage=Integer.parseInt(suggestInput.nextLine());
//
//                switch (whichLanguage) {
//
//                    case 1:
//                        suggestMovie.setMovieLanguage(Language.ARABIC);
//                        break;
//                    case 2:
//                        suggestMovie.setMovieLanguage(Language.ENGLISH);
//                        break;
//                    case 3:
//                        suggestMovie.setMovieLanguage(Language.FRENCH);
//                        break;
//                    case 4:
//                        suggestMovie.setMovieLanguage(Language.ITALIAN);
//                        break;
//                    case 5:
//                        suggestMovie.setMovieLanguage(Language.PERSIAN);
//                        break;
//                    case 6:
//                        suggestMovie.setMovieLanguage(Language.RUSSIAN);
//                        break;
//                }
//                break;
//            case 9:
//                System.out.println("enter the release date year");
//                int year=Integer.parseInt(suggestInput.nextLine());
//                System.out.println("enter the release date month");
//                int month=Integer.parseInt(suggestInput.nextLine());
//                System.out.println("enter the release date date");
//                int date=Integer.parseInt(suggestInput.nextLine());
//                suggestMovie.setReleaseDate(new Date(year,month,date));
//                break;
//        }
    }

    public void addMissingDataPeople(People people){
        Scanner input=new Scanner(System.in);
        while(true){
            System.out.println("which part do you want to add?");
            System.out.println("press a to add person name!");
            System.out.println("press b to add person age!");
            System.out.println("press c to add person bio!");
            System.out.println("press d to add his/her jobs!");
            System.out.println("any other key to exit");
            String whichOne=input.nextLine();
            if(whichOne.equals("a")){
                System.out.println("enter the name");
                people.setName(input.nextLine());
            }
            else if(whichOne.equals("b")){
                System.out.println("enter the age");
                int age;
                while (true){
                    try {
                        age=Integer.parseInt(input.nextLine());
                        break;
                    }catch (Exception e){
                        System.out.println("enter number!");
                    }
                }
                people.setAge(age);
            }
            else if(whichOne.equals("c")){
                System.out.println("enter the bio");
                people.setBio(input.nextLine());
            }
            else if(whichOne.equals("d")){
                while(true) {
                    System.out.println("press 1 if you want to add actor to his/her types");
                    System.out.println("press 2 if you want to add director to his/her types");
                    System.out.println("press 3 if you want to add writer to his/her types");
                    System.out.println("press 0 to not adding any other type");
                    int whichRoll = Integer.parseInt(input.nextLine());
                    if (whichRoll == 0) {
                        break;
                    } else if (whichRoll == 1) {
                        people.addPeopleType(PeopleType.ACTOR);
                    } else if (whichRoll == 2) {
                        people.addPeopleType(PeopleType.DIRECTOR);
                    } else if (whichRoll == 3) {
                        people.addPeopleType(PeopleType.WRITER);
                    }
                }
            }
            else{
                break;
            }
        }
    }

    public void addMissingData(Movie movie){
        Scanner Input = new Scanner(System.in);
        System.out.println("which part do you want to add?");
        System.out.println("press 1 to add movie name!");
        System.out.println("press 2 to add movie title!");
        System.out.println("press 3 to add movie plot summary!");
        System.out.println("press 4 to add movie poster!");
        System.out.println("press 5 to add movie trailer!");
        System.out.println("press 6 to add movie craftsMen!");
        System.out.println("press 7 to add movie genre!");
        System.out.println("press 8 to add movie language!");
        System.out.println("press 9 to add movie release date!");
        int whichPartToAdd;
        while(true){
            try {
                whichPartToAdd=Integer.parseInt(Input.nextLine());
                break;
            }
            catch (Exception e){
                System.out.println("enter number!");
            }
        }
        switch (whichPartToAdd){
            case 1:
                if(movie.getMovieName()==null){
                    System.out.println("enter the movie name");
                    String newMovieName=Input.nextLine();
                    movie.setMovieName(newMovieName);
                }
                else {
                    System.out.println("The movie has name you cant add movie name!");
                }
                break;
            case 2:
                if(movie.getTitle()==null){
                    System.out.println("enter the movie title");
                    String newMovieTitle=Input.nextLine();
                    movie.setTitle(newMovieTitle);
                }
                else {
                    System.out.println("The movie has title you cant add movie title!");
                }
                break;
            case 3:
                if(movie.getPlotSummary()==null){
                    System.out.println("enter the movie plot summary");
                    String newMoviePlotSummary=Input.nextLine();
                    movie.setPlotSummary(newMoviePlotSummary);
                }
                else {
                    System.out.println("The movie has plot summary you cant add movie plot summary!");
                }
                break;
            case 4:
                System.out.println("enter the movie poster");
                String newMoviePoster=Input.nextLine();
                movie.addPoster(newMoviePoster);
                break;
            case 5:
                System.out.println("enter the movie trailer");
                String newMovieTrailer=Input.nextLine();
                movie.addTrailer(newMovieTrailer);
                break;
            case 6:
                System.out.println("enter the craftMan name");
                String craftManName=Input.nextLine();
                int isExist=0;
                for (People people:Admin.getCraftsMen()){
                    if (people.getName().equals(craftManName)){
                        PeopleAndTypeInMovie newPeople=new PeopleAndTypeInMovie(people);
                        while(true) {
                            System.out.println("press 1 if you want to add actor to his/her types");
                            System.out.println("press 2 if you want to add director to his/her types");
                            System.out.println("press 3 if you want to add writer to his/her types");
                            System.out.println("press 0 to not adding any other type");
                            int whichRoll = Integer.parseInt(Input.nextLine());
                            if (whichRoll == 0) {
                                break;
                            } else if (whichRoll == 1) {
                                newPeople.addNewType(PeopleType.ACTOR);
                            } else if (whichRoll == 2) {
                                newPeople.addNewType(PeopleType.DIRECTOR);
                            } else if (whichRoll == 3) {
                                newPeople.addNewType(PeopleType.WRITER);
                            }
                        }
                        movie.addCraftsmen(newPeople);
                        isExist = 1;
                        break;
                    }
                }
                if (isExist==0){
                    System.out.println("there is no such a people!");
                    System.out.println("press 1 to add new people and any other key to not");
                    int wantsToAddNew=Integer.parseInt(Input.nextLine());
                    if (wantsToAddNew==1){
                        System.out.println("enter the age");
                        int craftsManAge=Integer.parseInt(Input.nextLine());
                        People newCraftsMan=new People(craftManName,craftsManAge);
                        PeopleAndTypeInMovie newPeople = new PeopleAndTypeInMovie(newCraftsMan);
                        while(true){
                            System.out.println("press 1 if you want to add actor to his/her types");
                            System.out.println("press 2 if you want to add director to his/her types");
                            System.out.println("press 3 if you want to add writer to his/her types");
                            System.out.println("press 0 to not adding any other type");
                            int whichRoll=Integer.parseInt(Input.nextLine());
                            if(whichRoll==0){
                                break;
                            }
                            else if(whichRoll==1){
                                newCraftsMan.addPeopleType(PeopleType.ACTOR);
                                newPeople.addNewType(PeopleType.ACTOR);
                                break;
                            }
                            else if(whichRoll==2){
                                newCraftsMan.addPeopleType(PeopleType.DIRECTOR);
                                newPeople.addNewType(PeopleType.DIRECTOR);
                                break;
                            }
                            else if(whichRoll==3){
                                newCraftsMan.addPeopleType(PeopleType.WRITER);
                                newPeople.addNewType(PeopleType.WRITER);
                                break;
                            }
                        }
                        movie.addCraftsmen(newPeople);
                    }
                    else {
                        break;
                    }
                }
                break;
            case 7:
                if(movie.getMovieGenre()==null){
                    System.out.println("which genre do you want to add?");
                    int whichGenre=Integer.parseInt(Input.nextLine());
                    System.out.println("press 1 if you want to add the action");
                    System.out.println("press 2 if you want to add the comedy");
                    System.out.println("press 3 if you want to add the drama");
                    System.out.println("press 4 if you want to add the fantasy");
                    System.out.println("press 5 if you want to add the historical");
                    System.out.println("press 6 if you want to add the horror");
                    System.out.println("press 7 if you want to add the mystery");
                    System.out.println("press 8 if you want to add the romance");
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
                }
                else{
                    System.out.println("The movie has genre you cant add genre!");
                }
                break;
            case 8:
                if (movie.getMovieLanguage()==null){
                    System.out.println("press 1 if you want to change the language to arabic");
                    System.out.println("press 2 if you want to change the language to english");
                    System.out.println("press 3 if you want to change the language to french");
                    System.out.println("press 4 if you want to change the language to italian");
                    System.out.println("press 5 if you want to change the language to persian");
                    System.out.println("press 6 if you want to change the language to russian");
                    int whichLanguage=Integer.parseInt(Input.nextLine());

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
                }
                else{
                    System.out.println("The movie has language you cant add language!");
                }
                break;
            case 9:
                if(movie.getReleaseDate()==null){
                    System.out.println("enter the release date year");
                    int year=Integer.parseInt(Input.nextLine());
                    System.out.println("enter the release date month");
                    int month=Integer.parseInt(Input.nextLine());
                    System.out.println("enter the release date date");
                    int date=Integer.parseInt(Input.nextLine());
                    movie.setReleaseDate(new Date(year,month,date));
                }
                else {
                    System.out.println("The movie has date you cant add date!");
                }

                break;
        }
    }

    public void reporting(String text,Object object){
        Admin.addReport(text,object);
    }
    public void showEditorProfile(){
        System.out.println("username: "+getUsername());
        System.out.println("Name: "+getName());
        System.out.println("email: "+getEmail());
        System.out.println("Date of birth: "+getDateOfBirth());
        System.out.println("Roll: Editor");
    }
}
