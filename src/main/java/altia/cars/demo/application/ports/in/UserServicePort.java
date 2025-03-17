package altia.cars.demo.application.ports.in;

import altia.cars.demo.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserServicePort {

    User addUser(User user);
    Optional<User> getUserById(Long id);
    Optional<User> updateUser(Long id, User user);
    Boolean deleteUserByIds(List<Long> ids);
    List<User> findAllUsers();
}
