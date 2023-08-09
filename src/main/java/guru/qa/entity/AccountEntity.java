package guru.qa.entity;

public class AccountEntity {
    private int id;
    private String name;
    private int balance;

    public int getId() {
        return id;
    }

    public AccountEntity setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AccountEntity setName(String name) {
        this.name = name;
        return this;
    }

    public int getBalance() {
        return balance;
    }

    public AccountEntity setBalance(int balance) {
        this.balance = balance;
        return this;
    }
}
