import java.util.ArrayList;
import java.util.Scanner;

public class Member extends User {

    private ArrayList<PersonalList> personalLists=new ArrayList<>();
    private ArrayList<Forum> memberForums=new ArrayList<>();
    private ArrayList<MemberList> memberLists=new ArrayList<>();
    private ArrayList<People> peopleWhoFollows=new ArrayList<>();
    private ArrayList<Review> memberReviews=new ArrayList<>();
    private ArrayList<Rate> memberRates=new ArrayList<>();
    private ArrayList<User> friends=new ArrayList<>();
    private ArrayList<Chat> chats=new ArrayList<>();
    private IsRulesOk isRulesOk=IsRulesOk.NO;

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    }

    public ArrayList<Forum> getMemberForums() {
        return memberForums;
    }

    public void setMemberForums(ArrayList<Forum> memberForums) {
        this.memberForums = memberForums;
    }

    public void searchPersonalList(String listName){
        int flag=0;
        for(PersonalList list:personalLists){
            flag=1;
            if(list.getListName().equals(listName)){
                if(list.getPersonalListMovies().isEmpty()){
                    System.out.println("your list is empty");
                }
                else{
                    for(Movie movie:list.getPersonalListMovies()){
                        System.out.println(movie.getMovieName());
                    }
                }
            }
        }
        if(flag==0){
            System.out.println("you dont have any personal list with this name");
        }
    }
    public void searchList(ListType listType){
        for(MemberList memberList:memberLists){
            if(memberList.getListType().equals(listType)){
                if(memberList.getListMovies().isEmpty()){
                    System.out.println("your list is empty");
                }
                else{
                    for(Movie movie:memberList.getListMovies()){
                        System.out.println(movie.getMovieName());
                    }
                }
            }
        }
    }
    public void addMovieToPersonalList(String personalListName,Movie movie){
        int isOk=0;
        for(PersonalList personalList:personalLists){
            if(personalList.getListName().equals(personalListName)){
                personalList.addMovie(movie);
                isOk=1;
                break;
            }
        }
        if(isOk==0){
            PersonalList newPersonalList=new PersonalList(personalListName);
            newPersonalList.addMovie(movie);
            personalLists.add(newPersonalList);
        }
    }

    public void addToList(ListType listType,Movie movie){
        int isOk=0;
        for(MemberList memberList:memberLists){
            if(memberList.getListType().equals(listType)){
                memberList.addToListMovies(movie);
                isOk=1;
                break;
            }
        }
        if(isOk==0){
            MemberList newMemberList=new MemberList(listType);
            newMemberList.addToListMovies(movie)    ;
            memberLists.add(newMemberList);

        }
    }

    public ArrayList<MemberList> getMemberLists() {
        return memberLists;
    }

    public void setMemberLists(ArrayList<MemberList> memberLists) {
        this.memberLists = memberLists;
    }

    public IsRulesOk getIsRulesOk() {
        return isRulesOk;
    }

    public void setIsRulesOk(IsRulesOk isRulesOk) {
        this.isRulesOk = isRulesOk;
    }

    public Member(String username, String password,String name){
        setUsername(username);
        setPassword(password);
        setName(name);
    }

    public void sendMessage(String text,Member member,Forum forum){
        forum.addMessage(new Message(text,member));
    }

    public ArrayList<Rate> getMemberRates() {
        return memberRates;
    }

    public void setMemberRates(ArrayList<Rate> memberRates) {
        this.memberRates = memberRates;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public ArrayList<Review> getMemberReviews() {
        return memberReviews;
    }

    public void setMemberReviews(ArrayList<Review> memberReviews) {
        this.memberReviews = memberReviews;
    }

    public ArrayList<Rate> getRates() {
        return memberRates;
    }

    public void setRates(ArrayList<Rate> rates) {
        this.memberRates = rates;
    }

    public void addFollowing(People people){
        peopleWhoFollows.add(people);
    }

    public ArrayList<People> getPeopleWhoFollows() {
        return peopleWhoFollows;
    }

    public void setPeopleWhoFollows(ArrayList<People> peopleWhoFollows) {
        this.peopleWhoFollows = peopleWhoFollows;
    }

    public void addPersonalList(PersonalList personalList){
        personalLists.add(personalList);
    }

    public ArrayList<PersonalList> getPersonalLists() {
        return personalLists;
    }

    public void setPersonalLists(ArrayList<PersonalList> personalLists) {
        this.personalLists = personalLists;
    }

    public void rate(Movie movie, Rate Rates){
        movie.addRating(Rates);
        memberRates.add(Rates);
    }

    public void writeReview(Review review,Movie movie){
        movie.addReview(review);
        memberReviews.add(review);
    }

    public void addFriend(Member member){
        friends.add(member);
    }

    public void reporting(String text,Object object){
        Admin.addReport(text,object);
    }

    public void showMemberProfile(){
        Scanner input=new Scanner(System.in);
        System.out.println("username: "+getUsername());
        System.out.println("Name: "+getName());
        System.out.println("email: "+getEmail());
        System.out.println("Date of birth: "+getDateOfBirth());
        System.out.println("Roll: Member");
        System.out.println("which one do you want to see?");
        boolean flag=true;
        while(flag){
            System.out.println("1: lists\n2: followings\n3: friends\n4: reviews\n5: rates\n any other number to not see any more");
            int which;
            try {
                which=Integer.parseInt(input.nextLine());
            }
            catch (Exception e){
                System.out.println("enter number!");
                continue;
            }
            switch (which){
                case 1:
                    for(MemberList memberList:getMemberLists()){
                        if(memberList.getListType().equals(ListType.FAVORITES)){
                            System.out.println("favorite movies are:\n");
                        }
                        else if(memberList.getListType().equals(ListType.RECENTLYSEEN)){
                            System.out.println("recently seen movies are:\n");
                        }
                        else if(memberList.getListType().equals(ListType.WATCHLIST)){
                            System.out.println("watchlist movies are:\n");
                        }
                        if(memberList.getListMovies().isEmpty()){
                            System.out.println("the list is empty!");
                        }
                        else {
                            for (Movie movie:memberList.getListMovies()){
                                System.out.println(movie.getMovieName());
                            }
                        }
                    }
                    break;
                case 2:
                    if(peopleWhoFollows.isEmpty()){
                        System.out.println("you didnt follow any person");
                    }
                    else{
                        for (People people:peopleWhoFollows){
                            System.out.println(people.getName());
                        }
                    }
                    break;
                case 3:
                    if(friends.isEmpty()){
                        System.out.println("you dont have any friend!");
                    }
                    else {
                        for (User user:friends){
                            System.out.println(user.getName());
                        }
                    }
                    break;
                case 4:
                    if(memberReviews.isEmpty()){
                        System.out.println("you didnt write any reviews!");
                    }
                    else {
                        for(Review review:memberReviews){
                            System.out.println("this review is about "+review.getMovie());
                            if(review.getIsSpoiler().equals(IsSpoiler.YES)){
                                System.out.println("the review has spoiler alert!");
                            }
                            else if(review.getIsSpoiler().equals(IsSpoiler.NO)){
                                System.out.println("the review doesnt spoil anything!");
                            }
                            int helpful=0;
                            int notHelpful=0;
                            for(IsHelpful isHelpful:review.getMembersOpinions()){
                                if(isHelpful.equals(IsHelpful.YES)){
                                    ++helpful;
                                }
                                else if(isHelpful.equals(IsHelpful.NO)){
                                    ++notHelpful;
                                }
                            }
                            if(helpful>=notHelpful){
                                System.out.println("the review is helpful!");
                            }
                            else {
                                System.out.println("the review is not helpful!");
                            }
                            System.out.println(review.getText());
                        }
                    }
                    break;
                case 5:
                    if(memberRates.isEmpty()){
                        System.out.println("you didnt rate any movie!");
                    }
                    else {
                        for (Rate rate : memberRates) {
                            System.out.println("this rate is about " + rate.getMovieThatRated().getMovieName() + " and the rate that he/she rated to this movie is " + rate.getScore());
                        }
                    }
                    break;
                default:
                    flag=false;
                    break;
            }
        }
    }
}
