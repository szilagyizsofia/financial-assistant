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

    @GetMapping("/findByOwner/{ownerId}")
    public List<Transaction> findByOwner(@PathVariable int ownerId) {
        return spendingRepository.findByOwner(userRepository.findById(ownerId).get());
    }

    @GetMapping("/{id}")
    public Spending findById(@PathVariable int id) {
        return spendingRepository.findById(id);
    }

    @GetMapping("/findByCategory/{category}")
    public List<Spending> findByCategory(@PathVariable SpendingCategory category, Principal principal) {
        return spendingRepository.findByCategoryAndOwner(category, userRepository.findByUsername(principal.getName()).get());
    }

    @GetMapping("/findByCategoryThisMonth/{ownerId}/{category}")
    public List<Spending> findByCategoryThisMonth(@PathVariable SpendingCategory category, @PathVariable int ownerId) {
        List<Spending> ret = new ArrayList<>();
        Month thisMonth = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth();
        List<Spending> spendings = spendingRepository.findByCategoryAndOwner(category, userRepository.findById(ownerId).get());
        for (Spending spending : spendings) {
            if (spending.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth() == thisMonth) {
                ret.add(spending);
            }
        }
        return ret;
    }

    @GetMapping("/thisMonthSumByCategory/{ownerId}/{category}")
    public int thisMonthSum(@PathVariable SpendingCategory category, @PathVariable int ownerId) {
        int sum = 0;
        Month thisMonth = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth();
        List<Spending> spendings = spendingRepository.findByCategoryAndOwner(category, userRepository.findById(ownerId).get());
        for (Spending spending : spendings) {
            if (spending.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth() == thisMonth) {
                sum += spending.getAmount();
            }
        }
        return sum;
    }

    @GetMapping("/thisMonthSum/{ownerId}")
    public int thisMonthSum(@PathVariable int ownerId) {
        int sum = 0;
        Month thisMonth = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth();
        List<Transaction> spendings = spendingRepository.findByOwner(userRepository.findById(ownerId).get());
        for (Transaction spending : spendings) {
            if (spending.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth() == thisMonth) {
                sum += spending.getAmount();
            }
        }
        return sum;
    }
}
