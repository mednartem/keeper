package guru.qa.service;

import guru.qa.data.ModalWindowOptions;
import guru.qa.db.AccountRepository;
import guru.qa.db.IncomeRepository;
import guru.qa.db.impl.PostgresAccountRepository;
import guru.qa.db.impl.PostgresIncomeRepository;
import guru.qa.entity.AccountEntity;
import guru.qa.entity.IncomeEntity;

import javax.swing.*;
import java.util.Arrays;

public class IncomeService {
    private final IncomeRepository incomeRepository = new PostgresIncomeRepository();
    private final AccountRepository accountRepository = new PostgresAccountRepository();

    public boolean doIncome(AccountEntity accountEntity) {
        if (topUpBalance().equals(ModalWindowOptions.YES)) {
            int incomeValue = getIncomeValue();

            if (incomeValue > 0) {
                String description = JOptionPane.showInputDialog("Введите описание пополнения: ");

                IncomeEntity incomeEntity = new IncomeEntity()
                        .setIncome(incomeValue)
                        .setDescription(description)
                        .setAccountId(accountEntity.getId());

                incomeRepository.addIncome(incomeEntity);
                accountEntity.setBalance(accountEntity.getBalance() + incomeValue);
                accountRepository.updateAccount(accountEntity);
                return true;
            }
        }
        return false;
    }

    public void showAllIncome(AccountEntity account) {
        Object[][] rows = incomeRepository.getAllForAccount(account)
                .stream()
                .map(income -> new Object[]{income.getIncome(), income.getDescription()})
                .toArray(Object[][]::new);

        Object[] headers = {"Сумма пополнения", "Описание пополнения"};

        JTable table = new JTable(rows, headers);
        JOptionPane.showMessageDialog(null, new JScrollPane(table));
    }

    private ModalWindowOptions topUpBalance() {
        int index = JOptionPane.showOptionDialog(
                null,
                "Вы хотите пополнить баланс?",
                "Пополнения",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                Arrays.stream(ModalWindowOptions.values()).map(ModalWindowOptions::getValue).toArray(String[]::new),
                ModalWindowOptions.YES.getValue()
        );

        return ModalWindowOptions.values()[index];
    }

    private int getIncomeValue() {
        int income;

        try {
            income = Integer.parseInt(
                    JOptionPane.showInputDialog("Введите сумму пополнения")
            );
        } catch (NumberFormatException e) {
            income = 0;
        }

        return income;
    }
}
