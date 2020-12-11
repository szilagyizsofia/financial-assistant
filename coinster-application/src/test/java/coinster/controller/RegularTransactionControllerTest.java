package coinster.controller;

import coinster.model.*;
import coinster.repository.IncomeRepository;
import coinster.repository.RegularTransactionRepository;
import coinster.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

@WebMvcTest(RegularTransactionController.class)
public class RegularTransactionControllerTest {

    @Autowired
    private RegularTransactionController regularTransactionController = new RegularTransactionController();
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RegularTransactionRepository regularTransactionRepository;

    @Test
    public void findByOwner() {
        User owner1 = new User();
        owner1.setUserId(1);
        owner1.setPlan(Plan.premium);
        owner1.setUsername("TestUser1");
        owner1.setCurrency(CurrencyUsed.Ft);
        owner1.setPassword("password");
        owner1.setRole(Role.BASIC);

        RegularTransaction regularTransaction1 = new RegularTransaction();
        regularTransaction1.setAmount(200);
        regularTransaction1.setCurrency(CurrencyUsed.Ft);
        regularTransaction1.setOwner(owner1);
        regularTransaction1.setCreatedAt(new Date());

        RegularTransaction regularTransaction2 = new RegularTransaction();
        regularTransaction2.setAmount(700);
        regularTransaction2.setCurrency(CurrencyUsed.Ft);
        regularTransaction2.setOwner(owner1);
        regularTransaction2.setCreatedAt(new GregorianCalendar(2010, Calendar.MARCH, 26).getTime());

        ArrayList<RegularTransaction> regularTransactions = new ArrayList<>();
        regularTransactions.add(regularTransaction1);
        regularTransactions.add(regularTransaction2);


        Mockito.when(regularTransactionRepository.findByOwner(owner1)).thenReturn(regularTransactions);

        assertEquals(regularTransactions, regularTransactionController.findByOwner(owner1));
    }

    @Test
    public void getRegularSpendings() {
        User owner1 = new User();
        owner1.setUserId(1);
        owner1.setPlan(Plan.premium);
        owner1.setUsername("TestUser1");
        owner1.setCurrency(CurrencyUsed.Ft);
        owner1.setPassword("password");
        owner1.setRole(Role.BASIC);

        RegularTransaction regularTransaction1 = new RegularTransaction();
        regularTransaction1.setAmount(-200);
        regularTransaction1.setCurrency(CurrencyUsed.Ft);
        regularTransaction1.setOwner(owner1);
        regularTransaction1.setCreatedAt(new Date());

        RegularTransaction regularTransaction2 = new RegularTransaction();
        regularTransaction2.setAmount(700);
        regularTransaction2.setCurrency(CurrencyUsed.Ft);
        regularTransaction2.setOwner(owner1);
        regularTransaction2.setCreatedAt(new GregorianCalendar(2010, Calendar.MARCH, 26).getTime());

        ArrayList<RegularTransaction> regularTransactions = new ArrayList<>();
        regularTransactions.add(regularTransaction1);
        regularTransactions.add(regularTransaction2);

        Mockito.when(regularTransactionRepository.findByOwner(owner1)).thenReturn(regularTransactions);

        ArrayList<RegularTransaction> regularSpendings = new ArrayList<>();
        regularSpendings.add(regularTransaction1);

        assertEquals(regularSpendings, regularTransactionController.getRegularSpendings(owner1));
    }

    @Test
    public void getRegularIncomes() {
        User owner1 = new User();
        owner1.setUserId(1);
        owner1.setPlan(Plan.premium);
        owner1.setUsername("TestUser1");
        owner1.setCurrency(CurrencyUsed.Ft);
        owner1.setPassword("password");
        owner1.setRole(Role.BASIC);

        RegularTransaction regularTransaction1 = new RegularTransaction();
        regularTransaction1.setAmount(-200);
        regularTransaction1.setCurrency(CurrencyUsed.Ft);
        regularTransaction1.setOwner(owner1);
        regularTransaction1.setCreatedAt(new Date());

        RegularTransaction regularTransaction2 = new RegularTransaction();
        regularTransaction2.setAmount(700);
        regularTransaction2.setCurrency(CurrencyUsed.Ft);
        regularTransaction2.setOwner(owner1);
        regularTransaction2.setCreatedAt(new GregorianCalendar(2010, Calendar.MARCH, 26).getTime());

        ArrayList<RegularTransaction> regularTransactions = new ArrayList<>();
        regularTransactions.add(regularTransaction1);
        regularTransactions.add(regularTransaction2);

        Mockito.when(regularTransactionRepository.findByOwner(owner1)).thenReturn(regularTransactions);

        ArrayList<RegularTransaction> regularIncomes = new ArrayList<>();
        regularIncomes.add(regularTransaction2);

        assertEquals(regularIncomes, regularTransactionController.getRegularIncomes(owner1));
    }
}