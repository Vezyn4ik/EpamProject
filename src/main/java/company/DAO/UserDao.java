package company.DAO;

import company.Entity.Role;
import company.Entity.State;
import company.Entity.Status;
import company.Entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static final String SQL_FIND_USER_BY_LOGIN =
            "SELECT * FROM user WHERE login=?";

    private static final String SQL_FIND_USER_BY_ID =
            "Select u.id,u.name,surname,birth,login,password,role, " +
                    "u.create_time,u.state,telephone,email,count(*) as count_accounts " +
                    "from user u join account a " +
                    "on(u.id=a.fk_user)" +
                    "where role ='USER' and u.id=?  group by u.id;";

    private static final String SQL_UPDATE_USER =
            "UPDATE `user` SET `name`=?, surname=?,birth=?, password=? ,`state`=?,email=? WHERE id=?";

    private static final String SQL_FIND_ALL_USERS=
                 "Select u.id,u.name,surname,birth,login,password,role, " +
                         "u.create_time,u.state,telephone,email,count(*) as count_accounts " +
                         "from user u join account a " +
                         "on(u.id=a.fk_user)" +
                         "where role ='USER'  group by u.id;";
    private static final String SQL_INSERT_USER=
            "Insert into user(name,surname,birth,login,password,role,create_time,state,telephone,email) " +
            "values(?,?,?,?,?,?,?,default,?,?)";
    /**
     * Returns a user with the given identifier.
     *
     * @param id User identifier.
     * @return User entity.
     */
    public User findUser(Long id) {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(SQL_FIND_USER_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                user = mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return user;
    }

    /**
     * Returns a user with the given login.
     *
     * @param login User login.
     * @return User entity.
     */
    public User findUserByLogin(String login) {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next())
                user = mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            if (con != null)
                DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            if (con != null)
                DBManager.getInstance().commitAndClose(con);
        }
        return user;
    }

    public boolean insertUser(User user) {
        boolean res = false;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmp = con.prepareStatement(SQL_INSERT_USER,
                     Statement.RETURN_GENERATED_KEYS)
        ) {
            stmp.setString(1, user.getName());
            stmp.setString(2, user.getSurname());
            stmp.setDate(3, user.getBirth());
            stmp.setString(4, user.getLogin());
            stmp.setString(5, user.getPassword());
            stmp.setString(6, user.getRole().name());
            stmp.setTimestamp(7, user.getCreateTime());
            stmp.setString(8, user.getTelephone());
            stmp.setString(9, user.getEmail());
            if (stmp.executeUpdate() > 0) {
                try (ResultSet rs = stmp.getGeneratedKeys()) {
                    if (rs.next()) {
                        user.setId(rs.getLong(1));
                        res = true;
                    }
                }
            }
            con.commit();
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            res = false;
        }

        return res;
    }

    /**
     * Update user.
     *
     * @param user
     *            user to update.
     */
    public void updateUser(User user) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_USER);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getSurname());
            pstmt.setDate(3, user.getBirth());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getStatus().name());
            pstmt.setString(6, user.getEmail());
            pstmt.setLong(7, user.getId());

            if (pstmt.executeUpdate() > 0) {
            }
            con.commit();
            con.close();
        } catch (SQLException ex) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        }
    }

    public List<User> findAllUsers(){
        List<User> result=new ArrayList<>();
        UserMapper mapper = new UserMapper();
        try (Connection con = DBManager.getInstance().getConnection();
             Statement stmp = con.createStatement();
             ResultSet rs= stmp.executeQuery(SQL_FIND_ALL_USERS)
        ) {
            while (rs.next()) {
                result.add(mapper.mapRow(rs));
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return result;
    }

    private static class UserMapper implements EntityMapper<User> {

        @Override
        public User mapRow(ResultSet rs) {
            try {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setBirth(rs.getDate("birth"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRole(Role.valueOf(rs.getString("role")));
                user.setCreateTime(rs.getTimestamp("create_time"));
                user.setStatus(Status.valueOf(rs.getString("state")));
                user.setEmail(rs.getString("email"));
                user.setTelephone(rs.getString("telephone"));
                try{
                    user.setCountAccounts(Integer.parseInt(rs.getString("count_accounts")));
                }
                catch (SQLException e){
                    System.out.println(e);
                }
                return user;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
