package company.Services;

import company.DAO.AccountDao;
import company.DAO.UserDao;
import company.Entity.Account;
import company.Entity.User;
import company.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserProfile extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
       //String login = (String) session.getAttribute("login");
       User user= (User) session.getAttribute("user");

        // put user order beans list to request
        request.setAttribute("user", user);
        List<Account> accounts = new AccountDao().findAllByUser(user);
        request.setAttribute("accounts",accounts);
        return Path.ACCOUNT;
    }
}
