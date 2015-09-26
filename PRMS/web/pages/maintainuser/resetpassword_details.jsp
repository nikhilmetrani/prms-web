<%@page import="sg.edu.nus.iss.phoenix.authenticate.entity.User"%>
<%@page import="sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
    <head>
        <fmt:setBundle basename="ApplicationResources" />
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><fmt:message key="label.cruduser.resetpw" /></title>
        <script type="text/javascript">
            function validateInput() {
                newPassword = document.forms["usrForm"]["new_password"].value;
                oldPassword = document.forms["usrForm"]["old_password"].value;
                userId = document.forms["usrForm"]["id"].value;
                alert("old"+Password);
                alert("id"+userId);
                if (newPassword == null || newPassword == "")
                {
                    alert("Please enter new password");
                    return false;
                }
                if (newPassword == userid) {
                    alert("Password must be different from UserId");
                    usrform.newPassword.focus();
                    return false;
                }

                if (newPassword == oldPassword) {
                    alert("Password must be different from Old password");
                    usrform.newPassword.focus();
                    return false;
                }
                var minNumberofChars = 6;
                var maxNumberofChars = 15;
                var regularExpression = /^[a-zA-Z0-9!@#$%^&*]{6,16}$/;
                if (newPassword.length < minNumberofChars || newPassword.length > maxNumberofChars) {
                    alert("Password length must be greater than 6 and less than 15 characters");
                    usrform.newPassword.focus();
                    return false;
                }
                if (!regularExpression.test(newPassword)) {
                    alert("Password should contain atleast one number and one special character");
                    usrform.newPassword.focus();
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <h2>
            <fmt:message key="label.resetpassword.title" />
        </h2>
        <%
            UserDaoImpl userDao = new UserDaoImpl();
            User user = new User();
            try {
                user = userDao.getObject(request.getParameter("id"));
            } catch (Exception e) {

            }
        %>

        <form name="usrForm" action="${pageContext.request.contextPath}/nocturne/resetpassword" method="post">
            <p id="demo"></p>
            <center>
                <table cellpadding=4 cellspacing=2 border=0>

                    <tr>
                        <th><fmt:message key="label.cruduser.id" /></th>

                        <td><input type="text" name="id" value="${user.id}" size=15 maxlength=20 readonly></td>
                    </tr
                    <tr>
                        <th><fmt:message key="label.cruduser.name" /></th>
                        <td><input type="text" name="name" value="${user.name}" size=15 maxlength=20 readonly></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="label.cruduser.oldpassword" /></th>
                        <td><input type="text" name="old_password" value="${user.password}" size=15 maxlength=20 readonly></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="label.cruduser.newpassword" /></th>

                        <td><input type="text" name="new_password" value="" size=15 maxlength=20></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="Reset Password"> 
                        </td>
                    </tr>
                </table>
            </center>
        </form>
    </body>
</html>