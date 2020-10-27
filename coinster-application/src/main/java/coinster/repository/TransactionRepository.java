package coinster.repository;

import coinster.model.Transaction;
import coinster.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {

    @Autowired
    SpendingRepository spendingRepository;
    @Autowired
    IncomeRepository incomeRepository;
    @Autowired
    RegularTransactionRepository regularTransactionRepository;

    public List<Transaction> findAll() {
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.addAll(spendingRepository.findAll());
        transactions.addAll(incomeRepository.findAll());
        transactions.addAll(regularTransactionRepository.findAll());
        return transactions;
    }

    public List<Transaction> findByOwner(final User owner) {
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.addAll(spendingRepository.findByOwner(owner));
        transactions.addAll(incomeRepository.findByOwner(owner));
        return transactions;
    }
}
