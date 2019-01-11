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
        <jsp:param name="pageTopic" value="coaches"/>
        <jsp:param name="currentPage" value="coaches"/>

    </jsp:include>
    <section>
        <div class="container">
            <div class="rightcolumn">
                <ul>
                    <c:choose>
                        <c:when test="${coach_client_id!=null}">
                            <c:forEach items="${coaches}" var="coach">
                                <div>
                                    <c:choose>
                                        <c:when test="${coach_client_id==coach.id}">
                                            <li class="coach"><c:out value="${coach.name} ${coach.surname}(current coach)"/></li>
                                            <input class="modal__check" type="checkbox" id="modal"/>
                                            <div class="modal">
                                                <label class="modal__closetwo" for="modal"></label>
                                                <div class="modal__info">
                                                    <label class="modal__close" for="modal">&times;</label>
                                                    <form name="form" action="${pageContext.request.contextPath}/controller?command=add_comment&coach_id=${coach.id}" method="post">
                                                        <textarea id="commentContent" name="commentContent" class="textArea"></textarea>
                                                        <input class="button" type="submit" value="Save">
                                                    </form>
                                                </div>
                                            </div>
                                            <label for="modal" class="buttonSub changeButton" style="text-align: center;margin-top: 10px;">Comment</label>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="coach"><c:out value="${coach.name} ${coach.surname} "/></li>
                                            <form action="${pageContext.servletContext.contextPath}/controller?command=choose_coach&coachId=${coach.id}" method="post">
                                                <input class="button li_button" type="submit" value="Change">
                                            </form>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${coaches}" var="coach">
                                <div>
                                    <li class="coach"><c:out value="${coach.name} ${coach.surname}"/></li>
                                    <form action="${pageContext.servletContext.contextPath}/controller?command=choose_coach&coachId=${coach.id}" method="post">
                                        <input class="button li_button" type="submit" value="Choose">
                                    </form>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </section>
</div>

</body>
</html>


