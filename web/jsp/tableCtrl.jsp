<%@ page import="java.util.List" %>
<%@ page import="bean.Person" %>

<%--
  Created by IntelliJ IDEA.
  User: Елена
  Date: 14.12.2015
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>

<%@page language="java" import="java.util.*" %>
<html>
<head>
  <script type="text/javascript" src="/check.js"></script>
    <script type="text/javascript" src="/validat.js"></script>
  <title>SQL Servlet</title>
  <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
  <div id="header">
    <h1>Students</h1>
  </div>
<form action="/person">
    <div id="sidebar">
      <input class=button type=button value=ADD onclick="location.href='/person?add=true'">
      <button class="button" type="submit" name="del" value="true" onclick="return checked(this);">DELETE</button>
      <button class=button type=submit name="edittable" value="true" >EDIT</button>
        <button class="button" type="submit" name="bulkedit" value="true" onclick="return checked(this);">BULK EDIT</button>
        <input class=button type=button value=CLEAR id=save onclick="if(confirm('Are you sure you want to clear the entire table?')) location.href='/save?meth=clean'; else return false;">
    </div>
  <div id="content">
    <table border="1">
      <tr>
        <th><p><input id="one" class=check type="checkbox" name="one" value="all" onclick="bulkCheck(this)" /></p></th>
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th> </th>
        <%
          List<Person> persons = new ArrayList<Person>();
          persons = (List<Person>)request.getAttribute("data");
          int i=0;
         if (!persons.isEmpty()) {
            for (Person p:persons ) {%>
              <tr><td><input type=checkbox class=checkbox name="checkId" value="<%=Long.toString(p.getId())%>"></td>
             <% out.println("<td><a href=\"/person?edit=false&id=" + p.getId() + "\">" + p.getId() + "</a></td>");
              out.println("<td>" + p.getSurname() + "</td><td>"+p.getName()+"</td>");
              out.println("<td><input class=del type=button value=DEL id=save onclick=\"location.href='/save?meth=del&id="+p.getId()+"'\"></td></tr>");
            }
         }
        %>
      </tr>
    </table>
  </div>
  </form>
</body>
</html>
