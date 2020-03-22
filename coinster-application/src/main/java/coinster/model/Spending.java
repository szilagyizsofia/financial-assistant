package coinster.model;

import javax.persistence.*;

@Entity
@Table(name = "spendings")
public class Spending implements Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "owner")
    private String owner;

    @Column(name = "amount")
    private int amount;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private CurrencyUsed currency;

    @Column(name = "planned")
    private boolean planned;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private SpendingCategory category;

    public Spending() {}

    public Spending(User owner, int amount, boolean planned, SpendingCategory category) {
        this.owner = owner.getUsername();
        this.amount = amount;
        this.planned = planned;
        this.category = category;
        this.currency = owner.getCurrency();
    }

    public Spending(User owner, int amount, boolean planned, SpendingCategory category, CurrencyUsed currency) {
        this.owner = owner.getUsername();
        this.amount = amount;
        this.planned = planned;
        this.category = category;
        this.currency = currency;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    public boolean isPlanned() {
        return planned;
    }

    public SpendingCategory getCategory() {
        return category;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPlanned(boolean planned) {
        this.planned = planned;
    }

    public void setCategory(SpendingCategory category) {
        this.category = category;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
