<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="adel" class="sg.edu.nus.iss.phoenix.authenticate.delegate.AuthenticateDelegate" scope="page"/>
<%@page import="sg.edu.nus.iss.phoenix.authenticate.entity.User"%>
<%@page import="sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl"%>
<html>
    <head>
        <fmt:setBundle basename="ApplicationResources" />
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><fmt:message key="label.modifyuser.title" /></title>
    </head>
    <body>
        <h2>
            <fmt:message key="label.modifyuser.title" />
        </h2>
        <%
            UserDaoImpl userDao = new UserDaoImpl();
            User user = new User();
            try {
                user = userDao.getObject(request.getParameter("name"));
            } catch (Exception e) {

            }
        %>

        <form action="${pageContext.request.contextPath}/nocturne/modifyuser"
              method="post">
            <p id="demo"></p>
            <center>
                <table cellpadding=4 cellspacing=2 border=0>	
                    <tr>
                        <th><fmt:message key="label.cruduser.id" /></th>
                        <td><input type="text" name="id" value="${user.id}" size=15 maxlength=20 readonly></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="label.cruduser.name" /></th>
                        <td><input type="text" name="name" value="${user.name}" size=15 maxlength=20></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="label.cruduser.password" /></th>
                        <td><input type="text" name="password" value="${user.password}" size=15 maxlength=20></td>
                    </tr>
                    <tr>
                        <th valign="top"><fmt:message key="label.cruduser.role" /></th>
                        <td>
                            <select name="role" id="role" multiple="multiple" style="width:125px">
                                <c:forEach var="item" items="${adel.findAllRole()}" >
                                    <c:set var="strRole" value="${item.getRole()}" />
                                    <c:choose>
                                        <c:when test="${fn:contains(strRoles, strRole)}">
                                            <option value="${item.getRole()}" selected="true">${item.getRole()}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${item.getRole()}">${item.getRole()}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>                              
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="Save"> 
                        </td>
                    </tr>
                </table>
            </center>
        </form>
    </body>
</html>