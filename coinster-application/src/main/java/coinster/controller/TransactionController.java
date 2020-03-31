package coinster.controller;

import coinster.model.Transaction;
import coinster.repository.TransactionRepository;
import coinster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/transactions/findall")
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @GetMapping("/transactions")
    public List<Transaction> findByOwner(Principal principal) {
        return transactionRepository.findByOwner(userRepository.findByUsername(principal.getName()).get());
    }
}
