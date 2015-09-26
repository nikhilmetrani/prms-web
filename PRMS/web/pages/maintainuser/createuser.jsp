<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="adel" class="sg.edu.nus.iss.phoenix.authenticate.delegate.AuthenticateDelegate" scope="page"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

        <fmt:setBundle basename="ApplicationResources" />
        <script type="text/javascript">

            function validateForm()
            {
                var x = document.forms["usrForm"]["id"].value;
                var alertMsg;
                if (x == null || x == "")
                {
                    //alertMsg = alertMsg+"Please enter Id";
                    alert("Please enter Id");
                    return false;
                }
                x = document.forms["usrForm"]["name"].value;
                if (x == null || x == "")
                {   
                    alertMsg=alertMsg\u000A+"Please enter User Name";
                    //alert("Please enter User Name");
                    //return false;
                }
                x = document.forms["usrForm"]["password"].value;
                if (x == null || x == "")
                {
                    alertMsg=alertMsg\u000A+"Please enter password";
                    //alert("Please enter password");
                    //return false;
                }
                //if(alertMsg === undefined){
                //    alert(alertMsg);
               //     return false;
              //  }
            }
            function setDispatchAction(value)
            {
                document.forms["usrForm"]["action"].value = value;
                //alert(document.forms["usrForm"]["action"].value);
            }
        </script>        
        <title><fmt:message key="title.manageuser" /></title>
    </head>

    <body>
        <c:set var="usr" value="${adel.findUser(param['id'])}"/>
        <form name="usrForm" action="${pageContext.request.contextPath}/nocturne/adduser" method="post" >
            ${errorMessage}
            ${successMessage}
            <p id="demo"></p>
            <center>
                <input type="hidden" name="currId" value="${param['id']}" >
                <input type="hidden" name="ins" value="${param['insert']}" />
                <table cellpadding=4 cellspacing=2 border=0>

                    <tr>
                        <th><fmt:message key="label.cruduser.id" /></th>
                        <td>
                            <input type="text" name="id" value="${usr.getId()}" size=15 
                                   maxlength=20>
                        </td>
                    </tr>
                    <tr>
                        <th><fmt:message key="label.cruduser.name" /></th>
                        <td>
                            <input type="text" name="name" value="${usr.getName()}" size=15
                                   maxlength=20>
                        </td>
                    </tr>
                    <tr>
                        <th><fmt:message key="label.cruduser.password" /></th>
                        <td>
                            <input type="text" name="password" value="${usr.getPassword()}" size=15
                                   maxlength=20>
                        </td>
                    </tr>    

                    <tr>
                        <th valign="top"><fmt:message key="label.cruduser.role" /></th>
                            <c:set var="strRoles" value="${usr.getRoleString()}"/>
                        <td>
                            <select name="role" id="role" multiple="multiple" style="width:125px">
                                <c:forEach var="item" items="${adel.findAllRole()}" >
                                    <c:set var="strRole" value="${item.getRole()}" />
                                    <c:choose>
                                        <c:when test="${fn:contains(strRoles, strRole)}">
                                            <option value="${item.getRole()}" selected="true">${item.getRole()}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${item.getRole()}">${item.getRole()}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>                              
                        </td>
                    </tr>
                </table>
                <input type="hidden" name="action">
                <input type="submit" value="Submit" onClick="setDispatchAction('saveupdate')"> <input type="reset" value="Reset">
                <c:choose>
                    <c:when test="${psList.size() == 0}">
                        <input type="submit" value="Delete" onClick="setDispatchAction('delete')">
                    </c:when>
                </c:choose>
            </center>

            <!--                <input type="submit" value="Submit" onclick="validateInput()"> 
            <input type="reset" value="Reset">
            -->

        </form>


    </body>