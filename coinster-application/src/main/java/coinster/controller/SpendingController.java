package coinster.controller;

import coinster.model.Spending;
import coinster.model.Transaction;
import coinster.model.User;
import coinster.repository.SpendingRepository;
import coinster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("spendings")
public class SpendingController {

    @Autowired
    SpendingRepository spendingRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<Spending> create(@RequestBody Spending spending, Principal principal) {
        User owner = userRepository.findByUsername(principal.getName()).get();
        spending.setAmount(0 - spending.getAmount());
        spending.setOwner(owner);
        spending.setCurrency(owner.getCurrency());
        return ResponseEntity.ok(spendingRepository.save(spending));
    }

    @GetMapping("/findall")
    public List<Spending> findAll() {
        return spendingRepository.findAll();
    }

    @GetMapping("/findByOwner/{owner}")
    public List<Transaction> findByOwner(@PathVariable User owner) {
        return spendingRepository.findByOwner(owner);
    }

    @GetMapping("/findById/{id}")
    public Spending findById(@PathVariable int id) {
        return spendingRepository.findById(id);
    }
}
