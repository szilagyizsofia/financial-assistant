package coinster.controller;

import coinster.model.Admin;
import coinster.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

    @GetMapping("/createAdmin")
    public String create(){
        adminRepository.save(new Admin("Rajesh02", "admin"));
        return "Customer created";
    }
}
