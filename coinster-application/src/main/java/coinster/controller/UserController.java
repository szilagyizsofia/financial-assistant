package coinster.controller;

import coinster.model.CurrencyUsed;
import coinster.model.Plan;
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

    @GetMapping("/testcreateUser")
    public String testcreateUser(){
        userRepository.save(new User("Rajesh01", "Bhojwani", Plan.regular, CurrencyUsed.USD));
        return "Customer created";
    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody User user){
        userRepository.save(user);
        return "User is created";
    }

    @GetMapping("/findallUser")
    public List<User> findAllUser(){
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
