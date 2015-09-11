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
</script>
</head>
<body>
	<h2>
		<fmt:message key="title.setupSchedule" />
	</h2>
	<form action="${pageContext.request.contextPath}/nocturne/viewSchedule"
		method=post>
		<center>
			<table class="framed">
				<tr>
                                    <td colspan="2" align="left">
                                        <a href="#" onclick="">Create Annual Schedule</a> 
                                        | <a href="#" onclick="">Copy Weekly Schedule</a>
                                    </td>
				</tr>
				<tr>
					<th width="25%"><fmt:message key="label.setupsch.annualSchedule" /></th>
					<th width="25%">
                                            <select name="annualSch" onchange="selectAnnualSchedule()">
                                                <option value="">--Select--</option>
                                                <c:forEach var="asch" items="${annualScheduleList.getAllAnnualSchedules()}">
                                                    <c:if test="${annualSchedule != null && annualSchedule.year eq asch.year}">
                                                        <option value="${asch.year}" selected>${asch.year}</option>
                                                    </c:if>
                                                    <c:if test="${annualSchedule == null}">
                                                        <option value="${asch.year}">${asch.year}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </th>
					<th width="25%"><fmt:message key="label.setupsch.weeklySchedule" /></th>
					<th width="25%">
                                            <select name="weeklySch">
                                                <option value="">--Select--</option>
                                                <c:forEach var="wsch" items="${annualSchedule.getWeeklySchedules()}">
                                                    <option value="${wsch.startDate}">${wsch.startDate}</option>
                                                </c:forEach>
                                            </select>
                                        </th>
                                </tr>
				<tr>
					<td colspan="4" align="center" ><input type="submit" value="Submit"> <input
						type="reset" value="Reset"></td>
				</tr>
			</table>
		</center>

	</form>
	<!--<c:if test="${! empty  searchrplist}">
		<table class="borderAll">
			<tr>
				<th><fmt:message key="label.radioprogram.name" /></th>
				<th><fmt:message key="label.radioprogram.description" /></th>
				<th><fmt:message key="label.radioprogram.duration" /></th>
			</tr>
			<c:forEach var="rprogram" items="${searchrplist}" varStatus="status">
				<tr class="${status.index%2==0?'even':'odd'}">
					<td class="nowrap">${rprogram.name}</td>
					<td class="nowrap">${rprogram.description}</td>
					<td class="nowrap">${rprogram.typicalDuration}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>-->

</body>
</html>