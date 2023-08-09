package guru.qa.entity;

public class IncomeEntity {
    private int id;
    private int accountId;
    private int income;
    private String description;

    public int getId() {
        return id;
    }

    public IncomeEntity setId(int id) {
        this.id = id;
        return this;
    }

    public int getAccountId() {
        return accountId;
    }

    public IncomeEntity setAccountId(int accountId) {
        this.accountId = accountId;
        return this;
    }

    public int getIncome() {
        return income;
    }

    public IncomeEntity setIncome(int income) {
        this.income = income;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public IncomeEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
