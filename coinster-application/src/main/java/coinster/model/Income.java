package coinster.model;

public class Income implements Transaction {

    private int id;
    private int amount;
    private String note;

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
}
