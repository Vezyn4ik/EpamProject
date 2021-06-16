<%@ include file="../jspf/page.jspf" %>
<%@ include file="../jspf/taglib.jspf" %>

<html>

<c:set var="title" value="make_payment"/>
<%@ include file="../jspf/head.jspf" %>

<body>
<%@ include file="../jspf/header.jspf" %>
<div class="wrap content card my-5 card-body">
            <form id="register_form" action="controller" method="post">
                <input type="hidden" name="command" value="makePayment"/>
                <fieldset>
                    <legend>
                        Account
                    </legend>
                     <select name="account" required>
                         <c:forEach var="account" items="${accounts}">
                             <option value="${account.id}">${account.name}</option>
                         </c:forEach>
                         <br/>
                </select>

                    <legend>
                        Category
                    </legend>
                    <select name="category" required>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                        <br/>
                    </select>
                </fieldset>
                <br/>

                <fieldset>
                    <legend>
                        Recipient Account
                    </legend>
                    <input name="recipient_account" required/><br/>

                    <legend>
                        Recipient Name
                    </legend>
                    <input name="recipient_name" required/><br/>
                </fieldset>
                <br/>

                <fieldset>
                    <legend>
                        Amount
                    </legend>
                    <input name="amount" type="number" required/><br/>

                    <legend>
                        Purpose
                    </legend>
                    <label>
                        <input name="purpose" required/>
                    </label>
                </fieldset>
                <br/>
                <fieldset>
                    <legend>
                        Commission ${amount*0.01}
                    </legend>
                </fieldset>
                <br/>
                <input type="submit" value='<fmt:message key="login_jsp.button.register"/>'>
            </form>
</div>
    <%@ include file="../jspf/footer.jspf" %>

</body>
</html>