package company.DAO;

import company.Entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDao {
    private static final String SQL_ADD_PAYMENT =
            "INSERT INTO `payment`(fk_account,status,`date`,purpose,fk_category,amount,commission,recipient_account,recipient_name) values(?,?,?,?,?,?,?,?,?)";

    public List<Payment> findAllByAccount(Account account){
        List<Payment> result=new ArrayList<>();
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmp = con.prepareStatement("Select * from payment where fk_account=?");
        ) {
            stmp.setLong(1,account.getId());
            ResultSet rs= stmp.executeQuery();
            while (rs.next()) {
                result.add(mapPayment(rs));
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return result;
    }

    public Payment findById(Long id){
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmp = con.prepareStatement("Select * from payment where id=?");
        ) {
            stmp.setLong(1,id);
            ResultSet rs= stmp.executeQuery();
            if (rs.next()) {
                return mapPayment(rs);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return null;
    }

    public Payment mapPayment(ResultSet rs) {
        try {
            Payment payment = new Payment();
            payment.setId(rs.getLong("id"));
            payment.setAccount(new AccountDao().findById(rs.getLong("fk_account")));
            payment.setStatus(Status.valueOf(rs.getString("status")));
            payment.setDate(rs.getTimestamp("date"));
            payment.setPurpose(rs.getString("purpose"));
            payment.setCategory(findCategoryById(rs.getLong("fk_category")));
            payment.setAmount(rs.getDouble("amount"));
            payment.setCommission(rs.getDouble("commission"));
            payment.setRecipientAccount(rs.getString("recipient_account"));
            payment.setRecipientName(rs.getString("recipient_name"));
            return payment;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    public Category findCategoryById(Long id){
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmp = con.prepareStatement("Select * from category where id=?");
        ) {
            stmp.setLong(1,id);
            ResultSet rs= stmp.executeQuery();
            if (rs.next()) {
                return mapCategory(rs);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return null;
    }

    public Category mapCategory(ResultSet rs) {
        try {
            Category category = new Category();
            category.setId(rs.getLong("id"));
            category.setName(rs.getString("name"));
            return category;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public boolean insert(Payment payment) {
        boolean res = false;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmp = con.prepareStatement(SQL_ADD_PAYMENT,
                     Statement.RETURN_GENERATED_KEYS)
        ) {
            stmp.setLong(1,payment.getAccount().getId());
            stmp.setString(2, payment.getStatus().name());
            stmp.setTimestamp(3,payment.getDate());
            stmp.setString(4,payment.getPurpose());
            stmp.setLong(5,payment.getCategory().getId());
            stmp.setDouble(6,payment.getAmount());
            stmp.setDouble(7,payment.getCommission());
            stmp.setString(8,payment.getRecipientAccount());
            stmp.setString(9,payment.getRecipientName());
            if (stmp.executeUpdate() > 0) {
                try(ResultSet rs = stmp.getGeneratedKeys()) {
                    if (rs.next()) {
                        payment.setId(rs.getLong(1));
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

    public List<Category> findAllCategories(){
        List<Category> result=new ArrayList<>();
        try (Connection con = DBManager.getInstance().getConnection();
             Statement stmp = con.createStatement();
             ResultSet rs= stmp.executeQuery("Select * from category")
        ) {
            while (rs.next()) {
                result.add(mapCategory(rs));
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return result;
    }
}
