package guru.qa.db.impl;

import guru.qa.db.DataSourceProvider;
import guru.qa.db.IncomeEntityRowMapper;
import guru.qa.db.IncomeRepository;
import guru.qa.entity.AccountEntity;
import guru.qa.entity.IncomeEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PostgresIncomeRepository implements IncomeRepository {

    private static final JdbcTemplate template = new JdbcTemplate(DataSourceProvider.INSTANCE.getDataSource());

    @Override
    public List<IncomeEntity> getAllForAccount(AccountEntity account) {
        return template.query(
                "SELECT * FROM income WHERE  account_id = ?",
                new IncomeEntityRowMapper(),
                account.getId()
        );
    }

    @Override
    public void addIncome(IncomeEntity incomeEntity) {
        template.update("INSERT INTO income (income, account_id, description) values (?, ?, ?)",
                incomeEntity.getIncome(),
                incomeEntity.getAccountId(),
                incomeEntity.getDescription()
        );
    }
}
