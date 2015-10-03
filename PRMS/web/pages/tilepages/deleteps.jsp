<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- 
    Document   : deleteps.jsp
    Created on : Sep 26, 2015, 08:30:12 PM
    Author     : Niu Yiming
--%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<fmt:setBundle basename="ApplicationResources" />

<title><fmt:message key="title.deleteps" /></title>

</head>
<body>
        <h2>
		<fmt:message key="title.deleteps" />
	</h2>
	<form action="${pageContext.request.contextPath}/nocturne/deleteps" method=post>
		<center>
			<table cellpadding=5 cellspacing=2 border=0>
				<tr>
                                    <th width="30%"><fmt:message key="label.deleteps.value" /></th>
                                    <th width="45%"><fmt:message key="label.deleteps.data" /></th>
				</tr>
				<tr>
                                    <th width="25%"><fmt:message key="label.deleteps.programDate" /></th>
                                        <th width="25%">
                                            
                                        </th>
				</tr>
                                
                                <tr>
                                <th width="25%"><fmt:message key="label.deleteps.startTime" /></th>
                                    <th width="25%">
                                        
                                    </th>
                                </tr>
			</table>                        
                        <input  type="submit" value="Delete"> <input  type="reset" value="Reset">
		</center>                     
	</form>    
</body>
</html>