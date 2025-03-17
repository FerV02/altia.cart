package altia.cars.demo.domain.model;


public class User {
    private Long id;
    private String userName;
    private String userEmail;
    private String userPassword;
    private UserRole userRole;

    public User(Long id, String userName, String userEmail, String userPassword, UserRole userRole) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public UserRole getUserRole() {
        return userRole;
    }
}