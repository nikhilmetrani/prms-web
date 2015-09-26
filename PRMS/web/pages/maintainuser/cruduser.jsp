<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<link href="<c:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
<fmt:setBundle basename="ApplicationResources" />
<title> <fmt:message key="title.cruduser"/> </title>
</head>
<body>
        <h1><fmt:message key="label.cruduser"/></h1>
        <c:url var="url" scope="page" value="/nocturne/maintainuser">
        	<c:param name="name" value=""/>
                <c:param name="id" value=""/>
                <c:param name="password" value=""/>
                <c:param name="role" value=""/>
                <c:param name="insert" value="true"/>
        </c:url>
        <a href="${url}"><fmt:message key="label.cruduser.add"/></a>
        <br/><br/>
        
        <c:url var="url" scope="page" value="/nocturne/modifyuser">
        </c:url>
        <a href="${url}"><fmt:message key="label.cruduser.edit"/></a>
        <br/><br/>
         <c:url var="url" scope="page" value="/nocturne/deleteuser">
        </c:url>
        <a href="${url}"><fmt:message key="label.cruduser.delete"/></a>
      
        <br/><br/>
        <c:url var="url" scope="page" value="/nocturne/resetpassword">
        </c:url>
        <a href="${url}"><fmt:message key="label.cruduser.resetpw"/></a>
        <br/><br/>
        
</body>
</html>