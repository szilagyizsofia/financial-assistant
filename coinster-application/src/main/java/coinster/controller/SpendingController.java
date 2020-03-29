package coinster.controller;

import coinster.model.Spending;
import coinster.model.Transaction;
import coinster.repository.SpendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("spendings")
public class SpendingController {

    @Autowired
    SpendingRepository spendingRepository;

    @PostMapping("/create")
    public String create(@RequestBody Spending spending) {
        spendingRepository.save(spending);
        return "Spending is created";
    }

    @GetMapping("/findall")
    public List<Spending> findAll() {
        return spendingRepository.findAll();
    }

    @GetMapping("/findByOwner/{owner}")
    public List<Transaction> findByOwner(@PathVariable String owner) {
        return spendingRepository.findByOwner(owner);
    }

    @GetMapping("/findById/{id}")
    public Spending findById(@PathVariable int id) {
        return spendingRepository.findById(id);
    }
}
