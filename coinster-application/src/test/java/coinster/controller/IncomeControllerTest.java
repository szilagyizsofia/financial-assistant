package coinster.controller;

import coinster.controller.IncomeController;
import coinster.model.*;
import coinster.repository.IncomeRepository;
import coinster.repository.RegularTransactionRepository;
import coinster.repository.UserRepository;
import coinster.security.AuthenticatedUser;
import coinster.security.WebSecurityConfig;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;
import java.util.*;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(IncomeController.class)
class IncomeControllerTest {

    @Autowired
    private IncomeController incomeController = new IncomeController();
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private IncomeRepository incomeRepository;
    @MockBean
    private RegularTransactionRepository regularTransactionRepository;

    @Test
    void testFindByOwner() {
        User owner1 = new User();
        owner1.setUserId(1);
        owner1.setPlan(Plan.premium);
        owner1.setUsername("TestUser1");
        owner1.setCurrency(CurrencyUsed.Ft);
        owner1.setPassword("password");
        owner1.setRole(Role.BASIC);

        Income income1 = new Income();
        income1.setOwner(owner1);
        income1.setAmount(100);
        income1.setCurrency(CurrencyUsed.Ft);
        income1.setCreatedAt(new Date());

        Income income2 = new Income();
        income2.setOwner(owner1);
        income2.setAmount(500);
        income2.setCurrency(CurrencyUsed.Ft);
        income2.setCreatedAt(new Date());

        Income income3 = new Income();
        income3.setOwner(owner1);
        income3.setAmount(100);
        income3.setCurrency(CurrencyUsed.Ft);
        income3.setCreatedAt(new GregorianCalendar(2010, Calendar.MARCH, 26).getTime());

        ArrayList<Transaction> incomes = new ArrayList<>();
        incomes.add(income1);
        incomes.add(income2);
        incomes.add(income3);

        Mockito.when(incomeRepository.findByOwner(Mockito.any())).thenReturn(incomes);

        Mockito.when(userRepository.findById(1)).thenReturn(java.util.Optional.of(owner1));

        assertEquals(incomes, incomeController.findByOwner(1));
    }

    @Test
    void testThisMonthSum() {
        User owner1 = new User();
        owner1.setUserId(1);
        owner1.setPlan(Plan.premium);
        owner1.setUsername("TestUser1");
        owner1.setCurrency(CurrencyUsed.Ft);
        owner1.setPassword("password");
        owner1.setRole(Role.BASIC);

        Income income1 = new Income();
        income1.setOwner(owner1);
        income1.setAmount(100);
        income1.setCurrency(CurrencyUsed.Ft);
        income1.setCreatedAt(new Date());
        income1.setNote("This should be added to the monthly sum");

        Income income2 = new Income();
        income2.setOwner(owner1);
        income2.setAmount(500);
        income2.setCurrency(CurrencyUsed.Ft);
        income2.setCreatedAt(new Date());
        income2.setNote("This should be added to the monthly sum");

        Income income3 = new Income();
        income3.setOwner(owner1);
        income3.setAmount(100);
        income3.setCurrency(CurrencyUsed.Ft);
        income3.setCreatedAt(new GregorianCalendar(2010, Calendar.MARCH, 26).getTime());
        income3.setNote("This should not be added to the monthly sum");


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

        ArrayList<Transaction> incomes = new ArrayList<>();
        incomes.add(income1);
        incomes.add(income2);
        incomes.add(income3);

        Mockito.when(incomeRepository.findByOwner(Mockito.any())).thenReturn(incomes);

        ArrayList<RegularTransaction> regularTransactions = new ArrayList<>();
        regularTransactions.add(regularTransaction1);
        regularTransactions.add(regularTransaction2);

        Mockito.when(regularTransactionRepository.findByOwner(Mockito.any())).thenReturn(regularTransactions);

        Mockito.when(userRepository.findById(1)).thenReturn(java.util.Optional.of(owner1));

        assertEquals(1500, incomeController.thisMonthSum(1));
    }

}