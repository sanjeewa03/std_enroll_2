<%-- 
    Document   : editCourses
    Created on : Sep 23, 2019, 4:07:41 PM
    Author     : sanjeewa_s
--%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table style="width: 50%">
            <tr>
                <th>Subject Code</th>
                <th>Subject Name</th>
                <th>Credit</th>
            </tr>
            <s:iterator value="subjects" >
                <tr>
                    <td style="background: grey"><s:property value="sub_code"/></td>
                    <td style="background: grey"><s:property value="sub_name"/></td>
                    <td style="background: grey"><s:property value="credit"/></td>
                </tr>
            </s:iterator>
        </table>

        <div class="pagination">
            <s:iterator status="stat" value="(count).{ #this }" >
                <a href="
                   <s:url action='ViewSubjects'>
                       <s:param name="page">5</s:param>
                       <s:param name="offset" value="#stat.count"/>
                   </s:url>
                   "><s:property value="#stat.count" /></a>
            </s:iterator>

        </div>
        <br>
        <a href="<s:url action='PDFDownloader' />">Download Subject List PDF</a>
        <br>
        <a href="<s:url action='ExcelDownloader' />">Download Subject List Excel</a>
        <br>
        <p><a href="<s:url action='Logout'/>">Logout</a></p>
        <br>
        <s:property value="count"/>
    </body>
</html>
