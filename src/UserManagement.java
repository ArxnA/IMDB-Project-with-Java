import java.util.Scanner;

public class UserManagement {

    public static boolean IsAdmin(User user){
        return user instanceof Admin;
    }
    public static boolean IsEditor(User user){
        return user instanceof Editor;
    }
    public static boolean IsMember(User user){
        return user instanceof Member;
    }


    public static User login() {
        Scanner input = new Scanner(System.in);
        System.out.println("enter your username");
        String username = input.nextLine();
        while (isUsernameOk(username)) {
            System.out.println("the username doesnt exist try again!");
            username = input.nextLine();
        }
        for (User user : Admin.getAllUsers()) {
            if (user.getUsername().equals(username)) {
                while (true) {
                    System.out.println("enter your password");
                    String password = input.nextLine();
                    if (user.getPassword().equals(password)) {
                        return user;
                    } else {
                        System.out.println("the password is wrong try again!");
                    }
                }
            }
        }
        return null;
    }

    public static boolean isUsernameOk(String username) {
        for (User user : Admin.getAllUsers()) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public static User signup() {
        Scanner input = new Scanner(System.in);
        System.out.println("enter your name");
        String name=input.nextLine();
        System.out.println("enter your username");
        String username = input.nextLine();
        while (!isUsernameOk(username)) {
            System.out.println("the username has used try again!");
            username = input.nextLine();
        }
        System.out.println("enter your password");
        String password = input.nextLine();
        Member newMember = new Member(username, password,name);
        Admin.getAllUsers().add(newMember);
        return newMember;
    }
}
