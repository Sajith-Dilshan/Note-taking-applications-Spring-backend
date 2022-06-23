package lk.ijse.dep8.note.api;

import lk.ijse.dep8.note.dto.UserDTO;
import lk.ijse.dep8.note.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public UserDTO registerUser(@RequestBody UserDTO user) {
        // Todo: Validate the user
        return userService.registerUser(user);
    }

    @GetMapping(path = "/{userId:[A-Fa-f0-9\\-]{36}}", produces = "application/json")
    public UserDTO getUserInfo(@PathVariable String userId) {
        return userService.getUserInfo(userId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{userId:[A-Fa-f0-9\\-]{36}}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(path = "/{userId:[A-Fa-f0-9\\-]{36}}", consumes = "application/json")
    public void updateUser(@PathVariable String userId, @RequestBody UserDTO user) {
        /* Todo: Validate the user */

        user.setId(userId);
        userService.updateUser(user);
    }
}
