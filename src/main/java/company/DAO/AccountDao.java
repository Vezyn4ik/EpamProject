package company.DAO;

import company.Entity.Account;
import company.Entity.State;
import company.Entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private static final String SQL_ADD_ACCOUNT =
            "INSERT INTO `account`(fk_user,create_time,`name`,`limit`,currency,fk_requisite) values(?,?,?,?,?,?)";

    /**
     * Returns a user with the given identifier.
     *
     * @param account User identifier.
     * @return boolean.
     */
    public boolean insert(Account account) {
        boolean res = false;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmp = con.prepareStatement(SQL_ADD_ACCOUNT,
                     Statement.RETURN_GENERATED_KEYS)
        ) {
            stmp.setLong(1, account.getUser().getId());
            stmp.setTimestamp(2, account.getCreateTime());
            stmp.setString(3, account.getName());
            stmp.setDouble(4, account.getLimit());
            stmp.setString(5, account.getCurrency());
            stmp.setLong(6, 1);
            if (stmp.executeUpdate() > 0) {
                try (ResultSet rs = stmp.getGeneratedKeys()) {
                    if (rs.next()) {
                        account.setId(rs.getLong(1));
                        res = true;
                        con.commit();
                    }
                }
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            res = false;
        }

        return res;
    }

    public Account findById(Long id) {
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmp = con.prepareStatement("Select * from account where id=?");
        ) {
            stmp.setLong(1, id);
            ResultSet rs = stmp.executeQuery();
            if (rs.next()) {
                return new AccountMapper().mapRow(rs);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return null;
    }

    public List<Account> findAllByUser(User user) {
        List<Account> temp = new ArrayList<>();
        AccountMapper accountMapper=new AccountMapper();
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmp = con.prepareStatement("Select * from account where fk_user=?");
        ) {
            stmp.setLong(1, user.getId());
            ResultSet rs = stmp.executeQuery();
            while (rs.next()) {
                temp.add(accountMapper.mapRow(rs));
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return temp;
    }


    public boolean update(Account account) {
        boolean res = false;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmp = con.prepareStatement("UPDATE `account` set balance=?, `name`=?, `limit` =?, currency=?, `state`=? where id=?",
                     Statement.RETURN_GENERATED_KEYS)
        ) {
            stmp.setDouble(1, account.getBalance());
            stmp.setString(2, account.getName());
            stmp.setDouble(3, account.getLimit());
            stmp.setString(4, account.getCurrency());
            stmp.setString(5, account.getState().name());
            stmp.setLong(6, account.getId());
            if (stmp.executeUpdate() > 0) {
                res = true;
                con.commit();
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            res = false;
        }
        return res;
    }

    public Account findByName(String name) {
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmp = con.prepareStatement("Select * from account where name=?");
        ) {
            stmp.setString(1, name);
            ResultSet rs = stmp.executeQuery();
            if (rs.next()) {
                return new AccountMapper().mapRow(rs);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return null;
    }
    private static class AccountMapper implements EntityMapper<Account> {
        public Account mapRow(ResultSet rs) {
            try {
                Account account = new Account();
                account.setId(rs.getLong("id"));
                account.setUser(new UserDao().findUser(rs.getLong("fk_user")));
                account.setName(rs.getString("name"));
                account.setBalance(rs.getDouble("balance"));
                account.setCreateTime(rs.getTimestamp("create_time"));
                account.setLimit(rs.getDouble("limit"));
                account.setCurrency(rs.getString("currency"));
                account.setState(State.valueOf(rs.getString("state")));
                return account;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
