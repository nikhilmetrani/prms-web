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
    
    function popupPresenterPage(){        
        var url = "/phoenix/nocturne/searchpresenter";        
        window.open(url,"","dialogWidth:950px;dialogHeight:400px"); 
    }
    
   
    
    function popupProducerPage(){
        
        var url = "/phoenix/nocturne/searchproducer";              
        window.open(url,"","dialogWidth:950px;dialogHeight:400px");  
    }
    
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

    function modifyProgramSlot(){
        document.forms[0].actionType.value = "modifyPgmSlot";
        document.forms[0].action = "${pageContext.request.contextPath}/nocturne/modifyPgmSlot";
        document.forms[0].submit();
    }

</script>
</head>
<body>
        <h2>
		<fmt:message key="title.modifyps" />
	</h2>
	<form action="${pageContext.request.contextPath}/nocturne/modifyps" method=post>
            <input type="hidden" name="actionType" value="${actionType}" />
		<center>
			<table cellpadding=5 cellspacing=2 border=0>
				<tr>
					<th width="30%"><fmt:message key="label.modifyps.value" /></th>
					<th width="45%"><fmt:message key="label.modifyps.data" /></th>
				</tr>
                                
                                <tr>
					<th width="25%"><fmt:message key="label.setupsch.annualSchedule" /></th>
					<th width="25%">
                                            <select required name="annualSch" onchange="selectAnnualSchedule()">
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
                                </tr>
                                <tr>
					<th width="25%"><fmt:message key="label.setupsch.weeklySchedule" /></th>
					<th width="25%">
                                            <select required name="weeklySch" onchange="selectWeeklySchedule()">
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
					<th width="25%"><fmt:message key="label.modifyps.programDate" /></th>
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
                               <tr>
                                    <th width="25%"><fmt:message key="label.presenter.id" /></th>
				    <th width="25%"><input type="text" name="presenterName" size=25 maxlength=40 title="Please choose the presenter" required readonly>
                                        <c:url var="url" scope="page" value="/nocturne/searchpresenter"> </c:url>
                                        <a href="#" onclick="popupPresenterPage()">Select</a>    
                                    </th>
				</tr>
                                
                                 <tr>
                                    <th width="25%"><fmt:message key="label.producer.id" /></th>
				    <th width="25%"><input type="text" name="producerName" size=25 maxlength=40 title="Please choose the producer" required readonly>
                                        <c:url var="url" scope="page" value="/nocturne/searchproducer"> </c:url>
                                        <a href="#" onclick="popupProducerPage()">Select</a>    
                                    </th>
				</tr>
			</table>
                                         <input  type="submit" value="Submit"> <input  type="reset" value="Reset">
                                         
		</center>                     
	</form>    
</body>
</html>