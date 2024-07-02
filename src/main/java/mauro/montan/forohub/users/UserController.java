package mauro.montan.forohub.users;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user, UriComponentsBuilder ucb) {

        var newUser =  userService.createUser(user);

        URI userLocation = ucb
                .path("users/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();

        return ResponseEntity
                .created(userLocation)
                .body(newUser.toProfileResponse());
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserProfileResponse> getUserById(@PathVariable(name = "id") @Valid Long id) {

        return ResponseEntity
                .ok(userService.findById(id));
    }
}
