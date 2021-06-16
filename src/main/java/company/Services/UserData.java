package company.Services;

import company.DAO.AccountDao;
import company.DAO.UserDao;
import company.Entity.Account;
import company.Entity.User;
import company.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserData extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User viewedUser = new UserDao().findUser(Long.parseLong(request.getParameter("userId")));
        request.setAttribute("viewedUser",viewedUser);
        List<Account> accountList= new AccountDao().findAllByUser(viewedUser);
        request.setAttribute("accountList",accountList);
        return Path.USER_DATA;
    }
}
