package altia.cars.demo.infrastructure.rest.response;

import altia.cars.demo.domain.model.User;
import altia.cars.demo.domain.model.UserRole;

public class UserResponse {
    private Long id;
    private String userName;
    private String userEmail;
    private UserRole userRole;


    public UserResponse(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.userRole = user.getUserRole();
    }

    public UserResponse(Long id, String userName, String userEmail, UserRole userRole) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}