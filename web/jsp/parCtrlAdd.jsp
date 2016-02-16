<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Елена
  Date: 15.12.2015
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <script type="text/javascript" src="/validat.js"></script>
  <title>SQL Servlet</title>
  <link rel="stylesheet" type="text/css" href="../css/style.css"/>

</head>
<body>
  <div id="header"><h1>Students</h1></div>

  <FORM id="add" ACTION="/save" method=service onSubmit="return validate_name(this);">
    <div id="content">
    <input type="hidden" name="meth" value=add>
    <table border="3">
      <caption>Person</caption>
      <%
        List<String> col=(List<String>) request.getAttribute("col");
        for (String s: col){
          if(s.equals("id"))
            continue;
          out.println("<td>" + s + "</td><td><input class=inp id=fil type=\"text\" onchange=\"return validate_name(this)\" name=" + s +" required</td>");
          out.println("<tr>");
          }%>
    </table>
      </div>
    <div id="sidebar">
      <input class=button type=button value=BACK onclick="location.href='/table'" />
      <input class=button type=submit value=SEND id=save>
    </div>
  </FORM>

</body>
</html>
