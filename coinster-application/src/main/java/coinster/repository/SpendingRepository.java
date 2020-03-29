package coinster.repository;

import coinster.model.Spending;
import coinster.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpendingRepository extends JpaRepository<Spending, Integer> {

    List<Transaction> findByOwner(String owner);

    Spending findById(int id);

    List<Spending> findAll();
}
