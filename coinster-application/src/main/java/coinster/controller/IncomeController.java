package coinster.controller;

import coinster.model.CurrencyUsed;
import coinster.model.Income;
import coinster.model.Plan;
import coinster.model.User;
import coinster.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IncomeController {

    @Autowired
    IncomeRepository incomeRepository;

    @GetMapping("/testcreateIncome")
    public String testcreateIncome(){
        incomeRepository.save(new Income(new User("Rajesh01", "Bhojwani", Plan.regular, CurrencyUsed.USD), 100, "test income"));
        return "Customer created";
    }

    @PostMapping("/createIncome")
    public String createIncome(@RequestBody Income income){
        incomeRepository.save(income);
        return "Income is created";
    }

    @GetMapping("/findallIncome")
    public List<Income> findAllIncome(){
        List<Income> incomes = incomeRepository.findAll();
        return incomes;
    }
}
