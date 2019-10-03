<%-- 
    Document   : teacherHome
    Created on : Sep 25, 2019, 2:56:44 PM
    Author     : sanjeewa_s
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>teacher Home</title>
    </head>
    <body>
        <h1>Teacher Home</h1><br><br>
        <p><a href="<s:url action='ViewSubjects'>
                  <s:param name="page">5</s:param>
                  <s:param name="offset">1</s:param>
              </s:url>">All Subjects</a></p>
        <br><br>
        <p><a href="<s:url action='Logout'/>">Logout</a></p>
    </body>
</html>
