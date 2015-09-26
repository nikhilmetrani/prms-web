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
    </head>
    <body>
        <h2>
            <fmt:message key="label.cruduser.resetpw" />
        </h2>
        <%
            UserDaoImpl userDao = new UserDaoImpl();
            User user = new User();
            try {
                user = userDao.getObject(request.getParameter("id"));
            } catch (Exception e) {

            }
        %>

        <form action="${pageContext.request.contextPath}/nocturne/resetpassword"
              method="post">
            ${successMessage}

            <center>
                <table class="framed">
                    <tr>
                        <th width="45%"><fmt:message key="caption.search.user" /></th>
                    </tr>
                    <tr>
                        <td><input type="text" name="id" value="" size=45 maxlength=45></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" value="Submit"> <input
                                type="reset" value="Reset"></td>
                    </tr>
                </table>
            </center>
        </form>

    </body>
</html>