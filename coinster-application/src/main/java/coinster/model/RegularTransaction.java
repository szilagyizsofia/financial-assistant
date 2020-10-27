package coinster.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "regularTransaction")
public class RegularTransaction implements Transaction {

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
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setCurrency(CurrencyUsed currency) {
        this.currency = currency;
    }

    public void setCreatedAt(Date date) {
        this.createdAt = date;
    }
}
