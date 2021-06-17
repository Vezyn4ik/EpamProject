package company.Services;

import company.DAO.AccountDao;
import company.DAO.PaymentDao;
import company.Entity.Account;
import company.Entity.State;
import company.Entity.User;
import company.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ToMakePayment extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user= (User) session.getAttribute("user");

        // put user order beans list to request
        request.setAttribute("user", user);
        List<Account> accounts= new AccountDao().findAllByUser(user);
        accounts.removeIf(account -> account.state == State.LOCKED);
        request.setAttribute("accounts",accounts);
        request.setAttribute("categories",new PaymentDao().findAllCategories());
        return Path.TO_MAKE_PAYMENT;
    }
}
