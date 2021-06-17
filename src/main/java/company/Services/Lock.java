package company.Services;

import company.DAO.AccountDao;
import company.DAO.UserDao;
import company.Entity.Account;
import company.Entity.State;
import company.Entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Lock extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String type=request.getParameter("type");
        if(type.equals("account")) {
            System.out.println("Account req:" + request.getParameter("account"));
            Account account = new AccountDao().findById(Long.valueOf(request.getParameter("account")));
            account.setState(State.LOCKED);
            new AccountDao().update(account);
        }
        else if(type.equals("user")){
            System.out.println("User req:" + request.getParameter("userId"));
            User user=new UserDao().findUser(Long.valueOf(request.getParameter("userId")));
            request.setAttribute("userId",user.getId());
            user.setState(State.LOCKED);
            new UserDao().updateUser(user);
            System.out.println(request.getParameter("userId"));
        }
        System.out.println(request.getParameter("userId"));
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
                System.out.println(request.getParameter("userId"));
                return new UserData().execute(request,response);
            default:
                return null;

        }
    }
}
