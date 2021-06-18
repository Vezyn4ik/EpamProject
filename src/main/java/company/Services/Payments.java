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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Payments extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PaymentDao paymentDao = new PaymentDao();
        List<Payment> payments;
        System.out.println(request.getParameter("accountId"));
        if(!request.getParameter("accountId").isEmpty()){
            Account account=new AccountDao().findById(Long.parseLong(request.getParameter("accountId")));
            payments = paymentDao.findAllByAccount(account);
            payments.addAll(paymentDao.findAllByRecipientAccount(account.getName()));
        }else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            List<Account> temp = new AccountDao().findAllByUser(user);
            payments = new ArrayList<>();
            for (Account account : temp) {
                payments.addAll(paymentDao.findAllByAccount(account));
            }
        }
        String sort =request.getParameter("sort");

        switch (sort) {
            case "numberUp":
                payments= payments.stream().sorted((o1, o2) -> (int)(o1.getId()-o2.getId())).collect(Collectors.toList());
                break;
            case "numberDown":
                payments= payments.stream().sorted((o1, o2) -> (int)(o2.getId()-o1.getId())).collect(Collectors.toList());
                break;
            case "dateUp":
                payments= payments.stream().sorted(Comparator.comparing(Payment::getDate)).collect(Collectors.toList());
                break;
            case "dateDown":
                payments= payments.stream().sorted(Comparator.comparing(Payment::getDate).reversed()).collect(Collectors.toList());
                break;
        }
        request.setAttribute("payments", payments);
        return Path.PAYMENTS;
    }
}
