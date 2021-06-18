package company.Services;

import company.DAO.AccountDao;
import company.DAO.UserDao;
import company.Entity.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Unlock extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String type=request.getParameter("type");
        if(type.equals("account")) {
            System.out.println("Account req:" + request.getParameter("account"));
            Account account = new AccountDao().findById(Long.valueOf(request.getParameter("account")));
            HttpSession session=request.getSession();
            User user = (User) session.getAttribute("user");
            if(user.getRole()== Role.ADMIN){
            account.setState(State.UNLOCKED);
            } else if(user.getRole()==Role.USER){
                account.setState(State.WAITING);
            }

            new AccountDao().update(account);
        }
        else if(type.equals("user")){
            System.out.println("User req:" + request.getParameter("userId"));
            User user=new UserDao().findUser(Long.valueOf(request.getParameter("userId")));
            user.setStatus(Status.UNLOCKED);
            new UserDao().updateUser(user);
        }
        return selectPage(request,response);
    }
    private String selectPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page= request.getParameter("page");
        switch (page){
            case "profile":
                return new UserProfile().execute(request, response);
            case "list_users":
                return new ListUsers().execute(request, response);
            case "user_data":
                return new UserData().execute(request,response);
            case "applications":
                return new Applications().execute(request,response);
            default:
                return null;

        }
    }
}