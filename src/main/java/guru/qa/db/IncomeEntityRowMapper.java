package guru.qa.db;

import guru.qa.entity.IncomeEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IncomeEntityRowMapper implements RowMapper<IncomeEntity> {

    @Override
    public IncomeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new IncomeEntity()
                .setId(rs.getInt("id"))
                .setIncome(rs.getInt("income"))
                .setAccountId(rs.getInt("account_id"))
                .setDescription(rs.getString("description"));
    }
}
