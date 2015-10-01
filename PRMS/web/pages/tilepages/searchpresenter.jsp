<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<fmt:setBundle basename="ApplicationResources" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="title.searchpresenter" /></title>
</head>
<body>
	<h2>
		<fmt:message key="title.searchpresenter" />
	</h2>
	<form action="${pageContext.request.contextPath}/nocturne/searchpresenter" method=post>
		<center>
			<table cellpadding=5 cellspacing=2 border=0>
				<tr>
					<th width="45%"><fmt:message key="caption.name" /></th>					
				</tr>
				<tr>
					<td><fmt:message key="fieldLabel.name.presenter" /></td>
					<td><input type="text" name="name" value="${param['presentername']}" size=45 maxlength=45></td>
				</tr>				
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Submit"> <input
						type="reset" value="Reset"></td>
				</tr>
			</table>
		</center>

	</form>
	<c:if test="${! empty  searchpresenterlist}">
		<table class="borderAll">
			<tr>
				<th><fmt:message key="label.presenter.name" /></th>
				<th><fmt:message key="label.presenter.email" /></th>
				<th><fmt:message key="label.presenter.phoneNumber" /></th>
                                <th><fmt:message key="label.presenter.action" /></th>
			</tr>
			<c:forEach var="presenter" items="${searchpresenterlist}" varStatus="status">
				<tr class="${status.index%2==0?'even':'odd'}">
					<td class="nowrap">${presenter.name}</td>
					<td class="nowrap">${presenter.email}</td>
					<td class="nowrap">${presenter.phoneNumber}</td>
                                        <td class="nowrap">
                                           
                                        </td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>