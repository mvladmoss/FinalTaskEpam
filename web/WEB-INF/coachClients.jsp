<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${all_clients}" var="client">
    <c:out value="${client.name} ${client.surname}"/>
    <form action="${pageContext.servletContext.contextPath}/controller?command=show_client_program" method="post">
        <input type="submit" value="Edit">
    </form>
    <br>
</c:forEach>
</body>
</html>
