package services.model;


public class User {
    private String email;
    private String username;
    private String password;
    private final UserType userType;

    public User(UserType userType) {
        this.userType = userType;
    }

    public User(UserType userType, String username) {
        this.username = username;
        this.userType = userType;
    }

    public User(UserType userType, String password, String username) {
        this.userType = userType;
        this.password = password;
        this.username = username;
    }

    public User(UserType userType, String password, String username, String email) {
        this.userType = userType;
        this.password = password;
        this.username = username;
        this.email = email;
    }
}
