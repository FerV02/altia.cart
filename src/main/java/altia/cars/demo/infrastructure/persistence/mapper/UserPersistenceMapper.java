package altia.cars.demo.infrastructure.persistence.mapper;

import altia.cars.demo.domain.model.User;
import altia.cars.demo.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceMapper {

    public User toDomain(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        return new User(
                userEntity.getId(),
                userEntity.getUserName(),
                userEntity.getUserEmail(),
                userEntity.getUserPassword(),
                userEntity.getUserRole()
        );
    }

    public UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setUserName(user.getUserName());
        userEntity.setUserEmail(user.getUserEmail());
        userEntity.setUserPassword(user.getUserPassword());
        userEntity.setUserRole(user.getUserRole());
        return userEntity;
    }
}
