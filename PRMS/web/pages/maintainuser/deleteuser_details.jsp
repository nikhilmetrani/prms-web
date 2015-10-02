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
        <style type="text/css">
            errorMessage{
                color: crimson;
                margin-bottom: 10px;
            }
            successMessage{
                color: green;
                margin-bottom: 10px;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><fmt:message key="label.modifyuser.title" /></title>
    </head>
    <body>
        <h2>
            <fmt:message key="label.deleteuser.title" />
        </h2>
        <%
            UserDaoImpl userDao = new UserDaoImpl();
            User user = new User();
            try {
                user = userDao.getObject(request.getParameter("id"));
            } catch (Exception e) {

            }
        %>

        <form action="${pageContext.request.contextPath}/nocturne/deleteuser"
              method="post">
            <errorMessage>${errorMessage}</errorMessage>
           <successMessage>${successMessage}</successMessage>
            <p id="demo"></p>
            <center>
                <table cellpadding=4 cellspacing=2 border=0>	
                    <tr>
                        <th width="45%">User details:</th>
                    </tr>

                    <tr>
                        <th><fmt:message key="label.cruduser.id" /></th>

                        <td><input type="text" name="id" value="${user.id}" size=45 maxlength=45 readonly></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="label.cruduser.name" /></th>

                        <td><input type="text" name="name" value="${user.name}" size=45 maxlength=45 readonly></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="label.cruduser.password" /></th>

                        <td><input type="text" name="password" value="${user.password}" size=45 maxlength=45 readonly></td>
                    </tr>
                    <tr>
                        <th valign="top"><fmt:message key="label.cruduser.role" /></th>
                        <td><input type="textarea" name="role" value="${user.getRoleString()}" size=45 maxlength=45 readonly></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="Delete User" name="delete"></td>
                    </tr>
                </table>
            </center>
        </form>
    </body>
</html>