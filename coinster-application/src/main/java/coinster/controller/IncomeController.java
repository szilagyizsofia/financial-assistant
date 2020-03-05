package coinster.controller;

import coinster.model.CurrencyUsed;
import coinster.model.Income;
import coinster.model.Plan;
import coinster.model.User;
import coinster.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IncomeController {

    @Autowired
    IncomeRepository incomeRepository;

    @GetMapping("/createIncome")
    public String create(){
        incomeRepository.save(new Income(new User("Rajesh01", "Bhojwani", Plan.regular, CurrencyUsed.USD), 100, "test income"));
        return "Customer created";
    }
}
