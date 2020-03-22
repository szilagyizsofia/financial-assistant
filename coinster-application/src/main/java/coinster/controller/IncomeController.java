package coinster.controller;

import coinster.model.Income;
import coinster.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("incomes")
public class IncomeController {

    @Autowired
    IncomeRepository incomeRepository;

    @PostMapping("/create")
    public String create(@RequestBody Income income) {
        incomeRepository.save(income);
        return "Income is created";
    }

    @GetMapping("/findall")
    public List<Income> findAll() {
        return incomeRepository.findAll();
    }

    @GetMapping("/findByOwner/{owner}")
    public List<Income> findByOwner(@PathVariable String owner) {
        return incomeRepository.findByOwner(owner);
    }

    @GetMapping("/findById/{id}")
    public Income findById(@PathVariable int id) {
        return incomeRepository.findById(id);
    }
}
