<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<fmt:setBundle basename="ApplicationResources" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="title.setupSchedule" /></title>
<script type="text/javascript">
function selectAnnualSchedule(){
    if(document.forms[0].annualSch.value!=""){
        document.forms[0].action = "${pageContext.request.contextPath}/nocturne/selectAnnualSchedule";
    }
    document.forms[0].submit();
}

function selectWeeklySchedule(){
    if(document.forms[0].weeklySch.value!=""){
        document.forms[0].action = "${pageContext.request.contextPath}/nocturne/selectWeeklySchedule";
        document.forms[0].submit();
    }else{
        selectAnnualSchedule();
    }
}

function copyWeeklySchedule(){
    document.forms[0].actionType.value = "copy";
    document.forms[0].action = "${pageContext.request.contextPath}/nocturne/copyWeeklySchedule";
    document.forms[0].submit();
}

function confirmCopy(){
    if(confirm('All the Program Slots in the selected Weekly Schedule will be replaced with the copied slots. Do you want to continue?', 'Yes|No')){
        document.forms[0].action = "${pageContext.request.contextPath}/nocturne/confirmCopy";
        document.forms[0].submit();
    }
}

function cancelCopy(){
    if(confirm('Do you want to Cancel the copy operation?', 'Yes|No')){
        document.forms[0].action = "${pageContext.request.contextPath}/nocturne/cancelCopy";
        document.forms[0].submit();
    }
}
</script>
</head>
<body>
	<h2>
		<fmt:message key="title.setupSchedule" />
	</h2>
	<form action="${pageContext.request.contextPath}/nocturne/viewSchedule"
		method=post>
            <input type="hidden" name="actionType" value="${actionType}" />
		<center>
			<table class="framed">
				<tr>
                                    <td colspan="2" align="left">
                                        <c:if test="${actionType == null || actionType eq ''}">
                                        <a href="#" onclick="">Create Annual Schedule</a> 
                                        </c:if>
                                        <c:if test="${weeklySchedule != null && (actionType == null || actionType eq '')}">
                                        | <a href="#" onclick="copyWeeklySchedule()">Copy Weekly Schedule</a> 
                                        </c:if>
                                        <c:if test="${actionType eq 'copy' && weeklySchedule != null && srcWeeklySchedule != null}">
                                        <a href="#" onclick="confirmCopy()">Confirm</a> | 
                                        </c:if>
                                        <c:if test="${actionType eq 'copy'}">
                                        <a href="#" onclick="cancelCopy()">Cancel</a> 
                                        </c:if>
                                    </td>
				</tr>
				<tr>
					<th width="25%"><fmt:message key="label.setupsch.annualSchedule" /></th>
					<th width="25%">
                                            <select name="annualSch" onchange="selectAnnualSchedule()">
                                                <option value="">--Select--</option>
                                                <c:forEach var="asch" items="${annualScheduleList.getAllAnnualSchedules()}">
                                                    <c:choose>
                                                    <c:when test="${annualSchedule != null && annualSchedule.year eq asch.year}">
                                                        <option value="${asch.year}" selected>${asch.year}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${asch.year}">${asch.year}</option>
                                                    </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </th>
					<th width="25%"><fmt:message key="label.setupsch.weeklySchedule" /></th>
					<th width="25%">
                                            <select name="weeklySch" >
                                                <option value="">--Select--</option>
                                                <c:forEach var="wsch" items="${annualSchedule.getWeeklySchedules()}">
                                                    <c:choose>
                                                    <c:when test="${weeklySchedule != null && weeklySchedule.startDate eq wsch.startDate}">
                                                        <option value="${wsch.startDate}" selected>${wsch.startDate}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${wsch.startDate}">${wsch.startDate}</option>
                                                    </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </th>
                                </tr>
				<tr>
					<td colspan="4" align="center" ><input type="submit" value="Submit" onclick="selectWeeklySchedule()"> <input
						type="reset" value="Reset"></td>
				</tr>
			</table>
		</center>

	</form>
	<c:if test="${! empty  weeklySchedule.getProgramSlots()}">
		<table class="borderAll">
			<tr>
				<th><fmt:message key="label.setupsch.psdate" /></th>
                                <th><fmt:message key="label.setupsch.psstarttime" /></th>
                                <th><fmt:message key="label.setupsch.psname" /></th>
				<th><fmt:message key="label.setupsch.psduration" /></th>
			</tr>
			<c:forEach var="ps" items="${weeklySchedule.getProgramSlots()}" varStatus="status">
				<tr class="${status.index%2==0?'even':'odd'}">
                                        <td class="nowrap">${ps.dateOfProgram}</td>
                                        <td class="nowrap">${ps.startTime}</td>
                                        <td class="nowrap">${ps.programName}</td>
					<td class="nowrap">${ps.duration}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>