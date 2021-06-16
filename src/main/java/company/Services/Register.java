package company.Services;

import company.DAO.UserDao;
import company.Entity.Role;
import company.Entity.User;
import company.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

public class Register extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        Date birth = Date.valueOf(request.getParameter("birth"));
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("telephone");
        // error handler
        String errorMessage = null;
        String forward = Path.PAGE_ERROR_PAGE;

        if (new UserDao().findUserByLogin(login) != null) {
            errorMessage = "User with such login is exists";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setBirth(birth);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(Role.USER);
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        user.setTelephone(telephone);
        user.setEmail(email);

        if (new UserDao().insertUser(user)) {
            forward = Path.ACCOUNT;
        }

        return forward;
    }
}
