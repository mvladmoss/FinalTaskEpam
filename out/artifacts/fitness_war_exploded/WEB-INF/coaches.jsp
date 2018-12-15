<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>CoachesPage</title>
</head>
<body>
    <c:forEach items="${coaches}" var="coach">
            <div>
                <c:out value="${coach.name} ${coach.surname} ${coach.id}"/>
                <a href="${pageContext.servletContext.contextPath}/controller?command=choose_coach&coachId=${coach.id}">Choose</a>
                <hr>
            </div>
    </c:forEach>
</body>
</html>
