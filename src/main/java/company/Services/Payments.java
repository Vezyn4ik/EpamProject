package company.Services;

import company.DAO.AccountDao;
import company.DAO.PaymentDao;
import company.Entity.Account;
import company.Entity.Payment;
import company.Entity.User;
import company.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Payments extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PaymentDao paymentDao = new PaymentDao();
        List<Payment> payments;
        System.out.println(request.getParameter("accountId"));
        if(!request.getParameter("accountId").isEmpty()){
            payments = paymentDao.findAllByAccount(new AccountDao().findById(Long.parseLong(request.getParameter("accountId"))));
        }else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            List<Account> temp = new AccountDao().findAllByUser(user);
            payments = new ArrayList<>();
            for (Account account : temp) {
                payments.addAll(paymentDao.findAllByAccount(account));
            }
        }
        request.setAttribute("payments", payments);
        return Path.PAYMENTS;
    }
}
