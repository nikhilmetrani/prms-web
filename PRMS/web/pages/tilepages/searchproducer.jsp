<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<fmt:setBundle basename="ApplicationResources" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="title.searchproducer" /></title>

<script>
    function selectProducer(name){
        window.opener.document.getElementsByName('producerName')[0].value=name;        
        window.close();
    }

</script>
</head>
<body>
	<h2>
		<fmt:message key="title.searchproducer" />
	</h2>
        <base target="_self" />
	<form target="_self" action="${pageContext.request.contextPath}/nocturne/searchproducer" method=post  >
		<center>
			<table cellpadding=5 cellspacing=2 border=0>
				<tr>
					<th width="45%"><fmt:message key="caption.name" /></th>					
				</tr>
				<tr>
					<td><fmt:message key="fieldLabel.name.producer" /></td>
					<td><input type="text" name="producername" value="${param['producername']}" size=45 maxlength=45></td>
				</tr>				
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Search"> <input
						type="reset" value="Reset"></td>
				</tr>
			</table>
		</center>

	</form>
	<c:if test="${! empty  searchproducerlist}">
		<table class="borderAll">
			<tr>
                                <th><fmt:message key="label.producer.id" /></th>
				<th><fmt:message key="label.producer.name" /></th>
				<th><fmt:message key="label.producer.email" /></th>
				<th><fmt:message key="label.producer.phoneNumber" /></th>
                                <th><fmt:message key="label.producer.action" /></th>
			</tr>
			<c:forEach var="producer" items="${searchproducerlist}" varStatus="status">
				<tr class="${status.index%2==0?'even':'odd'}">
                                        <td class="nowrap">${producer.id}</td>
					<td class="nowrap">${producer.name}</td>
					<td class="nowrap">${producer.email}</td>
					<td class="nowrap">${producer.phoneNumber}</td>
                                        <td class="nowrap">                                           
                                             <a href="#" onclick="selectProducer('${producer.id}');">Select</a> 
                                        </td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>