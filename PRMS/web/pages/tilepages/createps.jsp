<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- 
    Document   : createps.jsp
    Created on : Sep 24, 2015, 09:51:40 PM
    Author     : Rushabh Shah
--%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<fmt:setBundle basename="ApplicationResources" />

<title><fmt:message key="title.createps" /></title>
<script>
    function selectRadioProgram(){
    if(document.forms[0].radioProgram.value!=""){
        document.forms[0].action = "${pageContext.request.contextPath}/nocturne/createPgmSlot";
    }
    document.forms[0].submit();
}

    function selectProgramDate(){
    if(document.forms[0].programDate.value!=""){
        document.forms[0].action = "${pageContext.request.contextPath}/nocturne/createPgmSlot";
    }
    document.forms[0].submit();
    
    }
</script>
</head>
<body>
        <h2>
		<fmt:message key="title.createps" />
	</h2>
	<form action="${pageContext.request.contextPath}/nocturne/enterps" method=post>
		<center>
			<table cellpadding=5 cellspacing=2 border=0>
                                <tr valign="top">
                                     <c:if test="${errPgmSlot!=null && !errPgmSlot.isEmpty()}">
                                        <td width="40%" style="color: red"><b>Error</b></td>
                                        <td style="color: red">${errPgmSlot}</td>
                                     </c:if>
                                </tr>
				<tr>
					<th width="30%"><fmt:message key="label.createps.value" /></th>
					<th width="45%"><fmt:message key="label.createps.data" /></th>
				</tr>
				<tr>
					<th width="25%"><fmt:message key="label.createps.programDate" /></th>
                                        <th width="25%">
                                            <select required name="programDate" onchange="selectProgramDate()">
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
                                     <th width="25%"><fmt:message key="label.createps.startTime" /></th>
                                     <th width="25%"><input type="time" name="startTime" pattern="([0-1][0-9]|20|21|22|23|[0-9]):([0-5][0-9]|[0-9]):([0-5][0-9]|[0-9])"
                                                            value="${param['startTime']}" size=15 maxlength=8 title=" of Start time" required />
                                </tr>
				<tr>
                                    <th width="25%"><fmt:message key="label.createps.duration" /></th>
				    <th width="25%"><input type="time" name="pgmSlotDuration" pattern="([0-1][0-9]|20|21|22|23|[0-9]):([0-5][0-9]|[0-9]):([0-5][0-9]|[0-9])"
                                                             value="${param['pgmSlotDuration']}" size=15 maxlength=8 title=" of Duration" required></th>
				</tr>
                                <tr>
                                 <th width="25%"><fmt:message key="label.createps.radioProgram" /></th>
                                        <th width="25%">
                                            <select required name="radioProgram" onchange="selectRadioProgram()" >
                                                <option value="">--Select--</option>
                                                <c:forEach var="radioPgm" items="${radioPgms}">
                                                    <c:choose>
                                                    <c:when test="${radioPgmName != null && radioPgmName eq radioPgm.name}"> 
                                                    <option value="${radioPgm.name}" selected>${radioPgm.name}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <option value="${radioPgm.name}">${radioPgm.name}</option>
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