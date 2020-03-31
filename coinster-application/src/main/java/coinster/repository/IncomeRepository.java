package coinster.repository;

import coinster.model.Income;
import coinster.model.Transaction;
import coinster.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {

    List<Transaction> findByOwner(User owner);

    Income findById(int id);

    List<Income> findAll();
}
