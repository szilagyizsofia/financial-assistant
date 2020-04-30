package coinster.controller;

import coinster.model.RegularTransaction;
import coinster.model.User;
import coinster.repository.RegularTransactionRepository;
import coinster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("regulars")
public class RegularTransactionController {

    @Autowired
    private RegularTransactionRepository regularTransactionRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("")
    public ResponseEntity<RegularTransaction> create(@RequestBody RegularTransaction regularTransaction, Principal principal) {
        User owner = userRepository.findByUsername(principal.getName()).get();
        regularTransaction.setOwner(owner);
        regularTransaction.setCurrency(owner.getCurrency());
        regularTransaction.setCreatedAt(new Date());
        return ResponseEntity.ok(regularTransactionRepository.save(regularTransaction));
    }

    @GetMapping("/findByOwner/{owner}")
    public List<RegularTransaction> findByOwner(@PathVariable User owner) {
        return regularTransactionRepository.findByOwner(owner);
    }
}
