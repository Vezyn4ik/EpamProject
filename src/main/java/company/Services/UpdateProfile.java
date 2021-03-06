package company.Services;

import company.DAO.UserDao;
import company.Entity.User;
import company.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.sql.Date;

public class UpdateProfile extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // UPDATE USER ////////////////////////////////////////////////////////

        User user = (User)request.getSession().getAttribute("user");
        boolean updateUser = false;

        String name = request.getParameter("name");
        if (name != null && !name.isEmpty()) {
            user.setName(name);
            updateUser = true;
        }

        String surname = request.getParameter("surname");
        if (surname != null && !surname.isEmpty()) {
            user.setSurname(surname);
            updateUser = true;
        }

        String birth=request.getParameter("birth");
        if (birth != null && !birth.isEmpty()) {
            Date birthdate = Date.valueOf(birth);
            user.setBirth(birthdate);
            updateUser = true;
        }

        String password = request.getParameter("password");
        if (password != null && !password.isEmpty()) {
            user.setPassword(password);
            updateUser = true;
        }

        String email = request.getParameter("email");
        if (email != null && !email.isEmpty()) {
            user.setEmail(email);
            updateUser = true;
        }

        if (updateUser) {
            new UserDao().updateUser(user);
        }
        return Path.COMMAND_USER_ACCOUNT;
    }
}
