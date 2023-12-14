<%-- 
    Document   : user
    Created on : Oct 18, 2023, 2:28:37 PM
    Author     : akshaydatir
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h2>Using Core Tags</h2>
        <%
           
            List<Integer> list = Arrays.asList(1, 2, 3);
        %>

        <c:forEach items="<%= list%>" var="user">
            <p>ID: <c:out value="${user}" /></p>
        </c:forEach>
        <hr> 
        <h2>Using Function Tags</h2>

        <c:set var="String" value="Welcome Akshay!!!"/>  
        <p>1)Find Akshay in string = Welcome Akshay!!! using Function tag<p>
            <c:if test="${fn:containsIgnoreCase(String, 'Akshay')}">  
            <p>Found Akshay in string<p>  
            </c:if>

            <c:set var="String" value="second first one four three"/>
        <p>2)Find index of second and first in string = second first one four three using Function tag<p>
        <p>Index-1 : ${fn:indexOf(String, "second")}</p>  
        <p>Index-2 : ${fn:indexOf(String, "first")}</p> 

        <c:set var="str1" value="Welcome to JSP        programming         "/> 
        <p>3)Trim String "Welcome to JSP        programming         "</p>
        <p>String-1 Length is : ${fn:length(str1)}</p>  

        <c:set var="str2" value="${fn:trim(str1)}" />  
        <p>String-2 Length is : ${fn:length(str2)}</p>  
        <p>Final value of string is : ${str2}</p>  
        <hr>
        <h2>Using Formatting Tags</h2>
        <p>1)Parsed Date: 1997-11-06 </p>  
        <c:set var="date" value="1997-11-06" />  
        <fmt:parseDate value="${date}" var="parsedDate"  pattern="yyyy-MM-dd" />  
        <p><c:out value="${parsedDate}" /></p>  

        <p>2)Parse double Amount
            <c:set var="Amount" value="235535.57456756" />  
            <fmt:parseNumber var="j" type="number" value="${Amount}" />  
        <p><i>Amount is:</i>  <c:out value="${j}" /></p>  

        <fmt:parseNumber var="j" integerOnly="true" type="number" value="${Amount}" />  
        <p><i>Amount is:</i>  <c:out value="${j}" /></p>

        <p>3) Formating <%=new java.util.Date()%> time Zone to Asia/Kolkata</p>
        <c:set var="str" value="<%=new java.util.Date()%>" />  
        <%
            Date currentTime = new Date();
            TimeZone istTimeZone = TimeZone.getTimeZone("Asia/Kolkata");
        %>
        <table border="1">    
            <set var="zone" value="<%= istTimeZone%>">  
            <tr>  
                <td>  
                    <c:out value="Asia/Kolkata"/>  
                </td>  
                <td>  
                    <fmt:timeZone value="${zone}">  
                        <fmt:formatDate value="${str}" timeZone="${zn}"  
                                        type="both"/>  
                    </fmt:timeZone>  
                </td>  
            </tr>  
        </table> 
        <hr>
</body>
</html>
