package company.Services;

import company.DAO.AccountDao;
import company.Entity.Account;
import company.Entity.User;
import company.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

public class AddAccount extends Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        // obtain login and password from the request
        String name = request.getParameter("name");
        System.out.println("Request parameter: name --> " + name);

        Double limit = Double.valueOf(request.getParameter("limit"));
        System.out.println("Request parameter: limit --> " + limit);

        String currency = request.getParameter("currency");
        System.out.println("Request parameter: currency --> " + currency);

        // error handler
        String errorMessage = null;
        String forward = Path.PAGE_ERROR_PAGE;

        /*if(new UserDao().findUserByLogin(login)!=null){
            errorMessage = "User with such login is exists";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }*/

        Account account=new Account();
        account.setName(name);
        account.setCreateTime(new Timestamp(System.currentTimeMillis()));
        account.setLimit(limit);
        account.setCurrency(currency);
        account.setUser((User) session.getAttribute("user"));

        if(new AccountDao().insert(account)){
            forward=new UserProfile().execute(request,response);
        }
        return forward;
    }
}
