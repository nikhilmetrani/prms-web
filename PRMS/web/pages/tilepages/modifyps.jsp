<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- 
    Document   : modifyps.jsp
    Created on : Sep 26, 2015, 07:41:40 PM
    Author     : Niu Yiming
--%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<fmt:setBundle basename="ApplicationResources" />

<title><fmt:message key="title.modifyps" /></title>
<script>
    function selectRadioProgram(){
    if(document.forms[0].radioProgram.value!=""){
        document.forms[0].action = "${pageContext.request.contextPath}/nocturne/modifyPgmSlot";
    }
    document.forms[0].submit();
}

    function selectProgramDate(){
    if(document.forms[0].programDate.value!=""){
        document.forms[0].action = "${pageContext.request.contextPath}/nocturne/modifyPgmSlot";
    }
    document.forms[0].submit();
    
    }
</script>
</head>
<body>
        <h2>
		<fmt:message key="title.modifyps" />
	</h2>
	<form action="${pageContext.request.contextPath}/nocturne/enterps" method=post>
		<center>
			<table cellpadding=5 cellspacing=2 border=0>
				<tr>
					<th width="30%"><fmt:message key="label.modifyps.value" /></th>
					<th width="45%"><fmt:message key="label.modifyps.data" /></th>
				</tr>
				<tr>
					<th width="25%"><fmt:message key="label.modifyps.programDate" /></th>
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
                                     <th width="25%"><fmt:message key="label.modifyps.startTime" /></th>
                                     <th width="25%"><input type="time" name="startTime" value="${param['startTime']}" />
                                </tr>
				<tr>
                                    <th width="25%"><fmt:message key="label.modifyps.duration" /></th>
					<th width="25%"><input type="text" name="pgmSlotDuration"
                                                                   value="${param['pgmSlotDuration']}" size=15 maxlength=20></th>
				</tr>
                                <tr>
                                 <th width="25%"><fmt:message key="label.modifyps.radioProgram" /></th>
                                        <th width="25%">
                                            <select name="radioProgram" onchange="selectRadioProgram()" >
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