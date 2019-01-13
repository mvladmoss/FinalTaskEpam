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
        <jsp:param name="pageTopic" value="exercises"/>
        <jsp:param name="currentPage" value="show_client_exercises"/>
    </jsp:include>
    <section>
        <div class="container">
            <div class="rightcolumn">
                <c:choose>
                    <c:when test="${is_membership_valid == true}">
                        <jsp:useBean id="program" type="com.epam.fitness.model.Program" scope="request"/>
                        <input id="programIdBlock" value="${program.id}" style="display: none;">
                        <div class="tabs">
                            <c:forEach var = "i" begin = "1" end = "${program.trainsPerWeek}">
                                <input class="tabs__tab" type="radio" id="tabs__tab${i}" onclick="setCurrentTrainDayInForm('${i}')" name="tabstab" style="display: none;" checked="checked"/>
                                <label class="tabs__title" for="tabs__tab${i}">Day <c:out value="${i}"/></label>
                                <div class="tabs__text">
                                    <ul>
                                        <c:forEach items="${program.exercises}" var="exerciseDto">
                                            <c:if test="${exerciseDto.numberTrainDay==i}">
                                                <div class="flex-container">
                                                    <div class="flex-item flex-text">
                                                        <c:out value="${exerciseDto.exercise.name}(${exerciseDto.setNumber},${exerciseDto.repeatNumber})"/>
                                                    </div>
                                                    <div class="flex-item ">
                                                        <input class="modal__check" type="checkbox" id="modal${exerciseDto.id}"/>
                                                        <div class="modal">
                                                            <label class="modal__closetwo" for="modal${exerciseDto.id}"></label>
                                                            <div class="modal__info">
                                                                <label class="modal__close" for="modal${exerciseDto.id}">&times;</label>
                                                                <h2 class="edit" >Edit</h2>
                                                                <form action="${pageContext.request.contextPath}/controller?command=update_exercise&exerciseDtoId=${exerciseDto.id}" method="post">
                                                                    <div class="col-25" style="margin-left: -11px;">
                                                                        <label for="setNumber">Number of set</label>
                                                                    </div>
                                                                    <div class="col-75">
                                                                        <input type="text" id="setNumber" style="width: 101%;" name="setNumber">
                                                                    </div>
                                                                    <div class="col-25" style="margin-left: -31px;">
                                                                        <label for="repeats" style="margin-left: 10px;margin-top: 5px;">Repeats</label>
                                                                    </div>
                                                                    <div class="col-75">
                                                                        <input type="text" style="width: 101%; margin-left: 20px;;" id="repeats" name="repeats">
                                                                    </div>
                                                                    <input type="submit" class="buttonSub" value="Update" id="update" style="margin-top: 10px;">
                                                                </form>
                                                            </div>
                                                        </div>
                                                        <label for="modal${exerciseDto.id}"  style="width: 60px;
                                                    height: 22px;padding-top: 10px;"><img class="update" src="../images/update.png" height="40px" width="40px" border="0"></label>
                                                    </div>
                                                    <div class="flex-item delete">
                                                        <p>
                                                            <a href="${pageContext.request.contextPath}/controller?command=delete_exercise&exerciseDtoId=${exerciseDto.id}"><img src="../images/delete.jpg" width="40" height="40" alt="Удалить"></a>
                                                        </p>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </div>

                            </c:forEach>
                        </div>
                        <input class="modal__check" type="checkbox" id="modal"/>
                        <div class="modal">
                            <label class="modal__closetwo" for="modal"></label>
                            <div class="modal__info">
                                <label class="modal__close" for="modal">&times;</label>
                                <input type="search" oninput="makeRequest()" placeholder="Enter the name of exercise" name="searchType" id="searchType" style="width: 30%">
                                <hr style="margin-left: -20px;width: 110%;">
                                <form name="form" class="beatForm" action="${pageContext.request.contextPath}/controller?command=add_exercise&trainDay=${program.trainsPerWeek}" method="post">
                                    <div id="reg" style="display: none">
                                        <input type="button" class="buttonSub" onclick="chang('{exercise.id}')" style='width: 700px;' value="{exercise.name}">
                                        <div id="informer{exercise.id}" class="b-toggle">
                                            <div class="exercise">
                                                <div class="col-25">
                                                    <label for="setNumber" style="margin-top: 5px;">Sets</label>
                                                </div>
                                                <div class="col-75">
                                                    <input id="setNumber{exercise.id}" style="margin-left: -170px;width: 618px;"  type="text">
                                                </div>

                                                <div class="col-25">
                                                    <label for="setNumber" style="margin-top: 5px;">Repeats</label>
                                                </div>
                                                <div class="col-75">
                                                    <input id="repeats{exercise.id}" style="margin-left: -170px;width: 618px;"  type="text">
                                                </div>
                                            </div>
                                            <input type="submit" class="button" onclick="setExerciseProgram('{exercise.id}','{program.id}')" value="Add">
                                        </div>
                                    </div>
                                    <br/>
                                    <hr style="margin-left: -20px;width: 110%;"/>
                                </form>
                            </div>
                        </div>
                        <label for="modal" class="buttonSub" onclick="makeRequest()" style="text-align: center;margin-top: 10px;">Add exercise</label>

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


