package altia.cars.demo.infrastructure.rest;

import altia.cars.demo.application.ports.in.UserServicePort;
import altia.cars.demo.domain.model.User;
import altia.cars.demo.infrastructure.rest.request.UserRequest;
import altia.cars.demo.infrastructure.rest.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserRestAdapter {
    private final UserServicePort userServicePort;

    public UserRestAdapter(UserServicePort userServicePort) {
        this.userServicePort = userServicePort;
    }

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest request) {
        User user = new User(
                null,
                request.getUserName(),
                request.getUserEmail(),
                request.getUserPassword(),
                request.getUserRole()
        );
        User createdUser = userServicePort.addUser(user);
        UserResponse response = new UserResponse(
                createdUser.getId(),
                createdUser.getUserName(),
                createdUser.getUserEmail(),
                createdUser.getUserRole()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        Optional<User> user = userServicePort.getUserById(id);
        return user.map(u -> ResponseEntity.ok(new UserResponse(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest request) {
        User user = new User(
                id,
                request.getUserName(),
                request.getUserEmail(),
                request.getUserPassword(),
                request.getUserRole()
        );
        Optional<User> updatedUser = userServicePort.updateUser(id, user);
        return updatedUser.map(u -> ResponseEntity.ok(new UserResponse(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUserByIds(@RequestBody List<Long> ids) {
        Boolean deleted = userServicePort.deleteUserByIds(ids);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAllUsers() {
        List<User> users = userServicePort.findAllUsers();
        List<UserResponse> responses = users.stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}