package guru.qa.service;

import guru.qa.data.Category;
import guru.qa.data.ModalWindowOptions;
import guru.qa.db.AccountRepository;
import guru.qa.db.SpendRepository;
import guru.qa.db.impl.PostgresAccountRepository;
import guru.qa.db.impl.PostgresSpendRepository;
import guru.qa.entity.AccountEntity;
import guru.qa.entity.SpendEntity;

import javax.swing.*;
import java.util.Arrays;

public class SpendService {
    private final SpendRepository spendRepository = new PostgresSpendRepository();
    private final AccountRepository accountRepository = new PostgresAccountRepository();

    public void doSpend(AccountEntity accountEntity) {
        if (writeSpend().equals(ModalWindowOptions.YES)) {
            int spendValue = getSpendValue();

            if (isSpendAcceptedForGivenUser(accountEntity, spendValue)) {
                Category category = spendCategory();
                String description = JOptionPane.showInputDialog("Введите описание траты: ");

                SpendEntity spendEntity = new SpendEntity()
                        .setSpend(spendValue)
                        .setSpendCategory(category)
                        .setDescription(description)
                        .setAccountId(accountEntity.getId());

                spendRepository.addSpend(spendEntity);
                accountEntity.setBalance(accountEntity.getBalance() - spendValue);
                accountRepository.updateAccount(accountEntity);
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Невозможно совершить списание",
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    public void showAllSpends(AccountEntity account) {
        Object[][] rows = spendRepository.getAllForAccount(account)
                .stream()
                .map(spend -> new Object[]{spend.getSpendCategory().getDescription(), spend.getSpend(), spend.getDescription()})
                .toArray(Object[][]::new);

        Object[] headers = {"Категория", "Размер траты", "Описание траты"};

        JTable table = new JTable(rows, headers);
        JOptionPane.showMessageDialog(null, new JScrollPane(table));
    }

    private boolean isSpendAcceptedForGivenUser(AccountEntity givenUser, int spend) {
        return spend > 0 && givenUser.getBalance() >= spend;
    }

    private Category spendCategory() {
        int index = JOptionPane.showOptionDialog(
                null,
                "Категория",
                "Выберите категорию траты: ",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                Arrays.stream(Category.values()).map(Category::getDescription).toArray(String[]::new),
                Category.BAR.getDescription()
        );

        return Category.values()[index];
    }

    private ModalWindowOptions writeSpend() {
        int index = JOptionPane.showOptionDialog(
                null,
                "Вы хотите записать траты?",
                "Расходы",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                Arrays.stream(ModalWindowOptions.values()).map(ModalWindowOptions::getValue).toArray(String[]::new),
                ModalWindowOptions.YES.getValue()
        );

        return ModalWindowOptions.values()[index];
    }

    private int getSpendValue() {
        int spend;

        try {
            spend = Integer.parseInt(
                    JOptionPane.showInputDialog("Введите размер траты: ")
            );
        } catch (NumberFormatException e) {
            spend = 0;
        }

        return spend;
    }
}
