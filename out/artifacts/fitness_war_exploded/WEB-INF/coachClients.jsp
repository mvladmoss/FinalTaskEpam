<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="${sessionScope.language}">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>Whitesquare</title>
    <link rel="stylesheet" href="../styless/baseStyles.css" type="text/css">
    <link rel="stylesheet" href="../styless/clientProfileStyle.css" type="text/css">
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
    <script src="../scripts/ajaxConnection.js"></script>
    <script src="../scripts/ajaxRequest.js"></script>
    <script src="../scripts/search.js"></script>
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
                <c:forEach items="${all_clients}" var="client">
                <div class="flex-container">
                    <div class="flex-item">
                        <div class="textClass">
                        <c:out value="${client.name} ${client.surname}"/>
                        </div>
                    </div>
                    <div class="flex-item ">
                        <form action="${pageContext.request.contextPath}/controller?command=show_client_exercises&id_coach's_client=${client.id}" method="post">
                            <input type="submit" class="buttonSub" value="Exercises">
                        </form>
                    </div>
                    <div class="flex-item ">
                        <form action="${pageContext.request.contextPath}/controller?command=show_client_nutrition&id_coach's_client=${client.id}" method="post">
                            <input type="submit" class="buttonSub" value="Nutrition">
                        </form>
                    </div>
                    <br>
                </div>
                </c:forEach>
            </div>
        </div>
    </section>
</div>

</body>
</html>
