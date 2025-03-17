package altia.cars.demo.application.ports.out;

import altia.cars.demo.domain.model.User;


import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findUserById(Long id);
    Optional<User> updateUser(Long id, User user);
    Boolean deleteByUsersIds(List<Long> ids);
    List<User> findAllUsers();
}
