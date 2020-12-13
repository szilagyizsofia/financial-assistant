package coinster.controller;

import coinster.model.*;
import coinster.repository.IncomeRepository;
import coinster.repository.RegularTransactionRepository;
import coinster.repository.SpendingRepository;
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

@WebMvcTest(SpendingController.class)
public class SpendingControllerTest {

    @Autowired
    private SpendingController spendingController = new SpendingController();
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private SpendingRepository spendingRepository;
    @MockBean
    private RegularTransactionRepository regularTransactionRepository;


    @Test
    public void thisMonthSum() {
        User owner1 = new User();
        owner1.setUserId(1);
        owner1.setPlan(Plan.premium);
        owner1.setUsername("TestUser1");
        owner1.setCurrency(CurrencyUsed.Ft);
        owner1.setPassword("password");
        owner1.setRole(Role.BASIC);

        //"This should be added to the monthly sum"
        Spending spending1 = new Spending();
        spending1.setOwner(owner1);
        spending1.setAmount(-100);
        spending1.setCurrency(CurrencyUsed.Ft);
        spending1.setPlanned(true);
        spending1.setCategory(SpendingCategory.bills);
        spending1.setCreatedAt(new Date());

        //"This should be added to the monthly sum"
        Spending spending2 = new Spending();
        spending2.setOwner(owner1);
        spending2.setAmount(-500);
        spending2.setCurrency(CurrencyUsed.Ft);
        spending2.setPlanned(true);
        spending2.setCategory(SpendingCategory.bills);
        spending2.setCreatedAt(new Date());

        //"This should not be added to the monthly sum"
        Spending spending3 = new Spending();
        spending3.setOwner(owner1);
        spending3.setAmount(-100);
        spending3.setCurrency(CurrencyUsed.Ft);
        spending3.setPlanned(true);
        spending3.setCategory(SpendingCategory.bills);
        spending3.setCreatedAt(new GregorianCalendar(2010, Calendar.MARCH, 26).getTime());

        //"This should be added to the monthly sum"
        Spending spending4 = new Spending();
        spending4.setOwner(owner1);
        spending4.setAmount(-100);
        spending4.setCurrency(CurrencyUsed.Ft);
        spending4.setPlanned(true);
        spending4.setCategory(SpendingCategory.eatingout);
        spending4.setCreatedAt(new Date());

        //"This should be added to the monthly sum"
        Spending spending5 = new Spending();
        spending5.setOwner(owner1);
        spending5.setAmount(-500);
        spending5.setCurrency(CurrencyUsed.Ft);
        spending5.setPlanned(false);
        spending5.setCategory(SpendingCategory.bills);
        spending5.setCreatedAt(new Date());

        //"This should be added to the monthly sum"
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

        Mockito.when(userRepository.findById(1)).thenReturn(java.util.Optional.of(owner1));

        assertEquals(-2200, spendingController.thisMonthSum(1));
    }

    @Test
    public void findPlannedSumThisMonth() {
        User owner1 = new User();
        owner1.setUserId(1);
        owner1.setPlan(Plan.premium);
        owner1.setUsername("TestUser1");
        owner1.setCurrency(CurrencyUsed.Ft);
        owner1.setPassword("password");
        owner1.setRole(Role.BASIC);

        //"This should be added to the planned monthly sum"
        Spending spending1 = new Spending();
        spending1.setOwner(owner1);
        spending1.setAmount(-100);
        spending1.setCurrency(CurrencyUsed.Ft);
        spending1.setPlanned(true);
        spending1.setCategory(SpendingCategory.bills);
        spending1.setCreatedAt(new Date());

        //"This should be added to the planned monthly sum"
        Spending spending2 = new Spending();
        spending2.setOwner(owner1);
        spending2.setAmount(-500);
        spending2.setCurrency(CurrencyUsed.Ft);
        spending2.setPlanned(true);
        spending2.setCategory(SpendingCategory.bills);
        spending2.setCreatedAt(new Date());

        //"This should not be added to the planned monthly sum"
        Spending spending3 = new Spending();
        spending3.setOwner(owner1);
        spending3.setAmount(-100);
        spending3.setCurrency(CurrencyUsed.Ft);
        spending3.setPlanned(true);
        spending3.setCategory(SpendingCategory.bills);
        spending3.setCreatedAt(new GregorianCalendar(2010, Calendar.MARCH, 26).getTime());

        //"This should be added to the planned monthly sum"
        Spending spending4 = new Spending();
        spending4.setOwner(owner1);
        spending4.setAmount(-100);
        spending4.setCurrency(CurrencyUsed.Ft);
        spending4.setPlanned(true);
        spending4.setCategory(SpendingCategory.eatingout);
        spending4.setCreatedAt(new Date());

        //"This should not be added to the planned monthly sum"
        Spending spending5 = new Spending();
        spending5.setOwner(owner1);
        spending5.setAmount(-500);
        spending5.setCurrency(CurrencyUsed.Ft);
        spending5.setPlanned(false);
        spending5.setCategory(SpendingCategory.bills);
        spending5.setCreatedAt(new Date());

        //"This should not be added to the planned monthly sum"
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

        ArrayList<Spending> plannedSpendings = new ArrayList<>();
        plannedSpendings.add(spending1);
        plannedSpendings.add(spending2);
        plannedSpendings.add(spending4);

        Mockito.when(spendingRepository.findByOwner(owner1)).thenReturn(spendings);
        Mockito.when(spendingRepository.findByPlannedAndOwner(true, owner1)).thenReturn(plannedSpendings);

        ArrayList<RegularTransaction> regularTransactions = new ArrayList<>();
        regularTransactions.add(regularTransaction1);
        regularTransactions.add(regularTransaction2);

        Mockito.when(regularTransactionRepository.findByOwner(Mockito.any())).thenReturn(regularTransactions);

        Mockito.when(userRepository.findById(1)).thenReturn(java.util.Optional.of(owner1));

        assertEquals(-700, spendingController.findPlannedSumThisMonth(1, true));
    }

    @Test
    public void thisMonthSumByCategory() {
        User owner1 = new User();
        owner1.setUserId(1);
        owner1.setPlan(Plan.premium);
        owner1.setUsername("TestUser1");
        owner1.setCurrency(CurrencyUsed.Ft);
        owner1.setPassword("password");
        owner1.setRole(Role.BASIC);

        //"This should be added to the sum"
        Spending spending1 = new Spending();
        spending1.setOwner(owner1);
        spending1.setAmount(-100);
        spending1.setCurrency(CurrencyUsed.Ft);
        spending1.setPlanned(true);
        spending1.setCategory(SpendingCategory.bills);
        spending1.setCreatedAt(new Date());

        //"This should be added to the sum"
        Spending spending2 = new Spending();
        spending2.setOwner(owner1);
        spending2.setAmount(-500);
        spending2.setCurrency(CurrencyUsed.Ft);
        spending2.setPlanned(true);
        spending2.setCategory(SpendingCategory.bills);
        spending2.setCreatedAt(new Date());

        //"This should not be added to the sum"
        Spending spending3 = new Spending();
        spending3.setOwner(owner1);
        spending3.setAmount(-100);
        spending3.setCurrency(CurrencyUsed.Ft);
        spending3.setPlanned(true);
        spending3.setCategory(SpendingCategory.bills);
        spending3.setCreatedAt(new GregorianCalendar(2010, Calendar.MARCH, 26).getTime());

        //"This should not be added to the monthly sum"
        Spending spending4 = new Spending();
        spending4.setOwner(owner1);
        spending4.setAmount(-100);
        spending4.setCurrency(CurrencyUsed.Ft);
        spending4.setPlanned(true);
        spending4.setCategory(SpendingCategory.eatingout);
        spending4.setCreatedAt(new Date());

        //"This should be added to the monthly sum"
        Spending spending5 = new Spending();
        spending5.setOwner(owner1);
        spending5.setAmount(-500);
        spending5.setCurrency(CurrencyUsed.Ft);
        spending5.setPlanned(false);
        spending5.setCategory(SpendingCategory.bills);
        spending5.setCreatedAt(new Date());

        //"This should not be added to the monthly sum"
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

        ArrayList<Spending> bills = new ArrayList<>();
        bills.add(spending1);
        bills.add(spending2);
        bills.add(spending5);

        Mockito.when(spendingRepository.findByOwner(owner1)).thenReturn(spendings);
        Mockito.when(spendingRepository.findByCategoryAndOwner(SpendingCategory.bills, owner1)).thenReturn(bills);

        ArrayList<RegularTransaction> regularTransactions = new ArrayList<>();
        regularTransactions.add(regularTransaction1);
        regularTransactions.add(regularTransaction2);

        Mockito.when(regularTransactionRepository.findByOwner(Mockito.any())).thenReturn(regularTransactions);

        Mockito.when(userRepository.findById(1)).thenReturn(java.util.Optional.of(owner1));

        assertEquals(-1100, spendingController.thisMonthSum("bills",   1));
    }
}