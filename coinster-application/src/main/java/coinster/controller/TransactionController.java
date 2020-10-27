package coinster.controller;

import coinster.model.Transaction;
import coinster.model.RegularTransaction;
import coinster.repository.RegularTransactionRepository;
import coinster.repository.TransactionRepository;
import coinster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RegularTransactionRepository regularTransactionRepository;

    @GetMapping("/transactions")
    public List<Transaction> findByOwner(Principal principal) {
        return transactionRepository.findByOwner(userRepository.findByUsername(principal.getName()).get());
    }

    @GetMapping("/transactions/findThisMonth")
    public List<Transaction> findThisMonth(Principal principal) {
        List<Transaction> ret = new ArrayList<>();
        Month thisMonth = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth();
        List<Transaction> transactions = transactionRepository.findByOwner(userRepository.findByUsername(principal.getName()).get());
        for (Transaction transaction : transactions) {
            if (transaction.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth() == thisMonth) {
                ret.add(transaction);
            }
        }
        List<RegularTransaction> regularTransactions = regularTransactionRepository.findByOwner(userRepository.findByUsername(principal.getName()).get());
        for (RegularTransaction regularTransaction : regularTransactions) {
            ret.add(regularTransaction);
        }
        return ret;
    }
}
