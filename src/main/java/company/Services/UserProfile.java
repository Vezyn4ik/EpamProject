package company.Services;

import company.DAO.AccountDao;
import company.DAO.UserDao;
import company.Entity.Account;
import company.Entity.Payment;
import company.Entity.User;
import company.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserProfile extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
       //String login = (String) session.getAttribute("login");
       User user= (User) session.getAttribute("user");

        // put user order beans list to request
        request.setAttribute("user", user);
        List<Account> accounts = new AccountDao().findAllByUser(user);

        String sort =request.getParameter("sort");
        if(sort!=null) {
            switch (sort) {
                case "numberUp":
                    accounts = accounts.stream().sorted((o1, o2) -> (int) (o1.getId() - o2.getId())).collect(Collectors.toList());
                    break;
                case "numberDown":
                    accounts = accounts.stream().sorted((o1, o2) -> (int) (o2.getId() - o1.getId())).collect(Collectors.toList());
                    break;
                case "nameUp":
                    accounts = accounts.stream().sorted(Comparator.comparing(Account::getName)).collect(Collectors.toList());
                    break;
                case "nameDown":
                    accounts = accounts.stream().sorted(Comparator.comparing(Account::getName).reversed()).collect(Collectors.toList());
                    break;
                case "balanceUp":
                    accounts = accounts.stream().sorted(Comparator.comparing(Account::getBalance)).collect(Collectors.toList());
                    break;
                case "balanceDown":
                    accounts = accounts.stream().sorted(Comparator.comparing(Account::getBalance).reversed()).collect(Collectors.toList());
                    break;
            }
        }

        request.setAttribute("accounts",accounts);

        return Path.ACCOUNT;
    }
}
