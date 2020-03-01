package coinster.model;


public class Spending implements Transaction {

    private int id;
    private int amount;
    private boolean planned;
    private SpendingCategory category;

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
}
