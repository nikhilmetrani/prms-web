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
                    <p width="30%"><fmt:message key="${errorMessage}" /></p>
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
					<td><input type="text" name="email" value="${email}" size=45 >
					</td>
				</tr>
                                <tr>
					<td><fmt:message key="label.viewempdetails.phoneno" /></td>
					<td><input type="text" name="phoneNo" value="${phoneNo}" size=45 >
					</td>
				</tr>
                                <tr>
					<td><fmt:message key="label.viewempdetails.sitelink" /></td>
					<td><input type="text" name="siteLink" value="${siteLink}" size=45 >
					</td>
				</tr>
                                <tr>
					<td><fmt:message key="label.viewempdetails.profileimage" /></td>
					<td><input type="text" name="profileImage" value="${profileImage}" size=45 >
					</td>
				</tr>
			</table>
		</center>
		<input type="submit" value="Submit"> <input type="reset"
			value="Reset">
	</form>
    <script language=javascript>
        document.forms[0].name.focus();
    </script>
</body>
</html>