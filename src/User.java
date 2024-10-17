import java.util.Date;
import java.util.Scanner;

public class User {
    private IsBan isBan=IsBan.NOTBAN;
    private String username;
    private String password;
    private String email;
    private String name;
    private Date dateOfBirth;

    public String getEmail() {
        return email;
    }

    public static void showUserProfile(User user){
        if(user instanceof Admin){
            System.out.println("you cant see admin profile");
        }
        else if(user instanceof Editor){
            System.out.println("Name: "+user.getName()+"\n"+"Date of birth: "+user.getDateOfBirth()+"\n"+"Roll: Admin\n");
        }
        else if(user instanceof Member){
            ((Member) user).showMemberProfile();
        }
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public IsBan getIsBan() {
        return isBan;
    }

    public void setIsBan(IsBan isBan) {
        this.isBan = isBan;
    }

    public Boolean IsBanOrNot(){
        return isBan.equals(IsBan.BAN);
    }

}
