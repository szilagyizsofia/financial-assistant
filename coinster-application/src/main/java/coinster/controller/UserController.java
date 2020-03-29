package coinster.controller;

import coinster.model.Plan;
import coinster.model.User;
import coinster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("users")
    public String create(@RequestBody User user) {
        userRepository.save(user);
        return "User is created";
    }

    @GetMapping("/users")
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/users/findByUsername/{username}")
    public Optional<User> getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    @GetMapping("/users/findById/{id}")
    public Optional<User> getUserById(@PathVariable int id) {
        return userRepository.findById(id);
    }

    @GetMapping("/users/{id}/changePlan")
    public String upgradePlan(@PathVariable int id) {
        User user = userRepository.findById(id).get();
        Plan plan;
        if (user.getPlan().equals(Plan.premium)) {
            user.setPlan(Plan.regular);
            plan = Plan.regular;
        } else {
            user.setPlan(Plan.premium);
            plan = Plan.premium;
        }
        return "User: " + user.getUsername() + ", plan changed to: " + plan;
    }

}
