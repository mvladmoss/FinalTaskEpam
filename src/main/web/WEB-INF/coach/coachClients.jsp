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
    <link rel="stylesheet" href="../../styless/baseStyles.css" type="text/css">
    <link rel="stylesheet" href="../../styless/client/clientProfileStyles.css" type="text/css">
    <link rel="stylesheet" href="../../styless/client/clientExerciseStyle.css" type="text/css">
    <link rel="stylesheet" href="../../styless/modalWindows.css" type="text/css">
    <link rel="stylesheet" href="../../styless/coach/coachesStyles.css" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Oswald:300" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
    <script src="../../scripts/notification.js"></script>

</head>
<body>
<div id="wrapper">
    <jsp:include page="../menu.jsp">
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
                                    <form action="${pageContext.request.contextPath}/controller?command=show_client_exercises" method="post">
                                        <input id="coach_client_id" name="coach_client_id" value="${client.id}" style="display: none;">
                                        <button type="submit" style="display: contents;"><img src="../../images/gantelya.png" width="40" height="40" alt="Exercises" style="display: inline;"></button>
                                    </form>
                                </div>
                                <div class="flex-item ">
                                    <form action="${pageContext.request.contextPath}/controller?command=show_client_nutrition" method="post">
                                        <input id="coach_client_id" name="coach_client_id" value="${client.id}" style="display: none;">
                                        <button type="submit" style="display: contents;"><img src="../../images/nutrition.png" width="40" height="40" alt="Exercises" style="display: inline"></button>
                                    </form>
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
<c:if test="${incorrect_input_data_error eq true}">
    <script>notifyAboutInvalidData('Incorrect exercise data was input')</script>
</c:if>

<c:if test="${incorrect_input_nutrition_data_error eq true}">
    <script>notifyAboutInvalidData('Incorrect nutrition data was input')</script>
</c:if>


</body>
</html>
