import java.util.Date;
import java.util.Scanner;

public class IMDB {

    static Scanner input = new Scanner(System.in);
    static Admin admin=new Admin("admin","admin");


    public static void main(String[] args) throws Exception {
        Admin.getAllUsers().add(admin);
        System.out.println("-----------------------------------");
        System.out.println("|                                 |");
        System.out.println("|         Welcome to IMDB         |");
        System.out.println("|                                 |");
        System.out.println("-----------------------------------");
        IMDB imdb=new IMDB();
        imdb.start();
    }

    private void start() throws Exception{
        while (true){
            System.out.println("press 1 to signup, press 2 to login or any other number to exit from IMDB!");
            int choose;
            User user;
            try{
                choose=Integer.parseInt(input.nextLine());
            }catch (Exception e){
                System.out.println("You Must Enter Number");
                continue;
            }
            if (choose==1){
                user=UserManagement.signup();
                for(String a:Admin.getRules()){
                    System.out.println(a);
                }
                System.out.println("press y to accept rules or any other key to not");
                if(input.nextLine().equals("y")){
                    ((Member)user).setIsRulesOk(IsRulesOk.YES);
                }
            }
            else if (choose==2){
                user=UserManagement.login();
            }
            else {
                break;
            }
            if (UserManagement.IsMember(user)){
                if(((Member)user).getIsRulesOk().equals(IsRulesOk.NO)){
                    System.out.println("you cant use the site because you didnt accept the rules");
                    System.out.println("press y to accept the rules or any other key to not");
                    if(input.nextLine().equals("y")){
                        ((Member)user).setIsRulesOk(IsRulesOk.YES);
                    }
                }
                if((((Member)user).getIsRulesOk().equals(IsRulesOk.YES))&& !user.IsBanOrNot()){
                    while(true) {
                        WhatMemberCanDo();
                        int whatToDo;
                        try {
                            whatToDo = Integer.parseInt(input.nextLine());
                        } catch (Exception e) {
                            System.out.println("enter number!");
                            continue;
                        }
                        if (whatToDo == 1) {
                            while (true) {
                                ((Member) user).showMemberProfile();
                                System.out.println("1: change username");
                                System.out.println("2: change password");
                                System.out.println("3: change email");
                                System.out.println("4: change name");
                                System.out.println("5: change birthdate");
                                System.out.println("any other number to back to privies page");
                                int whichOne;
                                try {
                                    whichOne = Integer.parseInt(input.nextLine());
                                } catch (Exception e) {
                                    System.out.println("enter number!");
                                    continue;
                                }
                                if (whichOne == 1) {
                                    System.out.println("enter your new username");
                                    String username = input.nextLine();
                                    while (!UserManagement.isUsernameOk(username)) {
                                        System.out.println("the username has used try again!");
                                        username = input.nextLine();
                                    }
                                    user.setUsername(username);
                                } else if (whichOne == 2) {
                                    System.out.println("enter your new password");
                                    String password = input.nextLine();
                                    user.setPassword(password);
                                } else if (whichOne == 3) {
                                    System.out.println("enter your new email");
                                    String email = input.nextLine();
                                    user.setEmail(email);
                                } else if (whichOne == 4) {
                                    System.out.println("enter your name");
                                    String name = input.nextLine();
                                    user.setName(name);
                                } else if (whichOne == 5) {
                                    System.out.println("enter your birthdate");
                                    System.out.println("enter the year");
                                    int year = Integer.parseInt(input.nextLine());
                                    System.out.println("enter the month");
                                    int month = Integer.parseInt(input.nextLine());
                                    System.out.println("enter the date");
                                    int date = Integer.parseInt(input.nextLine());
                                    user.setDateOfBirth(new Date(year, month, date));
                                } else {
                                    break;
                                }
                            }
                        } else if (whatToDo == 2) {
                            System.out.println("press a to see recommend movies based on your taste profile or any other key to see new movies");
                            String whichOne = input.nextLine();
                            if (whichOne.equals("a")) {
                                RecommendationEngine.recommendMovie((Member) user);
                            } else {
                                RecommendationEngine.recommendNewMovies();
                            }
                        } else if (whatToDo == 3) {
                            System.out.println("enter the user username");
                            String name=input.nextLine();
                            if(SearchEngine.searchUser(Admin.getAllUsers(),name)!=null){
                                User searchedUser=SearchEngine.searchUser(Admin.getAllUsers(),name);
                                System.out.println("what do you want to do?\npress a to follow him or any other key to not");
                                if(input.nextLine().equals("a")){
                                    ((Member)user).getFriends().add(searchedUser);
                                }
                                if(searchedUser instanceof Member){
                                    System.out.println("this user is member you can make a direct chat with him/her");
                                    System.out.println("press a to start a direct chat or any other key to not");
                                    if(input.nextLine().equals("a")){
                                        DirectChat directChat=new DirectChat(((Member) user),((Member) searchedUser));
                                        ((Member) user).getChats().add(directChat);
                                        ((Member) searchedUser).getChats().add(directChat);
                                    }
                                }
                            }
                            else{
                                System.out.println("there is not any user with this username");
                            }
                        }
                        else if (whatToDo==4){
                            int counter=0;
                            if(((Member) user).getFriends().isEmpty()){
                                System.out.println("you dont have any friends");
                            }
                            else{
                                for(User following:((Member) user).getFriends()){
                                    System.out.println(counter+"    "+following.getName());
                                    ++counter;
                                }
                                System.out.println("if you want to choose a friend to see his/her profile enter the number of him/her in the list or any other number to not");
                                int next;
                                while (true){
                                    try {
                                        next=Integer.parseInt(input.nextLine());
                                    }
                                    catch (Exception e){
                                        System.out.println("enter a number");
                                        continue;
                                    }
                                    if(next<=((Member) user).getFriends().size()){
                                        User.showUserProfile(((Member) user).getFriends().get(next));
                                        System.out.println("press a if you want to unfollow him/her and any other number to not");
                                        if(input.nextLine().equals("a")){
                                            ((Member) user).getFriends().remove(((Member) user).getFriends().get(next));
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        else if (whatToDo==5){
                            System.out.println("do you want to make or join a forum?\npress m to make and any other key to join");
                            if(input.nextLine().equals("m")){
                                System.out.println("enter the forumName");
                                String name=input.nextLine();
                                while (!Forum.IsForumNameOk(name)){
                                    System.out.println("this forum is exist try another name");
                                    name=input.nextLine();
                                }
                                Forum newForum=new Forum(name);
                                newForum.getForumMembers().add((Member) user);
                                ((Member)user).getMemberForums().add(newForum);
                                Admin.getForums().add(newForum);
                            }
                            else{
                                int counter=0;
                                if(Admin.getForums().isEmpty()){
                                    System.out.println("there is not any forum!");
                                }
                                else{
                                    for(Forum forum:Admin.getForums()){
                                        System.out.println(counter+"    "+forum.getForumName());
                                        ++counter;
                                    }
                                    System.out.println("choose the number of forum you want to choose or any other number to not");
                                    int whichForum;
                                    while (true){
                                        try {
                                            whichForum=Integer.parseInt(input.nextLine());
                                        }
                                        catch (Exception e){
                                            System.out.println("enter a number");
                                            continue;
                                        }
                                        if(whichForum<Admin.getForums().size()&&whichForum>=0){
                                            Admin.getForums().get(whichForum).getForumMembers().add((Member) user);
                                            ((Member)user).getMemberForums().add(Admin.getForums().get(whichForum));
                                            System.out.println("you have joined successfully");
                                            break;
                                        }
                                        else{
                                            System.out.println("wrong number try again!");
                                        }
                                    }
                                }
                            }
                        }
                        else if(whatToDo==6){
                            int counter=0;
                            for(Forum forum:((Member)user).getMemberForums()){
                                System.out.println(counter+"    "+forum.getForumName());
                            }
                            System.out.println("choose the number of forum you want to choose");
                            int whichForum;
                            while (true) {
                                try {
                                    whichForum = Integer.parseInt(input.nextLine());
                                    break;
                                } catch (Exception e) {
                                    System.out.println("enter a number");
                                }
                            }
                            if(whichForum<((Member)user).getMemberForums().size()&&whichForum>=0){
                                System.out.println("press a if you want to leave the forum or any other number to send message");
                                if(input.nextLine().equals("a")){
                                    ((Member)user).getMemberForums().remove(Admin.getForums().get(whichForum));
                                }
                                else{
                                    System.out.println("send any message you want");
                                    ((Member)user).getMemberForums().get(whichForum).getForumMessages().add(new Message(input.nextLine(),(Member) user));
                                }
                                break;
                            }
                        }
                        else if (whatToDo==7){
                            Movie.seeMostPopularMovies();
                        }
                        else if (whatToDo==8){
                            System.out.println("press a to see genre category\npress b to see language category\npress c to see release date category\nany other key to non of them");
                            String whichOne=input.nextLine();
                            if(whichOne.equals("a")){
                                System.out.println("what genre do you want?");
                                System.out.println("a:ACTION\nb:COMEDY\nc:HORROR\nd:DRAMA\ne:FANTASY\nf:ROMANCE\ng:MYSTERY\nh:HISTORICAL\nany other key to not");
                                String chooseFilterGenre=input.nextLine();
                                if(chooseFilterGenre.equals("a")){
                                    Movie.seeGenreCategory(Genre.ACTION);
                                }
                                else if(chooseFilterGenre.equals("b")){
                                    Movie.seeGenreCategory(Genre.COMEDY);
                                }
                                else if(chooseFilterGenre.equals("c")){
                                    Movie.seeGenreCategory(Genre.HORROR);
                                }
                                else if(chooseFilterGenre.equals("d")){
                                    Movie.seeGenreCategory(Genre.DRAMA);
                                }
                                else if(chooseFilterGenre.equals("e")){
                                    Movie.seeGenreCategory(Genre.FANTASY);
                                }
                                else if(chooseFilterGenre.equals("f")){
                                    Movie.seeGenreCategory(Genre.ROMANCE);
                                }
                                else if(chooseFilterGenre.equals("g")){
                                    Movie.seeGenreCategory(Genre.MYSTERY);
                                }
                                else if(chooseFilterGenre.equals("h")){
                                    Movie.seeGenreCategory(Genre.HISTORICAL);
                                }
                                else{
                                    System.out.println("you didnt choose any genre!");
                                }
                            }
                            else if(whichOne.equals("b")){
                                System.out.println("what language do you want?");
                                System.out.println("a:PERSIAN\nb:ENGLISH\nc:FRENCH\nd:ITALIAN\ne:RUSSIAN\nf:ARABIC\nany other key to not");
                                String chooseFilterGenre=input.nextLine();
                                if(chooseFilterGenre.equals("a")){
                                    Movie.seeLanguageCategory(Language.PERSIAN);
                                }
                                else if(chooseFilterGenre.equals("b")){
                                    Movie.seeLanguageCategory(Language.ENGLISH);
                                }
                                else if(chooseFilterGenre.equals("c")){
                                    Movie.seeLanguageCategory(Language.FRENCH);
                                }
                                else if(chooseFilterGenre.equals("d")){
                                    Movie.seeLanguageCategory(Language.ITALIAN);
                                }
                                else if(chooseFilterGenre.equals("e")){
                                    Movie.seeLanguageCategory(Language.RUSSIAN);
                                }
                                else if(chooseFilterGenre.equals("f")){
                                    Movie.seeLanguageCategory(Language.ARABIC);
                                }
                                else{
                                    System.out.println("you didnt choose any language!");
                                }
                            }
                            else if(whichOne.equals("c")){
                                System.out.println("enter the release year");
                                int year=Integer.parseInt(input.nextLine());
                                Movie.seeReleaseDateCategory(year);
                            }
                        }
                        else if (whatToDo==9){
                            System.out.println("enter the name of the person that you want or a movie that belongs to him/her");
                            String name=input.nextLine();
                            People people=SearchEngine.searchPeople(Admin.getCraftsMen(),name);
                            if(people!=null){
                                people.showPeopleDetails();
                            }
                            System.out.println("press a to follow him or any other key to not");
                            if(input.nextLine().equals("a")){
                                ((Member)user).getPeopleWhoFollows().add(people);
                            }
                        }
                        else if (whatToDo==10){
                            int counter=0;
                            if(((Member) user).getPeopleWhoFollows().isEmpty()){
                                System.out.println("you didnt follow any one!");
                            }
                            else{
                                for(People person:((Member) user).getPeopleWhoFollows()){
                                    System.out.println(counter+"    "+person.getName());
                                    ++counter;
                                }
                                System.out.println("if you want to choose a person to see his/her movies enter the number of him/her in the list or any other number to not");
                                int next;
                                while (true){
                                    try {
                                        next=Integer.parseInt(input.nextLine());
                                    }
                                    catch (Exception e){
                                        System.out.println("enter a number");
                                        continue;
                                    }
                                    if(next<=((Member) user).getPeopleWhoFollows().size()){
                                        for(Movie movie:Admin.getAllMovies()){
                                            for(PeopleAndTypeInMovie people:movie.getCraftsmen()){
                                                if(people.getPeople().equals(((Member) user).getPeopleWhoFollows().get(next))){
                                                    System.out.println(movie.getMovieName());
                                                    break;
                                                }
                                            }
                                        }
                                        System.out.println("press a if you want to unfollow him/her and any other number to not");
                                        if(input.nextLine().equals("a")){
                                            ((Member) user).getPeopleWhoFollows().remove(((Member) user).getPeopleWhoFollows().get(next));
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        else if (whatToDo==11){
                            int count=0;
                            Movie movie=null;
                            while (movie==null){
                                System.out.println("enter the name of the movie!");
                                String name=input.nextLine();
                                for(Movie mvi:Admin.getAllMovies()){
                                    if(mvi.getMovieName().equals(name)){
                                        System.out.println(count);
                                        mvi.showMovieDetails();
                                        ++count;
                                    }
                                }
                                System.out.println("enter the number of movie that you want!");
                                int chooseMovie=Integer.parseInt(input.nextLine());
                                if(chooseMovie<=Admin.getAllMovies().size()){
                                    movie=Admin.getAllMovies().get(chooseMovie);
                                }
                                if(movie==null){
                                    System.out.println("cant find!");
                                }
                            }
                            System.out.println("write your review text");
                            String text=input.nextLine();
                            System.out.println("does your review has spoil?\n press y for yes and any other key to no");
                            IsSpoiler isSpoiler=IsSpoiler.NO;
                            if(input.nextLine().equals("y")){
                                isSpoiler=IsSpoiler.YES;
                            }
                            ((Member) user).writeReview(new Review(text,(Member) user,movie,isSpoiler),movie);
                        }
                        else if (whatToDo==12){
                            System.out.println("do you want to see all movies or search\n press a to see all movies and any other key to search");
                            String whichChoose=input.nextLine();
                            int count=0;
                            Movie chooseMovie;
                            if(whichChoose.equals("a")){
                                for (Movie movie:Admin.getAllMovies()){
                                    System.out.println(count+movie.getMovieName());
                                    ++count;
                                }
                                System.out.println("choose movie number!");
                                chooseMovie=Admin.getAllMovies().get(Integer.parseInt(input.nextLine()));
                            }
                            else{
                                System.out.println("a:advance search\nany other key search by name");
                                if(input.nextLine().equals("a")){
                                    chooseMovie=SearchEngine.searchMovie(Admin.getAllMovies());
                                }
                                else{
                                    System.out.println("enter the name");
                                    String name=input.nextLine();
                                    for (Movie movie:Admin.getAllMovies()){
                                        if(movie.getMovieName().equals(name)){
                                            System.out.println(count+movie.getMovieName());
                                            ++count;
                                        }
                                    }
                                    System.out.println("choose movie number!");
                                    chooseMovie=Admin.getAllMovies().get(Integer.parseInt(input.nextLine()));
                                }
                            }
                            Movie.whatToDoToAMovie(chooseMovie,input,user);
                        }
                        else if (whatToDo==13){
                            System.out.println("press a for your member lists and any other key for your personal lists");
                            if(input.nextLine().equals("a")){
                                System.out.println("which one of you member list do you want to see?");
                                System.out.println("press a for your favorites\npress b for your watchlist\npress any other key for your recently seen");
                                String whichOne=input.nextLine();
                                ListType listType;
                                if(whichOne.equals("a")){
                                    listType=ListType.FAVORITES;
                                }
                                else if(whichOne.equals("b")){
                                    listType=ListType.WATCHLIST;
                                }
                                else{
                                    listType=ListType.RECENTLYSEEN;
                                }
                                ((Member) user).searchList(listType);
                            }
                            else{
                                System.out.println("enter the personal list that you want");
                                String name=input.nextLine();
                                ((Member) user).searchPersonalList(name);
                            }
                        }
                        else if (whatToDo==14){
                            System.out.println("you can search for anything!");
                            System.out.println("enter the name of anything you want!");
                            String name=input.nextLine();
                            Object searchResult=SearchEngine.search(name);
                            if(searchResult instanceof User){
                                System.out.println("what do you want to do?\npress a to follow him or any other key to not");
                                if(input.nextLine().equals("a")){
                                    ((Member)user).getFriends().add((User) searchResult);
                                }
                            }
                            else if(searchResult instanceof People){
                                ((People)searchResult).showPeopleDetails();
                                System.out.println("press a to follow him or any other key to not");
                                if(input.nextLine().equals("a")){
                                    ((Member)user).getPeopleWhoFollows().add((People)searchResult);
                                }
                            }
                            else if(searchResult instanceof Movie){
                                Movie.whatToDoToAMovie(((Movie) searchResult),input,user);
                            }
                        }
                        else if (whatToDo==15){
                            System.out.println("enter the group name");
                            String groupName=input.nextLine();
                            GroupChat groupChat=new GroupChat((Member) user,groupName);
                            ((Member) user).getChats().add(groupChat);
                            while(true){
                                System.out.println("search and choose a member to add to group");
                                System.out.println("enter the user username or 0 to not add anyone else");
                                String name=input.nextLine();
                                if(name.equals("0")){
                                    break;
                                }
                                if(SearchEngine.searchUser(Admin.getAllUsers(),name)!=null){
                                    User searchedUser=SearchEngine.searchUser(Admin.getAllUsers(),name);
                                    if(searchedUser instanceof Member){
                                        if(!groupChat.getGroupMembers().contains((Member) searchedUser)){
                                            groupChat.getGroupMembers().add((Member) searchedUser);
                                        }
                                        else{
                                            System.out.println("the member has already in the group!");
                                        }
                                    }
                                }
                                else{
                                    System.out.println("there is not any user with this username");
                                }
                            }
                        }
                        else if (whatToDo==16){
                            int count=0;
                            for(Chat chat:((Member) user).getChats()){
                                System.out.println(count);
                                if(chat instanceof GroupChat){
                                    System.out.println(((GroupChat) chat).getGroupName());
                                }
                                else if(chat instanceof DirectChat){
                                    System.out.println("this is your direct chat with:  "+((DirectChat) chat).getSecondMember().getUsername());
                                }
                                ++count;
                            }
                            System.out.println("choose a chat if you want or any other number to not");
                            int whichChat;
                            while (true){
                                try {
                                    whichChat=Integer.parseInt(input.nextLine());
                                    break;
                                }
                                catch (Exception e){
                                    System.out.println("enter a number!");
                                }
                            }
                            if(whichChat<((Member) user).getChats().size()){
                                System.out.println("press a to see messages\npress b to send message\npress c to left and delete the chat\npress d to see members\npress any other key to do nothing");
                                String whichOption=input.nextLine();
                                if (whichOption.equals("a")){
                                    int countMessage=0;
                                    for(Message message:((Member) user).getChats().get(whichChat).getMessages()){
                                        System.out.println(countMessage);
                                        message.showMessageDetails();
                                        ++countMessage;
                                    }
                                    System.out.println("choose a message if you want or any other number to not");
                                    int which;
                                    while (true){
                                        try {
                                            which=Integer.parseInt(input.nextLine());
                                            break;
                                        }
                                        catch (Exception e){
                                            System.out.println("enter a number!");
                                        }
                                    }
                                    if(which<((Member) user).getChats().get(whichChat).getMessages().size()){
                                        System.out.println("press a to like this message\npress b to forward this message\npress c to delete the message\npress any other key to do nothing");
                                        String what=input.nextLine();
                                        if(what.equals("a")){
                                            ((Member) user).getChats().get(whichChat).getMessages().get(which).getLikes().add(new Like(((Member) user),((Member) user).getChats().get(whichChat).getMessages().get(which)));
                                        }
                                        else if(what.equals("b")){
                                            int countChat=0;
                                            for(Chat chat:((Member) user).getChats()){
                                                System.out.println(countChat);
                                                if(chat instanceof GroupChat){
                                                    System.out.println(((GroupChat) chat).getGroupName());
                                                }
                                                else if(chat instanceof DirectChat){
                                                    System.out.println("this is your direct chat with:  "+((DirectChat) chat).getSecondMember().getUsername());
                                                }
                                                ++countChat;
                                            }
                                            System.out.println("choose a chat if you want or any other number to not");
                                            int whichOfChats;
                                            while (true){
                                                try {
                                                    whichOfChats=Integer.parseInt(input.nextLine());
                                                    break;
                                                }
                                                catch (Exception e){
                                                    System.out.println("enter a number!");
                                                }
                                            }
                                            if(whichOfChats<((Member) user).getChats().size()){
                                                ((Member) user).getChats().get(whichOfChats).getMessages().add(((Member) user).getChats().get(whichChat).getMessages().get(which));
                                            }
                                        }
                                        else if(what.equals("c")){
                                            if(((Member) user).getChats().get(whichChat).getMessages().get(which).getSender().equals(((Member) user))){
                                                ((Member) user).getChats().get(whichChat).getMessages().remove(((Member) user).getChats().get(whichChat).getMessages().get(which));
                                            }
                                            else{
                                                System.out.println("you cant delete the message because you are not the sender!");
                                            }
                                        }
                                        if(((Member) user).getChats().get(whichChat) instanceof GroupChat){
                                            if(((GroupChat) ((Member) user).getChats().get(whichChat)).getAdmin().equals((Member) user)){
                                                System.out.println("you are admin press a to delete the message or any other key to do nothing");
                                                if(input.nextLine().equals("a")){
                                                    ((Member) user).getChats().get(whichChat).getMessages().remove(((Member) user).getChats().get(whichChat).getMessages().get(which));
                                                }
                                            }
                                        }
                                    }
                                }
                                else if (whichOption.equals("b")){
                                    System.out.println("enter a message text");
                                    String text=input.nextLine();
                                    ((Member) user).getChats().get(whichChat).getMessages().add(new Message(text,((Member) user)));
                                }
                                else if (whichOption.equals("c")){
                                    ((Member) user).getChats().remove(((Member) user).getChats().get(whichChat));
                                    if(((Member) user).getChats().get(whichChat) instanceof DirectChat){
                                        ((DirectChat) ((Member) user).getChats().get(whichChat)).setFirstMember(null);
                                    }
                                    else if(((Member) user).getChats().get(whichChat) instanceof GroupChat){
                                        ((GroupChat) ((Member) user).getChats().get(whichChat)).getGroupMembers().remove((Member) user);
                                        if(((GroupChat) ((Member) user).getChats().get(whichChat)).getAdmin().equals((Member) user)){
                                            ((GroupChat) ((Member) user).getChats().get(whichChat)).setAdmin(null);
                                        }
                                    }
                                }
                                else if (whichOption.equals("d")){
                                    if(((Member) user).getChats().get(whichChat) instanceof DirectChat){
                                        System.out.println("you and "+((DirectChat) ((Member) user).getChats().get(whichChat)).getSecondMember().getUsername());
                                    }
                                    else if(((Member) user).getChats().get(whichChat) instanceof GroupChat){
                                        int memberCount=0;
                                        for(Member member:((GroupChat) ((Member) user).getChats().get(whichChat)).getGroupMembers()){
                                            System.out.println(memberCount+"  "+member.getUsername());
                                            ++memberCount;
                                        }
                                        if(((GroupChat) ((Member) user).getChats().get(whichChat)).getAdmin().equals(((Member) user))){
                                            System.out.println("you are admin choose a member if you want");
                                            int whichMember;
                                            while (true){
                                                try {
                                                    whichMember=Integer.parseInt(input.nextLine());
                                                    break;
                                                }
                                                catch (Exception e){
                                                    System.out.println("enter a number");
                                                }
                                            }
                                            System.out.println("press a to remove him/her or any other number to not");
                                            if(input.nextLine().equals("a")){
                                                ((GroupChat) ((Member) user).getChats().get(whichChat)).getGroupMembers().remove(((GroupChat) ((Member) user).getChats().get(whichChat)).getGroupMembers().get(whichMember));
                                            }
                                        }
                                    }
                                }
                                if(((Member) user).getChats().get(whichChat) instanceof GroupChat){
                                    if(((GroupChat) ((Member) user).getChats().get(whichChat)).getAdmin().equals((Member) user)){
                                        System.out.println("you are admin you can add a member\npress a to add a member or any other key to not");
                                        if(input.nextLine().equals("a")){
                                            System.out.println("enter the user username");
                                            String name=input.nextLine();
                                            if(SearchEngine.searchUser(Admin.getAllUsers(),name)!=null) {
                                                User searchedUser = SearchEngine.searchUser(Admin.getAllUsers(), name);
                                                if(!((GroupChat) ((Member) user).getChats().get(whichChat)).getGroupMembers().contains(searchedUser)){
                                                    ((GroupChat) ((Member) user).getChats().get(whichChat)).getGroupMembers().add((Member) searchedUser);
                                                }
                                                else{
                                                    System.out.println("the user has already in the group");
                                                }
                                            }
                                            else{
                                                System.out.println("cant find a user with this username!");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else {
                            break;
                        }
                    }
                }
                else if (user.IsBanOrNot()){
                    System.out.println("you are ban");
                }
            }
            else if (UserManagement.IsEditor(user)){
                while (true){
                    whatEditorCanDo();
                    int whatToDo;
                    try {
                        whatToDo=Integer.parseInt(input.nextLine());
                    }
                    catch (Exception e){
                        System.out.println("enter a number!");
                        continue;
                    }if (whatToDo==1){
                        while (true) {
                            ((Editor) user).showEditorProfile();
                            System.out.println("1: change username");
                            System.out.println("2: change password");
                            System.out.println("3: change email");
                            System.out.println("4: change name");
                            System.out.println("5: change birthdate");
                            System.out.println("any other number to back to privies page");
                            int whichOne;
                            try {
                                whichOne = Integer.parseInt(input.nextLine());
                            } catch (Exception e) {
                                System.out.println("enter number!");
                                continue;
                            }
                            if (whichOne == 1) {
                                System.out.println("enter your new username");
                                String username = input.nextLine();
                                while (!UserManagement.isUsernameOk(username)) {
                                    System.out.println("the username has used try again!");
                                    username = input.nextLine();
                                }
                                user.setUsername(username);
                            } else if (whichOne == 2) {
                                System.out.println("enter your new password");
                                String password = input.nextLine();
                                user.setPassword(password);
                            } else if (whichOne == 3) {
                                System.out.println("enter your new email");
                                String email = input.nextLine();
                                user.setEmail(email);
                            } else if (whichOne == 4) {
                                System.out.println("enter your name");
                                String name = input.nextLine();
                                user.setName(name);
                            } else if (whichOne == 5) {
                                System.out.println("enter your birthdate");
                                System.out.println("enter the year");
                                int year = Integer.parseInt(input.nextLine());
                                System.out.println("enter the month");
                                int month = Integer.parseInt(input.nextLine());
                                System.out.println("enter the date");
                                int date = Integer.parseInt(input.nextLine());
                                user.setDateOfBirth(new Date(year, month, date));
                            } else {
                                break;
                            }
                        }
                    }
                    else if (whatToDo==2){
                        System.out.println("do you want to see all movies or search\n press a to see all movies and any other key to search");
                        String whichChoose=input.nextLine();
                        int count=0;
                        Movie chooseMovie;
                        if(whichChoose.equals("a")){
                            for (Movie movie:Admin.getAllMovies()){
                                System.out.println(count+movie.getMovieName());
                                ++count;
                            }
                            System.out.println("choose movie number!");
                            chooseMovie=Admin.getAllMovies().get(Integer.parseInt(input.nextLine()));
                        }
                        else{
                            System.out.println("a:advance search\nany other key search by name");
                            if(input.nextLine().equals("a")){
                                chooseMovie=SearchEngine.searchMovie(Admin.getAllMovies());
                            }
                            else{
                                System.out.println("enter the name");
                                String name=input.nextLine();
                                for (Movie movie:Admin.getAllMovies()){
                                    if(movie.getMovieName().equals(name)){
                                        System.out.println(count+movie.getMovieName());
                                        ++count;
                                    }
                                }
                                System.out.println("choose movie number!");
                                chooseMovie=Admin.getAllMovies().get(Integer.parseInt(input.nextLine()));
                            }
                        }
                        chooseMovie.showMovieDetails();
                        while (true) {
                            System.out.println("press a to suggest edit for this movie\npress b to report this movie\npress c to add missing data of this movie\npress d to see movie's reviews\npress any other key to exit");
                            String whichOne = input.nextLine();
                            if (whichOne.equals("a")) {
                                ((Editor) user).suggestEdit(chooseMovie);
                            } else if (whichOne.equals("b")) {
                                System.out.println("enter the report text");
                                String text = input.nextLine();
                                Admin.getReports().add(new Report(text, chooseMovie));
                            } else if (whichOne.equals("c")) {
                                ((Editor) user).addMissingData(chooseMovie);
                            } else if (whichOne.equals("d")) {
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
                                    System.out.println("press a to report this review\npress b to mark as is helpful or not\nany other key to do nothing");
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
                                }
                            } else {
                                break;
                            }
                        }
                    }
                    else if (whatToDo==3){
                        System.out.println("enter the name of the person that you want or a movie that belongs to him/her");
                        String name=input.nextLine();
                        People people=SearchEngine.searchPeople(Admin.getCraftsMen(),name);
                        if(people!=null){
                            people.showPeopleDetails();
                            System.out.println("press a to suggest edit or any other number to add missing data");
                            if(input.nextLine().equals("a")){
                                ((Editor) user).suggestPeopleEdit(people);
                            }
                            else{
                                ((Editor) user).addMissingDataPeople(people);
                            }
                        }
                        else{
                            System.out.println("cant find!");
                        }
                    }
                    else {
                        break;
                    }
                }
            }
            else if (UserManagement.IsAdmin(user)){
                while (true){
                    whatAdminCanDo();
                    int whatToDo;
                    try {
                        whatToDo=Integer.parseInt(input.nextLine());
                    }
                    catch (Exception e){
                        System.out.println("enter a number!");
                        continue;
                    }
                    if (whatToDo==1){
                        while (true) {
                            ((Admin) user).showAdminProfile();
                            System.out.println("1: change username");
                            System.out.println("2: change password");
                            System.out.println("3: change email");
                            System.out.println("4: change name");
                            System.out.println("5: change birthdate");
                            System.out.println("any other number to back to privies page");
                            int whichOne;
                            try {
                                whichOne = Integer.parseInt(input.nextLine());
                            } catch (Exception e) {
                                System.out.println("enter number!");
                                continue;
                            }
                            if (whichOne == 1) {
                                System.out.println("enter your new username");
                                String username = input.nextLine();
                                while (!UserManagement.isUsernameOk(username)) {
                                    System.out.println("the username has used try again!");
                                    username = input.nextLine();
                                }
                                user.setUsername(username);
                            } else if (whichOne == 2) {
                                System.out.println("enter your new password");
                                String password = input.nextLine();
                                user.setPassword(password);
                            } else if (whichOne == 3) {
                                System.out.println("enter your new email");
                                String email = input.nextLine();
                                user.setEmail(email);
                            } else if (whichOne == 4) {
                                System.out.println("enter your name");
                                String name = input.nextLine();
                                user.setName(name);
                            } else if (whichOne == 5) {
                                System.out.println("enter your birthdate");
                                System.out.println("enter the year");
                                int year = Integer.parseInt(input.nextLine());
                                System.out.println("enter the month");
                                int month = Integer.parseInt(input.nextLine());
                                System.out.println("enter the date");
                                int date = Integer.parseInt(input.nextLine());
                                user.setDateOfBirth(new Date(year, month, date));
                            } else {
                                break;
                            }
                        }
                    }
                    else if (whatToDo==2){
                        for(String rule:Admin.getRules()){
                            System.out.println(rule);
                        }
                        System.out.println("press a to add new rule or any other number to not");
                        if(input.nextLine().equals("a")){
                            System.out.println("enter the new rule");
                            Admin.getRules().add(input.nextLine());
                        }
                    }
                    else if (whatToDo==3){
                        ((Admin) user).approveRejectEdit();
                    }
                    else if (whatToDo==4){
                        ((Admin) user).approveRejectEditPeople();
                    }
                    else if (whatToDo==5){
                        for (Report report:Admin.getReports()){
                            if(report.getContent() instanceof Movie){
                                ((Movie) report.getContent()).showMovieDetails();
                            }
                            else if(report.getContent() instanceof Review){
                                ((Review) report.getContent()).showReviewDetails();
                            }
                            System.out.println("the text of the report is: "+report.getReportText());
                        }
                    }
                    else if (whatToDo==6){
                        ((Admin) user).addUser();
                    }
                    else if (whatToDo==7){
                        System.out.println("enter the user username");
                        String name=input.nextLine();
                        if(SearchEngine.searchUser(Admin.getAllUsers(),name)!=null){
                            User searchedUser=SearchEngine.searchUser(Admin.getAllUsers(),name);
                            if(searchedUser!=null){
                                System.out.println("what do you want to do?\npress a to delete\npress b to ban/unban\npress any other number to do nothing");
                                String whichToDo=input.nextLine();
                                if(whichToDo.equals("a")){
                                    Admin.getAllUsers().remove(searchedUser);
                                }
                                else if(whichToDo.equals("b")){
                                    System.out.println("press a to ban\npress any other key to unban");
                                    if(input.nextLine().equals("a")){
                                        searchedUser.setIsBan(IsBan.BAN);
                                    }
                                    else{
                                        searchedUser.setIsBan(IsBan.NOTBAN);
                                    }
                                }
                            }
                            else{
                                System.out.println("cant find!");
                            }
                        }
                        else{
                            System.out.println("there is not any user with this username");
                        }
                    }
                    else if (whatToDo==8){
                        System.out.println("enter the forum name");
                        String name=input.nextLine();
                        if(SearchEngine.searchForum(Admin.getForums(),name)!=null){
                            Forum searchedForum=SearchEngine.searchForum(Admin.getForums(),name);
                            if(searchedForum!=null){
                                System.out.println("what do you want to do?\npress a to delete forum\npress b remove a user from forum\npress c to delete a message from forum\npress any other number to do nothing");
                                String whichToDo=input.nextLine();
                                if(whichToDo.equals("a")){
                                    for(Member member:searchedForum.getForumMembers()){
                                        member.getMemberForums().remove(searchedForum);
                                    }
                                    Admin.getForums().remove(searchedForum);
                                }
                                else if(whichToDo.equals("b")){
                                    int count=0;
                                    for(Member member:searchedForum.getForumMembers()){
                                        System.out.println(count+"  "+member.getUsername());
                                        ++count;
                                    }
                                    System.out.println("choose the member that you want to remove or any other number to not");
                                    int whichMember;
                                    while (true){
                                        try {
                                            whichMember=Integer.parseInt(input.nextLine());
                                            break;
                                        }
                                        catch (Exception e){
                                            System.out.println("enter a number");
                                        }
                                    }
                                    if(whichMember<searchedForum.getForumMembers().size()){
                                        searchedForum.getForumMembers().get(whichMember).getMemberForums().remove(searchedForum);
                                        searchedForum.getForumMembers().remove(whichMember);
                                    }
                                }
                                else if(whichToDo.equals("c")){
                                    int count=0;
                                    for(Message message:searchedForum.getForumMessages()){
                                        System.out.println(count+"  "+message.getText()+"\nfrom: "+message.getSender().getUsername());
                                        ++count;
                                    }
                                    System.out.println("choose the message that you want to delete or any other number to not");
                                    int whichMessage;
                                    while (true){
                                        try {
                                            whichMessage=Integer.parseInt(input.nextLine());
                                            break;
                                        }
                                        catch (Exception e){
                                            System.out.println("enter a number");
                                        }
                                    }
                                    if(whichMessage<searchedForum.getForumMessages().size()){
                                        searchedForum.getForumMessages().remove(whichMessage);
                                    }
                                }
                            }
                            else{
                                System.out.println("cant find!");
                            }
                        }
                        else{
                            System.out.println("there is not any forum with this name");
                        }
                    }
                    else if (whatToDo==9){
                        System.out.println("press a to add new person\npress b to delete a person\npress c to edit a peron\npress any other key to do nothing");
                        String whichOne=input.nextLine();
                        if(whichOne.equals("a")){
                            System.out.println("enter the name of the person");
                            String name=input.nextLine();
                            System.out.println("enter the age of the person");
                            int age;
                            while (true){
                                try {
                                    age=Integer.parseInt(input.nextLine());
                                    break;
                                }
                                catch (Exception e){
                                    System.out.println("enter a number");
                                }
                            }
                            ((Admin) user).addNewPeople(name,age);
                        }
                        else if(whichOne.equals("b")){
                            int count=0;
                            for(People person:Admin.getCraftsMen()){
                                System.out.println(count);
                                person.showPeopleDetails();
                                ++count;
                            }
                            System.out.println("choose a person that you want or any other number to not");
                            int whichPerson;
                            while (true){
                                try {
                                    whichPerson=Integer.parseInt(input.nextLine());
                                    break;
                                }
                                catch (Exception e){
                                    System.out.println("enter a number");
                                }
                            }
                            if(whichPerson<Admin.getCraftsMen().size()){
                                ((Admin) user).deletePeople(whichPerson);
                                for(Movie movie:Admin.getAllMovies()){
                                    for(PeopleAndTypeInMovie peopleAndTypeInMovie:movie.getCraftsmen()){
                                        if(peopleAndTypeInMovie.getPeople().equals(Admin.getCraftsMen().get(whichPerson))){
                                            movie.getCraftsmen().remove(peopleAndTypeInMovie);
                                            break;
                                        }
                                    }
                                }
                                for(User usr:Admin.getAllUsers()){
                                    if(usr instanceof Member){
                                        for(People people:((Member) usr).getPeopleWhoFollows()){
                                            if(people.equals(Admin.getCraftsMen().get(whichPerson))){
                                                ((Member) usr).getPeopleWhoFollows().remove(people);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else if(whichOne.equals("c")){
                            System.out.println("enter the name of the person or a movie which is related to");
                            String name=input.nextLine();
                            People searchedPerson=SearchEngine.searchPeople(Admin.getCraftsMen(),name);
                            Admin.editPeople(searchedPerson);
                        }
                    }
                    else if (whatToDo==10){
                        System.out.println("press a to add new movie\npress b to delete a movie\npress c to edit a movie\npress any other key to do nothing");
                        String whichOne=input.nextLine();
                        if(whichOne.equals("a")){
                            System.out.println("enter the movie name");
                            String name=input.nextLine();
                            ((Admin) user).addNewMovie(name);
                        }
                        else if(whichOne.equals("b")){
                            Movie chooseMovie=SearchEngine.searchMovie(Admin.getAllMovies());
                            Admin.getAllMovies().remove(chooseMovie);
                            for (User member:Admin.getAllUsers()){
                                if(member instanceof Member){
                                    for (Review review:((Member) member).getMemberReviews()){
                                        if(review.getMovie().equals(chooseMovie)){
                                            ((Member) member).getMemberReviews().remove(review);
                                            break;
                                        }
                                    }
                                }
                            }
                            for (User member:Admin.getAllUsers()){
                                if(member instanceof Member){
                                    for (PersonalList personalList:((Member) member).getPersonalLists()){
                                        for (Movie movie:personalList.getPersonalListMovies()){
                                            if (movie.equals(chooseMovie)){
                                                personalList.getPersonalListMovies().remove(movie);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            for (User member:Admin.getAllUsers()){
                                if(member instanceof Member){
                                    for (MemberList memberList:((Member) member).getMemberLists()){
                                        for (Movie movie:memberList.getListMovies()){
                                            if (movie.equals(chooseMovie)){
                                                memberList.getListMovies().remove(movie);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else if(whichOne.equals("c")){
                            Movie searchedMovie=SearchEngine.searchMovie(Admin.getAllMovies());
                            Admin.editMovie(searchedMovie);
                        }
                    }
                    else if (whatToDo==11){
                        System.out.println("first search a movie to see its reviews");
                        Movie chooseMovie=SearchEngine.searchMovie(Admin.getAllMovies());
                        int count=0;
                        for(Review review:chooseMovie.getReviews()){
                            System.out.println(count+"  "+review.getText()+"\nfrom: "+review.getMember().getUsername()+"\n about: "+review.getMovie().getMovieName());
                            ++count;
                        }
                        System.out.println("choose a review that you want or any other number to not");
                        int whichReview;
                        while(true){
                            try {
                                whichReview=Integer.parseInt(input.nextLine());
                                break;
                            }catch (Exception e){
                                System.out.println("enter a number!");
                            }
                        }
                        if(whichReview<chooseMovie.getReviews().size()){
                            System.out.println("press a to delete this review or any other key to not");
                            if(input.nextLine().equals("a")){
                                chooseMovie.getReviews().remove(whichReview);
                                chooseMovie.getReviews().get(whichReview).getMember().getMemberReviews().remove(chooseMovie.getReviews().get(whichReview));
                            }
                        }
                    }
                    else {
                        break;
                    }
                }
            }
        }
    }

    private void WhatMemberCanDo(){
        System.out.println("what do you want to do?(press the number)");
        System.out.println("1: update your profile");
        System.out.println("2: get recommendation for movies");
        System.out.println("3: search users");
        System.out.println("4: see friends");
        System.out.println("5: search or make forums");
        System.out.println("6: see your forums and send message");
        System.out.println("7: see most popular movies");
        System.out.println("8: see movies based on genre/language/release date");
        System.out.println("9: search people");
        System.out.println("10: see people that you followed");
        System.out.println("11: write review");
        System.out.println("12: see movies");
        System.out.println("13: see your personal or member lists");
        System.out.println("14: search");
        System.out.println("15: make group chat");
        System.out.println("16: see your chats");
        System.out.println("any other number to logout!");
    }
    private void whatEditorCanDo(){
        System.out.println("what do you want to do?(press the number)");
        System.out.println("1: update your profile");
        System.out.println("2: see movies");
        System.out.println("3: see people");
        System.out.println("any other number to logout!");
    }
    private void whatAdminCanDo(){
        System.out.println("what do you want to do?(press the number)");
        System.out.println("1: update your profile");
        System.out.println("2: set new rules");
        System.out.println("3: approve or reject movie edits");
        System.out.println("4: approve or reject person edits");
        System.out.println("5: see reports");
        System.out.println("6: add user");
        System.out.println("7: users handling");
        System.out.println("8: forums handling");
        System.out.println("9: people handling");
        System.out.println("10: movies handling");
        System.out.println("11: reviews handling");
        System.out.println("any other number to logout!");
    }
}
