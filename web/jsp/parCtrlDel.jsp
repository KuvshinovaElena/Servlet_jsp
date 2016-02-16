<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Елена
  Date: 20.12.2015
  Time: 18:09
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
  <input class=button type=button value=CANSEL onclick="location.href='/table'" />
  <%
    ArrayList<Map<String, String>> params=(ArrayList<Map<String,String>>)request.getAttribute("params");
    String delid=null;
    for (int i=0;i<params.size();i++) {
      delid = "id="+params.get(i).get("id")+",";
    }
    delid=delid.substring(0,delid.length()-1);
    out.println("<input class=button type=button value=OK onclick=\"location.href='/save?meth=del&" + delid +"'\">");%>
</div>
<div id="content">
  <table class="tabl" border="1">
      <caption>Are you sure you want to delete these records?</caption>
    <%
      for (String key : params.get(0).keySet()) {%>
      <th><%=key%> </th>
     <% }%>
      <tr>
     <% for (int i=0;i<params.size();i++) {

      for (String key : params.get(i).keySet()) {
        out.println("<td>" + params.get(i).get(key) + "</td>");
        }
      out.print("</tr>");
      }
    %>

      </tr>
  </table>
</div>
</body>
</html>
