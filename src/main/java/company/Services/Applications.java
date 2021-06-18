package company.Services;

import company.DAO.AccountDao;
import company.Entity.State;
import company.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Applications extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("apps", new AccountDao().findAllByState(State.WAITING));
        return Path.APPLICATIONS;
    }
}
