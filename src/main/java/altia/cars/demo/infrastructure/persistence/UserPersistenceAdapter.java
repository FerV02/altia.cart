package altia.cars.demo.infrastructure.persistence;

import altia.cars.demo.application.ports.out.UserRepositoryPort;
import altia.cars.demo.domain.model.User;
import altia.cars.demo.infrastructure.persistence.entity.UserEntity;
import altia.cars.demo.infrastructure.persistence.mapper.UserPersistenceMapper;
import altia.cars.demo.infrastructure.persistence.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserPersistenceAdapter implements UserRepositoryPort {
    private final UserRepository userRepository;
        private final UserPersistenceMapper userPersistenceMapper;

        public UserPersistenceAdapter(UserRepository userRepository, UserPersistenceMapper userPersistenceMapper) {
            this.userRepository = userRepository;
            this.userPersistenceMapper = userPersistenceMapper;
        }

        @Override
        public User save (User user) {
            try {
                UserEntity userEntity = userPersistenceMapper.toEntity(user);
                return userPersistenceMapper.toDomain(userRepository.save(userEntity));
            } catch (Exception e) {
                throw new PersistenceException("Error saving user", e);
            }
        }

        @Override
        public Optional<User> findUserById(Long id) {
            try {
                return userRepository.findById(id).map(userPersistenceMapper::toDomain);
            } catch (Exception e) {
                throw new PersistenceException("Error finding user by ID", e);
            }
        }

        @Override
        public List<User> findAllUsers() {
            try {
                return userRepository.findAll()
                        .stream()
                        .map(userPersistenceMapper::toDomain)
                        .collect(Collectors.toList());
            } catch (Exception e) {
                throw new PersistenceException("Error finding all users", e);
            }
        }

        @Override
        public Boolean deleteByUsersIds(List<Long> ids) {
            try {
                userRepository.deleteAllById(ids);
                return true;
            } catch (EmptyResultDataAccessException ex) {
                return false;
            } catch (Exception e) {
                throw new PersistenceException("Error deleting cars by IDs", e);
            }
        }


        @Override
        public Optional<User> updateUser(Long id, User user) {
            try {
                return userRepository.findById(id).map(existingUserEntity -> {
                    if (user.getUserName() != null) {
                    existingUserEntity.setUserName(user.getUserName());
                    }
                    if (user.getUserEmail() != null) {
                    existingUserEntity.setUserEmail(user.getUserEmail());
                    }
                    if (user.getUserPassword() != null) {
                    existingUserEntity.setUserPassword(user.getUserPassword());
                    }
                    UserEntity savedUserEntity = userRepository.save(existingUserEntity);
                    return Optional.of(userPersistenceMapper.toDomain(savedUserEntity));
                }).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
            } catch (Exception e) {
                throw new PersistenceException("Error updating user", e);
            }
        }
}
