package coinster.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "plan")
    @Enumerated(EnumType.STRING)
    private Plan plan;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private CurrencyUsed currency;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;


    public User() {}

    public User(String username, String password, Plan plan, CurrencyUsed currency) {
        this.username = username;
        this.password = password;
        this.plan = plan;
        this.currency = currency;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public void setCurrency(CurrencyUsed currency) {
        this.currency = currency;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

