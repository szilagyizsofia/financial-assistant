package coinster.controller;

import coinster.model.Admin;
import coinster.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admins")
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

    @PostMapping("/create")
    public String create(@RequestBody Admin admin) {
        adminRepository.save(admin);
        return "Admin is created";
    }

    @GetMapping("/findall")
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @GetMapping("/findByUser/{username")
    public Admin findByUsername(@PathVariable String username) {
        return adminRepository.findByUsername(username);
    }

    @GetMapping("/findById/{id")
    public Admin findById(@PathVariable int id) {
        return adminRepository.findById(id);
    }
}
