<%@ include file="../jspf/page.jspf" %>
<%@ include file="../jspf/taglib.jspf" %>
<html>
<head>
    <title>Payments</title>
</head>
<body>
<%@ include file="../jspf/header.jspf" %>
<div class="card my-5">
    <div class="card-body">
        <div>
        <c:if test="${fn:length(payments) == 0}"><fmt:message key="payments_jsp.label.no_payments"/></c:if>
        </div>
        <table class="table table-bordered table-striped">
            <thead class="thead-dark">
            <tr>
                <th scope="col"><fmt:message key="payments_jsp.label.account_name"/></th>
                <th scope="col"><fmt:message key="payments_jsp.label.date"/></th>
                <th scope="col"><fmt:message key="payments_jsp.label.purpose"/></th>
                <th scope="col"><fmt:message key="payments_jsp.label.amount"/></th>
                <th scope="col"><fmt:message key="payments_jsp.label.commission"/></th>
                <th scope="col"><fmt:message key="payments_jsp.label.recipient_account"/></th>
                <th scope="col"><fmt:message key="payments_jsp.label.recipient_name"/></th>
                <th scope="col"><fmt:message key="payments_jsp.label.state"/></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="payment" items="${payments}">
            <tr>
                <td><p>${payment.account.name}</p></td>
                <td><p>${payment.date}</p></td>
                <td><p>${payment.purpose}</p></td>
                <td> <p>${payment.amount}</p></td>
                <td> <p>${payment.commission}</p></td>
                <td> <p>${payment.recipientAccount}</p></td>
                <td> <p>${payment.recipientName}</p></td>
                <td> <p>${payment.state}</p></td>
                <td> <form id="pdf" action="controller" method="post">
                    <input type="hidden" name="command" value="pdf"/>
                    <input type="hidden" name="payment" value="${payment.id}"/>
                    <p>
                        <button type="submit"><fmt:message key="payments_jsp.label.pdf"/></button>
                    </p>
                </form></td>
                </c:forEach>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<%@ include file="../jspf/footer.jspf" %>
</body>
</html>
