import java.util.ArrayList;
import java.util.Scanner;

public class SearchEngine {

    public static Object search(String name){
        ArrayList<Object> allPossibilities=new ArrayList<>();
        int count=1;
        System.out.println("users:");
        for(User user:Admin.getAllUsers()){
            if(user.getUsername().equals(name)){
                allPossibilities.add(user);
                System.out.println(count+"  "+user.getName());
                ++count;
            }
        }
        System.out.println("people:");
        for(People person:Admin.getCraftsMen()){
            if(person.getName().equals(name)){
                allPossibilities.add(person);
                System.out.println(count+"  "+person.getName());
                ++count;
            }
        }
        System.out.println("movies:");
        for(Movie movie:Admin.getAllMovies()){
            if(movie.getMovieName().equals(name)){
                allPossibilities.add(movie);
                System.out.println(count+"  "+movie.getMovieName());
                ++count;
            }
        }
        System.out.println("which one do you want?");
        Scanner input = new Scanner(System.in);
        int choose = Integer.parseInt(input.nextLine());
        return allPossibilities.get(choose-1);
    }

    public static User searchUser(ArrayList<User> users,String username){
        for(User user:users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }
    public static Forum searchForum(ArrayList<Forum> forums,String forumName){
        for(Forum forum:forums){
            if(forum.getForumName().equals(forumName)){
                return forum;
            }
        }
        return null;
    }
    public static People searchPeople(ArrayList<People> people,String movieOrPersonName){
        System.out.println("search for the person you want with his/her name");
        ArrayList<People> allPeopleThatCanBe=new ArrayList<>();
        for(People person:people){
            if(person.getName().equals(movieOrPersonName)){
                allPeopleThatCanBe.add(person);
            }
        }
        for(Movie movie:Admin.getAllMovies()){
            for(PeopleAndTypeInMovie peopleAndTypeInMovie:movie.getCraftsmen()){
                if(peopleAndTypeInMovie.getPeople().getName().equals(movieOrPersonName)){
                    allPeopleThatCanBe.add(peopleAndTypeInMovie.getPeople());
                }
            }
        }
        if(allPeopleThatCanBe.isEmpty()){
            System.out.println("cant find anyone!");
            return null;
        }
        System.out.println("people that found by your search from his/her name or movie name");
        int count=1;
        for(People person:allPeopleThatCanBe){
            System.out.println(count+person.getName());
            ++count;
        }
        System.out.println("choose the person you want");
        Scanner input=new Scanner(System.in);
        int choose=Integer.parseInt(input.nextLine());
        return allPeopleThatCanBe.get(choose-1);
    }
    public static Movie searchMovie(ArrayList<Movie> movies){
        Scanner input=new Scanner(System.in);
        System.out.println("enter the movie name or a key word to find the movie or '.' to not!");
        String movieName=input.nextLine();
        System.out.println("here you can filter your search!");
        System.out.println("choose genres you want to");
        ArrayList<Genre> genresFilter=new ArrayList<>();
        while (true){
            System.out.println("any key:no (another) genre\na:ACTION\nb:COMEDY\nc:HORROR\nd:DRAMA\ne:FANTASY\nf:ROMANCE\ng:MYSTERY\nh:HISTORICAL\n");
            String chooseFilterGenre=input.nextLine();
            if(chooseFilterGenre.equals("a")){
                genresFilter.add(Genre.ACTION);
            }
            else if(chooseFilterGenre.equals("b")){
                genresFilter.add(Genre.COMEDY);
            }
            else if(chooseFilterGenre.equals("c")){
                genresFilter.add(Genre.HORROR);
            }
            else if(chooseFilterGenre.equals("d")){
                genresFilter.add(Genre.DRAMA);
            }
            else if(chooseFilterGenre.equals("e")){
                genresFilter.add(Genre.FANTASY);
            }
            else if(chooseFilterGenre.equals("f")){
                genresFilter.add(Genre.ROMANCE);
            }
            else if(chooseFilterGenre.equals("g")){
                genresFilter.add(Genre.MYSTERY);
            }
            else if(chooseFilterGenre.equals("h")){
                genresFilter.add(Genre.HISTORICAL);
            }
            else{
                break;
            }
        }
        System.out.println("choose languages you want to");
        ArrayList<Language> languagesFilter=new ArrayList<>();
        while (true){
            System.out.println("any other number:no (another) language\n1:PERSIAN\n2:ENGLISH\n3:FRENCH\n4:ITALIAN\n5:RUSSIAN\n6:ARABIC\n");
            int chooseFilterLanguage;
            try {
                chooseFilterLanguage=Integer.parseInt(input.nextLine());
            }
            catch (Exception e){
                System.out.println("enter a number!");
                continue;
            }
            if(chooseFilterLanguage==1){
                languagesFilter.add(Language.PERSIAN);
            }
            else if(chooseFilterLanguage==2){
                languagesFilter.add(Language.ENGLISH);
            }
            else if(chooseFilterLanguage==3){
                languagesFilter.add(Language.FRENCH);
            }
            else if(chooseFilterLanguage==4){
                languagesFilter.add(Language.ITALIAN);
            }
            else if(chooseFilterLanguage==5){
                languagesFilter.add(Language.RUSSIAN);
            }
            else if(chooseFilterLanguage==6){
                languagesFilter.add(Language.ARABIC);
            }
            else{
                break;
            }
        }
        System.out.println("enter release year you want to or 0 to not");
        int year=Integer.parseInt(input.nextLine());
        int minRate=0;
        int maxRate=10;
        System.out.println("do you wanna change the rate?\na:yes\nany other key:no");
        String choose=input.nextLine();
        if(choose.equals("a")){
            System.out.println("enter the min rate");
            minRate=Integer.parseInt(input.nextLine());
            System.out.println("enter the max rate");
            maxRate=Integer.parseInt(input.nextLine());
        }
        ArrayList<Movie> moviePossibilities=new ArrayList<>();
        for(Movie movie:movies){
            if((year==0||year==movie.getReleaseDate().getYear())){
                int sum=0;
                int score=0;
                int count=0;
                for(Rate rate:movie.getRates()){
                    sum+=rate.getScore();
                    ++count;
                }
                if(count==0){
                    count=1;

                }
                score=sum/count;
                if(score>=minRate&&score<=maxRate){
                    if(!genresFilter.isEmpty()){
                        for(Genre genre:genresFilter){
                            if(movie.getMovieGenre().equals(genre)){
                                moviePossibilities.add(movie);
                                break;
                            }
                        }
                    }
                    else {
                        moviePossibilities.add(movie);
                    }
                }
            }
        }
        int flag=0;
        if(!languagesFilter.isEmpty()){
            for(Movie movie:moviePossibilities){
                for (Language language:languagesFilter){
                    if(movie.getMovieLanguage().equals(language)){
                        flag=1;
                        break;
                    }
                }
                if(flag==0){
                    moviePossibilities.remove(movie);
                }
            }
        }
        if(!movieName.equals(".")){
            for(Movie movie:moviePossibilities){
                if(!movie.getMovieName().contains(movieName)){
                    moviePossibilities.remove(movie);
                }
            }
        }
        int count=0;
        for (Movie movie:moviePossibilities){
            System.out.println(count+movie.getMovieName());
        }
        System.out.println("choose the movie you want!");
        int chooseMovie=Integer.parseInt(input.nextLine());
        return moviePossibilities.get(chooseMovie);

    }
}
