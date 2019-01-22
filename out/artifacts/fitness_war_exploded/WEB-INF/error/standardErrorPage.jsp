<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Show Error Page</title>
</head>

<body>
<h1>Opps...</h1>
<table width = "100%" border = "1">
    <tr valign = "top">
        <td width = "40%"><b>Error:</b></td>
        <td>${pageContext.exception.message}</td>
    </tr>

    <tr valign = "top">
        <td><b>URI:</b></td>
        <td>${pageContext.errorData.requestURI}</td>
    </tr>

    <tr valign = "top">
        <td><b>Status code:</b></td>
        <td>${pageContext.errorData.statusCode}</td>
    </tr>


    <tr valign = "top">
        <td><b>Stack trace:</b></td>
        <td>
                <p>${pageContext.exception.printStackTrace()}</p>
        </td>
    </tr>
</table>

</body>
</html>
