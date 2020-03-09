package coinster.controller;

import coinster.model.*;
import coinster.repository.SpendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpendingController {

    @Autowired
    SpendingRepository spendingRepository;

    @GetMapping("/testcreateSpending")
    public String testcreateSpending(){
        spendingRepository.save(new Spending(new User("Rajesh01", "Bhojwani", Plan.regular, CurrencyUsed.USD), 100, true, SpendingCategory.bills));
        return "Customer created";
    }

    @PostMapping("/createSpending")
    public String createSpending(@RequestBody Spending spending){
        spendingRepository.save(spending);
        return "Spending is created";
    }

    @GetMapping("/findallSpending")
    public List<Spending> findAllSpending(){
        List<Spending> spendings = spendingRepository.findAll();
        return spendings;
    }
}
