<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text" var="text"/>

<fmt:message bundle="${text}" key="header.button.signOut" var="sign_out"/>
<fmt:message bundle="${text}" key="header.button.profile" var="profile"/>
<fmt:message bundle="${text}" key="header.button.home" var="home"/>
<fmt:message bundle="${text}" key="header.button.coaches" var="coaches"/>
<fmt:message bundle="${text}" key="header.button.buyGymMembership" var="buy_gym_membership"/>


<html lang="${sessionScope.language}">
<head>
    <link rel="stylesheet" type="text/css" href="../styless/mainStyle.css">
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>

<header>
    <img width="47" height="47" class="logo-header" src="../pictures/LOGO_1.png" alt="">
    <a href="controller?command=main" style="float:left">${home}</a>
    <a href="controller?command=language&currentPage=main&language=${sessionScope.nextLanguage}"
       style="float:right">${sessionScope.language}</a>
    <a href="controller?command=sign_out">${sign_out}</a>
    <a href="controller?command=profile">${profile}</a>
    <c:if test="${is_gym_membership=='true'}">
        <c:redirect url="buyGymMembership.jsp"/>
    </c:if>
    <div class="welcome">
        <h3>Welcome ${name} ${surname}</h3>
    </div>
</header>

<div class="card">
    <div class="row">
        <div class="side">
            <a href="controller?command=coaches" >${coaches}</a>
        </div>


    </div>
</div>

<footer>
    <h4>${contact_us}: atlet-center@gmail.com</h4>
</footer>

</body>
</html>