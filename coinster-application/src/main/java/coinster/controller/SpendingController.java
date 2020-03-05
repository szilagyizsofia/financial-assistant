package coinster.controller;

import coinster.model.*;
import coinster.repository.SpendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpendingController {

    @Autowired
    SpendingRepository spendingRepository;

    @GetMapping("/createSpending")
    public String create(){
        spendingRepository.save(new Spending(new User("Rajesh01", "Bhojwani", Plan.regular, CurrencyUsed.USD), 100, true, SpendingCategory.bills));
        return "Customer created";
    }
}
