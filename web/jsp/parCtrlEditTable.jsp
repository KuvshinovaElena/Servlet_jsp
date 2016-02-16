<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Елена
  Date: 20.12.2015
  Time: 12:49
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
<FORM ACTION="/save" method=service onSubmit="return validate_name(this);">
  <div id="content">
    <input type="hidden" name="meth" value="edittable">
    <%ArrayList<Map<String,String>> list=(ArrayList<Map<String,String>>)request.getAttribute("data");
    %>
    <table border="1">
      <caption>Person</caption>
<%
  for (String key : list.get(0).keySet()) {
    out.println("<th>" + key + "</th>");
  }%>
      <tr>
 <% for (int i=0;i<list.size();i++) {

    for (String key : list.get(i).keySet()) {
      if (key.equals("id")){
      out.println("<input type=\"hidden\" name=\"id\" value="+list.get(i).get(key)+">");
      out.println("<td>"+list.get(i).get(key)+"</td>");
        continue;
        }
      out.println("<td><input class=edit id=fil type=\"text\" onchange=\"return validate_name(this)\" value= " + list.get(i).get(key) + " name= " + key + " required</td>");
    }
    out.println("<tr>");
  }

%>
    </table>
  </div>
  <div id="sidebar">
    <input class=button type=button value=BACK onclick="location.href='/table'">
    <input class=button type=submit value=SEND id=save>
  </div>
</FORM>
</body>
</html>
