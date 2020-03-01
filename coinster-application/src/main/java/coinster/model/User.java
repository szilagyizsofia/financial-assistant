package coinster.model;

import java.util.HashMap;
import java.util.Map;

public class User {

    private int userId;
    private String username;
    private String password;
    private Plan plan;
    private CurrencyUsed currency;
    private Map<Integer, Income> incomes = new HashMap<>();
    private Map<Integer, Spending> spendings = new HashMap<>();

    public void changeCurrency(CurrencyUsed currency) {
        this.currency = currency;
    }

    public void addNewIncome(Income income) {
        incomes.put(income.getId(), income);
    }

    public void addNewSpending(Spending spending) {
        spendings.put(spending.getId(), spending);
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Plan getPlan() {
        return plan;
    }

    public CurrencyUsed getCurrency() {
        return currency;
    }

    public Map<Integer, Income> getIncomes() {
        return incomes;
    }

    public Map<Integer, Spending> getSpendings() {
        return spendings;
    }

    public Map<Integer, Transaction> getTransactions() {
        Map<Integer, Transaction> transactions = new HashMap<>();
        transactions.putAll(spendings);
        transactions.putAll(incomes);
        return transactions;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public void setCurrency(CurrencyUsed currency) {
        this.currency = currency;
    }
}

