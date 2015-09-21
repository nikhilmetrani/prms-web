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
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th width="30%"><fmt:message key="label.createas.year" /></th>
					<th width="45%"><fmt:message key="label.createas.assignedBy" /></th>
				</tr>
				<tr>
					<td><fmt:message key="label.createas.year" /></td>
					<td><input type="text" name="year" value="${param['year']}" size=15
								maxlength=4>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="label.createas.assignedBy" /></td>
					<td><input type="text" name="assignedBy"
						value="${param['assignedBy']}" size=45 maxlength=20 readonly="readonly">
                                        </td>
				</tr>
			</table>
		</center>
		<input type="submit" value="Submit"> <input type="reset"
			value="Reset">
	</form>

</body>
</html>