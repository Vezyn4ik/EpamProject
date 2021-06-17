package company.Services;

import company.DAO.AccountDao;
import company.DAO.PaymentDao;
import company.DAO.UserDao;
import company.Entity.*;
import company.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

public class MakePayment extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String recipient_account = request.getParameter("recipient_account");
        System.out.println("Request parameter: recipient_account --> " + recipient_account);

        String recipient_name = request.getParameter("recipient_name");
        System.out.println("Request parameter: recipient_name --> " + recipient_name);

        Double amount = Double.parseDouble(request.getParameter("amount"));
        System.out.println("Request parameter: amount --> " + amount);

        String purpose = request.getParameter("purpose");
        System.out.println("Request parameter: purpose --> " + purpose);

        Category category = new PaymentDao().findCategoryById(Long.parseLong(request.getParameter("category")));
        Account account = new AccountDao().findById(Long.parseLong(request.getParameter("account")));

        // error handler
        String errorMessage = null;
        String forward = Path.PAGE_ERROR_PAGE;
        if(amount*1.01>account.getBalance() || amount>account.getLimit()
                ||account.getName().equals(recipient_account) || account.state==State.LOCKED){
            return forward;
        }
        Payment payment=new Payment();
        payment.setRecipientName(recipient_name);
        payment.setRecipientAccount(recipient_account);
        payment.setAmount(amount);
        payment.setCommission(amount*0.01);
        payment.setPurpose(purpose);
        payment.setDate(new Timestamp(System.currentTimeMillis()));
        payment.setAccount(account);
        payment.setCategory(category);
        payment.setStatus(Status.PREPARED);

        if(new PaymentDao().insert(payment)){
            account.withdrawal(amount*1.01);
            new AccountDao().update(account);
            forward=Path.ACCOUNT;
        }
        Account recipient=new AccountDao().findByName(payment.getRecipientAccount());
        if(recipient!=null){
            recipient.replenishment(payment.getAmount());
            new AccountDao().update(recipient);
        }
        return forward;
    }
}
