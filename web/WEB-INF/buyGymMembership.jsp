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
    <link href="https://fonts.googleapis.com/css?family=Staatliches" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Noto+Serif+TC" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Oswald:300" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Oswald:300" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
    <link href="../styless/buyGymMembershipStyles.css" rel="stylesheet">
    <script src="../scripts/button.js"></script>
    <script src="../scripts/setCosts.js"></script>
    <script src="../scripts/setCommands.js"></script>

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
        <h1>BUY MEMBERSHIP</h1>
    </div>
    <aside>
        <h2>MENU</h2>
        <nav>
            <ul class="aside-menu">
                <c:if test="${role == 'client'}">
                    <li id="profile">
                        <a href="${pageContext.servletContext.contextPath}/controller?command=profile" onmouseover="changeStyle('profile')" onmouseout="changeStyleBack('profile')" >Profile</a>
                    </li>
                    <li id="program" >
                        <a href="${pageContext.servletContext.contextPath}/controller?command=show_client_program" onmouseover="changeStyle('program')" onmouseout="changeStyleBack('program')">My program</a>
                    </li>
                    <li id="buyMembership">
                        <a href="${pageContext.servletContext.contextPath}/controller?command=get_order_page" onmouseover="changeStyle('buyMembership')" onmouseout="changeStyleBack('buyMembership')" >Buy membership</a>
                    </li>
                </c:if>
                <c:if test="${role == 'coach'}">
                    <li id="myClients">
                        <a href="${pageContext.servletContext.contextPath}/controller?command=all_coach_clients" onmouseover="changeStyle('myClients')" onmouseout="changeStyleBack('myClients')">My clients</a>
                    </li>
                </c:if>
                <li id="prices">
                    <a href="/vestibulum/" onmouseover="changeStyle('prices')" onmouseout="changeStyleBack('prices')">Prices</a>
                </li>
                <li id="coaches">
                    <a href="${pageContext.servletContext.contextPath}/controller?command=coaches"  onmouseover="changeStyle('coaches')" onmouseout="changeStyleBack('coaches')">Our coaches</a>
                </li>
            </ul>
        </nav>
        <h2>OUR GYM</h2>
        <p>
            <img src="../images/gym.jpg" o width="250" height="181" alt="Our offices">
        </p>
    </aside>
    <section>
        <div class = "container">

            <div class="rightcolumn">
                <div class="card">
                    <div class="col-25">
                        <label for="period_cost">Select the duration</label>
                    </div>
                    <div class="col-75">
                        <select id="period_cost" onchange="setCosts()" name="period">
                            <option value="Choose tariff"></option>
                            <c:forEach items="${tariffs}" var="tariff">
                                <option value="${tariff.value}">${tariff.key}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-25">
                        <label for="cost" voc>Cost</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="cost" name="cost" value="0.0$" readonly>
                    </div>

                    <div class="col-25">
                        <label for="personal_sale">Personal sale</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="personal_sale" name="personal_sale" value="${client_personal_sale}" readonly>
                    </div>

                    <div class="col-25">
                        <label for="corporate_sale">Corporate sale</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="corporate_sale" name="corporate_sale" value="${client_corporate_sale}" readonly>
                    </div>

                    <div class="col-25">
                        <label for="final_cost">Final cost</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="final_cost" name="final_cost"  value="0.0$" readonly>
                    </div>

                    <form name="form" action="${pageContext.servletContext.contextPath}/controller?command=update_gym_membership" method="post">
                        <div  class="findButtonDiv">
                            <c:choose>
                                <c:when test="${end_date_membership=='true'}">
                                    <%--<h3>You have a valid membership</h3>--%>
                                    <input type="submit" class="button" value="Extend" id="findButton" style="margin: 10px 15px 5px -10px;">
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" class="buttonSub" value="Buy" id="findButton">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>

</body>
</html>




