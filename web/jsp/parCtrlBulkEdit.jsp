<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Елена
  Date: 21.12.2015
  Time: 13:51
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
    <FORM id="bulk" ACTION="/save" method=service onSubmit="return validate_name(this);">
      <div id="content">
        <input type="hidden" name="meth" value=bulkedit>
          <%ArrayList<Map<String,String>> data=(ArrayList<Map<String,String>>)request.getAttribute("data");%>
        <table class="tabl" border="1">
          <caption>Editable entries</caption>
            <%for (String key : data.get(0).keySet()) {%>
          <th><%=key%> </th>
            <% }%>
          <tr>
            <% for (int i=0;i<data.size();i++) {
              for (String key : data.get(i).keySet()) {
              if (key.equals("id")){
              out.println("<input type=\"hidden\" name=\"id\" value="+data.get(i).get(key)+">");
              }
                out.println("<td>" + data.get(i).get(key) + "</td>");
              }

              out.print("</tr>");
            }
            %>
          <tr>
            <br/>
            <br/>
          </table>
        <table class="tabl" border="1">
            <caption>New data</caption>
         <% for (String key : data.get(0).keySet()) {
           if(key.equals("id")){
             continue;
           }
          out.println("<th>" + key + "</th>");
          }%>
          <tr>
            <%for (String key : data.get(0).keySet()) {
                if(key.equals("id")){

                  continue;
                }%>
                <td><input class=edit id=fil type="text" onchange="return validate_name(this)" name="<%=key%>"></td>
                <%}%>
          </table>

      </div>
      <div id="sidebar">
        <input class=button type=button value=BACK onclick="location.href='/table'">
        <input class=button type=submit value=SEND id=save>
      </div>
    </FORM>
</body>
</html>
