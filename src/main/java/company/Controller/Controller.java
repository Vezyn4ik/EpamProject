package company.Controller;

import company.Services.Command;
import company.Services.CommandContainer;
import company.Services.Login;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;


public class Controller extends HttpServlet {

    private static final long serialVersionUID = 2423353715955164816L;

    private static final Logger log = Logger.getLogger(Controller.class);
    private static Command lastCommand = new Login();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Main method of this controller, which process request and return page
     */

    private void process(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {

        log.debug("Controller starts");

        // extract command name from the request
        String commandName = request.getParameter("command");
        log.trace("Request parameter: command --> " + commandName);

        // change local if commandName is selectLocale or obtain command object by its name
        Command command;
        if (commandName.equals("selectLocale")) {
            String localeToSet = request.getParameter("localeToSet");
            log.trace("localToSet --> "+localeToSet);
            if (localeToSet != null && !localeToSet.isEmpty()) {
                HttpSession session = request.getSession();
                Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", localeToSet);
                session.setAttribute("defaultLocale", localeToSet);
                log.debug("Set locale");
            }
            command = lastCommand;
        } else {
            command = CommandContainer.get(commandName);
        }

        log.trace("Obtained command --> " + command);
        System.out.println(command);

        // execute command and get forward address
        String forward = command.execute(request, response);
        log.trace("Forward address --> " + forward);

        log.debug("Controller finished, now go to forward address --> " + forward);
        lastCommand = command;

        // if the forward address is not null go to the address
        if (forward != null) {
            RequestDispatcher disp = request.getRequestDispatcher(forward);
            disp.forward(request, response);
        }
    }
}
