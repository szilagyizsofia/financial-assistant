package coinster.repository;

import coinster.model.RegularTransaction;
import coinster.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegularTransactionRepository extends JpaRepository<RegularTransaction, Integer> {

    List<RegularTransaction> findByOwner(User owner);

    RegularTransaction findById(int id);
}
