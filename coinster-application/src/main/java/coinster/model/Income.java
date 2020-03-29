package coinster.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "incomes")
public class Income implements Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "owner")
    private String owner;

    @Column(name = "amount")
    private int amount;

    @Column(name = "note")
    private String note;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private CurrencyUsed currency;

    @Column(name = "createdAt")
    private Date createdAt;

    public Income() {}

    public Income(User owner, int amount, String note) {
        this.owner = owner.getUsername();
        this.amount = amount;
        this.note = note;
        this.currency = owner.getCurrency();
        this.createdAt = new Date();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    public String getNote() {
        return note;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner.getUsername();
    }

    public CurrencyUsed getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyUsed currency) {
        this.currency = currency;
    }
}
