package coinster.repository;

import coinster.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {

    List<Income> findByOwner(String owner);

    Income findById(int id);

    List<Income> findAll();
}
