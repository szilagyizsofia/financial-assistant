package coinster.controller;

import coinster.model.CurrencyUsed;
import coinster.model.Plan;
import coinster.model.User;
import coinster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/create")
    public String create(){
        userRepository.save(new User("Rajesh01", "Bhojwani", Plan.regular, CurrencyUsed.USD));
        return "Customer created";
    }
}
