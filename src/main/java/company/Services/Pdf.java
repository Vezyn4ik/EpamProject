package company.Services;

import company.DAO.AccountDao;
import company.DAO.PaymentDao;
import company.Entity.Account;
import company.Entity.Payment;
import org.w3c.dom.Document;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class Pdf extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Payment payment= new PaymentDao().findById(Long.valueOf(request.getParameter("payment")));
        return null;
    }
}
