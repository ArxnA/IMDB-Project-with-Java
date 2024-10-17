import java.util.Date;

public class RecommendationEngine {



    public static void recommendNewMovies(){
        Date todayDate=new Date();
        if(Admin.getAllMovies().isEmpty()){
            System.out.println("there is no movie in this site!");
            return;
        }
        for (Movie movie:Admin.getAllMovies()){
            if(todayDate.getYear()-movie.getReleaseDate().getYear()<=1){
                System.out.println(movie.getMovieName());
            }
        }
    }

    public static void recommendMovie(Member member){
        if (member.getMemberLists().isEmpty()){
            System.out.println("you dont have any member list to get recommendation!");
            return;
        }
        for (MemberList memberList:member.getMemberLists()){
            if (memberList.getListType().equals(ListType.FAVORITES)) {
                System.out.println("recommendation movies based on your favorite list");
            } else if (memberList.getListType().equals(ListType.WATCHLIST)) {
                System.out.println("recommendation movies based on your watchlist list");
            } else if (memberList.getListType().equals(ListType.RECENTLYSEEN)) {
                System.out.println("recommendation movies based on your recently seen list");
            }
            int count=0;
            for(Movie movie:Admin.getAllMovies()) {
                if(count<3&&count<Admin.getAllMovies().size()){
                    for (Movie mvi : memberList.getListMovies()) {
                        if(movie.getMovieGenre().equals(mvi.getMovieGenre())&&!movie.equals(mvi)){
                            System.out.println(movie.getMovieName());
                            ++count;
                            break;
                        }
                    }
                }
                else {
                    break;
                }
            }
        }
        System.out.println("recommendation based on your rates");
        int count1=0;
        for(Movie movie:Admin.getAllMovies()){
            if(count1<3&&count1<Admin.getAllMovies().size()) {
                for (Rate rate : member.getMemberRates()) {
                    if (rate.getScore() >= 8.5) {
                        if (movie.getMovieGenre().equals(rate.getMovieThatRated().getMovieGenre())&&!movie.equals(rate.getMovieThatRated())) {
                            System.out.println(movie.getMovieName());
                            ++count1;
                        }
                    }
                }
            }
            else {
                break;
            }
        }

//        for(MemberList memberList:member.getMemberLists()){
//            if(memberList.getListType().equals(ListType.FAVORITES)){
//                System.out.println("recommendation movies based on your favorite list");
//            }
//            else if(memberList.getListType().equals(ListType.WATCHLIST)){
//                System.out.println("recommendation movies based on your watchlist list");
//            }
//            else if(memberList.getListType().equals(ListType.RECENTLYSEEN)){
//                System.out.println("recommendation movies based on your recently seen list");
//            }
//            for (Movie movie:memberList.getListMovies()){
//                for (Movie mvi:Admin.getAllMovies()){
//                    if(mvi.getMovieGenre().equals(movie.getMovieGenre())){
//                        System.out.println(mvi.getMovieName());
//                    }
//                }
//            }
//        }
//        System.out.println("recommendation based on your rates");
//        Date todayDate=new Date();
//        for(Rate rate:member.getMemberRates()){
//            if(rate.getScore()>=8.5){
//                for(Movie movie:Admin.getAllMovies()){
//                    if(movie.getMovieGenre().equals(rate.getMovieThatRated().getMovieGenre())){
//                        System.out.println(movie.getMovieName());
//                    }
//                }
//            }
//        }
    }


//    public static Genre getFavoriteGenre(Member member){
//        int[] genreNumbers= {0,0,0,0,0,0,0,0};
//        for(MemberList memberList:member.getMemberLists()){
//            for (Movie movie:memberList.getListMovies()){
//                if(movie.getMovieGenre().equals(Genre.ACTION)){
//                    genreNumbers[0]++;
//                }
//                else if(movie.getMovieGenre().equals(Genre.COMEDY)){
//                    genreNumbers[1]++;
//                }
//                else if(movie.getMovieGenre().equals(Genre.HORROR)){
//                    genreNumbers[2]++;
//                }
//                else if(movie.getMovieGenre().equals(Genre.DRAMA)){
//                    genreNumbers[3]++;
//                }
//                else if(movie.getMovieGenre().equals(Genre.FANTASY)){
//                    genreNumbers[4]++;
//                }
//                else if(movie.getMovieGenre().equals(Genre.ROMANCE)){
//                    genreNumbers[5]++;
//                }
//                else if(movie.getMovieGenre().equals(Genre.MYSTERY)){
//                    genreNumbers[6]++;
//                }
//                else if(movie.getMovieGenre().equals(Genre.HISTORICAL)){
//                    genreNumbers[7]++;
//                }
//            }
//        }
//        int maxIndex=0;
//        for (int i=0;i<8;++i){
//            if(genreNumbers[i]>=maxIndex){
//                maxIndex=i;
//            }
//        }
//        if(maxIndex==0){
//            return Genre.ACTION;
//        }
//        else if(maxIndex==1){
//            return Genre.COMEDY;
//        }
//        else if(maxIndex==2){
//            return Genre.HORROR;
//        }
//        else if(maxIndex==3){
//            return Genre.DRAMA;
//        }
//        else if(maxIndex==4){
//            return Genre.FANTASY;
//        }
//        else if(maxIndex==5){
//            return Genre.ROMANCE;
//        }
//        else if(maxIndex==6){
//            return Genre.MYSTERY;
//        }
//        else if(maxIndex==7){
//            return Genre.HISTORICAL;
//        }
//        return null;
//    }
}
