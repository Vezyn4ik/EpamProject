<%@ include file="../jspf/page.jspf" %>
<%@ include file="../jspf/taglib.jspf" %>
<html>
<head>
    <title>Payments</title>
    <link rel="stylesheet" type="text/css" media="screen" href="../../style/profile.css"/>
    <%@ include file="../jspf/head.jspf" %>
</head>
<body>
<%@ include file="../jspf/header.jspf" %>
<div class="card my-5">
    <div class="card-body">
        <div>
            <c:if test="${fn:length(payments) == 0}"><fmt:message key="payments_jsp.label.no_payments"/></c:if>
        </div>
        <c:if test="${fn:length(payments) > 0}">
        <div class="btn-group">
            <button class="btn btn-lg btn-light" onclick="location.href='/controller?command=payments&accountId=${account.id}&sort=numberUp'" >numberup</button>
            <button class="btn btn-lg btn-light" onclick="location.href='/controller?command=payments&accountId=${account.id}&sort=numberDown'">numberdown</button>
            <button class="btn btn-lg btn-light" onclick="location.href='/controller?command=payments&accountId=${account.id}&sort=dateUp'"> dataup</button>
            <button class="btn btn-lg btn-light" onclick="location.href='/controller?command=payments&accountId=${account.id}&sort=dateDown'">datadown</button>
        </div>
            <table class="table table-bordered table-striped">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">id</th>
                    <th scope="col"><fmt:message key="payments_jsp.label.account_name"/></th>
                    <th scope="col"><fmt:message key="payments_jsp.label.date"/></th>
                    <th scope="col"><fmt:message key="payments_jsp.label.purpose"/></th>
                    <th scope="col"><fmt:message key="payments_jsp.label.amount"/></th>
                    <th scope="col"><fmt:message key="payments_jsp.label.commission"/></th>
                    <th scope="col"><fmt:message key="payments_jsp.label.recipient_account"/></th>
                    <th scope="col"><fmt:message key="payments_jsp.label.recipient_name"/></th>
                    <th scope="col"><fmt:message key="payments_jsp.label.state"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="payment" items="${payments}">
                <tr>
                    <td><p>${payment.id}</p></td>
                    <td><p>${payment.account.name}</p></td>
                    <td><p>${payment.date}</p></td>
                    <td><p>${payment.purpose}</p></td>
                    <td><p>${payment.amount}</p></td>
                    <td><p>${payment.commission}</p></td>
                    <td><p>${payment.recipientAccount}</p></td>
                    <td><p>${payment.recipientName}</p></td>
                    <td><p>${payment.status}</p></td>
                    </c:forEach>
                </tr>
                </tbody>
            </table>
        </c:if>
    </div>
</div>
</body>
</html>
