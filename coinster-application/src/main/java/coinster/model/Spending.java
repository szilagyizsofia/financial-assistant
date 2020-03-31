package coinster.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "spendings")
public class Spending implements Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @Column(name = "amount")
    private int amount;

    @Column(name = "planned")
    private boolean planned;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private SpendingCategory category;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private CurrencyUsed currency;

    @Column(name = "createdAt")
    private Date createdAt;

    public Spending() {}

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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public CurrencyUsed getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyUsed currency) {
        this.currency = currency;
    }
}
