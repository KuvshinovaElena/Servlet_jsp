<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: Елена
  Date: 14.12.2015
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SQL Servlet</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
  <div id="header"><h1>Students</h1></div>
  <div id="sidebar">
    <input class=button type=button value=BACK onclick="location.href='/table'" />
    <%
      String id=request.getParameter("id");
      out.println("<input class=button type=button value=EDIT onclick=\"location.href='/person?edit=true&id=" + id +"'\">");%>
  </div>
  <div id="content">
    <table border="1">
      <caption>Person</caption>
     <%
       Map<String, String> params=(Map<String,String>)request.getAttribute("params");
       for(String key:params.keySet()) {
         out.println("<td>" + key + "</td><td>" + params.get(key) + "</td>");
         out.println("<tr>");
       }
     %>
    </table>
  </div>
</body>
</html>
