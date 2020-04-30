package coinster.controller;

import coinster.model.Plan;
import coinster.model.Role;
import coinster.model.User;
import coinster.repository.UserRepository;
import coinster.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticatedUser authenticatedUser;

    @PostMapping("users/create")
    public ResponseEntity<Object> create(@RequestBody User user) {
        Optional<User> oUser = userRepository.findByUsername(user.getUsername());
        if (oUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.BASIC);
        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("users/logout")
    public ResponseEntity<String> logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.ok("ok");
    }

    @PostMapping("users/login")
    public ResponseEntity login() {
        return ResponseEntity.ok(authenticatedUser.getUser());
    }

    @GetMapping("/users")
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/users/{username}")
    public Optional<User> getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
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
