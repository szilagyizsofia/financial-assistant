package coinster.controller;

import coinster.model.Income;
import coinster.model.Transaction;
import coinster.model.User;
import coinster.repository.IncomeRepository;
import coinster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("incomes")
public class IncomeController {

    @Autowired
    IncomeRepository incomeRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<Income> create(@RequestBody Income income, Principal principal) {
        User owner = userRepository.findByUsername(principal.getName()).get();
        income.setOwner(owner);
        income.setCurrency(owner.getCurrency());
        income.setCreatedAt(new Date());
        return ResponseEntity.ok(incomeRepository.save(income));
    }


    @GetMapping("/findall")
    public List<Income> findAll() {
        return incomeRepository.findAll();
    }

    @GetMapping("/findByOwner/{owner}")
    public List<Transaction> findByOwner(@PathVariable User owner) {
        return incomeRepository.findByOwner(owner);
    }

    @GetMapping("/findById/{id}")
    public Income findById(@PathVariable int id) {
        return incomeRepository.findById(id);
    }
}
