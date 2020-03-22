package coinster.controller;

import coinster.model.User;
import coinster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/create")
    public String create(@RequestBody User user) {
        userRepository.save(user);
        return "User is created";
    }

    @GetMapping("/findall")
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/findByUser/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    @GetMapping("/findById/{id}")
    public User getUserById(@PathVariable int id) {
        return userRepository.findById(id);
    }

}
