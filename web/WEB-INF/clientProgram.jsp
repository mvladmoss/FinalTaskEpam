<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Client Program</title>
</head>
<body>
    <jsp:useBean id="programDto" type="com.epam.fitness.model.dto.ProgramDto" scope="request"/>
    <h2>Program name:${programDto.program.name}(${programDto.program.trainsPerWeek } trains every week)</h2>
    <hr>
    <h2>Exercises</h2>
    <c:forEach var = "i" begin = "1" end = "${programDto.program.trainsPerWeek}">
        <h3>Day <c:out value="${i}"/></h3>
        <ul>
            <c:forEach items="${programDto.exercises}" var="exerciseDto">
                <c:if test="${exerciseDto.numberTrainDay==i}">
                    <li><c:out value="${exerciseDto.exercise.name}(${exerciseDto.setNumber},${exerciseDto.repeatNumber})"/></li>
                </c:if>
            </c:forEach>
        </ul>
    </c:forEach>

</body>
</html>
