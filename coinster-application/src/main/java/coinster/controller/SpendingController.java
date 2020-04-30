package coinster.controller;

import coinster.model.Spending;
import coinster.model.SpendingCategory;
import coinster.model.Transaction;
import coinster.model.User;
import coinster.repository.SpendingRepository;
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
@RequestMapping("spendings")
public class SpendingController {

    @Autowired
    SpendingRepository spendingRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping()
    public ResponseEntity<Spending> create(@RequestBody Spending spending, Principal principal) {
        System.out.println(principal.getName());
        User owner = userRepository.findByUsername(principal.getName()).get();
        spending.setAmount(0 - spending.getAmount());
        spending.setOwner(owner);
        spending.setCurrency(owner.getCurrency());
        return ResponseEntity.ok(spendingRepository.save(spending));
    }

    @GetMapping()
    public List<Transaction> findByOwner(Principal principal) {
        return spendingRepository.findByOwner(userRepository.findByUsername(principal.getName()).get());
    }

    @GetMapping("/findByOwner/{owner}")
    public List<Transaction> findByOwner(@PathVariable User owner) {
        return spendingRepository.findByOwner(owner);
    }

    @GetMapping("/{id}")
    public Spending findById(@PathVariable int id) {
        return spendingRepository.findById(id);
    }

    @GetMapping("/findByCategory/{category}")
    public List<Spending> findByCategory(@PathVariable SpendingCategory category, Principal principal) {
        return spendingRepository.findByCategoryAndOwner(category, userRepository.findByUsername(principal.getName()).get());
    }

    @GetMapping("/findByCategoryThisMonth/{category}")
    public List<Spending> findByCategoryThisMonth(@PathVariable SpendingCategory category, Principal principal) {
        List<Spending> ret = new ArrayList<>();
        Month thisMonth = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth();
        List<Spending> spendings = spendingRepository.findByCategoryAndOwner(category, userRepository.findByUsername(principal.getName()).get());
        for (Spending spending : spendings) {
            if (spending.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth() == thisMonth) {
                ret.add(spending);
            }
        }
        return ret;
    }

    @GetMapping("/thisMonthSumByCategory/{category}")
    public int thisMonthSum(@PathVariable SpendingCategory category, Principal principal) {
        int sum = 0;
        Month thisMonth = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth();
        List<Spending> spendings = spendingRepository.findByCategoryAndOwner(category, userRepository.findByUsername(principal.getName()).get());
        for (Spending spending : spendings) {
            if (spending.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth() == thisMonth) {
                sum += spending.getAmount();
            }
        }
        return sum;
    }

    @GetMapping("/thisMonthSum")
    public int thisMonthSum(Principal principal) {
        int sum = 0;
        Month thisMonth = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth();
        List<Transaction> spendings = spendingRepository.findByOwner(userRepository.findByUsername(principal.getName()).get());
        for (Transaction spending : spendings) {
            if (spending.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth() == thisMonth) {
                sum += spending.getAmount();
            }
        }
        return sum;
    }
}
