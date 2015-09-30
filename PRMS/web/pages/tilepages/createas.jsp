<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<fmt:setBundle basename="ApplicationResources" />

<title><fmt:message key="title.createas" /></title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/nocturne/enteras" method=post>
        <center>
            <c:if test="${errorMessage!=''}">
                <p><font color="red">Error: ${errorMessage}"</font></p>
            </c:if>
            <table cellpadding=4 cellspacing=2 border=0>
                <tr>
                    <th width="30%"><fmt:message key="label.createas.value" /></th>
                    <th width="45%"><fmt:message key="label.createas.data" /></th>
                </tr>
                <tr>
                    <td><fmt:message key="label.createas.year" /></td>
                    <td><input type="text" pattern="(\d{4})" name="year" value="${param['year']}" size=25
                               maxlength=4 title="Please enter a valid year (current year or higher) in format YYYY. Example: 2015" required>
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="label.createas.assignedBy" /></td>
                    <td><input type="text" name="assignedBy"
                            value="${param['assignedBy']}" size=25 readonly="readonly">
                    </td>
                </tr>
            </table>
            <input type="submit" value="Create"> <input type="reset"
                                                        value="Reset"> 
            <!--input type="button" value="Back" onclick="pages/maintainSchedule/setupSchedule.jsp"-->
        </center>
    </form>
    <script language=javascript>
        document.forms[0].year.focus();
    </script>
</body>
</html>