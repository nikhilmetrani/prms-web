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
<script>
    function selectProgramSlot(){
        if(document.forms[0].programSlot.value!=""){
            document.forms[0].action = "${pageContext.request.contextPath}/nocturne/deletePgmSlot";
        }
        document.forms[0].submit();
    }
    
    function selectProgramDate(){
        if(document.forms[0].programDate.value!=""){
            document.forms[0].action = "${pageContext.request.contextPath}/nocturne/deletePgmSlot";
        }
        document.forms[0].submit();
    }
</script>
</head>
<body>
        <h2>
		<fmt:message key="title.deleteps" />
	</h2>
	<form action="${pageContext.request.contextPath}/nocturne/enterps" method=post>
		<center>
			<table cellpadding=5 cellspacing=2 border=0>
				<tr>
					<th width="30%"><fmt:message key="label.deleteps.value" /></th>
					<th width="45%"><fmt:message key="label.deleteps.data" /></th>
				</tr>
				<tr>
					<th width="25%"><fmt:message key="label.deleteps.programDate" /></th>
                                        <th width="25%">
                                            <select name="programDate" onchange="selectProgramDate()" >
                                                <option value="">--Select--</option>
                                                <c:forEach var="pgmDate" items="${availableDates}">
                                                    <c:choose>
                                                    <c:when test="${selectPgmDate != null && selectPgmDate eq pgmDate}"> 
                                                     <option value="${pgmDate}" selected>${pgmDate}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <option value="${pgmDate}">${pgmDate}</option>
                                                    </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </th>
				</tr>
                                
                                <tr>
                                <th width="25%"><fmt:message key="label.deleteps.programSlot" /></th>
                                    <th width="25%">
                                        <select name="programSlot" onchange="selectProgramSlot()" >
                                            <option value="">--Select--</option>
                                                <c:forEach var="pgmSlot" items="${pgmSlots}">
                                                    <c:choose>
                                                    <c:when test="${pgmSlotName != null && pgmSlotName eq pgmSlot.name}"> 
                                                    <option value="${pgmSlot.name}" selected>${pgmSlot.name}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${pgmSlot.name}">${pgmSlot.name}</option>
                                                    </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </th>
                                </tr>
			</table>                        
                        <input  type="submit" value="Submit"> <input  type="reset" value="Reset">
		</center>                     
	</form>    
</body>
</html>