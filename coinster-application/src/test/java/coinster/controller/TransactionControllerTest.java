package coinster.controller;

import coinster.model.*;
import coinster.repository.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private TransactionController transactionController = new TransactionController();
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private TransactionRepository transactionRepository;
    @MockBean
    private RegularTransactionRepository regularTransactionRepository;
    @MockBean
    private  SpendingRepository spendingRepository;
    @MockBean
    private IncomeRepository incomeRepository;


    @Test
    public void findByOwner() {
        User owner1 = new User();
        owner1.setUserId(1);
        owner1.setPlan(Plan.premium);
        owner1.setUsername("TestUser1");
        owner1.setCurrency(CurrencyUsed.Ft);
        owner1.setPassword("password");
        owner1.setRole(Role.BASIC);

        Spending spending1 = new Spending();
        spending1.setOwner(owner1);
        spending1.setAmount(-100);
        spending1.setCurrency(CurrencyUsed.Ft);
        spending1.setPlanned(true);
        spending1.setCategory(SpendingCategory.bills);
        spending1.setCreatedAt(new Date());

        Spending spending2 = new Spending();
        spending2.setOwner(owner1);
        spending2.setAmount(-500);
        spending2.setCurrency(CurrencyUsed.Ft);
        spending2.setPlanned(true);
        spending2.setCategory(SpendingCategory.bills);
        spending2.setCreatedAt(new Date());

        Spending spending3 = new Spending();
        spending3.setOwner(owner1);
        spending3.setAmount(-100);
        spending3.setCurrency(CurrencyUsed.Ft);
        spending3.setPlanned(true);
        spending3.setCategory(SpendingCategory.bills);
        spending3.setCreatedAt(new GregorianCalendar(2010, Calendar.MARCH, 26).getTime());

        Spending spending4 = new Spending();
        spending4.setOwner(owner1);
        spending4.setAmount(-100);
        spending4.setCurrency(CurrencyUsed.Ft);
        spending4.setPlanned(true);
        spending4.setCategory(SpendingCategory.eatingout);
        spending4.setCreatedAt(new Date());

        Spending spending5 = new Spending();
        spending5.setOwner(owner1);
        spending5.setAmount(-500);
        spending5.setCurrency(CurrencyUsed.Ft);
        spending5.setPlanned(false);
        spending5.setCategory(SpendingCategory.bills);
        spending5.setCreatedAt(new Date());

        Spending spending6 = new Spending();
        spending6.setOwner(owner1);
        spending6.setAmount(-100);
        spending6.setCurrency(CurrencyUsed.Ft);
        spending6.setPlanned(false);
        spending6.setCategory(SpendingCategory.eatingout);
        spending6.setCreatedAt(new Date());

        RegularTransaction regularTransaction1 = new RegularTransaction();
        regularTransaction1.setAmount(-200);
        regularTransaction1.setCurrency(CurrencyUsed.Ft);
        regularTransaction1.setOwner(owner1);
        regularTransaction1.setCreatedAt(new Date());

        RegularTransaction regularTransaction2 = new RegularTransaction();
        regularTransaction2.setAmount(-700);
        regularTransaction2.setCurrency(CurrencyUsed.Ft);
        regularTransaction2.setOwner(owner1);
        regularTransaction2.setCreatedAt(new GregorianCalendar(2010, Calendar.MARCH, 26).getTime());

        ArrayList<Transaction> spendings = new ArrayList<>();
        spendings.add(spending1);
        spendings.add(spending2);
        spendings.add(spending3);
        spendings.add(spending4);
        spendings.add(spending5);
        spendings.add(spending6);

        Mockito.when(spendingRepository.findByOwner(owner1)).thenReturn(spendings);

        ArrayList<RegularTransaction> regularTransactions = new ArrayList<>();
        regularTransactions.add(regularTransaction1);
        regularTransactions.add(regularTransaction2);

        Mockito.when(regularTransactionRepository.findByOwner(Mockito.any())).thenReturn(regularTransactions);

        Mockito.when(userRepository.findByUsername("TestUser1")).thenReturn(java.util.Optional.of(owner1));

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

        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.addAll(incomes);
        transactions.addAll(spendings);

        Mockito.when(transactionRepository.findByOwner(owner1)).thenReturn(transactions);

        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(principal.getName()).thenReturn("TestUser1");

        assertEquals(transactions, transactionController.findByOwner(principal));

    }

    @Test
    public void findThisMonth() {
        User owner1 = new User();
        owner1.setUserId(1);
        owner1.setPlan(Plan.premium);
        owner1.setUsername("TestUser1");
        owner1.setCurrency(CurrencyUsed.Ft);
        owner1.setPassword("password");
        owner1.setRole(Role.BASIC);

        Spending spending1 = new Spending();
        spending1.setOwner(owner1);
        spending1.setAmount(-100);
        spending1.setCurrency(CurrencyUsed.Ft);
        spending1.setPlanned(true);
        spending1.setCategory(SpendingCategory.bills);
        spending1.setCreatedAt(new Date());

        Spending spending2 = new Spending();
        spending2.setOwner(owner1);
        spending2.setAmount(-500);
        spending2.setCurrency(CurrencyUsed.Ft);
        spending2.setPlanned(true);
        spending2.setCategory(SpendingCategory.bills);
        spending2.setCreatedAt(new Date());

        Spending spending3 = new Spending();
        spending3.setOwner(owner1);
        spending3.setAmount(-100);
        spending3.setCurrency(CurrencyUsed.Ft);
        spending3.setPlanned(true);
        spending3.setCategory(SpendingCategory.bills);
        spending3.setCreatedAt(new GregorianCalendar(2010, Calendar.MARCH, 26).getTime());

        Spending spending4 = new Spending();
        spending4.setOwner(owner1);
        spending4.setAmount(-100);
        spending4.setCurrency(CurrencyUsed.Ft);
        spending4.setPlanned(true);
        spending4.setCategory(SpendingCategory.eatingout);
        spending4.setCreatedAt(new Date());

        Spending spending5 = new Spending();
        spending5.setOwner(owner1);
        spending5.setAmount(-500);
        spending5.setCurrency(CurrencyUsed.Ft);
        spending5.setPlanned(false);
        spending5.setCategory(SpendingCategory.bills);
        spending5.setCreatedAt(new Date());

        Spending spending6 = new Spending();
        spending6.setOwner(owner1);
        spending6.setAmount(-100);
        spending6.setCurrency(CurrencyUsed.Ft);
        spending6.setPlanned(false);
        spending6.setCategory(SpendingCategory.eatingout);
        spending6.setCreatedAt(new Date());

        RegularTransaction regularTransaction1 = new RegularTransaction();
        regularTransaction1.setAmount(-200);
        regularTransaction1.setCurrency(CurrencyUsed.Ft);
        regularTransaction1.setOwner(owner1);
        regularTransaction1.setCreatedAt(new Date());

        RegularTransaction regularTransaction2 = new RegularTransaction();
        regularTransaction2.setAmount(-700);
        regularTransaction2.setCurrency(CurrencyUsed.Ft);
        regularTransaction2.setOwner(owner1);
        regularTransaction2.setCreatedAt(new GregorianCalendar(2010, Calendar.MARCH, 26).getTime());

        ArrayList<Transaction> spendings = new ArrayList<>();
        spendings.add(spending1);
        spendings.add(spending2);
        spendings.add(spending3);
        spendings.add(spending4);
        spendings.add(spending5);
        spendings.add(spending6);

        Mockito.when(spendingRepository.findByOwner(owner1)).thenReturn(spendings);

        ArrayList<RegularTransaction> regularTransactions = new ArrayList<>();
        regularTransactions.add(regularTransaction1);
        regularTransactions.add(regularTransaction2);

        Mockito.when(regularTransactionRepository.findByOwner(Mockito.any())).thenReturn(regularTransactions);

        Mockito.when(userRepository.findByUsername("TestUser1")).thenReturn(java.util.Optional.of(owner1));

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

        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.addAll(incomes);
        transactions.addAll(spendings);

        Mockito.when(transactionRepository.findByOwner(owner1)).thenReturn(transactions);

        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(principal.getName()).thenReturn("TestUser1");

        ArrayList<Transaction> allTransactions = new ArrayList<>();
        allTransactions.add(income1);
        allTransactions.add(income2);
        allTransactions.add(spending1);
        allTransactions.add(spending2);
        allTransactions.add(spending4);
        allTransactions.add(spending5);
        allTransactions.add(spending6);
        allTransactions.addAll(regularTransactions);

        assertEquals(allTransactions, transactionController.findThisMonth(principal));
    }
}