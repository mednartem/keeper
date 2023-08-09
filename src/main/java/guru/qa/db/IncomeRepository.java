package guru.qa.db;

import guru.qa.entity.AccountEntity;
import guru.qa.entity.IncomeEntity;

import java.util.List;

public interface IncomeRepository {
    List<IncomeEntity> getAllForAccount(AccountEntity account);

    void addIncome(IncomeEntity spendEntity);
}
