package coinster.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "incomes")
public class Income implements Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public CurrencyUsed getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyUsed currency) {
        this.currency = currency;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
