package coinster.controller;

import coinster.model.Income;
import coinster.model.RegularTransaction;
import coinster.model.Transaction;
import coinster.model.User;
import coinster.repository.IncomeRepository;
import coinster.repository.RegularTransactionRepository;
import coinster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("incomes")
public class IncomeController {

    @Autowired
    IncomeRepository incomeRepository;

    @Autowired
    RegularTransactionRepository regularTransactionRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("")
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

    @GetMapping("/findByOwner/{ownerId}")
    public List<Transaction> findByOwner(@PathVariable int ownerId) {
        return incomeRepository.findByOwner(userRepository.findById(ownerId).get());
    }

    @GetMapping("/{id}")
    public Income findById(@PathVariable int id) {
        return incomeRepository.findById(id);
    }

    @GetMapping("/findThisMonth/{ownerId}")
    public List<Transaction> findThisMonth(@PathVariable int ownerId) {
        List<Transaction> ret = new ArrayList<>();
        Month thisMonth = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth();
        List<Transaction> incomes = incomeRepository.findByOwner(userRepository.findById(ownerId).get());
        for (Transaction income : incomes) {
            if (income.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth() == thisMonth) {
                ret.add(income);
            }
        }
        return ret;
    }

    @GetMapping("/thisMonthSum/{ownerId}")
    public int thisMonthSum(@PathVariable int ownerId) {
        int sum = 0;
        Month thisMonth = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth();
        List<Transaction> incomes = incomeRepository.findByOwner(userRepository.findById(ownerId).get());
        for (Transaction income : incomes) {
            if (income.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth() == thisMonth) {
                sum += income.getAmount();
            }
        }
        List<RegularTransaction> regularTransactions = regularTransactionRepository.findByOwner(userRepository.findById(ownerId).get());
        for (RegularTransaction regularTransaction : regularTransactions) {
            if (regularTransaction.getAmount() > 0) {
                sum += regularTransaction.getAmount();
            }
        }
        return sum;
    }
}
