<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!doctype html>
<html lang="${sessionScope.language}">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>Whitesquare</title>
    <link rel="stylesheet" href="../styless/baseStyles.css" type="text/css">
    <link rel="stylesheet" href="../styless/clientProfileStyles.css" type="text/css">
    <link rel="stylesheet" href="../styless/clientExerciseStyle.css" type="text/css">
    <link rel="stylesheet" href="../styless/modalWindows.css" type="text/css">
    <link rel="stylesheet" href="../styless/coachesStyles.css" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Staatliches" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Noto+Serif+TC" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Oswald:300" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Oswald:300" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
    <script src="../scripts/button.js"></script>
    <script src="../scripts/setPeriods.js"></script>
    <script src="../scripts/floatWindow.js"></script>
    <script src="../scripts/ajaxConnections.js"></script>
    <script src="../scripts/ajaxRequest.js"></script>
</head>
<body>
<div id="wrapper">
    <jsp:include page="menu.jsp">
        <jsp:param name="pageTopic" value="coach_clients"/>
        <jsp:param name="currentPage" value="all_coach_clients"/>

    </jsp:include>
    <section>
        <div class="container">
            <div class="rightcolumn">
                <c:choose>
                    <c:when test="${fn:length(all_clients) eq 0}">
                        <h3>You don't have clients now</h3>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${all_clients}" var="client">
                            <div class="flex-container" style="margin-left: 0px;">
                                <div class="flex-item">
                                    <div class="textClass" style="width: 130px;font: 20px 'Oswald', sans-serif;padding-top: 2px;text-align: left">
                                        <c:out value="${client.name} ${client.surname}"/>
                                    </div>
                                </div>
                                <div class="flex-item " style="margin-left: 40px;">
                                    <a href="${pageContext.request.contextPath}/controller?command=show_client_exercises&client_id=${client.id}"><img src="../images/gantelya.png" width="40" height="40" alt="Exercises"></a>
                                </div>
                                <div class="flex-item ">
                                    <a href="${pageContext.request.contextPath}/controller?command=show_client_nutrition&client_id=${client.id}"><img src="../images/nutrition.png" width="40" height="40" alt="Exercises"></a>
                                </div>
                                <br>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </section>
</div>

</body>
</html>
