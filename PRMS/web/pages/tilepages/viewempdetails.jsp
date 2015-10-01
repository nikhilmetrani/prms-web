<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<fmt:setBundle basename="ApplicationResources" />

<title><fmt:message key="title.viewempdetails" /></title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/nocturne/updateprofile" method=post>
        <center>
            <c:if test="${errorMessage!=''}">
                <p><font color="red">${errorMessage}</font></p>
            </c:if>
            <c:if test="${errorMessage==''}">
            <table cellpadding=4 cellspacing=2 border=0>
                <tr>
                    <th width="30%"><fmt:message key="label.createas.value" /></th>
                    <th width="45%"><fmt:message key="label.createas.data" /></th>
                </tr>
                <tr>
                    <td><fmt:message key="label.viewempdetails.name" /></td>
                    <td><input type="text" name="name" value="${name}" size=45 >
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="label.viewempdetails.email" /></td>
                    <td><input type="email" name="email" value="${email}" size=45 
                               title="Please enter a valid email address. Example: yourname@domain.com">
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="label.viewempdetails.phoneno" /></td>
                    <td><input type="text" pattern="(?:[\+\d\(](?:[\-\d+\s\(\)])+(?:[\d\)]))"
                               title="Please enter a valid phone number"
                               name="phoneNo" value="${phoneNo}" maxlength="18" size=45 >
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="label.viewempdetails.sitelink" /></td>
                    <td><input type="url" name="siteLink" value="${siteLink}" 
                               title="Please enter a valid website address." size=45 >
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="label.viewempdetails.profileimage" /></td>
                    <td><img src="${profileImage}" height="192" width="192">
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="file" accept="image/*" name="profileImage" id="newProfileImage" size=45 >
                    </td>
                </tr>
            </table>
            <input type="submit" value="Update Profile"> <input type="reset"
                    value="Reset">
            </c:if>
        </center>
    </form>
    <script language=javascript>
        document.forms[0].name.focus();
    </script>
</body>
</html>