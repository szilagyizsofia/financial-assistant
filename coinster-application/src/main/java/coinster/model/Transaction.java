package coinster.model;

import java.util.Date;

public interface Transaction {

    int getId();

    int getAmount();

    Date getCreatedAt();

    void setAmount(int amount);
}
