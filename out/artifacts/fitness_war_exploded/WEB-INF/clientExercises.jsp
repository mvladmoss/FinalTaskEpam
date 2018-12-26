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
    <link rel="stylesheet" href="../styless/clientProgramStyles.css" type="text/css">
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
    <nav>
        <ul class="top-menu">
            <li id="home"><a href="${pageContext.servletContext.contextPath}/controller?command=main" onmouseover="changeStyle('home')" onmouseout="changeStyleBack('home')">HOME</a></li>
            <li id="signOut" style="position: relative; left: 630px"><a href="${pageContext.servletContext.contextPath}/controller?command=sign_out"  onmouseover="changeStyle('signOut')" onmouseout="changeStyleBack('signOut')">SIGN OUT</a></li>
            <li id="language" style="position: relative; left: 640px"><a href="${pageContext.servletContext.contextPath}/controller?command=language&currentPage=main&language=${sessionScope.nextLanguage}"  onmouseover="changeStyle('language')" onmouseout="changeStyleBack('language')">${sessionScope.language}</a></li>

        </ul>
    </nav>
    <div id="heading">
        <h1>EXERCISES</h1>
    </div>
    <aside>
        <h2>MENU</h2>
        <nav>
            <ul class="aside-menu">
                <c:if test="${role == 'client'}">
                    <li id="profile">
                        <a href="${pageContext.servletContext.contextPath}/controller?command=profile" onmouseover="changeStyle('profile')" onmouseout="changeStyleBack('profile')" >Profile</a>
                    </li>
                    <li id="my_exercises" >
                        <a href="${pageContext.servletContext.contextPath}/controller?command=show_client_exercises" onmouseover="changeStyle('my_exercises')" onmouseout="changeStyleBack('my_exercises')">My exercises</a>
                    </li>
                    <li id="my_nutrition" >
                        <a href="${pageContext.servletContext.contextPath}/controller?command=show_client_nutrition" onmouseover="changeStyle('my_nutrition')" onmouseout="changeStyleBack('my_nutrition')">My nutrition</a>
                    </li>
                    <li id="buyMembership">
                        <a href="${pageContext.servletContext.contextPath}/controller?command=get_order_page" onmouseover="changeStyle('buyMembership')" onmouseout="changeStyleBack('buyMembership')" >Buy membership</a>
                    </li>
                    <li id="coaches">
                        <a href="${pageContext.servletContext.contextPath}/controller?command=coaches"  onmouseover="changeStyle('coaches')" onmouseout="changeStyleBack('coaches')">Our coaches</a>
                    </li>
                </c:if>
                <c:if test="${role == 'coach'}">
                    <li id="myClients">
                        <a href="${pageContext.servletContext.contextPath}/controller?command=all_coach_clients" onmouseover="changeStyle('myClients')" onmouseout="changeStyleBack('myClients')">My clients</a>
                    </li>
                </c:if>
            </ul>
        </nav>
        <h2>OUR GYM</h2>
        <p>
            <img src="../images/gym.jpg"  width="250" height="181" alt="Our offices">
        </p>
    </aside>
    <section>
        <div class="container">
            <div class="rightcolumn">
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
                                            <li><c:out value="${exerciseDto.exercise.name}(${exerciseDto.setNumber},${exerciseDto.repeatNumber})"/></li>
                                            <div class="flex-container">
                                                <div class="flex-item ">
                                                    <form action="${pageContext.request.contextPath}/controller?command=delete_exercise&exerciseDtoId=${exerciseDto.id}" method="post">
                                                        <input type="submit" class="buttonSub" value="Delete">
                                                    </form>
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
                                                                        <label for="repeats">Repeats</label>
                                                                    </div>
                                                                    <div class="col-75">
                                                                        <input type="text" style="width: 101%; margin-left: 20px;;" id="repeats" name="repeats">
                                                                    </div>
                                                                    <input type="submit" class="buttonSub" value="Update" id="update" style="margin-top: 10px;">
                                                                </form>
                                                        </div>
                                                    </div>
                                                    <label for="modal${exerciseDto.id}" class="buttonSub" style="width: 60px;
                                                    height: 22px;padding-top: 10px;">Update</label>

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
                        <input type="search" oninput="makeRequest()" name="searchType" id="searchType">
                        <hr style="margin-left: -20px;width: 110%;">
                        <form name="form" action="${pageContext.request.contextPath}/controller?command=add_exercise&trainDay=${program.trainsPerWeek}" method="post">

                        </form>
                    </div>
                </div>
                <label for="modal" class="buttonSub" onclick="makeRequest()" style="text-align: center;margin-top: 10px;">Add exercise</label>
            </div>

        </div>
    </section>
</div>

</body>
</html>




<%--<c:forEach items="${exercises}" var="exercise">
    <div id="reg">
        <input type="button" class="button" onclick="chang(${exercise.id})" value="${exercise.name}">
        <div id="informer${exercise.id}" class="b-toggle">
            <div class="exercise">
                <div class="col-25">
                    <label for="setNumber">Sets</label>
                </div>
                <div class="col-75">
                    <input id="setNumber${exercise.id}"  type="text">
                </div>

                <div class="col-25">
                    <label for="setNumber">Repeats</label>
                </div>
                <div class="col-75">
                    <input id="repeats${exercise.id}"  type="text">
                </div>
            </div>
            <input type="submit" class="button" onclick="setExerciseProgram('${exercise.id}','${program.id}')" value="Add">
        </div>
    </div>
    <br/>
    <hr style="margin-left: -20px;width: 110%;"/>
</c:forEach>--%>


