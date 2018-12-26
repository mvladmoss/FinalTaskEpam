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
    <link rel="stylesheet" href="../styless/clientProgramStyless.css" type="text/css">
    <link rel="stylesheet" href="../styless/modalWindows.css" type="text/css">
    <link rel="stylesheet" href="../styless/coachesStyle.css" type="text/css">
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
                <c:forEach items="${all_clients}" var="client">
                <div class="flex-container">
                    <div class="flex-item">
                        <div class="textClass">
                        <c:out value="${client.name} ${client.surname}"/>
                        </div>
                    </div>
                    <div class="flex-item ">
                        <form action="${pageContext.request.contextPath}/controller?command=show_client_exercises&client_id=${client.id}" method="post">
                            <input type="submit" class="buttonSub" value="Exercises">
                        </form>
                    </div>
                    <div class="flex-item ">
                        <form action="${pageContext.request.contextPath}/controller?command=show_client_nutrition&client_id=${client.id}" method="post">
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
