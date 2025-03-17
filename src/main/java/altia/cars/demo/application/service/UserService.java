package altia.cars.demo.application.service;

import altia.cars.demo.application.ports.in.UserServicePort;
import altia.cars.demo.application.ports.out.UserRepositoryPort;
import altia.cars.demo.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServicePort {


    private final UserRepositoryPort userRepositoryPort;

    public UserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User addUser(User user) {
        return userRepositoryPort.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepositoryPort.findUserById(id);
    }

    @Override
    public Optional<User> updateUser(Long id, User user) {
        return userRepositoryPort.updateUser(id, user);
    }

    @Override
    public Boolean deleteUserByIds(List<Long> ids) {
        return userRepositoryPort.deleteByUsersIds(ids);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepositoryPort.findAllUsers();
    }
}
