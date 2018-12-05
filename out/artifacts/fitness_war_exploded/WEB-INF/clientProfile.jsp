<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text" var="text"/>
<fmt:message bundle="${text}" key="header.button.signOut" var="sign_out"/>
<fmt:message bundle="${text}" key="header.button.profile" var="profile"/>
<fmt:message bundle="${text}" key="header.button.home" var="home"/>
<fmt:message bundle="${text}" key="header.button.buyGymMembership" var="buyGymMembership"/>


<html lang="${sessionScope.language}">
<head>
    <link rel="stylesheet" type="text/css" href="../styless/userProfileStyle.css">
    <meta charset="UTF-8">
    <title>ENAUCT</title>
</head>
<body>

<header>
    <a href="controller?command=main" style="float:left">HOME</a>
    <a href="controller?command=language&currentPage=profile&language=${sessionScope.nextLanguage}" style="float:right">${sessionScope.language}</a>
    <a href="controller?command=sign_out">SIGN OUT</a>
    <a href="controller?command=profile">PROFILE</a>
    <c:if test="${is_gym_membership=='true'}">
        <a href="controller?command=sign_out">${buyGymMembership}</a>
    </c:if>
</header>


<div class = "container">

    <div class="rightcolumn">
        <div class="card">
            <h4>Personal information:</h4>
            <jsp:useBean id="user" type="com.epam.fitness.model.Client" scope="request"/>
            <div class="col-25">
                <label for="Name">Name</label>
            </div>
            <div class="col-75">
                <input type="text" id="Name" name="Name" value="${user.name}" readonly>
            </div>


            <div class="col-25">
                <label for="SurName">Surname</label>
            </div>
            <div class="col-75">
                <input type="text" id="SurName" name="SurName" value="${user.surname}" readonly>
            </div>

            <div class="col-25">
                <label for="login">Login</label>
            </div>
            <div class="col-75">
                <input type="text" id="login" name="login" value="${user.login}" readonly>
            </div>

            <div class="col-25">
                <label for="coach_name">Coach</label>
            </div>
            <div class="col-75">
                <input type="text" id="coach_name" name="coach_name"  value="${coach_name} ${coach_surname}" readonly>
            </div>

            <div class="col-25">
                <label for="visits_number">Visits Number</label>
            </div>
            <div class="col-75">
                <input type="text" id="visits_number" name="visits_number"  value="${user.visitNumber}" readonly>
            </div>

            <div class="col-25">
                <label for="sale">Sale</label>
            </div>
            <div class="col-75">
                <input type="text" id="sale" name="sale"  value="${user.sale}%" readonly>
            </div>

            <div class="col-25">
                <label for="is_corporate">Is Corparative</label>
            </div>
            <div class="col-75">
                <input type="text" id="is_corporate" name="is_corporate"  value="${user.corporate}" readonly>
            </div>
        </div>
    </div>
</div>

<footer>
    <h4>${contact_us}: enauct@gmail.com</h4>
</footer>

</body>
</html>