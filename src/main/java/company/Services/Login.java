package company.Services;

import company.DAO.UserDao;
import company.Entity.Role;
import company.Entity.User;
import company.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;


public class Login extends Command {


    private static final long serialVersionUID = -3071536593627692473L;

    //private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        //log.debug("Command starts");
        HttpSession session = request.getSession();
        // obtain login and password from the request
        String login = request.getParameter("login");
        System.out.println("Request parameter: login --> " + login);
        //log.trace("Request parameter: login --> " + login);
        String password = request.getParameter("password");
        System.out.println("Request parameter: password --> " + password);
        // error handler
        String errorMessage = null;
        String forward = Path.PAGE_LOGIN;

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            System.out.println("errorMessage " + errorMessage);
            //log.error("errorMessage --> " + errorMessage);
            return forward;
        }
        User user = new UserDao().findUserByLogin(login);
        //log.trace("Found in DB: user --> " + user);
        if (user == null || !password.equals(user.getPassword())) {
            errorMessage = "Cannot find user with such login/password";
            request.setAttribute("errorMessage", errorMessage);
            //log.error("errorMessage --> " + errorMessage);
            return forward;
        } else {
            Role userRole = user.getRole();
            //log.trace("userRole --> " + userRole);
            forward = Path.COMMAND_USER_ACCOUNT;
            session.setAttribute("user", user);
            //log.trace("Set the session attribute: user --> " + user);
            session.setAttribute("userRole", userRole);
            //log.trace("Set the session attribute: userRole --> " + userRole);
            //log.info("User " + user + " logged as " + userRole.toString().toLowerCase());
            // work with i18n
            String userLocaleName = user.getName() + " " + user.getSurname();
            //log.trace("userLocalName --> " + userLocaleName);

            if (userLocaleName != null && !userLocaleName.isEmpty()) {
                Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", userLocaleName);
                session.setAttribute("defaultLocale", userLocaleName);
                //	log.trace("Set the session attribute: defaultLocaleName --> " + userLocaleName);
                //	log.info("Locale for user: defaultLocale --> " + userLocaleName);
            }
        }
        //log.debug("Command finished");
        return forward;
    }
}
