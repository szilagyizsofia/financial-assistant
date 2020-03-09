package coinster.controller;

import coinster.model.Admin;
import coinster.model.User;
import coinster.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

    @GetMapping("/testcreateAdmin")
    public String testcreateAdmin(){
        adminRepository.save(new Admin("Rajesh02", "admin"));
        return "Customer created";
    }

    @PostMapping("/createAdmin")
    public String createAdmin(@RequestBody Admin admin){
        adminRepository.save(admin);
        return "Admin is created";
    }

    @GetMapping("/findallAdmin")
    public List<Admin> findAllAdmin(){
        List<Admin> admins = adminRepository.findAll();
        return admins;
    }
}
