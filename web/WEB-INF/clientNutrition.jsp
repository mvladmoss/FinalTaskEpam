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
    <script src="../scripts/button.js"></script>
    <script src="../scripts/setPeriods.js"></script>
    <script src="../scripts/floatWindow.js"></script>
</head>
<body>
<div id="wrapper">
    <jsp:include page="menu.jsp">
        <jsp:param name="pageTopic" value="nutrition"/>
        <jsp:param name="currentPage" value="show_client_nutrition"/>
    </jsp:include>
    <section>
        <div class="container">
            <div class="rightcolumn">
                <jsp:useBean id="nutrition" class="com.epam.fitness.model.Nutrition" scope="request"/>
                <c:choose>
                    <c:when test="${is_membership_valid == true}" >
                        <c:choose>
                            <c:when test="${nutrition.description eq 'none'}">
                                <div class="text">
                                    <h4>You have no nutrition now</h4>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="text">
                                    <c:out value="${nutrition.description}"/>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <input class="modal__check" type="checkbox" id="modal"/>
                        <div class="modal">
                            <label class="modal__closetwo" for="modal"></label>
                            <div class="modal__info">
                                <label class="modal__close" for="modal">&times;</label>
                                <form name="form" action="${pageContext.request.contextPath}/controller?command=update_nutrition&nutrition_id=${nutrition.id}" method="post">
                            <textarea id="nutrition_description" name="nutrition_description" class="textArea">${nutrition.description}
                            </textarea>
                                    <input class="button" type="submit" value="Save">
                                </form>
                            </div>
                        </div>
                        <label for="modal" class="buttonSub changeButton" style="text-align: center;margin-top: 10px;">Change</label>

                    </c:when>
                    <c:otherwise>
                        <c:out value="You can not have program until you don't buy membership"/>
                        <a href="${pageContext.servletContext.contextPath}/controller?command=get_order_page">Buy</a>
                    </c:otherwise>
                </c:choose>
            </div>

        </div>
    </section>
</div>

</body>
</html>

