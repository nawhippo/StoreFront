package NateGroup.StoreFront.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/account")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping("/{id}/alterAccount")
    public Optional<User> updateUser(@RequestBody ){
        return user
    }
}
