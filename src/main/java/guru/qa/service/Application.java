package guru.qa.service;

import guru.qa.entity.AccountEntity;

public class Application {
    private final AccountService accountService = new AccountService();
    private final IncomeService incomeService = new IncomeService();
    private final SpendService spendService = new SpendService();


    public void run() {
        AccountEntity accountEntity = accountService.login();
        accountService.showCurrentBalance(accountEntity);
        boolean doIncome = incomeService.doIncome(accountEntity);
        if (doIncome) {
            incomeService.showAllIncome(accountEntity);
        }
        spendService.doSpend(accountEntity);
        accountService.showCurrentBalance(accountEntity);
        spendService.showAllSpends(accountEntity);
        if (doIncome) {
            incomeService.showAllIncome(accountEntity);
        }
    }
}
