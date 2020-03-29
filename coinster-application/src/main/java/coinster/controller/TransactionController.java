package coinster.controller;

import coinster.model.Transaction;
import coinster.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping("/findall")
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @GetMapping("/findByOwner/{owner}")
    public List<Transaction> findByOwner(@PathVariable String owner) {
        return transactionRepository.findByOwner(owner);
    }
}
