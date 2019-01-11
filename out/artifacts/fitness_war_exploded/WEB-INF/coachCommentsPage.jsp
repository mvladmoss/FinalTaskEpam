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
    <link rel="stylesheet" href="../styless/clientNutritionStyles.css" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Staatliches" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Noto+Serif+TC" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Oswald:300" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Oswald:300" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
</head>
<body>
<div id="wrapper">
    <jsp:include page="menu.jsp">
        <jsp:param name="pageTopic" value="about_us"/>
        <jsp:param name="currentPage" value="show_client_nutrition"/>

    </jsp:include>
    <section>
        <div class="container">
            <div class="rightcolumn">
                <c:forEach var="coachComment" items="${comments}">
                    <c:out value="${coachComment.commmentContent}"/>
                    <hr>
                    <br>
                </c:forEach>
            </div>

        </div>
    </section>
</div>

</body>
</html>

